package org.mql.java.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.mql.java.reflection.ClassParser;

public class ClassParserFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
		
	private ClassParser parser = new ClassParser();
	
	private JLabel label = new JLabel("Entrer le nom de la classe  : ");
	private JButton inputButton = new JButton("Demmarer le parsing");
	private JTextField textField = new JTextField("org.mql.java.models.Document");
	

	private JLabel superClassName = new JLabel("");
	private JLabel totalConstractors = new JLabel("");
	private JLabel totalProperties = new JLabel("");
	private JLabel totalInterfaces = new JLabel("");
	private JLabel totalMethods = new JLabel("");
	private JLabel totalInnerClasses = new JLabel("");
	private JLabel heritageChaineDeclaration = new JLabel("");
	private JLabel test = new JLabel("");
	private JLabel champsPublics = new JLabel("");
	private JLabel  hierarchie  = new JLabel("");
	private JLabel  settersAndGetters  = new JLabel("");
	
	private JLabel[] heritageChaine;
	
	private static int heritageChaineSize = 0;
	
	
	public ClassParserFrame() {
		super("Class Parser");
		
		textField.setBounds(200,50, 300,30);
		label.setBounds(200, 10, 300,50);
		inputButton.setBounds(200,100, 300,30);
		
		superClassName.setBounds(200,150,300,30);
		hierarchie.setBounds(200,175,900,30);
		totalConstractors.setBounds(200,200,300,30);
		totalProperties.setBounds(200,225,300,30);
		totalInterfaces.setBounds(200,250,300,30);
		totalMethods.setBounds(200,250,275,30);
		totalInnerClasses.setBounds(200,300,300,30);
		test.setBounds(200,325,300,30);
		heritageChaineDeclaration.setBounds(200, 350, 300, 30);
		champsPublics.setBounds(200,370,330,30);
		settersAndGetters.setBounds(200,390,300,80);
		
		
		getContentPane().add(label);
		getContentPane().add(textField);
		getContentPane().add(inputButton);
		
		getContentPane().add(superClassName);
		getContentPane().add(totalConstractors);
		getContentPane().add(totalProperties);
		getContentPane().add(totalInterfaces);
		getContentPane().add(totalMethods);
		getContentPane().add(totalInnerClasses);
		getContentPane().add(heritageChaineDeclaration);
		getContentPane().add(test);
		getContentPane().add(champsPublics);
		getContentPane().add(hierarchie);
		getContentPane().add(settersAndGetters);
		
		
		heritageChaineSize = parser.getHeritageChaine().size();
		
		heritageChaine = new JLabel[heritageChaineSize];
		
		for (int i = 0; i < heritageChaineSize; i++) {
			heritageChaine[i] = new JLabel("");
			heritageChaine[i].setBounds(200,325 + i * 25,300,100);
			getContentPane().add(heritageChaine[i]);
		}
		
		inputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				superClassName.setText("");	
				totalConstractors.setText("");
				totalProperties.setText("");
				totalInterfaces.setText("");
				totalMethods.setText("");
				totalInnerClasses.setText("");
				heritageChaineDeclaration.setText("");
				
				for (int i = 0; i < heritageChaineSize; i++) {
						heritageChaine[i].setText("");
				}
					
				ClassParser ClassParser = new ClassParser(Class.forName(textField.getText()));
				heritageChaineSize = parser.getHeritageChaine().size();

				if (parser.getSuperClass() != null) superClassName.setText("Parent Class  : " + parser.getSuperClass().getCanonicalName());
				Vector<?> hierarchies =parser.getClassesParentes();
				hierarchie.setText("la hierarchie de la classe :" + hierarchies);
				totalConstractors.setText("Total Constractors : " + parser.getConstructors().length);
//				Vector<?> vv =parser.getConstructeurs(); 
//				for(int i=0;i<vv.size();i++) {
//					test.setText("le" + i + "constructeur >>>" + vv);
//				}
				
				
				totalProperties.setText("Total Properties : " + parser.getFields().length);
				//totalInterfaces.setText("Total Interfaces : " + parser.getIntefaces().length);
				totalMethods.setText("Total Methods : " + parser.getMethods().length);
				totalInnerClasses.setText("Total Inner Classes : " + parser.getInnerClasses().length);
				Vector<?> v=parser.getInterfaces();
				
				if(v!=null){
				for(int i=0;i<v.size();i++) {
					test.setText("la" + i+1 + "interface >>>" + v);
				}
				}
				Vector<?> champsPublics=parser.getChampsPublics();
				if(champsPublics!= null) {
				for(int i=0;i<champsPublics.size();i++) {
					test.setText("le" + i+1 + "champs publics >>>" + champsPublics);
				}
				
				}
				//settersAndGetters.setText(parser.afficherGettersSetters());
				// a afficher dans la console 
				
				System.out.println("la hierarchie de la classe :" + hierarchie);
					
				} catch (Exception exc) {
					System.out.println("Class not found");
				} 
			}
		});
		
        setBounds(200, 200, 900, 800);
        setLayout(null);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		
		new ClassParserFrame();
	
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
