package specialproblem;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
	public State tutorial;
	public State coding;
	public State secondCoding;
	public State thirdCoding;
	public State fourthCoding;
	
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
		createScriptFiles();
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
		tutorial = new Tutorial(handler);
		coding = new Coding(handler);
		secondCoding = new SecondCoding(handler);
		thirdCoding = new ThirdCoding(handler);
		fourthCoding = new FourthCoding(handler);
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
	
	public Display getDisplay() {
		return display;
	}
	
	public void createScriptFiles() {
		try {
			File f = new File("scripts");
			f.mkdir();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("scripts/concat1.sh"));
			writer.write("\n" + 
					"rm scripts/result.txt;\n" + 
					"echo \"false\" > scripts/result.txt;\n" + 
					"\n" + 
					"if [ \"$hello\" != \"world\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"if [ \"$hi\" != \"\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"chmod 755 scripts/concat1_1.sh;\n" + 
					"./scripts/concat1_1.sh;");
			writer.close();
			
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("scripts/concat1_1.sh"));
			writer2.write("#!/bin/bash\n" + 
					"\n" + 
					"if [ \"$hello\" != \"world\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"echo \"true\" > scripts/result.txt;");
			writer2.close();
			
			BufferedWriter writer3 = new BufferedWriter(new FileWriter("scripts/concat1_func.sh"));
			writer3.write("#!/bin/bash\n" + 
					"\n" + 
					"cat scripts/concat1.sh >> scripts/userscript.sh;");
			writer3.close();
			
			BufferedWriter writer4 = new BufferedWriter(new FileWriter("scripts/concat2.sh"));
			writer4.write("\n" + 
					"rm scripts/result.txt;\n" + 
					"echo \"false\" > scripts/result.txt;\n" + 
					"\n" + 
					"if [ \"$global\" != \"global\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(supersedes);\n" + 
					"\n" + 
					"if [ \"$var\" != \"local\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"echo \"true\" > scripts/result.txt;");
			writer4.close();
			
			BufferedWriter writer5 = new BufferedWriter(new FileWriter("scripts/concat2_func.sh"));
			writer5.write("#!/bin/bash\n" + 
					"\n" + 
					"cat scripts/concat2.sh >> scripts/userscript.sh;");
			writer5.close();
			
			BufferedWriter writer6 = new BufferedWriter(new FileWriter("scripts/concat3.sh"));
			writer6.write("\n" + 
					"rm scripts/result.txt;\n" + 
					"echo \"false\" > scripts/result.txt;\n" + 
					"\n" + 
					"var=$(funcname 0);\n" + 
					"if [ \"$var\" != \"zero\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 1);\n" + 
					"if [ \"$var\" != \"one\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 2);\n" + 
					"if [ \"$var\" != \"two\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 3);\n" + 
					"if [ \"$var\" != \"three\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 4);\n" + 
					"if [ \"$var\" != \"four\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 5);\n" + 
					"if [ \"$var\" != \"five\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 6);\n" + 
					"if [ \"$var\" != \"six\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 7);\n" + 
					"if [ \"$var\" != \"seven\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 8);\n" + 
					"if [ \"$var\" != \"eight\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"var=$(funcname 9);\n" + 
					"if [ \"$var\" != \"nine\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"echo \"true\" > scripts/result.txt;");
			writer6.close();
			
			BufferedWriter writer7 = new BufferedWriter(new FileWriter("scripts/concat3_func.sh"));
			writer7.write("#!/bin/bash\n" + 
					"\n" + 
					"cat scripts/concat3.sh >> scripts/userscript.sh;");
			writer7.close();
			
			BufferedWriter writer8 = new BufferedWriter(new FileWriter("scripts/concat4.sh"));
			writer8.write("\n" + 
					"rm scripts/result.txt;\n" + 
					"echo \"false\" > scripts/result.txt;\n" + 
					"\n" + 
					"var=$(echo $(funcname this is sparta));\n" + 
					"var2=\"this is sparta\";\n" + 
					"\n" + 
					"if [ \"$var\" != \"$var2\" ]; then\n" + 
					"	exit;\n" + 
					"fi\n" + 
					"\n" + 
					"echo \"true\" > scripts/result.txt;");
			writer8.close();
			
			BufferedWriter writer9 = new BufferedWriter(new FileWriter("scripts/concat4_func.sh"));
			writer9.write("#!/bin/bash\n" + 
					"\n" + 
					"cat scripts/concat4.sh >> scripts/userscript.sh;");
			writer9.close();
			
			BufferedWriter writer10 = new BufferedWriter(new FileWriter("scripts/keyword1.sh"));
			writer10.write("#!/bin/bash\n" + 
					"\n" + 
					"rm scripts/result.txt;\n" + 
					"echo \"false\" > scripts/result.txt;\n" + 
					"\n" + 
					"if grep -q \"unset\" scripts/userscript.sh; then\n" + 
					"    echo \"true\" > scripts/result.txt;\n" + 
					"fi");
			writer10.close();
			
			BufferedWriter writer11 = new BufferedWriter(new FileWriter("scripts/keyword4.sh"));
			writer11.write("#!/bin/bash\n" + 
					"\n" + 
					"rm scripts/result.txt;\n" + 
					"echo \"false\" > scripts/result.txt;\n" + 
					"\n" + 
					"if grep -q \"shift\" scripts/userscript.sh; then\n" + 
					"    echo \"true\" > scripts/result.txt;\n" + 
					"fi");
			writer11.close();
		}
		catch(Exception e) {
			
		}
	}
}
