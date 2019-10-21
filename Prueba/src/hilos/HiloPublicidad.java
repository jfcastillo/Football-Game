package hilos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HiloPublicidad extends Thread {
	private boolean stop;
	private int min;
	private int seg;
	private static DatagramSocket socketMulti;
    private static InetAddress group;
    private static byte[] buf1;
    private static byte[] buf2;
    private static byte[] buf;
	
	public  HiloPublicidad(int min, int seg) {
		this.min = min;
		this.seg = seg;
	}
	
	@Override
	public void run() {
		try {
			multicastPublisher("./resources/ads/coke.gif", "./resources/ads/coke.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSeg() {
		return seg;
	}

	public void setSeg(int seg) {
		this.seg = seg;
	}

	public void multicastPublisher(String img, String fil) throws IOException {
        socketMulti = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        buf1= img.getBytes();
        buf2= fil.getBytes();
        
        DatagramPacket packet1  = new DatagramPacket(buf1, buf1.length, group, 4446);
        DatagramPacket packet2  = new DatagramPacket(buf2, buf2.length, group, 4446);
        socketMulti.send(packet1);
        socketMulti.send(packet2);
        socketMulti.close();
    }

}
