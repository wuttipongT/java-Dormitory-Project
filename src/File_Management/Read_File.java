package File_Management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Read_File {
	String filename ="data/register.txt";
	int max = filename.length();
	
	public String readFile(){
		String line = "";
		try {
			Scanner sc = new Scanner(new File(filename));
			while (sc.hasNext()) {
				line += sc.nextLine()+"\n";
			}
			return line;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String search(String keysearch,int type){
		String dataReturn="",line="";
		try {
			Scanner sc=new Scanner(new File(filename));
			while(sc.hasNext()){
				line=sc.nextLine();
				String data[]=line.split("/");
				if(data[type].compareTo(keysearch)==0){
					dataReturn=line;
					break;
				}
			}
			return dataReturn;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String[] checkRoom(){
		String data[] =  new String [NumOfLineInData(new File("data/register.txt"))];
		String line = "";
		try {
			Scanner sc = new Scanner(new File(filename));
			for(int i=0;i<data.length;i++){
				line=sc.nextLine();
				String d[]=line.split("/");
				data[i]=d[0];
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String[] checkRoom2(){
		String data[] =  new String [NumOfLineInData(new File("data/register.txt"))];
		String line = "";
		try {
			Scanner sc = new Scanner(new File(filename));
			for(int i=0;i<data.length;i++){
				line=sc.nextLine();
				String d[]=line.split("/");
				data[i]=line;
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int NumOfLineInData(File file){
		int num=0; String line="";
		try {
			Scanner sc=new Scanner(file);
			while (sc.hasNext()) {
				line=sc.nextLine();
				num++;
			}
			return num;
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		return 0;
	}
	public int getNumberOfLinesearch(String key,int type){//ค้นหาบรรทัด
		int num=0;
		String line="";
		int count=0;
		
		int allline= new Read_File().NumOfLineInData(new File(filename));
		try {
			Scanner sc=new Scanner(new File(filename));
			for(int i=0;i<allline;i++){
				line=sc.nextLine();
				String d[]=line.split("/");
				count++;
				if(d[type].equals(key)==true){
					num=count;
					break;
				}
			}
			return num;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
