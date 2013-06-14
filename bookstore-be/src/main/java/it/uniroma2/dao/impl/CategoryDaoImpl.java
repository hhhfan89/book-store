package it.uniroma2.dao.impl;

import java.util.List;

import it.uniroma2.dao.CategoryDAO;
import it.uniroma2.domain.Category;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * A class that implements the interface CategoryDAO for the common CRUD operations for Category.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository("CategoryDAOImpl")
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDAO {
	
	 /**
     * Create a new CategoryDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public CategoryDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
     * Save a category into the database
     * @param category An instance of category
     */
	@Override
	public void save(Category category) {
		getHibernateTemplate().save(category);		
	}
	
	/**
     * Update a category into the database
     * @param category An instance of category
     */
	@Override
	public void update(Category category) {
		getHibernateTemplate().update(category);
		
	}

	/**
     * Delete a category into the database
     * @param category An instance of category
     */
	@Override
	public void delete(Category category) {
		getHibernateTemplate().delete(category);
		
	}
	
	 /**
     * @return Return a list of categories from the database
     */
	@Override
	public List<Category> listCategory() {
		return getHibernateTemplate().find("from Category");
	}
	
	/**
	* @param name Name of a category
    * @return Return a category from the database corresponding to a category's name
    */
	@Override
	public Category findCategoryByName(String name) {
		List list = getHibernateTemplate().find("from Category c where c.name=?",name);
		if (list.isEmpty()) {
			return null;
		}
		else {
			return (Category) list.get(0);
		}
	}

}
