package org.mql.java.examples;

import javax.swing.JFrame;

import org.mql.java.controllers.EditController;
import org.mql.java.controllers.FileController;
import org.mql.java.controllers.InternalService;
import org.mql.java.reflection.PackageExplorer;
import org.mql.java.ui.Menu;

public class MenuGenerator extends JFrame {
	private static final long serialVersionUID = 1L;

	public MenuGenerator() {
		exp02();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	void exp00() {
		String labels[][] = {
			{"File", "New", "Open", "Save"},
			{"Edit", "Copy", "Cut", "Paste"},		
		};
		Menu menu = new Menu(labels);
		setJMenuBar(menu);
	}
	
	void exp01() {
		Menu menu = new Menu();
		setJMenuBar(menu);
		
		menu.add(new FileController());
		menu.add(new EditController());
		menu.add(new InternalService());
	}
	
	void exp02() {
		Menu menu = new Menu();
		setJMenuBar(menu);

		PackageExplorer explorer = new PackageExplorer();
		String classes[] = explorer.getClassList("org.mql.java.controllers");
		for (String className : classes) {
			System.out.println(className);
			try {
				Class<?> cls = Class.forName(className);
				menu.add(cls.getDeclaredConstructor().newInstance());
			}
			catch (Exception e) {
				System.out.println("Erreur : " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new MenuGenerator();
	}
}
