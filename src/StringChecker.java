
public class StringChecker {


	public boolean checkForString(String checkString, String... findStrings){
		for(String findString : findStrings){
			//System.out.println("checking: "+checkString);
			if(checkString.toLowerCase().contains(findString)){
				return true;
			}
		}
		return false;
	}
}
