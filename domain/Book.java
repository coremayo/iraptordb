package domain;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Book extends Item {
	private String publisher;
	private String isbn;
	private List<Creator> authors;
	
	protected Book(String title) {
		super(title);
		this.authors = new ArrayList<Creator>();
		
		String sqltxt = "INSERT INTO Book (itemId) VALUES (?);";
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
	
	protected Book(ResultSet rs) throws SQLException {
		super(rs);
		this.authors = new ArrayList<Creator>();
		this.isbn = rs.getString("isbn");
		this.publisher = rs.getString("publisher");
		populateAuthors();
	}
	
	private void populateAuthors() throws SQLException{
		String sqltxt = 
			   "SELECT Creator.name " +
			     "FROM Creator " +
			"LEFT JOIN BookAuthor " +
			       "ON BookAuthor.author_creatorId = Creator.creatorId " +
			    "WHERE BookAuthor.book_itemId = ?;";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sqltxt);
		ps.setInt(1, this.getItemId());
		ResultSet rs = ps.executeQuery();
		Creator author;
		while (rs.next()) {
			author = new Creator();
			author.setCreatorId(rs.getInt("creatorId"));
			author.setName(rs.getString("name"));
			this.authors.add(author);
		}
		rs.close();
		ps.close();
		conn.close();
	}
	
	private void updateDB() {
		String sqltxt = 
			"UPDATE Book " +
			   "SET publisher = ?, " +
			   "isbn = ? " +
			  "WHERE itemId = ?;";
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sqltxt);
			ps.setString(1, this.publisher);
			ps.setString(2, this.isbn);
			ps.setInt(3, this.getItemId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
		updateDB();
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
		updateDB();
	}
	
	public List<Creator> getAuthors() {
		return this.authors;
	}

	public void addAuthor(String authorName) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sqltxt;
		int creatorId;
		
		try {
			//first, we check if the author already exists
			sqltxt = "SELECT COUNT(*) AS theCount FROM Creator WHERE name = ?;";
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, authorName);
			rs = ps.executeQuery();
			int theCount = rs.getInt("theCount");
			rs.close();
			ps.close();
			if (theCount > 0) {
				//if the author exists in the db, then we need get his creatorId
				sqltxt = "SELECT creatorId FROM Creator WHERE name = ?;";
				ps = conn.prepareStatement(sqltxt);
				ps.setString(1, authorName);
				rs = ps.executeQuery();
				creatorId = rs.getInt("creatorId");
				rs.close();
				ps.close();
				
				//now we need to check if he is already added to the book
				sqltxt = 
					"SELECT COUNT(*) " +
					    "AS theCount " +
					  "FROM BookAuthor " +
					 "WHERE book_itemId = ? " +
					   "AND author_creatorId = ?;";
				ps = conn.prepareStatement(sqltxt);
				ps.setInt(1, this.getItemId());
				ps.setInt(2, creatorId);
				rs = ps.executeQuery();
				theCount = rs.getInt("theCount");
				rs.close();
				ps.close();
				if (theCount > 0) {
					//author is already added to book, so we are done
					conn.close();
					return;
				}
			} else {
				//we need to create the new author in the db
				sqltxt = "INSERT INTO Creator (name) VALUES (?);";
				ps = conn.prepareStatement(sqltxt);
				ps.setString(1, authorName);
				ps.executeUpdate();
				ps.close();
				
				//now we need to get the new authors creatorId
				sqltxt = "SELECT MAX(creatorId) AS creatorId FROM Creator;";
				ps = conn.prepareStatement(sqltxt);
				rs = ps.executeQuery();
				creatorId = rs.getInt("creatorId");
				rs.close();
				ps.close();
			}
			//now we can actually add the author to the book in the db
			sqltxt = "INSERT INTO BookAuthor VALUES (?,?);";
			ps = conn.prepareStatement(sqltxt);
			ps.setInt(1, this.getItemId());
			ps.setInt(2, creatorId);
			ps.executeUpdate();
			ps.close();
			conn.close();
			
			//and finally, we need a local copy
			Creator author = new Creator();
			author.setCreatorId(creatorId);
			author.setName(authorName);
			this.authors.add(author);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeAuthor(String authorName) {
		//first, remove local copy
		int creatorId = -1;
		for (Creator c : this.authors) {
			if (c.getName().equals(authorName)) {
				this.authors.remove(c);
				creatorId = c.getCreatorId();
				break;
			}
		}
		if (creatorId > 0) {
			String sqltxt = 
				"DELETE " +
				  "FROM BookAuthor " +
				 "WHERE book_itemId = ? " +
				   "AND author_creatorId = ?;";
			Connection conn = DBUtil.getConnection();
			try {
				PreparedStatement ps = conn.prepareStatement(sqltxt);
				ps.setInt(1, this.getItemId());
				ps.setInt(2, creatorId);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
