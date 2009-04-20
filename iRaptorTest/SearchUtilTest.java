package iRaptorTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.DBUtil;
import domain.DomainUtil;
import domain.Item;
import domain.SearchUtil;

public class SearchUtilTest {
		static String tag1 = "tag1", tag2 = "tag2", tag3 = "tag3";
		static String genre1 = "g1", genre2 = "g2", genre3 = "g3";
		static String title1 = "t1", title2 = "t2", title3 = "t3";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBUtil.fileName = File.createTempFile("RaptorUnitTests", ".db");
		System.out.println("Testing DBUtil using file: " + 
				DBUtil.fileName.getAbsolutePath());
		DBUtil.setUpDatabase();
		
		Item i = DomainUtil.addBook(title1);
		i.addTag(tag1);
		i.addTag(tag2);
		i.setGenre(genre1);
		
		i = DomainUtil.addDVD(title2);
		i.addTag(tag2);
		i.setGenre(genre1);
		
		i = DomainUtil.addVideoGame(title3);
		i.setGenre(genre2);
	}

	@Test
	public void testSearchTag() {
		Collection<Item> c = SearchUtil.searchTag(tag1);
		assertEquals(1, c.size());
		
		c = SearchUtil.searchTag(tag2);
		assertEquals(2, c.size());
		
		c = SearchUtil.searchTag(tag3);
		assertEquals(0, c.size());
	}

	@Test
	public void testSearchGenre() {
		Collection<Item> c = SearchUtil.searchGenre(genre1);
		assertEquals(2, c.size());
		
		c = SearchUtil.searchGenre(genre2);
		assertEquals(1, c.size());
		
		c = SearchUtil.searchGenre(genre3);
		assertEquals(0, c.size());
	}

	@Test
	public void testSearchTitle() {
		Collection<Item> c = SearchUtil.searchTitle(title1);
		assertEquals(1, c.size());
		
		c = SearchUtil.searchTitle(title2);
		assertEquals(1, c.size());
		
		c = SearchUtil.searchTitle(title3);
		assertEquals(1, c.size());
	}
}
