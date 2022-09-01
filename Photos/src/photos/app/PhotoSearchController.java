package photos.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
 * This class is the controller for PhotoSearch.fxml and gathers the users search criteria
 * @author Usayed Mahmud
 *
 */
public class PhotoSearchController {
	
	@FXML RadioButton dateRange;
	@FXML RadioButton tags;
	
	@FXML RadioButton na;
	@FXML RadioButton and;
	@FXML RadioButton or;
	
	@FXML Button searchButton;
	@FXML DatePicker startDate;
	@FXML DatePicker endDate;
	
	@FXML ChoiceBox<String> type1;
	@FXML ChoiceBox<String> type2;
	
	@FXML TextField value1;
	@FXML TextField value2;
	
	
	
	private Stage stage;
	private User user;
	
	private ArrayList<String> tagList;
	private ObservableList<String> obsList;

	/**
	 * sets up the elements required for photo search
	 * @param stage the stage that is currently open
	 * @param user the user that is logged in
	 */
	public void start(Stage stage, User user) {
		
		this.stage = stage;
		this.user = user;
		
		ToggleGroup tg = new ToggleGroup();
		dateRange.setToggleGroup(tg);
		tags.setToggleGroup(tg);
		dateRange.setSelected(true);
		
		ToggleGroup tg2 = new ToggleGroup();
		na.setToggleGroup(tg2);
		and.setToggleGroup(tg2);
		or.setToggleGroup(tg2);
		na.setSelected(true);
		
		tagList = user.getTags();
		
		obsList = FXCollections.observableArrayList(tagList);
		
		type1.setItems(obsList);
		type2.setItems(obsList);
		
		
	}
	
	/**
	 * allows user to press enter key instead of clicking button
	 * @param e the KeyEvent that has occurred
	 */
	public void enterKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			searchButton.fire();
		}
	}
	
	/**
	 * performs the search and opens window that will display the search results
	 * @throws IOException
	 */
	public void search() throws IOException {
		
		ArrayList<Photo> searchResults = new ArrayList<Photo>();
		
		//date range search
		if (dateRange.isSelected()) {
			
			if (startDate.getValue() == null || endDate.getValue() == null || startDate.getValue().isAfter(endDate.getValue())) {
				Alert alert = new Alert(AlertType.WARNING, "");
				alert.setTitle("Invalid Dates");  //warning box title
			    alert.setHeaderText("Invalid Dates");// Header
			    alert.showAndWait();
				return;
			}
			
			
			for (Album album: user.getAlbums()) {
				for (Photo photo: album.getPhotoList()) {
					Calendar date = photo.getDate();
					LocalDate photoDate = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
					if (photoDate.isBefore(startDate.getValue()) || photoDate.isAfter(endDate.getValue())) {
						continue;
					}
					searchResults.add(photo);
				}
			}
			
			
		}
		// tag search
		else {
			
			
			
			
			String name1 = (String) type1.getValue();
			if(name1 == null) {
				return;
			}
			String val1 = value1.getText();
			
			Tag t1 = new Tag(name1, val1);
			
			
			int a = na.isSelected() ? 0 : (and.isSelected() ? 1 : 2);
			
			
			String name2 = (String) type2.getValue();
			if(!na.isSelected() && name2 == null) {
				return;
			}
			String val2 = value2.getText();
			
			Tag t2 = new Tag(name2, val2);
			
			
			
			
			
			
			
			for (Album album: user.getAlbums()) {
				for (Photo photo: album.getPhotoList()) {
					boolean one = false;
					boolean two = false;
					for (Tag tag: photo.getTags()) {
						
						if (a == 0) { //N/A
							if (tag.equals(t1)) {
								searchResults.add(photo);
								break;
							}
						} else if (a == 1) { // AND
							if (tag.equals(t1)) {
								one = true;
							}
							if (tag.equals(t2)) {
								two = true;
							}
							if (one && two) {
								searchResults.add(photo);
								break;
							}
						} else { // OR
							if (tag.equals(t1) || tag.equals(t2)) {
								searchResults.add(photo);
								break;
							}
						}
					}
				}
			}
			
			
		}
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/SearchResults.fxml"));
		Pane root = (Pane)loader.load();
		
		
		Stage stage = new Stage();
		
		SearchResultsController controller = loader.getController();
		controller.start(stage, user, searchResults);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Search Results");
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		
		this.stage.close();
	}


	
	

}
