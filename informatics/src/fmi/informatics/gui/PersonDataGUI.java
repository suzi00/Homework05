package fmi.informatics.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fmi.informatics.AscendingComparators.AgeComparator;
import fmi.informatics.AscendingComparators.EgnComparator;
import fmi.informatics.AscendingComparators.HeightComparator;
import fmi.informatics.AscendingComparators.NameComparator;
import fmi.informatics.AscendingComparators.PersonComparator;
import fmi.informatics.AscendingComparators.WeightComparator;
import fmi.informatics.DescendingComparators.*;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;

// създаваме клас PersonDataGUI
public class PersonDataGUI {
	
	public static Person[] people;
	JTable table;
	PersonDataModel personDataModel;

	public static void main(String[] args) {
		getPeople();
		PersonDataGUI gui = new PersonDataGUI();
		gui.createAndShowGUI();
	}
	
	public static void getPeople() {
		people = new Person[8];
		
		for (int i = 0; i < 4; i++) {
			Person student = Student.StudentGenerator.make();
			people[i] = student;
		}
		
		for (int i = 4; i < 8; i++) {
			Person professor = Professor.ProfessorGenerator.make();
			people[i] = professor;
		}
	}
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Таблица с данни за хора");
		frame.setSize(650, 600);
		
		JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);
		
		personDataModel = new PersonDataModel(people);
		table = new JTable(personDataModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Добавяме бутон за сортиране по години с Comparable interface
		JButton buttonSortDescending = new JButton("Сортирай по низходящ ред");

		// Добавяме бутон за сортиране
		JButton buttonSort = new JButton("Сортирай по възходящ ред");
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(label, BorderLayout.NORTH);
		container.add(scrollPane, BorderLayout.CENTER);

		container.add(buttonSortDescending, BorderLayout.NORTH);
		container.add(buttonSort, BorderLayout.SOUTH);
		final JDialog sortDialogDescending = new CustomDialogDesc(getSortText(), this);

		// Добавяме listener към бутона за сортиране по години
		buttonSortDescending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialogDescending.pack();
				sortDialogDescending.setVisible(true);
			}
		});

		// Добавяме диалог
		final JDialog sortDialog = new CustomDialog(getSortText(), this);

		// Добавяме listener към бутона за сортиране
		buttonSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialog.pack();
				sortDialog.setVisible(true);
			}
		});
		
		frame.setVisible(true);
	}

	public void sortTable(int intValue, JTable table, Person[] people) {
		PersonComparator comparator = null;
		
		switch (intValue) {
			case 1: 
				comparator = new NameComparator(); 
				break;
			case 2: 
				comparator = new EgnComparator();
				break;
			case 3:
				comparator = new HeightComparator();
				break;
			case 4: 
				comparator = new WeightComparator();
				break;
			case 5:
				comparator = new AgeComparator();
				break;

		}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people); // Сортировка по подразбиране по години
		} else {
			Arrays.sort(people, comparator);
		}
		
		table.repaint();
	}

	public void sortTableDescending(int intValue, JTable table, Person[] people) {
		PersonComparator comparator = null;

		switch (intValue) {
			case 1:
				comparator = new NameDescendingComparator();
				break;
			case 2:
				comparator = new EgnDescendingComparator();
				break;
			case 3:
				comparator = new HeightDescendingComparator();
				break;
			case 4:
				comparator = new WeightDescendingComparator();
				break;
			case 5:
				comparator = new AgeDescendingComparator();
				break;

		}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people); // Сортировка по подразбиране по години
		} else {
			Arrays.sort(people, comparator);
		}

		table.repaint();
	}

	private static String getSortText() {
		return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
				" 1 - Име \n" +
				" 2 - ЕГН \n" +
				" 3 - Височина \n" +
				" 4 - Тегло \n" +
				" 5 - Години \n"; 
	}
}
