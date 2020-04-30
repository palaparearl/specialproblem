package specialproblem;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Crossword extends State {
	
	public final String[] alphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

	private UIManager uiManager;
	private int level;
	private int[] lettersIndices;
	private int numLettersSelected;
	private ArrayList<CrosswordTile> crosswordTiles;
	private String stringFormed;
	private String[] words;
	private String[] defs;
	private int[] wordsCreatedIndices;
	private int numWordsCreatedIndices;
	private int defStartLine;
	private UIImageButton proceed;
	private UIImageButton mute, unmute, restartLevel, closeHintWindow;
	private boolean showHintWordsWindow;
	private String[] tempStrArr;
	private int tempStrArrLen;
	
	public Crossword(Handler handler, int level, int[] lettersIndices, String[] words, String[] defs) {
		super(handler);
		uiManager = new UIManager(handler);
		
		restartLevel = new UIImageButton(794 - 58 - 58, 10, 48, 32, Assets.restartLevel, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().createNewInstance(level - 1);
				handler.getGame().readHintWordsFormed();
				handler.getGame().setHints(0);
				State.setState(handler.getGame().teaching[level - 1]);
				handler.getGame().teaching[level - 1].setUIManager();
			}
		});
		
		uiManager.addObject(restartLevel);
		
		closeHintWindow = new UIImageButton(900 - 10 - 48, 52, 0, 0, Assets.closeHintWordsWindow, new ClickListener() {
			@Override
			public void onClick() {
				showHintWordsWindow = false;
				closeHintWindow.setWidth(0);
				closeHintWindow.setHeight(0);
				closeHintWindow.updateBounds();
			}
		});
		
		uiManager.addObject(closeHintWindow);
		
		mute = new UIImageButton(794 - 58, 10, 0, 0, Assets.mute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().pauseMusic();
			}
		});
		
		uiManager.addObject(mute);
		
		unmute = new UIImageButton(794 - 58, 10, 0, 0, Assets.unmute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().playMusic();
			}
		});
		
		uiManager.addObject(unmute);
		
		this.level = level;
		this.lettersIndices = lettersIndices;
		numLettersSelected = 0;
		crosswordTiles = new ArrayList<CrosswordTile>();
		stringFormed = "";
		this.words = words;
		this.defs = defs;
		wordsCreatedIndices = new int[this.words.length];
		for(int i = 0; i < this.words.length; i++) {
			wordsCreatedIndices[i] = -1;
		}
		numWordsCreatedIndices = 0;
		if(defs.length == 4) {
			defStartLine = 145;
		}
		else {
			defStartLine = 145 + 130;
		}
		
		showHintWordsWindow = false;
		tempStrArr = new String[627];
		tempStrArrLen = 0;
		
		proceed = new UIImageButton(handler.getGame().getWidth() / 2 - 48, 150, 0, 0, Assets.proceed, new ClickListener() {
			@Override
			public void onClick() {
//				State.setState(handler.getGame().crosswords[level]);
//				handler.getGame().crosswords[level].setUIManager();
				
				State.setState(handler.getGame().mazes[level - 1]);
				handler.getGame().mazes[level - 1].setUIManager();
				handler.getGame().mazes[level - 1].startTimer();
				reset();
			}
		});
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				CrosswordTile c = new CrosswordTile((float) (325 + 50 * i), (float)(275 + 50 * j), 50, 50, Assets.alphabet[lettersIndices[5 * i + j]], lettersIndices[5 * i + j], this);
				uiManager.addObject(c);
				crosswordTiles.add(c);
			}
		}
		
		uiManager.addObject(new UIImageButton(700, 270, 32 * 3, 32 * 2, Assets.submit_btn, new ClickListener() {
			@Override
			public void onClick() {
//				if(numWordsCreatedIndices != words.length) {
				
				boolean found = false;
				
				for(int i = 0; i < words.length; i++) {
					if(words[i].equals(stringFormed) && contains(wordsCreatedIndices, i) == false) {
						wordsCreatedIndices[numWordsCreatedIndices++] = i;
						for(CrosswordTile c : crosswordTiles) {
							if(c.isSelected() == true) {
								c.resetCoord();
							}
						}
						stringFormed = "";
						numLettersSelected = 0;
						if(numWordsCreatedIndices == words.length) {
							enableNextLevel();
						}
						
						found = true;
					}
				}
				
				if(found == false) {
					if(containsStringArray(handler.getGame().getCommandDictionary(), handler.getGame().getCommandDictionary().length, stringFormed)
							&& containsStringArray(handler.getGame().getHintWordsFormed(), handler.getGame().getNumHintWordsFormed(), stringFormed) == false) {
						handler.getGame().setHints(handler.getGame().getHints() + 1);
						handler.getGame().getHintWordsFormed()[handler.getGame().getNumHintWordsFormed()] = stringFormed;
						handler.getGame().setNumHintWordsFormed(handler.getGame().getNumHintWordsFormed() + 1);
						
						tempStrArr[tempStrArrLen++] = stringFormed;
						
						for(CrosswordTile c : crosswordTiles) {
							if(c.isSelected() == true) {
								c.resetCoord();
							}
						}
						stringFormed = "";
						numLettersSelected = 0;
					}
				}
//				}
			}
		}));
		
		uiManager.addObject(new UIImageButton(700, 270 + 64 + 34, 32 * 3, 32 * 2, Assets.clear_btn, new ClickListener() {
			@Override
			public void onClick() {
				for(CrosswordTile c : crosswordTiles) {
					if(c.isSelected() == true) {
						c.resetCoord();
					}
				}
				stringFormed = "";
				numLettersSelected = 0;
			}
		}));
		
		uiManager.addObject(new UIImageButton(900 / 2 - 48, 535, 32 * 3, 32 * 2, Assets.shuffle_btn, new ClickListener() {
			@Override
			public void onClick() {
				// clear first
				for(CrosswordTile c : crosswordTiles) {
					if(c.isSelected() == true) {
						c.resetCoord();
					}
				}
				stringFormed = "";
				numLettersSelected = 0;
				//
				shuffle();
				
//				showHintWordsWindow = true;
//				closeHintWindow.setWidth(48);
//				closeHintWindow.setHeight(32);
//				closeHintWindow.updateBounds();
			}
		}));
		
		uiManager.addObject(new UIImageButton(700, 466, 32 * 3, 32 * 2, Assets.bonusWords, new ClickListener() {
			@Override
			public void onClick() {
				showHintWordsWindow = true;
				closeHintWindow.setWidth(48);
				closeHintWindow.setHeight(32);
				closeHintWindow.updateBounds();
			}
		}));
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setPrevState(State.getState());
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		uiManager.addObject(proceed);
		
		shuffle();
