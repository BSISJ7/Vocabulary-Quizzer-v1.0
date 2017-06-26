import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ReviewPanel {

	private JPanel panel;
	private JButton button;
	
	public ReviewPanel(ActionListener cmd){
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setOpaque(false);
		//panel.setPreferredSize(new Dimension(140,30));
		
		QuickSwing cust = new QuickSwing("Review Questions", VocabPrac.workDir+"\\bluebutton.jpg", 130, 35);
		button = cust.getButton();
		button.setActionCommand("review");
		button.addActionListener(cmd);
		button.setPreferredSize(new Dimension(130, 35));
		button.setToolTipText("Review questions that are wrong.");
		
		panel.add(button);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public JButton getButton(){
		return button;
	}
}
