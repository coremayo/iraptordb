package iRaptorPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* converted sql queries to use prepared statements
 * useful website about prepared statements and sql injection:
 * http://www.owasp.org/index.php/Preventing_SQL_Injection_in_Java
 */

/**
 * This class is used to interface with the database. Any function that 
 * interacts directly should be put in this file, so that it is all in one 
 * place and easier to maintain.
 * 
 * @author Corey
 *
 */
public class DBUtil {
	// this is the database file that our program will read/write to
	public static File fileName = new File("test.db");
	
	/**
	 * DBUtil.BOOK_TYPE, DBUtil.DVD_TYPE, DBUtil.VIDEO_GAME_TYPE, and 
	 * DBUtil.CD_TYPE are ints that are used to determine which type of item 
	 * are returned by certain functions in the DBUtil class.
	 */
	public static final int BOOK_TYPE=1, DVD_TYPE=2, 
			VIDEO_GAME_TYPE=3, CD_TYPE=4;
	
	private static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * This function will add a new Item to the database then return its itemId
	 * The only parameter required is the title, all others can be set to null
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @return The new item's id or a negative value if unsuccessful
	 */
	private static int addItem(String title, String genre, 
			int rating, int year, String notes) {
		int ret = -1;
		String sqltxt = 
			"INSERT INTO Item (" +
			             "title, " +
			             "genre, " +
			             "rating, " +
			             "year, " +
			             "notes, " +
			             "dateAdded" +
			") VALUES (" +
			             "?, " +
			             "?, " +
			             "?, " +
			             "?, " +
			             "?, " +
			             "CURRENT_TIMESTAMP);";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, title);
			ps.setString(2, genre);
			ps.setInt(3, rating);
			ps.setInt(4, year);
			ps.setString(5, notes);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = mostRecentItem();
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		}
		return ret;
	}
	
	/**
	 * This function will modify the row in the Item table with the specified 
	 * itemId with the given values 
	 * @param itemId
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @return The itemId modified if successful or a negative value otherwise.
	 */
	private static int updateItem(int itemId, String title, String genre, 
			int rating, int year, String notes) {
		int ret = -1;
		String sqltxt = 
		"UPDATE Item " +
		   "SET title=?, " + 
		        "genre=?, " +
		        "rating=?, " +
		        "year=?, " + 
		        "notes=? " +
		 "WHERE itemId=?;";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, title);
			ps.setString(2, genre);
			ps.setInt(3, rating);
			ps.setInt(4, year);
			ps.setString(5, notes);
			ps.setInt(6, itemId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = itemId;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		}
		return ret;
	}
	
	/**
	 * The removeItem function will remove the row in the Item table with the 
	 * specified itemId
	 * @param itemId the itemId of the item to be removed.
	 * @return Positive integer if successful, negative otherwise.
	 */
	private static int removeItem(int itemId){
		int ret = -1;
		//parameterized form of our sql query
		String sqltxt = "DELETE FROM Item WHERE itemId = ?";
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = 1;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		}
		return ret;
	}
	
	/**
	 * The addBook function will add a new row into the Item table then add a 
	 * row to the Book table with the same itemId. The only parameter required 
	 * is title; all others can be set to null if unknown.
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @param publisher
	 * @param isbn
	 * @return The itemId of the newly added book or a 
	 * negative value if unsuccessful.
	 */
	public static int addBook(String title, String genre, int rating, 
			int year, String notes, String publisher, String isbn) {
		int itemId;
		int ret = -1;
		String sqltxt;
		
		itemId = addItem(title, genre, rating, year, notes);
		if (itemId < 0) {  //if a negative value was returned from
			return ret; //adding to the Item table, then we have failed.
		}
		
		sqltxt = "INSERT INTO Book (" +
				              "itemId, " +
				              "publisher, " +
				              "isbn" +
				    ") VALUES (" + 
				              "?, " +
				              "?, " +
				              "?);";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.setString(2, publisher);
			ps.setString(3, isbn);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = itemId;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		}
		return ret;
	}
	
	/**
	 * Adds a new DVD to the database
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @param director
	 * @return The itemId of the newly added DVD or a negative if it failed.
	 */
	public static int addDVD(String title, String genre, int rating, int year, 
			String notes, String director) {
		int ret = -1, itemId;
		String sqltxt;
		
		itemId = addItem(title, genre, rating, year, notes);
		if (itemId < 0) {
			return ret;
		}
		
		sqltxt = 
			"INSERT INTO DVD (" +
			             "itemId, " +
			             "directorName" +
			   ") VALUES (" +
			             "?, " +
			             "?);";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.setString(2, director);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = itemId;
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
		}
		
		return ret;
	}
	
	/**
	 * Adds a new CD to the database
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @return The itemId of the newly added CD or a negative if it failed.
	 */
	public static int addCD(String title, String genre, int rating, int year, 
			String notes) {
		int ret = -1;
		//TODO implement addCD
		//TODO add test for addCD
		return ret;
	}
	
	/**
	 * Adds a new VideoGame to the database.
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @return The itemId of the newly added VideoGame or 
	 * a negative if it failed.
	 */
	public static int addVideoGame(String title, String genre, int rating, 
			int year, String notes) {
		int ret = -1;
		//TODO implement addVideoGame
		//TODO add test for addVideoGame
		return ret;
	}
	
	/**
	 * This function will modify the row in the Book table with the specified 
	 * itemId and the given values. 
	 * @param itemId
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @param publisher
	 * @param isbn
	 * @return The book's itemId if successful or a negative value otherwise.
	 */
	public static int updateBook(int itemId, String title, String genre, 
			int rating, int year, String notes, String publisher, String isbn) {
		String sqltxt;
		int ret = -1;
		if (updateItem(itemId, title, genre, rating, year, notes) < 0) {
			return ret;
		}
		
		sqltxt = 
			"UPDATE Book " +
			   "SET publisher=?, " +
			   		"isbn=? " +
			 "WHERE itemId=?;";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, publisher);
			ps.setString(2, isbn);
			ps.setInt(3, itemId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = itemId;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		}
		return ret;
	}
	
	public static int updateDVD(int itemId, String title, String genre, 
			int rating, int year, String notes, String director) {
		int ret = -1;
		//TODO implement updateDVD
		//TODO add test for updateDVD
		return ret;
	}
	
	public static int updateCD(int itemId, String title, String genre, 
			int rating, int year, String notes) {
		int ret = -1;
		//TODO implement updateCD
		//TODO add test for updateCD
		return ret;
	}
	
	public static int updateVideoGame(int itemId, String title, String genre, 
			int rating, int year, String notes) {
		int ret = -1;
		//TODO implement updateVideoGame
		//TODO add test for updateVideoGame
		return ret;
	}
	
	/**
	 * The removeBook function will remove the row from the Item and Book 
	 * tables which have the same itemId.  
	 * @param itemId The itemId of the item which will be removed.
	 * @return A positive integer if successful, negative if failed.
	 */
	public static int removeBook(int itemId) {
		int ret = -1;
		if ((removeItem(itemId)) < 0) {
			return ret;
		}
		String sqltxt = "DELETE FROM Book WHERE itemId = ?;";
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = 1;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		}
		return ret;
	}
	
	//TODO implement removeDVD
	//TODO add test for removeDVD
	public static int removeDVD(int itemId) {
		return -1;
	}
	
	//TODO implement removeCD
	//TODO add test for removeCD
	public static int removeCD(int itemId) {
		return -1;
	}
	
	//TODO implement removeVideoGame
	//TODO add test for removeVideoGame
	public static int removeVideoGame(int itemId) {
		return -1;
	}
	
	/**
	 * The getTitle function will return the title in the Item table in the 
	 * database associated with the specified itemId
	 * @param itemId itemId which we will look up
	 * @return that particular item's title or empty string if problems arise
	 */
	public static String getTitle(int itemId) {
		//this SQL query will get the title for the given itemId
		String sqltxt = 
			 "SELECT title FROM Item WHERE itemId = ?;";
		String ret;
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			
			//this will return the first record in the "title" column
			ret = ps.executeQuery().getString("title");
			ps.close();
			conn.close();
		}
		catch (Exception e) {
			System.err.println("getTitle function failed while trying to get " +
					"title for item: " + itemId);
			e.printStackTrace();
			ret = "";
		}
		return ret;
	}
	
	/**
	 * Finds the most recently added item
	 * @return the itemId of the item added to the database most recently or a 
	 * negative value if something failed
	 */
	public static int mostRecentItem() {
		//Since we are autoincrementing, the most recent 
		//item should be the one with the max itemId.
		String sqltxt = "SELECT MAX(itemId) AS itemId FROM Item;";
		int ret = -1;

		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ret = ps.executeQuery().getInt("itemId");
			ps.close();
			conn.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			ret = -1;
		}
		return ret;
	}

	/**
	 * The setUpDatabase() method will run all CREATE TABLE statements on a 
	 * newly created file so that it can be used by the application
	 */
	public static void setUpDatabase() {
		Connection conn = getConnection();
		try {
			File file = new File("createTables.sql");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			SqlScript script = new DBUtil().new SqlScript(br, conn);
			
			script.loadScript();
			script.execute();
			
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	//TODO implement all find methods
	/**
	 * The findBookByTitle method with attempt to search the database for a 
	 * book with the given title.
	 * <br /><br />
	 * there will eventually be the following search methods:<br />
	 * -findBookByTitle<br />
	 * -findDVDByTitle<br />
	 * -findCDByTitle<br />
	 * -findVideoGameByTitle<br />
	 * -findBookByGenre<br />
	 * -findDVDByGenre<br />
	 * -findCDByGenre<br />
	 * -findVideoGameByGenre<br />
	 * -findByTitle<br />
	 * -findByGenre<br />
	 * 
	 * The final two methods, findByTitle and findByGenre, will search items of 
	 * all mediums and will return a 2D array as follows:<br />
	 * itemId, typeId<br />
	 * itemId, typeId<br />
	 * ...<br />
	 * where typeId is defined in DBUtil.BOOK_TYPE, DBUtil.DVD_TYPE, etc.
	 * 
	 * @param searchTitle the title to search the database for
	 * @return the itemId of the first book found or a negative value if none 
	 * were found or a problem occurred
	 */
	public static int[] findBookByTitle(String searchTitle) {
		int[] ret = {-1};
		return ret;
		//TODO implement findBookByTitle to return an array of itemId's
//		//we have to enclose our search term in %'s for the LIKE keyword to work
//		searchTitle = "%" + searchTitle + "%";
//		
//		String sqltxt = 
//			    "SELECT Book.itemId " +
//			      "FROM Item " +
//			"INNER JOIN Book " +
//			        "ON Item.itemId=Book.itemId " +
//			     "WHERE title " +
//			      "LIKE ?;";
//		
//		Connection conn = getConnection();
//		
//		try {
//			PreparedStatement ps = conn.prepareStatement(sqltxt);
//			ps.setString(1, searchTitle);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				ret = rs.getInt("itemId");
//			} else {
//				ret = -1;
//			}
//			rs.close();
//			ps.close();
//			conn.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			ret = -1;
//		}
//		return ret;
	}

	/**
	 * Will execute a SQL script file on a connection
	 * 
	 * taken from
	 * http://www.sternsquirt.com/blog/?cat=10
	 *
	 */
	public class SqlScript {

		public final static char QUERY_ENDS = ';';

		private Connection conn;
		private BufferedReader rdr;
		private Statement statement;

		/**
		 * Constructor: takes a bufferedReader and a sql connection to create the SqlScript object.
		 * Note that construction does not automatically read the script.
		 * @param bufRdr BufferedReader to the script data.
		 * @param connection SQL Connection
		 * @throws SQLException
		 */
		public SqlScript(BufferedReader bufRdr, Connection connection) throws SQLException {
			rdr = bufRdr;
			conn = connection;
			statement = conn.createStatement();
		}

		/**
		 * Loads the Sql Script from the BufferedReader and parses it into a statement.
		 * @throws IOException
		 * @throws SQLException
		 */
		public void loadScript() throws IOException, SQLException {
			String line;
			StringBuffer query = new StringBuffer();
			boolean queryEnds = false;

			while ((line = rdr.readLine()) != null) {
				if (isComment(line))
					continue;
				queryEnds = checkStatementEnds(line);
				query.append(line);
				if (queryEnds) {
					statement.addBatch(query.toString());
					query.setLength(0);
				}
			}
		}

		/**
		 * @param line
		 * @return
		 */
		private boolean isComment(String line) {
			if ((line != null) && (line.length() > 0))
				return (line.charAt(0) == '#');
			return false;
		}

		/**
		 * Executes the statement created by loadScript.
		 *
		 * @throws IOException
		 * @throws SQLException
		 */
		public void execute() throws IOException, SQLException {
			statement.executeBatch();
		}

		private boolean checkStatementEnds(String s) {
			return (s.indexOf(QUERY_ENDS) != -1);
		}

		/**
		 * @return the statement
		 */
		public Statement getStatement() {
			return statement;
		}

		/**
		 * @param statement the statement to set
		 */
		public void setStatement(Statement statement) {
			this.statement = statement;
		}
	}
}
