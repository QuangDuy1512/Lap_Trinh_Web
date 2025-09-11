package vn.iotstar.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "manager_user")
@IdClass(ManagerUserId.class)
public class ManagerUser implements Serializable {
    @Id
    @Column(name = "manager_id")
    private int managerId;

    @Id
    @Column(name = "user_id")
    private int userId;

    public ManagerUser() {}
    public ManagerUser(int managerId, int userId) {
        this.managerId = managerId;
        this.userId = userId;
    }
    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
