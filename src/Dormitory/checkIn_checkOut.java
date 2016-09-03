package Dormitory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.ldap.SortControl;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ConnectionDB.FigureTableModel;
import ConnectionDB.MYSQLConnectionDB;
import File_Management.Read_File;
import File_Management.Write_File;
import Main_Programe.Main_Dormitory;
//import Main_Programe.Main_Dormitory;
public class checkIn_checkOut extends JPanel implements ActionListener ,ItemListener{


	String dataDisplay[] ;
	public JCheckBox roomFloor [] = new JCheckBox[45];
	JPanel pFloor = new JPanel(new GridLayout(5,9));
	String pricesetName [] = {"sometimes","daily","monthly","packet1","packet2"};
	String priceRoom [] = {"200","350","8000","10","20"};
	JLabel nameFirst  [] = new JLabel[4];
	JTextField room,typeRoom,customer,searching,lastName;
	JTextField date [] = new JTextField[2];
	JTextField status [] = new JTextField[4];
	JButton checkRoom,searchButton,addTime,addDisplay;
	JButton menu[] = new JButton[3];
	JRadioButton list[] = new JRadioButton[2];
	JRadioButton search[] = new JRadioButton[3];
	JRadioButton type[] = new JRadioButton[3];
	ButtonGroup listGroup,searchGroup,typeGroup,priceGroup1,priceGroup2;
	JButton cancelSave [] = new JButton[2];
	JRadioButton price [] = new JRadioButton[5];
	JLabel pricel [] = new JLabel[2];
	JLabel statusl [] = new JLabel[4];
    JTable dataTable;
	JScrollPane sp ;
	JLabel disL[] = new JLabel[10];
	
