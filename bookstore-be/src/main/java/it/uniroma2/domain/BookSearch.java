package it.uniroma2.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A model of a bookSearch.
 * Books id, price, numpages, title, description, image
 * Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "book_search", catalog = "book_store")
public class BookSearch {
	
	final static Logger log = LoggerFactory.getLogger(BookSearch.class);
	//The book's id
	private int book_id;
	//The book's title
	private String title;
	//The book's description
	private String description;
	
	
	 /**
     * Define also the mapping for Hibernate for the whole BookSearch's table 
     * based on the association between class' id and table's id.
     *
     * @return Return the id of the book
     */
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id", unique = true, nullable = false)
	public int getBook_id() {
		log.debug("getBook_Id(): {}", book_id);
		return book_id;
	}
	

    /**
     * @param bookId The Id of the book
     */
	public void setBook_id(int book_id) {
		log.debug("setBook_Id(): book_id={}", book_id);
		this.book_id = book_id;
	}
	

    /**
     * @return Return the title of the book
     */
	@Column(name = "title", nullable = false)
	public String getTitle() {
		log.debug("getTitle(): {}", title);
		return title;
	}
	
	/**
     * @param title Title of the book
     */
	public void setTitle(String title) {
		log.debug("setTitle(): title={}", title);
		this.title = title;
	}
	
	/**
     * @return Return the description of the book
     */
	@Column(name = "description", nullable = true)
	public String getDescription() {
		log.debug("getDescription(): {}", description);
		return description;
	}
	
	/**
     * @param description Description of the book
     */
	public void setDescription(String description) {
		log.debug("setDescription(): description={}", description);
		this.description = description;
	}

}
