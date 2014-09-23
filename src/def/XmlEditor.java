package def;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

	public class XmlEditor {
		
		static String[][] readXmlFromFile(String file){
			Document doc;
			DocumentBuilderFactory dFac;
			DocumentBuilder docBuilder;
			NodeList properties;
			NodeList propElements;
			String[][] fileProp=null;
			int numElements;
			try{
				//get file paths object from file
				dFac = DocumentBuilderFactory.newInstance();
				docBuilder = dFac.newDocumentBuilder();
				//want to read from the 3 files - and take what properties they have, will resize the array as properties are added - could use arrayList
				doc = docBuilder.parse(file);
				properties= doc.getElementsByTagName("property"); //node list of all properties
				numElements=properties.getLength();
				
				 
				fileProp = new String[2][numElements];//where [0][n] are the names and [1][n] are the values
				
				
				for (int i=0; i < numElements; i++){
					propElements = properties.item(i).getChildNodes(); // get name/value of property (immediate children)
					fileProp[0][i]=propElements.item(1).getTextContent(); 
					fileProp[1][i]=propElements.item(3).getTextContent(); //0 and 4 are empty
					
					//adds all names and values to their respective lists.
				}										
			}catch (ParserConfigurationException pce){
				pce.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			return fileProp;
		}		
		private String[][] configList;
		
		
		
		private int totalNumElements;		
		
		
		
		
		
		public void add(String name, String value, String file){
			Document doc = null;
			DocumentBuilderFactory dFac;
			DocumentBuilder docBuilder = null;
			
			
			dFac = DocumentBuilderFactory.newInstance();
			try {
				docBuilder = dFac.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//want to read from the 3 files - and take what properties they have, will resize the array as properties are added - could use arrayList
			try {
				doc = docBuilder.parse(new File(file));
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			Element nm = doc.createElement("name");
			nm.setTextContent(name);
			Element val = doc.createElement("value");
			val.setTextContent(value);
			Element prop = doc.createElement("property");
			prop.appendChild(nm);
			prop.appendChild(val);
			doc.getDocumentElement().appendChild(prop);
			
			
			//add to list storing values
			String[][] tmpList = new String[2][totalNumElements+1];
			System.arraycopy(configList[0], 0, tmpList[0], 0, totalNumElements);
			System.arraycopy(configList[1], 0, tmpList[1], 0, totalNumElements);
			this.configList=tmpList;
			
			
			
			configList[0][totalNumElements]=name;
			configList[1][totalNumElements]=value;
			
			totalNumElements++;

			System.out.println("# elements is now :" +Integer.toString(totalNumElements));
			
			System.out.println("---");
			listAll();
			System.out.println("---");
			
			write(file);
		}
		
		
		
		public String[][] getConfig(){
			return configList;
		}
			
		public int indexOf(String property){
			for (int i=0; i<totalNumElements;i++){
				if (configList[0][i].equals(property)){
					return i;
				}
			}
			return -1;
		}
		
		
		
	/*
		public void modify(String){
			int pos=indexOf(prop);
			 if (pos!=-1){
				 if (configList[1][pos]!=value){
					 configList[1][pos]=value;
					 propElements.item(pos).setTextContent(value);
					 write(file);
				 }
			 }
		}
	*/
	
	
	
		public void listAll(){
			for (int i =0; i<totalNumElements; i++){
				System.out.println("name: " + configList[0][i] + "\tvalue: " + configList[1][i]);
			}
		}
		
		//function to verify parameter exists in XML file
		public boolean propExists(String property){
			for (int i=0; i< totalNumElements; i++){
				if (configList[0][i].equals(property)){
					return true;
				}
			}
			return false;
		}
		
		public void remove(String prop, String file){
			int index = indexOf(prop); // which node is it
			if (index!=-1){//exists -> we know which property is the one we've found -> 
				Document doc = null;
				DocumentBuilderFactory dFac;
				DocumentBuilder docBuilder = null;				
				
				dFac = DocumentBuilderFactory.newInstance();
				try {
					docBuilder = dFac.newDocumentBuilder();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//want to read from the 3 files - and take what properties they have, will resize the array as properties are added - could use arrayList
				try {
					doc = docBuilder.parse(new File(file));
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				NodeList nl = (NodeList) doc.getChildNodes().item(2);			
				
				for (int i=0; i<nl.getLength();i++){
						if (i==(1+index*2)){
							System.out.println("i is" +i +" "+nl.item(i).getTextContent());
							nl.item(i).getParentNode().removeChild(nl.item(i));
						}
				}
				
				//properties.item(index).removeChild(properties.item(index).getFirstChild());
				//properties.item(index).removeChild(properties.item(index).getFirstChild());
			}
			//need to update the two arrays with updated values -> resize without the array in the index
			String[][] tmpList = new String[2][totalNumElements-1];
			int i=0;
			int j=0;
			/*test
			 */
			 for (int y=0; y< totalNumElements; y++){
				 System.out.println("y:" +y + "  -  " + configList[0][y]);
			 }
			
			
			
			while(i<totalNumElements){
				if (i!=index){
					tmpList[0][j]=configList[0][j];
					tmpList[1][j]=configList[1][j];
					j++;
				}
				i++;
			}
			configList = tmpList;		
			totalNumElements--;
			
			write(file);
			
			System.out.println("now");
			for (int y=0; y< configList.length; y++){
				 System.out.println("y:" +y + "  -  " + configList[0][y]);
			 }
			
		}
		
		public void write(String filePath){
			Document doc = null;
			DocumentBuilderFactory dFac;
			DocumentBuilder docBuilder = null;
			
			
			dFac = DocumentBuilderFactory.newInstance();
			try {
				docBuilder = dFac.newDocumentBuilder();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//want to read from the 3 files - and take what properties they have, will resize the array as properties are added - could use arrayList
			try {
				doc = docBuilder.parse(new File(filePath));
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			try{				
				TransformerFactory tf = TransformerFactory.newInstance(); //allows you to create a transformer
				Transformer t = tf.newTransformer(); // creates a transformer - allows you to write an xml tree to file
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(filePath);
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				t.transform(source, result);
			}
			catch(TransformerException tfe){
				tfe.printStackTrace();
			}
		}
		
	}


