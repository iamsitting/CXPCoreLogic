import java.util.*;
import java.io.*;

public class METRICS {
	private volatile boolean main_finished = false;

	public class Speed implements Runnable{
		Random rand;

		public Speed(){
			rand = new Random();
		}
		public void run(){

		}
	}

	public class Distance implements Runnable{
		Random rand;

		public Distance(){
			rand = new Random();
		}
		public void run(){

		}
	}
	
	public class ADS implements Runnable{
		Random rand;

		public ADS(){
			rand = new Random();
		}
		public void run(){

		}
	}

	public boolean isMainFinished(){
		return main_finished;
	}

	public void go(){

	}

	public static void main(String[] args){
		METRICS metrics = new METRICS();
		metrics.go();
		System.out.println("Done!");
	}
}