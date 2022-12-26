package org.mql.java.controllers;

import org.mql.java.annotations.Action;
import org.mql.java.annotations.Controller;

@Controller("Edit")
public class EditController {

	public EditController() {
	}

	@Action(value="Copy", icon = "copy.gif")
	public String copy() {
		System.out.println("Copy");
		return "copy.jsp";
	}

	@Action(value="Cut", icon = "cut.gif")
	public String cut() {
		System.out.println("Cut");
		return "cut.jsp";
	}

	@Action(value="Paste", icon = "paste.gif")
	public String paste() {
		System.out.println("Paste");
		return "paste.jsp";
	}
	
	public void service() {
		
	}
}
