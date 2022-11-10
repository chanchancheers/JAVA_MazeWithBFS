package maze.bfs;

import java.awt.*;

import javax.swing.*;

public class TimeThread extends Thread {
	private int speed = 1000;
	

	public void run() {
		while(true) {
			try {
				Thread.sleep(speed);
			} catch(InterruptedException e) { return; }
		}
	}
}