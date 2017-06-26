import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class VocabPracSetup {

	public VocabPracSetup(){
		if(!new File(VocabPrac.workDir+"").exists()){
			boolean success = (new File(VocabPrac.workDir+"")).mkdir();
		}
		if(!new File(VocabPrac.workDir+"\\Test Files").exists()){
			boolean success = (new File(VocabPrac.workDir+"\\Test Files")).mkdir();
		}
		if(!new File(VocabPrac.workDir+"\\Saved Data").exists()){
			boolean success = (new File(VocabPrac.workDir+"\\Saved Data")).mkdir();
		}
		if(!new File(VocabPrac.workDir+"\\Music").exists()){
			boolean success = (new File(VocabPrac.workDir+"\\Music")).mkdir();
		}
		
		if(!new File(VocabPrac.workDir+"\\Options.ini").exists()){
			try {
				PrintWriter store = new PrintWriter(new FileOutputStream(new File(VocabPrac.workDir+"\\Options.ini")), true);
				store.println(false);
				store.println(4);
				store.println(true);
				store.println(false);
				store.println(false);
				store.println(false);
				store.println(false);
				store.println(false);
				store.println(true);
				store.println(false);
				store.println(false);
				store.println("None");
			} catch (FileNotFoundException e) {}
		}
	}
	
	public File[] getFiles(){
		return (new File(VocabPrac.workDir+"\\Test Files")).listFiles();
	}
}
