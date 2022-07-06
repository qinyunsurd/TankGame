package com.surd.one;

public class TankMain {

    public static void main(String[] args) throws InterruptedException {
	    TankFrame frame = new TankFrame();
	    while (true){
	        Thread.sleep(30);
	        frame.repaint();
	    }
    }
}
