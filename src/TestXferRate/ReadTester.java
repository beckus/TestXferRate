package TestXferRate;

import java.io.InputStream;

class ReadTester extends Tester {
	volatile protected InputStream in_stream;
	
	ReadTester(InputStream in_stream) {
		this.in_stream = in_stream;
	}
	
    public void run() {
        int b = -1;
        do {
            try {
                b = in_stream.read();
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            bytes_xfered++;
        } while(b != -1);
    }
}
