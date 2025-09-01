package Service;

import java.util.List;
import DAO.CategoryDAO;
import DAO.CategoryDAOImpl;
import Model.Category;

public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public void insert(Category category) {
        categoryDAO.insert(category);
    }

    @Override
    public void edit(Category category) {
        categoryDAO.edit(category);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDAO.get(id);
    }

    @Override
    public List<Category> getAllByUser(int userId) {
        return categoryDAO.getAllByUser(userId);
    }
}

