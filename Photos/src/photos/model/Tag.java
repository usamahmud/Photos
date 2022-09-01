package photos.model;

import java.io.Serializable;

/**
 * This class contains the information for a tag
 * @author Usayed Mahmud
 *
 */
public class Tag implements Serializable {
	
	static final long serialVersionUID = 1L;
	/**
	 * name/type of the tag
	 */
	private String name;
	/**
	 * value of the tag
	 */
	private String value;
	
	/**
	 * constructor for a tag
	 * @param name the name/type of the tag
	 * @param value the value of the tag
	 */
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * returns the name of the tag
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns the value of the tag
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * checks if this tag is equivalent to the given tag
	 * @param tag the tag that is checked for equality
	 * @return true if the two tags are the same
	 */
	public boolean equals(Tag tag) {
		return name.equals(tag.getName()) && value.equals(tag.getValue());
	}
	
	/**
	 * returns the String representation of this tag
	 */
	public String toString() {
		return name + ": " + value;
	}
}
