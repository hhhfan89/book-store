package it.uniroma2.dao.impl;

import java.util.List;

import it.uniroma2.dao.RoleDAO;
import it.uniroma2.domain.Role;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * A class that implements the interface RoleDAO for the common CRUD operations for Role.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository("RoleDAOImpl")
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDAO {
	
	/**
     * Create a new RoleDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public RoleDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
     * Save a role into the database
     * @param role An instance of role
     */
	@Override
	public void save(Role role) {
		getHibernateTemplate().save(role);
		
	}
	
	/**
     * Update a role into the database
     * @param role An instance of role
     */
	@Override
	public void update(Role role) {
		getHibernateTemplate().update(role);
		
	}
	
	/**
     * Delete a role into the database
     * @param role An instance of role
     */
	@Override
	public void delete(Role role) {
		getHibernateTemplate().delete(role);
		
	}
	

   /**
    * @return Return a list of roles from the database
    */
	@Override
	public List<Role> listRole() {
		return getHibernateTemplate().find("from roles");
	}
	
	/**
	* @param name Title of a role
    * @return Return a role from the database corresponding to a identifier's title
    */
	@Override
	public Role findRoleByName(String name) {
		List list = getHibernateTemplate().find("from roles where name like ?", "%"+name+"%");
		return (Role) list.get(0);
	}
	
	/**
	* @param id Identifier of a role
    * @return Return a role from the database corresponding to a identifier's id
    */
	@Override
	public Role findRoleById(Integer id) {
		List list = getHibernateTemplate().find("from roles where role_id=?", id);
		return (Role) list.get(0);
	}
	
}
