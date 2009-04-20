package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class SearchUtil {
	public static Collection<Item> searchTag(String tag) {
		String sql = "SELECT itemId FROM ItemTag LEFT JOIN Tag ON Tag.tagId = ItemTag.tagId WHERE name = ?;";
		Connection conn = DBUtil.getConnection();
		Collection<Item> theList = new ArrayList<Item>();
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
	
	public static Collection<Item> searchGenre(String genre) {
		Collection<Item> theList = new ArrayList<Item>();
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
	
	public static Collection<Item> searchTitle(String title) {
		Collection<Item> theList = new ArrayList<Item>();
		String sql = "SELECT itemId FROM Item WHERE title = ?;";
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
