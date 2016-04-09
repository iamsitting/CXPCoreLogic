import java.util.*;
import java.io.*;

public class ERPS {
	
	//BTBuffer In Job
	public class CheckBTBufferIn implements Runnable{
		int buffval;
		Scanner reader;
		public CheckBTBufferIn(){
			reader = new Scanner(System.in);
		}
		
		public void run(){
			try{
				while(true){
					System.out.println("Waiting for value: ");
					buffval = reader.nextInt();
					if(buffval == 1){
						System.out.println("Enabled!");
						break;
					}
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void go(){
		int btbufferin = 0;
		Random rand = new Random();
		int enable = 0;
	
		Runnable checkIn = new CheckBTBufferIn();
		Thread t1 = new Thread(checkIn);
		t1.start();
		System.out.println("Buffer Checker Started");
	}
	
	public static void main(String[] args){
		ERPS erps = new ERPS();
		erps.go();
		
	}
	
	
}