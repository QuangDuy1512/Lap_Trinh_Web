package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static EntityManagerFactory factory;
    
    static {
        try {
            factory = Persistence.createEntityManagerFactory("dataSource");
        } catch (Exception e) {
            System.err.println("Initial EntityManagerFactory creation failed: " + e);
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
        try {
            EntityManager entityManager = factory.createEntityManager();
            // Kiểm tra kết nối bằng cách thực hiện một truy vấn đơn giản
            entityManager.createQuery("SELECT 1").getSingleResult();
            return entityManager;
        } catch (Exception e) {
            System.err.println("Error creating EntityManager: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void closeEntityManagerFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}