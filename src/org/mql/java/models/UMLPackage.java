package org.mql.java.models;

import java.util.Set;

public class UMLPackage {
	
	private String projectName ;
	private String packageName ;
	private Set<UMLClasse> classes ;
	private Set<UMLRelation> relation ;

	public UMLPackage(String projecName,String packageName ) {
		this.projectName=projecName;
		this.packageName=packageName;
	}

	public String getProjectName() {
		return projectName;
	}

	public Set<UMLRelation> getRelation() {
		return relation;
	}

	public void setRelation(Set<UMLRelation> relation) {
		this.relation = relation;
	}

	public String getPackageName() {
		return packageName;
	}

	public Set<UMLClasse> getClasses() {
		return classes;
	}
	
	public void setClasses(Set<UMLClasse> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "UMLPackage [projectName=" + projectName + ", packageName=" + packageName + ", classes=" + classes
				+ ", relation=" + relation + "] \n";
	}
}
