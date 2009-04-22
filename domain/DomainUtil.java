package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Hashtable;

/**
 * Contains utilities that are to be used to interact with 
 * the domain classes
 * @author Corey
 *
 */
public class DomainUtil {
	
	private static Hashtable<Integer,Book> BOOKS;
	private static Hashtable<Integer,DVD> DVDS;
	private static Hashtable<Integer,VideoGame> VIDEOGAMES;
	private static Hashtable<Integer,CD> CDS;
	
	/**
	 * Parses the database to create objects for all items.
	 */
	protected static void populateItems() {
		try {
			BOOKS = new Hashtable<Integer,Book>();
			populateBooks();
			DVDS = new Hashtable<Integer,DVD>();
			populateDVDs();
			CDS = new Hashtable<Integer,CD>();
			populateCDs();
			VIDEOGAMES = new Hashtable<Integer,VideoGame>();
			populateVideoGames();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * populates all DVDs from the database
	 * @throws SQLException
	 */
	private static void populateDVDs() throws SQLException {
		String sqltxt = 
			   "SELECT * " +
			     "FROM Item " +
			"LEFT JOIN DVD " +
			       "ON DVD.itemId = Item.itemId;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ResultSet rs = ps.executeQuery();
		DVD d;
		while (rs.next()) {
			d = new DVD(rs);
			DVDS.put(d.getItemId(), d);
		}
		rs.close();
		ps.close();
		conn.close();
	}
	
	/**
	 * populates all VideoGames from the database
	 * @throws SQLException
	 */
	private static void populateVideoGames() throws SQLException {
		String sqltxt = 
			   "SELECT * " +
			     "FROM Item " +
			"LEFT JOIN VideoGame " +
			       "ON VideoGame.itemId = Item.itemId;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ResultSet rs = ps.executeQuery();
		VideoGame v;
		while (rs.next()) {
			v = new VideoGame(rs);
			VIDEOGAMES.put(v.getItemId(), v);
		}
		rs.close();
		ps.close();
		conn.close();
	}
	
	/**
	 * populates all CDs from the database
	 * @throws SQLException
	 */
	private static void populateCDs() throws SQLException {
		String sqltxt = 
			   "SELECT * " +
			     "FROM Item " +
			"LEFT JOIN CD " +
			       "ON CD.itemId = Item.itemId;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ResultSet rs = ps.executeQuery();
		CD c;
		while (rs.next()) {
			c = new CD(rs);
			CDS.put(c.getItemId(), c);
		}
		rs.close();
		ps.close();
		conn.close();
	}
	
	/**
	 * populates all Books from the database
	 * @throws SQLException
	 */
	private static void populateBooks() throws SQLException {
		String sqltxt = 
			   "SELECT * " +
			     "FROM Item " +
			"LEFT JOIN Book " +
			       "ON Book.itemId = Item.itemId;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ResultSet rs = ps.executeQuery();
		Book b;
		while (rs.next()) {
			b = new Book(rs);
			BOOKS.put(b.getItemId(), b);
		}
		rs.close();
		ps.close();
		conn.close();
	}

	/**
	 * Gets a collection of all the books that are in the database 
	 * @return
	 */
	public static Collection<Book> getBooks() {
		return BOOKS.values();
	}
	
	/**
	 * Creates a new instance of a Book item and add it to the database.
	 * @param title The title of the new book.
	 * @return the newly created Book item.
	 */
	public static Book addBook(String title) {
		Book b = new Book(title);
		BOOKS.put(b.getItemId(), b);
		return b;
	}

	/**
	 * Gets a collection of all the DVDs from the database.
	 * @return
	 */
	public static Collection<DVD> getDVDs() {
		return DVDS.values();
	}
	
	/**
	 * Creates a new instance of a DVD item and adds it to the database.
	 * @param title The title of the new DVD.
	 * @return The newly created DVD item.
	 */
	public static DVD addDVD(String title) {
		DVD d = new DVD(title);
		DVDS.put(d.getItemId(), d);
		return d;
	}

	/**
	 * Gets a collection of all the VideoGame items in the database.
	 * @return
	 */
	public static Collection<VideoGame> getVideoGames() {
		return VIDEOGAMES.values();
	}
	
	/**
	 * Creates a new instance of a VideoGame item and adds it to the database.
	 * @param title The title of the newly created DVD.
	 * @return The newly created DVD item.
	 */
	public static VideoGame addVideoGame(String title) {
		VideoGame v = new VideoGame(title);
		VIDEOGAMES.put(v.getItemId(), v);
		return v;
	}

	/**
	 * Gets a collection of all the CD items from the database. 
	 * @return
	 */
	public static Collection<CD> getCDs() {
		return CDS.values();
	}
	
	/**
	 * Creates a new instance of a CD item and also adds it to the database.
	 * @param title The title of the new CD.
	 * @return The newly created CD item.
	 */
	public static CD addCD(String title) {
		CD c = new CD(title);
		CDS.put(c.getItemId(), c);
		return c;
	}
	
	/**
	 * Gets an item from the database referenced by the itemId.
	 * @param itemId The itemId of the item to be retrieved.
	 * @return
	 */
	public static Item getItem(int itemId) {
		if (BOOKS.containsKey(itemId)) {
			return BOOKS.get(itemId);
		}
		if (CDS.containsKey(itemId)) {
			return CDS.get(itemId);
		}
		if (DVDS.containsKey(itemId)) {
			return DVDS.get(itemId);
		}
		if (VIDEOGAMES.containsKey(itemId)) {
			return VIDEOGAMES.get(itemId);
		}
		return null;
	}
	
	/**
	 * Removes an item from the database. 
	 * That item should no longer be used as that 
	 * would most likely cause us some problems.
	 * @param itemId
	 */
	public static void removeItem(int itemId) {
		String type = "";
		if (BOOKS.containsKey(itemId)) {
			//item is a book
			type = "Book";
			BOOKS.remove(itemId);
		}
		else if (CDS.containsKey(itemId)) {
			//item is a cd
			type = "CD";
			CDS.remove(itemId);
		}
		else if (DVDS.containsKey(itemId)) {
			//item is a dvd
			type = "DVD";
			DVDS.remove(itemId);
		}
		else if (VIDEOGAMES.containsKey(itemId)) {
			//item is a video game
			type = "VideoGame";
			VIDEOGAMES.remove(itemId);
		}
		else {
			//we don't have that item :(
			return;
		}
		try {
			String sql;
			Connection conn = DBUtil.getConnection();
			Statement s;
			sql = 
				"DELETE FROM " + type + " WHERE itemId = " + itemId + "; " + 
				"DELETE FROM Item WHERE itemId = " + itemId + ";";
			s = conn.createStatement();
			s.executeUpdate(sql);
			s.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
