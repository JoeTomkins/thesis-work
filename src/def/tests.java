package def;

import java.util.ArrayList;


public class tests {
	public tests(){
		// do stuff to test program
		
		
	}
	
	public static void createTestStuff(){
		Algorithm a1 = new Algorithm("test1", false, "filepath1");
		Algorithm a2 = new Algorithm("test2", true, "filepath2");
		Algorithm a3 = new Algorithm("test3", true, "filepath3");
		
		Dataset d1a = new Dataset("d1", "filepath2", 231);
		Dataset d1b = new Dataset("d1", "filepath2b", 432);
		
		Dataset d2a = new Dataset("d2", "filepathblah", 111);
		Dataset d2b = new Dataset("d2", "filepatht", 222);
		Dataset d3b = new Dataset("d2", "filepatht", 22252);
		Dataset d4b = new Dataset("d2", "filepatht", 51222);
		
		ArrayList<Dataset> d1g = new ArrayList<Dataset>();
		d1g.add(d1a);
		d1g.add(d1b);
		ArrayList<Dataset> d2g = new ArrayList<Dataset>();
		d2g.add(d2a);
		d2g.add(d2b);
		d2g.add(d3b);
		d2g.add(d4b);
		
		ArrayList<Algorithm> a1g = new ArrayList<Algorithm>();
		a1g.add(a1);
		a1g.add(a2);
		
		ArrayList<Algorithm> a2g = new ArrayList<Algorithm>();
		a2g.add(a3);
		
		DatasetGroup groupa = new DatasetGroup(d1g, a1g );
		DatasetGroup groupb = new DatasetGroup(d2g, a2g);
		
		ArrayList<DatasetGroup> dg1 = new ArrayList<DatasetGroup>();
		dg1.add(groupa);
		dg1.add(groupb);
		
		//Main.getResourceHandler().setDatasetGroups(dg1);
		
		
	}
}
