package it.uniroma2.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileOutputStream;

import it.uniroma2.domain.Author;
import it.uniroma2.domain.Book;
import it.uniroma2.domain.Category;
import it.uniroma2.domain.Editor;
import it.uniroma2.service.AuthorService;
import it.uniroma2.service.BookService;
import it.uniroma2.service.CategoryService;
import it.uniroma2.service.EditorService;
import it.uniroma2.service.impl.AuthorEditor;
import it.uniroma2.service.impl.CategoryEditor;
import it.uniroma2.service.impl.EditorEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The controller to administrate the urls and pages about Book, for adding and deleting
 * istance of books from the database.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	EditorService editorService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;

	final static Logger log = LoggerFactory.getLogger(BookController.class);
	final static String PATH = "/Users/Daniele/";
	/**
	 * Show the JSP page to add a new Book into the database 
	 * 
	 * @return The string that identify the JSP page to save the book in the database
	 */
	@RequestMapping(value="/home/showAddBookPage", method = RequestMethod.GET)
	public ModelAndView showAddBookPage(){
		log.info("Page with form to insert a Book");
		
		ModelAndView modelAndView = new ModelAndView("addBook");
		
		modelAndView.addObject("listEditor", editorService.listEditor());
		modelAndView.addObject("listAuthor", authorService.listAuthor());
		modelAndView.addObject("listCategory", categoryService.listCategory());
		
		Book book= new Book();
		modelAndView.addObject("book", book);

		log.debug("showAddBookPage(): modelAndView = {}", modelAndView);
		return modelAndView;
	}

	/**
	 *  Based on the data entered by the user in the specific text area, 
	 *  picks up content from ModelAttribute, 
	 *  check if the book is already present and otherwise, if the uploading of file  
	 *  been successful, inserts it into the database.
	 *  
	 * @return The model with a positive or negative message to show on the JSP showAddBook Page
	 */
	@RequestMapping(value="/home/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") Book book,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		Book bookFromDatabase = bookService.findBookByISBN(book.getBookId());
		log.debug("addBook(): bookFromDatabase = {}", bookFromDatabase);

		if(bookFromDatabase!=null) {
			log.info("addBook(): There is a book in the DB with the same ISBN");
			redirectAttributes.addFlashAttribute("msg", "Book already added");
		}
		else {	
			try{
				log.info("addBook(): Into the Try. No book in the DB with the same ISBN");
				CommonsMultipartFile imageFile = (CommonsMultipartFile) book.getImageFile();
				CommonsMultipartFile textFile = (CommonsMultipartFile) book.getTextFile();
				FileOutputStream outputStream = null;

				String filePathImage = PATH + imageFile.getOriginalFilename();
				String filePathText = PATH + textFile.getOriginalFilename();

				try {
					log.info("addBook(): Into the Try. Creation of file");
					outputStream = new FileOutputStream(new File(filePathImage));
					outputStream.write(imageFile.getFileItem().get());
					outputStream.close();
					outputStream = new FileOutputStream(new File(filePathText));
					outputStream.write(textFile.getFileItem().get());
					outputStream.close();

					book.setImage(filePathImage);
					book.setText(filePathText);
					bookService.save(book);
					log.debug("addBook(): book = {}", book);

					redirectAttributes.addFlashAttribute("msg", "Book added");
					
				} catch (FileNotFoundException e) {
					log.info("addBook(): Into the Catch. Uploading failure" + e.toString());
					redirectAttributes.addFlashAttribute("msg", "Problem with loading file");
					
				} catch (IOException e) {
					log.info("addBook(): Into the Catch. Uploading failure" + e.toString());
					redirectAttributes.addFlashAttribute("msg", "Problem with loading file");
				}
			}
			catch (NullPointerException e) {
				log.info("addBook(): Into the Catch" + e.toString());
				redirectAttributes.addFlashAttribute("msg", "Problem with loading file");
			}
		}

		return "redirect:showAddBookPage";
	}

	/**
	 * Show the JSP page to delete a book from the database
	 * The JSP show a list of books 
	 * 
	 * @param model A model to add the list of Books
	 * @return The string that identify the JSP page to delete a book from the database
	 */
	@RequestMapping(value="/home/showDeleteBookPage", method = RequestMethod.GET)
	public String showDeleteBookPage(Model model){
		model.addAttribute("bookList", bookService.listBook());
		log.debug("showDeleteBookPage(): bookList = {}", bookService.listBook());

		return "deleteBook";
	}

	/**
	 *  Takes as input the id of the selected book 
	 *  from the book's list in the webpage, 
	 *  and deletes it from database
	 *  
	 * @param bookId The book's id
	 * @return The string for redirect the browser to a specific JSP page
	 */
	@RequestMapping("/home/deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId) {

		Book book = bookService.findBookByISBN(bookId);
		bookService.delete(book);
		log.debug("deleteBook(): book = {}", book);

		return "redirect:home/showDeleteBookPage";
	}
	
	/**
     * Show the JSP page to update description and price of
     * a selected Book into the database 
     * 
     * @return The string that identify the JSP page to search a book into the database
     */
	@RequestMapping(value="/home/showUpdateBookPage/{bookId}", method = RequestMethod.GET)
	public ModelAndView showUpdateBookPage(@PathVariable("bookId") int bookId){
		
		ModelAndView model = new ModelAndView("updateBook");
		Book book = bookService.findBookByISBN(bookId);

		model.addObject("book", book);
		
		log.debug("showUpdateBookPage(): book = {}", book);
		
		return model;
	}
	
	/**
	 *  Update the selected book 
	 *  
	 * @param book An instance of Book
	 * @return The string for redirect the browser to a specific JSP page
	 */
	@RequestMapping(value="/home/showUpdateBookPage/updateBook", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute("book") Book book, BindingResult result) {
		
		bookService.update(book);
		log.debug("updateBook(): book = {}", book);

		return "redirect:/home/showDeleteBookPage";
	}
	
	/**
	 *  Default method to manage the selected options
	 *  of authors, editors and categories from the JSP
	 *  to allow SpringMVC and Hibernate to do the matching
	 *  between the value and the complex type that represent
	 *  authors, editors and categories
	 *  
	 * @param binder 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Author.class, new AuthorEditor(authorService));
		binder.registerCustomEditor(Editor.class, new EditorEditor(editorService));
	}
}