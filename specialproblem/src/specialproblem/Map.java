package specialproblem;

import java.awt.Graphics;

public class Map {
	private Maze maze;
	
	public Map(Maze maze) {
		this.maze = maze;
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.mazeMap, 900 - 210 - 20, 600 - 210 - 20, null);
		
		for(int i = 0; i < 9; i++) {
			if(maze.getVisibleRooms()[i] == 1) {
				switch(i) {
					case 0:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 670, 510, null);
						if(maze.getRoomNumber() == 0) {
							g.drawImage(Assets.marker, 670, 510, null);
						}
						break;
					case 1:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 740, 510, null);
						if(maze.getRoomNumber() == 1) {
							g.drawImage(Assets.marker, 740, 510, null);
						}
						break;
					case 2:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 810, 510, null);
						if(maze.getRoomNumber() == 2) {
							g.drawImage(Assets.marker, 810, 510, null);
						}
						break;
					case 3:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 810, 440, null);
						if(maze.getRoomNumber() == 3) {
							g.drawImage(Assets.marker, 810, 440, null);
						}
						break;
					case 4:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 740, 440, null);
						if(maze.getRoomNumber() == 4) {
							g.drawImage(Assets.marker, 740, 440, null);
						}
						break;
					case 5:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 670, 440, null);
						if(maze.getRoomNumber() == 5) {
							g.drawImage(Assets.marker, 670, 440, null);
						}
						break;
					case 6:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 670, 370, null);
						if(maze.getRoomNumber() == 6) {
							g.drawImage(Assets.marker, 670, 370, null);
						}
						break;
					case 7:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 740, 370, null);
						if(maze.getRoomNumber() == 7) {
							g.drawImage(Assets.marker, 740, 370, null);
						}
						break;
					case 8:
						g.drawImage(Assets.mapRooms[MazeLevelAttributes.mapRoomValues[maze.getLevel() - 1][i]], 810, 370, null);
						if(maze.getRoomNumber() == 8) {
							g.drawImage(Assets.marker, 810, 370, null);
						}
						break;
				}
			}
		}
	}
}
