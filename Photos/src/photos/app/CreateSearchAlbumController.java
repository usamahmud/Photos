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
import photos.model.Photo;
import photos.model.User;

/**
 * This class is the controller for CreateSearchAlbum.fxml, which handles the naming of the album for search results
 * @author Usayed Mahmud
 *
 */
public class CreateSearchAlbumController {
	
	@FXML Button createAlbumButton;
	@FXML TextField albumName;
	
	private User user;
	private Stage stage;
	
	private ArrayList<Photo> photoList;

	/**
	 * 
	 * @param stage the stage that is currently open
	 * @param user the user that is logged in
	 * @param photoList the results from the search
	 */
	public void start(Stage stage, User user, ArrayList<Photo> photoList) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.user = user;
		this.photoList = photoList;
	}
	
	/**
	 * allows user to press enter key instead of pressing button
	 * @param e the KeyEvent that has occurred
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			createAlbumButton.fire();
		}
	}
	
	/**
	 * creates a new album containing the search results
	 */
	public void create() {
		String name = albumName.getText();
		
		
		
		if (!user.createAlbum(name)) {
			Alert alert = new Alert(AlertType.WARNING, "");
			alert.setTitle("Invalid album name");  //warning box title
		    alert.setHeaderText("Invalid album name");// Header
		    alert.showAndWait();
		    
		} else {
			
			Album album = user.getAlbum(name);
			for (Photo photo: photoList) {
				Photo newPhoto = new Photo(photo.getCaption(), photo.getPath(), photo.getDate());
				album.addPhoto(newPhoto);
			}
			stage.close();
		}
		
		
		
		
	}
	
	/**
	 * closes window without creation of album
	 */
	public void cancel() {
		stage.close();
	}

}
