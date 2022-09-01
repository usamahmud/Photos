package photos.app;

import java.io.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import photos.model.Album;
import photos.model.Photo;
import photos.model.User;

/**
 * This class is the controller for AlbumHomepage.fxml
 * @author Usayed Mahmud
 *
 */
public class AlbumHomepageController {
	
	private User user;
	private Album album;
	
	@FXML Stage stage;
	
	//@FXML ListView<Photo> photoList;
	
	@FXML ListView<HBox> photoList;
	//@FXML ListView<ImageView> photoList;
	private ObservableList<HBox> obsList = FXCollections.observableArrayList();
	//private ObservableList<ImageView> obsList = FXCollections.observableArrayList();
	private ObservableList<Photo> path;
	
	private ArrayList<Photo> photos;
	
	/**
	 * sets up the ObservableList to display photos in the album
	 * @param stage the stage currently open
	 * @param user the user that is logged in
	 * @param album the album that is open
	 * @throws FileNotFoundException
	 */
	public void start(Stage stage, User user, Album album) throws FileNotFoundException {   
		
		
		
		this.user = user;
		this.album = album;
		this.stage = stage;
		photos = album.getPhotoList();
		
		loadList();
		
		
	}
	
	/**
	 * populates the list with hboxes
	 * @throws FileNotFoundException
	 */
	public void loadList() throws FileNotFoundException {
		
		final int MAX_W = 25;
		
		obsList = FXCollections.observableArrayList();
		
		path = FXCollections.observableArrayList(photos);
		
		for(int i = 0;i<path.size();i++) {
			
			ImageView imageView = null;
			InputStream is = getClass().getResourceAsStream(path.get(i).getPath());
			if (is == null) {
				imageView = new ImageView(new Image(new FileInputStream(path.get(i).getPath()), MAX_W, MAX_W, true, true));
			} else {
				imageView = new ImageView(new Image(getClass().getResourceAsStream(path.get(i).getPath()), MAX_W, MAX_W, true, true));
			}
			
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER_LEFT);
			hbox.setSpacing(20);
			
			HBox hb = new HBox();
			hb.setAlignment(Pos.CENTER);
			hb.setMinWidth(MAX_W);
			hb.setMaxWidth(MAX_W);
			hb.setMinHeight(MAX_W);
			hb.setMaxHeight(MAX_W);
			
			hb.getChildren().add(imageView);
			hbox.getChildren().add(hb);
			
			Text textView = new Text(path.get(i).getCaption());
			hbox.getChildren().add(textView);
			
			obsList.add(hbox);
			
		}
	
		
		photoList.setItems(obsList);
		
		photoList.getSelectionModel().select(0);
	}
	
	/**
	 * opens a new window which will prompt the user for destination album
	 * @throws IOException
	 */
	public void copy() throws IOException {
		
		album.getUser().getAlbums();
		

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/MovePhoto.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		
		
		MovePhotoController controller = loader.getController();
		controller.start(stage, album.getUser(), path.get(photoList.getSelectionModel().getSelectedIndex()), photoList.getSelectionModel().getSelectedIndex(), obsList, photos);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Move Album");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		photoList.setItems(obsList);
		photoList.getSelectionModel().select(0);
		
	
		Photos.writeUser(user);
		
		loadList();
		
		
	}
	
	/**
	 * removes selected photo from album
	 * @throws IOException
	 */
	public void remove() throws IOException {
		
		photos.remove(photoList.getSelectionModel().getSelectedIndex());
		obsList.remove(photoList.getSelectionModel().getSelectedIndex());
		
		photoList.setItems(obsList);
		
		photoList.getSelectionModel().select(0);
		
		//writes user to file
		Photos.writeUser(user);
		
	
	
	
		
	}
	
	/**
	 * opens window that prompts user for caption name
	 * @throws IOException
	 */
	public void caption() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/Caption.fxml"));
		Pane root = (Pane)loader.load();
		
		Photo photo = photos.get(photoList.getSelectionModel().getSelectedIndex());
		//photo = photoList.getSelectionModel().getSelectedItem();
		
		Stage stage = new Stage();
		
		CaptionController controller = loader.getController();
		controller.start(stage, photo);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Photo Caption");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		Photos.writeUser(user);
		
	}
	
	/**
	 * opens window that displays photo tags and allows user add or remove those tags
	 * @throws IOException
	 */
	public void tags() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/Tags.fxml"));
		Pane root = (Pane)loader.load();
		
		Photo photo = null;
		photo = photos.get(photoList.getSelectionModel().getSelectedIndex());
		//photo = photoList.getSelectionModel().getSelectedItem();
		
		Stage stage = new Stage();
		
		TagsController controller = loader.getController();
		controller.start(stage, user, photo);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Photo Tags");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		Photos.writeUser(user);
		
	}
	
	/**
	 * opens a new window that displays the photo and allows user to cycle throught the photos of the open album
	 * @throws IOException
	 */
	public void display() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/Display.fxml"));
		Pane root = (Pane)loader.load();
		
		int index = photoList.getSelectionModel().getSelectedIndex();
		if (index == -1)
			return;
		
		Stage stage = new Stage();
		
		DisplayController controller = loader.getController();
		controller.start(stage, photos, index);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Slideshow");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
	}
	
	/**
	 * allows user to choose file from their computer
	 * @throws FileNotFoundException
	 */
	public void uploadFile() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add Photo");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Picture Files", "*.jpg", "*.png", "*.gif", "*.bmp"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
            );
		
		
		File file = fileChooser.showOpenDialog(stage);
		
		if(file == null)
			return;
		
		
		String name = file.getName();
		String filePath = file.getAbsolutePath();
		Calendar date = Calendar.getInstance();
		
		
		date.setTimeInMillis(file.lastModified());
		
		
		Photo newPhoto = new Photo(name, filePath, date);
		
		//added photo
		if (album.addPhoto(newPhoto)) {
		} else {
			return;
		}
		
		loadList();
		
		
		
	}
	
	/**
	 * closes the current window
	 */
	public void close() {
		stage.close();
	}
	
}
