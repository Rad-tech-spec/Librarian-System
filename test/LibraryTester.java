package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.DBController;
import model.ItemSearchAttribute;

class LibraryTester {
	private final DBController db = new DBController();

	@ParameterizedTest
	@ValueSource(strings = {"The Irishman", "Irishman", "irishman", "irish"})
	void testGetItemsByTitle(String searchValue) {
		assertTrue(db.getItemsByAttribute(ItemSearchAttribute.TITLE, "The Irishman") != null && 
				!db.getItemsByAttribute(ItemSearchAttribute.TITLE, "The Irishman").isEmpty());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Marcus Aurelius", "marcus aurelius", "marcus", "aurelius"})
	void testGetItemsByAuthor(String searchValue) {
		assertTrue(db.getItemsByAttribute(ItemSearchAttribute.AUTHOR, searchValue) != null &&
				!db.getItemsByAttribute(ItemSearchAttribute.AUTHOR, searchValue).isEmpty());
	}

	@ParameterizedTest
	@ValueSource(strings = {"122", "150", "123"})
	void testGetItemsById(String searchValue) {
		assertTrue(db.getItemsByAttribute(ItemSearchAttribute.ID, searchValue) != null &&
				!db.getItemsByAttribute(ItemSearchAttribute.ID, searchValue).isEmpty());
	}
}
