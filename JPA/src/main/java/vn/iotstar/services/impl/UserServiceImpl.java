package vn.iotstar.services.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {
    private EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User login(String username, String password) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                User.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insert(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
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
            User user = findById(id);
            if (user != null) {
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public List<User> findManagedUsers(User manager) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT DISTINCT u FROM User u, ManagerUser mu " +
            "WHERE mu.managerId = :managerId AND mu.userId = u.id",
            User.class
        );
        query.setParameter("managerId", manager.getId());
        return query.getResultList();
    }

    @Override
    public boolean canManageUser(User manager, User user) {
        if (manager.getRoleid() == 3) { // Admin can manage all
            return true;
        }
        if (manager.getRoleid() != 2) { // Only managers can manage users
            return false;
        }
        
        // Check if there's a manager-user relationship
        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(mu) FROM ManagerUser mu WHERE mu.managerId = :managerId AND mu.userId = :userId",
            Long.class
        );
        query.setParameter("managerId", manager.getId());
        query.setParameter("userId", user.getId());
        return query.getSingleResult() > 0;
    }
}