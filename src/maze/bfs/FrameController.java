package maze.bfs;


import java.awt.*;
import javax.swing.*;

public class FrameController extends JFrame {

		PanelController pc;
			
		public FrameController(Maze maze, Simulator simulator) {
			
			this.setTitle("BFS를 활용한 미로찾기");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			pc = new PanelController(maze, simulator);
			
			
			this.add(pc, BorderLayout.CENTER);
			this.setLocationRelativeTo(null);
			this.setSize(700,700);
			this.setVisible(true);
			
			
		}
	
	
		
		
		
		
		
		
	
}
