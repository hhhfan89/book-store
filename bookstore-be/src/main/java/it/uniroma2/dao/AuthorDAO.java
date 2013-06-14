package it.uniroma2.dao;

import it.uniroma2.domain.Author;

import java.util.List;

/**
 * The DAO for Author. An interface representing the common CRUD operations for Author.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface AuthorDAO {
	
	/**
     * Save an author into the database
     * @param author An instance of author
     */
	void save(Author author);
	
	/**
     * Update an author into the database
     * @param author An instance of author
     */
	void update(Author author);
	
	/**
     * Delete a specified author into the database
     * @param author An instance of author
     */
	void delete(Author author);
	
	 /**
     * @return Return a list of authors from the database
     */
	List<Author> listAuthor();
	
	 /**
	 * @param surname Surname of an author
     * @return Return an author from the database corresponding to the author's surname
     */
	Author findAuthorBySurname(String surname);
}
