package specialproblem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
	private UIImageButton proceed;
	private boolean answered;
	private UIImageButton mute, unmute, restartLevel, viewQuestion, useHint;
	
	public Room(Maze maze, int roomNo, Handler handler, String question, int[] doorPositions, int[] doorDestinations, int correctAnswer) {
		this.maze = maze;
		this.roomNo = roomNo;
		this.handler = handler;
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
		
		viewQuestion = new UIImageButton(794, 52, 0, 0, Assets.viewQuestion, new ClickListener() {
			@Override
			public void onClick() {
				maze.toggleDisplayQuestion();
				if(maze.getDisplayQuestion() == false) {
					appearDoors();
				}
				else {
					disappearDoors();
				}
			}
		});
		
		uiManager.addObject(viewQuestion);
		
		restartLevel = new UIImageButton(794 - 58 - 58, 10, 48, 32, Assets.restartLevel, new ClickListener() {
			@Override
			public void onClick() {
				handler.getGame().createNewInstance(maze.getLevel()- 1);
				handler.getGame().readHintWordsFormed();
				handler.getGame().setHints(0);
				State.setState(handler.getGame().teaching[maze.getLevel() - 1]);
				handler.getGame().teaching[maze.getLevel() - 1].setUIManager();
			}
		});
		
		uiManager.addObject(restartLevel);
		
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
		
		choiceButtons = new UIImageButton[MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]];
		for(int i = 0; i < MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]; i++) {
			switch(i) {
				case 0:
					choiceButtons[0] = new UIImageButton(200, 415, 400, 50, Assets.choices[maze.getLevel() - 1][roomNo][0], new ClickListener() {
						@Override
						public void onClick() {
							answered = true;
							appearViewQuestion();
							maze.addVisited(roomNo);
							disappearChoices();
							maze.toggleDisplayQuestion();
							appearDoors();
						}
					});
					uiManager.addObject(choiceButtons[0]);
					break;
				case 1:
					choiceButtons[1] = new UIImageButton(200, 475, 400, 50, Assets.choices[maze.getLevel() - 1][roomNo][1], new ClickListener() {
						@Override
						public void onClick() {
							answered = true;
							appearViewQuestion();
							maze.addVisited(roomNo);
							disappearChoices();
							maze.toggleDisplayQuestion();
							appearDoors();
						}
					});
					uiManager.addObject(choiceButtons[1]);
					break;
				case 2:
					choiceButtons[2] = new UIImageButton(200, 535, 400, 50, Assets.choices[maze.getLevel() - 1][roomNo][2], new ClickListener() {
						@Override
						public void onClick() {
							answered = true;
							appearViewQuestion();
							maze.addVisited(roomNo);
							disappearChoices();
							maze.toggleDisplayQuestion();
							appearDoors();
						}
					});
					uiManager.addObject(choiceButtons[2]);
					break;
			}
		}
		
		uiManager.addObject(new UIImageButton(20, 420, 32 * 3, 32, Assets.torch, new ClickListener() {
			@Override
			public void onClick() {
				if(handler.getGame().getHints() > 0) {
					handler.getGame().setHints(handler.getGame().getHints() - 1);
					maze.torch();
				}
			}
		}));
		
		uiManager.addObject(new UIImageButton(20, 462, 32 * 3, 32, Assets.hint, new ClickListener() {
			@Override
			public void onClick() {
				if(handler.getGame().getHints() > 0) {
					handler.getGame().setHints(handler.getGame().getHints() - 1);
					State.setPrevState(State.getState());
					State.setState(new Teaching(handler, maze.getLevel(), true));
					State.getState().setUIManager();
				}
			}
		}));
		
		uiManager.addObject(new UIImageButton(794, 10, 32 * 3, 32, Assets.menu, new ClickListener() {
			@Override
			public void onClick() {
				maze.stopTimer();
				State.setPrevState(State.getState());
				State.setState(handler.getGame().menuState);
				handler.getGame().menuState.setUIManager();
			}
		}));
		
		proceed = new UIImageButton(794, 200 - 32, 0, 0, Assets.proceed, new ClickListener() {
			@Override
			public void onClick() {
				reset();
				if(nextRoom != 99) {
					maze.setRoomNumber(nextRoom);
				}
				else {
					maze.handler.getGame().writeHintsToFile();
					maze.handler.getGame().setHints(0);
					maze.reset();
					if(maze.getLevel() != 10) {
						handler.getGame().unlockLevel(maze.getLevel());
					}
					handler.getGame().levelSelect.setUnlockedLevels();
					State.setState(handler.getGame().levelSelect);
					handler.getGame().levelSelect.setUIManager();
				}
			}
		});
		uiManager.addObject(proceed);
		
		for(int i = 0; i < 4; i++) {
			if(doorPositions[i] == 1) {
				switch(i) {
					case 0:
						doorButtons[0] = new UIImageButton(100 - 60, 142, 0, 0, Assets.doors[0], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[0]);
//								maze.setRoomNumber(doorDestinations[0]);
								if(maze.checkVisited(doorDestinations[0])/* || maze.checkVisited(doorDestinations[0])*/) {
									nextRoom = doorDestinations[0];
									maze.setRoomNumber(nextRoom);
								}
								else {
									if(doorDestinations[0] != 99) {
										maze.toggleDisplayQuestion();
										nextRoom = doorDestinations[0];
//										disappearDoors();
										maze.setRoomNumber(nextRoom);
									}
									else {
										maze.handler.getGame().writeHintsToFile();
										maze.handler.getGame().setHints(0);
										maze.reset();
										if(maze.getLevel() != 10) {
											handler.getGame().unlockLevel(maze.getLevel());
										}
										handler.getGame().levelSelect.setUnlockedLevels();
										State.setState(handler.getGame().levelSelect);
										handler.getGame().levelSelect.setUIManager();
									}
								}
							}
						});
						uiManager.addObject(doorButtons[0]);
						break;
					case 1:
						doorButtons[1] = new UIImageButton(900 / 2 - 60, 127, 0, 0, Assets.doors[1], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[1]);
