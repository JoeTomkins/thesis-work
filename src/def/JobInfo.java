package def;
import java.io.Serializable;



public class JobInfo implements Serializable{
	private Algorithm alg; // what algorithm was used
	//object contains the info of a job
	private HadoopConfiguration hc; //what hadoop configuration was used if any -> may be null if using non distributed
	private int numberComps;
	private SystemInfo[] si; // information on the system(s) used -> cluster, specs etc, if Arraysize>1 then its a cluster
	
	
	public JobInfo(HadoopConfiguration hc, long dataSetSize, Algorithm algorithm, SystemInfo[] si){
		this.hc=hc;		
		this.alg=algorithm;
		this.si=si;
		this.numberComps = si.length;
	}

	public Algorithm getAlg() {
		return alg;
	}

	public HadoopConfiguration getHc() {
		return hc;
	}

	public SystemInfo[] getSi() {
		return si;
	}

	public void setAlg(Algorithm alg) {
		this.alg = alg;
	}

	

	public void setHc(HadoopConfiguration hc) {
		this.hc = hc;
	}

	public void setSi(SystemInfo[] si) {
		this.si = si;
	}
	
	

}
