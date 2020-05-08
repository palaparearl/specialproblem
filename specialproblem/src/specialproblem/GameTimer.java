package specialproblem;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

	Maze maze;
	int secondsPassed = 120;
	Timer myTimer;
	TimerTask task;
	
	public GameTimer(Maze maze) {
		this.maze = maze;
	}
	
	public void start() {
		myTimer = new Timer();
		task = new TimerTask() {
			public void run() {
				secondsPassed--;
				if(secondsPassed == 0) {
					maze.levelDone(1, (float) 0.0);
				}
//				System.out.println(secondsPassed);
			}
		};
		
		myTimer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	public void stop() {
		myTimer.cancel();
		myTimer.purge();
	}
	
	public int getSecondsPassed() {
		return secondsPassed;
	}
}
