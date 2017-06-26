import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class OptionsPanel {
	
	private boolean runExit;
	private boolean reviewing = false;
	private boolean saveWrong = false;
	private boolean playRightSound = false;
	private boolean playWrongSound = false;
	private boolean hiddenAnswers = false;
	private boolean fillInTheBlank = false;
	private boolean playMusic = false;
	private boolean synonymsTest = false;
	private boolean matchingWordTest = false;
	private boolean definitionsTest = false;
	
	private final static String homeDir = System.getProperty("user.home");
	//private final static File optionsINI = new File("Options.ini");
	final ImageIcon churchBG = new ImageIcon(VocabPrac.workDir+"\\fullchurch.jpg");
	
	private ActionListener aListener;
	private int choiceNum;
	private String currentTheme;
	private boolean next;
	
	public OptionsPanel(ActionListener listener){
		aListener = listener;
		if((new File(VocabPrac.workDir+"\\Options.ini")).exists()){
			getOptions();
		}
		else{
			storeOptions();
		}
	}
	
	public JPanel optionsMenu(){
		JButton exitButtonII = new JButton("Exit");
		exitButtonII.setActionCommand("exitOptions");
		exitButtonII.addActionListener(aListener);
		
		JPanel optionsMenu = new JPanel(){
		protected void paintComponent(Graphics g)
			{
			Dimension d = getSize();
			g.drawImage(new ImageIcon(VocabPrac.workDir+"\\clouds.jpg").getImage(), 0, 0, d.width, d.height, null);
			
			super.paintComponent(g);
			}
		};
		optionsMenu.setOpaque(false);
		JPanel exitPanel = new JPanel();
		exitPanel.setOpaque(false);
		
		String[] list = {"Current Number Of Choices: "+choiceNum, "4", "3", "2", "1"};
		JComboBox numChoices = new JComboBox(list);
		numChoices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox) event.getSource();
				String cBox = (String) cb.getSelectedItem();
				try{
				choiceNum = Integer.parseInt(cBox);
				}catch(NumberFormatException NFE){}
				storeOptions();
			}
		});
		JCheckBox saveWrongJCB = new JCheckBox("Save Wrong Answers: ", saveWrong);
		JCheckBox playRightSoundJCB = new JCheckBox("Play Sound On Right Choice: ", playRightSound);
		JCheckBox playWrongSoundJCB = new JCheckBox("Play Sound On Wrong Choice: ", playWrongSound);
		JCheckBox playMusicJCB = new JCheckBox("Play Music On Start: ", playMusic);
		JCheckBox hiddenAnswersJCB = new JCheckBox("Hide Answers: ", hiddenAnswers);
		JCheckBox fillInTheBlankJCB = new JCheckBox("Fill In The Blank: ", fillInTheBlank);
		
		JRadioButton synonymsJRB = new JRadioButton("Synonms Test: ", synonymsTest);
		JRadioButton matchingWordJRB = new JRadioButton("Matching Word Test: ", matchingWordTest);
		JRadioButton definitionJRB = new JRadioButton("Definitions Test: ", definitionsTest);

		ButtonGroup testTypeGroup = new ButtonGroup();
		
		testTypeGroup.add(synonymsJRB);
		testTypeGroup.add(matchingWordJRB);
		testTypeGroup.add(definitionJRB);
		
		saveWrongJCB.setForeground(Color.WHITE);
		playRightSoundJCB.setForeground(Color.WHITE);
		playWrongSoundJCB.setForeground(Color.WHITE);
		hiddenAnswersJCB.setForeground(Color.WHITE);
		fillInTheBlankJCB.setForeground(Color.WHITE);
		playMusicJCB.setForeground(Color.WHITE);
		synonymsJRB.setForeground(Color.WHITE);
		matchingWordJRB.setForeground(Color.WHITE);
		definitionJRB.setForeground(Color.WHITE);
		
		saveWrongJCB.setOpaque(false);
		playRightSoundJCB.setOpaque(false);
		playWrongSoundJCB.setOpaque(false);
		hiddenAnswersJCB.setOpaque(false);
		fillInTheBlankJCB.setOpaque(false);
		playMusicJCB.setOpaque(false);
		synonymsJRB.setOpaque(false);
		matchingWordJRB.setOpaque(false);
		definitionJRB.setOpaque(false);
		
		playMusicJCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 playMusic = true;
				 }else
					 playMusic = false;
				 storeOptions();
			}
		});
		fillInTheBlankJCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 fillInTheBlank = true;
				 }else{
					 fillInTheBlank = false;
				 }
				 storeOptions();
			}
		});
		saveWrongJCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 saveWrong = true;
				 }else{
					 saveWrong = false;
				 }
				 storeOptions();
			}
		});
		playRightSoundJCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 playRightSound = true;
				 }else
					 playRightSound = false;
				 storeOptions();
			}
		});
		playWrongSoundJCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 playWrongSound = true;
				 }
				 else
					 playWrongSound = false;
				 storeOptions();
			}
		});
		hiddenAnswersJCB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 hiddenAnswers = true;
				 }else
					 hiddenAnswers = false;
				 storeOptions();
			}
		});
		definitionJRB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 definitionsTest = true;
					 matchingWordTest = false;
					 synonymsTest = false;
				 }else{
					 matchingWordTest = true;
					 definitionsTest = false;
				 }
				 storeOptions();
			}
		});
		matchingWordJRB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 matchingWordTest = true;
					 definitionsTest = false;
					 synonymsTest = false;
				 }else{
					 definitionsTest = true;
					 matchingWordTest = false;
				 }
				 storeOptions();
			}
		});
		synonymsJRB.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event) {		
				 int source = event.getStateChange();
				 if (source == 1){
					 synonymsTest = true;
					 matchingWordTest = false;
					 definitionsTest = false;
				 }else{
					 matchingWordTest = true;
					 synonymsTest = false;
				 }
				 storeOptions();
			}
		});
		
		
		optionsMenu.add(numChoices);
		optionsMenu.add(saveWrongJCB);
		optionsMenu.add(playRightSoundJCB);
		optionsMenu.add(playWrongSoundJCB);
		//optionsMenu.add(hiddenAnswersJCB);
		optionsMenu.add(fillInTheBlankJCB);	
		optionsMenu.add(playMusicJCB);
		optionsMenu.add(matchingWordJRB);
		optionsMenu.add(definitionJRB);
		optionsMenu.add(synonymsJRB);
		optionsMenu.add(exitPanel);
		exitPanel.add(exitButtonII);
		
		optionsMenu.setLayout(new FlowLayout(1,300,40));
		return optionsMenu;
	}
	
	public boolean getReviewing(){
		return reviewing;
	}

	public int getchoiceNum(){
		return choiceNum;
	}

	public boolean getSaveWrong(){
		return saveWrong;
	}

	public boolean getPlayRightSound(){
		return playRightSound;
	}

	public boolean getPlayWrongSound(){
		return playWrongSound;
	}

	public boolean getHiddenAnswers(){
		return hiddenAnswers;
	}

	public boolean getFillInTheBlank(){
		return fillInTheBlank;
	}

	public boolean getPlayMusic(){
		return playMusic;
	}

	public String getCurrentTheme(){
		return currentTheme;
	}

	public boolean getNext(){
		return next;
	}	

	
	public void setReviewing(boolean stat){
		reviewing = stat;
	}

	public void setChoiceNum(int stat){
		choiceNum = stat;
	}

	public void setSaveWrong(boolean stat){
		saveWrong = stat;
	}

	public void setPlayRightSound(boolean stat){
		playRightSound = stat;
	}

	public void setPlayWrongSound(boolean stat){
		playWrongSound = stat;
	}

	public void setHiddenAnswers(boolean stat){
		hiddenAnswers = stat;
	}

	public void setFillInTheBlank(boolean stat){
		fillInTheBlank = stat;
	}

	public void setPlayMusic(boolean stat){
		playMusic = stat;
	}

	public void setNext(boolean stat){
		next = stat;
	}
	
	public void setCurrentTheme(String stat){
		currentTheme = stat;
	}
	
	public void setMatchingWordTest(boolean stat){
		matchingWordTest = stat;
	}

	
	public void setDefinitionsTest(boolean stat){
		definitionsTest = stat;
	}

	
	public void setSynonymsTest(boolean stat){
		synonymsTest = stat;
	}
	
	
	public boolean getMatchingWordTest(){
		return matchingWordTest;
	}

	
	public boolean getDefinitionsTest(){
		return definitionsTest;
	}

	
	public boolean getSynonymsTest(){
		return synonymsTest;
	}
	
	public void getOptions(){
		try
		{
			Scanner inputStream = new Scanner(new File(VocabPrac.workDir+"\\Options.ini"));
			
			while(inputStream.hasNext()){
				reviewing = Boolean.parseBoolean(inputStream.nextLine());
				String getData = inputStream.nextLine();
				choiceNum = Integer.parseInt(getData);
				saveWrong = Boolean.parseBoolean(inputStream.nextLine());
				playRightSound = Boolean.parseBoolean(inputStream.nextLine());
				playWrongSound = Boolean.parseBoolean(inputStream.nextLine());
				hiddenAnswers = Boolean.parseBoolean(inputStream.nextLine());
				fillInTheBlank = Boolean.parseBoolean(inputStream.nextLine());
				playMusic = Boolean.parseBoolean(inputStream.nextLine());
				matchingWordTest = Boolean.parseBoolean(inputStream.nextLine());
				definitionsTest = Boolean.parseBoolean(inputStream.nextLine());
				synonymsTest = Boolean.parseBoolean(inputStream.nextLine());
				currentTheme = inputStream.nextLine();
			}
		}catch(IOException IOE){}
	}
	
	public void storeOptions(){
		try {
			PrintWriter store = new PrintWriter(new FileOutputStream(new File(VocabPrac.workDir+"\\Options.ini")), true);
			
			store.println(reviewing);
			store.println(choiceNum);
			store.println(saveWrong);
			store.println(playRightSound);
			store.println(playWrongSound);
			store.println(hiddenAnswers);
			store.println(fillInTheBlank);
			store.println(playMusic);
			store.println(matchingWordTest);
			store.println(definitionsTest);
			store.println(synonymsTest);
			store.println(VocabPrac.workDir+"\\Music\\" + currentTheme);
		} catch (FileNotFoundException e) {}
	}
	
	public int getChoiceNum(){
		return choiceNum;
	}

}
