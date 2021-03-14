package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import dao.DBController;
import dao.Data;
import dao.LoginAdminDBController;
import model.ItemSearchAttribute;

/**
 * This class performs unit tests for the <i>Library Application</i>.
 * 
 * @author Nikita Mezhenskyi
 * @author Alexander Samaniego
 * @author Rad Eshghi
 */
class LibraryTester {
	private final DBController db = new DBController();

	@ParameterizedTest
	@DisplayName("Get Items by Title - valid titles")
	@ValueSource(strings = {"The Irishman", "Irishman", "irishman", "irish"})
	void testGetItemsByTitle(String searchValue) {
		assertTrue(db.getItemsByAttribute(ItemSearchAttribute.TITLE, "The Irishman") != null && 
				!db.getItemsByAttribute(ItemSearchAttribute.TITLE, "The Irishman").isEmpty());
	}
	
	@ParameterizedTest
	@DisplayName("Get Items by Author - valid author names")
	@ValueSource(strings = {"Marcus Aurelius", "marcus aurelius", "marcus", "aurelius"})
	void testGetItemsByAuthor(String searchValue) {
		assertTrue(db.getItemsByAttribute(ItemSearchAttribute.AUTHOR, searchValue) != null &&
				!db.getItemsByAttribute(ItemSearchAttribute.AUTHOR, searchValue).isEmpty());
	}

	@ParameterizedTest
	@DisplayName("Get Items by ID - valid IDs")
	@ValueSource(strings = {"122", "150", "123"})
	void testGetItemsById(String searchValue) {
		assertTrue(db.getItemsByAttribute(ItemSearchAttribute.ID, searchValue) != null &&
				!db.getItemsByAttribute(ItemSearchAttribute.ID, searchValue).isEmpty());
	}
	
	@Test
	@DisplayName("Get Borrowed Items - invalid Student ID")
	void testGetBorrowedItems() {
		assertTrue(db.getBorrowedItems("100200300", true).isEmpty());
	}
	
	@Test
	@DisplayName("Login to Librarian account by ID and password - valid ID and password")
	void testValidLibrariansLogin() {
		try {
			assertTrue(LoginAdminDBController.librarianLogin(123, "abc").next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Login to Librarian account by ID and password - invalid ID and password")
	void testInvalidLibrarianLogin() {
		try {
			assertTrue(!LoginAdminDBController.librarianLogin(000, "zzz").next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Login to Admin account by ID and password - valid ID and password")
	void testValidAdminLogin() {
		try {
			assertTrue(LoginAdminDBController.adminLogin(1234, "abcd").next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Login to Admin account by ID and password - invalid ID and password")
	void testInvalidAdminLogin() {
		try {
			assertTrue(!LoginAdminDBController.adminLogin(0000, "zzzz").next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Adding An Item To Library")
	void testAddingItem()
	{
		assertTrue(Data.AddItem("NewBook", 852, "Rad", "History", "Hold", "Book"));
	}
	
	
	@Test
	@DisplayName("Testing - Generating Report")
	public void testGenerateFile()
	{
		assertTrue(Data.GenerateFile());
	}
	
	
	@Test
	@DisplayName("Testing - Returning Item method")
	public void testReturnItem()
	{
		assertTrue(Data.Return("Miracle", 456, "Rad", 123456, "999-999-9999"));
	}
	
	
	@Test
	@DisplayName("Testing - QuantityType")
	void testGetingQuantityType()
	{
		assertTrue(Data.GetQuantityType());
	}
	
	
	@Test
	@DisplayName("Testing - Issuing a Item")
	void testIssuing()
	{
		assertTrue(Data.Issue("NewBook", 189, 1, "Rad", 123, "905-999-9999"));
	}
	
	
	@Test
	@DisplayName("Testing - Searching for quantity")
	public void testSearchingQuantity()
	{
		assertEquals( true , Data.SearchQuantity("NewBook"));
	}
}
