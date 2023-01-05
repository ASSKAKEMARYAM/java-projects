package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class ProjectExplorer {

	public ProjectExplorer() {
		exploreProject("C:\\Users\\MARYAM\\eclipse-workspace\\p02-Generics (1)\\p02-Generics\\bin\\");
	}

	public static void exploreProject(String projectDirectory) {
		Set<String> packageList = new HashSet<>();
		getPackageList(projectDirectory, packageList);

		for (String packg : packageList) {
			Set<String> classList = new HashSet<String>();
			getClassList(projectDirectory, packg, classList);

			for (String clas : classList) {
				loadClass(projectDirectory, packg, clas);
			}
		}
		System.out.println("la tache 1");
	}

	public static void getPackageList(String repoName, Set<String> packageList) {
		File directory = new File(repoName);
		File[] filesList = directory.listFiles();//retournent tous les fichiers (sans les répertoires) de ce répertoire.

		for (File files : filesList) {
			if (files.isFile()) {
				String pathFile = files.getPath();
				String packages = pathFile.substring(pathFile.indexOf("bin") + 4, pathFile.lastIndexOf('\\'));
				packageList.add(packages.replace('\\', '.'));
			} else if (files.isDirectory()) {
				getPackageList(files.getAbsolutePath(), packageList);
			}
			
				
			}
//		for(String p :packageList) {
//			System.out.println("--->"+ p);
//		}
	}

	public static void getClassList(String projectDirectory, String packageName, Set<String> classList) {
		File directory = new File(projectDirectory + packageName.replace(".", "/"));
		File classFiles[] = directory.listFiles();

		for (File cls : classFiles) {
			if (cls.isFile() && cls.getName().endsWith(".class")) {
				classList.add(packageName + "." + cls.getName().replace(".class", ""));
			}
		}
	}

	private static void loadClass(String projectDirectory, String packageName, String className) {
		try {
			File file = new File(projectDirectory);
			URL[] cp = { file.toURI().toURL() };//convert to uri 
			try (URLClassLoader urlClassLoader = new URLClassLoader(cp)) {
				Class<?> myclass = urlClassLoader.loadClass(className);
				System.out.println(myclass.getCanonicalName());
				ClassParser parser = new ClassParser(myclass);
				System.out.println("nombre de methods  : " + parser.getMethods().length);
			}
		} catch (Exception E) {
			System.out.println("Class not found");
		}
	}
	
	
//	public Set<Class> findAllClassesUsingClassLoader(String packageName) {
//        InputStream stream = ClassLoader.getSystemClassLoader()
//                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//        return reader.lines()
//                .filter(line -> line.endsWith(".class"))
//                .map(line -> getClass(line, packageName))
//                .collect(Collectors.toSet());
//    }


	public static void main(String[] args) {
		new ProjectExplorer();
	}
}
