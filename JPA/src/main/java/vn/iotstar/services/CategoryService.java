package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;

public interface CategoryService {
	List<Category> findAll();
    void insert(Category cate);
	void create(Category cate);
	void update(Category cate);
	void delete(int id);
	Category findById(int id);
	List<Category> findByUserId(int userId);
	List<Category> findByUserIds(List<Integer> userIds);
	List<Category> findByManagedUsers(User manager);
}