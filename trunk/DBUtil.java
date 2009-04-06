

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* TODO convert sql queries to use prepared statements
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
		
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, title);
			ps.setString(2, genre);
			ps.setInt(3, rating);
			ps.setInt(4, year);
			ps.setString(5, notes);
			ps.execute();
			
			ret = mostRecentItem();
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
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
		
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, title);
			ps.setString(2, genre);
			ps.setInt(3, rating);
			ps.setInt(4, year);
			ps.setString(5, notes);
			ps.setInt(6, itemId);
			ps.execute();
			
			ret = itemId;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
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
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.execute();
			
			ret = 1;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
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
		
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.setString(2, publisher);
			ps.setString(3, isbn);
			ps.execute();
			
			ret = itemId;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
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
		
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, publisher);
			ps.setString(2, isbn);
			ps.setInt(3, itemId);
			ps.execute();
			
			ret = itemId;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
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
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, itemId);
			ps.execute();
			
			ret = 1;
		} catch (Exception e) {
			ret = -1;
			System.err.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
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
		//this sql query will get the title for the given itemId
		String sqltxt = 
			 "SELECT title FROM Item " +
			  "WHERE itemId = " + itemId + ";";
		
		Connection conn = null; //first, we need to set up the connection
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			Statement query = conn.createStatement();
			
			//this will return the first record in the "title" column
			return query.executeQuery(sqltxt).getString("title");
		}
		catch (Exception e) {
			System.err.println("getTitle function failed");
			System.err.println(e.getMessage());
		}
		finally {
			if (conn != null) {
				try {             //we have to close the connection no
					conn.close(); //matter what
				}
				catch (SQLException e) { //this will occur if the connection 
					System.err.println(e.getMessage()); //fails to close
				}
			}
		}
		return ""; //if something happened, & we haven't returned anything yet,
	}              //we will return an empty string
	
	/**
	 * This function takes in a string and escapes every special character
	 * in it so that it should not cause any SQL injection
	 * <br /><br />
	 * For example: in Java <em>"INSERT into Item (title) VALUES ('" + titleVar 
	 * + "');"</em> should be changed to <em>"INSERT into Item (title) VALUES 
	 * ('" + makeSQLSafe(titleVar) + "');"</em> so that everything that needs 
	 * will be escaped
	 * 
	 * @param input string which might cause SQL injection
	 * @return string which should not cause SQL injection
	 */
	private static String makeSQLSafe(String input) {
		String output;
		
		/* TODO implement makeSQLSafe function 
		 * (wont be needed if using prepared statements)
		 * make this function actually do something :)
		 * perhaps it could replace all ' with '' for starters 
		 */
		output = input;
		return output;
	}
	
	/**
	 * This function will send an INSERT, UPDATE, or DELETE 
	 * statement to the database
	 * @param sqltxt INSERT statement to send
	 * @return positive value if successful, negative if failed
	 */
	private static int insertQuery(String sqltxt) {
		int ret = -1;
		Connection conn = null; //first, set up connection
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + fileName.getName());
			Statement query = conn.createStatement();
			
			query.executeUpdate(sqltxt); //this line executes the INSERT
			
			ret = 1;
		}
		catch (Exception e) { //do some error handling
			System.err.println("'" + sqltxt + "' failed for some reason");
			System.err.println(e.getMessage());
			
		} finally { //no matter what, try to close connection
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					System.err.println(e.getMessage());
					ret = -1;
				}
			}
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

		Connection conn = null; //same stuff to set up connection
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + fileName.getName());
			Statement query = conn.createStatement();
			ResultSet rs = query.executeQuery(sqltxt);
			ret = rs.getInt("itemId");
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			ret = -1;
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return ret;
	}
}
