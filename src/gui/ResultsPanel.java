package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import def.Result;
import def.ResultsFile;


public class ResultsPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private TableColumnModel model;
	private JPanel algPanel;
	private JPanel dataPanel;
	private Map hCols;
	
	ResultsPanel(){
		String[] columnNames = {"Number", "Date", "Algorithm","Is Distributed","DataSet Name","DataSet Size", "HadoopConfiguration Info"};
		String[][] sampleData = {
								{"Number", "Date", "Algorithm","Is Distributed","DataSet Name","DataSet Size", "HadoopConfiguration Info"},
								{"Number", "Date", "Algorithm","Is Distributed","DataSet Name","DataSet Size", "HadoopConfiguration Info"}
		
		};
		
		//Object[][] sampleData = getResults();
		
		table = new JTable(sampleData, columnNames){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		//table.setFillsViewportHeight(true);
		//table.setAutoResizeMode(JTable.AUTO_REhisSIZE_OFF);
		model = table.getColumnModel();
		hCols = new HashMap();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel checkBoxPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //add table to scroll Pane
		
		
		
		for(int i = 0; i< columnNames.length; i++){
			JCheckBox cBox = new JCheckBox(columnNames[i]);
			cBox.setSelected(true);
			cBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					JCheckBox cb = (JCheckBox) e.getSource();
					if (cb.isSelected()){
						showColumn(cb.getText());
					}
					else{
						hideColumn(cb.getText());
					}
				}
			});
			checkBoxPanel.add(cBox);
		}
		
		add(scrollPane);
		add(checkBoxPanel);		
	}
	
	
	public void hideColumn(String cName){
		int index = model.getColumnIndex(cName); //index of column we want to hide
		TableColumn c = model.getColumn(index);  // get that column
		hCols.put(cName, c ); // put it into our map of colums to be hidden
		hCols.put(":" + cName,  new Integer(index));
		model.removeColumn(c);
	}
	
	public void showColumn(String cName){
		Object col = hCols.remove(cName);  //remove column from list of hidden columns
		if (col==null){
			return;
		}		
		model.addColumn((TableColumn) col); // add column to the table again
		
		col = hCols.remove(":" + cName);
		if (col==null){
			return;
		}
		
		int columnPos = ((Integer) col).intValue(); // get position of column
		int numCols = model.getColumnCount() -1;
		
		if (columnPos < numCols){
			model.moveColumn(numCols, columnPos); 
			// crude way of maintaining some kind of order when removing/replacing columns
			// problematic if columns are removed after another has been removed due to changes in index in 
			// what is currently portrayed
			// might be better to simply set width as 0, when hiding and autosize when displaying?
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private Object[][] getResults(){
		ArrayList<Result> results = ResultsFile.getResults();
		
		Object[][] info = new Object[results.size()][7]; // 0-6 will be String, but 7 will be a button which will link to the Hadoop Config information
		for (int i=0; i< results.size(); i++){
			info[i][0] = results.get(i).getResultNumber();
			info[i][1] = results.get(i).getDate();
			info[i][2] = results.get(i).getAlgorithm().getName();
			info[i][3] = results.get(i).getAlgorithm().isDistributed();
			info[i][4] = results.get(i).getDataset().getName();
			info[i][5] = results.get(i).getDataset().getSize();
			if (results.get(i).getHadoopConfiguration() ==null){		
				info[i][6]="";
			}
			else{
				JButton HadoopConfigInfo = new JButton("Hadoop Config Info");
				HadoopConfigInfo.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// give info? in pop up box? Extend Table?
					}
				});
				
				info[i][6] = HadoopConfigInfo;
			}
			
		}	
		return info;
	}

}
