package specialproblem;

import java.awt.Graphics;
import java.util.ArrayList;

public class Crossword extends State {

	private UIManager uiManager;
	private int[] lettersIndices;
	private int numLettersSelected;
	private ArrayList<CrosswordTile> crosswordTiles;
	
	public Crossword(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		lettersIndices = new int[]{1, 17, 25, 2, 8, 11, 12, 13, 18, 13, 20, 7, 0, 4, 10, 13, 14, 11, 7, 13, 11, 4, 3, 19, 23};
		numLettersSelected = 0;
		crosswordTiles = new ArrayList<CrosswordTile>();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				CrosswordTile c = new CrosswordTile((float) (325 + 50 * i), (float)(275 + 50 * j), 50, 50, Assets.alphabet[lettersIndices[5 * i + j]], this);
				uiManager.addObject(c);
				crosswordTiles.add(c);
			}
		}
		
		uiManager.addObject(new UIImageButton(0, 100, 100, 100, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				for(CrosswordTile c : crosswordTiles) {
					if(c.isSelected() == true) {
						c.changeCoord();
					}
				}
				numLettersSelected = 0;
			}
		}));
		
		uiManager.addObject(new UIImageButton(0, 300, 100, 100, Assets.credits_btn, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().platform);
			}
		}));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.cw_wallpaper, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.letterBox, 300, 250, (int)(384 * 0.78125), (int)(384 * 0.78125), null);
//		g.drawImage(Assets.alphabet[25][1], 325, 275, (int)(64 * 0.78125), (int)(64 * 0.78125), null);
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
}