//		
//		uiManager.addObject(new UIImageButton(0, 300, 100, 100, Assets.credits_btn, new ClickListener() {
//			@Override
//			public void onClick() {
//				handler.getMouseManager().setUIManager(null);
//				State.setState(handler.getGame().platform);
//			}
//		}));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(handler.getGame().getBgMusicPlayer().status.equals("play")) {
			onMuteIcon();
		}
		else {
			onUnmuteIcon();
		}
		uiManager.updateRender();
		
		g.drawImage(Assets.cw_wallpaper, 0, 0, handler.getWidth(), handler.getHeight(), null);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
//		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.PINK);
		g2.drawRoundRect(290, -10, 620, 290, 20, 20);
		g2.setStroke(oldStroke);
		
		
		g.drawImage(Assets.letterBox, 300, 250, null);
//		g.drawImage(Assets.alphabet[25][1], 325, 275, (int)(64 * 0.78125), (int)(64 * 0.78125), null);
		
		for(int i = 0; i < defs.length; i++) {
			Text.drawStringMultiLine(g, defs[i], 285, 5, defStartLine + (i * 130), Color.BLACK, Assets.garamonditalic);
		}
//		System.out.println(this.stringFormed);
		
		for(int i = 0; i < words.length; i++) {
			if(wordsCreatedIndices[i] >= 0) {
				int index = wordsCreatedIndices[i];
				Text.drawString(g, words[index], 5, defStartLine + (index * 130) - 20, false, Color.RED, Assets.garamondbold);
			}
		}
		
