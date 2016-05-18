import java.util.*;
import java.io.*;

public abstract class METRICS{
	public int metric;
	Random rand;
	int val;
	private boolean interrupted;
	
	public METRICS(){
		interrupted = false;
		rand = new Random();
	}
	
	protected void setInterrupt(){
		interrupted = true;
	}
	
	protected void printMetric(){
		System.out.println(val);
	}
	
	protected boolean isInterrupted(){
		return interrupted;
	}
	
}