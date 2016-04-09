import java.util.*;
import java.io.*;

public class ERPS {
	private volatile boolean running = true;
	//BTBuffer In Job
	public class CheckBTBufferIn implements Runnable{
		int buffval;
		//simulate bufferin with System.in
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
						terminate();
						break;
					}
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void terminate(){
		running = false;
	}
	
	public void go(){
		int btbufferin = 0;
		Random rand = new Random();
		int enable = 0;
	
		Runnable checkIn = new CheckBTBufferIn();
		Thread t1 = new Thread(checkIn);
		t1.start();
		
		try{
			while(running){
				System.out.println("Processing...");
				Thread.sleep(1500);
			}
		} catch (InterruptedException ex){
			System.out.println("Something went wrong");
		}
		System.out.println("Enabled!");	
	}
	
	public static void main(String[] args){
		ERPS erps = new ERPS();
		erps.go();
		
	}
	
	
}