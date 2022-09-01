package photos.app;
import javafx.collections.FXCollections;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import photos.model.Album;
import photos.model.Photo;
import photos.model.Tag;
import photos.model.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 * This class is the controller for AddTag.fxml
 * @author Usayed Mahmud
 *
 */
public class AddTagController {
	
	@FXML ChoiceBox tagType;
	@FXML TextField otherType;
	
	@FXML TextField tagValue;
	
	private Stage stage;
	private User user;
	private Photo photo;
	
	private ArrayList<String> tags;
	
	private ObservableList<String> obsList;
	
	/**
	 * sets up the observableArrayList to be used to display tags for the photo
	 * @param stage the stage that is being displayed
	 * @param user the user that is logged in
	 * @param photo the photo whose tags will be displayed
	 */
	public void start(Stage stage, User user, Photo photo) {
		// TODO Auto-generated method stub
		
		this.stage = stage;
		this.user = user;
		this.photo = photo;
		
		tags = user.getTags();
		
		obsList = FXCollections.observableArrayList(tags);
		obsList.add("Other");
		
		tagType.setItems(obsList);
		
		
	}
	
	/**
	 * will call {@link User#addTag(String)} and add tag to the photo's tag list
	 */
	public void addTag() {
		
		
		String type = (String) tagType.getValue();
		System.out.println(type);
		if(type == null || type.equals("Other")) {
			type = otherType.getText();
		}
		
		String value = tagValue.getText();
		
		photo.addTag(type, value);
		
		user.addTag(type);
		
		stage.close();
		
	}
	
	/**
	 * closes the open stage
	 */
	public void cancel() {
		stage.close();
	}

}
