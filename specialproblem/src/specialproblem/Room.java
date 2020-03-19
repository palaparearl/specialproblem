package specialproblem;

import java.awt.Color;
import java.awt.Graphics;

public class Room {
	private Maze maze;
	private String question;
	private int[] doorPositions;
	private int[] doorDestinations;
	private int correctAnswer;
	
	public Room(Maze maze, String question, int[] doorPositions, int[] doorDestinations, int correctAnswer) {
		this.maze = maze;
		this.question = question;
		this.doorPositions = doorPositions;
		this.doorDestinations = doorDestinations;
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.scroll, 5, 10, 600, 350, null);
		Text.drawStringMultiLine(g, question, 440, 90, 90, Color.BLACK, Assets.lazyMorning);
	}
}
