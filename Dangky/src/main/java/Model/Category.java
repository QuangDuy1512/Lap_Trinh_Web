package Model;

public class Category {
	private int id;
    private String name;
    private String icon;
    private int userId;  // liên kết với Users.id
    
    public Category() {}
    public Category(int id, String name, String icon, int userId) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.userId = userId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

}
