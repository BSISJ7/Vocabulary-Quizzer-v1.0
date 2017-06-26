public class GetVocabList {

	private String[] lists;
	private String[] vocab;
	
	public GetVocabList(VocabList[] list){
		int listSize = 1;
		try{
			listSize = list.length+1;
		}catch(NullPointerException  NPE){
			listSize = 1;
			System.out.println("NULL");
		}

		lists = new String[listSize];
		lists[0] = "Vocabulary Lists";
		
		int index = 0;
		for(int x = 1; x < listSize; x++){
			lists[x] = list[index++].getName();
		}
	}
	
	
	public String[] getVocab(VocabList vocab){
		
		int listSize = 1;
		try{
			listSize = vocab.getTotalVocab()+1;
		}catch(NullPointerException  NPE){
		}
		
		this.vocab = new String[listSize];
		this.vocab[0] = "Vocabulary";
		
		int index = 0;
		
		for(int x = 0; x < listSize-1; x++){
			this.vocab[x+1] = vocab.getVocab(x).getName();
		}
		return this.vocab;
	}
	
	public String[] getList(){
		return lists;
	}
}
