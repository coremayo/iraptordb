package iRaptorTest;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.Book;
import domain.DBUtil;
import domain.DomainUtil;
import domain.Tag;
import static org.junit.Assert.*;

public class DomainTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBUtil.openTemporaryFile();
		System.out.println(
				"Testing DBUtil using file: " + 
				DBUtil.getFile().getAbsolutePath());
	}
	
	@Test
	public void testBook() {
		String bookTitle1 = "Book1", bookTitle2 = "Book2";
		
		// Test some basic functionality of Book class
		Book b1 = DomainUtil.addBook(bookTitle1);
		assertEquals(bookTitle1, b1.getTitle());
		
		bookTitle1 = "NewBook1";
		b1.setTitle(bookTitle1);
		assertEquals(bookTitle1, b1.getTitle());
		
		Collection<Book> bookCollection = DomainUtil.getBooks();
		assertEquals(bookCollection.size(), 1);
		
		Book b2 = DomainUtil.addBook(bookTitle2);
		assertEquals(bookCollection.size(), 2);
		
		//Test tagging functionality of Book
		String tagName1 = "Tag1", tagName2 = "Tag2";
		b1.addTag(tagName1);
		b1.addTag(tagName2);
		b2.addTag(tagName1);
		
		Collection<Tag> tagCollection1 = b1.getTags();
		Collection<Tag> tagCollection2 = b2.getTags();
		
		b1.removeTag(tagName2);
		if (tagCollection1.size() == tagCollection2.size()) {
			Tag[] tagArray1 = tagCollection1.toArray(new Tag[0]);
			Tag[] tagArray2 = tagCollection2.toArray(new Tag[0]);
			int i = 0;
			for (Tag t : tagArray1) {
				assertEquals(t.getName(), tagArray2[i].getName());
			}
		}
		
		//test the author stuff
		String author1 = "Author1", author2 = "Author2";
		b1.addAuthor(author1);
		b1.addAuthor(author2);
		assertEquals(b1.getAuthors().size(), 2);
		
		b1.removeAuthor(author2);
		assertEquals(b1.getAuthors().size(), 1);
		
		//test removing
		int itemId = b1.getItemId();
		DomainUtil.removeItem(itemId);
	}
}
