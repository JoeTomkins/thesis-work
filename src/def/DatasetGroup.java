package def;

import java.io.Serializable;
import java.util.ArrayList;

public class DatasetGroup implements Serializable{
	private ArrayList<Dataset> datasets;
	private ArrayList<Algorithm> associatedAlgorithms;
	private int id;
	private static int totalId;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public DatasetGroup(ArrayList<Dataset> dGroup, ArrayList<Algorithm> aGroup){
		datasets = dGroup;
		associatedAlgorithms = aGroup;
		this.id = totalId;
		name = dGroup.get(0).getName();
		totalId++;
	}
	
	
	public DatasetGroup(ArrayList<Dataset> dGroup){
		this.id = totalId;
		name = dGroup.get(0).getName();
		totalId++;
		datasets = dGroup;
		associatedAlgorithms = new ArrayList<Algorithm>();
	}
	
	public DatasetGroup(Dataset[] dGroup){
		this.id = totalId;
		if (dGroup.length>0){name = dGroup[0].getName();};
		totalId++;
		for(int i = 0; i < dGroup.length; i++){
			datasets.add(dGroup[i]);
		}
		associatedAlgorithms = new ArrayList<Algorithm>();
	}
	
	public DatasetGroup(Dataset[] dGroup, Algorithm[] aGroup){
		this.id = totalId;
		totalId++;
		if (dGroup.length>0){name = dGroup[0].getName();}
		for(int i = 0; i < dGroup.length; i++){
			datasets.add(dGroup[i]);
		}
		for(int i = 0; i < aGroup.length; i++){
			associatedAlgorithms.add(aGroup[i]);
		}		
	}
	
	public DatasetGroup(){ //dummy grouop
		this.id = totalId;
		System.out.println("id is " + this.id);
		totalId++;
		associatedAlgorithms = new ArrayList<Algorithm>();
		datasets = new ArrayList<Dataset>();
	}
	
	public void addAlgorithm(Algorithm alg){
		associatedAlgorithms.add(alg);
	}
	
	public void addDataset(Dataset dSet){
		datasets.add(dSet);
	}
	
	public ArrayList<Dataset> getDatasets(){
		return datasets;
	}
	
	public ArrayList<Algorithm> getAssociatedAlgorithms(){
		return associatedAlgorithms;
	}
	
	public void setDatasets(ArrayList<Dataset> dGroup){
		datasets = dGroup;		
	}
	
	public int getID(){
		return id;
	}
	
	public int getNumDatasets(){
		return totalId;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}
	
	public void removeDataset(int index){
		datasets.remove(index);
	}

}
