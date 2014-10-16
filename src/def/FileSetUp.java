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

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/*
 * This class is responsible for checking that the required files are present so that they can be read from
 * If they are not present this class is responsible for creating them and, where necessary, getting the required info from the user.
 */


public class FileSetUp {
	static File mapredSite = null;
	static File hdfsSite = null;
	static File coreSite = null;
	static File hadConfFilePath = null; 
	
	
	
	public static HadoopConfigFile setUpHadoopConfig(){		
		File filePaths = new File("filePaths.ser");		
		
		if (filePaths.exists()){
			System.out.println("file exists: loading from file"+filePaths.getAbsolutePath());
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePaths));
				//test.fPaths = (filePaths) in.readObject();
				Main.getResourceHandler().setFPaths((HadoopFilePaths) in.readObject());				
				in.close();
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
		
		else{
			createHadoopFilepathFile(filePaths);
		}	
		
		//sort out next thing -> hadoop info
		File hadoopConfigFilepath = new File("hadoopConfig.ser");
		HadoopConfigFile hadoopConfigFile = new HadoopConfigFile(hadoopConfigFilepath);
		return hadoopConfigFile;
	}
	
	private static void createHadoopFilepathFile(File filePaths){
		
		// prompt user for filepaths using GUI for hadoop config filepaths		
		hadConfFilePath = getFilepath("please give the name of the Hadoop config directory", true);
		
		if (hadConfFilePath==null){
			System.out.println("please choose a directory");
			createHadoopFilepathFile(filePaths);
		}
		else{
			
			//ensure the folder is the correct folder and contains the necessary files.
			mapredSite = new File(hadConfFilePath.getAbsolutePath()+"/mapred-site.xml");
			coreSite = new File(hadConfFilePath.getAbsolutePath()+"/core-site.xml");
			hdfsSite = new File(hadConfFilePath.getAbsolutePath()+"/hdfs-site.xml");
			if (!mapredSite.exists() || !coreSite.exists() || !hdfsSite.exists()){
				System.out.println("Directory chosen is invalid");
				
				createHadoopFilepathFile(filePaths);
			}
		}
		System.out.println("creating file");
		
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePaths));
			Main.getResourceHandler().setFPaths(new HadoopFilePaths(hdfsSite.getAbsolutePath(), coreSite.getAbsolutePath(), mapredSite.getAbsolutePath()));			
			out.writeObject(Main.getResourceHandler().getFPaths());			
			out.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static File getFilepath(String message, boolean directory){
		File filePath = null;
		// TODO -> use fileBrowser, create class to do so.
		//Scanner input = new Scanner(System.in);
		//System.out.println("enter FilePath for yadda");		
		JFileChooser choice = new JFileChooser();
		if (directory){
			choice.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		choice.setDialogTitle(message);
		JFrame frame = new JFrame();
		int val = choice.showOpenDialog(frame);
		
		if (val == JFileChooser.APPROVE_OPTION){
			filePath = choice.getSelectedFile();
		}		
		return filePath;
	}
	
	public static File[] getFiles(String message){
		System.out.println("hm");
		File[] files = null;
		JFileChooser choice = new JFileChooser();
		choice.setMultiSelectionEnabled(true);
		choice.setDialogTitle(message);
		JFrame frame = new JFrame();
		int val = choice.showOpenDialog(frame);
		if (val == JFileChooser.APPROVE_OPTION){
			files = choice.getSelectedFiles();		
		}
		return files;
	}
	
	public static ArrayList getObjectListFromFile(File filePath){
		ArrayList object;
		try{
			if (filePath.exists()){	 //file exists, there should be some stuff in there.	
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));			
				while(true){
					try{
						
						object = (ArrayList) in.readObject();
					}
					catch(EOFException e){
						break;			
					}
					return object;
				}	
				in.close();			
			}
			
		} 
		catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		object = new ArrayList();
		return object;
	}
	
	public static Counter getCounterFromFile(File filePath){
		Counter counter = new Counter();
		try{
			if (filePath.exists()){	 //file exists, there should be some stuff in there.	
				
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));			
				counter = (Counter) in.readObject();
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
		
		return counter;
	}
	
	
	public static Object getObjectFromFile(File filePath){
		Object o = new Object();
		try{
			if (filePath.exists()){	//file exists, there should be some stuff in there.
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));	
				o = in.readObject();
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
		return o;
	}
	
	public static void appendResultToFile(File filePath, Object object){
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
	        out.writeObject(object);
	        out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	public static void writeToFile(File filePath, Object object){
		try{			
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
				out.writeObject(object);				
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