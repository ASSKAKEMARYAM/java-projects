package org.mql.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClassParser {
	
	private Class<?> targetClass;
	 private PackageExplorer pe =new PackageExplorer();
	private String [] T ;
	private String PackageName = "org.mql.java.annotations";
 
	public ClassParser() {
		T=pe.getClassList(null);
		
		try {
			this.targetClass = Class.forName("org.mql.java.models.Author");
		} catch (ClassNotFoundException e) {}
	}
	
	public ClassParser(Class<?> targetClass) {
		this.targetClass = targetClass;
	}
	
	public Class<?> getSuperClass(){
		return targetClass.getSuperclass();
	}
	//renvoie la hierarhie d'une classe
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getClassesParentes() {
			Vector cp = new Vector();

		    Class<?> sousClasse = targetClass;
		    Class<?> superClasse;

		    cp.add(sousClasse.getName());
		    superClasse = sousClasse.getSuperclass();
		    while (superClasse != null) {
		      cp.add(0,superClasse.getName());
		      sousClasse = superClasse;
		      superClasse = sousClasse.getSuperclass();
		    }
		    return cp;
		  }
	
	public Constructor<?>[] getConstructors(){
		return targetClass.getDeclaredConstructors();
	}
	
	public Field[] getFields() {
		return targetClass.getDeclaredFields();
	}
	
//	public Class<?>[] getIntefaces() {
//		return targetClass.getInterfaces();
//	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getInterfaces() {  
	    Vector cp = new Vector(); 
	     
	    Class[] interfaces = targetClass.getInterfaces(); 
	    for (int i = 0; i < interfaces.length; i++) { 
	      cp.add(interfaces[i].getName()); 
	    } 
	    return cp; 
	  }
	
	public Method[] getMethods() {
		return targetClass.getMethods();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSignatureMethodes() { 
	     
	    List cp = new ArrayList(); 
	    Method[] methodes = targetClass.getDeclaredMethods(); 
	    for (int i = 0; i < methodes.length; i++) { 
	      StringBuffer methode = new StringBuffer(); 
	                 
	      methode.append(formatParametre(methodes[i].getReturnType().getName())); 
	      methode.append(" "); 
	      methode.append(methodes[i].getName()); 
	      methode.append(rechercheParametres(methodes[i].getParameterTypes())); 
	       
	      cp.add(methode.toString()); 
	    } 
	    return cp; 
	  }

	public Class<?>[] getInnerClasses(){	
		return targetClass.getDeclaredClasses();
	}
	
	public Vector<Class<?>> getHeritageChaine(){
		Class<?> classTmp = targetClass;
		Vector<Class<?>> classes = new Vector<Class<?>>();
		while (classTmp != null) {
			classes.add(classTmp);
			classTmp = classTmp.getSuperclass();
		}
		return classes;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getConstructeurs() { 
	     
	    Vector cp = new Vector(); 
	    Constructor[] constructeurs = targetClass.getConstructors(); 
	    for (int i = 0; i < constructeurs.length; i++) { 
	      cp.add(rechercheParametres(constructeurs[i].getParameterTypes())); 
	    }
	             
	    return cp; 
	  }
	private String rechercheParametres(Class[] classes) { 
	    StringBuffer param = new StringBuffer("("); 
	                 
	    for (int i = 0; i < classes.length; i ++) { 
	      param.append(formatParametre(classes[i].getName())); 
	      if (i < classes.length - 1)  
	        param.append(", "); 
	    } 
	    param.append(")"); 
	     
	    return param.toString(); 
	  }

	private String formatParametre(String s) { 
		   
	    if (s.charAt(0) == '[') { 
	     
	      StringBuffer param = new StringBuffer(""); 
	      int dimension = 0; 
	      while (s.charAt(dimension) == '[') dimension++; 
	       
	      switch(s.charAt(dimension)) { 
	        case 'B' : param.append("byte");break; 
	        case 'C' : param.append("char");break; 
	        case 'D' : param.append("double");break; 
	        case 'F' : param.append("float");break; 
	        case 'I' : param.append("int");break; 
	        case 'J' : param.append("long");break; 
	        case 'S' : param.append("short");break; 
	        case 'Z' : param.append("boolean");break; 
	        case 'L' : param.append(s.substring(dimension+1,s.indexOf(";"))); 
	      } 
	       
	      for (int i =0; i < dimension; i++) 
	        param.append("[]"); 
	                 
	      return param.toString();  
	    }           
	    else return s; 
	                 
	  }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getChampsPublics() { 
	    Vector cp = new Vector(); 

	    Field[] champs = targetClass.getFields(); 
	    for (int i = 0; i < champs.length; i++) 
	      cp.add(champs[i].getType().getName()+" "+champs[i].getName()); 
	    return cp; 
	  }
	public void afficherGettersSetters() {
	    Method[] methods = targetClass.getMethods();
	    System.out.println("classe : " + targetClass.getName());
	    for (Method method : methods) {
	      if (isGetter(method)) {
	        System.out.println("  getter : " + method);
	      }
	      if (isSetter(method)) {
	        System.out.println("  setter : " + method);
	      }
	    }
	  }
	public static boolean isGetter(Method method) {
	    boolean result = method.getName().startsWith("get")
	            && (method.getParameterTypes().length == 0)
	            && (!Void.class.equals(method.getReturnType()));
	    return result;
	  }

	  public static boolean isSetter(Method method) {
	    boolean result = (method.getName().startsWith("set"))
	            && (method.getParameterTypes().length == 1);
	    return result;
	  }
}
