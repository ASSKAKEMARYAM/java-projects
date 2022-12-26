package org.mql.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.mql.java.annotations.Action;
import org.mql.java.annotations.Controller;

public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private String iconsFolder = "resources/icons/";

	public Menu() {
	}

	public Menu(String[][] labels) {
		for (int i = 0; i < labels.length; i++) {
			add(labels[i]);	
		}
	}
	
	public void add(String t[]) {
		JMenu m = new JMenu(t[0]);
		add(m);
		for (int i = 1; i < t.length; i++) {
			JMenuItem item = new JMenuItem(t[i]);
			m.add(item);
		}
	}

	public void add(Object controller) {
		Controller ca = controller.getClass().getDeclaredAnnotation(Controller.class);
		if (ca != null) {
			JMenu menu = new JMenu(ca.value());
			add(menu);
			
			Method m[] = controller.getClass().getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				Action aa = m[i].getDeclaredAnnotation(Action.class);
				if (aa != null) {
					String name = aa.value();
					if (name.equals("")) {
						name = m[i].getName();
					}
					ImageIcon icon = new ImageIcon(iconsFolder + aa.icon());
					JMenuItem item = new JMenuItem(name, icon);
					menu.add(item);
					item.addActionListener(new ItemListener(controller, m[i]));
				}
			}
		}
	}
	
	public class ItemListener implements ActionListener {
		private Object controller;
		private Method method;

		public ItemListener(Object controller, Method method) {
			super();
			this.controller = controller;
			this.method = method;
		}

		public void actionPerformed(ActionEvent event) {
			try {
				String result = (String)method.invoke(controller);
				System.out.println(">>> result : " + result);
			}
			catch (Exception e) {
				System.out.println("Erreur : " + e.getMessage());
			}
		}		
	}

}
