package org.mql.java.models;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class UMLMethodes {
	
	private String name;
	private String modifier ;
	private UMLReturn returnType ;
	private UMLType [] parameterInfo; 
	
	public UMLMethodes(Method methode) {
		name = methode.getName();
		modifier = Modifier.toString(methode.getModifiers());
		returnType = new UMLReturn(methode);
		parameterInfo = new UMLType[methode.getParameterCount()];
	    for(int i = 0; i < methode.getParameterCount(); i++) {
	    	parameterInfo[i] = new UMLType(methode.getParameters()[i]);
	    }
	}

	@Override
	public String toString() {
		return "MethodeModel [name=" + name + ", modifier=" + modifier + ", returnType=" + returnType
				+ ", parameterInfo=" + Arrays.toString(parameterInfo) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public UMLReturn getReturnType() {
		return returnType;
	}

	public void setReturnType(UMLReturn returnType) {
		this.returnType = returnType;
	}

	public UMLType[] getParameterInfo() {
		return parameterInfo;
	}

	public void setParameterInfo(UMLType[] parameterInfo) {
		this.parameterInfo = parameterInfo;
	}

	
}
