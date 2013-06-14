package it.uniroma2.service.impl;	

import it.uniroma2.domain.Editor;
import it.uniroma2.service.EditorService;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A support class to build property editor
 * for matching the string "name" representing
 * editor returned from the addBook JSP page and an
 * instance of Editor
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public class EditorEditor extends PropertyEditorSupport {

	final static Logger log = LoggerFactory.getLogger(EditorEditor.class);
	private EditorService editorService;

	/**
     * Create a new EditorEditor. A EditorEditor must be created with
     * the editorService 
     * 
     * @param editorService The service of the model Editor.
     */
	public EditorEditor(EditorService editorService) {
		log.info("Constructor of EditorEditor");
		this.editorService=editorService;
	}

	/**
     * Sets the property value by parsing the string representing
     * editor's name
     * @param name The editor's name returned from the addBook JSP page
     */
	@Override
	public void setAsText(String name) throws IllegalArgumentException {
		log.debug("setAsText(): name = {}", name);
		super.setValue(editorService.findEditorByName(name));
	}

	/**
     * Gets the property value "Editor" as a string suitable 
     * for presentation to a human to edit
     * @return The editor's id
     */
	@Override
	public String getAsText() {
		Editor editor = (Editor) this.getValue();
		log.debug("getAsText(): editorId = {}", editor.getEditorId());
		return Integer.toString(editor.getEditorId());
	}
}


