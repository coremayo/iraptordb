package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains various functions helpful for searching the database.
 * @author Corey
 *
 */
public class SearchUtil {
	
	/**
	 * Will return a List of items that have the given tag.
	 * @param tag The name of the tag to search for.
	 * @return
	 */
	public static List<Item> searchTag(String tag) {
		String sql = "SELECT itemId FROM ItemTag LEFT JOIN Tag ON Tag.tagId = ItemTag.tagId WHERE name = ?;";
		Connection conn = DBUtil.getConnection();
		List<Item> theList = new ArrayList<Item>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tag);
			ResultSet rs = ps.executeQuery();
			Item i;
			while(rs.next()) {
				i = DomainUtil.getItem(rs.getInt("itemId"));
				if (null != i) {
					theList.add(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theList;
	}
	
	/**
	 * Returns a List of Items that have the given Genre.
	 * @param genre The genre name to search for.
	 * @return
	 */
	public static List<Item> searchGenre(String genre) {
		List<Item> theList = new ArrayList<Item>();
		String sql = "SELECT itemId FROM Item WHERE genre = ?;";
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, genre);
			ResultSet rs = ps.executeQuery();
			Item i;
			while (rs.next()) {
				i = DomainUtil.getItem(rs.getInt("itemId"));
				if (null != i) {
					theList.add(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theList;
	}
	
	/**
	 * Returns a List of Items that have a similar title to the search.
	 * @param title The title to search for.
	 * @return
	 */
	public static List<Item> searchTitle(String title) {
		List<Item> theList = new ArrayList<Item>();
		title = "%" + title + "%";
		String sql = "SELECT itemId FROM Item WHERE title LIKE ?;";
		Connection conn = DBUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			Item i;
			while (rs.next()) {
				i = DomainUtil.getItem(rs.getInt("itemId"));
				if (null != i) {
					theList.add(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theList;
	}
}
