package it.uniroma2.service.impl;

import it.uniroma2.domain.Author;
import it.uniroma2.service.AuthorService;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A support class to build property editor
 * for matching the string "surname" representing
 * author returned from the addBook JSP page and an
 * instance of Author
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public class AuthorEditor extends PropertyEditorSupport {
	
	final static Logger log = LoggerFactory.getLogger(AuthorEditor.class);
	private AuthorService authorService;

    /**
     * Create a new AuthorEditor. An AuthorEditor must be created with
     * the authorService 
     * 
     * @param authorService The service of the model Author.
     */
	public AuthorEditor(AuthorService authorService){
		log.info("Constructor of AuthorEditor");
		this.authorService=authorService;
	}

	/**
     * Sets the property value by parsing the string representing
     * author's surname
     * @param surname The author's surname returned from the addBook JSP page
     */
	@Override
	public void setAsText(String surname) throws IllegalArgumentException {
		log.debug("setAsText(): surname = {}", surname);
		super.setValue(authorService.findAuthorBySurname(surname));
	}

	/**
     * Gets the property value "Author" as a string suitable 
     * for presentation to a human to edit
     * @return The author's id
     */
	@Override
	public String getAsText() {
		Author author = (Author) this.getValue();
		log.debug("getAsText(): authorId = {}", author.getAuthorId());
		return Integer.toString(author.getAuthorId());
	}
}

