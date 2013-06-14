package it.uniroma2.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.UserDAO;

/**
 * The service to compare data added in the form
 * of login JSP with the credentials in the database to 
 * permit or not, trough Spring Security, access to the 
 * BookStore webapp
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;	

	final static Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
   
	/**
     * Make the matching between an instance of User and an instance of UserDetails
     * used by Spring Security to allow the login.
     * 
     * @param username The user's username put in the form of Login
     * @return An istance of UserDetails based on the data put in the form of login 
     */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.debug("loadUserByUsername(): username = {}", username);
		
		it.uniroma2.domain.User domainUser = userDAO.findUserByUsername(username);
		if(domainUser == null)
			throw new UsernameNotFoundException("User not found");

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		log.debug("loadUserByUsername(): username = {}, password {}, roleId = {}", domainUser.getUsername(), 
				domainUser.getPassword(), domainUser.getRole().getRoleId());
		return new User(
				domainUser.getUsername(), 
				domainUser.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked,
				getAuthorities(domainUser.getRole().getRoleId())
		);
	}
	
	/**
     * @param role The user's role, recover from the database
     * @return List of GrantedAuthority, that represents an authority granted
     * to an Authentication object
     */
	public Collection<? extends GrantedAuthority> getAuthorities(int role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		
		log.debug("getAuthorities(): authList = {}", authList); 
		return authList;
	}
	
	/**
     * @param role The user's role, recover from the database
     * @return List of roles
     */
	public List<String> getRoles(int role) {

		List<String> roles = new ArrayList<String>();
		if (role == 1) {  
			roles.add("ROLE_ADMIN");  
		} 
		else {
			roles.add("ROLE_USER");
		} 
		
		log.debug("getRoles(): roles = {}", roles);
		return roles; 
	}
	
	/**
     * @param roles List of user's roles
     * @return List of SimpleGrantedAuthority that stores a strin representation of
     * an authority granted
     */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		log.debug("getGrantedAuthorities(): authorities = {}", authorities);
		return authorities;
	}

}