package def;

public class ProcessInfo {
	Algorithm algorithm;
	Dataset dataset;
	HadoopConfiguration hadConf;
	
	public ProcessInfo(){
		
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
