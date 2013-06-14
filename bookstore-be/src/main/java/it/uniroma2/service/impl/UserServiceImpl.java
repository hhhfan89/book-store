package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.UserDAO;
import it.uniroma2.domain.User;
import it.uniroma2.service.UserService;

/**
 * The class that implements UserService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDao;

	public void save(User user) {
		userDao.save(user);
		
	}

	public void update(User user) {
		userDao.update(user);
		
	}

	public void delete(User user) {
		userDao.delete(user);
		
	}

	public List<User> listUser() {
		return userDao.listUser();
	}

	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	public User findUserBySurname(String surname) {
		return userDao.findUserBySurname(surname);
	}

	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}
	
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}

}
