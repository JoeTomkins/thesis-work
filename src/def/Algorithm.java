package def;

import java.io.Serializable;
import java.util.ArrayList;

public class Algorithm implements Serializable{
	private String name;
	private boolean distributed;
	private String filePath;
	private transient int numAlgorithms;
	private int algorithmID;
	private static final long serialVersionUID = 3L;
	private ArrayList<Parameter> arguments=new ArrayList<Parameter>();
	
	
	public Algorithm(String name, boolean distributed, String filePath, ArrayList argumentList){
		this.name=name;
		this.distributed = distributed;
		this.filePath = filePath;
		Main.getResourceHandler().getCounter().addAlgorithm();
		algorithmID = Main.getResourceHandler().getCounter().getAlgNumber();
		
		this.arguments = argumentList;
	}
	public Algorithm(String name, boolean distributed, String filePath){
		this.name=name;
		this.distributed = distributed;
		this.filePath = filePath;
		Main.getResourceHandler().getCounter().addAlgorithm();
		algorithmID = Main.getResourceHandler().getCounter().getAlgNumber();
	}
	
	public Algorithm(String filePath){
		this.filePath = filePath;
	}
	
	public void finalise(String name, boolean distributed, String filePath){
		this.name = name;
		this.distributed = distributed;
		this.filePath = filePath;
		Main.getResourceHandler().getCounter().addAlgorithm();
		algorithmID = Main.getResourceHandler().getCounter().getAlgNumber();
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public boolean isDistributed(){
		return distributed;
	}
	
	public void setDistributed(boolean isDistributed){
		distributed = isDistributed;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String fp){
		filePath = fp;
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
	
	public void swapParameter(int indexA, int indexB){
		if (indexA < arguments.size() && indexB < arguments.size()){
			Parameter temp = arguments.get(indexA);
			arguments.set(indexA, arguments.get(indexB));
			arguments.set(indexB, temp);
		}
	}
	
}
