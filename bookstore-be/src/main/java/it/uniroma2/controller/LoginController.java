package it.uniroma2.controller;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The controller to administrate the urls and pages about login, to allow access to registered users.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Controller
public class LoginController {

	final static Logger log = LoggerFactory.getLogger(LoginController.class);
	/**
	 * Show the JSP homepage after the user logged on
	 * @param model A ModelMap that allow to pass information to the view
	 * @param principal A Principal that identifies the user who is logged 
	 * @return The string that identify the JSP page of the Home
	 */
	@RequestMapping(value={"/home", "/"}, method=RequestMethod.GET)
	public String showHome(ModelMap model,Principal principal) {
		log.debug("showHome(): model = {} principal = {}", new Object[]{model, principal});
		String username = principal.getName();
	    model.addAttribute("username", username);
	    log.debug("showHome(): model = {}", model);
	    
	    return "home";
	}
	
	/**
     * Show the JSP page to login as administrator
     * 
     * @return The string that identify the JSP page to login 
     */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {	
		log.info("Page to login as administrator");
		return "login";
	}
 
	/**
     * Show the JSP page if the login as administrator is failed
     * 
     * @return The string that identify the JSP page to login failed
     */
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		log.info("Page to login failed");
		return "login";
 
	}
 
	/**
     * Show the JSP page to login as administrator
     * 
     * @return The string that identify the JSP page to login as administrator
     */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		log.info("Page to login as administrator");
		return "login";
	}
	
}