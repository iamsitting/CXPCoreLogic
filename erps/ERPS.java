import java.util.*;
import java.io.*;

public class ERPS {
	
	private volatile boolean erps_enabled = false;
	
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

					//simulate bufferin with Scanner
					buffval = reader.nextInt();

					//check if accident
					if(buffval == 1){
						enableERPS();
						break;
					}
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public class DisableButtonClick implements Runnable{
		Scanner reader;
		
		public DisableButtonClick(){
			reader = new Scanner(System.in);
		}

		public void run(){

			try{
				while(true){
					System.out.println("Disable, if you are okay...");
					int input_val = reader.nextInt();

					if(input_val == 100){
						disableERPS();
						break;
					}
				}	
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			
		}

	}
	
	public void enableERPS(){
		erps_enabled = true;
	}
	
	public void disableERPS(){
		erps_enabled = false;
	}


	public void go(){
	
		Runnable checkIn = new CheckBTBufferIn();
		Thread t1 = new Thread(checkIn);
		

		Runnable disablebtn = new DisableButtonClick();
		Thread t2 = new Thread(disablebtn);
		/***************
		
		Instead of starting, joining, starting, etc.
		Try a new instance each loop
		i.e t1 = new Thread(Runnable {
			public void run(){
				#code
				#See Head First Android for a good example
			}
		})




		****************/
		int count=18;
		while(true){

			//ERPS Enabled
			if(!erps_enabled){
				if (count < 20) {
					try{
						if (t2.isAlive()) t2.join();
						if (!t1.isAlive()) t1.start();
					} catch (Exception ex){
						ex.printStackTrace();
					}
					
					count = 20;
				}
				System.out.println("Processing...");
				try{
					Thread.sleep(1500);
				} catch (InterruptedException ex){
					System.out.println("Something went wrong");
				}
			}
			
			//ERPS Disabled
			else{
				if(count > 12){
					try{
						if (t1.isAlive()) t1.join();
					} catch (InterruptedException ex){
						System.out.println("Something went wrong.");
					}
					t2.start();
					count = 10;
					System.out.println("Enabled!");
				} 

				System.out.println(count +"s left...");
				try{
					Thread.sleep(1000);
					count = count-1;
				} catch (InterruptedException ex){
					System.out.println("Something went wrong.");
				}
				if (count < 1) System.out.println("SOS---HELP!!!");
			}
		}
	
	}
	
	public void timer(){
		
		Runnable disablebtn = new DisableButtonClick();
		Thread t2 = new Thread(disablebtn);
		t2.start();

		int init_time = 10;

		while(init_time > 0){
			System.out.println(init_time +" s left...");
			if(erps_enabled){
				try{
					Thread.sleep(1000);
					init_time = init_time-1;
				} catch (InterruptedException ex){
					System.out.println("Something went wrong.");
				}
			}
			else{
				break;
			}
		}
		//disablebtn.terminate();
		try{
			t2.join();
		} catch (InterruptedException ex){
			System.out.println("Something went wrong.");
		}
		System.out.println("SOS---HELP!!!");
	}

	public static void main(String[] args){
		ERPS erps = new ERPS();
		erps.go();
	}
	
	
}