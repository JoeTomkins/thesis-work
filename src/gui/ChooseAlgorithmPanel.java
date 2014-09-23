package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import def.Algorithm;

public class ChooseAlgorithmPanel extends JPanel implements ActionListener{
	JLabel number = new JLabel("Algorithm #");
	JLabel total = new JLabel("/");
	JLabel name = new JLabel("Name:");
	JLabel dist = new JLabel("Distributed: ");
	JLabel filepath = new JLabel("Filepath: ");
	
	JCheckBox distBox = new JCheckBox();
	JTextField numberTf = new JTextField();
	JTextField totalAlg = new JTextField();
	JTextField nameTf = new JTextField();
	JTextField filepathTf = new JTextField();
	
	JButton next = new JButton("Next");
	JButton prev = new JButton("Previous");
	
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	
	public void ChooseAlgorithmPanel(){		
		add(next);
		
		/*
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(number)
					.addComponent(name)
					.addComponent(filepath)
					.addComponent(dist)
					.addGroup(layout.createSequentialGroup()
							.addComponent(prev)
							.addComponent(next)))				
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addComponent(numberTf)
						.addComponent(totalAlg)
						.addComponent(totalAlg))
					.addComponent(nameTf)
					.addComponent(filepathTf)
					.addComponent(distBox)
					.addGroup(layout.createSequentialGroup()
							.addComponent(save)
							.addComponent(cancel)))
		);
		layout.linkSize(SwingConstants.HORIZONTAL, save, cancel);	
		layout.linkSize(SwingConstants.HORIZONTAL, prev, next);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(number)
						.addComponent(numberTf)
						.addComponent(total)
						.addComponent(totalAlg))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(name)
						.addComponent(nameTf))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(filepath)
						.addComponent(filepathTf))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(dist)
						.addComponent(distBox))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(prev)
						.addComponent(next)
						.addComponent(save)
						.addComponent(cancel))
		);
		*/
		
	}	
	
	
	
	
	
	
	public void setValues(Algorithm algorithm){
		numberTf.setText(Integer.toString(algorithm.getAlgorithmID()));
		nameTf.setText(algorithm.getName());
		filepathTf.setText(algorithm.getFilePath());
		if (algorithm.isDistributed()){
			distBox.setSelected(true);
		}
		else{
			distBox.setSelected(false);
		}
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
