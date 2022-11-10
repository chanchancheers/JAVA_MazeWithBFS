package maze.bfs;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class PanelController extends JPanel {

	final int x_block_size  = 20;
	final int y_block_size  = 20;
	Maze maze;
	Simulator simulator;
	
	
	public PanelController(Maze maze, Simulator simulator) {
		
		this.maze = maze;
		this.simulator = simulator;
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		drawMaze(g, this.maze.map_array);
		drawSimulator(g, simulator.get_pos_x(), simulator.get_pos_y());
	}
	
	public void drawMaze(Graphics g, int[][] map) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (int row=0; row< map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				if (map[row][col] == 0) {
					g2d.setColor(Color.GRAY);
					g2d.fillRect(col*x_block_size, row*y_block_size, x_block_size, y_block_size);
				}
				// 출발점 == 3
				else if (map[row][col] == 3) {
					g2d.setColor(Color.CYAN);
					g2d.fillRect(col*x_block_size, row*y_block_size, x_block_size, y_block_size);
				}
				// 
				else if (map[row][col] == 5) {
					g2d.setColor(Color.PINK);
					g2d.fillRect(col*x_block_size, row*y_block_size, x_block_size, y_block_size);
				}
				// 도착점 == 4
				else if (map[row][col] == 4) {
					g2d.setColor(Color.RED);
					g2d.fillRect(col*x_block_size, row*y_block_size, x_block_size, y_block_size);
				}
				
			}
		}
		setVisible(true);
		
	}
	
	
	public void drawSimulator(Graphics g, int current_x, int current_y) {
		Graphics2D g2d = (Graphics2D) g;
		
		int row = (int) current_y;
		int col = (int) current_x;
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(col*x_block_size, row*y_block_size, x_block_size, y_block_size);
		setVisible(true);
	}
	
	
	
}
