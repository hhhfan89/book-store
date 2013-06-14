package it.uniroma2.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A model of an user.
 * User id, name, surname, password, email, role
 *  Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "users", catalog = "book_store")
public class User implements Serializable {
	//The user's id
	private int userId;
	//The user's name
	private String name;
	//The user'surname
	private String surname;
	//The user's username
	private String username;
	//The user's password
	private String password;
	//The user's email
	private String email;
	//The user's role
	private Role role = new Role();
	
	 /**
     * Define also the mapping for Hibernate for the whole User's table 
     * based on the association between class' id and table's id.
     * IDENTITY define that the id is autoincrement
     * 
     * @return Return the id of the user
     */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}
	
	 /**
     * @param userId The Id of the user
     */
	public void setUserId(int userId) {
		this.userId= userId;
	}

	/**
     * @return Return the name of the user
     */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	/**
     * @param name The name of the user
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * @return Return the surname of the user
     */
	@Column(name = "surname", nullable = false)
	public String getSurname() {
		return surname;
	}
	
	/**
     * @param surname The surname of the user
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
     * @return Return the username of the user
     */
	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}
	
	/**
     * @param username The username of the user
     */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
     * @return Return the password of the user
     */
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	
	/**
     * @param password The password of the user
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
     * @return Return the email of the user
     */
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	
	/**
     * @param email The email of the user
     */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
     * Define the mapping for Hibernate between User's table 
     * and Role's table in the database. ManyToOne define the 
     * cardinality for this reletionship
     * 
     * @return Return The role of the user.
     */
	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}
	
	/**
     * @param role The role of the user
     */
	public void setRole(Role role) {
		this.role = role;
	}

}
