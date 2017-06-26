import javax.swing.*;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class VocabPrac extends JApplet implements ActionListener, KeyListener, Runnable{
	
	private String[] fileNames;

	private int numFiles;
	
	private double percentRight;
	private double percentWrong;
	
	private JButton exitButton;
	private JButton optionsButton;
	private JButton addVocabButton;
	private JButton musicButton;
	
	private JPanel optionsPanel;
	private JPanel optionsMenu;
	private JPanel exitPanel;
	private JPanel EOContainer;
	private JPanel container;
	private JPanel addVocabPanel;
	private JPanel addMenu;
	private JPanel musicMenu;
	private JPanel musicPanel;
	
	private boolean runExit;
	private boolean showScoreBool;
	
	final String custBtnLocation = new String(workDir+"\\bluebutton.jpg");
	final ImageIcon custBtn = new ImageIcon(workDir+"\\bluebutton.jpg");
	final ImageIcon churchBG = new ImageIcon(workDir+"\\fullchurch.jpg");
	final ImageIcon vortexBG = new ImageIcon(workDir+"\\vortexBG.jpg");
	final ImageIcon coastBG = new ImageIcon(workDir+"\\coast.jpg");
	final ImageIcon sunshineBG = new ImageIcon(workDir+"\\sunshineBG.jpg");
	final ImageIcon redBG = new ImageIcon(workDir+"\\redBG.jpg");
	final ImageIcon iconBackground = new ImageIcon(workDir+"\\5bdvki.jpg");
	
	private JScrollPane sPane;
	
	private PlayMusic themeMusic;
	private PlayMusic rightWrongSound;

	private ChangeMusic changeMusic;
	private RunVocab run;
	private RunVocab runII;
	private OptionsPanel options;
	private ShowScore showScore;
	private AddVocab setupVocab;

	private File[] files;
	
	final static String workDir = System.getProperty("user.dir");
	final static String homeDir = System.getProperty("user.home");

	private JFrame mainFrame;
	
	public VocabPrac(){}
	
	public VocabPrac(boolean runExit){this.runExit = runExit;} //Exit Program
	
	public VocabPrac(File[] files){
		
		VocabPracSetup setup = new VocabPracSetup();	
		files = setup.getFiles();
		
		mainFrame = new JFrame("Lang Practice Program"); 

		this.files = files;
		showScore = new ShowScore(this);
		
		options = new OptionsPanel(this);
		run = new RunVocab(files, options, this, this);
		
		rightWrongSound = new PlayMusic(new File("ShrinkEffect.wav"), false);
		
		//If there isn't music disable this, if the song in question isn't found then select an available one.
		try{
			themeMusic = new PlayMusic(new File(options.getCurrentTheme()), true);//sets the variables in PlayMusic
		}catch(NullPointerException e){
			options.setPlayMusic(false);
		}


		//determine if the background music should be played
		if(options.getPlayMusic()){
			themeMusic.PlayMusic();
		}
		
		changeMusic = new ChangeMusic(this, themeMusic, options.getCurrentTheme());
		new Thread (changeMusic).start();
		
		EOContainer = new JPanel();

		numFiles = files.length;
		fileNames = new String[files.length];
		for(int x = 0; x < files.length; x++){
			fileNames[x] = files[x].getName();
		}
		
		setupVocab = new AddVocab();
		setupVocab.getActList(this);
		addMenu = setupVocab.getVocab();
		
		
		//setup program exit button and panel
		exitButton = new JButton("Exit");
		QuickSwing cust = new QuickSwing("Exit", custBtnLocation, 130, 35);
		exitButton = cust.getButton();
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");
		exitButton.setPreferredSize(new Dimension(130,35));
		exitButton.setToolTipText("Exit Language Practice");
		exitPanel = new JPanel();
		exitPanel.add(exitButton);
		exitPanel.setOpaque(false);
		exitPanel.setPreferredSize(new Dimension(130,35));

		//setup music menu button and panel
		musicButton = new JButton("Music");
		cust = new QuickSwing("Music", custBtnLocation, 130, 35);
		musicButton = cust.getButton();
		musicButton.addActionListener(this);
		musicButton.setActionCommand("music");
		musicButton.setPreferredSize(new Dimension(130,35));
		musicButton.setToolTipText("Change Music Playing");
		musicPanel = new JPanel();
		musicPanel.add(musicButton);
		musicPanel.setOpaque(false);
		musicPanel.setPreferredSize(new Dimension(130,35));

		//setup options button and panel
		optionsButton = new JButton("Options");
		cust = new QuickSwing("Options", custBtnLocation, 130, 35);
		optionsButton = cust.getButton();
		optionsButton.addActionListener(this);
		optionsButton.setActionCommand("options");
		optionsButton.setToolTipText("Test Options");
		optionsButton.setPreferredSize(new Dimension(130,35));
		optionsPanel = new JPanel();
		optionsPanel.add(optionsButton);
		optionsPanel.setOpaque(false);
		optionsPanel.setPreferredSize(new Dimension(130,35));
		

		//setup add vocabulary button and panel
		addVocabButton = new JButton("Add Vocabulary");
		cust = new QuickSwing("Add Vocabulary", custBtnLocation, 130, 35);
		addVocabButton = cust.getButton();
		addVocabButton.addActionListener(this);
		addVocabButton.setActionCommand("add");
		addVocabButton.setToolTipText("Add to the vocabulary database.");
		addVocabButton.setPreferredSize(new Dimension(130,35));
		addVocabPanel = new JPanel();
		addVocabPanel.add(addVocabButton);
		addVocabPanel.setOpaque(false);
		addVocabPanel.setPreferredSize(new Dimension(130,35));

		run.getBGContainerPanel().setOpaque(false);

		//displays vocabulary text document tests
		setup();

		//setup main containers
		mainFrame.add(run.getBGContainerPanel());
		run.getBGContainerPanel().add(run.getBackground());
		EOContainer.setLayout(new GridLayout(0,1,0,0));
		EOContainer.add(addVocabPanel);
		EOContainer.add(optionsPanel);
		EOContainer.add(musicPanel);
		EOContainer.add(exitPanel);
		EOContainer.setOpaque(false);
		run.getBGContainerPanel().add(EOContainer);
		run.getBGContainerPanel().setLayout(new GridLayout(0,1,0,0));
		mainFrame.setLocation(700,100);
		run.getBGContainerPanel().setPreferredSize(new Dimension(700,700));
		mainFrame.setPreferredSize(new Dimension(700,700));
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(new JFrame().EXIT_ON_CLOSE);
		mainFrame.setVisible(true); 
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setup(){
		
		JButton[] button = new JButton[numFiles];
		
		JPanel[] panel = new JPanel[numFiles];
		final JPanel buttonPanel = new JPanel();

		buttonPanel.setOpaque(false);
		
		for(int x = 0; x < numFiles; x++){
			
			QuickSwing cust = new QuickSwing(fileNames[x] , custBtnLocation);
			button[x] = cust.getButton();
			
			button[x].setOpaque(false);
			button[x].setPreferredSize(new Dimension(160,35));
			button[x].addActionListener(this);
			button[x].setActionCommand("load"+x);
			button[x].setToolTipText(fileNames[x]);
			       
			panel[x] = new JPanel();
			panel[x].setPreferredSize(new Dimension(160,35));
			panel[x].setOpaque(true);
			panel[x].setLayout(new FlowLayout(4,0,0));
			panel[x].add(button[x]);
			buttonPanel.add(panel[x]);
		}
		
		buttonPanel.setLayout(new GridLayout(0,3,50,10));		
		
		container = new JPanel();
		
		container.setOpaque(false);
		container.add(buttonPanel);
		sPane = new JScrollPane(container);
		
		sPane.setOpaque(false);
		sPane.getViewport().setOpaque(false);
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//sPane.setPreferredSize(new Dimension (400,300));
		sPane.getHorizontalScrollBar().setUnitIncrement(10);
		sPane.getVerticalScrollBar().setUnitIncrement(10);
		buttonPanel.setFocusable(true);
		buttonPanel.hasFocus();
		
		run.getBackground().add(sPane);
		run.getBackground().setLayout(new GridLayout());
		//background.setLayout(new FlowLayout());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void actionPerformed(ActionEvent event){
		String source = event.getActionCommand();
		int getNum = 0;
				
		//System.out.println(source);
		
		try{
			if(run.getWrongIndex() == run.getWrong().length && options.getReviewing()){
				run.resetWrongRight();
			}
		}catch(NullPointerException NPE){}
		
		try{			
			if(source.substring(0, 4).equals("load")){
				getNum = Integer.parseInt(source.substring(4, source.length()));

				options.setNext(true);
				options.setSynonymsTest(false);
				options.setDefinitionsTest(false);
				options.setMatchingWordTest(true);
				options.storeOptions();
				run.setFiles(files);
				run.setWrong(null);
				run.setNumWrong(0);
				run.setWrongIndex(0);
				run.getInfo(getNum);
				
				if(run.getTestFiles() < 1){
					JOptionPane.showMessageDialog(null, "This test is empty.");
				}
				else{
					EOContainer.setVisible(false);
					
					run.getLabelPanel().setVisible(true);
					run.getButtonPanel().setVisible(true);
					run.getContainerPanel().setVisible(true);
					run.getBGContainerPanel().setVisible(true);
					run.getBGContainerPanel().removeAll();
					run.getBGContainerPanel().invalidate();
					run.getBGContainerPanel().validate();
					run.getBGContainerPanel().repaint();
					run.setTitle(mainFrame);
					run.runTest();
				}
			}
		}catch(NumberFormatException NFE){}
		catch(IndexOutOfBoundsException NFE){}

		if(source.equals("listLoad")){
			if(setupVocab.getLists().length == 0){
				JOptionPane.showMessageDialog(null, "There are no vocabulary lists available.");		
			}
			else if(setupVocab.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(null, "No vocabulary list has been selected.");
			}				
			else{
				if(setupVocab.getList().getTotalVocab() > 0){
											
					options.setNext(true);
					run.setLists(setupVocab.getList());
					run.setWrong(null);
					run.setNumWrong(0);
					run.setWrongIndex(0);
					run.getInfoII(getNum);

					if(run.getTestSize() < 1){
						JOptionPane.showMessageDialog(null, "No answers have been input for this test type.");	
					}
					else{
						EOContainer.setVisible(false);
						addMenu.setVisible(false);
											
						run.getLabelPanel().setVisible(true);
						run.getButtonPanel().setVisible(true);
						run.getContainerPanel().setVisible(true);
						run.getBGContainerPanel().removeAll();
						run.getBGContainerPanel().invalidate();
						run.getBGContainerPanel().validate();
						run.getBGContainerPanel().setVisible(true);
						run.setTitleII(mainFrame);
						run.runTest();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "There is no vocabulary to study in this list.");						
				}
			}
		}
		
		else if(source.equals("add")){
			run.getBGContainerPanel().removeAll();
			run.getBGContainerPanel().add(addMenu);
			run.getButtonPanel().setVisible(true);
			run.getContainerPanel().setVisible(true);
			run.getBGContainerPanel().setVisible(false);
			EOContainer.setVisible(false);
			addMenu.setVisible(true);
			run.getBackground().repaint();
			
			run.getBGContainerPanel().revalidate();
			run.getBackground().repaint();
			run.getBGContainerPanel().setVisible(true);
		}
		
		else if(source.equals("exitadd")){
			addMenu.setVisible(false);
			run.getBGContainerPanel().removeAll();
			run.getBGContainerPanel().add(run.getBackground());
			run.getBGContainerPanel().add(EOContainer);
			run.getBGContainerPanel().revalidate();
		}
		
		else if(source.equals("options")){
			optionsMenu = options.optionsMenu();
			run.getBGContainerPanel().removeAll();
			run.getBGContainerPanel().add(optionsMenu);
			run.getBGContainerPanel().revalidate();
		}
		
		else if(source.equals("exitOptions")){
			optionsMenu.setVisible(false);
			run.getBGContainerPanel().removeAll();
			run.getBGContainerPanel().add(run.getBackground());
			run.getBGContainerPanel().add(EOContainer);
			
			run.getBGContainerPanel().revalidate();

			options.storeOptions();
		}
		
		else if(source.equals("music")){
			while(changeMusic.getRunning()){
				try {Thread.sleep(1000);}
				catch (InterruptedException e){}
			}
			//changeMusic.changeSelected();
			musicMenu = changeMusic.getPanel();
			
			run.getBGContainerPanel().removeAll();
			run.getBGContainerPanel().add(musicMenu);
			musicMenu.setVisible(true);
			run.getBGContainerPanel().invalidate();
			run.getBGContainerPanel().validate();
		}
		
		else if(source.equals("exitMusic")){
			musicMenu.setVisible(false);
			run.getBGContainerPanel().removeAll();
			run.getBGContainerPanel().add(run.getBackground());
			run.getBGContainerPanel().add(EOContainer);
			
			run.getBGContainerPanel().invalidate();
			run.getBGContainerPanel().validate();

			
			options.setCurrentTheme(changeMusic.getCurrentSong());
		}
		
		else if(source.equals("pause")){
			themeMusic.pauseMusic();
		}

		else if(source.equals("mute")){
			themeMusic.mute();
		}

		else if(source.equals("play")){
			themeMusic.startMusic();
			changeMusic.setCurrentTheme();
			//changeMusic.storeCurrentSong();
			options.storeOptions();
			options.setCurrentTheme(changeMusic.getCurrentSong());
		}
		
		else if(source.equals("finaltrue")){
			run.setCounter(0);
			if(options.getNext() && !options.getReviewing()){
				run.setCorrectNum(run.getCorrectNum()+1);
				run.getCounterLabelII().setText("Number Right "+ run.getCorrectNum());
			}
			options.setNext(true);
			if(options.getPlayRightSound()){
				PlayMusic sound = new PlayMusic(new File("Zelda - Treasure Chest Open Sound Bite.wav"), false, 10);
				sound.PlayMusic();
			}
			showScore.getFrame().setVisible(true);
		}
		
		else if(source.equals("TRUE")){

			run.setWrong(new int [0]);
			run.setWrongIndex(0);
			
			showScore.getFrame().setVisible(false);
			options.setReviewing(false);
			
			run.getWrongAnswers().setText("");
			run.resetWrongRight();
			
			run.numSwap();
			run.contTest();
			run.getBGContainerPanel().invalidate();
			run.getBGContainerPanel().validate();
		}
		
		else if(source.equals("true")){
			showScore.setVisible(false);
			
			if(options.getNext() && !options.getReviewing()){
				run.setCorrectNum(run.getCorrectNum()+1);
				run.getCounterLabelII().setText("Number Right "+ run.getCorrectNum());
			}
			options.setNext(true);
			if(options.getPlayRightSound()){
				rightWrongSound.setMusic(new File("Zelda - Treasure Chest Open Sound Bite.wav"), false, 10);
				rightWrongSound.PlayMusic();
			}
			if(options.getReviewing() && run.getWrong().length == run.getWrongIndex()){
				run.setWrongIndex(0);
			}
			run.contTest();
			run.getBGContainerPanel().invalidate();
			run.getBGContainerPanel().validate();
		}
		
		else if(source.equals("false")){
			
			if(options.getPlayWrongSound()){
				rightWrongSound.setMusic(new File("ShrinkEffect.wav"), false, 0);
				rightWrongSound.PlayMusic();
			}
			
			boolean duplicate = false;
			
			if(options.getNext() && !options.getReviewing()){
				run.next();
			}
			
			if(options.getFillInTheBlank()){
				run.getAnswerLabel().hasFocus();
				run.getAnswerLabel().setText("Enter Answer Here");
			}
			
			options.setNext(false);
			
			if(options.getSaveWrong() && !options.getReviewing()){
				try{
					for(int x = 0; x < run.getWrong().length; x++){
						if(run.getWrong()[x] == run.getCounter()-1){
							duplicate = true;
							break;
						}
					}
				}catch(NullPointerException NPE){}

				if(!duplicate){

					if(run.getWrongAnswers().getText().equalsIgnoreCase("")){
						run.getWrongAnswers().setText(run.getVocab());
					}
					else{
						run.getWrongAnswers().setText(run.getWrongAnswers().getText() + "|"+run.getVocab());
					}
					
					try{
						
						if(run.getWrong() == null){
							run.setWrong(new int[1]);
							run.getWrong()[0] = run.getCounter()-1;
						}
						
						else{
							int[] temp = run.getWrong();
							for(int x = 0; x < temp.length; x++){
								temp[x] = run.getWrong()[x];
							}
	
							run.setWrong(new int[temp.length+1]);
							
							for(int x = 0; x < temp.length; x++){
								run.getWrong()[x] = temp[x];
							}
							run.getWrong()[run.getWrong().length-1] = run.getCounter()-1;
						}
					}catch(NullPointerException NE){}
				}
			}
			else if(options.getReviewing() && run.getWrong().length == run.getWrongIndex()){
				options.setReviewing(false);
				run.resetWrongRight();
			}
		}
		
		else if(source.equals("exit")){
			options.storeOptions();
			(new Thread(new VocabPrac(true))).start();
		}
		
		else if(source.equals("review")){
			try{
				if(run.getWrongNum().length > 0){
					run.resetWrongRight();
					showScore.getFrame().setVisible(false);
					options.setReviewing(true);
					run.setWrongIndex(0);
					run.contTest();
				}
				else{
					JOptionPane.showMessageDialog(null, "There is no vocabulary to review.");
				}
			}catch(NullPointerException NPE){
				JOptionPane.showMessageDialog(null, "There have been no wrong answers so far.");
			}
		}
		
		else if(source.equals("back")){//HERE
			mainFrame.setTitle("Vocabulary Practice Program");
			
			options.setReviewing(false);
			
			run.getLabelPanel().setVisible(false);
			run.getButtonPanel().setVisible(false);
			run.getContainerPanel().setVisible(false);
			run.getBGContainerPanel().setVisible(false);
			run.getBGContainerPanel().removeAll();
			
			run.getBGContainerPanel().add(run.getBackground());
			run.getBGContainerPanel().add(EOContainer);
			
			run.getButtonPanel().setFocusable(true);
			run.getButtonPanel().hasFocus();
			
			EOContainer.repaint();
			run.getBackground().repaint();
			run.getBGContainerPanel().repaint();
			EOContainer.setVisible(true);
			run.getBackground().setVisible(true);
			run.getBGContainerPanel().setVisible(true);
			run.getButtonPanel().removeAll();
			run.getCounterPanel().removeAll();
			run.getContainerPanel().removeAll();
			
			showScore.getFrame().setVisible(false);
		}
		
		else if(source.equals("checkHiddenFinal")){
			if(run.getAnswerLabel().getText().trim().equalsIgnoreCase(run.getAnswer())){
				if(options.getNext() && !options.getReviewing()){
					run.setCorrectNum(run.getCorrectNum()+1);
					run.getCounterLabelII().setText("Number Right "+ run.getCorrectNum());
				}
				options.setNext(true);
				if(options.getPlayRightSound()){
					PlayMusic sound = new PlayMusic(new File("Zelda - Treasure Chest Open Sound Bite.wav"), false, 10);
					sound.PlayMusic();
				}
				showScore.getFrame().setVisible(true);
			}
			else{
				if(options.getPlayWrongSound()){
					rightWrongSound.setMusic(new File("ShrinkEffect.wav"), false, 0);
					rightWrongSound.PlayMusic();
				}
				
				//checks if this answer has been gotten wrong before
				boolean duplicate = false;
				
				if(options.getNext() && !options.getReviewing()){
					run.next();
				}
				
				if(options.getFillInTheBlank()){
					run.getAnswerLabel().hasFocus();
					run.getAnswerLabel().setText("Enter Answer Here");
				}
				
				options.setNext(false);
				
				if(options.getSaveWrong() && !options.getReviewing()){
					try{
						for(int x = 0; x < run.getWrong().length; x++){
							if(run.getWrong()[x] == run.getCounter()-1){
								duplicate = true;
								break;
							}
						}
					}catch(NullPointerException NPE){}

					if(!duplicate){

						if(run.getWrongAnswers().getText().equalsIgnoreCase("")){
							run.getWrongAnswers().setText(run.getVocab());
						}
						else{
							run.getWrongAnswers().setText(run.getWrongAnswers().getText() + "|"+run.getVocab());
						}
						
						try{
							
							if(run.getWrong() == null){
								run.setWrong(new int[1]);
								run.getWrong()[0] = run.getCounter()-1;
							}
							
							else{
								int[] temp = run.getWrong();
								for(int x = 0; x < temp.length; x++){
									temp[x] = run.getWrong()[x];
								}
		
								run.setWrong(new int[temp.length+1]);
								
								for(int x = 0; x < temp.length; x++){
									run.getWrong()[x] = temp[x];
								}
								run.getWrong()[run.getWrong().length-1] = run.getCounter()-1;
							}
						}catch(NullPointerException NE){}
					}
				}
				else if(options.getReviewing() && run.getWrong().length == run.getWrongIndex()){
					options.setReviewing(false);
					run.resetWrongRight();
				}
			}
		}
		
		else if(source.equals("checkHidden")){
			if(run.getAnswerLabel().getText().trim().equalsIgnoreCase(run.getAnswer())){
				showScore.setVisible(false);
				
				if(options.getNext() && !options.getReviewing()){
					run.setCorrectNum(run.getCorrectNum()+1);
					run.getCounterLabelII().setText("Number Right "+ run.getCorrectNum());
				}
				options.setNext(true);
				if(options.getPlayRightSound()){
					rightWrongSound.setMusic(new File("Zelda - Treasure Chest Open Sound Bite.wav"), false, 10);
					rightWrongSound.PlayMusic();
				}
				
				
				if(options.getReviewing() && run.getWrong().length == run.getWrongIndex()){
					run.setWrongIndex(0);
				}
				run.contTest();
				run.getBGContainerPanel().invalidate();
				run.getBGContainerPanel().validate();
			}
			else{
				if(options.getPlayWrongSound()){
					rightWrongSound.setMusic(new File("ShrinkEffect.wav"), false, 0);
					rightWrongSound.PlayMusic();
				}
				
				//checks if this answer has been gotten wrong before
				boolean duplicate = false;
				
				if(options.getNext() && !options.getReviewing()){
					run.next();
				}
				
				if(options.getFillInTheBlank()){
					run.getAnswerLabel().hasFocus();
					run.getAnswerLabel().setText("Enter Answer Here");
				}
				
				options.setNext(false);
				
				if(options.getSaveWrong() && !options.getReviewing()){
					try{
						for(int x = 0; x < run.getWrong().length; x++){
							if(run.getWrong()[x] == run.getCounter()-1){
								duplicate = true;
								break;
							}
						}
					}catch(NullPointerException NPE){}

					if(!duplicate){

						if(run.getWrongAnswers().getText().equalsIgnoreCase("")){
							run.getWrongAnswers().setText(run.getVocab());
						}
						else{
							run.getWrongAnswers().setText(run.getWrongAnswers().getText() + "|"+run.getVocab());
						}
						
						try{
							
							if(run.getWrong() == null){
								run.setWrong(new int[1]);
								run.getWrong()[0] = run.getCounter()-1;
							}
							
							else{
								int[] temp = run.getWrong();
								for(int x = 0; x < temp.length; x++){
									temp[x] = run.getWrong()[x];
								}
		
								run.setWrong(new int[temp.length+1]);
								
								for(int x = 0; x < temp.length; x++){
									run.getWrong()[x] = temp[x];
								}
								run.getWrong()[run.getWrong().length-1] = run.getCounter()-1;
							}
						}catch(NullPointerException NE){}
					}
				}
				else if(options.getReviewing() && run.getWrong().length == run.getWrongIndex()){
					options.setReviewing(false);
					run.resetWrongRight();
				}
			}
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args){
		File[] listOfFiles = (new File(workDir+"\\Test Files")).listFiles();
		
		VocabPrac start = new VocabPrac(listOfFiles);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void exit(){
		System.exit(0);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void run(){
		if(runExit)
			exit();
	}

	public void keyPressed(KeyEvent arg0) {}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}
