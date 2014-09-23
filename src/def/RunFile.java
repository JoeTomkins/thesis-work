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


//Class contains info on Algorithms and Datasets to be used when running processes. 
public class RunFile {
	private static ArrayList<Algorithm> algorithmList;
	private static ArrayList<Dataset> datasetList;
	
	
	final static File aFilePath = new File("algorithms.ser");
	final static File dFilePath = new File("datasets.ser");
	
	public RunFile(){		
		try{
			if (aFilePath.exists()){	 //file exists, there should be some stuff in there.	
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(aFilePath));			
				while(true){
					try{
						Algorithm a = (Algorithm) in.readObject();
						algorithmList.add(a);
					}
					catch(EOFException e){
						break;			
					}
				}	
				in.close();
			
			}
			if (dFilePath.exists()){
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(dFilePath));			
				while(true){
					try{
						Dataset d = (Dataset) in.readObject();
						datasetList.add(d);
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
	
	public static ArrayList<Algorithm> getAlgoList(){
		return algorithmList;
	}
	
	public static ArrayList<Dataset> getDatasetList(){
		return datasetList;
	}
	
	public Algorithm getAlgorithm(int number){
		for (int i=0; i<algorithmList.size(); i++){
			if (number == algorithmList.get(i).getAlgorithmID()){
				return algorithmList.get(i);
			}
		}
		return null;
	}
		
	
	public ArrayList<Dataset> getDataset(String name){
		ArrayList<Dataset> list = new ArrayList<Dataset>();
		for (int i=0; i<datasetList.size(); i++){
			if (name.equals(datasetList.get(i).getName())){
				list.add(datasetList.get(i));
			}
		}
		return list;
	}
	
	public void addDataset(Dataset dataset){
		datasetList.add(dataset);
		writeDataset(dataset);
	}
	
	private void writeDataset(Dataset dataset){
		try{
			if(!dFilePath.exists()){
				//to allow us to append to file
				ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(dFilePath));
				os1.close();
			}
			//overwriting the object output stream required to append to the file using serialized objects -> otherwise you corrupt the stream
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dFilePath, true)) {
	            protected void writeStreamHeader() throws IOException {
	                reset(); //
	            }
	        };
	        out.writeObject(dataset);
	        out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void addAlgorithm(Algorithm algorithm){
		algorithmList.add(algorithm);
		writeAlgorithm(algorithm);
	}

	private void writeAlgorithm(Algorithm algorithm){
		try{
			if(!dFilePath.exists()){
				//to allow us to append to file
				ObjectOutputStream os1 = new ObjectOutputStream(new FileOutputStream(aFilePath));
				os1.close();
			}
			//overwriting the object output stream required to append to the file using serialized objects -> otherwise you corrupt the stream
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(aFilePath, true)) {
	            protected void writeStreamHeader() throws IOException {
	                reset(); //
	            }
	        };
	        out.writeObject(algorithm);
	        out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
