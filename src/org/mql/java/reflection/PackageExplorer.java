package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import org.mql.java.models.UMLClasse;
import org.mql.java.models.UMLPackage;

public class PackageExplorer {

	private String projectDirectory;

	public PackageExplorer(String projectDirectory) {
		this.projectDirectory = projectDirectory;
	}

	public void getClassList(String packageName, Set<String> classList) {
		File directory = new File(projectDirectory + packageName.replace(".", "/"));
		File classFiles[] = directory.listFiles();
		for (File cls : classFiles) {
			if (cls.isFile() && cls.getName().endsWith(".class")) {
				classList.add(packageName + "." + cls.getName().replace(".class", ""));
			}
		}
	}

	public void setPackageModel(String packageName, UMLPackage packageModel) {
		try {
			packageModel = new UMLPackage(projectDirectory, packageName);
			Set<UMLClasse> classes = new HashSet<>();
			Set<String> classList = new HashSet<>();
			File f = new File(projectDirectory);
			URL[] cp = { f.toURI().toURL() };
			try (URLClassLoader urlcl = new URLClassLoader(cp)) {
				getClassList(packageName, classList);
				for (String cls : classList) {
					classes.add(new UMLClasse(cls));
				}
				packageModel.setClasses(classes);
			}
		} catch (Exception E) {
			System.out.println("Class not found !!! ");
		}
	}

//	public static void main(String[] args) {
//		new PackageExplorer("C:\\Users\\MARYAM\\eclipse-workspace\\project_UML\\bin\\");
//		
//	}

}
