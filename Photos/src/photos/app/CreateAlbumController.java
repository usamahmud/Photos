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
import photos.model.Album;
import photos.model.User;

/**
 * This class is the controller for AdminHomepage.fxml and creates a new album
 * @author Usayed Mahmud
 *
 */
public class CreateAlbumController {
	
	@FXML Button createAlbumButton;
	@FXML TextField albumName;
	
	private User user;
	private Stage stage;
	
	/**
	 * 
	 * @param stage the stage currently open
	 * @param user the user logged in
	 */
	public void start(Stage stage, User user) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.user = user;
	}
	
	/**
	 * allows user to press the enter key to create album
	 * @param e the KeyEvent that has occurred
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			createAlbumButton.fire();
		}
	}
	
	/**
	 * creates a new album with desired name if it does not already exist
	 */
	public void create() {
		String name = albumName.getText();
		
		if (!user.createAlbum(name)) {
			Alert alert = new Alert(AlertType.WARNING, "");
			alert.setTitle("Invalid album name");  //warning box title
		    alert.setHeaderText("Invalid album name");// Header
		    alert.showAndWait();
		}
		
		stage.close();
		
		
	}
	
	/**
	 * closes the open window without creating the album
	 */
	public void cancel() {
		stage.close();
	}

}
