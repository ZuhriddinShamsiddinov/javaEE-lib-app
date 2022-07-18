package com.example.libapp.dao;

import com.example.libapp.configs.HibernateConfigurer;
import com.example.libapp.domain.Book;
import com.example.libapp.domain.Uploads;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:28 AM 7/14/22 on Thursday in July
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadsDAO implements Dao {
    private static UploadsDAO instance;

    public void save(Uploads uploads) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.persist(uploads);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public void remove(Uploads uploads) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.remove(uploads);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public String findByGeneratedName(String generatedName) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Uploads> query = currentSession.createQuery("select t from Uploads t where t.generatedName =: generatedName", Uploads.class);
        Uploads uploads = query.setParameter("generatedName", generatedName).getSingleResultOrNull();
        currentSession.getTransaction().commit();
        currentSession.close();
        return uploads.getOriginalName();
    }


    public static UploadsDAO getInstance() {
        if (instance == null) {
            instance = new UploadsDAO();
        }
        return instance;
    }
}