	public checkIn_checkOut(){
		setLayout(null);
		setOpaque(true);
		setBackground(Color.pink);
		JPanel PnameFist = new JPanel(new GridLayout(4,1));
		String name [] = {"ห้อง","ประเภทห้อง","ชื่อ  ลูกค้า","นามสกุล "};
		for (int i = 0; i < nameFirst.length; i++) {
			nameFirst[i] = new JLabel(name[i]);
			nameFirst[i].setHorizontalAlignment(JLabel.RIGHT);
			nameFirst[i].setFont(new Font("Angsana New",Font.BOLD,20));
			nameFirst[i].setOpaque(true);
			nameFirst[i].setBackground(Color.pink);
			PnameFist.add(nameFirst[i]);
		}
		PnameFist.setBounds(60,20,80,128);
		add(PnameFist);
		////////////////////////===================================================
		JPanel ProomButton = new JPanel(new GridLayout(1,2)); // JTextField room+Button
		JPanel Padd = new JPanel(null); //Panel room
		room = new JTextField();/// input room;
		room.setHorizontalAlignment(JTextField.CENTER);
		room.setFont(new Font("Angsana New",Font.BOLD,20));
		room.setBounds(0,0,150,30);
		Padd.add(room); //add room
		
		checkRoom = new MyButton("ตรวจสอบ", "tool");
		checkRoom.setBounds(155,0,145,30);
		checkRoom.addActionListener(this);
		Padd.add(checkRoom);//add checkRoom
		
		ProomButton.add(Padd);
		ProomButton.setBounds(150,20,300,30);
		add(ProomButton);
///////////////////////===========================================================/		
		
		JPanel Ptypecusto = new JPanel(new GridLayout(3,1,0,2));/// ประเภทห้อง
		JPanel ProomName = new JPanel(null);
		typeRoom = new JTextField();
		typeRoom.setFont(new Font("Angsana New",Font.BOLD,30));
		typeRoom.setHorizontalAlignment(JTextField.CENTER);
		typeRoom.setBounds(0,0,400,30);
		JPanel pcus = new JPanel(null);
		
		customer = new JTextField(); 	//// ชื่อลูกค้า
		customer.setFont(new Font("AngsanaUPC",Font.BOLD,20));
		customer.setHorizontalAlignment(JTextField.CENTER);
		customer.setBounds(0,0,400,30);
		
		JPanel pLast = new JPanel(null);
		lastName = new JTextField(); 	//// นามสกุลลูกค้า
		lastName.setFont(new Font("AngsanaUPC",Font.BOLD,20));
		lastName.setHorizontalAlignment(JTextField.CENTER);
		lastName.setBounds(0,0,400,30);
		
		ProomName.add(typeRoom);
		pcus.add(customer);
		Ptypecusto.add(ProomName);
		Ptypecusto.add(pcus);
		pLast.add(lastName);
		Ptypecusto.add(pLast);
		Ptypecusto.setBounds(150,54,400,100);
		add(Ptypecusto);
		
///////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		JPanel Pmenu = new JPanel(new GridLayout(1,3));
		String menuName[] = {"พักชั่วคราว","พักรายวัน","CheckOut"};	
		String menuPic [] = {"blue","green","Delete"};
		for (int i = 0; i < menuName.length; i++) {
			menu[i] = new MyButton(menuName[i], menuPic[i]);
			menu[i].addActionListener(this);
			Pmenu.add(menu[i]);
		}
		Pmenu.setBounds(650,20,450,30);
		add(Pmenu);
		////////////////\\\\\\\\\\|||||||||||||||||||||||||-----------------------
		String listName [] = {"ห้องพัก","ผุ้เข้าพัก"};
		JPanel pList = new JPanel(new GridLayout(2,1));
		listGroup = new ButtonGroup();
		for (int i = 0; i < listName.length; i++) {
			list[i] = new JRadioButton(listName[i]);
			list[i].setFont(new Font("Angsana New",Font.BOLD,20));
			list[i].addActionListener(this);
			list[i].setFocusable(false);
			
			listGroup.add(list[i]);
			pList.add(list[i]);
		}
		
		pList.setFont(new Font("Angsana New",Font.BOLD,20));
		pList.setBorder(new TitledBorder(new LineBorder(Color.black),"รายการที่แสดงผล"));
		pList.setBounds(900, 60, 200, 100);
		add(pList);
		
		////////////////////--------------------------------------
		JPanel Psearch = new JPanel(new GridLayout(1,3));
		String searchName [] = {"ห้อง","ชื่อ","นามสกุล"};
		searchGroup = new ButtonGroup();
		for (int i = 0; i < search.length; i++) {
			search[i] = new JRadioButton(searchName[i]);
			search[i].setSelected(true);
			search[i].setFont(new Font("Angsana New",Font.BOLD,20));
			search[i].setFocusable(false);
			searchGroup.add(search[i]);
			Psearch.add(search[i]);
		}
		Psearch.setFont(new Font("Angsana New",Font.BOLD,20));
		Psearch.setBorder(new TitledBorder(new LineBorder(Color.black),"ค้นหาโดย"));
		Psearch.setBounds(80, 200, 250, 50);
		add(Psearch);
	////////////=================================
		JPanel Ptype = new JPanel(new GridLayout(1,2));
		String typeName [] = {"ค่าห้อง","ค่าสาธารณูปโภค","อื่นๆ"};
		typeGroup = new ButtonGroup();
		for (int i = 0; i < type.length; i++) {
			type[i] = new JRadioButton(typeName[i]);
			type[i].setFont(new Font("Angsana New",Font.BOLD,20));
			type[i].setFocusable(false);
			typeGroup.add(type[i]);
			Ptype.add(type[i]);
		}
		Ptype.setFont(new Font("Angsana New",Font.BOLD,20));
		Ptype.setBorder(new TitledBorder(new LineBorder(Color.black),"ประเภท"));
		Ptype.setBounds(350, 200, 400, 50);
		add(Ptype);
		///////////////---------------------------------------------
		JPanel PcancelSave = new JPanel(new GridLayout(1,2));
		String cancelSaveName[] = {"ยกเลิก","บันทึก"};
		String cancelSaveIm [] = {"dbmin2","Save"};
		for (int i = 0; i < cancelSaveName.length; i++) {
			cancelSave[i] = new MyButton(cancelSaveName[i],cancelSaveIm[i]);
			cancelSave[i].addActionListener(this);
			PcancelSave.add(cancelSave[i]);
		}
		PcancelSave.setBounds(800,200,300,30);
		add(PcancelSave);
		//================================
		searching = new JTextField();
		searching.setHorizontalAlignment(JTextField.CENTER);
		searching.setFont(new Font("Angsana New",Font.BOLD,20));
		searching.setBounds(80,269,400,30);
		add(searching);

		searchButton = new MyButton("ค้นหา", "Search");
		searchButton.addActionListener(this);
		searchButton.setBounds(490,269,150,30);
		add(searchButton);
		////////////////================================================
		String priceName [] = {"ราคา (พักชั่วคราว/ซม.)","ราคา (รายวัน/วัน)","ราคา (รายเดือน/เดือน)","ราคา (โปรโมชั่น 1)","ราคา (โปรโมชั่น 2)"};
		
		JPanel pPrice = new JPanel(new GridLayout(1,5));
		priceGroup1 = new ButtonGroup(); priceGroup2 = new ButtonGroup();
		for (int i = 0; i < price.length; i++) {
			price[i] = new JRadioButton(priceName[i]);
			price[i].setFont(new Font("Angsana New",Font.BOLD,20));
			price[i].addActionListener(this);
			price[i].setName(pricesetName[i]);
			price[i].setFocusable(false);
			pPrice.add(price[i]);
		}
		priceGroup1.add(price[0]);priceGroup1.add(price[1]);priceGroup1.add(price[2]);
		priceGroup2.add(price[3]);priceGroup2.add(price[4]);
		pPrice.setFont(new Font("Angsana New",Font.BOLD,20));
		pPrice.setBounds(80, 300, 750, 50);
		add(pPrice);
	//	=============================================================showDataFromDataBase
		dataTable = new JTable();
		sp = new JScrollPane(dataTable);
	
		sp.setBounds(80,350,700,300);
		sp.setVisible(false);
		add(sp);
	//-=--=---=-=-=-=-=-=-=-=-=-=-=	
		String roomName [] = {"101","102","103","104","105","106","107","108","109","201","202","203","204","205","206","207","208","209"
				,"301","302","303","304","305","306","307","308","309","401","402","403","404","405","406","407","408","409",
				"501","502","503","504","505","506","507","508","509",};
		for (int i = 0; i < roomFloor.length; i++) {
			roomFloor[i] = new JCheckBox(roomName[i]);
			roomFloor[i].addActionListener(this);
			roomFloor[i].addItemListener(this);
			pFloor.add(roomFloor[i]);
		}
		pFloor.setBounds(80,350,700,300);
		pFloor.setBorder(new TitledBorder("Room"));
		pFloor.setVisible(false);
		add(pFloor);
		if (list[0].isSelected()) {
			pFloor.setVisible(true);
		}
		JPanel Pdate = new JPanel(new GridLayout(2,1,0,2)); // JTextField room+Button
		for (int i = 0; i < date.length; i++) {
			date[i] = new JTextField();
			date[i].setHorizontalAlignment(JTextField.CENTER);
			date[i].setFont(new Font("Angsana New",Font.BOLD,20));
			Pdate.add(date[i]); //add room
		}
		Pdate.setBounds(900,350,150,60);
		add(Pdate);
		
		addTime = new MyButton("เพิ่มเวลา", "Add");
		addTime.addActionListener(this);
		addTime.setBounds(900,420,150,30);
		add(addTime);
		
		JPanel Pstatus = new JPanel(new GridLayout(4,1,0,2)); // JTextField room+Button
		
		for (int i = 0; i < status.length; i++) {
			status[i] = new JTextField();
			status[i].setHorizontalAlignment(JTextField.CENTER);
			status[i].setFont(new Font("Angsana New",Font.BOLD,20));
			Pstatus.add(status[i]); //add room
		}
		status[1].addActionListener(this);
		status[3].setFont(new Font("Angsana New",Font.BOLD,40));
		JLabel bath = new JLabel("บาท/Bath");
		bath.setBounds(1070, 590, 100, 30);
		bath.setFont(new Font("Angsana New",Font.BOLD,20));
		add(bath);
		Pstatus.setBounds(900,500,150,120);
		add(Pstatus);
		
		JPanel Ppricel = new JPanel(new GridLayout(2,1));
		String pricelname [] = {"ตั้งแต่วันที่","ถึงวันที่"};
		for (int i = 0; i < pricel.length; i++) {
			pricel[i] = new JLabel(pricelname[i]);
			pricel[i].setHorizontalAlignment(JLabel.RIGHT);
			pricel[i].setFont(new Font("Angsana New",Font.BOLD,20));
			Ppricel.add(pricel[i]);
		}
		Ppricel.setBounds(780,355,100,50);
		add(Ppricel);
		
		JPanel Pstatusl = new JPanel(new GridLayout(4,1));
		String statuslname [] = {"ราคาหน่วยล่ะ","เวลาพัก","ลด %","เป็นเงิน"};
		for (int i = 0; i < statusl.length; i++) {
			statusl[i] = new JLabel(statuslname[i]);
			statusl[i].setHorizontalAlignment(JLabel.RIGHT);
			statusl[i].setFont(new Font("Angsana New",Font.BOLD,20));
			Pstatusl.add(statusl[i]);
		}
		Pstatusl.setBounds(780,500,100,120);
		add(Pstatusl);
		//////////===============================================
		JPanel main = new JPanel(null);
		main.setBounds(10, 190, 1250, 500);
		main.setBorder(BorderFactory.createLineBorder(Color.black));
		add(main);
		
		int index = (int)(Math.random()*2);
		list[index].setSelected(true);
		if (list[0].isSelected()==true)getRoomShow();
		else if (list[1].isSelected()==true) getDisplayShow();
	}
	void getRoomShow(){
		pFloor.setVisible(true);
		sp.setVisible(false);
		//display.setText(null);
		String checkData [] = new Read_File().checkRoom();
		for (int i = 0; i < roomFloor.length; i++) {
			String rName = roomFloor[i].getText();
			for (int j = 0; j < checkData.length; j++) {
				if (checkData[j].equals(rName)) {
					roomFloor[i].setSelected(true);
				}
			}
		}
	}
	void getDisplayShow(){
		sp.setVisible(true);
		pFloor.setVisible(false);
		//dataDisplay = new Read_File().readFile().split("/");
		Connection conn = MYSQLConnectionDB.crateConnection();
		Statement s = MYSQLConnectionDB.createStatement(conn, true, false);
		DatabaseMetaData dbmt = MYSQLConnectionDB.createDatabaseMeataData(conn);
		String sql = "select room,typeRoom,name,statusDMTR,startRest,arrive from CHECKIN";
		try {
			ResultSet rs = s.executeQuery(sql);
			FigureTableModel model = new FigureTableModel(rs);
			dataTable.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		Read_File fileRead = new Read_File(); 
		Write_File fileWrite = new Write_File();
		if (source == checkRoom ) {//ประเภทห้อง
			boolean enable = true;
			String room = this.room.getText();
			String checkRoom = fileRead.search(room, 0);
			String check [] = checkRoom.split("/");
			String m = "";
			for (int i = 0; i < roomFloor.length; i++) {
				m += roomFloor[i].getText()+"/";
			}
			String d [] = m.split("/");
			for (int i = 0; i < d.length; i++) {
				if (room.compareTo(d[i])==0) enable = false;
			}
			if (room.equals("")) {
				JOptionPane.showMessageDialog(this, "ยังไม่ป้อนหมายเลขห้องครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
			}else if (enable == true) {
				JOptionPane.showMessageDialog(this, "กรุณาป้อนหมายเลขห้องให้ถูกต้องด้วยครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
				this.room.setText("");
			}
			else if (check[0].equals(room)) {
				JOptionPane.showMessageDialog(this, "มีคนพักแล้วครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (check[0].equals("")) {
				JOptionPane.showMessageDialog(this, "ยินดีด้วยครับ มีห้องว่างให้พักแล้ว !!","information", JOptionPane.INFORMATION_MESSAGE);
				String r = this.room.getText();
				int n  = Integer.parseInt(r);
				if ((n%2)==1) typeRoom.setText("เตียงเดี่ยว");
				else typeRoom.setText("เตียงคู่");
			}
		}
		else if (source == menu[2]) {//checkout
			String room = this.room.getText();
			int ans = JOptionPane.showConfirmDialog(this, "กรุณายืนยันการ CheckOut!!! ","Exit",JOptionPane.YES_NO_OPTION	);
			if (ans == JOptionPane.YES_OPTION) {
				status[3].setText(new Calculate(room).sum+"");
				
				int line = new Read_File().getNumberOfLinesearch(room, 0);
				new Write_File().delete(line-1);
				clear();
			}
		}else if (source == cancelSave[0]) {//cancel
			clear();
		}
		else if (source == cancelSave[1]) {//save
			String r = this.room.getText();
			String checkRoom = fileRead.search(r, 0);
			String check [] = checkRoom.split("/");
			
			if (this.room.getText().equals("")||typeRoom.getText().equals("")||customer.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูลให้ครบด้วยครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
			}else if (date[0].getText().equals("")||date[1].getText().equals("")||status[0].getText().equals("")||status[1].getText().equals("")||status[2].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูลให้ครบด้วยครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (check[0].equals(r)) {
				JOptionPane.showMessageDialog(this, "มีคนพักแล้วครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String data = "";
				String room = this.room.getText();
				String roomType = typeRoom.getText();
				String customer = this.customer.getText();
				String lastName = this.lastName.getText();
				String priceData = "";
				for (int i = 0; i < price.length; i++) {
					if (price[i].isSelected()==true) {
						priceData = price[i].getName();
					}
				}
				String dateIn = this.date[0].getText();
				String dateOut = this.date[1].getText();
				
				String price = status[0].getText();
				String dateStay = status[1].getText();
				String discount = status[2].getText();
	
				String sql = "insert into CHECKIN values("+room+",'"+roomType+"',"+"'"+customer+"','"+priceData+"','"+dateIn+"','"+dateOut+"',"+price+","+dateStay+","+discount+")";
				Connection conn = MYSQLConnectionDB.crateConnection();
				Statement s = MYSQLConnectionDB.createStatement(conn,true, true);
				try {
					s.executeUpdate(sql);
					s.close();
					JOptionPane.showMessageDialog(this, "บันทึกข้อมูลเรียบร้อยครับ !!","information", JOptionPane.INFORMATION_MESSAGE);
					clear();
					status[3].setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}if (source == searchButton) {//ค้นหา
			String type = "";
			for (int i = 0; i < search.length; i++) {
				if (search[0].isSelected()==true) type = "room";
				else if (search[1].isSelected()==true) type = "name";
				else if(search[2].isSelected()==true) type = "room";
			}
			String keySearch = searching.getText();
			if (keySearch.compareToIgnoreCase("")==0) {
				JOptionPane.showMessageDialog(this, "ขออภัยไม่พบข้อมูลครับ! !!","information", JOptionPane.INFORMATION_MESSAGE);
			}else {
				Connection conn = MYSQLConnectionDB.crateConnection();
				Statement s = MYSQLConnectionDB.createStatement(conn, true, false);
				String sql = "";
				if(type.equals("room"))sql = "select * from CHECKIN where "+type+" = "+keySearch;
				else if(type.compareTo("name")==0)sql = "select * from CHECKIN where "+type+" = '"+keySearch+"'";
				try {
					ResultSet rs = s.executeQuery(sql);
					FigureTableModel model = new FigureTableModel(rs);
					dataTable.setModel(model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if (source == addTime) { //addtime
		
			SimpleDateFormat f[] = new SimpleDateFormat[1];
			Date now = new Date();
			//13-5-1980
			String format[] = {"d-MM-yyyy"};
			for (int i = 0; i < format.length; i++) {
				f[i] = new SimpleDateFormat(format[i]);
				date[0].setText(f[i].format(now));
			}
			
		}else if (source == list[0]) {
			getRoomShow();
						
		}else if (source==list[1]) {//
			getDisplayShow();
		}
		else if (source == menu[0]) {//พักชั่วคราว
			price[0].setSelected(true);
			status[2].setText("0");
		}
		else if (source == menu[1]) {//พักรายวัน
			price[1].setSelected(true);
			status[2].setText("0");
		}
		else if (source == price[3]) {
			status[2].setText(priceRoom[3]);
		}
		else if (source == price[4]) {
			status[2].setText(priceRoom[4]);
		}
		else if (source == status[1]) {

			String getDate = date[0].getText();
			String getStatus = status[1].getText();
			setDate(getDate, getStatus);
		}
		
		for (int i = 0; i < price.length-2; i++) {
			if (source == price[i]||price[i].isSelected()==true) {
				status[0].setText(priceRoom[i]);
			}
		}
	}
	void clear (){
		room.setText(null);
		typeRoom.setText(null);
		customer.setText(null);
		lastName.setText(null);
		for (int i = 0; i < date.length; i++) {
			date[i].setText(null);
		}
		for (int i = 0; i < status.length-1; i++) {
			status[i].setText(null);
		}
		listGroup.clearSelection();
		searchGroup.clearSelection();
		typeGroup.clearSelection();
		priceGroup1.clearSelection();
		priceGroup2.clearSelection();
	}
	void setDate(String getDate , String getStatus){
		String arrayDate [] = getDate.split("-");
		int modifyDate = Integer.parseInt(arrayDate[0]);
				
				
				int modifyStatus = Integer.parseInt(getStatus);
				
				int sumDate = modifyStatus + modifyDate;
				
				arrayDate[0] = Integer.toString(sumDate);
				String dataReturn = "";
				for (int i = 0; i < arrayDate.length; i++) {
					dataReturn += arrayDate[i]+"-";
					
				}
				String dataSub = "";
				int c = 0;
				for (int i = 0; i < dataReturn.length(); i++) {
					dataReturn.substring(i);
					c++;
				}
				dataSub = dataReturn.substring(0,c-1);	
				date[1].setText(dataSub);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		for (int i = 0; i < roomFloor.length; i++) {
			if (e.getStateChange()==ItemEvent.SELECTED) {
				if (source == roomFloor[i]) {
					String room = roomFloor[i].getText();
					this.room.setText(room);
					break;
				}
				//System.out.println(r);
			}else if (e.getStateChange()==ItemEvent.DESELECTED) {
				if (source == roomFloor[i]) {
					roomFloor[i].setSelected(false);
				}
			}
		}
	}
}
