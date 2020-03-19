package specialproblem;

import java.awt.Color;
import java.awt.Graphics;

public class Maze extends State{
	private UIManager uiManager;
	
	private int level;
	private String[] questions;
	private int[][] doorPositions;
	private int[][] doorDestinations;
	private int[] correctAnswer;
	
	private int roomNumber;
	private Room[] rooms;
	
	public Maze(Handler handler, int level, String[] questions, int[][] doorPositions, int[][] doorDestinations, int[] correctAnswer) {
		super(handler);
		
		this.level = level;
		this.questions = questions;
		this.doorPositions = doorPositions;
		this.doorDestinations = doorDestinations;
		this.correctAnswer = correctAnswer;
		
		roomNumber = 0;
		rooms = new Room[9];
		for(int i = 0; i < 9; i++) {
			rooms[i] = new Room(this, this.questions[i], this.doorPositions[i], this.doorDestinations[i], this.correctAnswer[i]);
		}
		
		uiManager = new UIManager(handler);
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.mazeBg, 0, 0, null);
		
//		g.drawImage(Assets.doors[0], 100 - 60, 142, null);
//		g.drawImage(Assets.doors[1], 900 / 2 - 60, 127, null);
//		g.drawImage(Assets.doors[2], 800 - 60, 142, null);
//		
//		g.drawImage(Assets.scroll, 5, 10, 600, 350, null);
//		Text.drawStringMultiLine(g, "What are shell scripts?", 440, 90, 90, Color.BLACK, Assets.lazyMorning);
		
		rooms[roomNumber].render(g);
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 400, 900, 200);
		
//		g.setColor(Color.BLUE);
//		g.fillRect(900 - 210 - 20, 600 - 210 - 20, 210, 210);
				
		g.setColor(Color.RED);
		g.drawRect(900 - 210 - 30, 600 - 210 - 30, 230, 230);
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

}
