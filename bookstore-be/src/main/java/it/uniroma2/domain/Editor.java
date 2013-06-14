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
 * A model of an editor.
 * Editor id, name, list of the book of the editor
 *  Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "editors", catalog = "book_store")
public class Editor implements Serializable {
	//The editor's id
	private int editorId;
	//The editor's name
	private String name;
	//The list of books that have been published by the editor
	private List<Book> listBook = new ArrayList<Book>();
	
	 /**
     * Define also the mapping for Hibernate for the whole Editor's table 
     * based on the association between class' id and table's id.
     *
     * @return Return the id of the editor
     */
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "editor_id", unique = true, nullable = false)
	public int getEditorId() {
		return editorId;
	}
    
    /**
     * @param editorId The Id of the editor
     */
	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}
	
	/**
     * @return Return the name of the editor
     */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	
	/**
     * @param name The name of the editor
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Define the mapping for Hibernate between Editor's table 
     * and Book's table in the database. ManyToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return The list of books that have been published by the editor
     */
	@ManyToMany(mappedBy="listEditor", targetEntity = Book.class)
	public List<Book> getListBook() {
		return listBook;
	}
	
	/**
     * @param listBook The list of books that have been published by the editor
     */
	public void setListBook(List<Book> listBook) {
		this.listBook = listBook;
	}
}
