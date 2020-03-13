package specialproblem;

import java.awt.Color;
import java.awt.Graphics;
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
	private UIImageButton nxt_lvl;
	
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
		defStartLine = 145;
		nxt_lvl = new UIImageButton(handler.getGame().getWidth() / 2 - 48, 150, 0, 0, Assets.nxt_lvl, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[level]);
				handler.getGame().crosswords[level].setUIManager();
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
		
		uiManager.addObject(nxt_lvl);
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
		g.drawImage(Assets.letterBox, 300, 250, (int)(384 * 0.78125), (int)(384 * 0.78125), null);
//		g.drawImage(Assets.alphabet[25][1], 325, 275, (int)(64 * 0.78125), (int)(64 * 0.78125), null);
		for(int i = 0; i < defs.length; i++) {
			Text.drawStringMultiLine(g, defs[i], 290, 5, defStartLine + (i * 130), Color.BLACK, Assets.arial);
		}
//		System.out.println(this.stringFormed);
		
		for(int i = 0; i < words.length; i++) {
			if(wordsCreatedIndices[i] >= 0) {
				int index = wordsCreatedIndices[i];
				Text.drawString(g, words[index], 15, defStartLine + (index * 130) - 30, false, Color.RED, Assets.arial40);
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
		nxt_lvl.setWidth(32 * 3);
		nxt_lvl.setHeight(32 * 2);
		nxt_lvl.updateBounds();
	}
}
