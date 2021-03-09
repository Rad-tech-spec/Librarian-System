package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.DBController;
import model.ItemSearchAttribute;

class LibraryTester {
	private final DBController db = new DBController();

	@Test
	void testGetItemsByAuthor() {
		assertEquals(true, db.getItemsByAttribute(ItemSearchAttribute.AUTHOR, "Dan") != null);
	}

}
