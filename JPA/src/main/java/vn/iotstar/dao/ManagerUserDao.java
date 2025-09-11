package vn.iotstar.dao;
import java.util.List;

public interface ManagerUserDao {
    List<Integer> getUserIdsByManagerId(int managerId);
}
