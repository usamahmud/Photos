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
 * This class is the controller for RenameAlbum.fxml and prompts user for the new album name
 * @author Usayed Mahmud
 *
 */
public class RenameAlbumController {
	
	@FXML Button renameAlbumButton;
	@FXML TextField albumName;
	
	private User user;
	private Stage stage;
	
	private String oldName;
	
	/**
	 * 
	 * @param stage the stage that is open
	 * @param user the user that is logged in
	 * @param oldName the current name of the album
	 */
	public void start(Stage stage, User user, String oldName) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.user = user;
		this.oldName = oldName;
		
		albumName.setText(oldName);
	}
	
	/**
	 * allows user to press enter key instead of clicking button
	 * @param e the KeyEvent that has occurred
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			renameAlbumButton.fire();
		}
	}
	
	/**
	 * renames the album
	 * @throws IOException
	 */
	public void rename() throws IOException {
		String name = albumName.getText();
		
		
		
		if (!user.renameAlbum(oldName, name)) {
			Alert alert = new Alert(AlertType.WARNING, "");
			alert.setTitle("Invalid album name");  //warning box title
		    alert.setHeaderText("Invalid album name");// Header
		    alert.showAndWait();
		}
		
		stage.close();
		
		
	}
	
	/**
	 * closes the window without renaming album
	 */
	public void cancel() {
		stage.close();
	}

}
