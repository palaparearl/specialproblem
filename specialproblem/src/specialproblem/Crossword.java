package specialproblem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public Crossword(Handler handler, int level, int[] lettersIndices, String[] words, String[] defs) {
		super(handler);
		uiManager = new UIManager(handler);
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
		proceed = new UIImageButton(handler.getGame().getWidth() / 2 - 48, 150, 0, 0, Assets.proceed, new ClickListener() {
			@Override
			public void onClick() {
//				State.setState(handler.getGame().crosswords[level]);
//				handler.getGame().crosswords[level].setUIManager();
				
				State.setState(handler.getGame().mazes[level - 1]);
				handler.getGame().mazes[level - 1].setUIManager();
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
				if(numWordsCreatedIndices != words.length) {
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
						}
					}
				}
			}
		}));
		
		uiManager.addObject(new UIImageButton(700, 400, 32 * 3, 32 * 2, Assets.clear_btn, new ClickListener() {
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
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		uiManager.addObject(proceed);
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
		g.drawImage(Assets.cw_wallpaper, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.letterBox, 300, 250, null);
//		g.drawImage(Assets.alphabet[25][1], 325, 275, (int)(64 * 0.78125), (int)(64 * 0.78125), null);
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
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
		uiManager.render(g);
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
		numLettersSelected = 0;
		stringFormed = "";
		for(int i = 0; i < this.words.length; i++) {
			wordsCreatedIndices[i] = -1;
		}
		numWordsCreatedIndices = 0;
		disableNextLevel();
	}
}
