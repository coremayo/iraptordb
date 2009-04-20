package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Item {
	private int itemId;
	private String title;
	private String genre;
	private int rating;
	private int year;
	private Date dateAdded;
	private String notes;
	private List<Tag> tags;

	protected Item(ResultSet rs) throws SQLException {
		this.dateAdded = rs.getDate("dateAdded");
		this.genre = rs.getString("genre");
		this.itemId = rs.getInt("Item.itemId");
		this.notes = rs.getString("notes");
		this.rating = rs.getInt("rating");
		this.title = rs.getString("title");
		populateTags();
	}
	
	protected Item(String title) {
		super();
		this.tags = new ArrayList<Tag>();
		this.title = title;
		this.rating = 0;
		
		String sqltxt = 
			  "INSERT " +
			    "INTO Item (" +
			          "title, " +
			          "rating, " +
			          "dateAdded" +
			") VALUES (" +
			          "?, " +
			          "?, " +
			          "CURRENT_TIMESTAMP);";
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, title);
			ps.setInt(2, 0);
			ps.executeUpdate();
			ps.close();
			
			sqltxt = "SELECT MAX(itemId) AS itemId, dateAdded FROM Item;";
			ps = conn.prepareStatement(sqltxt);
			ResultSet rs = ps.executeQuery();
			this.itemId = rs.getInt("itemId");
			this.dateAdded = rs.getDate("dateAdded");
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void populateTags() throws SQLException {
		String sqltxt = 
			    "SELECT Tag.name " +
			      "FROM Tag " +
			"INNER JOIN ItemTag " +
			        "ON ItemTag.tagid = Tag.tagId " +
			     "WHERE ItemTag.itemId = ?;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ps.setInt(1, this.itemId);
		ResultSet rs = ps.executeQuery();
		Tag tag;
		while (rs.next()) {
			tag = new Tag();
			tag.setName(rs.getString("Tag.name"));
			tags.add(tag);
		}
		rs.close();
		ps.close();
		conn.close();
	}
	
	private void updateDB(){
		String sqltxt = 
			"UPDATE Item " +
			   "SET title=?, " + 
			        "genre=?, " +
			        "rating=?, " +
			        "year=?, " + 
			        "notes=? " +
			 "WHERE itemId=?;";

		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, this.title);
			ps.setString(2, this.genre);
			ps.setInt(3, this.rating);
			ps.setInt(4, this.year);
			ps.setString(5, this.notes);
			ps.setInt(6, this.itemId);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void addTag(String tagName) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sqltxt;
		int tagId;
		
		try {
			//first, check whether the tag already exists
			sqltxt = 
				"SELECT COUNT(*) " +
				    "AS theCount " +
				  "FROM Tag " +
				 "WHERE name = ?;";
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, tagName);
			rs = ps.executeQuery();
			int theCount = rs.getInt("theCount");
			rs.close();
			ps.close();
			if (theCount > 0) {
				//if tag does exist, check if the item is already tagged
				//so, first we need to find out the tagId
				sqltxt = "SELECT tagId FROM Tag WHERE name = ?;";
				ps = conn.prepareStatement(sqltxt);
				ps.setString(1, tagName);
				rs = ps.executeQuery();
				tagId = rs.getInt("tagId");
				rs.close();
				ps.close();
				
				//now, check if the item is already tagged
				sqltxt = 
					"SELECT COUNT(*) " +
					    "AS theCount " +
					  "FROM ItemTag " +
					 "WHERE tagId = ? " +
					   "AND itemId = ?;";
				ps = conn.prepareStatement(sqltxt);
				ps.setInt(1, tagId);
				ps.setInt(2, this.itemId);
				rs = ps.executeQuery();
				theCount = rs.getInt("theCount");
				rs.close();
				ps.close();
				if (theCount > 0) {
					//if the item is already tagged, then we're done
					conn.close();
					return;
				}
			} else {
				//if tag doesn't exist, create it
				sqltxt = "INSERT INTO Tag (name) VALUES (?);";
				ps = conn.prepareStatement(sqltxt);
				ps.setString(1, tagName);
				ps.executeUpdate();
				ps.close();
				
				//we need our new tag's id
				sqltxt = "SELECT MAX(tagId) AS tagId FROM Tag;";
				ps = conn.prepareStatement(sqltxt);
				rs = ps.executeQuery();
				tagId = rs.getInt("tagId");
				rs.close();
				ps.close();
			}
			//now, we actually get to add the tag-item relationship to the db
			sqltxt = "INSERT INTO ItemTag VALUES (?,?);";
			ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, this.itemId);
			ps.setInt(2, tagId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			//one last thing, we also need a local Tag object
			Tag tag = new Tag();
			tag.setName(tagName);
			tag.setTagId(tagId);
			this.tags.add(tag);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeTag(String tagName) {
		//first, remove the tag from our local list
		int tagId = -1;
		for (Tag t : tags) {
			if (t.getName().equals(tagName)) {
				tags.remove(t);
				tagId = t.getTagId();
				break;
			}
		}
		//now, write out our change to the database, if needed
		if (tagId > 0) {
			String sqltxt = 
				"DELETE " +
				  "FROM ItemTag " +
				 "WHERE tagId = ? " +
				   "AND itemId = ?;";
			try {
				Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sqltxt);
				ps.setInt(1, tagId);
				ps.setInt(2, this.itemId);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getItemId() {
		return itemId;
	}
//	I don't think there is any reason we need this
//	public void setItemId(int itemId) {
//		this.itemId = itemId;
//		updateDB();
//	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		updateDB();
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
		updateDB();
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
		updateDB();
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
		updateDB();
	}
	public Date getDateAdded() {
		return dateAdded;
	}
//	don't think we really need this either
//	public void setDateAdded(Date dateAdded) {
//		this.dateAdded = dateAdded;
//		updateDB();
//	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
		updateDB();
	}
}
