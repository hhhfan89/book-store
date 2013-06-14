package it.uniroma2.dao.impl;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import it.uniroma2.dao.UserDAO;
import it.uniroma2.domain.User;

/**
 * A class that implements the interface UserDAO for the common CRUD operations for User.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Repository("UserDAOImpl")
public class UserDaoImpl extends HibernateDaoSupport implements UserDAO {


    /**
     * Create a new UserDaoImpl. 
     * 
     * @param sessionFactory A SessionFactory allows us to create or open a session to connect to the database.
     */
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	
	/**
     * Save a user into the database
     * @param user An instance of user
     */
	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
		
	}
	
	/**
     * Update a user into the database
     * @param user An instance of user
     */
	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);
		
	}
	
	/**
     * Delete a user into the database
     * @param user An instance of user
     */
	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);
		
	}
	
	 /**
     * @return Return a list of users from the database
     */
	@Override
	public List<User> listUser() {
		return getHibernateTemplate().find("from User");
	}
	
	/**
	* @param name Name of a user
    * @return Return a user from the database corresponding to a user's name
    */
	@Override
	public User findUserByName(String name) {
		List list = getHibernateTemplate().find("from User where name= ?", name);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return (User) list.get(0);
		}
	}
	
	/**
	* @param surname Surname of a user
    * @return Return a user from the database corresponding to a user's surname
    */
	@Override
	public User findUserBySurname(String surname) {
		List list = getHibernateTemplate().find("from User where surname= ?", surname);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return (User) list.get(0);
		}
	}
	
	/**
	* @param username Username of a user
    * @return Return a user from the database corresponding to a user's username
    */
	@Override
	public User findUserByUsername(String username) {
		List list = getHibernateTemplate().find("from User where username= ?", username);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return (User) list.get(0);
		}
	}
	
	/**
	* @param email Email of a user
    * @return Return a user from the database corresponding to a user's email
    */
	@Override
	public User findUserByEmail(String email) {
		List list = getHibernateTemplate().find("from User where email= ?", email);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return (User) list.get(0);
		}
	}
	
	/**
	* @param id Identifier's of a user
    * @return Return a user from the database corresponding to a identifier's id
    */
	public User findUserById(int id) {
		List list = getHibernateTemplate().find("from User where id= ?", id);
		if(list.isEmpty()) {
			return null;
		}
		else {
			return (User) list.get(0);
		}
	}

}
