package Dormitory;

import File_Management.Read_File;

public class Calculate{
	
	checkIn_checkOut c = new checkIn_checkOut();
	int sum = 0;
	public Calculate(String user) {
		// TODO Auto-generated constructor stub\

		String data = new Read_File().search(user, 0);
		String d [] = data.split("/");
		String price = d[7];
		String timeStay = d[8];
		String discount = d[9];
		int convertPrice = Integer.parseInt(price);
		int convertTimestay = Integer.parseInt(timeStay);
		int convertDiscount = Integer.parseInt(discount);
		if (convertDiscount==0) {
			sum = convertPrice*convertTimestay;
		}else {
			int sumDiscount = ((convertPrice*convertTimestay)*convertDiscount)/100;
			sum = (convertPrice*convertTimestay)-sumDiscount;
		}
	}
}
