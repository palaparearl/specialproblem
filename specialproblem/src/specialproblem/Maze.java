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
	private boolean displayQuestion;
	private int[] visited;
	private int numVisited;
	private Map map;
	private int[] visibleRooms;
	
	private GameTimer timer;
	
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
			rooms[i] = new Room(this, i, handler, this.questions[i], this.doorPositions[i], this.doorDestinations[i], this.correctAnswer[i]);
		}
		
		displayQuestion = true;
		visited = new int[9];
		for(int i = 0; i < 9; i++) {
			visited[i] = -1;
		}
		numVisited = 0;
		map = new Map(this);
		timer = new GameTimer(this);
		visibleRooms = new int[9];
		for(int i = 0; i < 9; i++) {
			visibleRooms[i] = -1;
		}
		visibleRooms[0] = 1;
		
		uiManager = rooms[roomNumber].getUIManager();
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
//		g.drawImage(Assets.doors[3][0], 900 / 2 - 25, 400 - 60, null);
//		
//		g.drawImage(Assets.scroll, 5, 10, 600, 350, null);
//		Text.drawStringMultiLine(g, "What are shell scripts?", 440, 90, 90, Color.BLACK, Assets.lazyMorning);
		
		rooms[roomNumber].render(g);
		
//		g.setColor(Color.BLUE);
//		g.fillRect(900 - 210 - 20, 600 - 210 - 20, 210, 210);
		
		uiManager = rooms[roomNumber].getUIManager();
		setUIManager();
		uiManager.render(g);
		
		g.setColor(Color.RED);
		g.drawRect(900 - 210 - 30, 600 - 210 - 30, 230, 230);
		
		map.render(g);
		
		if(displayQuestion == true) {
			g.drawImage(Assets.scroll, 5, 10, 600, 350, null);
			Text.drawStringMultiLine(g, questions[roomNumber], 421, 90, 80, Color.BLACK, Assets.p22_typewriter);
		}
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		visibleRooms[roomNumber] = 1;
	}
	
	public int getRoomNumber() {
		return this.roomNumber;
	}
	
	public void toggleDisplayQuestion() {
		displayQuestion = !displayQuestion;
	}
	
	public boolean getDisplayQuestion() {
		return displayQuestion;
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean checkVisited(int n) {
		for(int i = 0; i < 9; i++) {
			if(visited[i] == n) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addVisited(int n) {
		visited[numVisited++] = n;
	}
	
	public void reset() {
		roomNumber = 0;
		for(int i = 0; i < 9; i++) {
			visited[i] = -1;
		}
		numVisited = 0;
		for(int i = 0; i < 9; i++) {
			visibleRooms[i] = -1;
		}
		visibleRooms[0] = 1;
		timer = new GameTimer(this);
		displayQuestion = true;
		for(int i = 0; i < 9; i++) {
			rooms[i] = new Room(this, i, handler, this.questions[i], this.doorPositions[i], this.doorDestinations[i], this.correctAnswer[i]);
		}
	}
	
	public int[] getVisibleRooms() {
		return visibleRooms;
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public GameTimer getTimer() {
		return timer;
	}
	
	public void resetCurrRoom() {
		rooms[roomNumber].reset();
		displayQuestion = false;
	}
	
	public void torch() {
		int[] temp = new int[9];
		
		for(int i = 0; i < 9; i++) {
			temp[i] = visibleRooms[i];
			visibleRooms[i] = 1;
		}
		
		try {
			Thread.sleep(500);
		}
		catch(Exception e) {
			
		}
		
		for(int i = 0; i < 9; i++) {
			visibleRooms[i] = temp[i];
		}
	}
	
	public void levelFailed() {
		reset();
		handler.getGame().readHintWordsFormed();
		handler.getGame().setHints(0);
		State.setState(handler.getGame().menuState);
		handler.getGame().menuState.setUIManager();
	}
}
