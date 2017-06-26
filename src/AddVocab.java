import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class AddVocab implements ActionListener{
	
	private int listIndex;
	private int vocabIndex;
	private int totalVocab;
	private int listSize;//number of vocabulary lists in the array
	private int[] rand;
	private int testSize;
	
	private Vocabulary data;
	private VocabList[] lists;
	private Vocabulary[] vocabData;
	
	private JEditorPane vocabDefinition;
	private JEditorPane vocabSynonyms;
	private JEditorPane ePane;
	
	private JTextField matchingWord;
	private JTextField listName;
	private JTextField vocabName;
	
	private final static String workDir = System.getProperty("user.dir");
	private final static String homeDir = System.getProperty("user.home");

	final ImageIcon churchBG = new ImageIcon(workDir+"\\fullchurch.jpg");

	private JComboBox listCBox;

	private ActionListener acListener;

	private JComboBox vocabCBox;

	private JPanel deletePanel;
	private JPanel listCBoxPanel;
	private JPanel splitPane;
	private JPanel selectionPanel;
	private JPanel addPanel;
	private JPanel vocabCBoxPanel;
	private JPanel delEditPanel;
	private JPanel mainPanel;
	private JPanel backPanel;

	private JLabel displayVocabInfo;
	private JLabel vocabNameLabel;
	private JLabel displayInfo;

	private String[] vocabNames;
	private String[] listNames;
	private JPanel CBoxPanel;
	private JPanel delEditVocabPanel;
	private boolean newList;
	private JLabel listNameLabel;
	private JPanel practicePanel;
	
	private QuickSwing getSwing = new QuickSwing();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public AddVocab(){
				
		listName = new JTextField("");
		vocabName = new JTextField("");
		matchingWord = new JTextField("");
		vocabDefinition = new JEditorPane();
		vocabSynonyms = new JEditorPane();
		
		mainPanel = new JPanel();
		vocabData = new Vocabulary[0];
		
		mainPanel.setOpaque(false);
		mainPanel.setVisible(true);
	}

	public void getActList(ActionListener acList){
		acListener = acList;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public JPanel getVocab(){
	
		loadLists();
		
	 	GetVocabList getData = new GetVocabList(lists);
	 	listNames = getData.getList();
	 	
		vocabNames = new String[] {"Vocabulary"};
		
		displayInfo = new JLabel("Currently Loaded Vocabulary List: Vocabulary Lists");

		displayVocabInfo = new JLabel("Currently Loaded Vocab: " +"Vocabulary");

		JButton deleteVocabButton = getSwing.getButton("Delete Vocab", VocabPrac.workDir+"\\bluebutton.jpg", 160, 30);
		deleteVocabButton.setPreferredSize(new Dimension(160,30));
		
		JButton editVocabButton = getSwing.getButton("Edit Vocab", VocabPrac.workDir+"\\bluebutton.jpg", 160, 30);
		editVocabButton.setPreferredSize(new Dimension(160,30));
		
		JButton deleteButton = getSwing.getButton("Delete List", VocabPrac.workDir+"\\bluebutton.jpg", 160, 30);
		deleteButton.setPreferredSize(new Dimension(160,30));
		
		JButton editButton = getSwing.getButton("Edit List", VocabPrac.workDir+"\\bluebutton.jpg", 160, 30);
		editButton.setPreferredSize(new Dimension(160,30));
		
		JButton addListButton = getSwing.getButton("Add New Vocabulary List", VocabPrac.workDir+"\\bluebutton.jpg", 210, 30);
		addListButton.setPreferredSize(new Dimension(210,30));

		JButton addVocabButton = getSwing.getButton("Add New Vocabulary Word", VocabPrac.workDir+"\\bluebutton.jpg", 210, 30);
		addVocabButton.setPreferredSize(new Dimension(210,30));
		
		JButton backButton = getSwing.getButton("Back", VocabPrac.workDir+"\\bluebutton.jpg", 160, 30);
		backButton.setPreferredSize(new Dimension(160,30));

		JButton practiceButton = getSwing.getButton("Practice List", VocabPrac.workDir+"\\bluebutton.jpg", 160, 30);
		practiceButton.setPreferredSize(new Dimension(160,30));
		
	 	JPanel delVocabPanel = new JPanel();
	 	JPanel editVocabPanel = new JPanel();
	 	JPanel delPanel = new JPanel();
	 	JPanel editPanel = new JPanel();
	 	backPanel = new JPanel();
	 	delEditPanel = new JPanel();
	 	delEditVocabPanel = new JPanel();
		addPanel = new JPanel();
		CBoxPanel = new JPanel();
		vocabCBoxPanel = new JPanel();
		listCBoxPanel = new JPanel();
		selectionPanel = new JPanel();
		practicePanel = new JPanel();
		
		practiceButton.addActionListener(acListener);
		backButton.addActionListener(acListener);
		deleteButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteVocabButton.addActionListener(this);
		editVocabButton.addActionListener(this);

		practiceButton.setActionCommand("listLoad");
		backButton.setActionCommand("back");
		editButton.setActionCommand("editList");
		deleteButton.setActionCommand("deleteList");
		editVocabButton.setActionCommand("editVocab");
		deleteVocabButton.setActionCommand("deleteVocab");
				
		splitPane = new JPanel();
		splitPane.add(delEditPanel);
		splitPane.add(delEditVocabPanel);
		splitPane.add(addPanel);
		splitPane.setOpaque(false);
		
		addListButton.setActionCommand("addList");
		addListButton.addActionListener(this);

		addVocabButton.setActionCommand("addVocab");
		addVocabButton.addActionListener(this);
		
		listIndex = -1;
	 	
	 	listCBox = new JComboBox(listNames);

	 	listCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				String cBox = (String) cb.getSelectedItem();
				listIndex = cb.getSelectedIndex()-1;
				displayInfo.setText("Currently Loaded Vocabulary List: " + listCBox.getSelectedItem());
				resetVocab();
			}
		});
	 	
	 	vocabIndex = -1;
	 	
		
	 	vocabCBox = new JComboBox(vocabNames);
	 	
	 	vocabCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				String cBox = (String) cb.getSelectedItem();
				vocabIndex = cb.getSelectedIndex() - 1;
				try{
					displayVocabInfo.setText("Currently Loaded Vocab: " + cBox);
				}catch(ArrayIndexOutOfBoundsException AIN){
					displayVocabInfo.setText("Currently Loaded Vocabulary List: Vocabulary Lists");
				}
			}
		});
 	
	 	CBoxPanel.setLayout(new GridLayout(2,0));
	 	
	 	displayVocabInfo.setForeground(new Color(1).WHITE);
	 	displayInfo.setForeground(new Color(1).WHITE);
	 	
	 	mainPanel.add(selectionPanel);

	 	CBoxPanel.add(listCBoxPanel);
	 	CBoxPanel.add(vocabCBoxPanel);
	 	vocabCBoxPanel.add(vocabCBox);
		listCBoxPanel.add(listCBox);
	 	selectionPanel.add(CBoxPanel);
	 	vocabCBoxPanel.add(displayVocabInfo);
	 	listCBoxPanel.add(displayInfo);

	 	listCBoxPanel.setOpaque(false);
	 	vocabCBoxPanel.setOpaque(false);
	 	vocabCBox.setOpaque(false);
		displayInfo.setOpaque(false);
		selectionPanel.setOpaque(false);
		addPanel.setOpaque(false);
		editPanel.setOpaque(false);
		delPanel.setOpaque(false);
		editVocabPanel.setOpaque(false);
		delVocabPanel.setOpaque(false);
		delEditPanel.setOpaque(false);
		CBoxPanel.setOpaque(false);
		backPanel.setOpaque(false);
		delEditVocabPanel.setOpaque(false);
		splitPane.setOpaque(false);
		practicePanel.setOpaque(false);
			
		addPanel.add(addListButton);
		addPanel.add(addVocabButton);
		delVocabPanel.add(deleteVocabButton);
		editVocabPanel.add(editVocabButton);
		delPanel.add(deleteButton);
		editPanel.add(editButton);
		delEditPanel.add(delPanel);
		delEditPanel.add(editPanel);
		backPanel.add(backButton);	
		delEditVocabPanel.add(delVocabPanel);
		delEditVocabPanel.add(editVocabPanel);	
		practicePanel.add(practiceButton);	

		mainPanel.add(addPanel);
		mainPanel.add(delEditPanel);
		mainPanel.add(delEditVocabPanel);
		mainPanel.add(practicePanel);
		mainPanel.add(backPanel);
		mainPanel.setLayout(new GridLayout(0,1));

		return mainPanel;
		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public VocabList getList(){
		return lists[listIndex];
	}
	
	public VocabList[] getLists(){
		return lists;
	}

	public int getListIndex(){
		return listIndex;
	}

	public int getSelectedIndex(){
		return listCBox.getSelectedIndex();
	}
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void loadLists(){
		
		if(new File(VocabPrac.workDir+"\\Test Files\\Tests.Dat").exists()){
			try{
				ObjectInputStream loadData = new ObjectInputStream(new FileInputStream(VocabPrac.workDir+"\\Test Files\\Tests.Dat"));
				lists = (VocabList[])loadData.readObject();
				loadData.close();

			}catch(IOException ex){}
			catch(ClassNotFoundException ex){}
			catch(NullPointerException ex){}
		}
		else{
			lists = new VocabList[0];
			
			if(!new File(VocabPrac.workDir+"\\Test Files").exists()){
					new File(VocabPrac.workDir+"\\Test Files").mkdir();
				
				try{
					ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream(VocabPrac.workDir+"\\Test Files\\Tests.Dat"));
					saveData.writeObject(lists);
					saveData.flush();
					saveData.close();
					
				}catch(IOException ex){
					 ex.printStackTrace();
				}
			}
		}
		try{
		listSize = lists.length;
		}catch(NullPointerException NPE){
			listSize = 0;
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void saveLists(){
		try{
			ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream(VocabPrac.workDir+"\\Test Files\\Tests.Dat"));
			saveData.writeObject(lists);
			saveData.flush();
			saveData.close();
			
		}catch(IOException ex){
			 ex.printStackTrace();
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void newList(){
		VocabEditor vbEdit = new VocabEditor();
		vbEdit.editList(mainPanel, listName, this);
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void newVocab(){
		VocabEditor vbEdit = new VocabEditor();
		vbEdit.editVocab(mainPanel, vocabName, matchingWord, vocabDefinition, vocabSynonyms, this);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void mainMenuOff(){
		mainPanel.setVisible(false);
	}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void mainMenu(){
		mainPanel.removeAll();
		mainPanel.setVisible(false);
		mainPanel.add(selectionPanel);
		mainPanel.add(addPanel);
		mainPanel.add(delEditPanel);
		mainPanel.add(delEditVocabPanel);
		mainPanel.add(practicePanel);
		mainPanel.add(backPanel);
		mainPanel.invalidate();
		mainPanel.repaint();
		mainPanel.validate();
		mainPanel.setVisible(true);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void resetList(){

	 	//Reset Vocabulary List ComboBox
		listCBoxPanel.removeAll();
	 	GetVocabList getData = new GetVocabList(lists);
	 	listNames = getData.getList();	 	
	 	if(listIndex > -1){
	 		vocabNames = getData.getVocab(lists[listIndex]);
	 	}

		listCBox = new JComboBox(listNames);
		if(listCBox.getItemCount() == 1){
			listCBox.setSelectedIndex(0);
		}
		else{
			listCBox.setSelectedIndex(listCBox.getItemCount()-1);
		}
	 	
	 	listCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				String cBox = (String) cb.getSelectedItem();
				listIndex = cb.getSelectedIndex()-1;
				displayInfo.setText("Currently Loaded Vocabulary List: " + listCBox.getSelectedItem());
				resetVocab();
			}
		});
	 	listCBoxPanel.setVisible(true);
	 	listCBoxPanel.add(listCBox);
	 	listCBoxPanel.add(displayInfo);
		displayInfo.setText("Currently Loaded Vocabulary List: " + listCBox.getSelectedItem());
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void resetVocab(){
		
		vocabCBoxPanel.removeAll();
	 	GetVocabList getData = new GetVocabList(lists);

	 	if(listIndex > -1){
	 		vocabNames = getData.getVocab(lists[listIndex]);
	 	}
	 	else{
	 		vocabNames = new String[] {"Vocabulary"};
	 	}

		//set the combobox list to the select vocabulary list
	 	vocabCBox = new JComboBox(vocabNames);

	 	//select the last vocabulary item in the list
		 //vocabIndex = vocabCBox.getItemCount()-2;

		//if there is only one item select that item, else select what the index is
		if(vocabCBox.getItemCount() == 1){
			vocabCBox.setSelectedIndex(0);
		}
		else{
			vocabCBox.setSelectedIndex(vocabIndex+1);
		}

		displayVocabInfo.setText("Currently Loaded Vocab: " + vocabCBox.getSelectedItem());
		
		newList = false;
		
	 	vocabCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				JComboBox cb = (JComboBox) event.getSource();
				String cBox = (String) cb.getSelectedItem();
				vocabIndex = cb.getSelectedIndex() - 1;
				if(listIndex >= 0){
					if(lists[listIndex].getTotalVocab() != 0){
						displayVocabInfo.setText("Currently Loaded Vocab: "
								+ vocabCBox.getSelectedItem());
					}
					else{
						displayVocabInfo.setText("Currently Loaded Vocab: "
								+"Vocabulary");
					}
				}
				else{	
					displayVocabInfo.setText("Currently Loaded Vocab: "+"Vocabulary");	
				}
			}
		});

	 	vocabCBoxPanel.add(vocabCBox);
	 	vocabCBoxPanel.add(displayVocabInfo);
	 	displayVocabInfo.setVisible(true);

		mainMenu();
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionPerformed(ActionEvent event) {
		String source = event.getActionCommand();
		
		if(source.equals("deleteList")){
			if(listCBox.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(null, "No vocabulary list has been selected.");
			}
			else{
				DeleteList delete = new DeleteList(lists, listCBox.getSelectedIndex()-1);
				lists = delete.getLists();
				listIndex--;
				resetList();
				resetVocab();
				mainMenu();
			}
		}
		
		else if(source.equals("listEdit")){
			lists[listIndex].setName(listName.getText());

			saveLists();
			resetList();
			resetVocab();
			mainMenu();
		}
		
		else if(source.equals("editList")){
			if(listIndex  > -1){
				listName.setText(lists[listIndex].getName());
				listName.setOpaque(false);
				VocabEditor vbEdit = new VocabEditor();
				vbEdit.editList(mainPanel, listName, this);
				vbEdit.editListII();
			}
			else{
				JOptionPane.showMessageDialog(null, "A list must be selected in order to edit.");
			}
		}
		
		else if(source.equals("acceptList")){//Here
			if(!listName.getText().trim().equals("")){
				try{
					VocabList[] temp = new VocabList[lists.length];
					for(int x = 0; x < lists.length; x++){
						temp[x] = lists[x];
					}
					lists = new VocabList[temp.length+1];
					for(int x = 0; x < temp.length; x++){
						lists[x] = temp[x];
					}
					lists[lists.length-1] = new VocabList(listName.getText());
				}catch(NullPointerException NPE){
					lists = new VocabList[1];
					lists[0] = new VocabList(listName.getText());
				}
				
				saveLists();
				
				listIndex = lists.length-1;
				resetList();
				resetVocab();
				mainMenu();
			}
			else{
				JOptionPane.showMessageDialog(null, "This List Has Not Been Named Yet.");
			}
		}
		
		else if(source.equals("acceptVocab")){
			if(!vocabName.getText().trim().equals("")){
				vocabIndex = vocabCBox.getItemCount()-1;
				
				try{
					lists[listIndex].addVocab(vocabName.getText());
				}catch(NullPointerException NPE){
					lists = new VocabList[1];
					lists[0].addVocab(vocabName.getText());
				}
				
				lists[listIndex].getVocab(vocabIndex).setMatchingWord(matchingWord.getText());
				lists[listIndex].getVocab(vocabIndex).setDefinition(vocabDefinition.getText());
				lists[listIndex].getVocab(vocabIndex).setSynonyms(vocabSynonyms.getText());
				
				saveLists();
				resetList();
				resetVocab();
				mainMenu();
			}
			else{
				JOptionPane.showMessageDialog(null, "This Vocabulary Has Not Been Named Yet.");				
			}
		}
		
		else if(source.equals("cancelList")){
			mainMenu();
		}
		
		else if(source.equals("cancelVocab")){
			mainMenu();
		}

		else if(source.equals("addList")){
			listName.setText("");
			newList();
		}

		else if(source.equals("deleteVocab")){
			if(!displayVocabInfo.getText().equals("Currently Loaded Vocab: "+"Vocabulary")){
				lists[listIndex].deleteVocab(vocabIndex);
				if(vocabIndex == lists[listIndex].getTotalVocab()){
					vocabIndex--;
				}
				vocabIndex = vocabCBox.getItemCount()-3;
				resetVocab();
			}
			else{
				JOptionPane.showMessageDialog(null, "No Vocabulary List Has Been Selected.");			
			}
		}
		
		else if(source.equals("addVocab")){

			listName.setText("");
			vocabName.setText("");
			matchingWord.setText("");
			vocabDefinition.setText("");
			vocabSynonyms.setText("");
			
			newList = true;
			
			if(!displayInfo.getText().equals("Currently Loaded Vocabulary List: Vocabulary Lists")){
				newVocab();
			}
			else
				JOptionPane.showMessageDialog(null, "No Vocabulary List Has Been Selected.");
		}
		
		else if(source.equals("acceptEdit")){
			lists[listIndex].getVocab(vocabIndex).setMatchingWord(matchingWord.getText());
			lists[listIndex].getVocab(vocabIndex).setDefinition(vocabDefinition.getText());
			lists[listIndex].getVocab(vocabIndex).setVocabName(vocabName.getText());
			lists[listIndex].getVocab(vocabIndex).setSynonyms(vocabSynonyms.getText());

			saveLists();
			resetList();
			resetVocab();
			mainMenu();
		}
		
		else if(source.equals("editVocab")){
			if(vocabIndex  > -1){
				matchingWord.setText(lists[listIndex].getVocab(vocabIndex).getMatchingWord());
				vocabDefinition = lists[listIndex].getVocab(vocabIndex).getDefinition();
				vocabName.setText(lists[listIndex].getVocab(vocabIndex).getName());
				vocabSynonyms = (lists[listIndex].getVocab(vocabIndex).getSynonyms());
			
				vocabName.setPreferredSize(new Dimension(210, 20));
				matchingWord.setPreferredSize(new Dimension(210, 20));
				vocabDefinition.setPreferredSize(new Dimension(550, 250));
				vocabSynonyms.setPreferredSize(new Dimension(550, 250));
				
				VocabEditor edit = new VocabEditor();
				edit.editVocab(mainPanel, vocabName, matchingWord, vocabDefinition, vocabSynonyms, this);
				edit.editVocabII();
			}
			else{
				JOptionPane.showMessageDialog(null, "No vocabulary list has been selected for editing.");				
			}
		}
		
		else if(source.equals("saveVocab")){
			lists[listIndex].getVocab(vocabIndex).setVocabName(vocabName.getText());
			lists[listIndex].getVocab(vocabIndex).setMatchingWord(matchingWord.getText());
			lists[listIndex].getVocab(vocabIndex).setDefinition(vocabDefinition.getText());
			lists[listIndex].getVocab(vocabIndex).setVocabName(vocabName.getText());
		}
		
		else if(source.equals("back")){
			mainPanel.setVisible(false);
		}
		
		SaveVocab save = new SaveVocab(lists);
	}
	
}
