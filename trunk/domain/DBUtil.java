package domain;

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
import java.util.Vector;

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
	
	public static Connection getConnection() {
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
		int itemId;
		String sqltxt;
		
		itemId = addItem(title, genre, rating, year, notes);
		if (itemId < 0) {
			return ret;
		}
		
		sqltxt = "INSERT INTO CD (itemId) VALUES (?);";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			ret = itemId;
		} catch (Exception e) {
			e.printStackTrace();
			ret = 1;
		}
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
		int itemId;
		String sqltxt;
		
		itemId = DBUtil.addItem(title, genre, rating, year, notes);
		if (itemId < 0) {
			return ret;
		}
		
		sqltxt = "INSERT INTO VideoGame (itemId) VALUES (?);";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
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
	
	/**
	 * Updates the attributes of a DVD in the database
	 * @param itemId
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @param director
	 * @return DVD's itemId if successful or a negative
	 */
	public static int updateDVD(int itemId, String title, String genre, 
			int rating, int year, String notes, String director) {
		int ret = -1;
		String sqltxt;
		
		if (updateItem(itemId, title, genre, rating, year, notes) < 0) {
			return ret;
		}
		
		sqltxt = "UPDATE DVD " +
				    "SET directorName=? " +
				  "WHERE itemId=?;";
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, director);
			ps.setInt(2, itemId);
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
	 * Updates a CD in the database
	 * @param itemId
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @return itemId if successful or negative value
	 */
	public static int updateCD(int itemId, String title, String genre, 
			int rating, int year, String notes) {
		return updateItem(itemId, title, genre, rating, year, notes);
	}
	
	/**
	 * updates a video game in the database
	 * @param itemId
	 * @param title
	 * @param genre
	 * @param rating
	 * @param year
	 * @param notes
	 * @return itemId if successful else negative value
	 */
	public static int updateVideoGame(int itemId, String title, String genre, 
			int rating, int year, String notes) {
		return updateItem(itemId, title, genre, rating, year, notes);
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
	
	/**
	 * removes a DVD from the database
	 * @param itemId
	 * @return positive if successful else negative
	 */
	public static int removeDVD(int itemId) {
		int ret = -1;
		if ((removeItem(itemId)) < 0) {
			return ret;
		}
		String sqltxt = "DELETE FROM DVD WHERE itemId=?;";
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
	 * removes a CD from the database
	 * @param itemId
	 * @return positive if successful else negative
	 */
	public static int removeCD(int itemId) {
		int ret = -1;
		if ((removeItem(itemId)) < 0) {
			return ret;
		}
		String sqltxt = "DELETE FROM CD WHERE itemId=?;";
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
	 * removes a VideoGame from the database
	 * @param itemId
	 * @return positive if successful else negative
	 */
	public static int removeVideoGame(int itemId) {
		int ret = -1;
		if ((removeItem(itemId)) < 0) {
			return ret;
		}
		String sqltxt = "DELETE FROM VideoGame WHERE itemId=?;";
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
	
	/**
	 * 
	 * @param searchTitle
	 * @returna 2D array as follows:<br />
	 * itemId, typeId<br />
	 * itemId, typeId<br />
	 * ...<br />
	 * where typeId is defined in DBUtil.BOOK_TYPE, DBUtil.DVD_TYPE, etc.
	 */
	public static int[][] findByTitle(String searchTitle) {
		int[][] ret = new int[0][2];
		int[] books, DVDs, CDs, videoGames;
		
		books = findBookByTitle(searchTitle);
		DVDs = findDVDByTitle(searchTitle);
		CDs = findCDByTitle(searchTitle);
		videoGames = findVideoGameByTitle(searchTitle);
		
		int i = 0;
		int size = books.length + DVDs.length + CDs.length + videoGames.length;
		if (size == 0) {
			return ret;
		}
		
		ret = new int[size][2];
		for (int item : books) {
			ret[i][0] = item;
			ret[i][1] = BOOK_TYPE;
		}
		for (int item : DVDs) {
			ret[i][0] = item;
			ret[i][1] = DVD_TYPE;
		}
		for (int item : CDs) {
			ret[i][0] = item;
			ret[i][1] = CD_TYPE;
		}
		for (int item : videoGames) {
			ret[i][0] = item;
			ret[i][1] = VIDEO_GAME_TYPE;
		}
		return ret;
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
	 * @return the itemIds of the books found or a zero element 
	 * array  if a problem occurred
	 */
	public static int[] findBookByTitle(String searchTitle) {
		int[] ret = new int[0];
		Vector<Integer> books = new Vector<Integer>();
		//we have to enclose our search term in %'s for the LIKE keyword to work
		searchTitle = "%" + searchTitle + "%";
		
		String sqltxt = 
			    "SELECT Book.itemId " +
			      "FROM Item " +
			"INNER JOIN Book " +
			        "ON Item.itemId=Book.itemId " +
			     "WHERE title " +
			      "LIKE ?;";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, searchTitle);
			ResultSet rs = ps.executeQuery();

			int i = 0;
			while (rs.next()) {
				books.add(rs.getInt("itemId"));
				i++;
			}
			if (i != 0) {
				ret = new int[books.size()];
				for (i = 0; i < books.size(); i++) {
					ret[i] = books.get(i);
				}
			}

			rs.close();
			ps.close();
			conn.close();
		}
		catch (Exception e) {
			ret = new int[0];
			e.printStackTrace();
		}
		return ret;
	}
	
	public static int[] findDVDByTitle(String searchTitle) {
		int[] ret = {};
		Vector<Integer> dvds = new Vector<Integer>();
		//we have to enclose our search term in %'s for the LIKE keyword to work
		searchTitle = "%" + searchTitle + "%";
		
		String sqltxt = 
			    "SELECT DVD.itemId " +
			      "FROM Item " +
			"INNER JOIN DVD " +
			        "ON Item.itemId=DVD.itemId " +
			     "WHERE title " +
			      "LIKE ?;";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, searchTitle);
			ResultSet rs = ps.executeQuery();

			int i = 0;
			while (rs.next()) {
				dvds.add(rs.getInt("itemId"));
				i++;
			}
			if (i != 0) {
				ret = new int[dvds.size()];
				for (i = 0; i < dvds.size(); i++) {
					ret[i] = dvds.get(i);
				}
			}

			rs.close();
			ps.close();
			conn.close();
		}
		catch (Exception e) {
			ret = new int[0];
			e.printStackTrace();
		}
		return ret;
	}
	
	public static int[] findCDByTitle(String searchTitle) {
		int[] ret = {};
		Vector<Integer> cds = new Vector<Integer>();
		//we have to enclose our search term in %'s for the LIKE keyword to work
		searchTitle = "%" + searchTitle + "%";
		
		String sqltxt = 
			    "SELECT CD.itemId " +
			      "FROM Item " +
			"INNER JOIN CD " +
			        "ON Item.itemId=CD.itemId " +
			     "WHERE title " +
			      "LIKE ?;";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, searchTitle);
			ResultSet rs = ps.executeQuery();

			int i = 0;
			while (rs.next()) {
				cds.add(rs.getInt("itemId"));
				i++;
			}
			if (i != 0) {
				ret = new int[cds.size()];
				for (i = 0; i < cds.size(); i++) {
					ret[i] = cds.get(i);
				}
			}

			rs.close();
			ps.close();
			conn.close();
		}
		catch (Exception e) {
			ret = new int[0];
			e.printStackTrace();
		}
		return ret;
	}
	
	public static int[] findVideoGameByTitle(String searchTitle) {
		int[] ret = {};
		Vector<Integer> videoGames = new Vector<Integer>();
		//we have to enclose our search term in %'s for the LIKE keyword to work
		searchTitle = "%" + searchTitle + "%";
		
		String sqltxt = 
			    "SELECT VideoGame.itemId " +
			      "FROM Item " +
			"INNER JOIN VideoGame " +
			        "ON Item.itemId=VideoGame.itemId " +
			     "WHERE title " +
			      "LIKE ?;";
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, searchTitle);
			ResultSet rs = ps.executeQuery();

			int i = 0;
			while (rs.next()) {
				videoGames.add(rs.getInt("itemId"));
				i++;
			}
			if (i != 0) {
				ret = new int[videoGames.size()];
				for (i = 0; i < videoGames.size(); i++) {
					ret[i] = videoGames.get(i);
				}
			}

			rs.close();
			ps.close();
			conn.close();
		}
		catch (Exception e) {
			ret = new int[0];
			e.printStackTrace();
		}
		return ret;
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
