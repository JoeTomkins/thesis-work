package def;
import java.io.Serializable;


public class HadoopFilePaths implements Serializable{
	private static final long serialVersionUID = 1L;
	private String coreSite;
	private String hdfsSite;
	private String mapredSite;
	
	HadoopFilePaths(String hSite, String cSite, String mSite){		
		hdfsSite=hSite;		
		coreSite=cSite;
		mapredSite=mSite;
	}

	public String getCoreSite() {
		return coreSite;
	}

	public String getHdfsSite() {
		return hdfsSite;
	}

	public String getMapredSite() {
		return mapredSite;
	}

	public void printAll(){
		System.out.println("hdfs: " + getHdfsSite() + "\n Core: "+getCoreSite() + "\nMapred: " + getMapredSite());
	}

	public void setCoreSite(String coreSite) {
		this.coreSite = coreSite;
	}

	public void setHdfsSite(String hdfsSite) {
		this.hdfsSite = hdfsSite;
	}
	
	public void setMapredSite(String mapredSite) {
		this.mapredSite = mapredSite;
	}
}
