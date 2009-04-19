package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoGame extends Item {
	protected VideoGame(String title) {
		super(title);
		
		String sqltxt = "INSERT INTO VideoGame (itemId) VALUES (?);";
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
	
	protected VideoGame(ResultSet rs) throws SQLException {
		super(rs);
	}
}
