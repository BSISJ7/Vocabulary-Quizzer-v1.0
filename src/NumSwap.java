import java.util.Random;


public class NumSwap {
	
	public NumSwap(int[] rand, int testSize){
		Random stat = new Random();
		rand = new int[testSize];
		for(int n = 0; n < testSize; n++){
			rand[n] = n;
		}
		
		for(int n = 0; n < testSize; n++){
			int x = Math.abs(stat.nextInt(testSize-1)) % 11;
			int temp = rand[n];
			rand[n] = rand[x];
			rand[x] = temp;
		}
	}
	
}
