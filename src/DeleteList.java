import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class DeleteList {

	private VocabList[] lists;

	public DeleteList(VocabList[] aList, int skip){
		int listSize = aList.length-1;
		lists = new VocabList[listSize];
		
		int index = 0;
		for(int x = 0; x < aList.length; x++){
			if(x == skip){
				x++;
				if(x == aList.length)
					break;
			}
			lists[index++] = aList[x];
		}
		saveLists();
	}
	
	public void deleteConfirm(){
		JFrame container = new JFrame();
		JPanel acceptConfirmPanel = new JPanel();
		
		JButton deleteButton = new JButton("Delte");
		JButton cancelButton = new JButton("Delte");

		deleteButton.setActionCommand("deleteVocab");
		cancelButton.setActionCommand("cancelVocab");
	}
	
	public VocabList[] getLists(){
		return lists;
	}
	
	public void saveLists(){
		try{
			ObjectOutputStream saveData = new ObjectOutputStream(new FileOutputStream("SavedData\\Saved.dat"));
			saveData.writeObject(lists);
			saveData.flush();
			saveData.close();
			
		}catch(IOException ex){
			 ex.printStackTrace();
		}
	} 
}
