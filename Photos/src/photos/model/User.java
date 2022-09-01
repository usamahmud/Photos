package photos.model;

import java.io.*;
import java.util.*;

/**
 * This class contains the information for a user
 * @author Usayed Mahmud
 *
 */
public class User implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	/**
	 * username of the user
	 */
	private String username;
	/**
	 * password of the user
	 */
	private String password;
	/**
	 * list of albums of the user
	 */
	private ArrayList<Album> albums;
	/**
	 * list of tags that the user can tag a photo with
	 */
	private ArrayList<String> tags;
	
	/**
	 * constructor for a user
	 * @param username the username for the user
	 * @param password the password for the user
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		albums = new ArrayList<Album>();
		tags = new ArrayList<String>();
		tags.add("location");
		tags.add("person");
	}
	
	/**
	 * returns the username of the user
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * returns the password of the user
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * creates an album that if the given name is valid
	 * @param albumName the desired name for the new album
	 * @return true if the album was created
	 */
	public boolean createAlbum(String albumName) {
		if (albumName.isBlank()) {
			return false;
		}
		for(Album album: albums) {
			if(albumName.equals(album.getName()))
				return false;
		}
		Album newAlbum = new Album(this, albumName);
		albums.add(newAlbum);
		return true;
	}
	
	/**
	 * renames the album to the given new name if it is valid
	 * @param oldName the previous name of the album
	 * @param newName the new desired name for the album
	 * @return true if the name of the album was changed
	 */
	public boolean renameAlbum(String oldName, String newName) {
		Album album = null;
		if (newName.isBlank()) {
			return false;
		}
		for(Album a: albums) {
			if(newName.equals(a.getName()) && !newName.equals(oldName))
				return false;
			if (oldName.equals(a.getName()))
				album = a;
		}
		album.setName(newName);
		return true;
	}
	
	/**
	 * returns the album that has the given name
	 * @param albumName the name of the album that is desired
	 * @return the album
	 */
	public Album getAlbum(String albumName) {
		for(Album album: albums) {
			if(albumName.equals(album.getName()))
				return album;
		}
		return null;
	}
	
	/**
	 * removes the given album from the user's list of albums
	 * @param album the album that should be deleted
	 */
	public void removeAlbum(Album album) {
		albums.remove(album);
	}
	
	/**
	 * returns the list of albums for the user
	 * @return albums
	 */
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	
	/**
	 * adds a user-defined tag type to the user's list of tags
	 * @param name the name of the tag that should be added
	 */
	public void addTag(String name) {
		if (!tags.contains(name)) {
			tags.add(name);
		}
	}
	
	/**
	 * returns the list of tags types that are default or are user-defined
	 * @return tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	
	
}
