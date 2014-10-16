package def;

import java.io.File;
import java.util.ArrayList;

public class ResourceHandler {
	private static HadoopFilePaths fPaths; // contains filepaths to each other directories needed by the program
	public static ResultsFile rPaths;
	private static Counter counter;
	private static ArrayList datasetGroups = new ArrayList();
	private static ArrayList algorithms = new ArrayList();
	private static ArrayList results = new ArrayList();
	private static ArrayList datasets = new ArrayList();
	
	private static File datasetGroupFp = new File("datasetGroup.ser");
	private static File datasetsFp = new File("datasets.ser");
	private static File resultsFp = new File("results.ser");
	private static File algorithmsFp = new File("algorithms.ser");
	private static File counterFp = new File("counter.ser");
	
	public ResourceHandler(){
		loadFromFiles();//initialises resources -> interacts with file handlers -
		
	}
	
	
	private void loadFromFiles(){
		datasetGroups = (ArrayList) FileSetUp.getObjectListFromFile(datasetGroupFp);
		datasets = (ArrayList) FileSetUp.getObjectListFromFile(datasetsFp);
		results = (ArrayList) FileSetUp.getObjectListFromFile(resultsFp);
		algorithms = (ArrayList) FileSetUp.getObjectListFromFile(algorithmsFp);
		counter = FileSetUp.getCounterFromFile(counterFp);
	}
	
	
	public static void setDatasetGroups(ArrayList dGroups){
		System.out.println("--------------------setting datasetGroups");
		datasetGroups= dGroups;
	}
	public static void setAlgs(ArrayList<Algorithm> aGroups){
		algorithms = aGroups;
	}
	public static void setResults(ArrayList<Result> rGroups){
		results = rGroups;
	}
	
	public static void setDatasets(ArrayList<Dataset> dGroups){
		datasets = dGroups;
	}
	
	public static void addDatasetToGroup(int index, Dataset d){
		
		((DatasetGroup) datasetGroups.get(index)).addDataset(d);
		writeDatasetGroups();
	}
	
	public static ArrayList getDatasetGroups(){
		return datasetGroups;
	}
	
	public static ArrayList getDatasets(){
		return datasets;
	}
	
	public static  ArrayList getAlgorithms(){
		return algorithms;
	}
	
	public static ArrayList getResults(){
		return results;
	}
	
	public static void addResult(Result r){
		results.add(r);
		FileSetUp.writeToFile(resultsFp, results);
	}
	
	public static void addDataset(Dataset d){
		datasets.add(d);
		FileSetUp.writeToFile(datasetsFp, datasets);
	}
	
	
	
	public static void addDatasetGroup(DatasetGroup d){
		datasetGroups.add(d);
		FileSetUp.writeToFile(datasetGroupFp, datasetGroups);
	}
	
	public static void addAlgorithm(Algorithm a){
		algorithms.add(a);
		FileSetUp.writeToFile(algorithmsFp, algorithms);
	}	
	
	public static void setFPaths(HadoopFilePaths h){
		fPaths = h;
	}
	
	public Counter getCounter(){
		return counter;
	}
	
	public void writeCounter(){
		FileSetUp.writeToFile(counterFp, counter);
	}
	
	
	public static HadoopFilePaths getFPaths(){
		return fPaths;
	}
	
	public static void removeDataset(int index){
		datasets.remove(index);
		FileSetUp.writeToFile(datasetsFp, datasets);
	}
	
	public static void removeDatasetFromGroup(int indexD, int indexG){
		((DatasetGroup) datasetGroups.get(indexG)).removeDataset(indexD);
		FileSetUp.writeToFile(datasetGroupFp, datasetGroups);
	}
	
	
	public static void removeDatasetGroup(int index){
		datasetGroups.remove(index);
		FileSetUp.writeToFile(datasetGroupFp, datasetGroups);
	}
	
	public static void removeAlgorithm(int index){
		algorithms.remove(index);
		FileSetUp.writeToFile(algorithmsFp, algorithms);
	}
	
	public static void removeResult(int index){
		results.remove(index);
		FileSetUp.writeToFile(resultsFp, results);
	}
	
	public static void writeDatasetGroups(){
		FileSetUp.writeToFile(datasetGroupFp, datasetGroups);
	}
	
	public static void writeAlgorithms(){
		FileSetUp.writeToFile(algorithmsFp, algorithms);
	}
	
	public static void writeDatasets(){
		FileSetUp.writeToFile(datasetsFp, datasets);
	}
	
	public static void writeResults(){
		FileSetUp.writeToFile(resultsFp, results);
	}
	
	
	
	
}
