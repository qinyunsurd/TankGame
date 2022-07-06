package com.surd.one;

public class TankMain {

    public static void main(String[] args) throws InterruptedException {
	    TankFrame frame = new TankFrame();
	    for (int i = 0; i < 5; i++){
	    	frame.tanks.add(new Tank(200+Tank.TANK_WIDTH*i+Tank.TANK_WIDTH,300,Dir.DOWN,frame));
		}
	    while (true){
	        Thread.sleep(30);
	        frame.repaint();
	    }
    }
}
