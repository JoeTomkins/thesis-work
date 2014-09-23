package def;
import java.io.Serializable;
import java.util.Calendar;

public class HadoopConfiguration implements Serializable{
	//part of core-default
	//String IOFileBufSize="4096"; // default 4096, is part of core-default - should be a multiple of 4096
	//String[][] parameters = {{"map.reduce.tasks", "io.sort.mb", "io.sort.record.percent", "mapreduce.task.io.sort.factor", "io.file.buffer.size"}, {"1", "100", "0.05", "10", "4096"}};
	HadoopProperty[] currentParameters;
	
	private static final long serialVersionUID = 1L;
	//part of mapred.default
	
	String date;
	
	//TODO -> change individual fields into an array-> make everything set.
	
	
	
	
	
	public HadoopConfiguration(HadoopProperty[] properties){
		currentParameters = properties;
		this.date = Calendar.getInstance().getTime().toString();
		
	}

	public HadoopConfiguration(){
		this.date = Calendar.getInstance().getTime().toString();
	}
	public void setParameters(HadoopProperty[] properties){
		currentParameters = properties;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getDefault(int index){
		return currentParameters[index].getDefault();
	}
	
	
	public String getName(int index){
		return currentParameters[index].getName();
	}
	
	public HadoopProperty getProperty(int index){
		return currentParameters[index];
	}
	
	public String getValue(int index){
		return currentParameters[index].getValue();
	}
	
	
	public String getValue(String name){
		for (int i=0; i< currentParameters.length; i++){
			if (name.equals(currentParameters[i].getName())){
				return currentParameters[i].getValue();
			}
		}
		return "";
	}
	
	public void printAll(){
		
		for (int i=0; i<currentParameters.length; i++){
			System.out.println(currentParameters[i].getName()+"\t value: "+currentParameters[i].getValue() +"\t default: " + currentParameters[i].getDefault());			
		}
	}
	
	public void setDate(String d){
		this.date = d;
	}
	
	public void setDefault(int index){
		currentParameters[index].setToDefault();
	}
	
	public void setDefault(String name){
		for (int i=0; i< currentParameters.length; i++){
			if (name.equals(currentParameters[i].getName())){
				currentParameters[i].setToDefault();
				break;
			}
		}
	}
	
	public void setDefaultAll(){ //use all default values
		currentParameters = HadoopConfigFile.createDefaultHadoopConfig();		
	}
	
	public void setValue(int index, String value){
		currentParameters[index].setValue(value);
	}
	
	public void setValue(String name, String value){
		for (int i=0; i< currentParameters.length; i++){
			if (name.equals(currentParameters[i].getName())){
				currentParameters[i].setValue(value);
				break;
			}
		}
	}
	

	
	
}
