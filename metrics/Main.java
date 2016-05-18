import java.util.*;
import java.io.*;

public class Main {
	private volatile boolean main_finished = false;
	public int speed = 0;
	public int distance = 0;
	public int ads = 0;

	public class Speed extends METRICS implements Runnable{
		
		public void run(){
			while(!isInterrupted()){
				val = rand.nextInt(1000);
				setSpeed(val);
				if(val%100 == 2) printMetric();
				if(isMainFinished()) setInterrupt();
			}
		}
	}

	public class Distance extends METRICS implements Runnable{
		
		public void run(){
			while(!isInterrupted()){
				val = rand.nextInt(1000);
				setSpeed(val);
				if(val%100 == 3) printMetric();
				if(isMainFinished()) setInterrupt();
			}
		}
	}
	
	public class ADS extends METRICS implements Runnable{
		
		public void run(){
			while(!isInterrupted()){
				val = rand.nextInt(1000);
				setSpeed(val);
				if(val%100 == 4) printMetric();
				if(isMainFinished()) setInterrupt();
			}
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
		Main m = new Main();
		m.go();
		System.out.println("Done!");
	}
}