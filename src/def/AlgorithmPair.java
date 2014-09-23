package def;

public class AlgorithmPair {
	private Algorithm distributed;
	private Algorithm nonDistributed;
	private String name;
	
	public AlgorithmPair(Algorithm a1, Algorithm a2, String name){
		this.distributed = a1;
		this.nonDistributed = a2;
		this.name = name;
	}
	
	public Algorithm getDistributedAlgorithm(){
		return distributed;
	}
	
	public Algorithm getNonDistributed(){
		return nonDistributed;
	}
	
	public String getName(){
		return name;
	}
}
