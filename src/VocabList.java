import java.io.Serializable;

import javax.swing.JOptionPane;


public class VocabList implements Serializable{

	private Vocabulary[] list;
	private String listName;
	private int listSize;
	private String[] sortList;

	public VocabList(){
		list = new Vocabulary[0];
		listName = "New List";
		listSize = 0;
		sortList = new String[0];
		
	}
	
	public VocabList(String name){
		list = new Vocabulary[0];
		listName = name;
		listSize = 0;
	}

	public void sortList(String newSort){
		if(sortList.length == 0){
			sortList = new String[1];
		}
		else{
			String[] temp = new String[sortList.length];
			
			for(int x = 0; x < sortList.length; x++){
				temp[x] = sortList[x];
			}
			sortList = new String[temp.length+1];
			for(int x = 0; x < sortList.length; x++){
				sortList[x] = temp[x];
			}
			sortList[sortList.length] = newSort;
		}
	}
	
	public void deleteSort(String deleteSort){
		if(sortList.length == 0){
			JOptionPane.showMessageDialog(null, "Error, there are no sorts to delete.");
		}
		else if(sortList.length > 1){
			sortList = new String[0];
		}
		else if(sortList.length > 1){
			String temp[] = new String[sortList.length-1];
			int y = 0;
			for(int x = 0; x < sortList.length; x++){
				if(!sortList[x].equalsIgnoreCase(deleteSort)){
					temp[y++] = sortList[x];
				}
			}
			sortList = new String[temp.length];
			for(int x = 0; x < sortList.length; x++){
				sortList[x] = temp[x];
			}
		}	
	}
	
	public void addVocab(String name){

		if(listSize == 0){
			list = new Vocabulary[++listSize];
			list[listSize-1] = new Vocabulary();
			list[listSize-1].setVocabName(name);
		}
		else{
			Vocabulary[] temp = new Vocabulary[listSize];

			for(int x = 0; x < listSize; x++){
				temp[x] = list[x];
			}

			list = new Vocabulary[++listSize];
			
			for(int x = 0; x < temp.length; x++){
				list[x] = temp[x];
			}
			list[listSize-1] = new Vocabulary();
			list[listSize-1].setVocabName(name);
		}
	}

	public void deleteVocab(int vocabIndex){
		if(listSize == 1){
			list = new Vocabulary[0];
		}
		else{
			Vocabulary[] temp = new Vocabulary[list.length-1];
			
			int index = 0;
			for(int x = 0; x < list.length; x++){
				if(x == vocabIndex){
					x++;
					if(x == list.length){
						break;
					}
				}
				temp[index++] = list[x];
			}
			
			list = new Vocabulary[temp.length];
			
			for(int x = 0; x < list.length; x++){
				list[x] = temp[x];
			}
		}
		listSize--;
	}
	
	public void setName(String name){
		listName = name;
	}
	
	public Vocabulary getVocab(int index){
		return list[index];
	}
	
	public Vocabulary getVocab(String name){
		int index = 0;
		
		for(int x = 0; x < listSize; x++){
			if(list[x].getName().equalsIgnoreCase(name)){
				index = x;
				break;
			}
		}
		return list[index];
	}
	
	public String getName(){
		return listName;
	}
	public int getTotalVocab(){
		return listSize;
	}
}
