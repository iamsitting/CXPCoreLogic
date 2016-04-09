import java.util.Random;

public class ERPS {
	public static void main(String[] args){
		int btbufferin = 0;
		Random rand = new Random();
		int enable = 0;
		//check bt receiver
		while (true){
			btbufferin = rand.nextInt(10) + 1;
			
			if (btbufferin == 1){
				enable = 1;
				break;
			}
		}
		
		if(enable == 1){
			System.out.println(btbufferin);
			System.out.println("ERPS enabled");
		}
	}
}