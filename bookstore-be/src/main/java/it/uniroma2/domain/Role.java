package it.uniroma2.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A model of a role.
 * Role id, title/label, list of users associated
 * Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "roles", catalog = "book_store")
public class Role {
	
	//The Role's id
	private int roleId;
	//The name/title of a role
	private String title;
    //The list of users associated
	private List<User> users = new ArrayList<User>();
	
    /**
     * Define also the mapping for Hibernate for the whole Role's table 
     * based on the association between class' id and table's id.
     * IDENTITY define that the id is autoincrement
     * 
     * @return Return the id of a role
     */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public int getRoleId() {
		return roleId;
	}
	
    /**
     * @param roleId The id of the role
     */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
    /**
     * @return Return the name/title/label of a role
     */
	public String getTitle() {
		return title;
	}
	
	/**
     * @param name The title/label of a editor
     */
	public void setTitle(String title) {
		this.title = title;
	}
	
    /**
     * Define the mapping for Hibernate between Role's table 
     * and User's table in the database. OneToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return the list of users associated
     */
	@OneToMany(mappedBy="role", cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}
	
    /**
     * @param users The list of users associated with a role
     */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
