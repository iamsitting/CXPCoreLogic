import java.util.*;
import java.io.*;

public class ERPS {
	
	private volatile boolean erps_enabled = false;
	private volatile boolean finished = false;
	
	//BTBuffer In Job
	public class CheckBTBufferIn implements Runnable{
		int buffval;
		Random rand;
		private boolean interrupted;

		public CheckBTBufferIn(){
			this.interrupted = false;
			rand = new Random();
		}
		
		public void run(){
			try{
				while(!this.interrupted){
					System.out.println("Waiting for value: ");

					//simulate bufferin with Scanner
					buffval = rand.nextInt(6);
					System.out.println("Input: "+buffval);

					//check if accident
					if(buffval == 1){
						enableERPS();
						setInterrupt();
					}
				}
				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		private void setInterrupt(){
			this.interrupted = true;
		}
	}

	public class DisableButtonClick implements Runnable{
		Scanner reader;
		private boolean interrupted;
		Random rand;
		
		public DisableButtonClick(){
			this.interrupted = false;
			rand = new Random();
		}

		public void run(){
			System.out.println("Disable, if you are okay...");
			try{
				while(!this.interrupted){
					
					//int input_val = rand.nextInt(20)+10;
					int input_val = 20;
					//System.out.println("Input: "+input_val);

					if(input_val == 25){
						disableERPS();
						setInterrupt();
					}
					if(isMainFinished()){
						setInterrupt();
					}
				}	
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			
		}

		private void setInterrupt(){
			this.interrupted = true;
		}

	}
	
	public void enableERPS(){
		erps_enabled = true;
	}
	
	public void disableERPS(){
		erps_enabled = false;
	}

	public boolean isMainFinished(){
		return finished;
	}

	public void go(){
		

		Thread t1 = new Thread() {
			public void run(){
				//nothing, on purpose
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				//nothing, on purpose
			}
		};

		int count=20;
		while(!finished){

			//ERPS Disabled
			if(!erps_enabled){
				if (count < 30) {
					
					if(count != 20){

						try{
							count = 39;
							while(t2.isAlive()){
								System.out.println("Waiting for t2 to die.");
							}
							t1 = new Thread(new CheckBTBufferIn());
							t1.start();

						} catch (Exception ex){
							ex.printStackTrace();
						}
					} //if-end
					// only happens once
					else {
						count = 40;
						while(t1.isAlive()){
								System.out.println("Waiting for t1 to die.");
							}
						t1 = new Thread(new CheckBTBufferIn());
						t1.start();
					}
				}//if-end

				System.out.println("Processing...");
				try{
					Thread.sleep(1500);
				} catch (InterruptedException ex){
					System.out.println("Something went wrong");
				}
			}//if-end
			
			//ERPS Enabled
			else{
				if(count > 12){
					try{
						while(t1.isAlive()){
							System.out.println("Waiting for t1 to die.");
						}
						t2 = new Thread(new DisableButtonClick());
						t2.start();

					} catch (Exception ex){
						ex.printStackTrace();
					}

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
				if (count < 1){
					System.out.println("SOS---HELP!!!");
					finished = true;
				} 
			}//if-else-end
			System.out.println(count);
		}//while-end
	
	}//go-end

	public static void main(String[] args){
		ERPS erps = new ERPS();
		erps.go();
		System.out.println("Done!");
	}
	
	
}