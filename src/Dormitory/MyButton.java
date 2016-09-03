package Dormitory;



import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	public MyButton(String name,String image){
		ImageIcon icon = new ImageIcon("images/"+image+".png");
		setIcon(icon);
		setText(name);
		setFocusable(false);
		setFont(new Font("Angsana New",Font.BOLD,20));
	}
}
