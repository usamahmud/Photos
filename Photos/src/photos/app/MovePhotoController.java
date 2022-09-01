package photos.app;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import photos.model.Album;
import photos.model.Photo;
import photos.model.User;

/**
 * This class is the controller for MovePhoto.fxml and copies or moves the selected photo to another album
 * @author Usayed Mahmud
 *
 */
public class MovePhotoController {
	
	@FXML ListView<String>AlbumList;
	@FXML Button Confirm;
	@FXML Button Cancel;
	@FXML RadioButton Copy;
	@FXML RadioButton Move;

	private Stage stage;
	private User user;
	private Photo p;
	
	private ObservableList<String> albums;
	private int selected;
	private ObservableList<HBox> obsList;
	private ArrayList<Photo> photos;
	
	/**
	 * sets up
	 * @param stage the stage currently open
	 * @param user the user that is logged in
	 * @param p the photo that is selected
	 * @param selected index of selected photo
	 * @param obsList ObservableList of HBoxes
	 * @param photos the list of photos
	 */
	public void start(Stage stage, User user, Photo p, int selected, ObservableList<HBox> obsList, ArrayList<Photo> photos) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.obsList = obsList;
		this.user = user;
		this.photos = photos;
		this.selected = selected;
		this.p = p;
		
		
		albums = FXCollections.observableArrayList();
		for(int i = 0;i<user.getAlbums().size();i++) {
			albums.add(user.getAlbums().get(i).getName());
			
		}
		
		
		AlbumList.setItems(albums);
		
		AlbumList.getSelectionModel().select(0);
		
		
		
//		this.oldName = oldName;
//		
//		albumName.setText(oldName);
	}
	
	/**
	 * calls {@link #copy_move()}
	 * @throws IOException
	 */
	public void Confirm() throws IOException {
		
	
		//we want to copy the photo
		
		copy_move();
		
		stage.close();
		
	}
	
	/**
	 * determines whether the user opted to copy or move the photo and performs the transfer
	 */
	public void copy_move() {
		//we want to copy the photo
		
				if(Copy.isSelected()) {
				
				Photo newPhoto = new Photo(p.getCaption(),p.getPath(),p.getDate());
				user.getAlbums().get(AlbumList.getSelectionModel().getSelectedIndex()).addPhoto(newPhoto);
				
				}
				else if(Move.isSelected()) {
					Photo newPhoto = new Photo(p.getCaption(),p.getPath(),p.getDate());
					
					user.getAlbums().get(AlbumList.getSelectionModel().getSelectedIndex()).addPhoto(newPhoto);
					photos.remove(selected);
					obsList.remove(selected);
				}
		
	}
	
	/**
	 * closes the window without transferring the photo
	 * @throws IOException
	 */
	public void Cancel() throws IOException{
		
		stage.close();
	}
}

	
	
	
	
	
	
	
	
	