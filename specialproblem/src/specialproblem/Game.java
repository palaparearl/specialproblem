package specialproblem;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
//	public State platform;
	public State menuState;
	public State credits;
	public State levelSelect;
	public State[] crosswords;
	public State[] mazes;
	public State[] teaching;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	private int[] levelsUnlocked;
	private SimpleAudioPlayer bgMusic;
	private String[] command_dictionary;
	private String[] hintWordsFormed;
	private int numHintWordsFormed;
	private int hints;
		
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		levelsUnlocked = readUnlockedLevels();
//		levelsUnlocked = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		
		readCommandDictionary();
		hints = 0;
		readHintWordsFormed();
		
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
		
//		platform = new Platform(handler);
		menuState = new MenuState(handler);
		credits = new Credits(handler);
		levelSelect = new LevelSelect(handler);
		crosswords = new Crossword[10];
		for(int i = 0; i < 10; i++) {
			crosswords[i] = new Crossword(handler, i + 1, CrosswordLevelAttributes.lettersIndices[i], CrosswordLevelAttributes.words[i], CrosswordLevelAttributes.defs[i]);
		}
		mazes = new Maze[10];
		for(int i = 0; i < 10; i++) {
			mazes[i] = new Maze(handler, i + 1, MazeLevelAttributes.questions[i], MazeLevelAttributes.doorPositions[i], MazeLevelAttributes.doorDestinations[i], MazeLevelAttributes.correctAnswer[i]);
		}
		teaching = new Teaching[10];
		for(int i = 0; i < 10; i++) {
			teaching[i] = new Teaching(handler, i + 1);
		}
//		crossword = new Crossword(handler, 1, CrosswordLevelAttributes.lettersIndices[0], CrosswordLevelAttributes.words[0], CrosswordLevelAttributes.defs[0]);
		display.doneLoading();
		State.setState(menuState);
		try {
			bgMusic = new SimpleAudioPlayer("/sounds/bgMusic.wav");
		}
		catch(Exception e) {
//			System.out.println("cant");
		}
		bgMusic.play();
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
		
		String unlockedLevelsStr = "";
		
		for(int j = 0; j < 10; j++) {
			if(levelsUnlocked[j] == 1) {
				unlockedLevelsStr = unlockedLevelsStr.concat("1");
			}
			else {
				unlockedLevelsStr = unlockedLevelsStr.concat("0");
			}
		}
//		System.out.println(unlockedLevelsStr);
		
		try {
			FileWriter writer = new FileWriter("ul.txt");
			BufferedWriter txtWriter = new BufferedWriter(writer);
			
			txtWriter.write(unlockedLevelsStr);
			
			txtWriter.close();
		}
		catch(Exception f) {
			
		}
	}
	
	public int[] readUnlockedLevels(){
		int[] returnVal = new int[] {1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		
		try {
			BufferedReader txtReader = new BufferedReader(new FileReader("ul.txt"));
			String temp = txtReader.readLine();
			for(int i = 0; i < 10; i++) {
//				returnVal[i] = Character.getNumericValue(temp.charAt(i));
				if(Character.getNumericValue(temp.charAt(i)) == 0) {
					returnVal[i] = -1;
				}
				else {
					returnVal[i] = 1;
				}
			}
			txtReader.close();
		}
		catch(Exception e) {
			String unlockedLevelsStr = "";
			
			for(int i = 0; i < 10; i++) {
				if(returnVal[i] == 1) {
					unlockedLevelsStr = unlockedLevelsStr.concat("1");
				}
				else {
					unlockedLevelsStr = unlockedLevelsStr.concat("0");
				}
			}
//			System.out.println(unlockedLevelsStr);
			
			try {
				FileWriter writer = new FileWriter("ul.txt");
				BufferedWriter txtWriter = new BufferedWriter(writer);
				
				txtWriter.write(unlockedLevelsStr);
				
				txtWriter.close();
			}
			catch(Exception f) {
				
			}
		}
		
		return returnVal;
	}
	
	public void playMusic() {
		bgMusic.play();
//		try {
//			bgMusic.resumeAudio();
//		}
//		catch(Exception e) {
//			
//		}
	}
	
	public void pauseMusic() {
		bgMusic.pause();
	}
	
	public SimpleAudioPlayer getBgMusicPlayer() {
		return bgMusic; 
	}
	
	public void readCommandDictionary() {
		command_dictionary = new String[627];
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(Game.class.getResourceAsStream("/command_dictionary.txt")));
		
		try {
			String line;
			int i = 0;
			
			while((line = reader.readLine()) != null) {
				command_dictionary[i++] = line;
			}
		}
		catch(Exception e) {
			
		}
	}
	
	public void readHintWordsFormed() {
		hintWordsFormed = new String[627];
		numHintWordsFormed = 0;
		
		try {
			BufferedReader txtReader = new BufferedReader(new FileReader("hwf.txt"));
			String line;
			
			while((line = txtReader.readLine()) != null) {
				hintWordsFormed[numHintWordsFormed++] = line;
			}
			
			txtReader.close();
		}
		catch(Exception e) {
			try {
				FileWriter writer = new FileWriter("hwf.txt");
				BufferedWriter txtWriter = new BufferedWriter(writer);
				
				txtWriter.write("");
				
				txtWriter.close();
			}
			catch(Exception f) {
				
			}
		}
	}
	
	public String[] getCommandDictionary() {
		return command_dictionary;
	}
	
	public String[] getHintWordsFormed() {
		return hintWordsFormed;
	}
	
	public int getNumHintWordsFormed() {
		return numHintWordsFormed;
	}
	
	public void setNumHintWordsFormed(int n) {
		numHintWordsFormed = n;
	}
	
	public int getHints() {
		return hints;
	}
	
	public void setHints(int n) {
		hints = n;
	}
	
	public void writeHintsToFile() {
		try {
			FileWriter writer = new FileWriter("hwf.txt");
			BufferedWriter txtWriter = new BufferedWriter(writer);
			
			for(int i = 0; i < numHintWordsFormed; i++) {
				if(i != numHintWordsFormed - 1) {
					txtWriter.write(hintWordsFormed[i] + "\n");
				}
				else {
					txtWriter.write(hintWordsFormed[i]);
				}
			}
			
			txtWriter.close();
		}
		catch(Exception f) {
			
		}
	}
	
	public void createNewInstance(int index) {
		crosswords[index] = new Crossword(handler, index + 1, CrosswordLevelAttributes.lettersIndices[index], CrosswordLevelAttributes.words[index], CrosswordLevelAttributes.defs[index]);
		mazes[index] = new Maze(handler, index + 1, MazeLevelAttributes.questions[index], MazeLevelAttributes.doorPositions[index], MazeLevelAttributes.doorDestinations[index], MazeLevelAttributes.correctAnswer[index]);
	}
}
