package src;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DataStructsFrame extends JFrame {
	public DataStructsFrame(String title, int[] numbers, int[] numbersB) {
		super(title);

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		JPanel unorderedPanel = new JPanel();
		unorderedPanel.setLayout(new BoxLayout(unorderedPanel, BoxLayout.PAGE_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		ArrayList<ListItem> list = arrayToList(numbers, numbersB);

		ListPanel unorderedList = new ListPanel("Unordered List");
		unorderedList.setDiameter(75);
		unorderedList.addItems(list);

		ListPanel orderedList = new ListPanel("Ordered List");
		orderedList.setDiameter(75);

		JButton sortButton = new JButton("Sort A List");
		sortButton.setSize(30, 10);
		sortButton.setAlignmentX(LEFT_ALIGNMENT);
		
		JButton sortButtonB = new JButton("Sort B List");
		sortButton.setSize(30, 10);
		sortButton.setAlignmentX(RIGHT_ALIGNMENT);

		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				ArrayList<ListItem> orderedlist = orderedArrayToList(numbers, numbersB);
				//container.removeAll(panel.getComponents());
				panel.removeAll();
				
				orderedList.addItems(orderedlist);
				panel.add(orderedList);
				pack();
				panel.revalidate();
				panel.repaint();

			}
		});
		sortButtonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				
				panel.removeAll();
				
				ArrayList<ListItem> orderedlist = orderedArrayToListBtoA(numbers, numbersB);
				orderedList.addItems(orderedlist);
				panel.add(orderedList);
				pack();
				panel.revalidate();
				panel.repaint();

			}
		});

		
		unorderedPanel.add(unorderedList);
		unorderedPanel.add(sortButton);
		unorderedPanel.add(sortButtonB);
		// add(unorderedPanel);
		container.add(unorderedPanel);
		container.add(panel);
		add(container);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private ArrayList<ListItem> arrayToList(int[] numbers, int[] numbersB) {
		ArrayList<ListItem> list = new ArrayList<ListItem>();
		list.removeAll(list);
		for (int i = 0; i < numbers.length; i++) {
			ListItem item = new ListItem(numbers[i], numbersB[i]);
			list.add(item);
		}
		// System.out.println("arrayToList: " + list);
		return list;
	}

	private ArrayList<ListItem> orderedArrayToList(int[] numbers, int[] numbersB) {
		ArrayList<ListItem> list = new ArrayList<ListItem>();

		for (int i = 0; i < numbers.length; i++) {
			ListItem item = new ListItem(numbers[i], numbersB[i]);
			list.add(item);
		}
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i).getValA());
			// System.out.println(temp.get(i));
			// System.out.println(temp);
		}
		// System.out.println("before " +temp);
		Collections.sort(temp);
		Collections.reverse(temp);
		// System.out.println(temp);
		ArrayList<ListItem> newA = new ArrayList<ListItem>();
		newA.removeAll(newA);
		for (int i = 0; i < numbers.length; i++) {
			ListItem item = new ListItem(0, 0);
			newA.add(item);
		}
		for (int i = 0; i < list.size(); i++) {
			newA.get(i).setValA(temp.get(i));
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).getValA() == newA.get(i).getValA()) {
					newA.get(i).setValB(list.get(j).getValB());
				}
			}
		}
		return newA;
	}
	
	private ArrayList<ListItem> orderedArrayToListBtoA(int[] numbers, int[] numbersB) {
		ArrayList<ListItem> list = new ArrayList<ListItem>();

		for (int i = 0; i < numbers.length; i++) {
			ListItem item = new ListItem(numbers[i], numbersB[i]);
			list.add(item);
		}
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i).getValB());
			// System.out.println(temp.get(i));
			// System.out.println(temp);
		}
		// System.out.println("before " +temp);
		Collections.sort(temp);
		// System.out.println(temp);
		ArrayList<ListItem> newB = new ArrayList<ListItem>();
		newB.removeAll(newB);
		for (int i = 0; i < numbers.length; i++) {
			ListItem item = new ListItem(0, 0);
			newB.add(item);
		}
		for (int i = 0; i < list.size(); i++) {
			newB.get(i).setValB(temp.get(i));
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).getValB() == newB.get(i).getValB()) {
					newB.get(i).setValA(list.get(j).getValA());
				}
			}
		}
		return newB;
	}
}
