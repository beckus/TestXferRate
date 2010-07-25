package TestXferRate;

class Tester extends Thread {
    volatile protected long bytes_xfered = 0;

    public long getBytesXfered() {
        return bytes_xfered;
    }
}
