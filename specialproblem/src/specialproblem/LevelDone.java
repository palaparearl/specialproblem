package specialproblem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class LevelDone extends State {
	private UIManager uiManager;
	private UIImageButton mute, unmute;
	private boolean isAccomplished;

	public LevelDone(Handler handler, boolean isAccomplished, int level) {
		super(handler);
		this.isAccomplished = isAccomplished;
		
		uiManager = new UIManager(handler);
		
		mute = new UIImageButton(794 + 48, 10, 0, 0, Assets.mute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().pauseMusic();
			}
		});
		
		uiManager.addObject(mute);
		
		unmute = new UIImageButton(794 + 48, 10, 0, 0, Assets.unmute, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().playMusic();
			}
		});
		
		uiManager.addObject(unmute);
		
		if(isAccomplished == true) {
			if(level != 10) {
				uiManager.addObject(new UIImageButton(349, 400, 32 * 3, 32, Assets.menu, new ClickListener() {
					@Override
					public void onClick() {
						State.setState(handler.getGame().menuState);
						handler.getGame().menuState.setUIManager();
					}
				}));
				
				uiManager.addObject(new UIImageButton(349 + 10 + 96, 400, 32 * 3, 32, Assets.nextLevel, new ClickListener() {
					@Override
					public void onClick() {
						State.setState(handler.getGame().teaching[level]);
						handler.getGame().teaching[level].setUIManager();
					}
				}));
			}
			else {
				uiManager.addObject(new UIImageButton(450 - 96 / 2, 400, 32 * 3, 32, Assets.menu, new ClickListener() {
					@Override
					public void onClick() {
						State.setState(handler.getGame().menuState);
						handler.getGame().menuState.setUIManager();
					}
				}));
			}
		}
		else {
			uiManager.addObject(new UIImageButton(349, 400, 32 * 3, 32, Assets.menu, new ClickListener() {
				@Override
				public void onClick() {
					State.setState(handler.getGame().menuState);
					handler.getGame().menuState.setUIManager();
				}
			}));
			
			uiManager.addObject(new UIImageButton(349 + 10 + 96, 400, 32 * 3, 32, Assets.restartLevelFailed, new ClickListener() {
				@Override
				public void onClick() {
					State.setState(handler.getGame().teaching[level - 1]);
					handler.getGame().teaching[level - 1].setUIManager();
				}
			}));
		}
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
		
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(50, 130, handler.getWidth() - 100, 350, 35, 25);
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
		if(isAccomplished == true) {
			Text.drawString(g, "LEVEL ACCOMPLISHED!", handler.getGame().getWidth() / 2,
					250, true, Color.BLACK, Assets.courier);
		}
		else {
			Text.drawString(g, "LEVEL FAILED", handler.getGame().getWidth() / 2,
					200, true, Color.BLACK, Assets.courier);
		}
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
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
}
