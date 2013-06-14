package it.uniroma2.controller;

import it.uniroma2.domain.Editor;
import it.uniroma2.service.EditorService;

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
 * The controller to administrate the url and pages about Editor, for adding and deleting
 * istance of editors from the database.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Controller
public class EditorController {
	
	@Autowired
	EditorService editorService;
	
	final static Logger log =  LoggerFactory.getLogger(EditorController.class);
	
	/**
     * Show the JSP page to add a new Editor into the database 
     * 
     * @return The string that identify the JSP page to save the editor in the database
     */
	@RequestMapping(value="/home/showAddEditorPage", method = RequestMethod.GET)
	public String showAddEditorPage(){
		log.info("Page with form to insert an Editor");
		return "addEditor";
	}
	
	/**
     *  Based on the data entered by the user in the specific text area, 
     *  picks up content from ModelAttribute, 
     *  check if the editor is already added and otherwise inserts it into the database.
     *  
     * @return The model with a positive or negative message to show on the JSP showAddAuthor Page
     */
	@RequestMapping(value="/home/addEditor", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("Editor") Editor editor, BindingResult result){
		ModelAndView model = new ModelAndView("addEditor");
		if(editorService.findEditorByName(editor.getName())!=null){
			log.info("addEditor(): There is an editor with the same credentials");
			model.addObject("msg", "Editor already added");
		}
		else {
			editorService.save(editor);
			log.debug("addEditor(): editor = {}", editor);
			
			model.addObject("msg", "Editor added");}
		
		log.debug("addEditor(): model = {}", model);
		return model;
	
	}
	
	/**
     * Show the JSP page to delete an editor from the database
     * The JSP show a list of editors 
     * 
     * @param model A model to add the list of Editors
     * @return The string that identify the JSP page to delete an editor from the database
     */
	@RequestMapping(value="/home/showDeleteEditorPage", method = RequestMethod.GET)
	public String showDeleteCategoryPage(Model model){
	
		model.addAttribute("editorList", editorService.listEditor());
		log.debug("showDeleteEditorPage(): editorList = {}", editorService.listEditor());

		return "deleteEditor";
	}
	
	/**
     *  Takes as input the id of the selected editor 
     *  from the editors' list in the webpage, 
     *  and deletes it from database
     *  
     * @param authorId The author's id
     * @param name The author's name
     * @param surname The author's surname
     * @return The string for redirect the browser to the JSP page for removing author
     */
	@RequestMapping("/home/deleteEditor/{name}")
	public String deleteEditor(@PathVariable("name") String name) {

		Editor editor = editorService.findEditorByName(name);
		editorService.delete(editor);
		log.debug("deleteEditor(): editor = {}", editor);
		
		return "redirect:/home/showDeleteEditorPage";
		
	}
	
	
}
