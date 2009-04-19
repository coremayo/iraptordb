package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DVD extends Item {
	private String directorName;
	
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
	
	protected DVD(ResultSet rs) throws SQLException {
		super(rs);
		this.directorName = rs.getString("directorName");
	}
	
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
}
