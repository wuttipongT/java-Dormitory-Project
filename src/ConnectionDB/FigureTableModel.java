package ConnectionDB;

import java.sql.*;
import javax.swing.table.AbstractTableModel;

public class FigureTableModel extends AbstractTableModel{ //��������� set model ��� JTable
		ResultSet rs; // rs ��ͧ�� scrollable ResultSet ������ method �ҧ����� first(), last()
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
			rs.absolute(r+1);//��㹡������͹ Record ��ѧ���˹觷���˹�
			return rs.getObject(c+1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;	
		}
	}
	public int getRowCount(){
		try {
			rs.last(); // rs ��ͧ�� scrollable ResultSet
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