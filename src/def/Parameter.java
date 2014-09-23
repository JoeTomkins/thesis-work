package def;

import java.io.Serializable;

public class Parameter implements Serializable{
	private String name;
	private String value;
	private String prefix; // e.g characters that identify the argument.
	private String description;

	private static final long serialVersionUID = 1L;
	
	
	public Parameter(String n, String p, String v, String d){
		this.name =n;
		this.value = v;
		this.prefix = p;
		this.description=d;
	}
	
	public Parameter(String n, String p, String d){
		this.name = n;
		this.prefix = p;
		this.description = d;
	}
	

	
	public Parameter(String n){
		this.name = n;
		this.prefix = "";
		this.value = "";
		this.description = "";
	}
	
	public String getName(){
		return name;
	}	
	
	public String getValue(){
		return value;
	}
	
	public String getPrefix(){
		return prefix;
	}
	
	public void setPrefix(String p){
		this.prefix = p;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String d){
		description = d;
	}
	
	public void setValue(String v){
		value = v;
	}
}
