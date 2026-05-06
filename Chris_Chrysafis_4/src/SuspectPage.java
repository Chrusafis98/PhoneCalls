import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SuspectPage extends JFrame {
	
	private Registry registry;
	private String name;
	private Suspect suspect;
	
	private JPanel panel1;
	private JTextField suspNameField;
	private JTextField suspCodeNameField;
	private JTextArea suspPhones;
	
	private JPanel panel2;
	private JTextField searchNumber;
	private JTextArea suspMassage;
	private JButton searchMassage;
	
	private JPanel panel3;
	private JLabel partnersField;
	private JTextArea partnerField;
	
	private JPanel panel4;
	private JLabel suggestedPartner;
	private JTextArea resultsSuggPartners;
	
	private JPanel panel5;
	private JTextArea suspectFromCountry;
	
	private JPanel mainPanel;
	private JButton ReturnToHomeScreen;
	
	public SuspectPage(Registry registry ,String suspName) {
		
		this.registry = registry;
		this.name = suspName;
		
		ButtonListener listener = new ButtonListener();
		for(Suspect sus:registry.getSuspects()) {
			if(suspName.equals(sus.getName())) {
				suspect = sus;
			}
			
		}
		//panel 1
		suspNameField = new JTextField(suspect.getName());
		suspCodeNameField = new JTextField(suspect.getCodeName());
		suspPhones = new JTextArea(3,10);
		
		for(String phone:suspect.getPhones()) {
			suspPhones.append(phone + "\n");
		}
		panel1 = new JPanel();
		panel1.add(suspNameField);
		panel1.add(suspCodeNameField);
		panel1.add(suspPhones);
		panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//panel 2
		searchNumber = new JTextField("");
		searchNumber.setPreferredSize(new Dimension(100,24));
		suspMassage = new JTextArea(10,20);
		searchMassage = new JButton("Find SMS");
		searchMassage.addActionListener(listener);
		
		panel2 = new JPanel();
		panel2.add(searchNumber);
		panel2.add(suspMassage);
		panel2.add(searchMassage);
		panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//panel 3
		partnersField = new JLabel("Partners");
		partnerField = new JTextArea(10,20);
		for(Suspect partners:suspect.getPartners()) {
			partnerField.append(partners.getName() + " , " + partners.getCodeName() + "\n");
			
		}
		
		panel3 = new JPanel();
		panel3.add(partnersField);
		panel3.add(partnerField);
		panel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//panel 4
		suggestedPartner = new JLabel("Suggested Partners ---->");
		resultsSuggPartners = new JTextArea(10 , 20);
		
		for(Suspect suggPartners:suspect.SuggestedPartners()) {
			resultsSuggPartners.append(suggPartners.getName() + "\n");
		}
		panel4 = new JPanel();
		panel4.add(suggestedPartner);
		panel4.add(resultsSuggPartners);
		panel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//panel 5
		suspectFromCountry = new JTextArea(6,30);
		suspectFromCountry.append("Suspects coming from" + suspect.getCountry()+ "\n");
		for(Suspect suspects:registry.getSuspects()) {
			if(suspects.getCountry().equals(suspect.getCountry()) ) {
				suspectFromCountry.append(suspects.getName() + "\n");
				
			}
			
		}
		
		panel5 = new JPanel();
		panel5.add(suspectFromCountry);
		panel5.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		
		
		ReturnToHomeScreen = new JButton("Return to Search Screen");
		ReturnToHomeScreen.addActionListener(listener);
		
		mainPanel = new JPanel();
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		mainPanel.add(ReturnToHomeScreen);
		
		this.setContentPane(mainPanel);
		this.setSize(500,850);
		this.setTitle("Suspect Page");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	class ButtonListener implements ActionListener{
		

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==searchMassage) {
				String numb = searchNumber.getText();
				ArrayList<SMS>  sms = new ArrayList<SMS>();
				for(String phone:suspect.getPhones()) {
					for(SMS massages:registry.getMessagesBetween(phone, numb )){
						sms.add(massages);
					}
				}
				for(SMS massage:sms) {
					suspMassage.append(massage.getMassage()+"\n");
				}
				
			}
			if(e.getSource()== ReturnToHomeScreen) {
				new FindSuspect(registry);
				setVisible(true);
				
				dispose();
			}
			
		}
   }

}
