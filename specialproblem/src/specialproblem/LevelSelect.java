package specialproblem;

import java.awt.Color;
import java.awt.Graphics;

public class LevelSelect extends State{
	
	private UIManager uiManager;
	private UIImageButton[] levelButtons;
	
	public LevelSelect(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		levelButtons = new UIImageButton[10];
		
		levelButtons[0] = new UIImageButton(15, 100, 0, 0, Assets.levels[0], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[0]);
				handler.getGame().crosswords[0].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[0]);
		
		levelButtons[1] = new UIImageButton(15, 100 + 85 + 15, 0, 0, Assets.levels[1], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[1]);
				handler.getGame().crosswords[1].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[1]);
		
		levelButtons[2] = new UIImageButton(15, 100 + 85 + 15 + 85 + 15, 0, 0, Assets.levels[2], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[2]);
				handler.getGame().crosswords[2].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[2]);
		
		levelButtons[3] = new UIImageButton(15, 100 + 85 + 15 + 85 + 15 + 85 + 15, 0, 0, Assets.levels[3], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[3]);
				handler.getGame().crosswords[3].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[3]);
		
		levelButtons[4] = new UIImageButton(15, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15, 0, 0, Assets.levels[4], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[4]);
				handler.getGame().crosswords[4].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[4]);
		
		levelButtons[5] = new UIImageButton(900 - 15 - 430, 100, 0, 0, Assets.levels[5], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[5]);
				handler.getGame().crosswords[5].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[5]);
		
		levelButtons[6] = new UIImageButton(900 - 15 - 430, 100 + 85 + 15, 0, 0, Assets.levels[6], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[6]);
				handler.getGame().crosswords[6].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[6]);
		
		levelButtons[7] = new UIImageButton(900 - 15 - 430, 100 + 85 + 15 + 85 + 15, 0, 0, Assets.levels[7], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[7]);
				handler.getGame().crosswords[7].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[7]);
		
		levelButtons[8] = new UIImageButton(900 - 15 - 430, 100 + 85 + 15 + 85 + 15 + 85 + 15, 0, 0, Assets.levels[8], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[8]);
				handler.getGame().crosswords[8].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[8]);
		
		levelButtons[9] = new UIImageButton(900 - 15 - 430, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15, 0, 0, Assets.levels[9], new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().crosswords[9]);
				handler.getGame().crosswords[9].setUIManager();
			}
		});
		uiManager.addObject(levelButtons[9]);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(handler.getWidth() / 2 - 250, 15, 500, 70, 35, 25);
		
//		g.fillRoundRect(15, 100, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15 + 85 + 15 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(15, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15, 430, 85, 35, 25);
//		g.fillRoundRect(900 - 15 - 430, 100, 430, 85, 35, 25);
		
		g.drawImage(Assets.levelsLocked[0], 15, 100, null);
		g.drawImage(Assets.levelsLocked[1], 15, 100 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[2], 15, 100 + 85 + 15 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[3], 15, 100 + 85 + 15 + 85 + 15 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[4], 15, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[5], 900 - 15 - 430, 100, null);
		g.drawImage(Assets.levelsLocked[6], 900 - 15 - 430, 100 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[7], 900 - 15 - 430, 100 + 85 + 15 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[8], 900 - 15 - 430, 100 + 85 + 15 + 85 + 15 + 85 + 15, null);
		g.drawImage(Assets.levelsLocked[9], 900 - 15 - 430, 100 + 85 + 15 + 85 + 15 + 85 + 15 + 85 + 15, null);
		
		Text.drawString(g, "SELECT LEVEL", handler.getGame().getWidth() / 2, 57, true, Color.BLACK, Assets.courier);
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
	
	public void setUnlockedLevels() {
		for(int i = 0; i < 10; i++) {
			if(handler.getGame().getLevelsUnlocked(i) != -1) {
				levelButtons[i].setWidth(430);
				levelButtons[i].setHeight(85);
				levelButtons[i].updateBounds();
			}
		}
	}
}
