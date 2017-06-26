import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

public class VocabEditor {

	private JButton acceptButton;

	public void editVocabII(){
		acceptButton.setActionCommand("acceptEdit");
	}
	public void editListII(){
		acceptButton.setActionCommand("listEdit");
	}
	
	public void editVocab(JPanel mainPanel, JTextField vocabName, JTextField matchingWord, JEditorPane vocabDefinition,
			JEditorPane vocabSynonyms, ActionListener ACL){

		mainPanel.removeAll();
		
		JPanel addPanel = new JPanel(){
		protected void paintComponent(Graphics g)
			{
			Dimension d = getSize();
				g.drawImage(new ImageIcon(VocabPrac.workDir+"\\clouds.jpg").getImage(), 0, 0, d.width, d.height, null);
			
			super.paintComponent(g);
			}
		};

		JLabel vocabNameLabel = new JLabel("Vocabulary Name");
		QuickSwing cust = new QuickSwing("Vocabulary Name", "");
		
		
		JLabel matchingWordLabel = new JLabel("Matching Word");
		JLabel vocabDefinitionLabel = new JLabel("Definition");
		JLabel vocabSynonymsLabel = new JLabel("Synonyms");
		JLabel vocabGroupsLabel = new JLabel("Groups");
		
		JPanel vocabNamePanel = new JPanel();
		JPanel matchingWordPanel = new JPanel();
		JPanel vocabDefinitionPanel = new JPanel();
		JPanel vocabSynonymsPanel = new JPanel();
		JPanel acceptCancelPanel = new JPanel();
		JPanel vocabGroupsPanel = new JPanel();

		JScrollPane vocabDefinitionSPane = new JScrollPane(vocabDefinition);
		JScrollPane vocabSynonymsSPane = new JScrollPane(vocabSynonyms);
		
		
		vocabDefinitionSPane.getViewport().setOpaque(false);
		vocabSynonymsSPane.getViewport().setOpaque(false);
		vocabDefinitionSPane.setOpaque(false);
		vocabSynonymsSPane.setOpaque(false);
		vocabDefinition.setOpaque(false);
		vocabSynonyms.setOpaque(false);
		vocabName.setOpaque(false);
		matchingWord.setOpaque(false);
		
		vocabDefinitionSPane.getVerticalScrollBar().setUnitIncrement(7);
		vocabDefinitionSPane.getHorizontalScrollBar().setUnitIncrement(7);
		vocabSynonymsSPane.getVerticalScrollBar().setUnitIncrement(7);
		vocabSynonymsSPane.getHorizontalScrollBar().setUnitIncrement(7);
		

		cust = new QuickSwing("Accept", VocabPrac.workDir+"\\bluebutton.jpg", 180, 30);
		acceptButton = cust.getButton();
		cust = new QuickSwing("Cancel", VocabPrac.workDir+"\\bluebutton.jpg", 180, 30);
		JButton cancelButton = cust.getButton();

		acceptButton.setPreferredSize(new Dimension(180, 30));
		cancelButton.setPreferredSize(new Dimension(180, 30));
		
		acceptButton.addActionListener(ACL);
		cancelButton.addActionListener(ACL);

		acceptButton.setActionCommand("acceptVocab");
		cancelButton.setActionCommand("cancelVocab");
		
		vocabGroupsPanel.setPreferredSize(new Dimension(210, 100));
		vocabName.setPreferredSize(new Dimension(210, 20));
		matchingWord.setPreferredSize(new Dimension(210, 20));
		vocabDefinition.setPreferredSize(new Dimension(550, 250));
		vocabSynonyms.setPreferredSize(new Dimension(550, 250));
		vocabDefinitionSPane.setPreferredSize(new Dimension(550, 200));
		vocabSynonymsSPane.setPreferredSize(new Dimension(550, 200));
		vocabDefinitionLabel.setPreferredSize(new Dimension(100, 20));
		vocabSynonymsLabel.setPreferredSize(new Dimension(100, 20));
		

		vocabName.setBackground(new Color(1).RED);
		matchingWord.setBackground(new Color(1).WHITE);
		//vocabDefinition.setBackground(new Color(1).WHITE);
		//vocabSynonyms.setBackground(new Color(1).WHITE);
		
		vocabName.setForeground(new Color(1).WHITE);
		matchingWord.setForeground(new Color(1).WHITE);
		vocabDefinition.setForeground(new Color(1).WHITE);
		vocabSynonyms.setForeground(new Color(1).WHITE);
		
		vocabNameLabel.setForeground(new Color(1).WHITE);
		matchingWordLabel.setForeground(new Color(1).WHITE);
		vocabDefinitionLabel.setForeground(new Color(1).WHITE);
		vocabSynonymsLabel.setForeground(new Color(1).WHITE);
		
		vocabGroupsPanel.add(vocabGroupsLabel);
		acceptCancelPanel.add(acceptButton);
		acceptCancelPanel.add(cancelButton);
		vocabNamePanel.add(vocabNameLabel);
		vocabNamePanel.add(vocabName);
		matchingWordPanel.add(matchingWordLabel);
		matchingWordPanel.add(matchingWord);
		vocabDefinitionPanel.add(vocabDefinitionLabel);
		vocabDefinitionPanel.add(vocabDefinitionSPane);
		vocabSynonymsPanel.add(vocabSynonymsLabel);
		vocabSynonymsPanel.add(vocabSynonymsSPane);
		
		addPanel.add(vocabNamePanel);
		addPanel.add(matchingWordPanel);
		addPanel.add(vocabDefinitionPanel);
		addPanel.add(vocabSynonymsPanel);
		addPanel.add(acceptCancelPanel);
		
		vocabNamePanel.setOpaque(false);
		matchingWordPanel.setOpaque(false);
		vocabDefinitionPanel.setOpaque(false);
		vocabSynonymsPanel.setOpaque(false);
		acceptCancelPanel.setOpaque(false);
		addPanel.setOpaque(false);
		
		//JScrollPane sFrame = new JScrollPane(addPanel);
		//sFrame.setOpaque(false);
		//sFrame.getViewport().setOpaque(false);
		//sFrame.setHorizontalScrollBarPolicy(new JScrollPane().HORIZONTAL_SCROLLBAR_NEVER );
		
		//addPanel.setLayout(new GridLayout(0,1,40,40));
		//vocabSynonymsPanel.setLayout(new GridLayout(2,0,0,0));
		//vocabDefinitionPanel.setLayout(new GridLayout(2,0,0,0));
		
		mainPanel.add(addPanel);
		mainPanel.setVisible(false);
		mainPanel.invalidate();
		mainPanel.repaint();
		mainPanel.validate();
		mainPanel.setVisible(true);
	}
	
