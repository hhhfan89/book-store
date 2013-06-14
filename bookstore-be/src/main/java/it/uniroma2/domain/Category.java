package it.uniroma2.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * A model of an author.
 * Category id, name list of Books associated
 * Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "categories", catalog = "book_store")
public class Category implements Serializable{
	
	//The Category's id
	private int categoryId;
	//The name of a category
	private String name;
    //The list of books associated
	private List<Book> listBook = new ArrayList<Book>();
	
    /**
     * Define also the mapping for Hibernate for the whole Category's table 
     * based on the association between class' id and table's id.
     * IDENTITY define that the id is autoincrement
     * 
     * @return Return the id of a category
     */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	public int getCategoryId() {
		return categoryId;
	}
	
    /**
     * @param categoryId The id of the category
     */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
    /**
     * @return Return the name of a category
     */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	/**
     * @param name The name of a category
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Define the mapping for Hibernate between Category's table 
     * and Book's table in the database. ManyToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return the list of books associated
     */
	@ManyToMany(mappedBy="listCategory", targetEntity = Book.class)
	public List<Book> getListBook() {
		return listBook;
	}
	
    /**
     * @param listBook The list of Books associated with a category
     */
	public void setListBook(List<Book> listBook) {
		this.listBook = listBook;
	}

}
