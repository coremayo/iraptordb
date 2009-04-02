package iRaptorTest;

import static org.junit.Assert.*;
import iRaptorPackage.WebUtility;

import org.junit.Test;

public class WebUtilityTest {

	@Test
	public void testAmazonSearch() {
		try {
			WebUtility.amazonSearch("Finding Nemo", "DVD");
			
			WebUtility.amazonSearch("Finding Nemo", "book");
			
			WebUtility.amazonSearch("Finding Nemo", "game");
			
			WebUtility.amazonSearch("Finding Nemo", "CD");
			
		} catch (Exception e) {
			fail("Exception thrown");
		}
			
	}

	@Test
	public void testGamesFAQsearch() {
		try {
			WebUtility.gamesFAQsearch("Halo 3");
		} catch (Exception e) {
		 	fail("Exception thrown");
		}
	}

}
