package model;

/**
 * This class represents an Item stored in the <i>Library</i>.
 */
public class Item {
	private String id = "";
	private String title = "";
	private String author = "";
	private String quantity = "";
	private String category = "";
	private String type = "";
	
	/**
	 * Creates an empty instance of Item.
	 */
	public Item() { }
	
	/**
	 * Creates an instance of Item with given properties.
	 * @param id Item id
	 * @param title Item title
	 * @param author Item author
	 * @param quantity Item quantity
	 * @param category Item category
	 * @param type Item type
	 */
	public Item(String id, String title, String author, String quantity, String category, String type) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setQuantity(quantity);
		setCategory(category);
		setType(type);
	}
	
	public void setId(String id) { this.id = id; }
	
	public String getId() { return id; }
	
	public void setTitle(String title) { this.title = title; }

	public String getTitle() { return title; }

	public void setAuthor(String author) { this.author = author; }

	public String getAuthor() { return author; }
	
	public void setQuantity(String quantity) { this.quantity = quantity; }

	public String getQuantity() { return quantity; }
	
	public void setCategory(String category) { this.category = category; }

	public String getCategory() { return category; }
	
	public void setType(String type) { this.type = type; }

	public String getType() { return type; }
}
