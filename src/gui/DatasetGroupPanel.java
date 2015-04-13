package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import def.Algorithm;
import def.Dataset;
import def.DatasetGroup;
import def.FileSetUp;
import def.Main;
import def.ResourceHandler;

public class DatasetGroupPanel extends JPanel implements ActionListener{
	private JLabel name = new JLabel("Name");
	private JLabel id = new JLabel("ID");
	private JLabel size = new JLabel("Dataset");
	private JLabel algorithm = new JLabel("Algorithms");
	private JLabel datasetGroupNum = new JLabel("DatasetGroup #");
	
	
	private JButton newDatasetGroup = new JButton("New");
	private JButton removeDataset = new JButton("Remove");
	private JButton addExistingDataset = new JButton("Add Existing");
	private JButton addNewDataset = new JButton("Add New");
	private JButton addAlgorithm = new JButton("Add Existing");
	private JButton removeAlgorithm = new JButton("Remove");
	private JButton prev = new JButton("<");
	private JButton next = new JButton(">");
	private JButton choosefilePath = new JButton("_");
	private JButton newGroup = new JButton("New");
	private JButton save = new JButton("Save");
	private JButton cancel = new JButton("Cancel");
	private JButton delete = new JButton("Delete");
	
	private JTextField nameT = new JTextField();
	private JTextField idT = new JTextField();
	private JTextField datasetGroupT = new JTextField();
	private JLabel outOf = new JLabel("/");
	private JTextField datasetGroupTotal = new JTextField();
	private JComboBox datasetBox = new JComboBox();
	private JComboBox algorithmBox = new JComboBox(); 
	
	private String[] datasets;
	private String[] algorithms;
	
	private DatasetGroup tempGroup;
	
	private ArrayList<DatasetGroup> datasetGroups;
	private int currentDatasetGroup;
	private int totalDatasetGroups;
	
	
	public DatasetGroupPanel(){
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border paneEdge = BorderFactory.createTitledBorder(blackline, "Dataset Group Panel");
		
		this.setBorder(paneEdge);
		initialise();
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//add actionlisteners to components
		prev.addActionListener(this);
		next.addActionListener(this);
		newDatasetGroup.addActionListener(this);
		choosefilePath.addActionListener(this);	
		newGroup.addActionListener(this);
		newDatasetGroup.addActionListener(this);
		addNewDataset.addActionListener(this);
		removeDataset.addActionListener(this);
		addAlgorithm.addActionListener(this);
		removeAlgorithm.addActionListener(this);
		delete.addActionListener(this);
		cancel.addActionListener(this);
		save.addActionListener(this);
		nameT.setEditable(false);
		idT.setEditable(false);
		datasetGroupT.setEditable(false);
		datasetGroupTotal.setEditable(false);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(datasetGroupNum)
						.addComponent(id)
						.addComponent(name)
						.addComponent(size)
						.addComponent(algorithm)
						.addComponent(newGroup)
						.addComponent(save)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(datasetGroupT)
								.addComponent(outOf)
								.addComponent(datasetGroupTotal))
						
