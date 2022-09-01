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
 * This class is the controller for Tags.fxml and displays the list of tags associated with the selected photo
 * @author Usayed Mahmud
 *
 */
public class TagsController {
	
	@FXML TableView<Tag> tagList;
	
	@FXML TableColumn<Tag,String> tagNameColumn;
	@FXML TableColumn<Tag,String> tagValueColumn;
	
	private Stage stage;
	private User user;
	private Photo photo;
	
	private ObservableList<Tag> obsList;
	
	/**
	 * sets up the ObservableList that will be used to display the list of tags
	 * @param stage the stage that is open
	 * @param user the user that is logged in
	 * @param photo the photo whose tag information is being displayed
	 * @throws IOException
	 */
	public void start(Stage stage, User user, Photo photo) throws IOException {
		// TODO Auto-generated method stub
		
		this.stage = stage;
		this.user = user;
		this.photo = photo;
		
		
		obsList = FXCollections.observableArrayList(photo.getTags());
		
		
		tagNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		tagValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		tagList.setItems(obsList);
		tagList.getSelectionModel().select(0);
		
	}
	
	/**
	 * opens a new window which will prompt user to enter the desired tag information
	 * @throws IOException
	 */
	public void addTag() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/AddTag.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		AddTagController controller = loader.getController();
		controller.start(stage, user, photo);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Create User");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		obsList = FXCollections.observableArrayList(photo.getTags());
		tagList.setItems(obsList);
		tagList.getSelectionModel().select(0);
		
	}
	
	/**
	 * removes the selected tag from the photo
	 */
	public void deleteTag() {
		photo.removeTag(tagList.getSelectionModel().getSelectedItem());
		obsList = FXCollections.observableArrayList(photo.getTags());
		System.out.println(obsList.size());
		
		tagList.setItems(obsList);
		tagList.getSelectionModel().select(0);
	}

}
