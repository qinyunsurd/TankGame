package com.surd.one;

public class TankMain {

    public static void main(String[] args) throws InterruptedException {
	    TankFrame frame = new TankFrame();

	    int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
	    //初始化敌方坦克
	    for (int i = 0; i < initTankCount; i++){
	    	frame.tanks.add(frame.gf.createTank(50+i*80,200,Dir.DOWN,Group.BAD,frame));
		}
	    new Thread(() -> new Audio("audio/war1.wav").loop()).start();
	    while (true){
	        Thread.sleep(20);
	        frame.repaint();
	    }
    }
}
