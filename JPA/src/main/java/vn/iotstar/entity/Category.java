package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll",query = "Select c From Category c")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(columnDefinition = "NVARCHAR(255)")
    private String name;
    
    @Column(columnDefinition = "NVARCHAR(50)")
    private String code;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String images;
    
    @Column(columnDefinition = "bit")
    private Boolean status;
    
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Category() {
        this.status = true; // Set default value
    }

    public Category(int id, String name, String code, String images, Boolean status, User user) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.images = images;
        this.status = status != null ? status : true;
        this.user = user;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status != null ? status : true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    // Helper methods for controller
    public int getUserid() {
        return user != null ? user.getId() : 0;
    }
    
    public void setUserid(int userid) {
        if (this.user == null) {
            this.user = new User();
        }
        this.user.setId(userid);
    }
}