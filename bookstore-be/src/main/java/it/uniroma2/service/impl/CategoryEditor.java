package it.uniroma2.service.impl;

import it.uniroma2.domain.Category;
import it.uniroma2.service.CategoryService;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A support class to build property editor
 * for matching the string "name" representing
 * category returned from the addBook JSP page and an
 * instance of Category
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public class CategoryEditor extends PropertyEditorSupport {

	final static Logger log = LoggerFactory.getLogger(CategoryEditor.class);
	private CategoryService categoryService;

	/**
     * Create a new CategoryEditor. A CategoryEditor must be created with
     * the categoryService 
     * 
     * @param categoryService The service of the model Category.
     */
	public CategoryEditor(CategoryService categoryService){
		log.info("Constructor of CategoryEditor");
		this.categoryService=categoryService;
	}

	/**
     * Sets the property value by parsing the string representing
     * category's name
     * @param surname The category's name returned from the addBook JSP page
     */
	@Override
	public void setAsText(String name) throws IllegalArgumentException {
		log.debug("setAsText(): name = {}", name);
		super.setValue(categoryService.findCategoryByName(name));
	}

	/**
     * Gets the property value "Category" as a string suitable 
     * for presentation to a human to edit
     * @return The category's id
     */
	@Override
	public String getAsText() {
		Category category = (Category) this.getValue();
		log.debug("getAsText(): categoryId = {}", category.getCategoryId());
		return Integer.toString(category.getCategoryId());
	}
}
