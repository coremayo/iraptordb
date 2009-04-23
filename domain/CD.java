package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CD in the database.
 * @author Corey
 *
 */
public class CD extends Item {
	private List<Creator> artists;
	
	private final int NUMFIELDS = 1;
	
	/**
	 * Creates a new instance of CD and also adds that CD to the database.
	 * @param title The title of the new CD.
	 */
	protected CD(String title) {
		super(title);
		this.artists = new ArrayList<Creator>();
		
		String sqltxt = "INSERT INTO CD (itemId) VALUES (?);";
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
	 * Generates a CD instance from a database query.
	 * The database query should be something like: 
	 * "SELECT Item.*, CD.* FROM Item INNER JOIN CD ON CD.itemId = Item.itemId ..."
	 * @param rs The ResultSet created by executing the database query.
	 * @throws SQLException
	 */
	protected CD(ResultSet rs) throws SQLException {
		super(rs);
		this.artists = new ArrayList<Creator>();
		populateArtists();
	}
	
	/**
	 * Gets all the Artists from the database for the CD.
	 * @throws SQLException
	 */
	private void populateArtists() throws SQLException {
		String sqltxt = 
			   "SELECT Creator.name " +
			     "FROM Creator " +
			"LEFT JOIN CDArtist " +
			       "ON CDArtist.artist_creatorId = Creator.creatorId " +
			    "WHERE CDArtist.cd_itemId = ?;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ps.setInt(1, this.getItemId());
		ResultSet rs = ps.executeQuery();
		Creator artist;
		while (rs.next()) {
			artist = new Creator();
			artist.setCreatorId(rs.getInt("creatorId"));
			artist.setName(rs.getString("name"));
			artists.add(artist);
		}
		rs.close();
		ps.close();
		conn.close();
	}

	public List<Creator> getArtists() {
		return artists;
	}

	public void addArtist(String artistName) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sqltxt;
		int creatorId;
		
		try {
			//first, check if artist already exists in db
			sqltxt = "SELECT COUNT(*) AS theCount FROM Creator WHERE name = ?;";
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, artistName);
			rs = ps.executeQuery();
			int theCount = rs.getInt("theCount");
			rs.close();
			ps.close();
			
			if (theCount > 0) {//if we have creator rows with the given name
				//since our creator already exists, we need to get his id
				sqltxt = "SELECT creatorId FROM Creator WHERE name = ?;";
				ps = conn.prepareStatement(sqltxt);
				ps.setString(1, artistName);
				rs = ps.executeQuery();
				creatorId = rs.getInt("creatorId");
				rs.close();
				ps.close();
				
				//check if artist already added to cd
				sqltxt = 
					"SELECT COUNT(*) " +
					    "AS theCount " +
					  "FROM CDArtist " +
					 "WHERE cd_itemId = ? " +
					   "AND artist_creatorId = ?;";
				ps = conn.prepareStatement(sqltxt);
				ps.setInt(1, this.getItemId());
				ps.setInt(2, creatorId);
				rs = ps.executeQuery();
				theCount = rs.getInt("theCount");
				rs.close();
				ps.close();
				
				if (theCount > 0) { //artist-cd relationship is already in db
					conn.close();
					return;
				}
			} else {
				//we need to create our artist in the db
				sqltxt = "INSERT INTO Creator (name) VALUES (?);";
				ps = conn.prepareStatement(sqltxt);
				ps.setString(1, artistName);
				ps.executeUpdate();
				ps.close();
				
				//now we need the new artist's creatorId
				sqltxt = "SELECT MAX(creatorId) AS creatorId FROM Creator;";
				ps = conn.prepareStatement(sqltxt);
				rs = ps.executeQuery();
				creatorId = rs.getInt("creatorId");
				rs.close();
				ps.close();
			}
			//now actually add artist-cd relationship to db
			sqltxt = "INSERT INTO CDArtist VALUES (?,?);";
			ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, this.getItemId());
			ps.setInt(2, creatorId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			//finally, we want a local copy too
			Creator c = new Creator();
			c.setCreatorId(creatorId);
			c.setName(artistName);
			this.artists.add(c);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeArtist(String artistName) {
		//first, we need to remove our local Creator object
		int creatorId = -1;
		for (Creator c : this.artists) {
			if (c.getName().equals(artistName)) {
				this.artists.remove(c);
				creatorId = c.getCreatorId();
				break;
			}
		}
		if (creatorId > 0) {
			//now write to database
			String sqltxt = 
				"DELETE " +
				  "FROM CDArtist " +
				 "WHERE cd_itemId = ? " +
				   "AND artist_creatorId = ?;";
			Connection conn = DBUtil.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement(sqltxt);
				ps.executeUpdate();
				ps.setInt(1, this.getItemId());
				ps.setInt(2, creatorId);
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("CD titled: ");
		ret.append(this.getTitle());
		
		for (Creator c : artists) {
			ret.append(" with artist: ");
			ret.append(c.getName());
		}
		return ret.toString();
	}
	
	public String getType() {
		return "CD";
	}

	@Override
	public int numberOfFields() {
		return Item.NUMFIELDS + this.NUMFIELDS;
	}
}
