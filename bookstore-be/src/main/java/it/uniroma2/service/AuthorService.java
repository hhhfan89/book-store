package it.uniroma2.service;

import it.uniroma2.domain.Author;

import java.util.List;

/**
 * An interface representing shared characteristics of authors
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface AuthorService {

	/**
     * Save a author into the database
     * @param user An instance of author
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
