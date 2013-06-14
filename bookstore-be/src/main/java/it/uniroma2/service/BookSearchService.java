package it.uniroma2.service;

import it.uniroma2.domain.BookSearch;

import java.util.List;

/**
 * An interface representing shared characteristics of books
 * added in the search's table
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface BookSearchService {
	
	/**
     * Save a book into the database
     * @param book An instance of book
     */
	void save(BookSearch book);
	
	/**
     * Update a book into the database
     * @param book An instance of book
     */
	void update(BookSearch book);
	
	/**
     * Delete a specified book into the database
     * @param book An instance of book
     */
	void delete(BookSearch book);
	
	 /**
     * @return Return a list of books from the database
     */
	List<BookSearch> listBook();
	
	/**
	* @param isbn ISBN of a book
    * @return Return a book from the database corresponding to ISBN
    */
	BookSearch findBookByISBN(Integer isbn);
	
	/**
	* @param title Title of a book
    * @return Return a book from the database corresponding to title
    */
	BookSearch findBookByTitle(String title);


}
