package ConnectionDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

public class GUIConnection extends JFrame {
	
	Statement statement;
	Connection conn;
	DatabaseMetaData dbmd;
	GUIConnection(){
		makeConnection();
	}
	public void makeConnection(){
		conn = MYSQLConnectionDB.crateConnection();
		if(conn!=null)System.out.println("Connection Successfull !");
		statement = MYSQLConnectionDB.createStatement(conn, true, false);
		dbmd = MYSQLConnectionDB.createDatabaseMeataData(conn);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIConnection();
	}

}
