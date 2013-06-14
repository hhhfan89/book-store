package it.uniroma2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma2.dao.EditorDAO;
import it.uniroma2.domain.Editor;
import it.uniroma2.service.EditorService;

/**
 * The class that implements EditorService
 * 
 * @author Daniele Pasquini, Silvia Naro, Mary Angeni Uminga, Stefano Di Vito
 * @version 2013.05.30
 */
@Service("editorService")
@Transactional
public class EditorServiceImpl implements EditorService{
	
	@Autowired
	EditorDAO editorDao;

	public void save(Editor editor) {
		editorDao.save(editor);
		
	}

	public void update(Editor editor) {
		editorDao.update(editor);		
	}

	public void delete(Editor editor) {
		editorDao.delete(editor);		
	}

	public List<Editor> listEditor() {
		return editorDao.listEditor();
	}

	public Editor findEditorByName(String name) {
		return editorDao.findEditorByName(name);
	}

}
