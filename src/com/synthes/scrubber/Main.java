package com.synthes.scrubber;

public class Main {

	
	public static void main(String args[]) {
		//configFile file needs to be full path or local
		try {
			String configFile = args[0];
			System.out.println("Config file is " + configFile);
		
			DataScrubber scrub = new DataScrubber(configFile);
			
			scrub.parseConfig();
			//scrub.connect();
			//scrub.scrub();
			//scrub.disconnect();
			//scrub.log();
			
		
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("No file name"+e);
		}
		

	}
}
