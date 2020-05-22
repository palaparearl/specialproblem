package specialproblem;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FourthCoding extends State {
	private UIManager uiManager;
	private UIImageButton mute, unmute;
	private String result;
	
	public FourthCoding(Handler handler) {
		super(handler);
		
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
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
				handler.getGame().getDisplay().setFrameInvisible(3);
			}
		}));
		
		uiManager.addObject(new UIImageButton(540 + 180 - 96 / 2, 450, 96, 64, Assets.submit_btn, new ClickListener() {
			@Override
			public void onClick() {
				createScriptFile();
				if(checkKeyword() == true) {
					concatScriptFile();
					runScripts();
				}
				checkOutput();
			}
		}));
		
		result = "";
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
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 900, 600);
		g.setColor(Color.GRAY);
		g.fillRect(40, 600 - 540 - 20, 500, 540);
		
		g.drawImage(Assets.blackBoard4, 560, 62, null);
		g.drawImage(Assets.codeHere, 545, 435, null);
		
		if(handler.getGame().getDisplay().getFrame().isActive() == false && handler.getGame().getDisplay().getChildFrame(3).isActive() == false) {
			handler.getGame().getDisplay().getChildFrame(3).setExtendedState(Frame.ICONIFIED);
		}
		else {
			handler.getGame().getDisplay().getChildFrame(3).setExtendedState(Frame.NORMAL);
		}
		
		if(result.equals("") == false) {
			Text.drawString(g, result, 540 + 180, 550, true, Color.BLACK, Assets.monospace);
		}
		
		uiManager.render(g);
	}
	
	public void setUIManager() {
		handler.getMouseManager().setUIManager(uiManager);
		handler.getGame().getDisplay().setFrameVisible(3);
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
	
	public void createScriptFile() {
		try {
//			Process pr = Runtime.getRuntime().exec("rm scripts/userscript.sh");
			BufferedWriter outFile = new BufferedWriter(new FileWriter("scripts/userscript.sh"));
			handler.getGame().getDisplay().getTextArea(3).write(outFile);
			outFile.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void concatScriptFile() {
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/concat4_func.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/concat4_func.sh"};
			Process pr2 = Runtime.getRuntime().exec(cmd);
			while(pr2.isAlive()) {
				
			}
		}
		catch(Exception e) {
			
		}
	}
	
	public void runScripts() {
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/userscript.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/userscript.sh"};
			Process pr2 = Runtime.getRuntime().exec(cmd);
			while(pr2.isAlive()) {
				
			}
			
			if(pr2.exitValue() != 0) {
				BufferedWriter writer = new BufferedWriter(new FileWriter("scripts/result.txt"));
				writer.write("false");
				writer.close();
			}
			
//			System.out.println("run scrpts");
//			checkOutput(pr2);
		}
		catch(Exception e) {
			
		}
	}
	
	public void checkOutput() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("scripts/result.txt"));
			
			result = reader.readLine();
			reader.close();
			
			deleteScripts();
		}
		catch(Exception e) {
			
		}
	}
	
	public void deleteScripts() {
		try {
			File f1 = new File("scripts/result.txt");
			File f2 = new File("scripts/userscript.sh");
			
			f1.delete();
			f2.delete();
		}
		catch(Exception e) {
			
		}
	}
	
	public boolean checkKeyword() {
		String returnVal = "";
		
		try {
			Process pr = Runtime.getRuntime().exec("chmod 755 scripts/keyword4.sh");
			while(pr.isAlive()) {
				
			}
			
			String[] cmd = new String[] {"/bin/bash", "scripts/keyword4.sh"};
			Process pr2 = Runtime.getRuntime().exec(cmd);
			while(pr2.isAlive()) {
				
			}
			
			BufferedReader reader = new BufferedReader(new FileReader("scripts/result.txt"));
			
			returnVal = reader.readLine();
			reader.close();
		}
		catch(Exception e) {
			
		}
		
		if(returnVal.equals("true")) {
			return true;
		}
		
		return false;
	}
	
}
