package org.mql.java.examples;

import java.lang.reflect.Field;

import org.mql.java.models.Author;
import org.mql.java.reflection.FormAnnotationEngine;

public class Examples {

	public Examples() {
		exp02();
	}
	
	void exp01() {
		Author a1 = new Author(101, "Erich Gamma", 1961, "Suisse");
		Class<?> c1 = a1.getClass();
		
		Field fields[] = c1.getDeclaredFields();
		for (Field f : fields) {
			System.out.print(f.getName() + " : ");
			f.setAccessible(true);
			try {System.out.println(f.get(a1));}catch (Exception e) {}
			f.setAccessible(false);
		}
		
		Class<?> c2 = Author.class;
		
		String className = "org.mql.java.models.Author";
		try {
			Class<?> c3 = Class.forName(className);
		}
		catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	void exp02() {
		new FormAnnotationEngine(new Author());
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
