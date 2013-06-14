package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.AuthorDAO;
import it.uniroma2.domain.Author;
import it.uniroma2.service.AuthorService;

/**
 * The class that implements AuthorService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("authorService")
@Transactional
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	AuthorDAO authorDao;

	public void save(Author author) {
		authorDao.save(author);
		
	}

	public void update(Author author) {
		authorDao.update(author);
		
	}

	public void delete(Author author) {
		authorDao.delete(author);
		
	}

	public List<Author> listAuthor() {
		return authorDao.listAuthor();
	}

	public Author findAuthorBySurname(String surname) {
		return authorDao.findAuthorBySurname(surname);
	}

}
