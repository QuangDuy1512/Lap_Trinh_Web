package vn.iotstar.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.configs.JPAConfig;

public class DataSeeder {
    public static void seedManagerUser() {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Kiểm tra nếu chưa có dữ liệu thì insert mẫu
            long count = (long) em.createQuery("SELECT COUNT(mu) FROM ManagerUser mu").getSingleResult();
            if (count == 0) {
                // Ví dụ: manager 2 quản lý user 1,2; manager 3 quản lý user 3,4
                em.persist(new ManagerUser(2, 1));
                em.persist(new ManagerUser(2, 2));
                em.persist(new ManagerUser(3, 3));
                em.persist(new ManagerUser(3, 4));
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
