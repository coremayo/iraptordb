package iRaptorTest;

import static org.junit.Assert.*;
import org.junit.Test;
import iRaptorPackage.DBUtil;

public class DBUtilTest {

	@Test
	public void testAddBook() {
		if((DBUtil.addBook("a new book", "fiction", 5, 1999, "good read", 
				"random ass books", "1234")) < 0) { 
			fail("Returned negative value");
		}
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostRecentItem() {
		fail("Not yet implemented");
	}

}
