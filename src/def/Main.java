package def;

import gui.MainGui;

import java.util.ArrayList;



public class Main {
	private static ResourceHandler resourceHandler;
	
	public static void main(String[] args){
		HadoopConfigFile hc = FileSetUp.setUpHadoopConfig();
		
		
		resourceHandler = new ResourceHandler();
		
		MainGui.start();
		System.out.println();
		
	}

	public static ResourceHandler getResourceHandler(){
		return resourceHandler;
	}
	
	
	
	
	
	
}
