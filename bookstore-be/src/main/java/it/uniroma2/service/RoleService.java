package it.uniroma2.service;

import it.uniroma2.domain.Role;

import java.util.List;

/**
 * An interface representing shared characteristics of roles
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface RoleService {
	
	/**
     * Save a role into the database
     * @param role An instance of role
     */
	void save(Role role);
	
	/**
     * Update a role into the database
     * @param role An instance of role
     */
	void update(Role role);
	
	/**
     * Delete a role into the database
     * @param role An instance of role
     */
	void delete(Role role);
	
	 /**
     * @return Return a list of roles from the database
     */
	List<Role> listRole();
	
	/**
	* @param name Title of a role
    * @return Return a role from the database corresponding to a identifier's title
    */
	Role findRoleByName(String name);
	
	/**
	* @param id Identifier of a role
    * @return Return a role from the database corresponding to a identifier's id
    */
	Role findRoleById(Integer id);

}
