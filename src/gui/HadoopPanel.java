package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import def.HadoopConfigFile;
import def.HadoopProperty;


public class HadoopPanel implements ActionListener{
	private static JPanel hadoopPanel;
	
	
	
	private final static JLabel dfsBlockSize = new JLabel("dfs.block.size");
	private static JButton dfsBlockSizeChg = new JButton("Change");
	private static JButton dfsBlockSizeDef = new JButton("Set To Default");
	private static JComboBox<Comparable> dfsBlockSizeTf = new JComboBox<Comparable>();
	
	private final static JLabel dfsReplication = new JLabel("dfs.replication");
	private static JButton dfsReplicationChg = new JButton("Change");
	private static JButton dfsReplicationDef = new JButton("Set To Default");
	private static JComboBox<Comparable> dfsReplicationTf = new JComboBox<Comparable>();
	
	
	private final static JLabel IOFileBufSize = new JLabel("IO File Buffer Size");
	private static JButton IOFileBufSizeChg = new JButton("Change");
	private static JButton IOFileBufSizeDef = new JButton("Set To Default");	
	private static JComboBox<Comparable> IOFileBufSizeTf = new JComboBox<Comparable>();
	
	private final static JLabel IOSortFactor = new JLabel("IO Sort Factor");
	private static JButton IOSortFactorChg = new JButton("Change");
	private static JButton IOSortFactorDef = new JButton("Set To Default");
	private static JComboBox<Comparable> IOSortFactorTf = new JComboBox<Comparable>();
	
	private final static JLabel ioSortMb = new JLabel("ioSortMB");
	private static JButton ioSortMbChg = new JButton("Change");
	private static JButton ioSortMbDef = new JButton("Set To Default");
	private static JComboBox<Comparable> ioSortMbTf = new JComboBox<Comparable>();
	
	private final static JLabel ioSortRecPerc = new JLabel("IO sort Rec %");
	private static JButton ioSortRecPercChg = new JButton("Change");
	private static JButton ioSortRecPercDef = new JButton("Set To Default");
	private static JComboBox<Comparable> ioSortRecPercTf = new JComboBox<Comparable>();
	
	private final static JLabel mapredChildJavaOpts = new JLabel("mapred.child.java.opts");
	private static JButton mapredChildJavaOptsChg = new JButton("Change");
	private static JButton mapredChildJavaOptsDef = new JButton("Set To Default");
	private static JComboBox<Comparable> mapredChildJavaOptsTf = new JComboBox<Comparable>();
	
	private final static JLabel mapredInmemMergeThreshold = new JLabel("mapred.inmem.merge.threshold");
	private static JButton mapredInmemMergeThresholdChg = new JButton("Change");
	private static JButton mapredInmemMergeThresholdDef = new JButton("Set To Default");
	private static JComboBox<Comparable> mapredInmemMergeThresholdTf = new JComboBox<Comparable>();
	
	private final static JLabel mapredJobReduceInputBufferPerc = new JLabel("mapred.job.reduce.input.buffer.percent");
	private static JButton mapredJobReduceInputBufferPercChg = new JButton("Change");
	private static JButton mapredJobReduceInputBufferPercDef = new JButton("Set To Default");
	private static JComboBox<Comparable> mapredJobReduceInputBufferPercTf = new JComboBox<Comparable>();
	
	private final static JLabel mapredJobShufInputBufferPerc = new JLabel("mapred.job.shuffle.input.buffer.percent");
	private static JButton mapredJobShufInputBufferPercChg = new JButton("Change");
	private static JButton mapredJobShufInputBufferPercDef = new JButton("Set To Default");
	private static JComboBox<Comparable> mapredJobShufInputBufferPercTf = new JComboBox<Comparable>();
	
	private final static JLabel mapredJobShufMergePerc = new JLabel("mapred.job.shuffle.merge.percent");
	private static JButton mapredJobShufMergePercChg = new JButton("Change");
	private static JButton mapredJobShufMergePercDef = new JButton("Set To Default");
	private static JComboBox<Comparable> mapredJobShufMergePercTf = new JComboBox<Comparable>();
	
	private final static JLabel mapReduceTasks = new JLabel("# MapReduce Tasks");
	private static JButton mapReduceTasksChg = new JButton("Change");
	private static JButton mapReduceTasksDef = new JButton("Set To Default");
	private static JComboBox<Comparable> mapReduceTasksTf = new JComboBox<Comparable>();
	
