package File_Management;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Write_File {
	String filename ="data/register.txt";
	public void writeFile(String data){
		try {

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename,true)));
			bw.write(data);
			bw.newLine();
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeFile2(String data){
		
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
			bw.write(data);
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int index){
		
		String Delete="";
		
		String data[]= new Read_File().checkRoom2();
		
		String copy[]=new String[data.length-1];
		
		for(int i=0;i<index;i++){
			copy[i]=data[i];
		}
		
		for(int j=index;j<copy.length;j++){
			copy[j]=data[j+1];
		}
		for(int i=0;i<copy.length;i++){
			Delete+=copy[i]+"\n";
		}
		
		new Write_File().writeFile2(Delete);
	}
}
