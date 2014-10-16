package def;

import java.io.Serializable;
import java.util.ArrayList;

public class DatasetGroup implements Serializable{
	private ArrayList<Dataset> datasets;
	private ArrayList<Algorithm> associatedAlgorithms;
	private int id;
	private transient int totalId;
	private String name;
	private static final long serialVersionUID = 2L;
	
	public DatasetGroup(ArrayList<Dataset> dGroup, ArrayList<Algorithm> aGroup){
		datasets = dGroup;		
		associatedAlgorithms = aGroup;
		Main.getResourceHandler().getCounter().addDatasetGroup();		
		id = Main.getResourceHandler().getCounter().getDatasetGroupsNumber();
		name = dGroup.get(0).getName();		
	}
	
	
	
	public DatasetGroup(ArrayList<Dataset> dGroup){
		Main.getResourceHandler().getCounter().addDatasetGroup();		
		id = Main.getResourceHandler().getCounter().getDatasetGroupsNumber();
		name = dGroup.get(0).getName();
		datasets = dGroup;
		associatedAlgorithms = new ArrayList<Algorithm>();
	}
	
	public DatasetGroup(Dataset[] dGroup){
		Main.getResourceHandler().getCounter().addDatasetGroup();		
		id = Main.getResourceHandler().getCounter().getDatasetGroupsNumber();
		if (dGroup.length>0){
			name = dGroup[0].getName();
		}
		
		for(int i = 0; i < dGroup.length; i++){
			datasets.add(dGroup[i]);
		}
		associatedAlgorithms = new ArrayList<Algorithm>();
	}
	
	public DatasetGroup(Dataset[] dGroup, Algorithm[] aGroup){
		Main.getResourceHandler().getCounter().addDatasetGroup();		
		id = Main.getResourceHandler().getCounter().getDatasetGroupsNumber();
		
		if (dGroup.length>0){
			name = dGroup[0].getName();
		}
		for(int i = 0; i < dGroup.length; i++){
			datasets.add(dGroup[i]);
		}
		for(int i = 0; i < aGroup.length; i++){
			associatedAlgorithms.add(aGroup[i]);
		}		
	}
	
	public DatasetGroup(){ //dummy group
		System.out.println("total IDs are = " +totalId);
		associatedAlgorithms = new ArrayList<Algorithm>();
		datasets = new ArrayList<Dataset>();
	}
	
	public void saveDatasetGroup(){
		Main.getResourceHandler().getCounter().addDatasetGroup();		
		id = Main.getResourceHandler().getCounter().getDatasetGroupsNumber();
		System.out.println("id is " + this.id);
		
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
		if (id != 0 ){
			return id;
		}
		else{			
			return (totalId);
		}
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
