package def;

import java.io.Serializable;
import java.util.ArrayList;

public class Algorithm implements Serializable{
	private String name;
	private boolean distributed;
	private String filePath;
	private static int numAlgorithms;
	private int algorithmID;
	private static final long serialVersionUID = 1L;
	private ArrayList<Parameter> arguments=new ArrayList<Parameter>();
	
	
	public Algorithm(String name, boolean distributed, String filePath, ArrayList argumentList){
		this.name=name;
		this.distributed = distributed;
		this.filePath = filePath;
		algorithmID=numAlgorithms;
		numAlgorithms++;
		this.arguments = argumentList;
	}
	public Algorithm(String name, boolean distributed, String filePath){
		this.name=name;
		this.distributed = distributed;
		this.filePath = filePath;
		algorithmID=numAlgorithms;
		numAlgorithms++;
	}
	
	public Algorithm(String filePath){
		this.filePath = filePath;
	}

	public String getName(){
		return name;
	}
	
	public boolean isDistributed(){
		return distributed;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public int getNumAlgorithms(){
		return numAlgorithms;
	}
	
	public int getAlgorithmID(){
		return algorithmID;
	}
	
	public ArrayList<Parameter> getArguments(){
		return arguments;
	}
	
	public Parameter getParameter(int index){
		return arguments.get(index);
	}
	
	public void addArgument(Parameter p){
		if (arguments==null){
			arguments=new ArrayList<Parameter>();
			System.out.println("blah");
		}
		arguments.add(p);
	}
	
	public void removeArgument(int i){
		arguments.remove(i);
	}
	
}
