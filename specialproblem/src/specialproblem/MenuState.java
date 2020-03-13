package specialproblem;

import java.awt.Color;
import java.awt.Graphics;

public class MenuState extends State{
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 200, 128, 64, Assets.play_btn, new ClickListener() {
			@Override
			public void onClick() {
//				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().crosswords[0]);
				handler.getGame().crosswords[0].setUIManager();
			}
		}));
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 300, 128, 64, Assets.tutorial_btn, new ClickListener() {
			@Override
			public void onClick() {
				System.out.println("tutorial!");
			}
		}));
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 400, 128, 64, Assets.credits_btn, new ClickListener() {
			@Override
			public void onClick() {
				System.out.println("credits!");
			}
		}));
		
		uiManager.addObject(new UIImageButton((handler.getGame().getWidth() / 2) - 64, 500, 128, 64, Assets.quit_btn, new ClickListener() {
			@Override
			public void onClick() {
				System.exit(0);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
		
		Text.drawString(g, "Less Hell with Shell", handler.getGame().getWidth() / 2,
				100, true, Color.YELLOW, Assets.font50);
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

}
