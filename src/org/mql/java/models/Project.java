package org.mql.java.models;

import java.util.Set;

public class Project {
	
	private String nameProject ; 
	private Set<UMLPackage> packages ;

	public Project(String name) {
		this.nameProject= name;
	}

	public String getNameProject() {
		return nameProject;
	}

	public Set<UMLPackage> getPackages() {
		return packages;
	}

	public void setPackages(Set<UMLPackage> packages) {
		this.packages = packages;
	}
	
	@Override
	public String toString() {
		return "ProjectModel [name=" + nameProject + ", packages=" + packages + "]";
	}
	

}
