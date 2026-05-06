import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindSuspect extends JFrame{
	
	private JPanel panel= new JPanel();
	private JTextField inputField = new JTextField("Plese enter suspect's name");
	private JButton confirm = new JButton("FIND");
	private JButton visualizeNetwork = new JButton("Visualize Network");
	
	private Registry registry;
	
	
	
	public FindSuspect(Registry registry){
		
		this.registry = registry;
		ButtonListener listener = new ButtonListener();
		confirm.addActionListener(listener);
		visualizeNetwork.addActionListener(listener);
		
		panel.add(inputField);
		panel.add(confirm);
		panel.add(visualizeNetwork);
		
		this.setContentPane(panel);
		this.setSize(300,150);
		this.setTitle("Find Suspect");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
				
	}
	
	class ButtonListener implements ActionListener{
		

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==confirm) {
				String suspName = inputField.getText();
				boolean flag = false;
				for(Suspect susps:registry.getSuspects()) {
					if(suspName.equals(susps.getName())) {
						flag = true;
					}
					
				}
				if(flag == true) {
					new SuspectPage(registry, suspName);
					dispose();
						
				}else {
					JOptionPane.showMessageDialog(null," Suspect " +suspName+ " Not Found");
				}
			}
			if(e.getSource()==visualizeNetwork) {
				new VisualNetwork(registry);
				dispose();
			}
			
		}
		
	}

}
