package maze.bfs;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;

public class Simulator {

	
	private static Simulator simulator = new Simulator();
	
	private int pos_x;
	private int pos_y;
	
	// 이 좌표쌍은 Maze에 더 어울릴 것 같지만 일단 여기에 박는다.
	private List<Integer> starting_coord = new ArrayList<Integer>();
	
	private Queue<List<Integer>> coord_queue_BFS = new LinkedList<List<Integer>>();
	
	private List<List<Integer>> ref_coord_list_BFS = new ArrayList<List<Integer>>();
	
	
	int print_speed = 50;
	
	
	private Simulator() {
		
	}
	
	static Simulator getInstance() {
		return simulator;
	}
	
	
	public void set_pos_x(int next_x) {
		this.pos_x = next_x;
	}
	
	public void set_pos_y(int next_y) {
		this.pos_y = next_y;
	}
	
	public int get_pos_x() {
		return this.pos_x;
	}
	public int get_pos_y() {
		return this.pos_y;
	}
	
	
	// 지도부터 만들어보
	
	public HashMap<List<Integer>, List<List<Integer>>> prepareMap(
							Maze maze,
							Queue<List<Integer>> SQ,
							List<List<Integer>> ref_coord_list_BFS
							
			) {
		
		// key = 좌표, value = 좌표들의 리스트
		HashMap<List<Integer>, List<List<Integer>>> path_hashmap = new HashMap<List<Integer>, List<List<Integer>>>();
		// 좌표 해시맵을 만들기 위해, 좌표값이 1인 놈들을 순서대로 집어넣고 하나씩 빼면서 가능한 좌표를 입력한
		Queue<List<Integer>> coord_queue = new LinkedList<List<Integer>>();
		// path_hashmap에 key로 들어갈 좌표값	
		List<Integer> point_xy = new ArrayList<Integer>();

		
		
		//시작점 찾기
		for (int row = 0; row < maze.map_array.length; row++) {
			for (int col = 0; col < maze.map_array[0].length; col++) {
				if (maze.map_array[row][col] == 3) {
					this.set_pos_x(col);
					this.set_pos_y(row);
					
					this.starting_coord.add(row);
					this.starting_coord.add(col);
					SQ.offer(this.starting_coord);
					ref_coord_list_BFS.add(this.starting_coord);
				}
				
			}
		}
		
		// 좌표를 작성할 기준
		//do-while을 사용해서 처음엔 get_pos_xy를 이용, 그 다음부턴 coord_queue.poll()을 이용한다
		
		
		
		int point_x = this.get_pos_x(); 
		int point_y = this.get_pos_y();
		
		
		
		

		
		point_xy.add(point_y);
		point_xy.add(point_x); // 여기까진 처음에만 실행
		
		coord_queue.offer(point_xy);
		
		int cnt = 0;
		while (!coord_queue.isEmpty()) {
		
			point_xy = coord_queue.poll();
			point_y = point_xy.get(0);
			point_x = point_xy.get(1);
		
		System.out.println("while 첫부분 Queue : "+coord_queue);
		System.out.println("while 첫부분 point_xy : "+point_xy);
		
		List<List<Integer>> possible_coord_list = new ArrayList<List<Integer>>();
		
		
		//point_x, point_y가 0보다 작을 경우는 신경을 써야합니까 말아야합니까
	
		
		if ((point_x + 1 < maze.map_array[0].length) && (maze.map_array[point_y][point_x + 1] == 1)) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y;
			int possible_x = point_x + 1;
			
			this.common_in_prepareMap(possible_x, possible_y, possible_coord, point_xy, possible_coord_list, path_hashmap, coord_queue);
			
			System.out.println("point_x + 1 success");
			
		} 
		if ((point_y + 1 < maze.map_array.length) && (maze.map_array[point_y + 1][point_x] == 1)) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y + 1;
			int possible_x = point_x;
			
			this.common_in_prepareMap(possible_x, possible_y, possible_coord, point_xy, possible_coord_list, path_hashmap, coord_queue);
			
			System.out.println("point_y + 1 success");
			
		}
		
		if ((point_x - 1 >=0) && (maze.map_array[point_y][point_x - 1] == 1)) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y;
			int possible_x = point_x - 1;
			
