package it.uniroma2.dao;

import it.uniroma2.domain.Editor;

import java.util.List;


/**
 * The DAO for Editor. An interface representing the common CRUD operations for Editor.
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
public interface EditorDAO {
	
	/**
     * Save a editor into the database
     * @param editor An instance of editor
     */
	void save(Editor editor);
	
	/**
     * Update a editor into the database
     * @param editor An instance of editor
     */
	void update(Editor editor);
	
	/**
     * Delete a editor into the database
     * @param editor An instance of editor
     */
	void delete(Editor editor);
	
	 /**
     * @return Return a list of editors from the database
     */
	List<Editor> listEditor();
	
	/**
	* @param name Name of a editor
    * @return Return a editor from the database corresponding to a editor's name
    */
	Editor findEditorByName(String name);

}
