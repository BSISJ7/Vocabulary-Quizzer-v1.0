import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowScore extends JFrame{

	private JFrame scoreFrame;
	private JPanel correctPanel;
	private JPanel scorePanel;

	public ShowScore(ActionListener acListener){
		scoreFrame = new JFrame();
		
		JPanel conRevPanel = new JPanel();
		correctPanel = new JPanel();	
		JPanel backPanel = new JPanel();
		scorePanel = new JPanel();
		
		JButton cont = new JButton();
		JButton review = new JButton();

		JLabel correctLabel = new JLabel();
		
		QuickSwing cust = new QuickSwing("Continue", "C:\\Program Files\\LangPrac\\CustButton.jpg");
		cont = cust.getButton();

		cust = new QuickSwing("Review", "C:\\Program Files\\LangPrac\\CustButton.jpg");
		review = cust.getButton();
		
		cust = new QuickSwing("Correct", "C:\\Program Files\\LangPrac\\CustButton.jpg", 250, 35);
		correctLabel = cust.getLabel();
		
		JButton revLaterButton = new JButton();
		revLaterButton = new QuickSwing().getButton("Set Review Date", "C:\\Program Files\\LangPrac\\CustButton.jpg", 250, 35);
		revLaterButton.addActionListener(acListener);
		revLaterButton.setActionCommand("revLater");
		JPanel revLaterPanel = new JPanel();
		revLaterPanel.setOpaque(false);
		revLaterPanel.setPreferredSize(new Dimension(250, 35));
		revLaterPanel.add(revLaterButton);
		
		cont.setToolTipText("Restarts Test");
		review.setToolTipText("Review Wrong Answers");

		cont.setActionCommand("continueTest");
		review.setActionCommand("review");

		cont.addActionListener(acListener);
		review.addActionListener(acListener);
		
		scoreFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		scorePanel.setLayout(new GridLayout(5,0));
		scoreFrame.setLayout(new GridLayout());
		
		correctPanel.setOpaque(false);
		scorePanel.setOpaque(false);
		backPanel.setOpaque(false);
		conRevPanel.setOpaque(false);
		
		//scoreFrame.getContentPane().setVisible(true);
		
		//scoreFrame.getContentPane().setPreferredSize(new Dimension(500,250));
		scorePanel.setPreferredSize(new Dimension(500,260));
		cont.setPreferredSize(new Dimension(180,40));
		review.setPreferredSize(new Dimension(180,40));
		correctLabel.setPreferredSize(new Dimension(250,35));
		
		//scoreFrame.add(scorePanel);
		correctPanel.add(correctLabel);
		conRevPanel.add(cont);
		conRevPanel.add(review);
		scorePanel.add(correctPanel);
		scorePanel.add(conRevPanel);
		scorePanel.add(backPanel);
		scorePanel.add(revLaterPanel);
	}
	
	public void resetCorrectPanel(String message){
		QuickSwing cust = new QuickSwing(message, "C:\\Program Files\\LangPrac\\CustButton.jpg", 250, 35);
		JLabel correctLabel = cust.getLabel();
		
		correctPanel.setVisible(false);
		correctPanel.add(correctLabel);
		correctPanel.repaint();
		correctPanel.setVisible(true);
	}
	
	public JFrame getFrame(){
		return scoreFrame;
	}
	
	public JPanel getPanel(){
		return scorePanel;
	}
}
