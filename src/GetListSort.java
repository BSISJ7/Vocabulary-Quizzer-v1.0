import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

public class GetListSort implements ActionListener{
	
	private JPanel mainPanel;
	private QuickSwing getSwing = new QuickSwing();
	
	private Vocabulary selectedVocab;
	private VocabList selectedList;
	
	private JTextField getSortNameVocab;
	private JTextField getSortNameList;
	
	public GetListSort(){
		System.out.println("HERE");
		JFrame testFrame = new JFrame();
		selectedList = new VocabList();
		selectedVocab = new Vocabulary();
		
		//mainPanel = getSwing.getPanel("clouds.jpg", 450, 650);
		mainPanel = new JPanel();
		setup();
		mainPanel.setOpaque(false);
		mainPanel.setVisible(true);
		testFrame.add(mainPanel);
		testFrame.pack();
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(new JFrame().EXIT_ON_CLOSE);
		System.out.println("HERE");
	}
	
	public void setup(){
		
		JPanel vocabButtonPanel = new JPanel();
		vocabButtonPanel.setOpaque(false);
		vocabButtonPanel.setPreferredSize(new Dimension(140, 60));

		JButton addSortVocab = getSwing.getButton("Add Vocab Group" ,VocabPrac.workDir+"\\bluebutton.jpg", 140, 30);
		addSortVocab.addActionListener(this);
		addSortVocab.setActionCommand("addSortVocab");
		vocabButtonPanel.add(addSortVocab);
		
		JButton deleteSortVocab = getSwing.getButton("Delete Vocab Group" ,VocabPrac.workDir+"\\bluebutton.jpg", 140, 30);
		deleteSortVocab.addActionListener(this);
		deleteSortVocab.setActionCommand("deleteSortVocab");
		vocabButtonPanel.add(deleteSortVocab);

		
		JPanel listButtonPanel = new JPanel();
		listButtonPanel.setOpaque(false);
		listButtonPanel.setPreferredSize(new Dimension(140, 60));
		
		JButton addSortList = getSwing.getButton("Add List Group" ,VocabPrac.workDir+"\\bluebutton.jpg", 140, 30);
		addSortList.addActionListener(this);
		addSortList.setActionCommand("addSortList");
		listButtonPanel.add(addSortList);
		
		JButton deleteSortList = getSwing.getButton("Delete List Group" ,VocabPrac.workDir+"\\bluebutton.jpg", 140, 30);
		deleteSortList.addActionListener(this);
		deleteSortList.setActionCommand("deleteSortList");
		listButtonPanel.add(deleteSortList);
		
		
		
		JPanel getSortNameVocabPanel = new JPanel();
		getSortNameVocabPanel.setOpaque(false);
		getSortNameVocabPanel.setPreferredSize(new Dimension(240, 40));
		getSortNameVocab = new JTextField("");
		getSortNameVocab.setPreferredSize(new Dimension(240, 40));
		getSortNameVocabPanel.add(getSortNameVocab);
		
		JPanel getSortNameListPanel = new JPanel();
		getSortNameListPanel.setOpaque(false);
		getSortNameListPanel.setPreferredSize(new Dimension(240, 40));
		getSortNameList = new JTextField("");
		getSortNameList.setPreferredSize(new Dimension(240, 40));
		getSortNameListPanel.add(getSortNameList);
		
		
		JPanel displayInfoVocabPanel = new JPanel();
		displayInfoVocabPanel.setOpaque(false);
		displayInfoVocabPanel.setPreferredSize(new Dimension(320, 90));
		JLabel displayInfoVocab = getSwing.getLabel("Enter the name of a vocabulary grouping" ,VocabPrac.workDir+"\\bluebutton.jpg", 320, 34);
		displayInfoVocabPanel.add(displayInfoVocab);
		JLabel displayInfoVocabII = getSwing.getLabel("add or delete." ,VocabPrac.workDir+"\\bluebutton.jpg", 320, 34);
		displayInfoVocabPanel.add(displayInfoVocabII);
		
		JPanel displayInfoListPanel = new JPanel();
		displayInfoListPanel.setOpaque(false);
		displayInfoListPanel.setPreferredSize(new Dimension(320, 45));
		JLabel displayInfoList = getSwing.getLabel("Enter the name of a vocabulary list" ,VocabPrac.workDir+"\\bluebutton.jpg", 320, 34);
		displayInfoListPanel.add(displayInfoList);
		JLabel displayInfoListII = getSwing.getLabel("grouping to add or delete." ,VocabPrac.workDir+"\\bluebutton.jpg", 320, 34);
		displayInfoListPanel.add(displayInfoListII);
		
		
		mainPanel.setLayout(new GridLayout(6,1));
		mainPanel.add(displayInfoVocabPanel);
		mainPanel.add(getSortNameVocabPanel);
		mainPanel.add(vocabButtonPanel);
		mainPanel.add(displayInfoListPanel);
		mainPanel.add(getSortNameListPanel);
		mainPanel.add(listButtonPanel);
	}
	
	public void actionPerformed(ActionEvent event){
		String getEvent = event.getActionCommand();
		
		if(getEvent.equals("addSortVocab")){
			
		}
		else if(getEvent.equals("deleteSortVocab")){
			
		}
		else if(getEvent.equals("addSortList")){
			
		}
		else if(getEvent.equals("deleteSortList")){
			
		}
	}

	public void setList(VocabList getList){
		selectedList = getList;
	}
	
	public void setVocab(Vocabulary getVocab){
		selectedVocab = getVocab;
	}

	/*public static void main(String args[]){
		
		GetListSort get = new GetListSort();
	}*/
	
	public JPanel getPanel(){
		return mainPanel;
	}
	
	public void resetFields(){
		getSortNameVocab.setText("");
		getSortNameList.setText("");
	}
}


