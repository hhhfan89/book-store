package it.uniroma2.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import it.uniroma2.dao.BookDAO;
import it.uniroma2.domain.Book;

/**
 * A class that implements the interface BookDAO for the common CRUD operations for Book.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository("BookDAOImpl")
public class BookDaoImpl extends HibernateDaoSupport implements BookDAO {

	 /**
     * Create a new BookDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public BookDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
     * Save a book into the database
     * @param book An instance of book
     */
	@Override
	public void save(Book book) {
		getHibernateTemplate().save(book);
	}


	/**
     * Update a book into the database
     * @param book An instance of book
     */
	@Override
	public void update(Book book) {
		getHibernateTemplate().update(book);
		
	}
	
	
	/**
     * Delete a specified book into the database
     * @param book An instance of book
     */
	@Override
	public void delete(Book book) {
		getHibernateTemplate().delete(book);
		
	}

	 /**
     * @return Return a list of books from the database
     */
	@Override
	public List<Book> listBook() {
		return getHibernateTemplate().find("from Book");
	}

	
	/**
	* @param isbn ISBN of a book
    * @return Return a book from the database corresponding to ISBN
    */
	@Override
	public Book findBookByISBN(int isbn) {
		List list = getHibernateTemplate().find("from Book where bookId=?", isbn);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return (Book) list.get(0);
		}
	}


	/**
	* @param title Title of a book
    * @return Return a book from the database corresponding to title
    */
	@Override
	public Book findBookByTitle(String title) {
		List list = getHibernateTemplate().find("from Book where title=?",title);
		return (Book) list.get(0);
	}

}
