import java.util.*;
import java.io.*;

public class METRICS {
	private volatile boolean main_finished = false;
	public int speed = 0;
	public int distance = 0;
	public int ads = 0;

	public class Speed implements Runnable{
		Random rand;
		int n;
		private boolean interrupted;
		
		public Speed(){
			this.interrupted = false;
			rand = new Random();
		}
		
		public void run(){
			while(!this.interrupted){
				n = rand.nextInt(1000);
				setSpeed(n);
				if(n==2) printMetric("speed");
				if(isMainFinished()) setInterrupt();
			}
		}
		private void setInterrupt(){
			this.interrupted = true;
		}
	}

	public class Distance implements Runnable{
		Random rand;
		int n;
		private boolean interrupted;

		public Distance(){
			this.interrupted = false;
			rand = new Random();
		}
		
		public void run(){
			while(!interrupted){
				n =  rand.nextInt(1000);
				setDistance(n);
				if (n==3) printMetric("distance");
				if(isMainFinished()) setInterrupt();
			}
			
		}
		
		private void setInterrupt(){
			this.interrupted = true;
		}
		
	}
	
	public class ADS implements Runnable{
		Random rand;
		int n;
		private boolean interrupted;
		
		public ADS(){
			this.interrupted = false;
			rand = new Random();
		}
		
		public void run(){
			n = rand.nextInt(1000);
			setADS(n);
			if (n==1) printMetric("ADS");
			if(isMainFinished()) setInterrupt();
		}
		
		private void setInterrupt(){
			this.interrupted = true;
		}
	}
	
	public void setSpeed(int value){
		speed = value;
	}
	public void setDistance(int value){
		distance = value;
	}
	public void setADS(int value){
		ads = value;
	}
	public void printMetric(String metric){
		switch(metric){
			case "speed":
				System.out.println(speed);
				break;
			case "distance":
				System.out.println(distance);
				break;
			case "ADS":
				System.out.println(ads);
				break;
			default:
				System.out.println("No metric exists");
		}
	}
	public boolean isMainFinished(){
		return main_finished;
	}

	public void go(){
		Thread speedThread = new Thread(new Speed());
		speedThread.start();
		
		Thread distThread = new Thread(new Distance());
		distThread.start();
		
		Thread adsThread = new Thread(new ADS());
		adsThread.start();
		
		int count = 10;
		while(!main_finished){
			System.out.println(count +"s left...");
			
			try{
				Thread.sleep(1000);
			} catch (InterruptedException ex){
				System.out.println("Something went wrong.");
			}
			
			count = count - 1;
			if (count < 1) main_finished = true;
		}
	}

	public static void main(String[] args){
		METRICS metrics = new METRICS();
		metrics.go();
		System.out.println("Done!");
	}
}