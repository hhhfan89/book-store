package it.uniroma2.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import it.uniroma2.dao.AuthorDAO;
import it.uniroma2.domain.Author;

/**
 * A class that implements the interface AuthorDAO for the common CRUD operations for Author.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository("authorDao")
public class AuthorDaoImpl extends HibernateDaoSupport implements AuthorDAO {

    /**
     * Create a new AuthorDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public AuthorDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
     * Save an author into the database
     * @param author An instance of author
     */
	@Override
	public void save(Author author) {
		getHibernateTemplate().save(author);
	}
	
	/**
     * Update an author into the database
     * @param author An instance of author
     */
	@Override
	public void update(Author author) {
		getHibernateTemplate().update(author);
		}

	/**
     * Delete a specified author into the database
     * @param author An instance of author
     */
	@Override
	public void delete(Author author) {
		getHibernateTemplate().delete(author);
		
	}
	
	/**
     * @return Return a list of authors from the database
     */
	@Override
	public List<Author> listAuthor() {
		return getHibernateTemplate().find("from Author");
	}

	
   /**
	 * @param surname Surname of an author
	 * @return Return an author from the database corresponding to the author's surname
	 */
	@Override
	public Author findAuthorBySurname(String surname) {
		List list = getHibernateTemplate().find("from Author a where a.surname=?", surname);
		if (list.isEmpty()) {
			return null;
		}
		else {
			return (Author) list.get(0);
		}
	}

}
