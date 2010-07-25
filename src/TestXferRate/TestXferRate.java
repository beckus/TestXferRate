package TestXferRate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestXferRate {
	static void printUsageAndExit() {
		System.out.println("Usage: java -jar TestXferRate.jar [OPTION] FILE");
		System.out.println("Continuously read/write to/from the specified file and periodically");
		System.out.println("report the average transfer rate.  FILE is intended to be a device file,");
		System.out.println("although any file will work.  Press Ctrl+c to end the program.");
		System.out.println("Examples: java -jar TestXferRate.jar -r /dev/zero");
		System.out.println("          java -jar TestXferRate.jar -w /dev/null");
		System.out.println();
		System.out.println("Options (only one may be specified):");
		System.out.println("  -h  display this help message");
		System.out.println("  -r  Read from the file and report the read rate");
		System.out.println("  -w  Write to the file and report the write rate");
		
		System.exit(1);
	}
	
	public static void main(String[] args)
			throws java.lang.InterruptedException, FileNotFoundException {

		Tester tester = null;
		
		// Prints the usage message.  Note this also
		// prints usage for the -h option since
		// it only has one parameter.
		if(args.length != 2) {
			printUsageAndExit();
		}

		String option = args[0];
		String filename = args[1];
		
		if(option.equals("-r")) {
			System.out.println("Reading from file " + filename);
			tester = new ReadTester(new FileInputStream(filename));
		} else if(option.equals("-w")) {
			System.out.println("Writing to file " + filename);
			tester = new WriteTester(new FileOutputStream(filename));
		} else {
			printUsageAndExit();
		}
		
		tester.start();
		
		while(true) {
			long start_bytes = tester.getBytesXfered();
			Thread.sleep(1000); // One second
			long end_bytes = tester.getBytesXfered();
            //Eventually the number of bytes will overflow, although with the long type,
			//it should take a lifetime.
            //Even if it overflowed, I think the delta calculation would be correct.
			long delta = end_bytes - start_bytes;
			//This will not be perfectly accurate depending on the actual time of the sleep,
			//but it should be close enough (we can never be perfect while running inside a non-real time
			//operating system).
			System.out.println("Rate: " + delta + "/s");
		}
	}
}
