package iRaptorTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import iRaptorPackage.DBUtil;

public class DBUtilTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBUtil.fileName = File.createTempFile("RaptorUnitTests", ".db");
		System.out.println("Testing DBUtil using file: " + 
				DBUtil.fileName.getAbsolutePath());
		DBUtil.setUpDatabase();
		
		//add a few books to test some stuff
		DBUtil.addBook("book1", "genre1", 5, 5, "none", "none", null);
		DBUtil.addBook("Book2", "genre2", 6, 6, null, "blah", null);
	}
	
	@Before
	public void setUp() throws Exception { }

	@Test
	public void testAddBook() {
		int ret = DBUtil.addBook("a new book", "fiction", 5, 1999, "good read", 
				"random ass books", "1234");
		if (ret < 0) {
			fail("Returned negative value");
		}
		
		System.out.println("--addBook test finished");
	}

	@Test
	public void testUpdateBook() {
		if ((DBUtil.updateBook(DBUtil.mostRecentItem(), "this is a new title", 
				"non-fiction", 5, 2008, "not as good", "nope", "4321")) < 0){
			fail("returned negative value");
		}
		
		System.out.println("--updateBook test finished");
	}

	@Test
	public void testRemoveBook() {
		int itemId = DBUtil.mostRecentItem();
		int ret = DBUtil.removeBook(itemId);
		if (ret < 0) {
			fail("returned negative value");
		}
		
		System.out.println("--removeBook test finished");
	}

	@Test
	public void testGetTitle() {
		String title = DBUtil.getTitle(DBUtil.mostRecentItem());
		if (title == "") {
			fail("returned empty string");
		}

		System.out.println("--getTitle test finished");
	}

	@Test
	public void testMostRecentItem() {
		String title = "newest book";
		int itemId = DBUtil.addBook(title, null, 5, 5, null, null, null);
		int newItemId = DBUtil.mostRecentItem();
		if (newItemId != itemId) {
			fail("most recent item was not returned");
		}
		
		System.out.println("--mostRecentItem test finished");
	}
	
	@Test
	public void testFindBookByTitle() {
		String title = "search for me";
		String searchString = "search for";
		int itemId = DBUtil.addBook(title, null, 5, 5, null, null, null);
		int searchItemId = DBUtil.findBookByTitle(searchString);
		
		if (itemId != searchItemId) {
			System.out.println("returned item: " + searchItemId + " when " +
					"should have returned: " + itemId);
			fail("did not find correct item");
		}
		
		System.out.println("--findBookByTitle test finished");
	}
	
	@After
	public void tearDown() throws Exception { }
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Done with testing DBUtil");
	}

}
