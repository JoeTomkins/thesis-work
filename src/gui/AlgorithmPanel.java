package gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.Policy.Parameters;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import def.Algorithm;
import def.FileSetUp;
import def.Main;
import def.Parameter;
import def.ResourceHandler;

public class AlgorithmPanel extends JPanel implements ActionListener{
	
	
	JLabel number = new JLabel("Algorithm #");
	JLabel total = new JLabel(" / ");
	JLabel name = new JLabel("Name:");
	JLabel dist = new JLabel("Distributed: ");
	JLabel filepath = new JLabel("Filepath: ");
	JLabel id = new JLabel("ID:");
	JLabel arg = new JLabel("Arguments");
	JLabel description = new JLabel("Description:" );
	JLabel algHeader = new JLabel("Algorithm");
	JLabel datasetHeader = new JLabel("Dataset");
	JLabel blank = new JLabel("");
	JLabel argPrefix = new JLabel("Prefix: ");
	JLabel defaultValue = new JLabel("Default Value:");
	
	private String[] arguments;
	private Algorithm tempAlgorithm;
	
	ArrayList<Parameter> Parameters;
	
	ArrayList<Parameter> tempParameters; // for use with creating new algorithms
	
	JComboBox<String> argumentBox = new JComboBox<String>();
	
	JCheckBox distBox = new JCheckBox();
	
	JTextField descriptionTf = new JTextField();
	JTextField numberTf = new JTextField();
	JTextField totalAlg = new JTextField();
	JButton next = new JButton("->");
	JButton prev = new JButton("<-");
	
	
	JTextField nameTf = new JTextField();
	JTextField filepathTf = new JTextField();
	JTextField algorithmIDTf = new JTextField();
	JTextField prefixTf = new JTextField();
	JTextField argumentTf = new JTextField();
	JTextField defaultValueTf = new JTextField();
	
	private boolean isNewAlgorithm = false;
	private boolean isNewArgument = false;

	JButton saveArgument = new JButton("Save");
	JButton addArgument = new JButton("Add");
	JButton moveArgumentUp = new JButton ("↑");
	JButton moveArgumentDown = new JButton("↓");
	JButton delete = new JButton("Delete");
	JButton editPrefix = new JButton("Edit");
	JButton savePrefix = new JButton("Save");
	
	JButton editDescription = new JButton("Edit");
	JButton saveDescription = new JButton("Save");
	JButton editDefaultValue = new JButton("Edit");
	JButton saveDefaultValue = new JButton("Save");
	
	JButton newAlgorithm = new JButton("New Algorithm");
	JButton cancel = new JButton("Cancel");
	JButton filePathButton = new JButton("_");
	JButton cancelArgument = new JButton("Cancel");
	
	JPanel dataPanel;
	JPanel algorithmPanel;
	JPanel buttonPanel;
	
	private static ArrayList<Algorithm> algorithmList;
	private Algorithm currentAlgorithm;
	
	int totalAlgorithms;
	int currentAlgNum;
	int currentArgNum;
	
	boolean isRunPanel;
	
