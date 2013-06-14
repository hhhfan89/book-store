package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.RoleDAO;
import it.uniroma2.domain.Role;
import it.uniroma2.service.RoleService;

/**
 * The class that implements RoleService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDAO roleDao;

	public void save(Role role) {
		roleDao.save(role);		
	}

	public void update(Role role) {
		roleDao.update(role);		
	}

	public void delete(Role role) {
		roleDao.delete(role);		
	}

	public List<Role> listRole() {
		return roleDao.listRole();
	}

	public Role findRoleByName(String name) {
		return roleDao.findRoleByName(name);
	}

	public Role findRoleById(Integer id) {
		return roleDao.findRoleById(id);
	}

}
