package org.mql.java.reflection;

import java.lang.reflect.Field;

import org.mql.java.annotations.Form;
import org.mql.java.annotations.TextField;

public class FormAnnotationEngine {
	private org.mql.java.ui.Form form;

	public FormAnnotationEngine(Object target) {
		Form f = target.getClass().getDeclaredAnnotation(Form.class);
		if (f != null) {
			String title = f.value();
			if ("".equals(title)) {
				title = "New " + target.getClass().getSimpleName();
			}
			System.out.println("Title : " + title);
			form = new org.mql.java.ui.Form(title);
			Field fields[] = target.getClass().getDeclaredFields();
			for (Field field : fields) {
				TextField tf = field.getDeclaredAnnotation(TextField.class);
				if (tf != null) {
					String label = tf.label();
					if ("".equals(label)) label = field.getName();
					System.out.println(" - " + label + " : " + tf.size());
					form.add(label, tf.size());
				}
			}
		}
	}
	
	public org.mql.java.ui.Form getForm() {
		return form;
	}

}
