package model;

/**
 * Represents valid attributes for searching the <i>Item</i> in the database.
 * 
 * @author Nikita Mezhenskyi
 * @version 1.0.0, 2021-03-07
 */
public enum ItemSearchAttribute {
	// Constants that represent attribute names
	ID("id"),
	TITLE("name"),
	AUTHOR("author");
	
	/**
	 * String value of attribute name
	 */
	private String attributeName;

	/**
	 * Sets values for <i>ItemSearchAttribute</i>.
	 * @param attributeName String value of attribute name
	 */
	private ItemSearchAttribute(String attributeName) {
		this.attributeName = attributeName;
	}
	
	/**
	 * Returns a string value of attribute name to be used in searching queries.
	 * @return String value of attribute name
	 */
	public String getAttributeName() { return attributeName; }
}