//		Text.drawString(g, "" + handler.getGame().getHints(), 700, 550, false, Color.BLACK, Assets.arial);
		
		uiManager.render(g);
		
		if(showHintWordsWindow) {
			g.drawImage(Assets.hintWordsWindow, 10, 52, null);
			
			if(handler.getGame().getNumHintWordsFormed() == 0) {
				Text.drawString(g, "No words formed", 900 / 2, 125, true, Color.BLACK, Assets.garamonditalic);
			}
			else {
				int x = 50;
				int y = 125;
				int i, duplicate = 0;
				
				for(i = 0; i < handler.getGame().getNumHintWordsFormed(); i++) {
					if(containsStringArray(tempStrArr, tempStrArrLen, handler.getGame().getHintWordsFormed()[i]) == false) {
						if(i != 0 && i % 20 == 0) {
							x = x + 150;
						}
						Text.drawString(g, "-" + handler.getGame().getHintWordsFormed()[i], x, y + (i % 20) * 23, false, Color.BLACK, Assets.garamonditalic);
					}
					else {
						duplicate++;
					}
				}
				
				i = i - duplicate;
				
				for(int j = 0; j < tempStrArrLen; j++, i++) {
					if(i != 0 && i % 20 == 0) {
						x = x + 150;
					}
					Text.drawString(g, "-" + tempStrArr[j], x, y + (i % 20) * 23, false, Color.RED, Assets.garamonditalic);
				}
			}
			
			
		}
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
	public void incrementNumLettersSelected() {
		this.numLettersSelected++;
	}
	
	public void decrementNumLettersSelected() {
		this.numLettersSelected--;
	}

	public int getNumLettersSelected() {
		return numLettersSelected;
	}
	
	public void buildWord(int index) {
		this.stringFormed = this.stringFormed + alphabet[index];
	}
	
	public static boolean contains(final int[] arr, final int key) {
	    return Arrays.stream(arr).anyMatch(i -> i == key);
	}
	
	public void enableNextLevel() {
		proceed.setWidth(32 * 3);
		proceed.setHeight(32 * 2);
		proceed.updateBounds();
	}
	
	public void disableNextLevel() {
		proceed.setWidth(0);
		proceed.setHeight(0);
		proceed.updateBounds();
	}
	
	public void reset() {
		for(CrosswordTile c : crosswordTiles) {
			if(c.isSelected() == true) {
				c.resetCoord();
			}
		}
		stringFormed = "";
		numLettersSelected = 0;
		shuffle();
		for(int i = 0; i < this.words.length; i++) {
			wordsCreatedIndices[i] = -1;
		}
		numWordsCreatedIndices = 0;
		disableNextLevel();
		
		closeHintWindow.setWidth(0);
		closeHintWindow.setHeight(0);
		closeHintWindow.updateBounds();
		showHintWordsWindow = false;
		tempStrArr = new String[627];
		tempStrArrLen = 0;
		
	}
	
	public void onMuteIcon() {
		mute.setWidth(48);
		mute.setHeight(32);
		mute.updateBounds();
		
		unmute.setWidth(0);
		unmute.setHeight(0);
		unmute.updateBounds();
	}
	
	public void onUnmuteIcon() {
		mute.setWidth(0);
		mute.setHeight(0);
		mute.updateBounds();
		
		unmute.setWidth(48);
		unmute.setHeight(32);
		unmute.updateBounds();
	}
	
	public int getLevel() {
		return level;
	}
	
	public void shuffle() {
		int[][] coordinates = new int[25][2];
		int i = 0;
		Random rand = new Random();
		int tempIndex;
		int[] used = new int[25];
		
		for(int j = 0; j < 25; j++) {
			used[j] = -1;
		}
		
		for(CrosswordTile c : crosswordTiles) {
			coordinates[i][0] = c.getOrigX();
			coordinates[i++][1] = c.getOrigY();
		}
		
		for(CrosswordTile c : crosswordTiles) {
			tempIndex = rand.nextInt(25);
			while(used[tempIndex] != -1) {
				tempIndex = rand.nextInt(25);
			}
			
			c.setOrigX(coordinates[tempIndex][0]);
			c.setOrigY(coordinates[tempIndex][1]);
			c.setX(c.getOrigX());
			c.setY(c.getOrigY());
			c.updateBounds();
			
			used[tempIndex] = 1;
		}
	}
	
	public boolean containsStringArray(String[] arr, int size, String str) {
		for(int i = 0; i < size; i++) {
			if(arr[i].equalsIgnoreCase(str)) {
				return true;
			}
		}
		
		return false;
	}
}
