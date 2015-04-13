package gui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import def.Dataset;
import def.DatasetGroup;
import def.FileSetUp;
import def.Main;
import def.ResourceHandler;

public class DandAPanel{
	
	private JPanel frontPanel;
	private JPanel datasetPanel;
	private JPanel algPanel;
	private JPanel datasetGroupPanel;
	private JPanel mainPanel;
	
	
	private JLabel datasetsL = new JLabel("Datasets");
	private JLabel datasetGroupL = new JLabel ("Dataset Group");
	private JLabel algorithmsL = new JLabel("Associated Algorithms");

	private JLabel distributedL = new JLabel("Distributed");
	
	private JCheckBox distributed = new JCheckBox();
	
	private JComboBox <String> datasetsBox = new JComboBox<String>();
	private JComboBox<String> algorithmBox = new JComboBox<String>();
	
	private JTextField datasetGroupT = new JTextField();
	
	private JButton prev = new JButton("<");
	private JButton next = new JButton(">");
	
	private ArrayList<DatasetGroup> datasetGroups;
	private int currentGroup;
	private int totalGroups;
	/**
	 * @param args
	 */
	public DandAPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
		datasetPanel = new DatasetPanel();
		algPanel = new AlgorithmPanel();
		datasetGroupPanel = new DatasetGroupPanel();
		frontPanel = frontPanel();
		

		
		
		
		
		mainPanel.add(frontPanel, "FRONTPANEL");
		mainPanel.add(datasetPanel, "DATASETPANEL");
		mainPanel.add(algPanel, "ALGPANEL");
		mainPanel.add(datasetGroupPanel, "DATASETGROUPPANEL");
	}

	
	
	
	
	public JPanel getPanel(){
		return mainPanel;
	}
	
	
	private JPanel frontPanel(){
		
		
		JPanel mainPanel = new JPanel();
		GroupLayout layout = new GroupLayout(mainPanel);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border paneEdge = BorderFactory.createTitledBorder(blackline, "Main Panel");
		mainPanel.setBorder(paneEdge);
		mainPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(datasetGroupL)
						.addComponent(datasetsL)
						.addComponent(algorithmsL)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(datasetGroupT)
						.addComponent(datasetsBox)
						.addComponent(algorithmBox)
						.addGroup(layout.createSequentialGroup()
								.addComponent(distributedL)
								.addComponent(distributed)						
						)
				)
				
				.addComponent(prev)
				.addComponent(next)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(datasetGroupL)
						.addComponent(datasetGroupT)
						.addComponent(prev)
						.addComponent(next)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(datasetsL)
						.addComponent(datasetsBox)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(algorithmsL)
						.addComponent(algorithmBox)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(distributedL)
						.addComponent(distributed)
						
				)
		);
		
		
		distributed.setEnabled(false);
		initialise();
		return mainPanel;
	}
	
	private void initialise(){
		datasetGroups = Main.getResourceHandler().getDatasetGroups();
		if (datasetGroups!=null){
			totalGroups = datasetGroups.size();
			if (totalGroups>0){
				currentGroup = 0;
				display(currentGroup);
			}
		}
	}
	
	private void display(int index){
		datasetGroupT.setText(datasetGroups.get(index).getName());
		int size = datasetGroups.get(index).getDatasets().size();
		String[] names = new String[size];
		if (size>0){
			for (int i =0; i< size; i++){
				names[i]=  String.valueOf(datasetGroups.get(index).getDatasets().get(i).getSize());
			}		
			datasetsBox = new JComboBox<String>(names);
			datasetsBox.setSelectedIndex(0);
			
			size = datasetGroups.get(index).getAssociatedAlgorithms().size();
			names = new String[size];
			for (int i =0; i< size; i++){
				names[i]=  String.valueOf(datasetGroups.get(index).getAssociatedAlgorithms().get(i).getName());
			}		
		}
	}
	
	private void algDist(int index){
		if (datasetGroups.get(currentGroup).getAssociatedAlgorithms().get(index).isDistributed()){
			distributed.setSelected(true);
		}
		else{
			distributed.setSelected(false);
		}		
	}
	
	
	
	private void addAlgorithm(){
		
	}
	
	private void addExistingDataset(){
		
	}
	
	private void addExistingAlgorithm(){
		
	}





	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
