package com.gruntik.resttest.service;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Store store) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(store);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Store> findAll() {
        Transaction tx = null;
        List<Store> stores = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            CriteriaQuery<Store> criteriaQuery = session.getCriteriaBuilder().createQuery(Store.class);
            criteriaQuery.from(Store.class);

            stores.addAll(session.createQuery(criteriaQuery).getResultList());

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        return stores;
    }

    public boolean existsByName(String name) {
        Transaction tx = null;
        boolean returnValue = false;

        try (Session session = sessionFactory.openSession();) {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Store> criteriaQuery = cb.createQuery(Store.class);
            Root<Store> root = criteriaQuery.from(Store.class);

            criteriaQuery.where(cb.equal(root.get("name"), name));

            List<Store> resultList = session.createQuery(criteriaQuery).getResultList();
            if (resultList.size() > 0) {
                returnValue = true;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return returnValue;
    }

    public int deleteByName(String name) {
        Transaction tx = null;
        int result = 0;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Store where name = :name");
            query.setParameter("name", name);
            result = query.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public void deleteAll() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Store");
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

}