			this.common_in_prepareMap(possible_x, possible_y, possible_coord, point_xy, possible_coord_list, path_hashmap, coord_queue);
			

			
			System.out.println("point_x - 1 success");
			}
		
		
		if ((point_y -1 >=0) &&(maze.map_array[point_y - 1][point_x] == 1)) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y - 1;
			int possible_x = point_x;
			
			this.common_in_prepareMap(possible_x, possible_y, possible_coord, point_xy, possible_coord_list, path_hashmap, coord_queue);
			
			
			System.out.println("point_y - 1 success");
			
		}
		
		
		path_hashmap.put(point_xy, possible_coord_list);

		
		System.out.println("");
		
		
		
		cnt++;
		System.out.println("Queue : " + coord_queue);
		System.out.println("Queue에서 뽑아 다음에 처리할 point_xy 좌표 " + point_xy);
		System.out.println("path_hashmap " + path_hashmap);
		System.out.println("while문 " + cnt + " 번 돌았습니다.\n\n");
		}
		
	
		
		
		
		
		
		return path_hashmap;
		
	}
	
	
	
	public void common_in_prepareMap(
									int possible_x, int possible_y,
									List<Integer> possible_coord,
									List<Integer> point_xy,
									List<List<Integer>> possible_coord_list,
									HashMap<List<Integer>, List<List<Integer>>> path_hashmap,
									Queue<List<Integer>> coord_queue
									) {
		
	
		possible_coord.add(possible_y);
		possible_coord.add(possible_x);
		
		System.out.println("possible_coord : " + possible_coord);
		
		possible_coord_list.add(possible_coord);
		
	
		
		if (!path_hashmap.containsKey(point_xy)) {
			coord_queue.offer(possible_coord);
			System.out.println("큐에 뭔가가 들어간 다음의 Queue : "+coord_queue);
		
	}
	}
	
	
	public List<Integer> calculate_path(
								Maze maze,
								Queue<List<Integer>> SQ,
								List<List<Integer>> ref_coord_list_BFS,
								FrameController fc,
								TimeThread th
								
								) {
		
		
		List<Integer> current_coord = SQ.poll();
		System.out.println("BFS계산함수에서 SQ에서 뽑은 것 : " + current_coord);
		
		int point_y = current_coord.get(0);
		int point_x = current_coord.get(1);
		
		this.set_pos_y(point_y);
		this.set_pos_x(point_x);
		fc.pc.repaint();
		try {
			th.sleep(this.print_speed);
		} catch (InterruptedException e) {}
		
		
		
		if ((point_x + 1 < maze.map_array[0].length) && ((maze.map_array[point_y][point_x + 1] == 1)||(maze.map_array[point_y][point_x + 1] == 4 ))) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y;
			int possible_x = point_x + 1;
			
			
			List<Integer> end_coord = this.common_in_calculate(maze, possible_x, possible_y, possible_coord, SQ, ref_coord_list_BFS);
			if (end_coord != null) {
				return end_coord;
			}
			
			
			
			
			
		} 
		if ((point_y + 1 < maze.map_array.length) && ((maze.map_array[point_y + 1][point_x] == 1) ||(maze.map_array[point_y+1][point_x] == 4 ))) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y + 1;
			int possible_x = point_x;
			

			List<Integer> end_coord = this.common_in_calculate(maze, possible_x, possible_y, possible_coord, SQ, ref_coord_list_BFS);
			if (end_coord != null) {
				return end_coord;
			}
			
			
		}
		
		if ((point_x - 1 >=0) && ((maze.map_array[point_y][point_x - 1] == 1) ||(maze.map_array[point_y][point_x - 1] == 4 ))) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y;
			int possible_x = point_x - 1;
			
			
			List<Integer> end_coord = this.common_in_calculate(maze, possible_x, possible_y, possible_coord, SQ, ref_coord_list_BFS);
			if (end_coord != null) {
				return end_coord;
			}
			
			
			}
		
		
		if ((point_y -1 >=0) &&((maze.map_array[point_y - 1][point_x] == 1) ||(maze.map_array[point_y - 1][point_x] == 4 ))) {
			List<Integer> possible_coord = new ArrayList<Integer>();
			int possible_y = point_y - 1;
			int possible_x = point_x;
			
			List<Integer> end_coord = this.common_in_calculate(maze, possible_x, possible_y, possible_coord, SQ, ref_coord_list_BFS);
			if (end_coord != null) {
				return end_coord;
			}
			
		}

		
		return null;
		
	}

	public List<Integer> common_in_calculate(Maze maze,
									int possible_x, int possible_y,
									List<Integer> possible_coord,
									Queue<List<Integer>> SQ,
									List<List<Integer>> ref_coord_list_BFS
			) {
		
		possible_coord.add(possible_y);
		possible_coord.add(possible_x);
		
		if (maze.map_array[possible_y][possible_x] == 4) {
			System.out.println("\n\n=======\n Exit found \n==========\n\n");
			System.out.println(possible_y + " " + possible_x);
			return possible_coord;
		}
		
		if (!ref_coord_list_BFS.contains(possible_coord)) {	
			SQ.offer(possible_coord);
			ref_coord_list_BFS.add(possible_coord);
		}
	return null;
	}
	
}
