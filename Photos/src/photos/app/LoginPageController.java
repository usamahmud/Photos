package photos.app;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photos.model.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 * This is the controller for the login page
 * @author Usayed Mahmud
 *
 */
public class LoginPageController {
	
	@FXML Button loginButton;
	@FXML TextField username;
	@FXML PasswordField password;
	
	private Stage primaryStage;	
	private ArrayList<String> users;
	
	/**
	 * 
	 * @param primaryStage
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void start(Stage primaryStage) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
		
		users = Photos.readUserList();
		
	}
	
	/**
	 * allows the user to press enter to login instead of clicking login button
	 * @param e the event that has occurred
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			loginButton.fire();
		}
	}
	
	/**
	 * sets up user login
	 * checks if user has entered admin's credentials
	 * then checks if user exists in user list and then checks the credentials entered
	 * then calls either {@link #switchTo_AdminHome()} or {@link #switchTo_UserHome(User)}
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void login() throws IOException, ClassNotFoundException {
		
		String name = username.getText();
		String pass = password.getText();
		
		if(name.equalsIgnoreCase("admin") && pass.equals("admin")) {
			//Username is there so we change scenes
			switchTo_AdminHome();
			
		} else if (users.contains(name)) {
			
			User user = Photos.readUser(name);
			
			if (pass.equals(user.getPassword())) {
				switchTo_UserHome(user);
			} else {
				Alert alert = new Alert(AlertType.WARNING, "");
				alert.setTitle("Incorrect Credentials");  //warning box title
			    alert.setHeaderText("Incorrect Credentials");// Header
			    alert.showAndWait();
			}
			
		} else {
			Alert alert = new Alert(AlertType.WARNING, "");
			alert.setTitle("User not found");  //warning box title
		    alert.setHeaderText("User not found");// Header
		    alert.showAndWait();
		}
		
		
		
	}


	/**
	 * opens user homepage
	 * @param user the user that will be logged in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void switchTo_UserHome(User user) throws IOException, ClassNotFoundException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/UserHomepage.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("User: " + user.getUsername());
		primaryStage.setResizable(false);
		primaryStage.show();
		
		UserHomepageController controller = loader.getController();
		controller.start(primaryStage, user);
		
		
	}
	
	/**
	 * opens admin homepage
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void switchTo_AdminHome() throws IOException, ClassNotFoundException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/AdminHomepage.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("admin");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		AdminHomepageController controller = loader.getController();
		controller.start(primaryStage, users);
	}
	
	
	
	
	

}
