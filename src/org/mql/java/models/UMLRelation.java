package org.mql.java.models;


import org.mql.java.utils.UMLRelationship;

public class UMLRelation {
	private String relatedClass;
	private String relatedWith; 
	private UMLRelationship relation ;
	
	
	public UMLRelation() {
	}

	public String getRelatedClass() {
		return relatedClass;
	}

	public void setRelatedClass(String relatedClass) {
		this.relatedClass = relatedClass;
	}

	public String getRelatedWith() {
		return relatedWith;
	}

	public void setRelatedWith(String relatedWith) {
		this.relatedWith = relatedWith;
	}

	public UMLRelationship getRelation() {
		return relation;
	}

	public void setRelation(UMLRelationship relation) {
		this.relation = relation;
	}

	@Override
	public String toString() {
		return "RelationModel [relatedClass=" + relatedClass + ", relatedWith=" + relatedWith + ", relation=" + relation
				+ "]";
	}
	
	
	

}
