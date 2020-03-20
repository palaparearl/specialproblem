package specialproblem;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
	public State platform;
	public State menuState;
	public State levelSelect;
	public State[] crosswords;
	public State[] mazes;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	private int[] levelsUnlocked;
		
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		levelsUnlocked = new int[]{1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		CrosswordLevelAttributes.init();
		MazeLevelAttributes.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		platform = new Platform(handler);
		menuState = new MenuState(handler);
		levelSelect = new LevelSelect(handler);
		crosswords = new Crossword[10];
		for(int i = 0; i < 10; i++) {
			crosswords[i] = new Crossword(handler, i + 1, CrosswordLevelAttributes.lettersIndices[i], CrosswordLevelAttributes.words[i], CrosswordLevelAttributes.defs[i]);
		}
		mazes = new Maze[10];
		for(int i = 0; i < 10; i++) {
			mazes[i] = new Maze(handler, i + 1, MazeLevelAttributes.questions[i], MazeLevelAttributes.doorPositions[i], MazeLevelAttributes.doorDestinations[i], MazeLevelAttributes.correctAnswer[i]);
		}
//		crossword = new Crossword(handler, 1, CrosswordLevelAttributes.lettersIndices[0], CrosswordLevelAttributes.words[0], CrosswordLevelAttributes.defs[0]);
		State.setState(menuState);
	}
		
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		
		if(State.getState() != null)
			State.getState().render(g);
		
		// End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
//				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getLevelsUnlocked(int i) {
		return levelsUnlocked[i];
	}

	public void unlockLevel(int i) {
		levelsUnlocked[i] = 1;
	}
}
