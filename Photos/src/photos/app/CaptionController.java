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
 * This class is the controller for Caption.fxml and sets the caption for a photo
 * @author Usayed Mahmud
 *
 */
public class CaptionController {
	
	@FXML Button captionButton;
	@FXML TextField captionName;
	
	private Stage stage;
	private Photo photo;
	
	/**
	 * sets up caption window
	 * @param stage the stage currently open
	 * @param photo the photo 
	 */
	public void start(Stage stage, Photo photo) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.photo = photo;
		captionName.setText(photo.getCaption());
	}
	
	/**
	 * 
	 * @param e
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			captionButton.fire();
		}
	}
	
	/**
	 * changes caption of the photo
	 */
	public void rename() {
		String name = captionName.getText();
		
		photo.setCaption(name);
		stage.close();
		
		
	}
	
	/**
	 * closes window
	 */
	public void cancel() {
		stage.close();
	}

}
