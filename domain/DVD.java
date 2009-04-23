package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a row from the DVD table in the database.
 * @author Corey
 *
 */
public class DVD extends Item {
	private String directorName;
	
	private final int NUMFIELDS = 1;
	
	/**
	 * Creates a new instance of a DVD and also adds it to the database.
	 * @param title The title of the new DVD.
	 */
	protected DVD(String title) {
		super(title);
		
		String sqltxt = "INSERT INTO DVD (itemId) VALUES (?);";
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, this.getItemId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates a DVD from the results of a database query. 
	 * The query should be something like: 
	 * "SELECT Item.*, DVD.* FROM ITEM INNER JOIN DVD ON DVD.itemId = Item.itemId ..."  
	 * @param rs The ResultSet created by executing the database query.
	 * @throws SQLException
	 */
	protected DVD(ResultSet rs) throws SQLException {
		super(rs);
		this.directorName = rs.getString("directorName");
	}
	
	/**
	 * Commits any changes that have been made to the item object to the database. 
	 * Usually called at the end of a setter function.
	 */
	private void updateDB() {
		String sqltxt = "UPDATE DVD SET directorName = ? WHERE itemId = ?;";
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, this.directorName);
			ps.setInt(2, this.getItemId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
		updateDB();
	}

	@Override
	public String toString() {
		return "DVD titled: " + this.getTitle() + " directed by: " + this.getDirectorName();
	}
	
	public String getType() {
		return "DVD";
	}

	@Override
	public int numberOfFields() {
		return Item.NUMFIELDS + this.NUMFIELDS;
	}
}