	JButton save = new JButton("Save");
	JButton ret = new JButton("Return");
	
	
	public AlgorithmPanel(boolean isRunPanel){
		
		this.isRunPanel = isRunPanel;
		algorithmPanel = new JPanel();
		GroupLayout layout = new GroupLayout(algorithmPanel);
		algorithmPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		distBox.setEnabled(false);
		next.addActionListener(this);
		prev.addActionListener(this);
		argumentTf.setVisible(false);
		
		
		initialise(); //set values of fields
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(number)
					.addComponent(id)
					.addComponent(name)
					.addComponent(filepath)
					.addComponent(dist)
					.addComponent(arg)
					.addComponent(argPrefix)
					.addComponent(description)
					.addComponent(defaultValue)
					.addComponent(blank)
					.addGroup(layout.createSequentialGroup()
							.addComponent(newAlgorithm)
							.addComponent(save)))				
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addComponent(numberTf)
						.addComponent(total)
						.addComponent(totalAlg))
					.addComponent(algorithmIDTf)
					.addComponent(nameTf)
					.addComponent(filepathTf)
					.addComponent(distBox)
					.addComponent(prefixTf)
					.addComponent(argumentBox)
					.addComponent(argumentTf)
					.addComponent(descriptionTf)
					.addComponent(defaultValueTf)
					.addComponent(delete))					
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						
						.addComponent(prev)
						.addComponent(next))
					.addComponent(filePathButton)
					.addGroup(layout.createSequentialGroup()
						.addComponent(addArgument)
						.addComponent(saveArgument)
						.addComponent(cancelArgument)
						.addComponent(moveArgumentUp)
						.addComponent(moveArgumentDown))
					.addComponent(editDescription)
					.addComponent(saveDescription)
					.addComponent(editPrefix)
					.addComponent(savePrefix)
					.addComponent(editDefaultValue)
					.addComponent(saveDefaultValue)
					.addComponent(cancel)
					.addComponent(ret))
		);
		layout.linkSize(SwingConstants.HORIZONTAL, prev, next);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(number)
						.addComponent(numberTf)
						.addComponent(total)
						.addComponent(totalAlg)
						.addComponent(prev)
						.addComponent(next))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(id)
						.addComponent(algorithmIDTf))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(name)
						.addComponent(nameTf))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(filepath)
						.addComponent(filepathTf)
						.addComponent(filePathButton))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(dist)
						.addComponent(distBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(arg)
						.addComponent(argumentTf)
						.addComponent(argumentBox)
						.addComponent(addArgument)
						.addComponent(saveArgument)
						.addComponent(cancelArgument)
						.addComponent(moveArgumentUp)
						.addComponent(moveArgumentDown))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(argPrefix)
						.addComponent(prefixTf)
						.addComponent(editPrefix)
						.addComponent(savePrefix))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(description)
						.addComponent(descriptionTf)
						.addComponent(editDescription)
						.addComponent(saveDescription))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(defaultValue)
						.addComponent(defaultValueTf)
						.addComponent(editDefaultValue)
						.addComponent(saveDefaultValue))
				.addComponent(blank)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(newAlgorithm)
						.addComponent(save)
						.addComponent(ret)
						.addComponent(delete)
						.addComponent(cancel))
			
						
		);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		algHeader.setBorder(new EmptyBorder(20,0,0,150));
		algHeader.setFont(new Font("Serif", Font.BOLD,16));
		add(algHeader);
		add(algorithmPanel);
		cancelArgument.setVisible(false);
		cancelArgument.addActionListener(this);
		moveArgumentUp.addActionListener(this);
		moveArgumentDown.addActionListener(this);
		filePathButton.setVisible(false);
		if (isRunPanel){
			dataPanel = new DataPanel();
			dataPanel.setVisible(true);			
			add(datasetHeader);
			datasetHeader.setBorder(new EmptyBorder(30,20,0,40));
			datasetHeader.setFont(new Font("Serif", Font.BOLD,16));
			add(dataPanel);
			save.setVisible(false);
			newAlgorithm.setVisible(false);
			addArgument.setVisible(false);
			moveArgumentUp.setVisible(false);
			moveArgumentDown.setVisible(false);
			delete.setVisible(false);
			editDescription.setVisible(false);
			editPrefix.setVisible(false);
			editDefaultValue.setVisible(false);
			
		}
		else{
			ret.addActionListener(this);
			filePathButton.addActionListener(this);
			cancel.addActionListener(this);
			save.addActionListener(this);
			newAlgorithm.addActionListener(this);
			argumentBox.addActionListener(this);
			addArgument.addActionListener(this);
			saveArgument.addActionListener(this);
			delete.addActionListener(this);
			savePrefix.addActionListener(this);
			editPrefix.addActionListener(this);
			saveDescription.addActionListener(this);
			editDescription.addActionListener(this);
			editDefaultValue.addActionListener(this);
			saveDefaultValue.addActionListener(this);
			defaultValueTf.setEditable(false);
			descriptionTf.setEditable(false);
			prefixTf.setEditable(false);
		}
		cancel.setVisible(false);
		saveArgument.setVisible(false);
		saveDescription.setVisible(false);
		save.setVisible(false);
		savePrefix.setVisible(false);
		saveDescription.setVisible(false);
		saveDefaultValue.setVisible(false);
	}
	
	
