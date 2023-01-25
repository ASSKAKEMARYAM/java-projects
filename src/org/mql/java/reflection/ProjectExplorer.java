package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import org.mql.java.models.UMLClasse;
import org.mql.java.models.UMLPackage;
import org.mql.java.models.Project;
import org.mql.java.models.UMLRelation;
import org.mql.java.models.UMLType;
import org.mql.java.utils.UMLRelationship;

public class ProjectExplorer {

	public ProjectExplorer() {

	}

	public ProjectExplorer(String projectDirectory) {
		Project p = exploreProject(projectDirectory);
		System.out.println(p);
	}

	public Project exploreProject(String projectDirectory) {
		Project projectModel = new Project(projectDirectory);
		Set<String> packageList = new HashSet<>();
		listOfPackage(projectDirectory, packageList);
		Set<UMLPackage> listPackageModel = new HashSet<>();
		for (String pack : packageList) {
			Set<UMLRelation> relations = new HashSet<>();
			PackageExplorer packageExplorer = new PackageExplorer(projectDirectory);
			try {
				UMLPackage packageModel = new UMLPackage(projectDirectory, pack);
				Set<UMLClasse> classes = new HashSet<>();
				Set<String> classList = new HashSet<>();
				File f = new File(projectDirectory);
				URL[] cp = { f.toURI().toURL() };
				try (URLClassLoader urlcl = new URLClassLoader(cp)) {
					packageExplorer.getClassList(pack, classList);
					for (String cls : classList) {
						
						Class<?> c = null ;
						try {
							c= Class.forName(cls);
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						classes.add(new UMLClasse(cls));
						Class<?> myclass = urlcl.loadClass(cls);
						ClassesRelationship clsR = new ClassesRelationship(projectDirectory, myclass);

						Set<Parameter> utilisation = new HashSet<>();
						Set<Field> agregation = new HashSet<>();
						Set<Field> composition = new HashSet<>();

						Class<?> extention = clsR.getExensionRelation();
						Class<?>[] implementation = clsR.getImplementation();

						utilisation = clsR.getUtilisation();
						agregation = clsR.getAgregation();
						composition = clsR.getComposition();

						setUtilisationModel(utilisation, relations, c.getSimpleName());
						setAgregationModel(agregation, relations, c.getSimpleName());
						setCompositionModel(composition, relations, c.getSimpleName());
						setImplementationModel(implementation, relations, c.getSimpleName());
						if (extention != null) {
							UMLRelation relationModel = new UMLRelation();
							relationModel.setRelatedClass(c.getSimpleName());
							relationModel.setRelatedWith(extention.getSimpleName());
							relationModel.setRelation(UMLRelationship.Extention);
							relations.add(relationModel);
						}
					}
					packageModel.setRelation(relations);
					packageModel.setClasses(classes);
					packageExplorer.setPackageModel(pack, packageModel);
					listPackageModel.add(packageModel);
				}
			} catch (Exception E) {
				System.out.println("Class not found !!! " + E.getMessage());
			}
		}

		projectModel.setPackages(listPackageModel);
		return projectModel;

	}

	public void setUtilisationModel(Set<Parameter> utilisation, Set<UMLRelation> relations, String cls) {
		for (Parameter parametre : utilisation) {
			UMLType typeModel = new UMLType(parametre);
			UMLRelation relationModel = new UMLRelation();
			relationModel.setRelatedClass(cls);
			relationModel.setRelatedWith(typeModel.getElementType().getTypeName()+"");
			relationModel.setRelation(UMLRelationship.Utilisation);
			relations.add(relationModel);
		}
	}

	public void setAgregationModel(Set<Field> agregation, Set<UMLRelation> relations, String cls) {
		for (Field field : agregation) {
			UMLType typeModel = new UMLType(field);
			UMLRelation relationModel = new UMLRelation();
			relationModel.setRelatedClass(cls);
			relationModel.setRelatedWith(typeModel.getElementType().getTypeName()+"");
			relationModel.setRelation(UMLRelationship.Agregation);
			relations.add(relationModel);
		}
	}

	public void setCompositionModel(Set<Field> composition, Set<UMLRelation> relations, String cls) {
		for (Field field : composition) {
			UMLRelation relationModel = new UMLRelation();
			UMLType typeModel = new UMLType(field);
			relationModel.setRelatedClass(cls);
			relationModel.setRelatedWith(typeModel.getElementType().getTypeName()+"");
			relationModel.setRelation(UMLRelationship.Composition);
			relations.add(relationModel);
		}
	}
	

	public void setImplementationModel(Class<?>[] implementation, Set<UMLRelation> relations, String cls) {
		for (int i = 0; i < implementation.length; i++) {
			UMLRelation relationModel = new UMLRelation();
			relationModel.setRelatedClass(cls);
			relationModel.setRelatedWith(implementation[i].getSimpleName());
			relationModel.setRelation(UMLRelationship.Implementation);
			relations.add(relationModel);

		}
	}

	public void listOfPackage(String directoryName, Set<String> pack) {
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				String path = file.getPath();
				String packName = path.substring(path.indexOf("bin") + 4, path.lastIndexOf('\\'));
				pack.add(packName.replace('\\', '.'));
			} else if (file.isDirectory()) {

				listOfPackage(file.getAbsolutePath(), pack);
			}
		}
	}

	public static void main(String[] args) {
		new ProjectExplorer("C:\\Users\\MARYAM\\eclipse-workspace\\project_UML\\bin\\");
		
	}

}
