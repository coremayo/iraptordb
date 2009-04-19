package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomainUtil {
	private static List<Book> BOOKS;
	private static List<DVD> DVDS;
	private static List<VideoGame> VIDEOGAMES;
	private static List<CD> CDS;
	
	protected static void initLists() {
		BOOKS = new ArrayList<Book>();
		DVDS = new ArrayList<DVD>();
		VIDEOGAMES = new ArrayList<VideoGame>();
		CDS = new ArrayList<CD>();
	}
	
	protected static void populateItems() {
		try {
			initLists();
			populateBooks();
			populateDVDs();
			populateCDs();
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
		while (rs.next()) {
			DVDS.add(new DVD(rs));
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
		while (rs.next()) {
			VIDEOGAMES.add(new VideoGame(rs));
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
		while (rs.next()) {
			CDS.add(new CD(rs));
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
		while (rs.next()) {
			BOOKS.add(new Book(rs));
		}
		rs.close();
		ps.close();
		conn.close();
	}
	
	

	public static List<Book> getBooks() {
		return BOOKS;
	}

	public static List<DVD> getDVDs() {
		return DVDS;
	}

	public static List<VideoGame> getVideoGames() {
		return VIDEOGAMES;
	}

	public static List<CD> getCDs() {
		return CDS;
	}
}
