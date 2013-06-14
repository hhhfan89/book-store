package it.uniroma2.service;

import it.uniroma2.domain.Book;

import java.util.List;

/**
 * An interface representing shared characteristics of books
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface BookService {
	
	/**
     * Save a book into the database
     * @param book An instance of book
     */
	void save(Book book);
	
	/**
     * Update a book into the database
     * @param book An instance of book
     */
	void update(Book book);
	
	/**
     * Delete a specified book into the database
     * @param book An instance of book
     */
	void delete(Book book);
	
	 /**
     * @return Return a list of books from the database
     */
	List<Book> listBook();
	
	/**
	* @param isbn ISBN of a book
    * @return Return a book from the database corresponding to ISBN
    */
	Book findBookByISBN(int isbn);
	
	/**
	* @param title Title of a book
    * @return Return a book from the database corresponding to title
    */
	Book findBookByTitle(String title);
	

}
