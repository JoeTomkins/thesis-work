package def;

import gui.MainGui;

import java.sql.Date;
import java.util.ArrayList;

public class Main {
	private static ResourceHandler resourceHandler;
	private static HadoopConfigFile hc;
	
	
	public static void main(String[] args){
		resourceHandler = new ResourceHandler();	
		hc = FileSetUp.setUpHadoopConfig();
		
		
			
		MainGui.start();
		
		//testFunction();
		
	}

	public static ResourceHandler getResourceHandler(){
		return resourceHandler;
	}
	
	public static HadoopConfigFile getHadoopConfig(){
		return hc;
	}
	
	public static void testFunction(){
		
		
	} 
	
	
	
	
}
