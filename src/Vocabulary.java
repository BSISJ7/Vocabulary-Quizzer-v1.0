import java.io.IOException;
import java.io.Serializable;

import javax.swing.JEditorPane;


public class Vocabulary implements Serializable{

	private JEditorPane definition;
	private JEditorPane synonyms;
	
	private String listName;
	private String[] groups;
	private String vocabName;
	private String matchingWord;
	
	public Vocabulary(){
		definition = new JEditorPane();
		synonyms = new JEditorPane();
		definition.setText("");
		synonyms.setText("");
	
		vocabName = "";
		matchingWord = "";
		listName = "";
	}
	
	public void removeGroup(String stat){
		int removeIndex = 0;
		boolean exists = false;
		int groupSize = groups.length;
		
		for(int x = 0; x < groupSize; x++){
			if(groups[x].equalsIgnoreCase(stat)){
				removeIndex = x;
				exists = true;
				break;
			}
		}
		
		if(exists){
			String[] temp = new String[groups.length-1];
			
			int index = 0;
			for(int x = 0; x < groupSize; x++){
				if(x == removeIndex){
					x++;
				}
				temp[index++] = groups[x];
			}
			groups = new String[--groupSize];
			
			for(int x = 0; x < groupSize; x++){
				groups[x] = temp[x];
			}
		}
	}
	
	public void addGroup(String stat){
		int groupSize = groups.length;
		String[] temp = new String[groups.length];
		
		for(int x = 0; x < groupSize; x++){
			temp[x] = groups[x];
		}

		groups = new String[++groupSize];
		
		for(int x = 0; x < groupSize; x++){
			groups[x] = temp[x];
		}
		groups[groupSize-1] = stat;
	}
	
	public void setListName(String stat){
		listName = stat;
	}
	
	public void setVocabName(String stat){
		vocabName = stat;
	}
	
	public void setDefinition(String info){
		definition.setText(info);
	}

	public void setSynonyms(String info){
		synonyms.setText(info);
	}
	
	public void addSynonym(String info){
		synonyms.setText(synonyms.getText() + info);
	}
	public void setMatchingWord(String stat){
		matchingWord = stat;
	}
	
	public String getName(){
		return vocabName;
	}	

	public JEditorPane getDefinition(){
		return definition;
	}
	
	public String getMatchingWord(){
		return matchingWord;
	}
	
	public JEditorPane getSynonyms(){
		return synonyms;
	}
}
