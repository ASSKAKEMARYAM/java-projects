package org.mql.java.models;

import org.mql.java.annotations.Form;
import org.mql.java.annotations.TextField;

@Form
public class Document {
	@TextField(size = 10)
	private int id;
	public Document() {
		// TODO Auto-generated constructor stub
	}

}
