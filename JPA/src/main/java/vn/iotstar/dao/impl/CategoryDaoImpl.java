package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c LEFT JOIN FETCH c.user";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            List<Category> categories = query.getResultList();
            // Set default status if null
            categories.forEach(c -> {
                if (c.getStatus() == null) {
                    c.setStatus(true);
                }
            });
            return categories;
        } finally {
            em.close();
        }
    }

    @Override
    public Category findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            Category category = em.find(Category.class, id);
            if (category != null && category.getStatus() == null) {
                category.setStatus(true);
            }
            return category;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findByUserId(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.user.id = :userId";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("userId", userId);
            List<Category> categories = query.getResultList();
            categories.forEach(c -> {
                if (c.getStatus() == null) {
                    c.setStatus(true);
                }
            });
            return categories;
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            if (category.getStatus() == null) {
                category.setStatus(true);
            }
            em.persist(category);
            em.flush();
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            if (category.getStatus() == null) {
                category.setStatus(true);
            }
            em.merge(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Override
    public void create(Category category) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> findByUserIds(List<Integer> userIds) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.user.id IN :ids";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("ids", userIds);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}