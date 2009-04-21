package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Hashtable;

public class DomainUtil {
	
	private static Hashtable<Integer,Book> BOOKS;
	private static Hashtable<Integer,DVD> DVDS;
	private static Hashtable<Integer,VideoGame> VIDEOGAMES;
	private static Hashtable<Integer,CD> CDS;
	
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

	public static Collection<Book> getBooks() {
		return BOOKS.values();
	}
	
	public static Book addBook(String title) {
		Book b = new Book(title);
		BOOKS.put(b.getItemId(), b);
		return b;
	}

	public static Collection<DVD> getDVDs() {
		return DVDS.values();
	}
	
	public static DVD addDVD(String title) {
		DVD d = new DVD(title);
		DVDS.put(d.getItemId(), d);
		return d;
	}

	public static Collection<VideoGame> getVideoGames() {
		return VIDEOGAMES.values();
	}
	
	public static VideoGame addVideoGame(String title) {
		VideoGame v = new VideoGame(title);
		VIDEOGAMES.put(v.getItemId(), v);
		return v;
	}

	public static Collection<CD> getCDs() {
		return CDS.values();
	}
	
	public static CD addCD(String title) {
		CD c = new CD(title);
		CDS.put(c.getItemId(), c);
		return c;
	}
	
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
