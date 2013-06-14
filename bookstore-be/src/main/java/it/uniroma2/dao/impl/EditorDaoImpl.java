package it.uniroma2.dao.impl;

import java.util.List;

import it.uniroma2.dao.EditorDAO;
import it.uniroma2.domain.Editor;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * A class that implements the interface EditorDAO for the common CRUD operations for Editor.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository("EditorDAOImpl")
public class EditorDaoImpl extends HibernateDaoSupport implements EditorDAO {
	
	/**
     * Create a new EditorDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public EditorDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
     * Save a editor into the database
     * @param editor An instance of editor
     */
	@Override
	public void save(Editor editor) {
		getHibernateTemplate().save(editor);
		
	}
	
	/**
     * Update a editor into the database
     * @param editor An instance of editor
     */
	@Override
	public void update(Editor editor) {
		getHibernateTemplate().update(editor);
		
	}
	
	/**
     * Delete a editor into the database
     * @param editor An instance of editor
     */
	@Override
	public void delete(Editor editor) {
		getHibernateTemplate().delete(editor);
		
	}
	
	 /**
     * @return Return a list of editors from the database
     */
	@Override
	public List<Editor> listEditor() {
		return getHibernateTemplate().find("from Editor");
	}
	
	/**
	* @param name Name of a editor
    * @return Return a editor from the database corresponding to a editor's name
    */
	@Override
	public Editor findEditorByName(String name) {
		List list = getHibernateTemplate().find("from Editor where name = ?", name);
		if (list.isEmpty()) {
			return null;
		}
		else {
			return (Editor) list.get(0);
		}
	}

}
