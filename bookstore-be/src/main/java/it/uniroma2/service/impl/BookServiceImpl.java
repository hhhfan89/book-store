package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.BookDAO;
import it.uniroma2.domain.Book;
import it.uniroma2.service.BookService;

/**
 * The class that implements BookService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDAO bookDao;

	public void save(Book book) {
		bookDao.save(book);		
	}

	public void update(Book book) {
		bookDao.update(book);			
	}

	public void delete(Book book) {
		bookDao.delete(book);			
	}

	public List<Book> listBook() {
		return bookDao.listBook();
	}

	public Book findBookByISBN(int isbn) {
		return bookDao.findBookByISBN(isbn);
	}

	public Book findBookByTitle(String title) {
		return bookDao.findBookByTitle(title);
	}

}
