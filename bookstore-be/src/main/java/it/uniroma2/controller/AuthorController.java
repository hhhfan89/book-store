package it.uniroma2.controller;


import it.uniroma2.domain.Author;
import it.uniroma2.service.AuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The controller to administrate the urls and pages about Author, for adding and deleting
 * instance of authors from the database.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Controller
public class AuthorController {
	final static Logger log = LoggerFactory.getLogger(AuthorController.class);
	
	@Autowired
	AuthorService authorService;
	
	/**
     * Show the JSP page to add a new Author into the database 
     * 
     * @return The string that identify the JSP page to save the author in the database
     */
	@RequestMapping(value="/home/showAddAuthorPage", method = RequestMethod.GET)
	public String showAddAuthorPage(){
		log.info("Page with form to insert an Author");
		return "addAuthor";
	}
	
	/**
     *  Based on the data entered by the user in the specific text area, 
     *  picks up content from ModelAttribute, 
     *  check if the author is already present and otherwise inserts it into the database.
     *  
     * @return The model with a positive or negative message to show on the JSP showAddAuthor Page
     */
	@RequestMapping(value="/home/addAuthor", method = RequestMethod.POST)
	public ModelAndView addAuthor(@ModelAttribute("Author") Author author, BindingResult result){
		
		ModelAndView model = new ModelAndView("addAuthor");
		if(authorService.findAuthorBySurname(author.getSurname())!=null) {
			log.info("addAuthor(): There is an author in the DB with the same username");
			
			Author authorDB = authorService.findAuthorBySurname(author.getSurname());
			log.debug("addAuthor(): authorFromDatabase = {}", authorDB);
			
			if(authorDB.getName().compareTo(author.getName())==0){
				log.info("addAuthor(): Same author is in the DB");
				model.addObject("msg", "Author already added");
			}
			else{
				authorService.save(author);
				log.info("addAuthor(): No author in the DB with the same credentials");
				log.debug("addAuthor(): author = {}", author);
				
				model.addObject("msg", "Author added");
			}
		}
		else{
			authorService.save(author);
			log.info("addAuthor(): No author in the DB with the same credentials");
			log.debug("addAuthor(): author = {}", author);
			
			model.addObject("msg", "Author added");
			}
		
		log.debug("addAuthor(): model = {}", model);
		return model;
	
	}
	
	/**
     * Show the JSP page to delete an author from the database
     * The JSP show a list of authors 
     * 
     * @param model A model to add the list of Authors
     * @return The string that identify the JSP page to delete an author from the database
     */
	@RequestMapping(value="/home/showDeleteAuthorPage", method = RequestMethod.GET)
	public String showDeleteAuthorPage(Model model){
		model.addAttribute("authorList", authorService.listAuthor());
		log.debug("showDeleteAuthorPage(): authorList = {}", authorService.listAuthor());
		
		return "deleteAuthor";
	}
	
	
	/**
     *  Takes as input the id of the selected author 
     *  from the author's list in the webpage, 
     *  and deletes it from database
     *  
     * @param authorId The author's id
     * @param name The author's name
     * @param surname The author's surname
     * @return The string for redirect the browser to the JSP page for removing author
     */
	@RequestMapping("/home/deleteAuthor/{authorId}/{name}/{surname}")
	public String deleteAuthor(@PathVariable("authorId") int authorId,@PathVariable("name") String name,@PathVariable("surname") String surname) {

		Author author = new Author();
		author.setAuthorId(authorId);
		author.setName(name);
		author.setSurname(surname);
		authorService.delete(author);
		
		log.debug("deleteAuthor(): author = {}", author);
		return "redirect:/home/showDeleteAuthorPage";
		
	}
}
