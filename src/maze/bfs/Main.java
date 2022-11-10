package maze.bfs;


import java.util.*;

import maze.bfs.TimeThread;

public class Main {

	
	static Queue<List<Integer>> SQ = new LinkedList<List<Integer>>();
	static List<List<Integer>> ref_coord_list_BFS = new ArrayList<List<Integer>>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		TimeThread timer_th = new TimeThread();
		timer_th.start();
		
		Simulator simulator = Simulator.getInstance();
		
		Maze maze = new Maze();
		
		//prepareMap 작업으로 possible_hashmap은 완벽한 지도가 되고, simulator의 starting_x, starting_y가 설정이 된다.
		HashMap<List<Integer>, List<List<Integer>>> possible_hashmap = simulator.prepareMap(maze, SQ, ref_coord_list_BFS);
		
//		System.out.println(possible_hashmap);
		
		
		
		FrameController fc = new FrameController(maze, simulator);
		
		List<Integer> end_condition = new ArrayList<Integer>();
		
		while (!SQ.isEmpty()) {
			end_condition = simulator.calculate_path(maze, SQ, ref_coord_list_BFS, fc, timer_th);
			if (end_condition != null) {
				break;
			}
		}
		
		System.out.print("끝이다 이 짓거리도  " + end_condition);
		
		// 이제 simulator가 BFS를 따라 도착지를 찾는다.
		
		
		
	}

}
