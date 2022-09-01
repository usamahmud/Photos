package photos.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;import java.io.InputStream;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import photos.model.Photo;

/**
 * This class is the controller for AdminHomepage.fxml
 * @author Usayed Mahmud
 *
 */
public class DisplayController {
	@FXML ImageView imageView;
	
	@FXML Button lButton;
	@FXML Button rButton;
	
	@FXML Text caption;
	@FXML Text date;
	@FXML Text tags;
	
	private Stage stage;
	private ArrayList<Photo> photos;
	private int currentIndex;
	
	/**
	 * sets up manual slideshow
	 * @param stage the stage that is open
	 * @param photos the list of photos in the album
	 * @param index the index of the photo that should be displayed
	 * @throws FileNotFoundException
	 */
	public void start(Stage stage, ArrayList<Photo> photos, int index) throws FileNotFoundException {
		
		
		this.stage = stage;
		this.photos = photos;
		currentIndex = index;
		
		displayPhoto();
		
	}
	
	/**
	 * displays the photo in the center of the window
	 * @throws FileNotFoundException
	 */
	public void displayPhoto() throws FileNotFoundException {
		
		
		Image image = null;
		InputStream is = getClass().getResourceAsStream(photos.get(currentIndex).getPath());
		if (is == null) {
			image = new Image(new FileInputStream(photos.get(currentIndex).getPath()), 1200, 600, true, true);
		} else {
			image = new Image(getClass().getResourceAsStream(photos.get(currentIndex).getPath()), 1200, 600, true, true);
		}
		
		double w = image.getWidth();
		double h = image.getHeight();
		
		imageView.setImage(image);
		
		imageView.setX((1200-w)/2);
		imageView.setY((600-h)/2);
		
		caption.setText(photos.get(currentIndex).getCaption());
		date.setText(photos.get(currentIndex).getFormattedDate());
		tags.setText(photos.get(currentIndex).printTags());
		
	}
	
	/**
	 * displays the previous photo if it exists
	 * @throws FileNotFoundException
	 */
	public void left() throws FileNotFoundException {
		if (currentIndex == 0)
			return;
		currentIndex--;
		displayPhoto();
		System.out.println("LEFT");
	}
	
	/**
	 * displays the next photo if it exists
	 * @throws FileNotFoundException
	 */
	public void right() throws FileNotFoundException {
		if (currentIndex == photos.size()-1)
			return;
		currentIndex++;
		displayPhoto();
		System.out.println("RIGHT");
	}
	
	/**
	 * an attempt to allow user to use arrow keys to cycle through photos
	 * @param e the KeyEvent that has occurred
	 */
	public void arrowKey(KeyEvent e) {
		if (e.getCode().equals(KeyCode.LEFT)) {
			lButton.fire();
		} else if (e.getCode().equals(KeyCode.RIGHT)) {
			rButton.fire();
		}
	}
}
