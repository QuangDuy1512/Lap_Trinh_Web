package vn.iotstar.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ManagerUserDao;

public class ManagerUserDaoImpl implements ManagerUserDao {
    @Override
    public List<Integer> getUserIdsByManagerId(int managerId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            List<Integer> userIds = em.createNativeQuery(
                "SELECT user_id FROM manager_user WHERE manager_id = :managerId")
                .setParameter("managerId", managerId)
                .getResultList();
            // Convert to Integer list (if needed)
            return userIds.stream().map(id -> ((Number)id).intValue()).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }
}
