package def;
import java.io.Serializable;
import java.util.Objects;


public class HadoopProperty implements Serializable{
	private float def;//default value
	private int file; //where 0 is HDFS, 1 is core, 2 is mapred
	private float highValue;
	private float lowValue;
	private String name;
	private boolean range;
	private float value;
	private static final long serialVersionUID = 1L;
	
	public HadoopProperty(String n, float v, float lowValue, float highValue, boolean range, int f){
		this.name=n;
		this.def = v;
		this.value =v;
		this.file = f;
		this.lowValue=lowValue;
		this.highValue=highValue;
		this.range=range;		
	}
	
	public HadoopProperty(String n, float v, float def, float lowValue, float highValue, boolean range, int f){
		this.name = n;
		this.value = v;
		this.lowValue = lowValue;
		this.highValue = highValue;
		this.range = range;
		this.file = f;
	}
	
	public HadoopProperty(String n, int v, int lowValue, int highValue, boolean range, int f){
		this.name=n;
		this.def = v;
		this.value =v;
		this.file = f;
		this.lowValue=lowValue;
		this.highValue=highValue;
		this.range=range;		
	}
	
	public HadoopProperty(String n, int v, int def, int lowValue, int highValue, boolean range, int f){
		this.name = n;
		this.value = v;
		this.lowValue = lowValue;
		this.highValue = highValue;
		this.range = range;
		this.file = f;
	}
	
	public String getDefault(){
		return Objects.toString(this.def);
	}

	public float getDefaultFloat(){
		return def;
	}
	
	public int getFile(){
		return file;
	}

	public float getHighValue(){
		return highValue;
	}
	
	public float getLowValue(){
		return lowValue;
	}
	
	public String getName() {
		return name;
	}

	public String getValue(){
		return Objects.toString(this.value);
	}
	
	public float getValueAsFloat() {
		return value;
	}
	
	public float getValueFloat(){
		return this.value;
	}
	
	public boolean isDefault(){
		if (value==def){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean range(){
		return range;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setToDefault(){
		this.value = def;
	}
	
	public void setValue(float value){
		this.value = value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public void setValue(String value) {
		try{
			this.value = Float.parseFloat(value);
		}catch(NumberFormatException e){
			String sub = value.substring(4, value.length()-1);
			this.value = Float.parseFloat(sub);
		}
	}
	
}
