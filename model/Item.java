package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents an item stored in the <i>Library</i>.
 * 
 * @author Nikita Mezhenskyi
 * @version 1.0.0, 2021-03-07
 */
public class Item {
	private SimpleStringProperty id;
	private SimpleStringProperty title;
	private SimpleStringProperty author;
	private SimpleStringProperty category;
	private SimpleStringProperty status;
	private SimpleStringProperty type;
	
	/**
	 * Creates an empty instance of <i>Item</i>.
	 */
	public Item() { 
		setId("");
		setTitle("");
		setAuthor("");
		setCategory("");
		setStatus("");
		setType("");
	}
	
	/**
	 * Creates an instance of <i>Item</i> with provided values.
	 * @param id Item id
	 * @param title Item title
	 * @param author Item author
	 * @param quantity Item quantity
	 * @param category Item category
	 * @param type Item type
	 */
	public Item(String id, String title, String author, String category, String status, String type) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCategory(category);
		setStatus(status);
		setType(type);
	}
	
	public void setId(String id) { this.id = new SimpleStringProperty(id); }
	
	public String getId() { return id.get(); }
	
	public void setTitle(String title) { this.title = new SimpleStringProperty(title); }

	public String getTitle() { return title.get(); }

	public void setAuthor(String author) { this.author = new SimpleStringProperty(author); }

	public String getAuthor() { return author.get(); }
	
	public void setCategory(String category) { this.category = new SimpleStringProperty(category); }

	public String getCategory() { return category.get(); }
	
	public void setStatus(String status) { this.status = new SimpleStringProperty(status); }

	public String getStatus() { return status.get(); }
	
	public void setType(String type) { this.type = new SimpleStringProperty(type); }

	public String getType() { return type.get(); }
	
	@Override
	public String toString() {
		return String.format("| %-6s | %-30s | %-25s | %-25s | %-15s | %-15s |\n", getId(), getTitle(), getAuthor(), getCategory(), getStatus(), getType());
	}
}
