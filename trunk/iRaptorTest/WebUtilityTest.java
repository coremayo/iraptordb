package iRaptorTest;

import static org.junit.Assert.*;
import iRaptorPackage.WebUtility;
import java.util.Vector;
import java.net.URL;
import java.io.IOException;

import junit.framework.Assert;

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
	
	
	@Test
	public void testGetSuggestions() {
		try {
			Vector<String> v;
			v = WebUtility.getSuggestions("Finding Nemo", "dvd");
			System.out.println(v.toString());
			if( !v.get(0).startsWith("Monsters") ) //First should be Monsters, Inc.
				fail("Wrong suggestions");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			e.printStackTrace();
		 	fail("Exception thrown");
		}
	}

	
	@Test
	public void testReadPage() {
		try {
			
			String s = WebUtility.readPage(new URL("http://www.cc.gatech.edu/"));
			Assert.assertTrue(s.contains("<!-- @import url(http://www.cc." 
					+ "gatech.edu/portal_css/coc/drop_down.css); --></style>"));
			Assert.assertTrue(s.contains("&copy;&nbsp;2005-2007 The College of "
					+ "Computing at Georgia Tech :: Atlanta, Georgia 30332"));
			
		} catch (IOException e) {
			fail("Exception thrown");
		}
	
	}
}
