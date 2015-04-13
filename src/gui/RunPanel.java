package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import def.Algorithm;
import def.Dataset;


public class RunPanel implements ActionListener{
	final int extraWindowWidth = 100;
	private static JPanel cards; //card panel to hold other panels
	private JPanel runPanel;
	private JPanel algPanel;
	private JPanel dataPanel;
	private static Algorithm currentAlgorithm=null;
	private static Dataset currentDataset=null;
	
	
	//JPANEL fields
	private JLabel algorithmL = new JLabel("Algorithm:");
	private JLabel distL = new JLabel("Distributed");
	private JLabel results = new JLabel("Results");
	private JCheckBox dBox = new JCheckBox();
	private JLabel datasetL = new JLabel("Dataset:");
	private JLabel hadoopConfigurationL = new JLabel("Hadoop Configuration");
	private JTextField algorithmT = new JTextField();
	private JTextField datasetT  = new JTextField();;
	private JTextField hadoopConfigurationT  = new JTextField();;
	private JButton algorithmB = new JButton("Choose");
	private JButton hadoopConfigurationB = new JButton("Select");
	private JButton runButton = new JButton("Run");
	
	
	//ALGPANEL fields
	
	
	RunPanel(){
		
		
		cards = new JPanel(new CardLayout());
						
		//mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		runPanel = new JPanel();
		createRunPanel(runPanel);
		
		
		algorithmB.addActionListener(this);
		algPanel = new AlgorithmPanel();
		dataPanel = new DataPanel();
		cards.add(runPanel, "RUNPANEL");
		cards.add(algPanel, "ALGPANEL");
		cards.add(dataPanel, "DATAPANEL");		
	}
	
	JPanel getPanel(){
		return cards;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(algorithmB)){
			
			setPanel("ALGPANEL");
		}	
	}
	
	public static void setPanel(String panel){
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, panel);
	}
	
	
	
	void createRunPanel(JPanel runPanel){
		
		GroupLayout layout = new GroupLayout(runPanel);
		runPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(algorithmL)
					.addComponent(distL)
					.addComponent(datasetL)
					.addComponent(hadoopConfigurationL)
					.addComponent(runButton))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(algorithmT)
					.addComponent(dBox)
					.addComponent(datasetT)
					.addComponent(hadoopConfigurationT))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(algorithmB)
					.addComponent(hadoopConfigurationB))					
						
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(algorithmL)
						.addComponent(algorithmT)
						.addComponent(algorithmB))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(distL)
						.addComponent(dBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(datasetL)
						.addComponent(datasetT))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(hadoopConfigurationL)
						.addComponent(hadoopConfigurationT)
						.addComponent(hadoopConfigurationB))
				.addComponent(runButton)
		);
		dBox.setEnabled(false);
	}
	
	
	public static void setCurrentAlgorithm(Algorithm algo){
		currentAlgorithm=algo;
	}
	
	public Algorithm getCurrentAlgorithm(){
		return currentAlgorithm;
	}
	
	public static void setCurrentDataset(Dataset dataset){
		currentDataset=dataset;
	}
	
	public Dataset getCurrentDataset(){
		return currentDataset;
	}
	
	public void setFields(){ //gets info of currentDataSet, current HadoopConfig and Current Algorithm objects and puts them in the relevant fields
		if (currentAlgorithm!=null){
			algorithmT.setText(currentAlgorithm.getName());
			if (currentAlgorithm.isDistributed()){
				dBox.setSelected(true);
			}
			else{
				dBox.setSelected(false);
			}
		}
		if (currentDataset!=null){
			datasetT.setText(currentDataset.getName());
		}
	}
	
	public void setBox(boolean b){
		if (b){
			dBox.setSelected(true);
		}
		else{
			dBox.setSelected(false);
		}
		
	}
	
	
	
}
