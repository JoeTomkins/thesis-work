package def;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ResultsFile {
	private static ArrayList<Result> results;
	
	final static File filePath = new File("results.ser");
	
	
	public ResultsFile(){
		
		try{
			if (filePath.exists()){	 //file exists, there should be some stuff in there.	
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));			
				while(true){
					try{
						Result r = (Result) in.readObject();
						results.add(r);
					}
					catch(EOFException e){
						break;			
					}
				}	
				in.close();
			
			}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public static ArrayList<Result> getResults(){
		return results;
	}
	
	private static void writeResult(Result result){
		try{
			if(!filePath.exists()){
				//to allow us to append to file
				ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(filePath));
				os1.close();
			}
			//overwriting the object output stream required to append to the file using serialized objects -> otherwise you corrupt the stream
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath, true)) {
	            protected void writeStreamHeader() throws IOException {
	                reset(); //
	            }
	        };
	        out.writeObject(result);
	        out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static void addResult(Result result){
		results.add(result);
		writeResult(result);
	}
	
	
	
}