	public void editList(JPanel mainPanel, JTextField listName, ActionListener ACL){

		mainPanel.removeAll();
		
		JPanel addPanel = new JPanel(){
		protected void paintComponent(Graphics g)
			{
			Dimension d = getSize();
			g.drawImage(new ImageIcon(VocabPrac.workDir+"\\clouds.jpg").getImage(), 0, 0, d.width, d.height, null);
			
			super.paintComponent(g);
			}
		};
		
		JLabel listNameLabel = new JLabel("List Name");
		
		JPanel listNamePanel = new JPanel();
		JPanel acceptCancelPanel = new JPanel();

		QuickSwing cust = new QuickSwing("Accept", VocabPrac.workDir+"\\bluebutton.jpg");
		acceptButton = cust.getButton();
		
		cust = new QuickSwing("Cancel", VocabPrac.workDir+"\\bluebutton.jpg");
		JButton cancelButton = cust.getButton();

		acceptButton.addActionListener(ACL);
		cancelButton.addActionListener(ACL);

		acceptButton.setPreferredSize(new Dimension(180,30));
		cancelButton.setPreferredSize(new Dimension(180,30));
		
		acceptButton.setActionCommand("acceptList");
		cancelButton.setActionCommand("cancelList");
		
		listName.setPreferredSize(new Dimension(210, 30));
		

		listName.setBackground(new Color(1).WHITE);
		
		listName.setForeground(new Color(1).WHITE);
		
		listNameLabel.setForeground(new Color(1).WHITE);
		
		acceptCancelPanel.add(acceptButton);
		acceptCancelPanel.add(cancelButton);
		listNamePanel.add(listNameLabel);
		listNamePanel.add(listName);
		
		addPanel.add(listNamePanel);
		addPanel.add(acceptCancelPanel);
		
		listNamePanel.setOpaque(false);
		acceptCancelPanel.setOpaque(false);
		addPanel.setOpaque(false);
		listName.setOpaque(false);
		
		JScrollPane scroll = new JScrollPane(addPanel);
		scroll.setPreferredSize(new Dimension(300,600));
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setVisible(true);
		
		addPanel.setLayout(new GridLayout(0,1,40,40));
		
		mainPanel.add(addPanel);
		mainPanel.setVisible(false);
		mainPanel.invalidate();
		mainPanel.repaint();
		mainPanel.validate();
		mainPanel.setVisible(true);
	}
}
