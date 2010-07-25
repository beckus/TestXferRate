package TestXferRate;

import java.io.OutputStream;

class WriteTester extends Tester {
	volatile protected OutputStream out_stream;
	
	WriteTester(OutputStream out_stream) {
		this.out_stream = out_stream;
	}

    public void run() {
        do {
            try {
                out_stream.write('x');
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            bytes_xfered++;
        } while(true);
    }
}
