package specialproblem;

import java.awt.Graphics;


public abstract class State {
	
	private static State currentState = null;
	private static State prevState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getPrevState() {
		return prevState;
	}
	
	public static void setPrevState(State state) {
		prevState = state;
	}
	
	public static State getState() {
		return currentState;
	}

	// CLASS
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public void setUIManager() {}
	
	public void setUnlockedLevels() {}
	
	public void startTimer() {}
	
	public void stopTimer() {}
	
	public int getLevel() {
		return -1;
	}
	
	public void reset() {}
	
	public void resetCurrRoom() {}
}
