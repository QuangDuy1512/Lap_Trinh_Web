package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Objects;

public class ManagerUserId implements Serializable {
    private int managerId;
    private int userId;

    public ManagerUserId() {}
    public ManagerUserId(int managerId, int userId) {
        this.managerId = managerId;
        this.userId = userId;
    }
    // getter, setter, equals, hashCode
    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerUserId that = (ManagerUserId) o;
        return managerId == that.managerId && userId == that.userId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(managerId, userId);
    }
}
