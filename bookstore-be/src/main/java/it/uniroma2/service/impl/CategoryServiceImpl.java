package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.CategoryDAO;
import it.uniroma2.domain.Category;
import it.uniroma2.service.CategoryService;

/**
 * The class that implements CategoryService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDao;

	public void save(Category category) {
		categoryDao.save(category);
		
	}

	public void update(Category category) {
		categoryDao.update(category);
		
	}

	public void delete(Category category) {
		categoryDao.delete(category);
		
	}

	public List<Category> listCategory() {
		return categoryDao.listCategory();
	}

	public Category findCategoryByName(String name) {
		return categoryDao.findCategoryByName(name);
	}

}