						.addComponent(idT)
						.addComponent(nameT)
						.addComponent(datasetBox)
						.addComponent(algorithmBox)
						.addComponent(delete)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(prev)
								.addComponent(next))
						.addComponent(addExistingDataset)
						.addComponent(addAlgorithm)
						.addComponent(cancel)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addComponent(removeDataset)
						.addComponent(removeAlgorithm)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addComponent(addNewDataset)
				)						
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(datasetGroupNum)
						.addComponent(datasetGroupT)
						.addComponent(outOf)
						.addComponent(datasetGroupTotal)
						.addComponent(prev)
						
						.addComponent(next)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(id)
						.addComponent(idT)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(name)
						.addComponent(nameT)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(size)
						.addComponent(datasetBox)
						.addComponent(addExistingDataset)
						.addComponent(removeDataset)
						.addComponent(addNewDataset)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(algorithm)
						.addComponent(algorithmBox)
						.addComponent(addAlgorithm)
						.addComponent(removeAlgorithm)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(newGroup)
					.addComponent(save)
					.addComponent(delete)
					.addComponent(cancel))
		);
		
		save.setVisible(false);
		cancel.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(next)){
			if (totalDatasetGroups>1){
				if (currentDatasetGroup>0){
					currentDatasetGroup--;
					display(currentDatasetGroup);
				}
				else{					
					currentDatasetGroup= totalDatasetGroups-1;
					display(currentDatasetGroup);					
				}
			}
		}
		else if (e.getSource().equals(prev)){
			if (totalDatasetGroups>0){
				if (currentDatasetGroup>=totalDatasetGroups-1){
					currentDatasetGroup=0;
					display(currentDatasetGroup);
				}
				else{					
					currentDatasetGroup++;
					display(currentDatasetGroup);
				}
			}
		}
		
		else if (e.getSource().equals(cancel)){
			cancel();
		}
		else if (e.getSource().equals(save)){
			save();
		}
		else if (e.getSource().equals(newGroup)){
			create();
		}
		else if( e.getSource().equals(addNewDataset)){
			addDatasets();			
		}
		else if (e.getSource().equals(removeDataset)){
			removeDataset();
		}
		else if (e.getSource().equals(delete)){
			delete();
		}
		// TODO Auto-generated method stub
		
	}
	
	
	private void initialise(){
		currentDatasetGroup = 0;
		datasetGroups = Main.getResourceHandler().getDatasetGroups();
		totalDatasetGroups = datasetGroups.size();
		if (datasetGroups!=null && totalDatasetGroups>0){
			datasetGroupTotal.setText(String.valueOf(totalDatasetGroups));
			display(0);
		}
		

	}
	
	private void display(int index){
		datasetBox.removeAllItems();
		algorithmBox.removeAllItems();
		datasetGroupT.setText(String.valueOf(index+1));
		nameT.setText(((DatasetGroup) datasetGroups.get(index)).getName());
		idT.setText(String.valueOf(((DatasetGroup) datasetGroups.get(index)).getID()));
		
		updateBoxes(datasetGroups.get(index));
		
	}
	
	private void updateBoxes(DatasetGroup dGroup){
		if (datasetBox == null || algorithmBox == null){
			//boxes are null, need to initialise them
			datasets = new String[dGroup.getDatasets().size()];
			
			for (int i = 0; i < datasets.length; i++){
				datasets[i] = String.valueOf(dGroup.getDatasets().get(i).getSize());
			}
			algorithms = new String[dGroup.getAssociatedAlgorithms().size()];
			
			for (int i=0; i< algorithms.length; i++){
				algorithms[i] = dGroup.getAssociatedAlgorithms().get(i).getName();
			}
			datasetBox = new JComboBox<String>(datasets);
			algorithmBox = new JComboBox<String>(algorithms);
		}
		else{
			
			datasetBox.removeAllItems();		
			for (int i=0; i< dGroup.getDatasets().size(); i++){
				datasetBox.addItem(String.valueOf(dGroup.getDatasets().get(i).getSize()));
			}
			algorithmBox.removeAllItems();
			for (int i=0; i< dGroup.getAssociatedAlgorithms().size(); i++){
				algorithmBox.addItem(dGroup.getAssociatedAlgorithms().get(i).getName());
			}
		}
	}
	
	private void create(){
		newGroup.setVisible(false);
		tempGroup = new DatasetGroup();
		save.setVisible(true);
		cancel.setVisible(true);
		delete.setVisible(false);
		next.setVisible(false);
		prev.setVisible(false);
		currentDatasetGroup = tempGroup.getID();
		idT.setText("");
		nameT.setEditable(true);
		nameT.setText("");
		
		
	}
	
	private void save(){
		if (tempGroup!=null){
			if (nameT.getText().length()>0){
				tempGroup.saveDatasetGroup();
				tempGroup.setName(nameT.getText());
				Main.getResourceHandler().addDatasetGroup(tempGroup);
				tempGroup = null;
				save.setVisible(false);
				cancel.setVisible(false);
				delete.setVisible(true);
				next.setVisible(true);
				prev.setVisible(true);
				nameT.setEditable(false);
				newGroup.setVisible(true);
				totalDatasetGroups++;

				datasetGroupTotal.setText(String.valueOf(totalDatasetGroups));
			}
		}
		
	}
	
	private void cancel(){
		save.setVisible(false);
		cancel.setVisible(false);
		delete.setVisible(true);
		next.setVisible(true);
		prev.setVisible(true);
		nameT.setEditable(false);
		newGroup.setVisible(true);
		if (totalDatasetGroups>0){
			display(0);
		}
		else{
			idT.setText("");
			nameT.setText("");
		}
	}
	
	private void delete(){
		//remove group from groups
		
		if (totalDatasetGroups>1){
			datasetGroups.remove(currentDatasetGroup);
			totalDatasetGroups--;
			//remove from file
			Main.getResourceHandler().writeDatasetGroups();
			//sort out display
			currentDatasetGroup=0;
			datasetGroupTotal.setText("totalDatasetGroups");
			display(0);
		}
		else if (totalDatasetGroups==1){
			datasetGroups.remove(currentDatasetGroup);
			totalDatasetGroups--;
			datasetGroupTotal.setText("totalDatasetGroups");
			//remove from file
			Main.getResourceHandler().writeDatasetGroups();
			//sort out display
			currentDatasetGroup=0;
			clearAll();
		}
		
		
	}
	
	
	private void addSingleDataset(){
		File f = FileSetUp.getFilepath("Select file", false);
		String name = JOptionPane.showInputDialog(null, "Enter Name of Dataset", f.getAbsolutePath());
		int saveDataset = JOptionPane.showConfirmDialog(null, "Save this Dataset to Group: ");
		if (saveDataset == JOptionPane.YES_OPTION){
			Dataset d = new Dataset(name, f.getAbsolutePath(), f.length());
			Main.getResourceHandler().addDatasetToGroup(currentDatasetGroup,d);
			display(currentDatasetGroup);
			//add dataset to datasetGroup
		}
	}
	
	private void addDatasets(){
		File[] datasets = FileSetUp.getFiles("Select the datasets");
		
		if (datasets!=null && datasets.length>0){
			String name = JOptionPane.showInputDialog(null, "Enter Name of Datasets", datasets[0].getAbsolutePath());
			
			for (int i =0; i<datasets.length; i++){
				Dataset temp = new Dataset(name, datasets[i].getAbsolutePath(), datasets[i].length());
				//datasetGroups.get(currentDatasetGroup).addDataset(temp);
				Main.getResourceHandler().addDataset(temp);
				Main.getResourceHandler().addDatasetToGroup(currentDatasetGroup,  temp);
			}
			updateBoxes(datasetGroups.get(currentDatasetGroup));			
		}		
	}
	
	private void removeDataset(){
		if (datasetBox.getItemCount()>0){
			System.out.println("datasetBox size is:" + datasetBox.getItemCount());
			System.out.println("selected index is "+ datasetBox.getSelectedIndex());
			int choice = datasetBox.getSelectedIndex();
			Main.getResourceHandler().removeDatasetFromGroup(choice, currentDatasetGroup);
			updateBoxes(datasetGroups.get(currentDatasetGroup));
		}
	}
	
	private void clearAll(){
		nameT.setText("");
		idT.setText("");
		if (datasetBox!=null){
			datasetBox.removeAllItems();
		}
		if (algorithmBox!=null){
			algorithmBox.removeAllItems();
		}
	}
	
	

}
