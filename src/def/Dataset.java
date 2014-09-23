package def;

import java.io.File;
import java.io.Serializable;

public class Dataset implements Serializable{
	private String name;
	private String filePath;
	private double size;
	
	private static final long serialVersionUID = 1L;
	
	public Dataset(String name, String filePath, double size){
		this.name = name;
		this.filePath=filePath;
		this.size = size;		
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getFilepath(){
		return filePath;
	}
	
	public double getSize(){
		return size;
	}
	
	
	
	
}
