package specialproblem;

import java.awt.Color;
import java.awt.Graphics;

public class Room {
	private UIManager uiManager;
	
	private Maze maze;
	private int roomNo;
	private Handler handler;
	private String question;
	private int[] doorPositions;
	private int[] doorDestinations;
	private int correctAnswer;
	private int nextRoom;
	private UIImageButton[] doorButtons;
	private UIImageButton[] choiceButtons;
	private boolean answered;
	
	public Room(Maze maze, int roomNo, Handler handler, String question, int[] doorPositions, int[] doorDestinations, int correctAnswer) {
		this.maze = maze;
		this.roomNo = roomNo;
		this.question = question;
		this.doorPositions = doorPositions;
		this.doorDestinations = doorDestinations;
		this.correctAnswer = correctAnswer;
		
		nextRoom = -1;
		answered = false;
		doorButtons = new UIImageButton[4];
		for(int i = 0; i < 4; i++) {
			doorButtons[i] = null;
		}
		
		uiManager = new UIManager(handler);
		
		choiceButtons = new UIImageButton[MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]];
		for(int i = 0; i < MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]; i++) {
			switch(i) {
				case 0:
					choiceButtons[0] = new UIImageButton(200, 415, 0, 0, Assets.choices[maze.getLevel() - 1][roomNo][0], new ClickListener() {
						@Override
						public void onClick() {
							answered = true;
							disappearChoices();
						}
					});
					uiManager.addObject(choiceButtons[0]);
					break;
				case 1:
					choiceButtons[1] = new UIImageButton(200, 475, 0, 0, Assets.choices[maze.getLevel() - 1][roomNo][1], new ClickListener() {
						@Override
						public void onClick() {
							answered = true;
							disappearChoices();
						}
					});
					uiManager.addObject(choiceButtons[1]);
					break;
				case 2:
					choiceButtons[2] = new UIImageButton(200, 535, 0, 0, Assets.choices[maze.getLevel() - 1][roomNo][2], new ClickListener() {
						@Override
						public void onClick() {
							answered = true;
							disappearChoices();
						}
					});
					uiManager.addObject(choiceButtons[2]);
					break;
			}
		}
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		for(int i = 0; i < 4; i++) {
			if(doorPositions[i] == 1) {
				switch(i) {
					case 0:
						doorButtons[0] = new UIImageButton(100 - 60, 142, 120, 242, Assets.doors[0], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[0]);
//								maze.setRoomNumber(doorDestinations[0]);
								maze.toggleDisplayQuestion();
								nextRoom = doorDestinations[0];
								disappearDoors();
							}
						});
						uiManager.addObject(doorButtons[0]);
						break;
					case 1:
						doorButtons[1] = new UIImageButton(900 / 2 - 60, 127, 120, 198, Assets.doors[1], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[1]);
//								maze.setRoomNumber(doorDestinations[1]);
								maze.toggleDisplayQuestion();
								nextRoom = doorDestinations[1];
								disappearDoors();
							}
						});
						uiManager.addObject(doorButtons[1]);
						break;
					case 2:
						doorButtons[2] = new UIImageButton(800 - 60, 142, 120, 243, Assets.doors[2], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[2]);
//								maze.setRoomNumber(doorDestinations[2]);
								maze.toggleDisplayQuestion();
								nextRoom = doorDestinations[2];
								disappearDoors();
							}
						});
						uiManager.addObject(doorButtons[2]);
						break;
					case 3:
						doorButtons[3] = new UIImageButton(900 / 2 - 25, 400 - 60, 50, 50, Assets.doors[3], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[3]);
//								maze.setRoomNumber(doorDestinations[3]);
								maze.toggleDisplayQuestion();
								nextRoom = doorDestinations[3];
								disappearDoors();
							}
						});
						uiManager.addObject(doorButtons[3]);
						break;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 400, 900, 200);
		
		if(answered) {
			for(int i = 0; i < MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]; i++) {
				switch(i) {
					case 0:
						if(correctAnswer == 0) {
							g.drawImage(Assets.check, 140, 415, null);
						}
						else {
							g.drawImage(Assets.cross, 140, 415, null);
						}
						g.drawImage(Assets.choices[maze.getLevel() - 1][roomNo][0][0], 200, 415, null);
						break;
					case 1:
						if(correctAnswer == 1) {
							g.drawImage(Assets.check, 140, 475, null);
						}
						else {
							g.drawImage(Assets.cross, 140, 475, null);
						}
						g.drawImage(Assets.choices[maze.getLevel() - 1][roomNo][1][0], 200, 475, null);
						break;
					case 2:
						if(correctAnswer == 2) {
							g.drawImage(Assets.check, 140, 535, null);
						}
						else {
							g.drawImage(Assets.cross, 140, 535, null);
						}
						g.drawImage(Assets.choices[maze.getLevel() - 1][roomNo][2][0], 200, 535, null);
						break;
				}
			}
		}
		
//		g.drawImage(Assets.choices[1][6][1][0], 200, 415, null);
//		g.drawImage(Assets.choices[1][6][1][0], 200, 475, null);
//		g.drawImage(Assets.choices[1][6][1][0], 200, 535, null);
//		g.drawImage(Assets.scroll, 5, 10, 600, 350, null);
//		Text.drawStringMultiLine(g, question, 440, 90, 90, Color.BLACK, Assets.arial);
	}
	
	public UIManager getUIManager() {
		return this.uiManager;
	}
	
	public void disappearDoors() {
		for(int i = 0; i < 4; i++) {
			if(doorButtons[i] != null) {
				doorButtons[i].setWidth(0);
				doorButtons[i].setHeight(0);
				doorButtons[i].updateBounds();
				appearChoices();
			}
		}
	}
	
	public void appearChoices() {
		for(int i = 0; i < MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]; i++) {
			choiceButtons[i].setWidth(400);
			choiceButtons[i].setHeight(50);
			choiceButtons[i].updateBounds();
		}
	}
	
	public void disappearChoices() {
		for(int i = 0; i < MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]; i++) {
			choiceButtons[i].setWidth(0);
			choiceButtons[i].setHeight(0);
			choiceButtons[i].updateBounds();
		}
	}
}
