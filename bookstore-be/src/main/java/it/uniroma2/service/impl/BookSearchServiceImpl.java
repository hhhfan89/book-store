package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.BookSearchDAO;
import it.uniroma2.domain.BookSearch;
import it.uniroma2.service.BookSearchService;

/**
 * The class that implements BookSearchService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("bookSearchService")
@Transactional
public class BookSearchServiceImpl implements BookSearchService {
	
	@Autowired
	BookSearchDAO bookSearchDao;

	public void save(BookSearch book) {
		bookSearchDao.save(book);
		
	}

	public void update(BookSearch book) {
		bookSearchDao.update(book);
		
	}

	public void delete(BookSearch book) {
		bookSearchDao.delete(book);
		
	}

	public List<BookSearch> listBook() {
		return bookSearchDao.listBook();
	}

	public BookSearch findBookByISBN(Integer isbn) {
		return bookSearchDao.findBookByISBN(isbn);
	}

	public BookSearch findBookByTitle(String title) {
		return bookSearchDao.findBookByTitle(title);
	}


}
