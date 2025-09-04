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
	        return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	    }

	    @Override
	    public Category findById(int id) {
	        EntityManager em = JPAConfig.getEntityManager();
	        return em.find(Category.class, id);
	    }

	    @Override
	    public void insert(Category cate) {
	        EntityManager em = JPAConfig.getEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.persist(cate);
	            trans.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            trans.rollback();
	        }
	    }

	    @Override
	    public void update(Category cate) {
	        EntityManager em = JPAConfig.getEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.merge(cate);
	            trans.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            trans.rollback();
	        }
	    }

	    @Override
	    public void delete(int id) {
	        EntityManager em = JPAConfig.getEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            Category cate = em.find(Category.class, id);
	            if (cate != null) {
	                em.remove(cate);
	            }
	            trans.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            trans.rollback();
	        }
	    }

		@Override
		public void create(Category category) {
			// TODO Auto-generated method stub
			
		}
	
}
