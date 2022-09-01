package photos.app;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import photos.model.User;

/**
 * This class is the controller for CreateUser.fxml and creates a new user
 * @author Usayed Mahmud
 *
 */
public class CreateUserController {
	
	@FXML Button createUserButton;
	@FXML TextField username;
	@FXML PasswordField password1;
	@FXML PasswordField password2;
	
	private ObservableList<String> obsList;
	private ArrayList<String> users;
	
	
	private Stage stage;
	
	/**
	 * 
	 * @param stage the stage that is open
	 * @param users the list of users
	 * @param obsList ObservableList to be used to display list of users
	 */
	public void start(Stage stage, ArrayList<String> users, ObservableList<String> obsList) {
		// TODO Auto-generated method stub
		this.users = users;
		this.stage = stage;
		this.obsList = obsList;
	}
	
	/**
	 * allows user to press enter key instead of clicking button
	 * @param e the KeyEvent that has occurred
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			createUserButton.fire();
		}
	}
	
	/**
	 * creates a new user
	 * @throws IOException
	 */
	public void create() throws IOException {
		String name = username.getText();
		String pass1 = password1.getText();
		String pass2 = password2.getText();
		
		if (users.contains(name)) {
			Alert alert = new Alert(AlertType.WARNING, "");
			alert.setTitle("User already exists");  //warning box title
		    alert.setHeaderText("User already exists");// Header
		    alert.showAndWait();
		} else if (!pass1.equals(pass2)) {
			Alert alert = new Alert(AlertType.WARNING, "");
			alert.setTitle("Passwords do not match");  //warning box title
		    alert.setHeaderText("Passwords do not match");// Header
		    alert.showAndWait();
		} else {
			users.add(name);
			obsList.add(name);
			User newUser = new User(name, pass1);
			Photos.writeUser(newUser);
			Photos.writeUserList(users);
			stage.close();
		}
		
	}
	
	/**
	 * closes window without user creation
	 */
	public void cancel() {
		stage.close();
	}

}
