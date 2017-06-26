import java.awt.Color;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Random;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RunVocab{

	private String[] fileNames;
	private String[] testFiles;
	private String[] answers;
	private String[] vocab;
	
	private Font font = new Font("Times New Roman", Font.LAYOUT_LEFT_TO_RIGHT, 18);
	
	private OptionsPanel options;
	
	private JLabel wrongAnswers;
	private JLabel counterLabel;
	private JLabel counterLabelII;
	private JLabel displayAnswer;
	private JLabel vocabQuestion;
	
	private JTextField answerLabel;

	private JEditorPane[] synonyms;
	private JEditorPane[] definitions;
	private JEditorPane definitionQuestion;
	private JEditorPane synonymQuestion;
	
	private ActionListener acListener;
	private KeyListener keyListener;

	private int counter;
	private int correctNum;
	private int numFiles;
	private int currentTest;
	private int numWrong;
	private int wrongIndex;
	private int testSize;
	private int[] rand;
	private int[][] randInfo;
	private int[] wrong;
	
	private JButton acceptButton;
	private JButton[] answerButtons;

	private JPanel bgContainerPanel;
	private JPanel counterPanel;
	private JPanel vocabPanel;
	private JPanel containerPanel;
	private JPanel labelPanel;
	private JPanel disaplyAnswerPanel;
	private JPanel definitionPanel;
	private JPanel synonymPanel;
	private JPanel backPanel;
	private JPanel buttonPanel;
	private JPanel background;
	private JPanel[] buttonPanels;
	
	private VocabList vocabList;
	
	private final static String homeDir = System.getProperty("user.home");
	private JPanel wrongAnswerPanel;
	
	public void setLists(VocabList files){
		
		vocabList = files;	

		numFiles = files.getTotalVocab();
		fileNames = new String[files.getTotalVocab()];
		testFiles = new String[files.getTotalVocab()];
		for(int x = 0; x < files.getTotalVocab(); x++){
			fileNames[x] = files.getVocab(x).getName();
		}
	}
	
	public void setFiles(File[] files){
		numFiles = files.length;
		fileNames = new String[files.length];
		testFiles = new String[files.length];
		for(int x = 0; x < files.length; x++){
			fileNames[x] = files[x].getName();
			testFiles[x] = files[x].getPath();
		}
		counter = 0;
	}
	
	public RunVocab(File[] files, OptionsPanel options, ActionListener acListener, KeyListener keyListener){

		wrongAnswerPanel = new JPanel();
		vocabPanel = new JPanel();
		definitionPanel = new JPanel();
		synonymPanel = new JPanel();
		
		bgContainerPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(new ImageIcon(VocabPrac.workDir+"\\clouds.jpg").getImage(), 0, 0, d.width, d.height, null);
				super.paintComponent(g);
			}
		};
	
		displayAnswer = new JLabel("View Answer");
			displayAnswer.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent e){
					displayAnswer.setText(answers[rand[counter-1]]);
				}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
			});
		displayAnswer.setOpaque(false);
		
		QuickSwing cust = new QuickSwing("Accept Answer", VocabPrac.workDir+"\\bluebutton.jpg");
		acceptButton = cust.getButton();
		acceptButton.setPreferredSize(new Dimension(180,40));
		acceptButton.setActionCommand("checkHidden");
		acceptButton.addActionListener(acListener);
		
		answerLabel = new JTextField("Enter Answer Here");
		answerLabel.setPreferredSize(new Dimension(150,40));
		answerLabel.setOpaque(false);
		answerLabel.addKeyListener(keyListener);
		answerLabel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if(answerLabel.getText().equals("Enter Answer Here")){
					answerLabel.setText("");
				}
			}
			public void mouseReleased(MouseEvent e) {}
		
		});
		
		disaplyAnswerPanel = new JPanel();
		disaplyAnswerPanel.setOpaque(false);
		disaplyAnswerPanel.setVisible(true);
		
		disaplyAnswerPanel.add(displayAnswer);

		counterPanel = new JPanel();
		
		background = new JPanel();
		background.setOpaque(false);
		background.setVisible(true);
			
		this.acListener = acListener;
		this.keyListener = keyListener;
		this.options = options;
		
		numFiles = files.length;
		fileNames = new String[files.length];
		testFiles = new String[files.length];
		for(int x = 0; x < files.length; x++){
			fileNames[x] = files[x].getName();
			testFiles[x] = files[x].getPath();
		}
		counter = 0;

		cust = new QuickSwing("Back", VocabPrac.workDir+"\\bluebutton.jpg", 100, 32);
		JButton back = cust.getButton();
		
		back.addActionListener(acListener);
		back.setActionCommand("back");
		back.setToolTipText("Return To Main Page");
		back.setPreferredSize(new Dimension(100,32));

		backPanel = new JPanel();
		backPanel.add(back);
		backPanel.setOpaque(false);
		backPanel.setPreferredSize(new Dimension(100,27));
		
		containerPanel = new JPanel();
		/*containerPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(new ImageIcon("testBG2.jpg").getImage(), 0, 0, d.width, d.height, null);
				
				super.paintComponent(g);
				}
		};*/
		buttonPanel = new JPanel();
		/*buttonPanel = new JPanel(){
			protected void paintComponent(Graphics g)
				{
				Dimension d = getSize();
				g.drawImage(new ImageIcon("testBG2.jpg").getImage(), 0, 0, d.width, d.height, null);
				super.paintComponent(g);
				}
			};*/

		labelPanel = new JPanel();
		/*labelPanel = new JPanel(){
			protected void paintComponent(Graphics g)
				{
				Dimension d = getSize();
				g.drawImage(new ImageIcon("testBG2.jpg").getImage(), 0, 0, d.width, d.height, null);
				super.paintComponent(g);
				}
		};*/
		labelPanel.setVisible(true);
		labelPanel.setLayout(new FlowLayout(1,400,10));
		labelPanel.setOpaque(false);
		labelPanel.add(answerLabel);
		labelPanel.add(acceptButton);
		labelPanel.add(disaplyAnswerPanel);
				
		bgContainerPanel.add(background);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void runTest(){
		correctNum = 0;
		numWrong = 0;

		if(testSize < options.getChoiceNum()){
			options.setChoiceNum(testSize);
		}
		
		counter = 0;
		randInfo = new int[vocab.length][options.getChoiceNum()+1];
		
		answerButtons = new JButton[options.getChoiceNum()];

		wrongAnswerPanel.removeAll();
		vocabPanel.removeAll();
		definitionPanel.removeAll();
		synonymPanel.removeAll();
		
		buttonPanels = new JPanel[options.getChoiceNum()];
		
		Random stat = new Random();
		int x = Math.abs(stat.nextInt()+1) % 11;
		numSwap();
		x = Math.abs(stat.nextInt(testSize)) % 11;
		
		for(int n = 0; n < options.getChoiceNum(); n++){
			if(x == testSize)
				x = 0;
			if(rand[x] == rand[counter])
				x++;
			if(x == testSize)
				x = 0;
			
			buttonPanels[n] = new JPanel();
			buttonPanels[n].setPreferredSize(new Dimension(180,40));
			buttonPanels[n].setOpaque(false);
						
			if(!options.getHiddenAnswers()){
				QuickSwing cust = new QuickSwing(answers[rand[x]], VocabPrac.workDir+"\\bluebutton.jpg");
				answerButtons[n] = cust.getButton();
			}
			else{
				QuickSwing cust = new QuickSwing(" ", VocabPrac.workDir+"\\bluebutton.jpg");
				answerButtons[n] = cust.getButton();
			}
			
			answerButtons[n].setToolTipText(answers[rand[x++]]);
			answerButtons[n].setActionCommand("false");
			answerButtons[n].addActionListener(acListener);
			answerButtons[n].setPreferredSize(new Dimension(180,40));
			buttonPanels[n].add(answerButtons[n]);
			
			buttonPanel.add(buttonPanels[n]);
		}

		x = Math.abs(stat.nextInt(options.getChoiceNum()));
		if(x == -1){x = 0;}
		
		if(!options.getHiddenAnswers()){
			QuickSwing cust = new QuickSwing(answers[rand[counter]], VocabPrac.workDir+"\\bluebutton.jpg");
			answerButtons[x] = cust.getButton();
			
			buttonPanels[x].removeAll();
			buttonPanels[x].add(answerButtons[x]);
			buttonPanels[x].revalidate();
			buttonPanels[x].repaint();
		}
		
		answerButtons[x].setPreferredSize(new Dimension(180,40));
		answerButtons[x].setToolTipText(answers[rand[counter]]);
		answerButtons[x].setActionCommand("true");
		answerButtons[x].addActionListener(acListener);
		if(counter == testSize-1){
			answerButtons[x].setActionCommand("finaltrue");
		}

		vocabQuestion = new JLabel(vocab[rand[counter]]);
		if(definitions.length != 0){
			try{
				definitionQuestion = definitions[rand[counter]];
				definitionQuestion.setOpaque(false);
				System.out.println("HERE");
				JScrollPane defScroll = new JScrollPane(definitionQuestion);
				definitionPanel.add(defScroll);
				defScroll.setOpaque(false);
				defScroll.getViewport().setOpaque(false);
			}catch(NullPointerException NPE){}
		}
		else if(synonyms.length != 0){
			try{
				synonymQuestion = synonyms[rand[counter]];
				synonymQuestion.setOpaque(false);
				JScrollPane synScroll = new JScrollPane(synonymQuestion);
				synonymPanel.add(synScroll);
				synScroll.setOpaque(false);
				synScroll.getViewport().setOpaque(false);
			}catch(NullPointerException NPE){}
		}
		backPanel.setVisible(true);
		buttonPanel.setVisible(true);
		
		backPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new GridLayout(2,2));
		vocabPanel.setLayout(new FlowLayout(1,0,50));
		synonymPanel.setLayout(new GridLayout());
		definitionPanel.setLayout(new GridLayout());
		containerPanel.setLayout(new GridLayout(0,1));
		
		bgContainerPanel.remove(background);
		
		if(options.getFillInTheBlank()){
			displayAnswer.setText("View Answer");
			answerLabel.setText("Enter Answer Here");
			answerLabel.setFocusable(true);
			answerLabel.hasFocus();
			labelPanel.setVisible(true);
			disaplyAnswerPanel.setVisible(true);
			bgContainerPanel.add(labelPanel);
		}
		else{bgContainerPanel.add(buttonPanel);}
		
		wrongAnswers = new JLabel("");

		synonymPanel.setOpaque(false);
		synonymPanel.setOpaque(false);
		definitionPanel.setOpaque(false);
		vocabPanel.setOpaque(false);
		containerPanel.setOpaque(false);
		backPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		wrongAnswerPanel.setOpaque(false);
		
		counterPanel = new JPanel();
		counterLabel = new JLabel("Number Wrong "+ numWrong);//Here
		counterLabelII = new JLabel("Number Right "+ correctNum);
		counterPanel.setOpaque(false);
		counterLabel.setForeground(new Color(1).WHITE);
		counterLabelII.setForeground(new Color(1).WHITE);
		counterPanel.setLayout(new FlowLayout(0,150,0));
		counterPanel.add(counterLabel);
		counterPanel.add(counterLabelII);
		counterLabel.setToolTipText("");
		counterLabelII.setToolTipText("");

		answerLabel.setForeground(new Color(1).WHITE);
		displayAnswer.setForeground(new Color(1).WHITE);
		
		wrongAnswerPanel.add(wrongAnswers);
		containerPanel.add(wrongAnswerPanel);
		containerPanel.add(counterPanel);
		vocabPanel.add(vocabQuestion);
		if(options.getMatchingWordTest()){
			containerPanel.add(vocabPanel);
		}else if(options.getDefinitionsTest()){
			containerPanel.add(definitionPanel);
		}else if(options.getSynonymsTest()){
			containerPanel.add(synonymPanel);			
		}
		containerPanel.add(backPanel);
		if(options.getSaveWrong())
		containerPanel.add(new ReviewPanel(acListener).getPanel());
		
		bgContainerPanel.add(containerPanel);
		bgContainerPanel.invalidate();
		bgContainerPanel.validate();
		counter++;
		answerButtons[0].setFocusable(true);
		answerButtons[0].hasFocus();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void contTest(){
				
		Random stat = new Random();
		int x = Math.abs(stat.nextInt(testSize)) % 11;
		
		if(counter == testSize && !options.getReviewing()){
			counterLabel.setToolTipText("");
			counterLabelII.setToolTipText("");
			counter = 0;
			numSwap();
		}
		
		if(options.getReviewing()){
			counter = wrong[wrongIndex++];
		}

		int randX = Math.abs(stat.nextInt(options.getChoiceNum()));
		x = Math.abs(stat.nextInt(options.getChoiceNum()));
		if(randX == -1)
			randX = 0;
		
		if(!options.getHiddenAnswers()){
			QuickSwing cust = new QuickSwing(answers[rand[counter]], VocabPrac.workDir+"\\bluebutton.jpg");
			answerButtons[randX] = cust.getButton();
		}
		else{

			QuickSwing cust = new QuickSwing(" ", VocabPrac.workDir+"\\bluebutton.jpg");
			answerButtons[randX] = cust.getButton();
		}
			
		answerButtons[randX].setToolTipText(answers[rand[counter]]);
		answerButtons[randX].addActionListener(acListener);
		answerButtons[randX].setActionCommand("true");
		answerButtons[randX].setPreferredSize(new Dimension(180,40));
		
		buttonPanels[randX].removeAll();
		buttonPanels[randX].add(answerButtons[randX]);
		buttonPanels[randX].revalidate();
		buttonPanels[randX].setPreferredSize(new Dimension(180,40));
		int answerLocation = randX;

		
		if(!options.getFillInTheBlank()){
			if(options.getChoiceNum() > 1){
				if(x > testSize)
					x = 0;
				for(int n = 0; n < options.getChoiceNum(); n++){
					if(n == answerLocation && n == options.getChoiceNum()-1){
						break;
					}
					if(n == answerLocation){
						n++;
					}
					
					if(x >= testSize)
						x = 0;
					if(rand[x] == rand[counter])
						x++;
					if(x >= testSize)
						x = 0;
					
					if(!options.getHiddenAnswers()){
						QuickSwing cust = new QuickSwing(answers[rand[x]], VocabPrac.workDir+"\\bluebutton.jpg");
						answerButtons[n] = cust.getButton();
					}
					else{
						QuickSwing cust = new QuickSwing(" ", VocabPrac.workDir+"\\bluebutton.jpg");
						answerButtons[n] = cust.getButton();
					}
					
					answerButtons[n].setToolTipText(answers[rand[x++]]);
					answerButtons[n].addActionListener(acListener);
					answerButtons[n].setActionCommand("false");
					answerButtons[n].setPreferredSize(new Dimension(180,40));
					buttonPanels[n].removeAll();
					buttonPanels[n].add(answerButtons[n]);
					buttonPanels[n].revalidate();
					buttonPanels[n].setPreferredSize(new Dimension(180,40));
				}
			}
		}
		if(options.getFillInTheBlank()){
			displayAnswer.setText("View Answer Here");
			answerLabel.hasFocus();
			answerLabel.setText("Enter Answer Here");
		}
		
		vocabQuestion.setText(vocab[rand[counter]]);
		if(definitions.length != 0){
			definitionQuestion.setText(definitions[rand[counter]].getText());
		}
		else if(synonyms.length != 0){
			synonymQuestion.setText(synonyms[rand[counter]].getText());
		}

		buttonPanel.revalidate();
		backPanel.revalidate();
		
		buttonPanel.setLayout(new GridLayout(2,2));
		//vocabPanel.setLayout(new FlowLayout(1,0,50));
		if(counter == testSize-1){
			answerButtons[randX].setActionCommand("finaltrue");
			acceptButton.setActionCommand("checkHiddenFinal");
		}
		else if(options.getReviewing() && wrong.length == wrongIndex){
			answerButtons[randX].setActionCommand("finaltrue");
			acceptButton.setActionCommand("checkHiddenFinal");
		}
		else{
			acceptButton.setActionCommand("checkHidden");			
		}
		counter++;
		answerButtons[0].setFocusable(true);
		answerButtons[0].hasFocus();
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public JTextField getAnswerLabel(){
		return answerLabel;
	}
	
	public JLabel getCounterLabel(){
		return counterLabel;
	}

	public JPanel getContainerPanel(){
		return containerPanel;
	}
	
	public JPanel getBGContainerPanel(){
		return bgContainerPanel;
	}
	
	public JLabel getCounterLabelII(){
		return counterLabelII;
	}
	
	public String getVocab(){
		return vocab[rand[counter-1]];
	}

	public String getAnswer(){
		return answers[rand[counter-1]];
	}
	
	public int[] getWrongNum(){
		return wrong;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void numSwap(){
		rand = new int[testSize];
		if(testSize > 1){
			Random stat = new Random();
			for(int n = 0; n < testSize; n++){
				rand[n] = n;
			}
				
			for(int n = 0; n < testSize; n++){
				int x = Math.abs(stat.nextInt(testSize-1)) % 11;
				int temp = rand[n];
				rand[n] = rand[x];
				rand[x] = temp;
			}
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public AttributedCharacterIterator setStringFont(int x){					
		AttributedString aString = new AttributedString(answers[rand[x]]);
		aString.addAttribute(TextAttribute.FONT, font, 0, answers[rand[x]].length()); 
		aString.addAttribute(TextAttribute.FOREGROUND, Color.WHITE, 0, answers[rand[x]].length());
		AttributedCharacterIterator name2 =  aString.getIterator();
		return name2;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getTestFiles(){
		return testSize;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void getInfo(int index){
		
		String[] answers;
		String[] vocab;
		definitions = new JEditorPane[0];
		synonyms = new JEditorPane[0];
		int size = 0;
		currentTest = index;
		try {
			Scanner getInfo = new Scanner(new File(testFiles[index]));
			while(getInfo.hasNextLine()){
				String check = getInfo.nextLine();
				size++;
			}
			getInfo.close();
			
			testSize = size/2;
			answers = new String[size/2];
			vocab = new String[size/2];
			
			getInfo = new Scanner(new File(testFiles[index]));
			for(int x = 0; x < size/2; x++){
				vocab[x] = getInfo.nextLine();
				answers[x] = getInfo.nextLine();
			}
			getInfo.close();
			
			this.answers = answers;
			this.vocab = vocab;
			
		} catch (FileNotFoundException e) {}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void getInfoII(int index){
	
		String[] answers;
		String[] vocab;
		JEditorPane[] definitions;
		JEditorPane[] synonyms;
		int size = vocabList.getTotalVocab();
		currentTest = index;
		
		int check = 0;
		for(int x = 0; x < vocabList.getTotalVocab(); x++){
			if(!vocabList.getVocab(x).getMatchingWord().trim().equals("") && options.getMatchingWordTest()){
				check++;
			}
			else if(!vocabList.getVocab(x).getDefinition().getText().equals("") && options.getDefinitionsTest()){
				check++;
			}
			else if(!vocabList.getVocab(x).getSynonyms().getText().equals("") && options.getSynonymsTest()){
				check++;
			}
		}
		
		testSize = check;
		answers = new String[testSize];
		vocab = new String[testSize];
		definitions = new JEditorPane[testSize];
		synonyms = new JEditorPane[testSize];
		
		int forIndex = 0;
		for(int x = 0; x < size; x++){
			if(!vocabList.getVocab(x).getMatchingWord().trim().equals("") && options.getMatchingWordTest()){
				vocab[forIndex] = vocabList.getVocab(x).getName();
				answers[forIndex] = vocabList.getVocab(x).getMatchingWord();
				forIndex++;
			}
			else if(!vocabList.getVocab(x).getDefinition().getText().equals("") && options.getDefinitionsTest()){
				answers[forIndex] = vocabList.getVocab(x).getName();
				definitions[forIndex] = vocabList.getVocab(x).getDefinition();
				forIndex++;
			}
			else if(!vocabList.getVocab(x).getSynonyms().getText().equals("") && options.getSynonymsTest()){
				answers[forIndex] = vocabList.getVocab(x).getName();
				synonyms[forIndex] = vocabList.getVocab(x).getSynonyms();
				forIndex++;
			}
		}
		this.answers = answers;
		this.vocab = vocab;
		this.definitions = definitions;
		this.synonyms = synonyms;
			
	}
	public void setWrongIndex(int stat){
		wrongIndex = stat;
	}
	
	public int getWrongIndex(){
		return wrongIndex;
	}
	
	public void setTitle(JFrame aFrame){
		aFrame.setTitle("Lang Practice Program -" + new File(testFiles[currentTest]).getName());
	}
	
	public void setTitleII(JFrame aFrame){
		aFrame.setTitle("Lang Practice Program -" + vocabList.getName());
	}
	
	public void setNumWrong(int stat){
		numWrong = stat;
	}

	public void setCorrectNum(int stat){
		correctNum = stat;
	}

	public int getNumWrong(){
		return numWrong;
	}

	public int getCorrectNum(){
		return correctNum;
	}
	
	public void setCounterLabelText(String stat){
		counterLabel.setText(stat);
	}
	
	public JPanel getBackground(){
		return background;
	}	

	public void next(){
		numWrong++;
		counterLabel.setText("Number Wrong "+ numWrong);
		counterLabel.setToolTipText(counterLabel.getToolTipText() +" | "+ vocab[rand[counter-1]]);
	}	

	public JPanel getButtonPanel(){
		return buttonPanel;
	}

	public JPanel getLabelPanel(){
		return labelPanel;
	}
	
	public JPanel getCounterPanel(){
		return counterPanel;
	}
	
	public void resetWrongRight(){
		numWrong = 0;
		correctNum = 0;
		counterLabel.setText("Number Wrong "+ numWrong);//Here
		counterLabelII.setText("Number Right "+ correctNum);//Here
	}

	public int getCounter(){
		return counter;
	}

	public int getTestSize(){
		return testSize;
	}
	
	public int[] getWrong(){
		return wrong;
	}
	public void setWrong(int[] num) {
		wrong = num;		
	}
	public void setCounter(int num) {
		counter = num;
	}
	
	public JLabel getWrongAnswers(){
		return wrongAnswers;
	}
}
