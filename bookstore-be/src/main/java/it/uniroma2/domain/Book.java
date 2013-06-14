package it.uniroma2.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 * A model of a book.
 * Books id, price, numpages, title, description, image, text.
 *  Entity and Table annotation define the mapping with
 * the database for Hibernate
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Entity
@Table(name = "books", catalog = "book_store")
public class Book implements Serializable {
	
	final static Logger log = LoggerFactory.getLogger(Book.class);
	
	//The book's id
	private int bookId;
	//The book's price
	private float price;
	//The book's number of pages
	private int numpages;
	//The book's title
	private String title;
	//The book's description
	private String description;
	//The image's url of a book
	private String image;
	//The url to whole or part of a book
	private String text;
	//The book's edition
	private int edition;
	//The book's text file uploaded 
    private MultipartFile textFile = null;
    //The book's image file uploaded
    private MultipartFile imageFile = null;
	//List of the authors who have written the book
	private List<Author> listAuthor = new ArrayList<Author>();
	//List of the categories of the book
	private List<Category> listCategory = new ArrayList<Category>();
	//List of the editors of the book
	private List<Editor> listEditor = new ArrayList<Editor>();
   

	

    /**
     * Define also the mapping for Hibernate for the whole Book's table 
     * based on the association between class' id and table's id.
     *
     * @return Return the id of the book
     */
	@Id
	@Column(name = "book_id", unique = true, nullable = false)
	public int getBookId() {
		return bookId;
	}
	

    /**
     * @param bookId The Id of the book
     */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


    /**
     * @return Return the price of the book
     */
	@Column(name = "price", nullable = false)
	public float getPrice() {
		return price;
	}
	
	/**
     * @param price Price of the book
     */
	public void setPrice(float price) {
		this.price = price;
	}
	

    /**
     * @return Return the title of the book
     */
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}
	
	/**
     * @param title Title of the book
     */
	public void setTitle(String title) {
		this.title = title;
	}


    /**
     * @return Return the description of the book
     */
	@Column(name = "description", nullable = true) 
	public String getDescription() {
		return description;
	}
	
	/**
     * @param description Description of the book
     */
	public void setDescription(String description) {
		this.description = description;
	}
	

    /**
     * @return Return the number of pages of the book
     */
	@Column(name = "numpages", nullable = false)
	public int getNumpages() {
		return numpages;
	}
	
	/**
     * @param numPages Number of pages of the book
     */
	public void setNumpages(int numpages) {
		this.numpages = numpages;
	}
	

    /**
     * @return Return the image's url of the book
     */
	@Column(name = "image", nullable = false)
	public String getImage() {
		return image;
	}
	
	/**
     * @param image Image's url of the book
     */
	public void setImage(String image) {
		this.image = image;
	}
	
    /**
     * @return Return the text's url of the book
     */
	@Column(name = "text", nullable = false)
	public String getText() {
		return text;
	}

	/**
     * @param text Text's url of the book
     */
	public void setText(String text) {
		this.text = text;
	}

    /**
     * @return Return the edition of the book
     */
	@Column(name = "edition", nullable = false)
	public int getEdition() {
		return edition;
	}

	/**
     * @param edition Edition of the book
     */
	public void setEdition(int edition) {
		this.edition = edition;
	}
	
	/**
     * @return Return the image multipart file 
     */
	@Transient
	public MultipartFile getImageFile() {
		return imageFile;
	}


	/**
     * @param imageFile The multipart file to upload the image
     */
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	/**
     * @return Return the text multipart file 
     */
	@Transient
	public MultipartFile getTextFile() {
		return textFile;
	}

	/**
     * @param textFile The multipart file to upload the text
     */
	public void setTextFile(MultipartFile textFile) {
		this.textFile = textFile;
	}
	

	/**
     * Define the mapping for Hibernate between Book's table 
     * and Author's table in the database. ManyToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return the list of the authors who have written the book
     */
	@ManyToMany
    @JoinTable(name="boau01",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
	public List<Author> getListAuthor() {
		return listAuthor;
	}
	
	/**
     * @param listAuthor List of the authors who have written the book
     */
	public void setListAuthor(List<Author> listAuthor) {
		this.listAuthor = listAuthor;
	}
	
	/**
     * Define the mapping for Hibernate between Book's table 
     * and Category's table in the database. ManyToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return the list of categories of book
     */
	@ManyToMany
    @JoinTable(name="boca01",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
	public List<Category> getListCategory() {
		return listCategory;
	}
	
	/**
     * @param listCategory List of the categories of the book
     */
	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}
	
	/**
     * Define the mapping for Hibernate between Book's table 
     * and Editor's table in the database. ManyToMany define the 
     * cardinality for this reletionship
     * 
     * @return Return the list of editors of book
     */
	@ManyToMany
    @JoinTable(name="boed01",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "editor_id")
    )
	public List<Editor> getListEditor() {
		log.debug("getListEditor(): {}", listEditor);
		return listEditor;
	}
	
	/**
     * @param listEditor List of the editors of the book
     */
	public void setListEditor(List<Editor> listEditor) {
		log.debug("setListEditor(): listEditor={}", listEditor);
		this.listEditor = listEditor;
	}
}
