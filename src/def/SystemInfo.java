package def;

public class SystemInfo {
	private long clockSpeed; // speed of processor -> 
	private int cores; // how many cores
	//class contains rough system info.
	private String name;
	private String os=""; //operating system
	private String processor; // name of processor
	private long ram;
	
	
	public SystemInfo(String name, long ram, int cores, String processor, long clockSpeed){
		this.name = name;
		this.ram = ram;
		this.cores = cores;
		this.processor=processor;
		this.clockSpeed=clockSpeed;
		this.os="";
	}
	
	public SystemInfo(String name, long ram, int cores, String processor, long clockSpeed, String os){
		this.name = name;
		this.ram = ram;
		this.cores = cores;
		this.processor=processor;
		this.clockSpeed=clockSpeed;
		this.os = os;
	}
	
	public long getClockSpeed() {
		return clockSpeed;
	}

	public int getCores() {
		return cores;
	}

	public String getName() {
		return name;
	}

	public String getOs() {
		return os;
	}

	public String getProcessor() {
		return processor;
	}

	public long getRam() {
		return ram;
	}

	public void setClockSpeed(long clockSpeed) {
		this.clockSpeed = clockSpeed;
	}

	public void setCores(int cores) {
		this.cores = cores;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public void setRam(long ram) {
		this.ram = ram;
	}

	
	
	
}
