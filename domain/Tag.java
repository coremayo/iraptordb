package domain;

/**
 * Represents a Tag. 
 * Tags are a way to organize and sort Items.
 * @author Corey
 *
 */
public class Tag {
	private int tagId;
	private String name;
	
	public int getTagId() {
		return tagId;
	}
	protected void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	} 
	
	public String toString() {
		return this.name;
		
	}
}
