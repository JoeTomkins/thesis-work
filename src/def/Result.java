package def;
import java.io.Serializable;
import java.util.Calendar;
public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	private int resultNumber; //effectively Id
	private Algorithm algo;
	private Double cTime; //how much time taken
	private Dataset dataset;
	final String date;	
	private HadoopConfiguration hadoopConfig;
	
		public Result(HadoopConfiguration config, Algorithm algo, Dataset dataset, Double cTime){
			
			
			this.hadoopConfig=config;
			this.algo=algo;
			this.dataset=dataset;
			this.cTime=cTime;
			this.date = Calendar.getInstance().getTime().toString();
			
			Main.getResourceHandler().getCounter().addResult();			
			this.resultNumber = Main.getResourceHandler().getCounter().getResultsNumber();
	
			
		}
		
		public Result(Algorithm algo, Dataset dataset, Double cTime){
			this.hadoopConfig=null;
			this.algo=algo;
			this.dataset=dataset;
			this.cTime=cTime;
			this.date = Calendar.getInstance().getTime().toString();
			Main.getResourceHandler().getCounter().addResult();			
			this.resultNumber = Main.getResourceHandler().getCounter().getResultsNumber();
		}
		
		public HadoopConfiguration getHadoopConfiguration(){
			return this.hadoopConfig;
		}
	
		public Algorithm getAlgorithm(){
			return this.algo;
		}
		
		public Dataset getDataset(){
			return dataset;
		}
		
		public int getResultNumber(){
			return resultNumber;
		}		
		
		public Double getTime(){
			return cTime;
		}
		
		public void setTime(Double time){
			cTime = time;
		}
		
		public String getDate(){
			return date;
		}
		
		public int getResultID(){
			return this.resultNumber;
		}
		
		
}
