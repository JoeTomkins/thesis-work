package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import def.DatasetGroup;
import def.Main;

public class DataPanel extends JPanel implements ActionListener{
	JLabel nameL = new JLabel("Name");
	JLabel sizeL = new JLabel("Size");
	JLabel filePathL = new JLabel("Filepath");
	
	JComboBox <String> nameBox =  new JComboBox<String>();
	JComboBox <String> sizeBox = new JComboBox<String>();
	JTextField filePathT = new JTextField();
	
	
	private int chosenSize;
	private int chosenDatasetGroup;
	
	public DataPanel(){
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		initialise();
		nameBox.addActionListener(this);
		sizeBox.addActionListener(this);
		filePathT.setEditable(false);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			
				.addComponent(nameL)
				.addComponent(sizeL)
				.addComponent(filePathL))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(nameBox)
				.addComponent(sizeBox)
				.addComponent(filePathT))
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(nameL)
						.addComponent(nameBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(sizeL)
						.addComponent(sizeBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(filePathL)
						.addComponent(filePathT))
				
		);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(nameBox)){			
			updateSizes((DatasetGroup) Main.getResourceHandler().getDatasetGroups().get(nameBox.getSelectedIndex()));
			
		}
		else if (e.getSource().equals(sizeBox)){
			chosenSize = sizeBox.getSelectedIndex();
		}
		
	}
	
	private void updateSizes(DatasetGroup dGroup){
		
		if (sizeBox == null){
			String[] sizes = new String[dGroup.getDatasets().size()];
			for (int i=0; i< sizes.length; i++){
				sizes[i] = String.valueOf(dGroup.getDatasets().get(i).getSize());			
			}
			sizeBox = new JComboBox<String>(sizes);
			filePathT = new JTextField(dGroup.getName());
		}
		else{
			
			sizeBox.removeAllItems();		
			for (int i=0; i< dGroup.getDatasets().size(); i++){
				sizeBox.addItem(String.valueOf(dGroup.getDatasets().get(i).getSize()));
			}
			filePathT.setText(dGroup.getName());
		}
		
	}
	
	private void initialise(){
		String[] names = new String[Main.getResourceHandler().getDatasetGroups().size()];
		if (names.length>0){
			for (int i=0; i<names.length; i++){
				names[i] = ((DatasetGroup) Main.getResourceHandler().getDatasetGroups().get(i)).getName();
			}
			nameBox = new JComboBox<String>(names);
			nameBox.setSelectedIndex(0);
			updateSizes((DatasetGroup) Main.getResourceHandler().getDatasetGroups().get(0));
		}
	}
	
	public int getChosenSize(){
		return chosenSize;
	}
	
	public int getChosenDataset(){
		return chosenDatasetGroup;
	}

}
