package domain;

/**
 * Represents a Creator, i.e. Author, Band, etc. 
 * @author Corey
 *
 */
public class Creator {
	private int creatorId;
	private String name;
	public int getCreatorId() {
		return creatorId;
	}
	protected void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
}
