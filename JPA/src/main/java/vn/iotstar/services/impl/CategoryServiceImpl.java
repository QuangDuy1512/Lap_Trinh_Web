package vn.iotstar.services.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    private EntityManager entityManager;

    public CategoryServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public CategoryServiceImpl() {
        super();
    }


    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void insert(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void update(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        try {
            entityManager.getTransaction().begin();
            Category category = entityManager.find(Category.class, id);
            if (category != null) {
                entityManager.remove(category);
                entityManager.flush(); // Force sync with database
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void create(Category category) {
        insert(category);
    }
    
    @Override
    public List<Category> findByUserId(int userId) {
        TypedQuery<Category> query = entityManager.createQuery(
            "SELECT c FROM Category c WHERE c.user.id = :userId", 
            Category.class
        );
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Category> findByUserIds(List<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return List.of();
        }
        TypedQuery<Category> query = entityManager.createQuery(
            "SELECT c FROM Category c WHERE c.user.id IN :userIds", 
            Category.class
        );
        query.setParameter("userIds", userIds);
        return query.getResultList();
    }

    @Override
    public List<Category> findByManagedUsers(User manager) {
        try {
            String jpql = "SELECT c FROM Category c WHERE c.user.id IN " +
                         "(SELECT mu.userId FROM ManagerUser mu WHERE mu.managerId = :managerId)";
            TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
            query.setParameter("managerId", manager.getId());
            List<Category> categories = query.getResultList();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}