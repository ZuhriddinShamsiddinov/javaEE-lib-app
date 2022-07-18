package com.example.libapp.dao;

import com.example.libapp.configs.HibernateConfigurer;
import com.example.libapp.domain.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:40 PM 7/13/22 on Wednesday in July
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDAO implements Dao {

    private static BookDAO instance;

    public static BookDAO getInstance() {
        if (instance == null) {
            instance = new BookDAO();
        }
        return instance;
    }

    public Book findByBookName(String bookName) {

        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t where t.name =: name", Book.class);
        Book book = query.setParameter("name", bookName).getSingleResultOrNull();
        currentSession.getTransaction().commit();
        currentSession.close();
        return book;
    }

    public void save(Book book) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.saveOrUpdate(book);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public void remove(Book book) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.remove(book);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public List<Book> findAllStatusPinned() {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        List<Book> bookList = currentSession.createQuery("select t from Book t where t.status='PINNED'", Book.class).list();
        currentSession.getTransaction().commit();
        currentSession.close();
        return bookList;
    }

    public List<Book> findAll() {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        List<Book> bookList = currentSession.createQuery("select t from Book t ", Book.class).list();
        currentSession.getTransaction().commit();
        currentSession.close();
        return bookList;
    }


    public List<Book> viewAllBooks(int i, int recordsPerPage) {
        SessionFactory sessionFactory = HibernateConfigurer.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        Query<Book> query = currentSession.createQuery("select t from Book t ", Book.class);
        query.setFirstResult(i);
        query.setMaxResults(recordsPerPage);
        List<Book> books = query.getResultList();
        currentSession.getTransaction().commit();
        currentSession.close();
        return books;
    }
}
