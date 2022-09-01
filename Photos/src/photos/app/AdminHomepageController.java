package photos.app;
import javafx.collections.FXCollections;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import photos.model.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 * This class is the controller for AdminHomepage.fxml
 * @author Usayed Mahmud
 *
 */
public class AdminHomepageController {
	
	@FXML ListView<String> userList;
	
	@FXML Button logout;
	
	@FXML Button create;
	@FXML Button delete;
	
	private Stage primaryStage;
	
	private ObservableList<String> obsList;
	private ArrayList<String> users;
	
	/**
	 * sets up ObservableList for displaying the list of users
	 * @param primaryStage the stage
	 * @param users the list of users
	 */
	public void start(Stage primaryStage, ArrayList<String> users) {
		// TODO Auto-generated method stub
		
		this.primaryStage = primaryStage;
		this.users = users;
		
		obsList = FXCollections.observableArrayList(users);
		userList.setItems(obsList);
		
		
		userList.getSelectionModel().select(0);
		
		
	}
	
	/**
	 * opens a window that will prompt user for information to create a new user account
	 * @throws IOException
	 */
	public void createUser() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/CreateUser.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		CreateUserController controller = loader.getController();
		controller.start(stage, users, obsList);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Create User");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		
		
	}
	
	/**
	 * deletes the user that is selected
	 * @throws IOException
	 */
	public void deleteUser() throws IOException {
		
		int index = userList.getSelectionModel().getSelectedIndex();
		String username = userList.getSelectionModel().getSelectedItem();
		
		
		if (index != -1) {
			obsList.remove(index);
			users.remove(index);
			Photos.writeUserList(users);
		}
		
	}
	
	/**
	 * calls {@link Photos#resetApplication()}
	 * @throws FileNotFoundException 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void reset() throws FileNotFoundException, IOException, ClassNotFoundException {
		Photos.resetApplication();
		
		obsList = FXCollections.observableArrayList(Photos.readUserList());
		userList.setItems(obsList);
		
		
		userList.getSelectionModel().select(0);
		
	}
	
	/**
	 * calls {@link #switchTo_Login()}, which will log out of admin account
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void logout() throws IOException, ClassNotFoundException {
		
		switchTo_Login();
		
	}
	
	/**
	 * logs out of admin account and returns to user login page
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void switchTo_Login() throws IOException, ClassNotFoundException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/LoginPage.fxml"));
		Pane root = (Pane)loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photos");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		LoginPageController controller = loader.getController();
		controller.start(primaryStage);
		
		
	}
	
	
	
}
