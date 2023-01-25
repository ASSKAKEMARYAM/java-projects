package org.mql.java.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class UMLClasse {
	
	private String name ;
	private Set<UMLFields> fields ;
	private Set<UMLMethodes> methods;
	private String simpleName;
	private boolean isInterface ;

	public UMLClasse(String name) {
		Class<?> classe = null ;
		this.name=name;
		try {
			classe= Class.forName(name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(classe.isInterface()) isInterface=true; else isInterface=false; 
		simpleName=classe.getSimpleName();
		Field [] declaredFields = classe.getDeclaredFields();
		Method[] declaredMethods =classe.getDeclaredMethods();
		fields = new HashSet<UMLFields>();
		methods = new HashSet<UMLMethodes>();
		for(Field field : declaredFields) {
			fields.add(new UMLFields(field));
		}
		for (Method method : declaredMethods) {
			methods.add(new UMLMethodes(method));
		}
	}

	public String getSimpleName() {
		return simpleName;
	}


	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}


	public String getName() {
		return name;
	}

	

	public void setName(String name) {
		this.name = name;
	}



	public Set<UMLFields> getFields() {
		return fields;
	}



	public void setFields(Set<UMLFields> fields) {
		this.fields = fields;
	}



	public Set<UMLMethodes> getMethods() {
		return methods;
	}



	public void setMethods(Set<UMLMethodes> methods) {
		this.methods = methods;
	}
	
	public boolean isInterface() {
		return isInterface;
	}


	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	@Override
	public String toString() {
		return "UMLClasse [name=" + name + ", fields=" + fields + ", methods=" + methods + ", simpleName=" + simpleName+"]";
	}



	
	
	

}
