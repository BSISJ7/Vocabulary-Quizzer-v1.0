import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MusicProgram implements ActionListener{

	private PlayMusic themeMusic;
	private ChangeMusic changeMusic;
	private JPanel mainPanel;
	private int numFiles;
	private boolean running;
	private boolean PlayMusic;
	private String songName;
	private String nextSong;
	private File aFile;
	private File[] allFiles;
	
	private String currentTheme;
	private String[] fileNames;
	private String fileNamesList;
	
	private JComboBox musicJCB;
	
	
	private int choiceNum;
	
	private File loadedSong;
	
	private PlayMusic song;
	
	private boolean runMusic;	
	private boolean runExit;
	private boolean saveWrong = false;
	private boolean playRightSound = false;
	private boolean playWrongSound = false;
	private boolean hiddenAnswers = false;
	private boolean fillInTheBlank = false;
	private boolean synonymsTest = false;
	private boolean matchingWordTest = false;
	private boolean definitionsTest = false;

	private String setSong;

	private JSlider volumeSlider;

	private ActionListener langListener;
	
	/*public static void main(String[] args) {

	}*/

	public MusicProgram(){
		mainPanel = new JPanel(){
			protected void paintComponent(Graphics g)
				{
				Dimension d = getSize();
				g.drawImage(new ImageIcon(VocabPrac.workDir+"\\clouds.jpg").getImage(), 0, 0, d.width, d.height, null);
				
				super.paintComponent(g);
				}
			};
			
			numFiles = 0;
			running = true;
			PlayMusic = false;
			loadedSong = new File(songName);
			setSong = songName;
			choiceNum = 0;
			musicJCB = new JComboBox();
			nextSong = songName;
	}
	
	
	public JPanel setup(){
		
		JButton backButton = new JButton("Back");
		QuickSwing cust = new QuickSwing("Back", VocabPrac.workDir+"\\bluebutton.jpg", 180, 30);
		backButton = cust.getButton();
		backButton.setPreferredSize(new Dimension(180, 30));
		backButton.setActionCommand("exitMusic");
		backButton.addActionListener(this);
		
		JButton pauseButton = new JButton("Pause");
		cust = new QuickSwing("Pause", VocabPrac.workDir+"\\bluebutton.jpg", 180, 30);
		pauseButton = cust.getButton();
		pauseButton.setPreferredSize(new Dimension(180, 30));
		pauseButton.setActionCommand("pause");
		pauseButton.addActionListener(this);
		
		JButton playButton = new JButton("Play");
		cust = new QuickSwing("Play", VocabPrac.workDir+"\\bluebutton.jpg", 180, 30);
		playButton = cust.getButton();
		playButton.setPreferredSize(new Dimension(180, 30));
		playButton.setActionCommand("play");
		playButton.addActionListener(this);		
		
		JButton muteButton = new JButton("Mute");
		cust = new QuickSwing("Mute", VocabPrac.workDir+"\\bluebutton.jpg", 180, 30);
		muteButton = cust.getButton();
		muteButton.setPreferredSize(new Dimension(180, 30));
		muteButton.setActionCommand("mute");
		muteButton.addActionListener(langListener);
		
		JPanel backPanel = new JPanel();		
		JPanel musicPanel = new JPanel();
		JPanel playPausePanel = new JPanel();
		JPanel volumePanel = new JPanel();
		JPanel mutePanel = new JPanel();

		musicJCB.setSelectedItem(currentTheme);
		musicJCB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JComboBox cb = (JComboBox) event.getSource();
				String cBox = (String) cb.getSelectedItem();
				loadedSong = new File(cBox);
				nextSong = loadedSong.getName();
				System.out.println("CBOX: " + VocabPrac.workDir+"\\Music\\" + song);
				song.setNextSong(VocabPrac.workDir+"\\Music\\" + cBox);
			}
		});
		
		volumeSlider = new JSlider();
		volumeSlider.setMaximum(20);
		volumeSlider.setMinimum(0);
		volumeSlider.setValue(11);
		volumeSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent source){
				JSlider info = (JSlider)source.getSource();
				song.setVolume((float) volumeSlider.getValue()/20);
			}
		});


		volumePanel.add(volumeSlider);
		musicPanel.add(musicJCB);
		backPanel.add(backButton);
		playPausePanel.add(playButton);
		playPausePanel.add(pauseButton);
		mutePanel.add(muteButton);

		musicPanel.setLayout(new GridLayout());
		backPanel.setLayout(new GridLayout());
		playPausePanel.setLayout(new GridLayout());
		
		mainPanel.setOpaque(false);
		volumePanel.setOpaque(false);
		volumeSlider.setOpaque(false);
		musicJCB.setOpaque(false);
		musicPanel.setOpaque(false);
		mutePanel.setOpaque(false);
		
		mainPanel.setVisible(true);
		
		mainPanel.add(musicPanel);
		mainPanel.add(volumePanel);
		mainPanel.add(playPausePanel);
		mainPanel.add(mutePanel);
		mainPanel.add(backPanel);
	
		mainPanel.setLayout(new FlowLayout(1,300,60));
		return mainPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String source = arg0.getActionCommand();
		if(source.equals("pause")){
			themeMusic.pauseMusic();
		}

		else if(source.equals("mute")){
			themeMusic.mute();
		}

		else if(source.equals("play")){
			themeMusic.startMusic();
			changeMusic.setCurrentTheme();
			changeMusic.storeCurrentSong();
		}
		
	}
	
	public JSlider getVol(){
		return volumeSlider;
	}
	
	public void setCurrentTheme(){
		currentTheme = (String) musicJCB.getSelectedItem();
	}
	
	public String getCurrentSong(){
		return currentTheme;
	}
	
	public PlayMusic getSong(){
		return song;
	}
	
	public void setRunMusic(boolean run){
		runMusic = run;
	}
	
	public boolean getRunning(){
		return running;
	}

	public JPanel getPanel(){
		return mainPanel;
	}
	
	public String[] getFiles(){
		return fileNames;
	}
	
	public void changeSelected(){
		musicJCB.setSelectedItem(song.getMusicName());
	}
	
	public void getFileNames(File aFolder){
		
		 allFiles = aFolder.listFiles();
		 
		 int files[] = new int[allFiles.length];
		 numFiles = 0;
		 int index = 0;
		 for(int x = 0; x < allFiles.length; x++){
			 if(getExt(x).equalsIgnoreCase("WAV File")){
				 musicJCB.insertItemAt(allFiles[x].getName(), index);
				 files[index++] = x;
				 numFiles++;
			 }
		 }
		 running = false;
	}
	
	public String getExt(int index){
		javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
		String filetype = fc.getTypeDescription(new java.io.File(allFiles[index].getPath()));
		return filetype;
	}

	public void run() {
		(new Thread()).start();
		getFileNames(new File(VocabPrac.workDir+"\\Music"));
		setup();
	}
}
