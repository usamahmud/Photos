package photos.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class contains the information for an album
 * @author Usayed Mahmud
 *
 */
public class Album implements Serializable {

	static final long serialVersionUID = 1L;
	
	/**
	 * name of the album
	 */
	private String name;
	/**
	 * date range of the photos in the album
	 */
	private String dateRange;
	/**
	 * number of photos in the album
	 */
	private int numPhotos;
	/**
	 * user that the album belongs to
	 */
	private User user;
	/**
	 * list of photos in the album
	 */
	private ArrayList<Photo> photos;
	
	/**
	 * constructor for Album
	 * the album initially contains no photos
	 * @param user the user associated with the album
	 * @param albumName the desired name for the album
	 */
	public Album(User user, String albumName) {
		this.user = user;
		name = albumName;
		dateRange = "";
		photos = new ArrayList<Photo>();
		numPhotos = photos.size();
	}
	
	/**
	 * sets album name
	 * @param aName the desired new name for album
	 */
	public void setName(String aName) {
		name = aName;
	}
	
	/**
	 * returns the album name
	 * @return the album name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the number of photos in the album
	 * @return numPhotos
	 */
	public int getNumPhotos() {
		numPhotos = photos.size();
		return numPhotos;
	}
	
	/**
	 * the string representation of the date range for all photos in the album
	 * @return the date range
	 */
	public String getDateRange() {
		
		Calendar oldest = null, newest = null;
		
		for (Photo photo: photos) {
			if (oldest == null) {
				oldest = photo.getDate();
				newest = photo.getDate();
			}
			if (photo.getDate().before(oldest)) {
				oldest = photo.getDate();
			}
			if (photo.getDate().after(newest)) {
				newest = photo.getDate();
			}
		}
		
		if (oldest == null) {
			dateRange = " - ";
			return dateRange;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		dateRange = sdf.format(oldest.getTime()) + " - " + sdf.format(newest.getTime());
		
		return dateRange;
	}
	
	/**
	 * returns the user that the album is associated with
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * returns the username of the user that the album is associated with
	 * @return username
	 */
	public String getUsername() {
		return user.getUsername();
	}
	
	/**
	 * adds the new photo to the albums list of photos
	 * @param newPhoto the photo to be added
	 * @return true if the photo has been added
	 */
	public boolean addPhoto(Photo newPhoto) {
		if (photos.contains(newPhoto)) {
			return false;
		}
		
		photos.add(newPhoto);
		return true;
	}
	
	/**
	 * returns the list of photos for this album
	 * @return the photo list
	 */
	public ArrayList<Photo> getPhotoList() {
		return photos;
	}
	
}