//ACTION LISTENERS
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		
		if (e.getSource().equals(prev)){
			if (algorithmList!=null && totalAlgorithms>0){ 
				if (currentAlgNum!=0){
					currentAlgNum--;
					display(currentAlgNum);
				}
				else{ 
					currentAlgNum=totalAlgorithms-1;
					display(currentAlgNum);
				}
				updateArguments(algorithmList.get(currentAlgNum)); // update list or arguments
			}
			
		}
		else if (e.getSource().equals(next)){
			if (algorithmList!=null && totalAlgorithms>0){
				if (currentAlgNum==(totalAlgorithms-1)){
					currentAlgNum=0;
					display(currentAlgNum);
				}
				else{
					currentAlgNum++;
					display(currentAlgNum);
				}
				updateArguments(algorithmList.get(currentAlgNum)); // update list or arguments
			}
		}
		//return to previous panel
		else if (e.getSource().equals(ret)){
			if (isRunPanel){
				RunPanel.setPanel("RUNPANEL");				
			}
			else{
				DandAPanel.setPanel("FRONTPANEL");
			}
		}
		
		else if (e.getSource().equals(save)){
			save();
		}
		
		else if (e.getSource().equals(newAlgorithm)){
			delete.setVisible(false);
			create();
		}
		
		else if (e.getSource().equals(cancel)){
			delete.setVisible(true);
			cancel();
			
			
		}
		else if (e.getSource().equals(argumentBox)){
			currentArgNum = argumentBox.getSelectedIndex();			
			updateArgumentDetails();
		}
		
		else if (e.getSource().equals(addArgument)){
			if (totalAlgorithms>0 || isNewAlgorithm){
				isNewArgument=true;
				saveArgument.setVisible(true);
				addArgument.setVisible(false);
				cancelArgument.setVisible(true);
				moveArgumentUp.setVisible(false);
				moveArgumentDown.setVisible(false);
				argumentTf.setVisible(true);
				argumentBox.setVisible(false);
				// clear text for each textfield
				descriptionTf.setText("");
				defaultValueTf.setText("");
				argumentTf.setText("");
				prefixTf.setText("");
				
				prefixTf.setEditable(true);
				descriptionTf.setEditable(true);
				defaultValueTf.setEditable(true);
				
				editPrefix.setVisible(false);
				editDescription.setVisible(false);
				editDefaultValue.setVisible(false);
				
			}
		}
		else if (e.getSource().equals(saveArgument)){
			saveArgument();
			argumentTf.setText("");
			// if argument is valid - 
			//TODO Check for validation.
			addArgument.setVisible(true);
			argumentBox.setVisible(true);
			saveArgument.setVisible(false);			
			argumentTf.setVisible(false);
			cancelArgument.setVisible(false);
			moveArgumentUp.setVisible(true);
			moveArgumentDown.setVisible(true);
			
			prefixTf.setEditable(false);
			descriptionTf.setEditable(false);
			defaultValueTf.setEditable(false);
			
			editPrefix.setVisible(true);
			editDescription.setVisible(true);
			editDefaultValue.setVisible(true);
			
		}
		else if (e.getSource().equals(cancelArgument)){
			cancelArgument.setVisible(false);
			addArgument.setVisible(true);
			saveArgument.setVisible(false);
			moveArgumentUp.setVisible(false);
			moveArgumentDown.setVisible(false);
			argumentTf.setVisible(false);
			argumentBox.setVisible(true);
			if (argumentBox.getItemCount()>0){
				argumentBox.setSelectedIndex(0);
				//MAYBE UPDATE DETAILS HERE IF NECESSARY
			}
			else{
				descriptionTf.setText("");
				defaultValueTf.setText("");
				argumentTf.setText("");
				prefixTf.setText("");
			}
			
			prefixTf.setEditable(false);
			descriptionTf.setEditable(false);
			defaultValueTf.setEditable(false);
			
			editPrefix.setVisible(true);
			editDescription.setVisible(true);
			editDefaultValue.setVisible(true);
			
		}
		else if (e.getSource().equals(filePathButton)){
			File f = FileSetUp.getFilepath("Select filepath to Algorithm", false);
			if (f!=null){
				filepathTf.setText(f.getAbsolutePath());
			}
		}
		
		else if (e.getSource().equals(delete)){
			if (totalAlgorithms>0){
				ResourceHandler.removeAlgorithm(currentAlgNum);
				
				initialise();
			}
		}
		
		else if (e.getSource().equals(saveDescription)){
			if (isNewArgument){
				return;
			}
			saveDescription.setVisible(false);
			editDescription.setVisible(true);
			descriptionTf.setEditable(false);
			if (isNewAlgorithm){
				tempParameters.get(currentArgNum).setDescription(descriptionTf.getText());
			}
			else{
				algorithmList.get(currentAlgNum).getArguments().get(currentArgNum).setDescription(descriptionTf.getText());
				Main.getResourceHandler().writeAlgorithms();
			}
		}
		else if (e.getSource().equals(editDescription)){
			if (argumentBox.getItemCount()>0 || isNewAlgorithm){
				editDescription.setVisible(false);
				saveDescription.setVisible(true);
				descriptionTf.setEditable(true);
			}
			
		}
		else if (e.getSource().equals(editPrefix)){
			if (argumentBox.getItemCount()>0|| isNewAlgorithm){
				editPrefix.setVisible(false);
				savePrefix.setVisible(true);
				prefixTf.setEditable(true);
			}
		}
		else if (e.getSource().equals(savePrefix)){
			if (isNewArgument){
				return;
			}
			savePrefix.setVisible(false);
			editPrefix.setVisible(true);
			prefixTf.setEditable(false);
			if (isNewAlgorithm){
				tempParameters.get(currentArgNum).setPrefix(prefixTf.getText());
			}
			else{
				algorithmList.get(currentAlgNum).getArguments().get(currentArgNum).setPrefix(prefixTf.getText());
				Main.getResourceHandler().writeAlgorithms();
			}
		}
		else if (e.getSource().equals(editDefaultValue)){
			if (argumentBox.getItemCount()>0|| isNewAlgorithm){
				editDefaultValue.setVisible(false);
				saveDefaultValue.setVisible(true);
				defaultValueTf.setEditable(true);
			}
		}
		else if (e.getSource().equals(saveDefaultValue)){
			if (isNewArgument){
				return;
			}
			saveDefaultValue.setVisible(false);
			editDefaultValue.setVisible(true);
			defaultValueTf.setEditable(false);
			if (isNewAlgorithm){
				tempParameters.get(currentArgNum).setValue(defaultValueTf.getText());
			}
			else{
				algorithmList.get(currentAlgNum).getArguments().get(currentArgNum).setValue(defaultValueTf.getText());
				Main.getResourceHandler().writeAlgorithms();
			}
		}
		
		else if (e.getSource().equals(moveArgumentUp)){
			if (totalAlgorithms>0){
				if (argumentBox.getItemCount()>1){
					if (currentArgNum<(argumentBox.getItemCount()-1)){
						algorithmList.get(currentAlgNum).swapParameter(currentArgNum, currentArgNum+1);
						updateArguments(algorithmList.get(currentAlgNum));
						argumentBox.setSelectedIndex(currentArgNum+1);
						Main.getResourceHandler().writeAlgorithms();
					}
				}
			}
		}
		else if (e.getSource().equals(moveArgumentDown)){
			if (totalAlgorithms>0){
				if (argumentBox.getItemCount()>1){
					if (currentArgNum>0){
						algorithmList.get(currentAlgNum).swapParameter(currentArgNum-1, currentArgNum);
						updateArguments(algorithmList.get(currentAlgNum));
						argumentBox.setSelectedIndex(currentArgNum-1);
						Main.getResourceHandler().writeAlgorithms();
					}
				}
			}
			

			
		}
		
			
		
	}
	
	
	
	private void updateArguments(Algorithm a){
		if (a.getArguments()!=null){
			if (a.getArguments().size()>0){
				if (argumentBox == null){
					arguments = new String[a.getArguments().size()];
					for (int i=0; i< arguments.length; i++){
						arguments[i] = ((Parameter) a.getArguments().get(i)).getName();
					}
					argumentBox = new JComboBox<String>(arguments);
						
				}
				else{
					argumentBox.removeAllItems();
					for (int i = 0; i<a.getArguments().size(); i++ ){
						argumentBox.addItem(((Parameter) a.getArguments().get(i)).getName());
					}
				}
				
				
				if(argumentBox.getItemCount()>0){
					updateArgumentDetails();
				}
			}
		}
	}
	
	private void updateArgumentDetails(){
		if (argumentBox.getSelectedIndex() == -1){
			return;
		}
		
		if (!isNewAlgorithm){
			descriptionTf.setText(((Parameter)algorithmList.get(currentAlgNum).getArguments().get(argumentBox.getSelectedIndex())).getDescription());
			prefixTf.setText(((Parameter)algorithmList.get(currentAlgNum).getArguments().get(argumentBox.getSelectedIndex())).getPrefix());
			defaultValueTf.setText(((Parameter)algorithmList.get(currentAlgNum).getArguments().get(argumentBox.getSelectedIndex())).getValue());
			
		}
		else{
			if (tempAlgorithm.getArguments().size()>0){
				descriptionTf.setText(tempAlgorithm.getArguments().get(argumentBox.getSelectedIndex()).getDescription());
				prefixTf.setText(tempAlgorithm.getArguments().get(argumentBox.getSelectedIndex()).getPrefix());
				defaultValueTf.setText(tempAlgorithm.getArguments().get(argumentBox.getSelectedIndex()).getValue());
			}
		}
		//bit of a long line here -> basically get the parameters from the current algorithm chosen (indexed) -> get the description from the currently selected argument.
	}
	
	private void create(){
		tempAlgorithm = new Algorithm("");
		
		isNewAlgorithm=true;
		newAlgorithm.setVisible(false);		
		filePathButton.setVisible(true);
		save.setVisible(true);
		cancel.setVisible(true);
		argumentBox.removeAllItems();
		descriptionTf.setText("");
		numberTf.setText(String.valueOf(totalAlgorithms+1));
		totalAlg.setText(String.valueOf(totalAlgorithms+1));
		nameTf.setText("");
		filepathTf.setText("");
		algorithmIDTf.setText("");
		prefixTf.setText("");
		defaultValueTf.setText("");
		descriptionTf.setText("");
		filepathTf.setEditable(true);
		
		nameTf.setEditable(true);
		distBox.setEnabled(true);
		next.setVisible(false);
		prev.setVisible(false);	
	}
	
	private void save(){
		if (nameTf.getText().length()>0 && filepathTf.getText().length()>0){
			
			// edit objects on pane
			newAlgorithm.setVisible(true);
			save.setVisible(false);
			cancel.setVisible(false);
			distBox.setEnabled(false);
			prev.setVisible(true);
			next.setVisible(true);
			filePathButton.setVisible(false);
			filepathTf.setEditable(false);
			//create algorithm and add it to resource handler
			tempAlgorithm.finalise(nameTf.getText(), distBox.isSelected(), filepathTf.getText());
			
			
			
			ResourceHandler.addAlgorithm(tempAlgorithm);
			initialise();
			isNewAlgorithm=false;			
		}
	}
	
	private void cancel(){
		newAlgorithm.setVisible(true);
		filepathTf.setEditable(false);
		save.setVisible(false);
		cancel.setVisible(false);
		prev.setVisible(true);
		next.setVisible(true);
		distBox.setEnabled(false);
		filePathButton.setVisible(false);
		filepathTf.setText("");
		nameTf.setEditable(false);
		numberTf.setText(String.valueOf(totalAlgorithms));
		totalAlg.setText(String.valueOf(totalAlgorithms));
		if(totalAlgorithms>0){
			display(totalAlgorithms-1);
		}
		else{
			//everything be blank!
			blankEverything();
		}
		isNewAlgorithm=false;
		isNewArgument=false;
		argumentBox.removeAllItems();
		saveArgument.setVisible(false);
		savePrefix.setVisible(false);
		saveDefaultValue.setVisible(false);
		addArgument.setVisible(true);
		editPrefix.setVisible(true);
		editDescription.setVisible(true);
		editDefaultValue.setVisible(true);
		argumentTf.setVisible(false);
		argumentBox.setVisible(true);
		
		
		
	}
	
	
	
	private void saveArgument(){
		
		if (argumentTf.getText().length()>0){
			Parameter newParameter = new Parameter(argumentTf.getText(), prefixTf.getText(), defaultValueTf.getText(), descriptionTf.getText());
	
			if (isNewAlgorithm){
				tempAlgorithm.addArgument(newParameter);
				//update argument box?
				System.out.println("tempalgorithms now has "+ tempAlgorithm.getArguments().size());
				updateArguments(tempAlgorithm);
				isNewArgument=false;
				
			}
			else{
				algorithmList.get(currentAlgNum).addArgument(newParameter);
				argumentBox.addItem(newParameter.getName());
				Main.getResourceHandler().writeAlgorithms();
			}
		}
		
	}
		//newParameters.add(e)
	
	
	
	
	
	

	private void initialise(){
		currentAlgNum= 0;
	
		algorithmList = Main.getResourceHandler().getAlgorithms();
		
		if (algorithmList != null){
			totalAlgorithms = algorithmList.size();			
			if (totalAlgorithms>0){
				
				display(0);
			}
			else{
				blankEverything();
			}
		}
		else{
			totalAlgorithms=0;
			blankEverything();
		}
		totalAlg.setText(String.valueOf(totalAlgorithms));
		nameTf.setEditable(false);
		numberTf.setEditable(false);
		filepathTf.setEditable(false);
		algorithmIDTf.setEditable(false);
		numberTf.setEditable(false);
		totalAlg.setEditable(false);		
	}
	
	private void display(int index){
		numberTf.setText(String.valueOf(index+1));
		nameTf.setText(algorithmList.get(index).getName());
		filepathTf.setText(algorithmList.get(index).getFilePath());
		algorithmIDTf.setText(String.valueOf(algorithmList.get(index).getAlgorithmID()));
		if (algorithmList.get(index).isDistributed()){
			distBox.setSelected(true);
		}
		else{
			distBox.setSelected(false);
		}
		updateArguments(algorithmList.get(index));
	}
	
	private void blankEverything(){
		numberTf.setText("0");
		algorithmIDTf.setText("");
		nameTf.setText("");
		filepathTf.setText("");
		distBox.setSelected(false);
		argumentBox.removeAllItems();
		prefixTf.setText("");
		descriptionTf.setText("");
		defaultValueTf.setText("");
	}
	
	
	
	
}
