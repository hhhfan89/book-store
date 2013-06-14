package it.uniroma2.controller;

import it.uniroma2.domain.Category;
import it.uniroma2.service.CategoryService;

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
 * The controller to administrate the urls and pages about Category, for adding and deleting
 * istance of categories from the database.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	final static Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	/**
     * Show the JSP page to add a new Category into the database 
     * 
     * @return The string that identify the JSP page to save the category in the database
     */
	@RequestMapping(value="/home/showAddCategoryPage", method = RequestMethod.GET)
	public String showAddCategoryPage(){
		log.info("Page with form to insert Category");
		return "addCategory";
	}
	
	/**
     *  Based on the data entered by the user in the specific text area, 
     *  picks up content from ModelAttribute, 
     *  check if the category is already present and otherwise inserts it into the database.
     *  
     * @return The model with a message to show on the JSP's showAddCategoryPage
     */
	@RequestMapping(value="/home/addCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("Category") Category category, BindingResult result){
		ModelAndView model = new ModelAndView("addCategory");
		if(categoryService.findCategoryByName(category.getName())!=null){
			log.info("addCategory(): A category in the DB with the same name");
				model.addObject("msg", "Category is already in the database");
			}
		else{
			categoryService.save(category);
			log.info("addCategory(): No category in the DB with the same credentials");
			log.debug("addCategory(): category = {}", category);
			model.addObject("msg", "Category added");}
		
		return model;
	
	}
	
	/**
     * Show the JSP page to delete a category from the database
     * The JSP show a list of categories 
     * 
     * @param model A model to add the list of Categories
     * @return The string that identify the JSP page to delete the category from the database
     */
	@RequestMapping(value="/home/showDeleteCategoryPage", method = RequestMethod.GET)
	public String showDeleteCategoryPage(Model model){
		model.addAttribute("categoryList", categoryService.listCategory());
		log.debug("showDeleteCategoryPage(): categoryList = {}",categoryService.listCategory());
		return "deleteCategory";
	}
	
	/**
     *  Takes as input the id of the selected author 
     *  from the author's list in the webpage, 
     *  and deletes it from database
     *  
     * @param name The category's name
     * @return The string for redirect the browser to a specific JSP page
     */
	@RequestMapping("/home/deleteCategory/{name}")
	public String deleteCategory(@PathVariable("name") String name) {

		Category category = categoryService.findCategoryByName(name);
		categoryService.delete(category);
		log.debug("deleteCategory(): category = {}", category);
		return "redirect:/home/showDeleteCategoryPage";
		
	}
	
}
