package def;

import java.io.File;
import java.util.ArrayList;



/*
 * Class relates to methods and attributes that are related to the setting up of the hadoopConfiguration object, 
 * through interacting with the XML Editor class and the currentConfig object
 
 Also responsible for reading the currentConfig from file, and writing it to file.
 */




public class HadoopConfigFile {
	private static HadoopConfiguration currentConfig;
	private static boolean fileExists = false;
	private static File filePath; // filePath to file containing the configuration info
	
	
	//Constructor 
	
	public HadoopConfigFile(File fP){
		filePath = fP;		
	
		if (filePath.exists()){
			fileExists=true;
			System.out.println("fp is : "+fP+ ": "+filePath.exists());
			
			currentConfig = (HadoopConfiguration) FileSetUp.getObjectFromFile(filePath);
			
			
			if (currentConfig == null){	
				System.out.println("current hadoop config is null");
			}
		}
		
		else{
			currentConfig = new HadoopConfiguration(createDefaultHadoopConfig());
			currentConfig.printAll();
			System.out.println("updating");
			currentConfig = new HadoopConfiguration(createDefaultHadoopConfig());			
			String[][] hdfsProperties = XmlEditor.readXmlFromFile(Main.getResourceHandler().getFPaths().getHdfsSite());
			updateConfigHDFS(hdfsProperties);
			String[][] mapReduceProperties = XmlEditor.readXmlFromFile(Main.getResourceHandler().getFPaths().getMapredSite());
			updateConfigMapred(mapReduceProperties);
			String[][] coreProperties = XmlEditor.readXmlFromFile(Main.getResourceHandler().getFPaths().getCoreSite());
			updateConfigCore(coreProperties);
			//compare properties from xml files to properties we're looking for
			//create file 
			System.out.println("Fp is " + fP);
			currentConfig.printAll();
			FileSetUp.writeToFile(fP, currentConfig);
		}
		
		
	}
	
	//Returns array of default hadoop properties
	
	static HadoopProperty[] createDefaultHadoopConfig(){
		HadoopProperty[] currentParameters = new HadoopProperty[12];
		currentParameters[0]= new HadoopProperty("mapred.reduce.tasks",1,5,300, true, 2); //default 1, part of mapred-core.site
		currentParameters[1]= new HadoopProperty("io.sort.mb", 100, 100, 200, true, 2);  //default 100
		currentParameters[2]= new HadoopProperty("io.sort.record.percent", .05f, 0.05f, 0.15f, true, 2); // default 0.05
		currentParameters[3]= new HadoopProperty("io.sort.factor",10, 10, 500, true, 2); // default 10		
		currentParameters[4] = new HadoopProperty("mapred.child.java.opts", 200, 200, 300, true, 2 ); // needs to be edited - -Xmx before, m after
		currentParameters[5] = new HadoopProperty("mapred.job.shuffle.input.buffer.percent",0.7f, 0.7f, 0.8f, false, 2);
		currentParameters[6] = new HadoopProperty("mapred.job.shuffle.merge.percent", 0.66f, 0.66f, 0.8f, false, 2);
		currentParameters[7] = new HadoopProperty("mapred.inmem.merge.threshold", 1000,0,0, false, 2);
		currentParameters[8] = new HadoopProperty("mapred.job.reduce.input.buffer.percent", 0,0, 0.8f, false, 2);
		
		currentParameters[9] = new HadoopProperty("dfs.replication", 3, 2, 3, false, 0);
		currentParameters[10] = new HadoopProperty("dfs.block.size", 67108864, 67108864, 134217728, false, 0);
		
		currentParameters[11] = new HadoopProperty("io.file.buffer.size", 4096, 4096, 32768, false, 1); // default is 2
		return currentParameters;		
	}
	
	public static HadoopConfiguration getCurrentConfig(){
		return currentConfig;
	}
	
	public static String getFilePath(){
		return filePath.getName();
	}
	
	
	public static void setConfiguration(HadoopConfiguration hc){
		currentConfig = hc;
		writeConfig(currentConfig);
	}
	
	public static void setFilePath(String file){
		filePath = new File(file);
	}
	
	private static void writeConfig(HadoopConfiguration hc){
		if (fileExists){
			FileSetUp.writeToFile(filePath, currentConfig);
		}
		else{
			System.out.println("filepath must exist");
		}
	}	
	
	public HadoopConfiguration getConfig(){
		return currentConfig;
	}
	
	private void updateConfigCore(String[][] properties){
		
			for (int j = 0; j< properties[1].length; j++){				
				if (properties[0][j].equals("io.file.buffer.size")){
					currentConfig.setValue(11, properties[1][j]);
					
					break;
				}
			}
	}
	
	private void updateConfigHDFS(String[][] properties){
		for (int i = 9; i<11; i++){
			for (int j = 0; j< properties[1].length; j++){
				
				
				if (properties[0][j].equals(currentConfig.getName(i))){
					currentConfig.setValue(i, properties[1][j]);
					
					break;
				}
			}
		}
	}
	
	private void updateConfigMapred(String[][] properties){
		for (int i = 0; i<9; i++){
			for (int j = 0; j< properties[1].length; j++){
				
				
				if (properties[0][j].equals(currentConfig.getName(i))){
					currentConfig.setValue(i, properties[1][j]);
					
					break;
				}
			}
		}
	}
}
	
	
	


