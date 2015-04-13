package gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;




public class MainGui{
	static JPanel info;
	//panels are held in separate classes for extra modularity
	private static JPanel results; // panel where results are shown
	static JPanel system;
	
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("programName");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainGui mg = new MainGui();
		mg.addComponents(frame.getContentPane());
		
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
	}

	public static void start(){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				createAndShowGUI();
			}
		});
	}
	
	private void addComponents(Container pane) {
		JTabbedPane tabbedPane = new JTabbedPane();
		/*runPanel = new JPanel(){
			public Dimension getPreferredSize() {
				Dimension size = super.getPreferredSize();
				size.width += extraWindowWidth;
				return size;
			}
		};*/
		tabbedPane.addTab("Run", addRunPanel());		
		tabbedPane.addTab("Results", addResultsPanel());
		tabbedPane.addTab("Hadoop",  addHadoopPanel());
		tabbedPane.addTab("System", addSystemPanel());
		tabbedPane.addTab("Datasets/Algorithms", addDandAPanel());
		pane.add(tabbedPane, BorderLayout.CENTER);
	}

	private JPanel addHadoopPanel(){
		HadoopPanel hadoopPanel = new HadoopPanel();		
		return hadoopPanel.getPanel();
	}
	
	private JPanel addResultsPanel(){
		ResultsPanel resultsPanel = new ResultsPanel();
		
		return resultsPanel;
	}
	
	private JPanel addRunPanel(){
		RunPanel runPanel = new RunPanel();
		return runPanel.getPanel();
	}
	
	private JPanel addDandAPanel(){
		DandAPanel dAndAPanel = new DandAPanel();
		return dAndAPanel.getPanel();
	}
	
	
	private JPanel addSystemPanel(){
		SystemPanel systemPanel = new SystemPanel();		
		return systemPanel.getPanel();
	}
	
}