//								maze.setRoomNumber(doorDestinations[1]);
								if(maze.checkVisited(doorDestinations[1])/* || maze.checkVisited(doorDestinations[1])*/) {
									nextRoom = doorDestinations[1];
									maze.setRoomNumber(nextRoom);
								}
								else {
									if(doorDestinations[1] != 99) {
										maze.toggleDisplayQuestion();
										nextRoom = doorDestinations[1];
//										disappearDoors();
										maze.setRoomNumber(nextRoom);
									}
									else {
										maze.handler.getGame().writeHintsToFile();
										maze.handler.getGame().setHints(0);
										maze.reset();
										if(maze.getLevel() != 10) {
											handler.getGame().unlockLevel(maze.getLevel());
										}
										handler.getGame().levelSelect.setUnlockedLevels();
										State.setState(handler.getGame().levelSelect);
										handler.getGame().levelSelect.setUIManager();
									}
								}
							}
						});
						uiManager.addObject(doorButtons[1]);
						break;
					case 2:
						doorButtons[2] = new UIImageButton(800 - 60, 142, 0, 0, Assets.doors[2], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[2]);
//								maze.setRoomNumber(doorDestinations[2]);
								if(maze.checkVisited(doorDestinations[2])/* || maze.checkVisited(doorDestinations[2])*/) {
									nextRoom = doorDestinations[2];
									maze.setRoomNumber(nextRoom);
								}
								else {
									if(doorDestinations[2] != 99) {
										maze.toggleDisplayQuestion();
										nextRoom = doorDestinations[2];
//										disappearDoors();
										maze.setRoomNumber(nextRoom);
									}
									else {
										maze.handler.getGame().writeHintsToFile();
										maze.handler.getGame().setHints(0);
										maze.reset();
										if(maze.getLevel() != 10) {
											handler.getGame().unlockLevel(maze.getLevel());
										}
										handler.getGame().levelSelect.setUnlockedLevels();
										State.setState(handler.getGame().levelSelect);
										handler.getGame().levelSelect.setUIManager();
									}
								}
							}
						});
						uiManager.addObject(doorButtons[2]);
						break;
					case 3:
						doorButtons[3] = new UIImageButton(900 / 2 - 25, 400 - 60, 0, 0, Assets.doors[3], new ClickListener() {
							@Override
							public void onClick() {
//								System.out.println(doorDestinations[3]);
//								maze.setRoomNumber(doorDestinations[3]);
								if(maze.checkVisited(doorDestinations[3])/* || maze.checkVisited(doorDestinations[3])*/) {
									nextRoom = doorDestinations[3];
									maze.setRoomNumber(nextRoom);
								}
								else {
									if(doorDestinations[3] != 99) {
										maze.toggleDisplayQuestion();
										nextRoom = doorDestinations[3];
//										disappearDoors();
										maze.setRoomNumber(nextRoom);
									}
									else {
										maze.handler.getGame().writeHintsToFile();
										maze.handler.getGame().setHints(0);
										maze.reset();
										if(maze.getLevel() != 10) {
											handler.getGame().unlockLevel(maze.getLevel());
										}
										handler.getGame().levelSelect.setUnlockedLevels();
										State.setState(handler.getGame().levelSelect);
										handler.getGame().levelSelect.setUIManager();
									}
								}
							}
						});
						uiManager.addObject(doorButtons[3]);
						break;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(handler.getGame().getBgMusicPlayer().status.equals("play")) {
			onMuteIcon();
		}
		else {
			onUnmuteIcon();
		}
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 400, 900, 200);
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(rh);
		
		Text.drawString(g, getMins() + ":" + getSecs(), 20, 540, false, Color.BLACK, Assets.sevensegment);
		Text.drawString(g, "HINTS: " + handler.getGame().getHints(), 20, 580, false, Color.BLACK, Assets.garamonditalic);
		
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
			}
		}
	}
	
	public void appearDoors() {
		for(int i = 0; i < 4; i++) {
			if(doorButtons[i] != null) {
				switch(i) {
					case 0:
						doorButtons[i].setWidth(120);
						doorButtons[i].setHeight(242);
						break;
					case 1:
						doorButtons[i].setWidth(120);
						doorButtons[i].setHeight(198);
						break;
					case 2:
						doorButtons[i].setWidth(120);
						doorButtons[i].setHeight(243);
						break;
					case 3:
						doorButtons[i].setWidth(50);
						doorButtons[i].setHeight(50);
						break;
				}
				doorButtons[i].updateBounds();
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
			
//			proceed.setWidth(32 * 3);
//			proceed.setHeight(32 * 2);
//			proceed.updateBounds();
		}
	}
	
	public void disappearChoicesReset() {
		for(int i = 0; i < MazeLevelAttributes.numChoices[maze.getLevel() - 1][roomNo]; i++) {
			choiceButtons[i].setWidth(0);
			choiceButtons[i].setHeight(0);
			choiceButtons[i].updateBounds();
			
			proceed.setWidth(0);
			proceed.setHeight(0);
			proceed.updateBounds();
		}
	}
	
	public void reset() {
		maze.toggleDisplayQuestion();
//		nextRoom = -1;
		appearDoors();
		disappearChoicesReset();
		answered = false;
	}
	
	public String getMins() {
		int mins = (int)(maze.getTimer().getSecondsPassed() / 60);
		
		if(mins < 10) {
			return "0" + mins;
		}
		
		return "" + mins;
	}
	
	public String getSecs() {
		int secs = (int)(maze.getTimer().getSecondsPassed() % 60);
		
		if(secs < 10) {
			return "0" + secs;
		}
		
		return "" + secs;
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
	
	public void appearViewQuestion() {
		viewQuestion.setWidth(96);
		viewQuestion.setHeight(32);
		viewQuestion.updateBounds();
	}
}
