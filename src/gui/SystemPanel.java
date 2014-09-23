package gui;

import javax.swing.JPanel;


public class SystemPanel extends JPanel{
	private static JPanel systemPanel;
	
	SystemPanel(){
		systemPanel = new JPanel();
	}
	
	JPanel getPanel(){
		return systemPanel;
	}
}
