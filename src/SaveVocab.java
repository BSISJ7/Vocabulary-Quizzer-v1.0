import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SaveVocab{

	private final static String workDir = System.getProperty("user.dir");
	private final static String homeDir = System.getProperty("user.home");
	
	VocabList data;
	
	public SaveVocab(VocabList[] info){
		boolean duplicate = duplicateName();
		try{
			ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream(VocabPrac.workDir+"\\Test Files"));
			saved.writeObject(info);
			saved.flush();
			saved.close();
						
		}catch(IOException ex){}
	}	
	
	public boolean duplicateName(){
		boolean duplicate = false;
		try{
			
			ObjectInputStream loadData = new ObjectInputStream(new FileInputStream(VocabPrac.workDir+"\\Test Files"));
			Vocabulary[] tempVocabulary = (Vocabulary[])loadData.readObject();
			loadData.close();
		}catch(Exception E){}

		return duplicate;
	}
}