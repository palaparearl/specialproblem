package specialproblem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tutorial extends State {
	private UIManager uiManager;
	
	private BufferedImage[] slides;
	private int currentSlide;
	
	private UIImageButton mute, unmute;
	
	public Tutorial(Handler handler) {
		super(handler);
		
		slides = Assets.tutorialSlides;
		currentSlide = 0;
		
		uiManager = new UIManager(handler);
		
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
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				currentSlide = 0;
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
//		State.getState().setUIManager();
		uiManager.updateRender();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 900, 52);
		g.fillRect(0, 496 + 52, 900, 52);
		
		Text.drawString(g, currentSlide + 1 + "/" + slides.length, 10, 496 + 77, false, Color.BLACK, Assets.trixie);
		
		g.drawImage(slides[currentSlide], 0, 52, 900, 496, null);
		
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
