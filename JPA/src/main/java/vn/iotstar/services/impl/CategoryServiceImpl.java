package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao cateDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return cateDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return cateDao.findById(id);
    }

    @Override
    public void insert(Category cate) {
        cateDao.insert(cate);
    }

    @Override
    public void update(Category cate) {
        cateDao.update(cate);
    }

    @Override
    public void delete(int id) {
        cateDao.delete(id);
    }

	@Override
	public void create(Category category) {
		// TODO Auto-generated method stub
		
	}
}
