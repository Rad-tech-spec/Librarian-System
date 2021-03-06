package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents an Item stored in the <i>Library</i>.
 */
public class Item {
	private SimpleStringProperty id;
	private SimpleStringProperty title;
	private SimpleStringProperty author;
	private SimpleStringProperty quantity;
	private SimpleStringProperty category;
	private SimpleStringProperty status;
	private SimpleStringProperty location;
	private SimpleStringProperty type;
	
	/**
	 * Creates an empty instance of Item.
	 */
	public Item() { 
		setId("");
		setTitle("");
		setAuthor("");
		setQuantity("");
		setCategory("");
		setStatus("");
		setLocation("");
		setType("");
	}
	
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
		setStatus("");
		setLocation("");
		setType(type);
	}
	
	public void setId(String id) { this.id = new SimpleStringProperty(id); }
	
	public String getId() { return id.get(); }
	
	public void setTitle(String title) { this.title = new SimpleStringProperty(title); }

	public String getTitle() { return title.get(); }

	public void setAuthor(String author) { this.author = new SimpleStringProperty(author); }

	public String getAuthor() { return author.get(); }
	
	public void setQuantity(String quantity) { this.quantity = new SimpleStringProperty(quantity); }

	public String getQuantity() { return quantity.get(); }
	
	public void setCategory(String category) { this.category = new SimpleStringProperty(category); }

	public String getCategory() { return category.get(); }
	
	public void setStatus(String status) { this.status = new SimpleStringProperty(status); }

	public String getStatus() { return status.get(); }
	
	public void setLocation(String location) { this.location = new SimpleStringProperty(location); }

	public String getLocation() { return location.get(); }
	
	public void setType(String type) { this.type = new SimpleStringProperty(type); }

	public String getType() { return type.get(); }
}
