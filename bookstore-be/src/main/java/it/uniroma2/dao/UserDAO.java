package it.uniroma2.dao;

import it.uniroma2.domain.User;

import java.util.List;

/**
 * The DAO for User. An interface representing the common CRUD operations for User.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface UserDAO {
	
	
	/**
     * Save a user into the database
     * @param user An instance of user
     */
	void save(User user);
	
	/**
     * Update a user into the database
     * @param user An instance of user
     */
	void update(User user);
	
	/**
     * Delete a user into the database
     * @param user An instance of user
     */
	void delete(User user);
	
	 /**
     * @return Return a list of users from the database
     */
	List<User> listUser();
	
	/**
	* @param name Name of a user
    * @return Return a user from the database corresponding to a user's name
    */
	User findUserByName(String name);
	
	/**
	* @param surname Surname of a user
    * @return Return a user from the database corresponding to a user's surname
    */
	User findUserBySurname(String surname);
	
	/**
	* @param username Username of a user
    * @return Return a user from the database corresponding to a user's username
    */
	User findUserByUsername(String username);
	
	/**
	* @param email Email of a user
    * @return Return a user from the database corresponding to a user's email
    */
	User findUserByEmail(String email);
	
	/**
	* @param id Identifier's of a user
    * @return Return a user from the database corresponding to a identifier's id
    */
	User findUserById(int id);

}
