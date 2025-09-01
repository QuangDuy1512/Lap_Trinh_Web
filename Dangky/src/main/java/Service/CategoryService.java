package Service;

import java.util.List;
import Model.Category;

public interface CategoryService {
    void insert(Category category);
    void edit(Category category);
    void delete(int id);

    Category get(int id);
    List<Category> getAllByUser(int userId);
}
