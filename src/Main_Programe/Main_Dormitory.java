package Main_Programe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Dormitory.checkIn_checkOut;
import File_Management.Read_File;

public class Main_Dormitory extends JFrame implements ActionListener{

	JMenuBar menuBar;
	JMenu file,edit,help;
	JMenuItem fileItem [] = new JMenuItem[1];
	JMenuItem helpItem [] = new JMenuItem[2];
	checkIn_checkOut main = new checkIn_checkOut();
	public Main_Dormitory(){
		EditCheckIn_checkOut();
		EditComponents();
		EditFrame();
	}
	void EditCheckIn_checkOut(){
		add(main,BorderLayout.CENTER);
	}
	public void setFrame(String title){
		setTitle(title);
	}
	void EditComponents(){
		menuBar = new JMenuBar();
		file = new JMenu("File");
		help = new JMenu("Help");
		edit = new JMenu("Edit");
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		
		String fileText [] = {"Exit"};
		
		for (int i = 0; i < fileItem.length; i++) {
			fileItem[i] = new JMenuItem(fileText[i]);
			fileItem[i].setIcon(new ImageIcon("images/Exit.png"));
			fileItem[i].addActionListener(this);
			file.add(fileItem[i]);
		}
		
		String helpn [] = {"คู่มือการใช่โปรแกรม","About Dormitry"};
		for (int i = 0; i < helpItem.length; i++) {
			helpItem[i] = new JMenuItem(helpn[i]);
			helpItem[i].addActionListener(this);
			help.add(helpItem[i]);
		}
		
		menuBar.add(file);
		menuBar.add(help);
		setJMenuBar(menuBar);
	}
	void EditFrame(){
		setTitle("Dormitory: 52011211206 วุฒิพงษ์ ทองมนต์");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		setSize(width, height-30);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source =e.getSource();
		if (source == fileItem[0]) {
			int ans = JOptionPane.showConfirmDialog(this, "ต้องการออกจากโปรอกรม Yes ","Exit",JOptionPane.YES_NO_CANCEL_OPTION	);
			if (ans == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main_Dormitory();
	}


}
