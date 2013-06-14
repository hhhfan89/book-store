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
 * Author id, name, surname and list of Books written
 * Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "authors", catalog = "book_store")
public class Author implements Serializable{
    
	//The Author's id
	private int authorId;
	//The name of the author
    private String name;
    //The surname of the author
    private String surname;
    //The list of books written by the author
    private List<Book> listBook = new ArrayList<Book>();
	
    /**
     * Define also the mapping for Hibernate for the whole Author's table 
     * based on the association between class' id and table's id.
     * IDENTITY define that the id is autoincrement
     * 
     * @return Return the id of an author
     */
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "author_id", unique = true, nullable = false)
    public int getAuthorId() {
		return authorId;
	}
    
    /**
     * @param authorId The id of the author
     */
    public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
    /**
     * @return Return the name of an author
     */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
    /**
     * @param name The name of the author
     */
	public void setName (String name){
		this.name = name;
	}
	
    /**
     * @return Return the surname of an author
     */
	@Column(name = "surname", nullable = false)
	public String getSurname() {
		return surname;
	}
    
	/**
     * @param surname The surname of the author
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
    /**
     * Define the mapping for Hibernate between Author's table 
     * and Book's table in the database. ManyToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return the list of books written by a given author
     */
	@ManyToMany(mappedBy="listAuthor", targetEntity = Book.class)
	public List<Book> getListBook() {
		return listBook;
	}
	
    /**
     * @param listBook The list of Books writter by the given author
     */
	public void setListBook(List<Book> listBook) {
		this.listBook = listBook;
	}
}
