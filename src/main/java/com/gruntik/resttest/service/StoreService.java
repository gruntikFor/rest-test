package com.gruntik.resttest.service;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void save(Store store) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(store);
            tx.commit();
        } catch (
                HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Store> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Store> stores = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            CriteriaQuery<Store> criteriaQuery = session.getCriteriaBuilder().createQuery(Store.class);
            criteriaQuery.from(Store.class);

            stores.addAll(session.createQuery(criteriaQuery).getResultList());

            tx.commit();
        } catch (
                HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return stores;
    }

    public boolean existsByName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        Transaction tx = null;
        boolean returnValue = false;

        try {
            tx = session.beginTransaction();

            CriteriaQuery<Store> criteriaQuery = cb.createQuery(Store.class);
            Root<Store> root = criteriaQuery.from(Store.class);
            criteriaQuery.where(cb.equal(root.get("name"), name));

            List<Store> resultList = session.createQuery(criteriaQuery).getResultList();
            if (resultList.size() > 0) {
                returnValue = true;
            }

            tx.commit();
        } catch (
                HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return returnValue;
    }

    public void deleteByName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        Transaction tx = null;
        boolean returnValue = false;

        try {
            tx = session.beginTransaction();

            Store load = session.load(Store.class, name);
            System.out.println(load);

            Query query = session.createQuery("delete Store where name = :name");
            query.setParameter("name", name);
            query.executeUpdate();
            tx.commit();
        } catch (
                HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
