package ConnectionDB;

import java.sql.*;
import javax.swing.table.AbstractTableModel;

public class FigureTableModel extends AbstractTableModel{ //คลาสไว้ใช้ set model ให้ JTable
		ResultSet rs; // rs ต้องเป็น scrollable ResultSet เพื่อใช้ method บางตัวเช่น first(), last()
		ResultSetMetaData rsmd;
		
	public FigureTableModel(ResultSet rs) {
		this.rs = rs;
		try {
				rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getColumnName(int c){
		try {
			return rsmd.getColumnName(c+1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}
	public int getColumDisplaySize(int c){
		try {
			return rsmd.getColumnDisplaySize(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public Object getValueAt(int r, int c){
		try {
			rs.absolute(r+1);//ใช้ในการเลื่อน Record ไปยังตำแหน่งที่กำหนด
			return rs.getObject(c+1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	
		}
	}
	public int getRowCount(){
		try {
			rs.last(); // rs ต้องเป็น scrollable ResultSet
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}