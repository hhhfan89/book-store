package it.uniroma2.controller;

import it.uniroma2.domain.Role;
import it.uniroma2.domain.User;
import it.uniroma2.service.UserService;

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
 * The controller to administrate the url and pages about User, for adding and deleting
 * istance of users from the database.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	final static Logger log =  LoggerFactory.getLogger(UserController.class);
	
	/**
     * Show the JSP page to add a new User into the database 
     * 
     * @return The string that identify the JSP page to save the user in the database
     */
	@RequestMapping(value="/home/showAddUserPage", method = RequestMethod.GET)
	public String showAddUserPage(){
		log.info("Page with form to insert an User");
		return "addUser";
	}
	
	/**
     *  Based on the data entered by the user in the specific text area, 
     *  picks up content from ModelAttribute, 
     *  check if the user is already present and otherwise inserts it into the database.
     *  
     * @return The model with a positive or negative message to show on the JSP showAddUser Page
     */
	@RequestMapping(value="/home/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("User") User user, BindingResult result){
		
		ModelAndView model = new ModelAndView("addUser");
		
		if(userService.findUserByUsername(user.getUsername()) != null) {
			log.info("addUser(): There is an user with the same username");
			model.addObject("msg", "Username is already in the database");
		}
		else {
				if(userService.findUserByEmail(user.getEmail()) != null) {
					log.info("addUser(): There is an user in the DB with the same email");	
					model.addObject("msg", "Email is already in the database");
				}
				else {
					log.info("addUser(): No user in the DB with the same credentials");
					Role role = new Role();
					//Set "1" (ROLE_USER) for the role of the user added 
					role.setRoleId(1);
					role.setTitle("ROLE_ADMIN");
					user.setRole(role);
					userService.save(user);
					log.debug("addUser(): user = {}", user);
					model.addObject("msg", "User added");
				}
			}
		
		log.debug("addUser(): model = {}", model);
		return model;
	}
	
	/**
     * Show the JSP page to delete an user from the database
     * The JSP show a list of users 
     * 
     * @param model A model to add the list of Users
     * @return The string that identify the JSP page to delete the user from the database
     */
	@RequestMapping(value="/home/showDeleteUserPage", method = RequestMethod.GET)
	public String showDeleteUserPage(Model model){
		model.addAttribute("userList", userService.listUser());
		log.debug("showDeleteUserPage(): userList = {}", userService.listUser());
		
		return "deleteUser";
	}
	
	/**
     *  
     *  Takes as input the id of the selected user from the web page, 
     *  it retrieves the user from the database and deletes it
     *  
     * @param user The user's id
     * @return The string for redirect the browser to the JSP page for removing user
     */
	@RequestMapping("/home/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") int userId) {

		User user = userService.findUserById(userId);
		userService.delete(user);
		log.debug("deleteUser(): user = {}", user);
		
		return "redirect:/home/showDeleteUserPage";

	}
}
