package Dormitory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Main_Programe.Main_Dormitory;

public class Room extends JFrame implements ActionListener{
	int max = 45;
	JCheckBox roomFloor [] = new JCheckBox[max];
	checkIn_checkOut c = new checkIn_checkOut();
	public static String text = "";
	Room(){
		setLayout(new BorderLayout());
		initComponents();
		
		setTitle("Room");
		setSize(500,300);
		setLocation(50,100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	void initComponents(){
		
		JPanel pFloor = new JPanel(new GridLayout(5,9));
		String room [] = {"101","102","103","104","105","106","107","108","109","201","202","203","204","205","206","207","208","209"
				,"301","302","303","304","305","306","307","308","309","401","402","403","404","405","406","407","408","409",
				"501","502","503","504","505","506","507","508","509",};
		ButtonGroup g = new ButtonGroup();
		for (int i = 0; i < max; i++) {
			roomFloor[i] = new JCheckBox(room[i]);
			roomFloor[i].setName(room[i]);
			roomFloor[i].addActionListener(this);
			g.add(roomFloor[i]);
			pFloor.add(roomFloor[i]);
		}
		pFloor.setBorder(new TitledBorder(new LineBorder(Color.black),"Room"));
	add(pFloor,BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		for (int i = 0; i < roomFloor.length; i++) {
			if (source == roomFloor[i]) {
				if (roomFloor[i].isSelected()==true) {
					String room = roomFloor[i].getText();
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Room();
	}
}
