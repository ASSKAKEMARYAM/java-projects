package org.mql.java.controllers;

import org.mql.java.annotations.Action;
import org.mql.java.annotations.Controller;

@Controller("File")
public class FileController {

	public FileController() {
	}
	
	@Action(value="New", icon="new.gif")
	public String newFile() {
		System.out.println(">> New File");
		return "new-file-ok.jsp";
	}

	@Action(value="Open", icon="open.gif")
	public String open() {
		System.out.println(">> Open File");
		return "open-file-ok.jsp";
	}
	
	@Action(value="Save", icon="save.gif")
	public String save() {
		System.out.println(">> Save File");
		return "save-file-ok.jsp";
	}
	
	@Action("Save As ...")
	public String saveAs() {
		System.out.println(">> Save As a new File");
		return "save-as-file-ok.jsp";
	}
	
	@Action("Exit")
	public String exit() {
		System.out.println(">> Close the application");
		return "exit.jsp";
	}

}