	private static JButton save = new JButton("Save as Default");
	private static JButton cancel = new JButton("Cancel");
	
//__________________________________________________________________________________________
//------------------------------------------------------------------------------------------
//__________________________________________________________________________________________
	//constructor
	
	
	HadoopPanel(){
		hadoopPanel = new JPanel();
		JPanel top = new JPanel();
		
		GroupLayout layout = new GroupLayout(top);
		top.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		setValues(); // set all textFields to appropriate values
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(ioSortMb)
					.addComponent(ioSortRecPerc)
					.addComponent(mapReduceTasks)
					.addComponent(IOSortFactor)
					.addComponent(IOFileBufSize)
					.addComponent(mapredChildJavaOpts)
					.addComponent(mapredJobShufInputBufferPerc)
					.addComponent(mapredJobShufMergePerc)
					.addComponent(mapredInmemMergeThreshold)
					.addComponent(mapredJobReduceInputBufferPerc)
					.addComponent(dfsReplication)
					.addComponent(dfsBlockSize))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(ioSortMbTf)
					.addComponent(ioSortRecPercTf)
					.addComponent(mapReduceTasksTf)
					.addComponent(IOSortFactorTf)
					.addComponent(IOFileBufSizeTf)
					.addComponent(mapredChildJavaOptsTf)
					.addComponent(mapredJobShufInputBufferPercTf)
					.addComponent(mapredJobShufMergePercTf)
					.addComponent(mapredInmemMergeThresholdTf)
					.addComponent(mapredJobReduceInputBufferPercTf)
					.addComponent(dfsReplicationTf)
					.addComponent(dfsBlockSizeTf))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(ioSortMbDef)
					.addComponent(ioSortRecPercDef)
					.addComponent(mapReduceTasksDef)
					.addComponent(IOSortFactorDef)
					.addComponent(IOFileBufSizeDef)
					.addComponent(mapredChildJavaOptsDef)
					.addComponent(mapredJobShufInputBufferPercDef)
					.addComponent(mapredJobShufMergePercDef)
					.addComponent(mapredInmemMergeThresholdDef)
					.addComponent(mapredJobReduceInputBufferPercDef)
					.addComponent(dfsReplicationDef)
					.addComponent(dfsBlockSizeDef))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(ioSortMbChg)
					.addComponent(ioSortRecPercChg)
					.addComponent(mapReduceTasksChg)
					.addComponent(IOSortFactorChg)
					.addComponent(IOFileBufSizeChg)
					.addComponent(mapredChildJavaOptsChg)
					.addComponent(mapredJobShufInputBufferPercChg)
					.addComponent(mapredJobShufMergePercChg)
					.addComponent(mapredInmemMergeThresholdChg)
					.addComponent(mapredJobReduceInputBufferPercChg)
					.addComponent(dfsReplicationChg)
					.addComponent(dfsBlockSizeChg))
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ioSortMb)
						.addComponent(ioSortMbTf)
						.addComponent(ioSortMbDef)
						.addComponent(ioSortMbChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ioSortRecPerc)
						.addComponent(ioSortRecPercTf)
						.addComponent(ioSortRecPercDef)
						.addComponent(ioSortRecPercChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mapReduceTasks)
						.addComponent(mapReduceTasksTf)
						.addComponent(mapReduceTasksDef)
						.addComponent(mapReduceTasksChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(IOSortFactor)
						.addComponent(IOSortFactorTf)
						.addComponent(IOSortFactorDef)
						.addComponent(IOSortFactorChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(IOFileBufSize)
						.addComponent(IOFileBufSizeTf)
						.addComponent(IOFileBufSizeDef)
						.addComponent(IOFileBufSizeChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mapredChildJavaOpts)
						.addComponent(mapredChildJavaOptsTf)
						.addComponent(mapredChildJavaOptsDef)
						.addComponent(mapredChildJavaOptsChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mapredJobShufInputBufferPerc)
						.addComponent(mapredJobShufInputBufferPercTf)
						.addComponent(mapredJobShufInputBufferPercDef)
						.addComponent(mapredJobShufInputBufferPercChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mapredJobShufMergePerc)
						.addComponent(mapredJobShufMergePercTf)
						.addComponent(mapredJobShufMergePercDef)
						.addComponent(mapredJobShufMergePercChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mapredInmemMergeThreshold)
						.addComponent(mapredInmemMergeThresholdTf)
						.addComponent(mapredInmemMergeThresholdDef)
						.addComponent(mapredInmemMergeThresholdChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(mapredJobReduceInputBufferPerc)
						.addComponent(mapredJobReduceInputBufferPercTf)
						.addComponent(mapredJobReduceInputBufferPercDef)
						.addComponent(mapredJobReduceInputBufferPercChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(dfsReplication)
						.addComponent(dfsReplicationTf)
						.addComponent(dfsReplicationDef)
						.addComponent(dfsReplicationChg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(dfsBlockSize)
						.addComponent(dfsBlockSizeTf)
						.addComponent(dfsBlockSizeDef)
						.addComponent(dfsBlockSizeChg))
		);
		hadoopPanel.setLayout(new BoxLayout(hadoopPanel, BoxLayout.Y_AXIS));
		JPanel bottom = new JPanel();
		
		//add listeners
		
		saveListener(save);
		cancelListener(cancel);
		
		mapReduceTasksDef.addActionListener(this);
		mapReduceTasksChg.addActionListener(this);
		
		ioSortMbDef.addActionListener(this);
		ioSortMbChg.addActionListener(this);
		
		ioSortRecPercDef.addActionListener(this);
		ioSortRecPercChg.addActionListener(this);
		
		IOSortFactorDef.addActionListener(this);
		IOSortFactorChg.addActionListener(this);
				
		mapredChildJavaOptsDef.addActionListener(this);
		mapredChildJavaOptsChg.addActionListener(this);
		
		mapredJobShufInputBufferPercDef.addActionListener(this);
		mapredJobShufInputBufferPercChg.addActionListener(this);
		
		mapredJobShufMergePercDef.addActionListener(this);
		mapredJobShufMergePercChg.addActionListener(this);
		
		mapredInmemMergeThresholdDef.addActionListener(this);
		mapredInmemMergeThresholdChg.addActionListener(this);
		
		mapredJobReduceInputBufferPercDef.addActionListener(this);
		mapredJobReduceInputBufferPercChg.addActionListener(this);
		
		dfsReplicationDef.addActionListener(this);
		dfsReplicationChg.addActionListener(this);
		
		dfsBlockSizeDef.addActionListener(this);
		dfsBlockSizeChg.addActionListener(this);
		
		IOFileBufSizeDef.addActionListener(this);
		IOFileBufSizeChg.addActionListener(this);
		
		bottom.add(save);		
		bottom.add(cancel);		
		hadoopPanel.add(top);
		hadoopPanel.add(bottom);	
		//addActionListeners();		
	}

//__________________________________________________________________________________________
//------------------------------------------------------------------------------------------
//__________________________________________________________________________________________
//Action Listeners
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(mapReduceTasksDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(0);
			mapReduceTasksTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(mapReduceTasksChg)){
			HadoopConfigFile.getCurrentConfig().setValue(0, mapReduceTasksTf.getSelectedItem().toString());
			
		}
		
		
		else if (e.getSource().equals(ioSortMbDef)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setDefault(1);
			ioSortMbTf.setSelectedIndex(0);
		}		
		else if (e.getSource().equals(ioSortMbChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(1, ioSortMbTf.getSelectedItem().toString());
		}
		
		
		else if (e.getSource().equals(ioSortRecPercDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(2);
			ioSortRecPercTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(ioSortRecPercChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(2, ioSortRecPercTf.getSelectedItem().toString());
		}
		
		
		else if (e.getSource().equals(IOSortFactorDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(3);
			IOSortFactorTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(IOSortFactorChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(3, IOSortFactorTf.getSelectedItem().toString());
		}
				
		
		else if (e.getSource().equals(mapredChildJavaOptsDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(4);
			mapredChildJavaOptsTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(mapredChildJavaOptsChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(4, mapredChildJavaOptsTf.getSelectedItem().toString());
		}
		
		
		else if (e.getSource().equals(mapredJobShufInputBufferPercDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(5);
			mapredJobShufInputBufferPercTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(mapredJobShufInputBufferPercChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(5, mapredJobShufInputBufferPercTf.getSelectedItem().toString());
		}
		
		
		
		else if (e.getSource().equals(mapredJobShufMergePercDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(6);
			mapredJobShufMergePercTf.setSelectedIndex(0);		
		}
		else if (e.getSource().equals(mapredJobShufMergePercChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(6, mapredJobShufMergePercTf.getSelectedItem().toString());
		}
		
		
		
		else if (e.getSource().equals(mapredInmemMergeThresholdDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(7);
			mapredInmemMergeThresholdTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(mapredInmemMergeThresholdChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(7, mapredInmemMergeThresholdTf.getSelectedItem().toString());
		}
		
		
		else if (e.getSource().equals(mapredJobReduceInputBufferPercDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(8);
			mapredJobReduceInputBufferPercTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(mapredJobReduceInputBufferPercChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(8, mapredJobReduceInputBufferPercTf.getSelectedItem().toString());
		}
		
		
		else if (e.getSource().equals(dfsReplicationDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(9);
			dfsReplicationTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(dfsReplicationChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(9, dfsReplicationTf.getSelectedItem().toString());
		}
		
		
		
		else if (e.getSource().equals(dfsBlockSizeDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(10);
			dfsBlockSizeTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(dfsBlockSizeChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(10, dfsBlockSizeTf.getSelectedItem().toString());
		}		
		
		
		else if (e.getSource().equals(IOFileBufSizeDef)){
			HadoopConfigFile.getCurrentConfig().setDefault(11);
			IOFileBufSizeTf.setSelectedIndex(0);
		}
		else if (e.getSource().equals(IOFileBufSizeChg)){ //value = 1
			HadoopConfigFile.getCurrentConfig().setValue(11, IOFileBufSizeTf.getSelectedItem().toString());
		}
		
		
	}

//__________________________________________________________________________________________
//------------------------------------------------------------------------------------------
//__________________________________________________________________________________________
//Add value method -> gets values for the comboboxes
	
	

	
	private static void addValue(JComboBox<Comparable> box, int index){
		HadoopProperty hp = HadoopConfigFile.getCurrentConfig().getProperty(index);
		float low = hp.getLowValue();
		float high = hp.getHighValue();
		
		boolean foundSelected = false;
		
		switch(index){
			case 0: case 1:	case 3:
				for (int i=(int)low; i<=(int)high; i+=1){
					box.addItem(Integer.toString(i));
					if (!foundSelected){
						if (Integer.toString(i).equals(hp.getValue())){
							foundSelected=true;
							box.setSelectedIndex(i);
						}
					}
				}
				break;			
			case 2:
				float values[] = {.05f,0.06f,0.07f,0.08f,0.09f,.1f,.11f,.12f,.13f,.14f,.15f};
				for (int i=0; i<values.length; i++){
					box.addItem(Objects.toString(values[i]));
					if (!foundSelected){
						if (Integer.toString(i).equals(hp.getValue())){
							foundSelected=true;
							box.setSelectedIndex(i);
						}
					}
				}
				break;
			case 4:
				for (int i=(int)low; i<=(int)high; i+=1){
					box.addItem("-Xmx"+Integer.toString(i)+"m");
					if (!foundSelected){
						if (Integer.toString(i).equals(hp.getValue())){
							foundSelected=true;
							box.setSelectedIndex(i);
						}
					}
					
				}
				break;
			case 5: case 6: case 7: 
			case 8: case 9: case 10:
			case 11:
				
				box.addItem(low);
				box.addItem(high);				
				
					if (hp.getValueAsFloat()==low){
						
						box.setSelectedIndex(0);
					}
					else{
						box.setSelectedIndex(1);
					}
				
				
				break;
			
		}		
	}
	
	public static void cancelListener(JButton cancel){
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//resets all changed text fields to what they were when the currentConfig was last saved or loaded.
				setValues();
				
				//reset to currentConfig -> from file
			}
		});
	}
	
	//TODO
	public static void saveListener(JButton save){
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//set values as Current Config and write to CurrentConfig file
				//then write values to XML
				//
				//Write currentConfig to file
			}
		});
	}
	
	//ACTION LISTENERS
	
	private static void setValues(){
		addValue(mapReduceTasksTf, 0);
		addValue(ioSortMbTf, 1);
		addValue(ioSortRecPercTf, 2);
		addValue(IOSortFactorTf, 3);
		addValue(mapredChildJavaOptsTf,4);
		addValue(mapredJobShufInputBufferPercTf,5);
		addValue(mapredJobShufMergePercTf,6);
		addValue(mapredInmemMergeThresholdTf,7);
		addValue(mapredJobReduceInputBufferPercTf,8);
		addValue(dfsReplicationTf,9);
		addValue(dfsBlockSizeTf,10);
		addValue(IOFileBufSizeTf,11);
	}
	
	
	
	JPanel getPanel(){
		return hadoopPanel;
	}
		
}
