package iRaptorTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.DBUtil;

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
		int ret = DBUtil.addBook("harry potter", "fiction", 5, 1999, "good read", 
				"random ass books", "1234");
		if (ret < 0) {
			fail("Returned negative value");
		}
	}
	
	@Test
	public void testAddDVD() {
		int ret = DBUtil.addDVD("Mission Impossible", "Action", 8, 1984, 
				"Tom Cruise is a scientologist", "not sure");
		assertFalse("Returned negative value", (ret < 0));
	}
	
	@Test
	public void testAddCD() {
		int ret = DBUtil.addCD("Inhuman Rampage", "Power Metal", 
				10, 1999, "Awesome");
		assertFalse("Returned negative value", (ret < 0));
	}
	
	@Test
	public void testAddVideoGame() {
		int ret = DBUtil.addVideoGame("Counterstrike", "First Person Shooter", 
				9, 2000, "Classic");
		assertFalse("Returned negative value", (ret < 0));
	}

	@Test
	public void testUpdateBook() {
		if ((DBUtil.updateBook(DBUtil.mostRecentItem(), "this is a new title", 
				"non-fiction", 5, 2008, "not as good", "nope", "4321")) < 0){
			fail("returned negative value");
		}
	}
	
	@Test
	public void testUpdateDVD() {
		String title = "Ocean's 11";
		String newTitle = "Ocean's 13";
		int itemId = DBUtil.addDVD(title, "Action", 9, 2011, 
				"Sweet casino heist", null);
		assertEquals("adding dvd failed", title, 
				DBUtil.getTitle(DBUtil.mostRecentItem()));
		assertFalse(DBUtil.updateDVD(itemId, newTitle, 
				null, 9, 2011, null, null) < 0);
		assertEquals("update dvd failed", newTitle, DBUtil.getTitle(itemId));
	}
	
	@Test
	public void testUpdateCD() {
		String title = "Bob Dylan";
		String newTitle = "the Beatles";
		
		int itemId = DBUtil.addCD(title, "Rock", 9, 1901, null);
		assertTrue(itemId > 0);
		assertFalse(
				DBUtil.updateCD(itemId, newTitle, null, 10, 1902, null) < 0);
		assertEquals(newTitle, DBUtil.getTitle(itemId));
	}
	
	@Test
	public void testUpdateVideoGame() {
		String title = "Half Life";
		String newTitle = "Half Life 2";
		
		int itemId = DBUtil.addVideoGame(title, null, 9, 1901, null);
		assertTrue(itemId > 0);
		assertFalse(DBUtil.updateVideoGame(itemId, newTitle, 
				null, 10, 1902, null) < 0);
		assertEquals(newTitle, DBUtil.getTitle(itemId));
	}

	@Test
	public void testRemoveBook() {
		int itemId = DBUtil.mostRecentItem();
		int ret = DBUtil.removeBook(itemId);
		if (ret < 0) {
			fail("returned negative value");
		}
	}

	@Test
	public void testRemoveDVD() {
		//TODO add test for removeDVD
	}

	@Test
	public void testRemoveCD() {
		//TODO add test for removeCD
	}

	@Test
	public void testRemoveVideoGame() {
		//TODO add test for removeVideoGame
	}

	@Test
	public void testGetTitle() {
		String title = DBUtil.getTitle(DBUtil.mostRecentItem());
		if (title == "") {
			fail("returned empty string");
		}
	}

	@Test
	public void testMostRecentItem() {
		String title = "newest book";
		int itemId = DBUtil.addBook(title, null, 5, 5, null, null, null);
		int newItemId = DBUtil.mostRecentItem();
		if (newItemId != itemId) {
			fail("most recent item was not returned");
		}
	}
	
	@Test
	//TODO implement find test methods
	public void testFindBookByTitle() {
		String title = "search for me";
		String searchString = "search for";
		int itemId = DBUtil.addBook(title, null, 5, 5, null, null, null);
		int[] searchItemId = DBUtil.findBookByTitle(searchString);
		
		assertTrue(arrayContains(searchItemId, itemId));
		
		searchString = "NOBOOKSHOULDBETITLEDANYTHINGLIKETHIS";
		searchItemId = DBUtil.findBookByTitle(searchString);
		
		assertFalse(arrayContains(searchItemId, itemId));
	}
	
	@After
	public void tearDown() throws Exception { }
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private boolean arrayContains(int[] arr, int i) {
		for (int j : arr) {
			if (i == j) {
				return true;
			}
		}
		return false;
	}

}
