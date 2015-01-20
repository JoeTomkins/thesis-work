package def;
/**
 *  Code designed to hold information on processes that have been created
 * @author Joe Tomkins
 *
 */

public class ProcessInfo {
	Algorithm algorithm;
	Dataset dataset;
	HadoopConfiguration hadConf;
	int exitStatus;
	
	public ProcessInfo(Algorithm alg, Dataset dataset, HadoopConfiguration hadoopConf, int exitStatus){
		this.algorithm = alg;
		this.dataset = dataset;
		this.hadConf = hadoopConf;
		this.exitStatus = exitStatus;
		
	}
	
	public Algorithm getAlgorithm(){
		return algorithm;
	}
	
	public Dataset getDataset(){
		return dataset;
	}
	
	public HadoopConfiguration getHadoopConfiguration(){
		return hadConf;
	}
	
	public void setAlgorithm(Algorithm a){
		algorithm =a;
	}
	
	public void setDataset(Dataset d){
		dataset = d;
	}
	
	public void setHadoopConfiguration(HadoopConfiguration hc){
		hadConf = hc;
	}
}
