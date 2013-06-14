package it.uniroma2.service;

import it.uniroma2.domain.Category;

import java.util.List;

/**
 * An interface representing shared characteristics of categories
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface CategoryService {
	
	/**
     * Save a category into the database
     * @param category An instance of category
     */
	void save(Category category);
	
	/**
     * Update a category into the database
     * @param category An instance of category
     */
	void update(Category category);
	
	/**
     * Delete a category into the database
     * @param category An instance of category
     */
	void delete(Category category);
	
	 /**
     * @return Return a list of categories from the database
     */
	List<Category> listCategory();
	
	/**
	* @param name Name of a category
    * @return Return a category from the database corresponding to a category's name
    */
	Category findCategoryByName(String name);

}
