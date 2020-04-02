package specialproblem;

import java.awt.Color;
import java.awt.Graphics;

public class Credits extends State {
	private UIManager uiManager;
	
	public Credits(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		uiManager.addObject(new UIImageButton(450 - 96 / 2, 500, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.menuBackground, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(handler.getWidth() / 2 - 150, 15, 300, 70, 35, 25);
		Text.drawString(g, "CREDITS", handler.getGame().getWidth() / 2, 57, true, Color.BLACK, Assets.courier);
		
		g.setColor(Color.GRAY);
		g.fillRoundRect(50, 130, handler.getWidth() - 100, 350, 35, 25);
		
		Text.drawString(g, "Developer:", 100, 200, false, Color.BLACK, Assets.trixie);
		Text.drawString(g, "Adviser:", 100, 300, false, Color.BLACK, Assets.trixie);
		Text.drawString(g, "Sprites:", 100, 400, false, Color.BLACK, Assets.trixie);
		
		Text.drawString(g, "Earl Joseph Palapar", 450, 200, false, Color.BLACK, Assets.trixie);
		Text.drawString(g, "Zenith Arnejo", 450, 300, false, Color.BLACK, Assets.trixie);
		Text.drawString(g, "Earl Joseph Palapar", 450, 400, false, Color.BLACK, Assets.trixie);
		
		uiManager.render(g);
	}

	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}
}
