package it.uniroma2.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import it.uniroma2.dao.BookSearchDAO;
import it.uniroma2.domain.BookSearch;

/**
 * A class that implements the interface BookSearchDAO for the common CRUD operations for BookSearch.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository(value="BookSearchDAO")
public class BookSearchDaoImpl extends HibernateDaoSupport implements BookSearchDAO {

	 /**
     * Create a new BookSearchDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public BookSearchDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
     * Save a book into the database
     * @param bookSearch An instance of bookSearch
     */
	@Override
	public void save(BookSearch book) {
		getHibernateTemplate().save(book);
		
	}

	/**
     * Update a bookSearch into the database
     * @param bookSearch An instance of bookSearch
     */
	@Override
	public void update(BookSearch book) {
		getHibernateTemplate().update(book);
		
	}
	
	/**
     * Delete a specified bookSearch into the database
     * @param bookSearch An instance of book
     */
	@Override
	public void delete(BookSearch book) {
		getHibernateTemplate().delete(book);
		
	}

	 /**
     * @return Return a list of books from the database
     */
	@Override
	public List<BookSearch> listBook() {
		return getHibernateTemplate().find("from BookSearch");
	}
	
	
	/**
	* @param isbn ISBN of a bookSearch
    * @return Return a bookSearch from the database corresponding to ISBN
    */
	@Override
	public BookSearch findBookByISBN(Integer isbn) {
		List list = getHibernateTemplate().find("from BookSearch where bookId=?", isbn);
		return (BookSearch) list.get(0);
	}

	/**
	* @param title Title of a book
    * @return Return a book from the database corresponding to title
    */
	@Override
	public BookSearch findBookByTitle(String title) {
		List list = getHibernateTemplate().find("from BookSearch where title=?", title);
		return (BookSearch) list.get(0);
	}

}
