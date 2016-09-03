package ConnectionDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MYSQLConnectionDB {
	public static Connection crateConnection(){
		Connection conn = null;
		String server = "YAJOK_SERVER",user = "user2_yajok",password = "password";
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String host = "jdbc:sqlserver://"+server+":1433";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(host,user,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static Statement createStatement(Connection conn,boolean scrollable,boolean update){
		Statement statement = null;
		
			try {
				if (scrollable && !update) {
					statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				}else if (scrollable && update) {
					statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				}else {
					statement = conn.createStatement();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return statement;
	}
	public static DatabaseMetaData createDatabaseMeataData(Connection conn){
		DatabaseMetaData dbmd = null;
		try {
			dbmd = conn.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbmd;
	}
}
