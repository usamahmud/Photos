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
 * This class is the controller for SearchResults.fxml and displays the results of the search
 * @author Usayed Mahmud
 *
 */
public class SearchResultsController {
	
	User user;
	
	@FXML Stage stage;
	
	@FXML ListView<HBox> photoList;
	
	private ArrayList<Photo> photos;
	
	private ObservableList<HBox> obsList = FXCollections.observableArrayList();
	private ObservableList<Photo> path;
	
	/**
	 * sets up the ObservableList which will be used to display search results
	 * @param stage the stage that is open
	 * @param user the user that is logged in
	 * @param photos the list of photos that match the search criteria
	 * @throws FileNotFoundException 
	 */
	public void start(Stage stage, User user, ArrayList<Photo> photos) throws FileNotFoundException {                
		this.user = user;
		this.stage = stage;
		this.photos = photos;
		
		loadList();
	}
	
	/**
	 * populates the list with hboxes
	 * @throws FileNotFoundException
	 */
	public void loadList() throws FileNotFoundException {
		
		final int MAX_W = 25;
		
		obsList = FXCollections.observableArrayList();
		
		//this makes the imageview objects and cause the thumbnails to show up
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
	 * will add results to a new album
	 * @throws IOException
	 */
	public void addToAlbum() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/CreateSearchAlbum.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		CreateSearchAlbumController controller = loader.getController();
		controller.start(stage, user, photos);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Create Album");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		this.stage.close();
	}
	
	/**
	 * closes window without creating new album
	 */
	public void cancel() {
		stage.close();
	}
	
}
