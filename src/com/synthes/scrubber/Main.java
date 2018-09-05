package com.synthes.scrubber;

public class Main {

	
	public static void main(String args[]) {
		//configFile file needs to be full path or local
		try {
			String configFile = args[0];
			//String configFile = "C:\\Users\\rbyrne\\dataAnon\\DataAnon\\exampleConfig.xml";
			if(configFile.equals("-h")) {
				System.out.println("Enter file name or -h for help");
				System.exit(1);
			}
			System.out.println("Config file is " + configFile);
		
			DataScrubber scrub = new DataScrubber(configFile);
			
			scrub.parseConfig();
			scrub.connect();
			scrub.migrate();
			scrub.scrub();
			scrub.disconnect();
			
		
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Enter file name or -h for help");
		}
		

	}
}
