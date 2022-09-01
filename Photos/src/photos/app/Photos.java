package photos.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import photos.model.Album;
import photos.model.Photo;
import photos.model.User;

/**
 * This class contains the main method that starts the program
 * @author Usayed Mahmud
 *
 */
public class Photos extends Application {
	
	
	/**
	 * sets up the primary stage with login page
	 */
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/photos/view/LoginPage.fxml"));
		Pane root = (Pane)loader.load();
		
		LoginPageController controller = loader.getController();
		controller.start(primaryStage);
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * resets the application to defaults, i.e. just the stock user with its stock album
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void resetApplication() throws FileNotFoundException, IOException {
		//creates stock user
		User stockUser = new User("stock", "stock");
		stockUser.createAlbum("stock");
		Album stockAlbum = stockUser.getAlbum("stock");
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("Aster");
		names.add("Golden Gate");
		names.add("Instagram Egg");
		names.add("Lau Pa Sat");
		names.add("Lion");
		
		for (String name: names) {
			String path = "/res/data/stock/" + name + ".jpg";
			File file = new File(path);
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(file.lastModified());
			stockAlbum.addPhoto(new Photo(name, path, date));
		}
		
		stockAlbum.getPhotoList().get(1).addTag("location", "San Francisco");
		stockAlbum.getPhotoList().get(3).addTag("location", "Singapore");
		
		writeUser(stockUser);
		
		
		
		//creates user list with only stock user in it
		ArrayList<String> userList = new ArrayList<String>();
		userList.add("stock");
		
		
		writeUserList(userList);
		
	}
	
	/**
	 * deserializes the user data for the user with the given username
	 * @param username the username of the user
	 * @return the {@link User} object of requested user
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static User readUser(String username) throws ClassNotFoundException, IOException {
		String storeDir = "src/res/data";
		String storeFile = username + ".dat";
		
		String path = storeDir + File.separator + storeFile;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		User user = (User)ois.readObject();
		return user;
	}
	
	/**
	 * serializes the user data
	 * @param user the {@link User} object that should be serialized
	 * @throws IOException
	 */
	public static void writeUser(User user) throws IOException {
		String storeDir = "src/res/data";
		String storeFile = user.getUsername() + ".dat";
		
		String path = storeDir + File.separator + storeFile;
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(user);
	}
	
	/**
	 * reads the list of users from file
	 * @return the list of users
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<String> readUserList() throws ClassNotFoundException, IOException {
		String storeDir = "src/res/data/users";
		String storeFile = "users.dat";
		
		String path = storeDir + File.separator + storeFile;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		ArrayList<String> userList = (ArrayList<String>)ois.readObject();
		return userList;
	}
	
	/**
	 * serializes the list of users
	 * @param userList the list of users
	 * @throws IOException
	 */
	public static void writeUserList(ArrayList<String> userList) throws IOException {
		String storeDir = "src/res/data/users";
		String storeFile = "users.dat";
		
		String path = storeDir + File.separator + storeFile;
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(userList);
	}
	
	/**
	 * main method that begins program
	 * @param args unused
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//resetApplication();
		launch(args);
	}
}