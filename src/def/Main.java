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
		//testFunction();
		
	}

	public static ResourceHandler getResourceHandler(){
		return resourceHandler;
	}
	
	public static void testFunction(){
		System.out.println("Number of DatasetGroups = " + resourceHandler.getDatasetGroups().size());
		if (resourceHandler.getDatasetGroups().size()>0)System.out.println("totalIds = " + ((DatasetGroup) resourceHandler.getDatasetGroups().get(0)).getNumDatasets());
		
		System.out.println("NumAlgorithms = " +  ((Algorithm) ( Main.getResourceHandler().getAlgorithms()).get(0)).getNumAlgorithms());
		System.out.println("Number of Algorithms = " + Main.getResourceHandler().getAlgorithms().size());
		for (int i = 0; i<Main.getResourceHandler().getAlgorithms().size(); i++){
			System.out.println("algorithm #"+ i);
			for (int j=0; j< ((Algorithm) Main.getResourceHandler().getAlgorithms().get(i)).getArguments().size(); j++){
				System.out.println("argument# " + j);
			}
		}
		
	} 
	
	
	
	
}
