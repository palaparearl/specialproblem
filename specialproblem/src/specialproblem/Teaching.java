package specialproblem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Teaching extends State{
	private UIManager uiManager;
	
	private int level;
	private BufferedImage[] slides;
	private int currentSlide;
	
	public Teaching(Handler handler) {
		super(handler);
		
		slides = Assets.slides[0];
		currentSlide = 0;
		
		uiManager = new UIManager(handler);
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		uiManager.addObject(new UIImageButton(243, 558, 32 * 3, 32, Assets.first, new ClickListener() {
			@Override
			public void onClick() {
				currentSlide = 0;
			}
		}));
		
		uiManager.addObject(new UIImageButton(349, 558, 32 * 3, 32, Assets.prev, new ClickListener() {
			@Override
			public void onClick() {
				if(currentSlide != 0) {
					currentSlide--;
				}
			}
		}));
		
		uiManager.addObject(new UIImageButton(455, 558, 32 * 3, 32, Assets.next, new ClickListener() {
			@Override
			public void onClick() {
				if(currentSlide != slides.length - 1) {
					currentSlide++;
				}
			}
		}));
		
		uiManager.addObject(new UIImageButton(561, 558, 32 * 3, 32, Assets.last, new ClickListener() {
			@Override
			public void onClick() {
				currentSlide = slides.length - 1;
			}
		}));
		
		uiManager.addObject(new UIImageButton(900 - 10 - 96, 558 - 32, 32 * 3, 32 * 2, Assets.proceed, new ClickListener() {
			@Override
			public void onClick() {
				
			}
		}));
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 900, 52);
		g.fillRect(0, 496 + 52, 900, 52);
		
		Text.drawString(g, currentSlide + 1 + "/" + slides.length, 10, 496 + 77, false, Color.BLACK, Assets.trixie);
		
//		g.fillRect(0, 52, 900, 496);
		
		g.drawImage(slides[currentSlide], 0, 52, 900, 496, null);
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
	}

}
