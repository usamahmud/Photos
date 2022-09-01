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
import photos.model.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 * This class is the controller for UserHomepage.fxml and displays the albums for the user
 * @author Usayed Mahmud
 *
 */
public class UserHomepageController {
	
	@FXML TableView<Album> albumList;
	
	@FXML TableColumn<Album,String> albumNameColumn;
	@FXML TableColumn<Album,Integer> numPhotosColumn;
	@FXML TableColumn<Album,String> dateRangeColumn;
	
	private Stage primaryStage;
	private User user;
	
	private ObservableList<Album> obsList;
	
	/**
	 * sets up the ObservableList that will be used to display list of albums
	 * @param primaryStage the main stage
	 * @param user the user that is logged in
	 * @throws IOException
	 */
	public void start(Stage primaryStage, User user) throws IOException {
		// TODO Auto-generated method stub
		
		this.primaryStage = primaryStage;
		this.user = user;
		
		
		
		obsList = FXCollections.observableArrayList(user.getAlbums());
		
		
		albumNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		numPhotosColumn.setCellValueFactory(new PropertyValueFactory<>("numPhotos"));
		dateRangeColumn.setCellValueFactory(new PropertyValueFactory<>("dateRange"));
		
		albumList.setItems(obsList);
		albumList.getSelectionModel().select(0);
		
		
	}
	
	/**
	 * opens a window that will allow user to enter search criteria
	 * @throws IOException
	 */
	public void photoSearch() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/PhotoSearch.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		PhotoSearchController controller = loader.getController();
		controller.start(stage, user);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Photo Search");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		obsList = FXCollections.observableArrayList(user.getAlbums());
		
		albumList.setItems(obsList);
		albumList.getSelectionModel().select(0);
		
		Photos.writeUser(user);
		
		
	}
	
	/**
	 * opens the selected album
	 * @throws IOException
	 */
	public void openAlbum() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/AlbumHomepage.fxml"));
		Pane root = (Pane)loader.load();
		
		Album album = albumList.getSelectionModel().getSelectedItem();
		
		
		Stage stage = new Stage();
		
		AlbumHomepageController controller = loader.getController();
		controller.start(stage, user, album);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(album.getName());
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		obsList = FXCollections.observableArrayList(user.getAlbums());
		
		albumList.setItems(obsList);
		albumList.getSelectionModel().select(0);
		
		albumList.getColumns().get(0).setVisible(false);
		albumList.getColumns().get(0).setVisible(true);
		
		Photos.writeUser(user);
	}
	
	/**
	 * opens a window which allows user to create new album
	 * @throws IOException
	 */
	public void createAlbum() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/CreateAlbum.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		CreateAlbumController controller = loader.getController();
		controller.start(stage, user);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Create Album");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		obsList = FXCollections.observableArrayList(user.getAlbums());
		
		albumList.setItems(obsList);
		albumList.getSelectionModel().select(0);
		
		Photos.writeUser(user);
		
	}
	
	/**
	 * opens windows which will allow user to rename currenly selected album
	 * @throws IOException
	 */
	public void renameAlbum() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/RenameAlbum.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		
		
		RenameAlbumController controller = loader.getController();
		controller.start(stage, user, albumList.getSelectionModel().getSelectedItem().getName());
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Rename Album");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		obsList = FXCollections.observableArrayList(user.getAlbums());
		
		
		albumList.setItems(obsList);
		albumList.getSelectionModel().select(0);
		
		//refreshes the table
		albumList.getColumns().get(0).setVisible(false);
		albumList.getColumns().get(0).setVisible(true);
		
		//writes user to file
		Photos.writeUser(user);
		
	}
	
	/**
	 * deletes the currently selected album
	 * @throws IOException
	 */
	public void deleteAlbum() throws IOException {
		
		Album album = albumList.getSelectionModel().getSelectedItem();
		user.removeAlbum(album);
		
		obsList = FXCollections.observableArrayList(user.getAlbums());
		
		albumList.setItems(obsList);
		albumList.getSelectionModel().select(0);
		
		//writes user to file
		Photos.writeUser(user);
		
	}
	
	/**
	 * calls {@link #switchTo_Login()} which returns to login page
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void logout() throws IOException, ClassNotFoundException {
		
		switchTo_Login();
		
		
	}
	
	/**
	 * returns to login page
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
