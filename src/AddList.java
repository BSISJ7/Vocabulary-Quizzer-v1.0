import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddList {

	private JButton addList;
	private JButton back;
	private JButton deleteList;
	private JButton editList;
	
	private JTextField listName;
	private int listSize;
	private VocabList[] lists;
	private Vocabulary data;
	private JPanel mainPanel;
	private Vocabulary[] vocabData;
	private int indexVocab;
	private int indexList;
	
	public AddList(){
		mainPanel = new JPanel();
		vocabData = new Vocabulary[0];
		
		mainPanel.setOpaque(false);
		mainPanel.setVisible(true);

		indexVocab = 0;
		indexList = 0;
	}
	
	public void setup(){
		 
	}
	
public void loadLists(){
		
		if(new File("SavedData\\Saved.dat").exists()){
			try{
				ObjectInputStream loadData = new ObjectInputStream(new FileInputStream("SavedData\\Saved.dat"));
				lists = (VocabList[])loadData.readObject();
				loadData.close();
				
				data = lists[0].getVocab(0);
				
			}catch(IOException ex){
				 ex.printStackTrace();
			}
			catch(ClassNotFoundException ex){
				ex.printStackTrace();
			}
		}
		else{
			lists = new VocabList[1];
			lists[0] = new VocabList();
			data = lists[0].getVocab(0);
			
			if(!new File("SavedData").exists())
				new File("SavedData").mkdir();
			
			try{
				ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream("SavedData\\Saved.dat"));
				saveData.writeObject(lists);
				saveData.flush();
				saveData.close();
				
			}catch(IOException ex){
				 ex.printStackTrace();
			}
		}
		
		listSize = lists.length;
	}
}
