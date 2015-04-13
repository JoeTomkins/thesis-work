package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import def.Dataset;
import def.FileSetUp;
import def.Main;

public class DatasetPanel extends JPanel implements ActionListener{
	
	
	private JLabel id = new JLabel("ID");
	
	private JLabel name = new JLabel("Name");
	private JLabel size = new JLabel("Size");
	private JLabel filePath = new JLabel("FilePath");
	private JButton newDataset = new JButton("New");
	private JButton deleteDataset = new JButton("Delete");
	private JButton prev = new JButton("<");
	private JButton next = new JButton(">");
	private JButton choosefilePath = new JButton("_");
	private JTextField nameT = new JTextField();
	private JTextField sizeT = new JTextField();
	private JTextField filePathT = new JTextField();
	private ArrayList datasets;
	private int currentDataset;
	private int totalDatasets;
	
	public DatasetPanel(){
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border paneEdge = BorderFactory.createTitledBorder(blackline, "datasetPanel");
		
		this.setBorder(paneEdge);
		//BorderFactory.createLineBorder(Color.black));
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		prev.addActionListener(this);
		next.addActionListener(this);
		newDataset.addActionListener(this);
		deleteDataset.addActionListener(this);
		choosefilePath.addActionListener(this);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(name)
					.addComponent(size)
					.addComponent(filePath)					
						.addGroup(layout.createSequentialGroup()
								.addComponent(newDataset)
								.addComponent(deleteDataset))
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(nameT)
					.addComponent(sizeT)
					.addComponent(filePathT))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addGroup(layout.createSequentialGroup()							
							.addComponent(prev)
							.addComponent(next))
				)
				
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(name)
				.addComponent(nameT)
				.addComponent(prev)
				.addComponent(next))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(size)
				.addComponent(sizeT))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(filePath)
				.addComponent(filePathT))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(newDataset)
				.addComponent(deleteDataset))
		);
		nameT.setEditable(false);
		sizeT.setEditable(false);
		filePathT.setEditable(false);
		initialise();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(newDataset)){
			newDataset();
		}
		else if (e.getSource().equals(prev)){
			if (currentDataset>0){
				currentDataset--;
				display(currentDataset);
			}
			else{
				if (totalDatasets>0){
					currentDataset= totalDatasets-1;
					display(currentDataset);
				}
			}
		}
		else if (e.getSource().equals(next)){
			if (totalDatasets>0){
				if (currentDataset>=totalDatasets-1){
					currentDataset=0;
					display(currentDataset);
				}
				else{					
					currentDataset++;
					display(currentDataset);
				}
			}
			
		}
		else if (e.getSource().equals(deleteDataset)){
			int verify = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Dataset?");
			if (verify==JOptionPane.YES_OPTION){
				Main.getResourceHandler().removeDataset(currentDataset);
				
				if (currentDataset>0){
					display(currentDataset-1);
					datasets.remove(currentDataset);
					currentDataset--;
					totalDatasets--;
					
				}
				else{
					if (totalDatasets>1){
						display(currentDataset+1);
						datasets.remove(currentDataset);
						totalDatasets--;
						display(totalDatasets-1);
					}
					else{
						nameT.setText("");
						sizeT.setText("");
						filePathT.setText("");
					}
				}
			}
		}
		
	}
	
	
	private void newDataset(){
		File filepath = FileSetUp.getFilepath("Select filepath to dataset", false);
		if (filepath!=null){
			String name = JOptionPane.showInputDialog(null, "Name of dataset", filepath.getAbsolutePath());
			Long size = filepath.length();
			System.out.println(filepath.getAbsolutePath());
			
			Dataset newDataset = new Dataset(name,filepath.getAbsolutePath(),size);
			Main.getResourceHandler().addDataset(newDataset);
			totalDatasets++;
			datasets.add(newDataset);
			display(totalDatasets-1);
		}
		//name -> 
	}
	
	private void initialise(){
		currentDataset = 0;
		
		datasets = (ArrayList) Main.getResourceHandler().getDatasets();
		if (datasets!= null){
			totalDatasets = datasets.size();		
			if (totalDatasets>0){
				display(0);				
			}
		}
	}
	
	private void display(int index){	
		
		nameT.setText((((Dataset) datasets.get(index)).getName()));
		sizeT.setText(String.valueOf(((Dataset) datasets.get(index)).getSize()));
		filePathT.setText((((Dataset) datasets.get(index)).getFilepath()));
	}

}
