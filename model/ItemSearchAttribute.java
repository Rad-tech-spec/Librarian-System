package model;

/**
 * Represents valid attributes for searching the <i>Item</i> in the database.
 */
public enum ItemSearchAttribute {
	// Constants that represent attribute names
	ID("id"),
	TITLE("title"),
	AUTHOR("author"),
	STATUS("status");
	
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
