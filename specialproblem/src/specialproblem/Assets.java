package specialproblem;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32, width2 = 25, height2 = 25;
	
	public static Font font28, font50, arial, arial40, courier, trixie, garamonditalic, garamondbold, sevensegment;
	
	public static BufferedImage dirt, grass, stone, tree;
	public static BufferedImage wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	public static BufferedImage[] play_btn;
	public static BufferedImage[] tutorial_btn;
	public static BufferedImage[] credits_btn;
	public static BufferedImage[] quit_btn;
	public static BufferedImage[] submit_btn;
	public static BufferedImage[] clear_btn;
	public static BufferedImage[] shuffle_btn;
//	public static BufferedImage[] nxt_lvl;
	public static BufferedImage[] proceed;
	public static BufferedImage[] menu;
	public static BufferedImage[] levelsLocked;
	public static BufferedImage[][] levels;
	public static BufferedImage[][] doors;
	
	public static BufferedImage[][] alphabet;
	public static BufferedImage letterBox;
	public static BufferedImage cw_wallpaper;
	public static BufferedImage menuBackground;
	public static BufferedImage scroll;
	public static BufferedImage mazeBg;
	
	public static BufferedImage check;
	public static BufferedImage cross;
	
	public static BufferedImage mazeMap;
	public static BufferedImage marker;
	
	public static BufferedImage[] mapRooms;
	
	public static BufferedImage[] first;
	public static BufferedImage[] prev;
	public static BufferedImage[] next;
	public static BufferedImage[] last;
	
	public static BufferedImage[] mute;
	public static BufferedImage[] unmute;
	
	public static BufferedImage[][] slides;
	
	public static BufferedImage[][][][] choices;
	
	public static BufferedImage hintWordsWindow;
	public static BufferedImage[] closeHintWordsWindow;
	
	public static BufferedImage[] restartLevel;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
		SpriteSheet alphabetSheet = new SpriteSheet(ImageLoader.loadImage("/textures/alphabetSheet.png"));
		SpriteSheet levelsLockedSheet = new SpriteSheet(ImageLoader.loadImage("/textures/levelslocked.png"));
		SpriteSheet levelsSelected = new SpriteSheet(ImageLoader.loadImage("/textures/levelsselected.png"));
		SpriteSheet levelsUnselected = new SpriteSheet(ImageLoader.loadImage("/textures/levelsunselected.png"));
				
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		hintWordsWindow = ImageLoader.loadImage("/textures/formedWords.png");
		closeHintWordsWindow = new BufferedImage[2];
		closeHintWordsWindow[0] = menuSheet.crop(96 * 4, 64 * 2 + 32, 48, 32);
		closeHintWordsWindow[1] = menuSheet.crop(96 * 4 + 48, 64 * 2 + 32, 48, 32);
		
		restartLevel = new BufferedImage[2];
		restartLevel[0] = menuSheet.crop(96 * 4, 64 * 2, 48, 32);
		restartLevel[1] = menuSheet.crop(96 * 4 + 48, 64 * 2, 48, 32);
		
		first = new BufferedImage[2];
		first[0] = menuSheet.crop(0, 32 * 8, 96, 32);
		first[1] = menuSheet.crop(0, 32 * 9, 96, 32);
		
		prev = new BufferedImage[2];
		prev[0] = menuSheet.crop(96 * 4, 32 * 6, 96, 32);
		prev[1] = menuSheet.crop(96 * 4, 32 * 7, 96, 32);
		
		next = new BufferedImage[2];
		next[0] = menuSheet.crop(96 * 3, 32 * 6, 96, 32);
		next[1] = menuSheet.crop(96 * 3, 32 * 7, 96, 32);
		
		last = new BufferedImage[2];
		last[0] = menuSheet.crop(96, 32 * 8, 96, 32);
		last[1] = menuSheet.crop(96, 32 * 9, 96, 32);
		
		mute = new BufferedImage[2];
		mute[0] = menuSheet.crop(96 * 2, 32 * 8, 48, 32);
		mute[1] = menuSheet.crop(96 * 2 + 48, 32 * 8, 48, 32);
		
		unmute = new BufferedImage[2];
		unmute[0] = menuSheet.crop(96 * 2, 32 * 9, 48, 32);
		unmute[1] = menuSheet.crop(96 * 2 + 48, 32 * 9, 48, 32);
		
		mazeMap = ImageLoader.loadImage("/textures/map.png");
		marker = ImageLoader.loadImage("/textures/marker.png");
		
		check = ImageLoader.loadImage("/textures/check.png");
		cross = ImageLoader.loadImage("/textures/cross.png");
		
		wood = sheet.crop(width, height, width, height);
		
		scroll = ImageLoader.loadImage("/textures/scroll.png");
		mazeBg = ImageLoader.loadImage("/textures/mazebg.png");
		
		mapRooms = new BufferedImage[14];
		for(int i = 0; i < 14; i++) {
			mapRooms[i] = ImageLoader.loadImage("/textures/rooms/room" + i + ".png");
		}
		
		doors = new BufferedImage[4][2];
		doors[0][0] = ImageLoader.loadImage("/textures/door0.png");
		doors[0][1] = ImageLoader.loadImage("/textures/lightdoor0.png");
		doors[1][0] = ImageLoader.loadImage("/textures/door1.png");
		doors[1][1] = ImageLoader.loadImage("/textures/lightdoor1.png");
		doors[2][0] = ImageLoader.loadImage("/textures/door2.png");
		doors[2][1] = ImageLoader.loadImage("/textures/lightdoor2.png");
		doors[3][0] = ImageLoader.loadImage("/textures/back.png");
		doors[3][1] = ImageLoader.loadImage("/textures/lightback.png");
		
		levelsLocked = new BufferedImage[10];
		levelsLocked[0] = levelsLockedSheet.crop(0, 0, 430, 85);
		levelsLocked[1] = levelsLockedSheet.crop(0, 85, 430, 85);
		levelsLocked[2] = levelsLockedSheet.crop(0, 85 * 2, 430, 85);
		levelsLocked[3] = levelsLockedSheet.crop(0, 85 * 3, 430, 85);
		levelsLocked[4] = levelsLockedSheet.crop(0, 85 * 4, 430, 85);
		levelsLocked[5] = levelsLockedSheet.crop(430, 0, 430, 85);
		levelsLocked[6] = levelsLockedSheet.crop(430, 85, 430, 85);
		levelsLocked[7] = levelsLockedSheet.crop(430, 85 * 2, 430, 85);
		levelsLocked[8] = levelsLockedSheet.crop(430, 85 * 3, 430, 85);
		levelsLocked[9] = levelsLockedSheet.crop(430, 85 * 4, 430, 85);
		
		levels = new BufferedImage[10][2];
		
		levels[0][0] = levelsUnselected.crop(0, 0, 430, 85);
		levels[1][0] = levelsUnselected.crop(0, 85, 430, 85);
		levels[2][0] = levelsUnselected.crop(0, 85 * 2, 430, 85);
		levels[3][0] = levelsUnselected.crop(0, 85 * 3, 430, 85);
		levels[4][0] = levelsUnselected.crop(0, 85 * 4, 430, 85);
		levels[5][0] = levelsUnselected.crop(430, 0, 430, 85);
		levels[6][0] = levelsUnselected.crop(430, 85, 430, 85);
		levels[7][0] = levelsUnselected.crop(430, 85 * 2, 430, 85);
		levels[8][0] = levelsUnselected.crop(430, 85 * 3, 430, 85);
		levels[9][0] = levelsUnselected.crop(430, 85 * 4, 430, 85);
		
		levels[0][1] = levelsSelected.crop(0, 0, 430, 85);
		levels[1][1] = levelsSelected.crop(0, 85, 430, 85);
		levels[2][1] = levelsSelected.crop(0, 85 * 2, 430, 85);
		levels[3][1] = levelsSelected.crop(0, 85 * 3, 430, 85);
		levels[4][1] = levelsSelected.crop(0, 85 * 4, 430, 85);
		levels[5][1] = levelsSelected.crop(430, 0, 430, 85);
		levels[6][1] = levelsSelected.crop(430, 85, 430, 85);
		levels[7][1] = levelsSelected.crop(430, 85 * 2, 430, 85);
		levels[8][1] = levelsSelected.crop(430, 85 * 3, 430, 85);
		levels[9][1] = levelsSelected.crop(430, 85 * 4, 430, 85);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height);
		
		play_btn = new BufferedImage[2];
		play_btn[0] = menuSheet.crop(128 * 2, 0, 128, 64);
		play_btn[1] = menuSheet.crop(128 * 3, 0, 128, 64);
		
		tutorial_btn = new BufferedImage[2];
		tutorial_btn[0] = menuSheet.crop(0, 0, 128, 64);
		tutorial_btn[1] = menuSheet.crop(128, 0, 128, 64);
		
		credits_btn = new BufferedImage[2];
		credits_btn[0] = menuSheet.crop(0, 64, 128, 64);
		credits_btn[1] = menuSheet.crop(128, 64, 128, 64);
		
		quit_btn = new BufferedImage[2];
		quit_btn[0] = menuSheet.crop(128 * 2, 64, 128, 64);
		quit_btn[1] = menuSheet.crop(128 * 3, 64, 128, 64);
		
		submit_btn = new BufferedImage[2];
		submit_btn[0] = menuSheet.crop(96 * 2, 64 * 2, 96, 64);
		submit_btn[1] = menuSheet.crop(96 * 3, 64 * 2, 96, 64);
		
		clear_btn = new BufferedImage[2];
		clear_btn[0] = menuSheet.crop(0, 64 * 3, 96, 64);
		clear_btn[1] = menuSheet.crop(96, 64 * 3, 96, 64);
		
		shuffle_btn = new BufferedImage[2];
		shuffle_btn[0] = menuSheet.crop(96 * 3, 64 * 4, 96, 64);
		shuffle_btn[1] = menuSheet.crop(96 * 4, 64 * 4, 96, 64);
		
		menu = new BufferedImage[2];
		menu[0] = menuSheet.crop(96 * 2, 64 * 3, 96, 32);
		menu[1] = menuSheet.crop(96 * 2, 64 * 3 + 32, 96, 32);
		
//		nxt_lvl = new BufferedImage[2];
//		nxt_lvl[0] = menuSheet.crop(0, height * 6, width * 3, height * 2);
//		nxt_lvl[1] = menuSheet.crop(width * 3, height * 6, width * 3, height * 2);
		
		proceed = new BufferedImage[2];
		proceed[0] = menuSheet.crop(0, 64 * 2, 96, 64);
		proceed[1] = menuSheet.crop(96, 64 * 2, 96, 64);
		
		alphabet = new BufferedImage[26][2];
		alphabet[0][0] = alphabetSheet.crop(0, 0, width2 * 2, height2 * 2);
		alphabet[0][1] = alphabetSheet.crop(width2 * 2, 0, width2 * 2, height2 * 2);
		alphabet[1][0] = alphabetSheet.crop(width2 * 2 * 2, 0, width2 * 2, height2 * 2);
		alphabet[1][1] = alphabetSheet.crop(width2 * 2 * 3, 0, width2 * 2, height2 * 2);
		alphabet[2][0] = alphabetSheet.crop(width2 * 2 * 4, 0, width2 * 2, height2 * 2);
		alphabet[2][1] = alphabetSheet.crop(width2 * 2 * 5, 0, width2 * 2, height2 * 2);
		alphabet[3][0] = alphabetSheet.crop(width2 * 2 * 6, 0, width2 * 2, height2 * 2);
		alphabet[3][1] = alphabetSheet.crop(width2 * 2 * 7, 0, width2 * 2, height2 * 2);
		alphabet[4][0] = alphabetSheet.crop(0, height2 * 2, width2 * 2, height2 * 2);
		alphabet[4][1] = alphabetSheet.crop(width2 * 2, height2 * 2, width2 * 2, height2 * 2);
		alphabet[5][0] = alphabetSheet.crop(width2 * 2 * 2, height2 * 2, width2 * 2, height2 * 2);
		alphabet[5][1] = alphabetSheet.crop(width2 * 2 * 3, height2 * 2, width2 * 2, height2 * 2);
		alphabet[6][0] = alphabetSheet.crop(width2 * 2 * 4, height2 * 2, width2 * 2, height2 * 2);
		alphabet[6][1] = alphabetSheet.crop(width2 * 2 * 5, height2 * 2, width2 * 2, height2 * 2);
		alphabet[7][0] = alphabetSheet.crop(width2 * 2 * 6, height2 * 2, width2 * 2, height2 * 2);
		alphabet[7][1] = alphabetSheet.crop(width2 * 2 * 7, height2 * 2, width2 * 2, height2 * 2);
		alphabet[8][0] = alphabetSheet.crop(0, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[8][1] = alphabetSheet.crop(width2 * 2, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[9][0] = alphabetSheet.crop(width2 * 2 * 2, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[9][1] = alphabetSheet.crop(width2 * 2 * 3, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[10][0] = alphabetSheet.crop(width2 * 2 * 4, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[10][1] = alphabetSheet.crop(width2 * 2 * 5, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[11][0] = alphabetSheet.crop(width2 * 2 * 6, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[11][1] = alphabetSheet.crop(width2 * 2 * 7, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[12][0] = alphabetSheet.crop(0, height2 * 2 * 3, width2 * 2, height2 * 2);
		alphabet[12][1] = alphabetSheet.crop(width2 * 2, height2 * 2 * 3, width2 * 2, height2 * 2);
		alphabet[13][0] = alphabetSheet.crop(width2 * 2 * 2, height2 * 3 * 2, width2 * 2, height2 * 2);
		alphabet[13][1] = alphabetSheet.crop(width2 * 2 * 3, height2 * 3 * 2, width2 * 2, height2 * 2);
		alphabet[14][0] = alphabetSheet.crop(width2 * 2 * 4, height2 * 3 * 2, width2 * 2, height2 * 2);
		alphabet[14][1] = alphabetSheet.crop(width2 * 2 * 5, height2 * 3 * 2, width2 * 2, height2 * 2);
		alphabet[15][0] = alphabetSheet.crop(width2 * 2 * 6, height2 * 3 * 2, width2 * 2, height2 * 2);
		alphabet[15][1] = alphabetSheet.crop(width2 * 2 * 7, height2 * 3 * 2, width2 * 2, height2 * 2);
		alphabet[16][0] = alphabetSheet.crop(0, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[16][1] = alphabetSheet.crop(width2 * 2, height2 * 2 * 2, width2 * 2, height2 * 2);
		alphabet[17][0] = alphabetSheet.crop(width2 * 2 * 2, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[17][1] = alphabetSheet.crop(width2 * 2 * 3, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[18][0] = alphabetSheet.crop(width2 * 2 * 4, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[18][1] = alphabetSheet.crop(width2 * 2 * 5, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[19][0] = alphabetSheet.crop(width2 * 2 * 6, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[19][1] = alphabetSheet.crop(width2 * 2 * 7, height2 * 2 * 4, width2 * 2, height2 * 2);
		alphabet[20][0] = alphabetSheet.crop(0, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[20][1] = alphabetSheet.crop(width2 * 2, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[21][0] = alphabetSheet.crop(width2 * 2 * 2, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[21][1] = alphabetSheet.crop(width2 * 2 * 3, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[22][0] = alphabetSheet.crop(width2 * 2 * 4, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[22][1] = alphabetSheet.crop(width2 * 2 * 5, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[23][0] = alphabetSheet.crop(width2 * 2 * 6, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[23][1] = alphabetSheet.crop(width2 * 2 * 7, height2 * 2 * 5, width2 * 2, height2 * 2);
		alphabet[24][0] = alphabetSheet.crop(0, height2 * 2 * 6, width2 * 2, height2 * 2);
		alphabet[24][1] = alphabetSheet.crop(width2 * 2, height2 * 2 * 6, width2 * 2, height2 * 2);
		alphabet[25][0] = alphabetSheet.crop(width2 * 2 * 2, height2 * 2 * 6, width2 * 2, height2 * 2);
		alphabet[25][1] = alphabetSheet.crop(width2 * 2 * 3, height2 * 2 * 6, width2 * 2, height2 * 2);
		
		letterBox = ImageLoader.loadImage("/textures/letterBox.png");
	
		cw_wallpaper = ImageLoader.loadImage("/textures/cw_wallpaper.jpg");
		menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
		
		choices = new BufferedImage[10][9][3][2];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 9; j++) {
				for(int k = 0; k < 3; k++) {
					for(int l = 0; l < 2; l++) {
						try {
							choices[i][j][k][l] = ImageLoader.loadImage("/textures/choices/" + i + j + k + l + ".png");
						}
						catch(Exception e) {
							
						}
					}
				}
			}
		}
		
		// to be changed after all slides have been created
		slides = new BufferedImage[10][];
		slides[0] = new BufferedImage[6];
		slides[1] = new BufferedImage[4];
		slides[2] = new BufferedImage[5];
		slides[3] = new BufferedImage[5];
		slides[4] = new BufferedImage[4];
		slides[5] = new BufferedImage[5];
		slides[6] = new BufferedImage[4];
		slides[7] = new BufferedImage[4];
		slides[8] = new BufferedImage[3];
		slides[9] = new BufferedImage[5];
		
//		for(int i = 0; i < 6; i++) {
//			slides[0][i] = ImageLoader.loadImage("/textures/slides/0" + i + ".png");
//		}
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < slides[i].length; j++) {
				slides[i][j] = ImageLoader.loadImage("/textures/slides/" + i + j + ".png");
			}
		}
		
		trixie = FontLoader.loadFont("/fonts/Trixie-Text.otf", 30);
		font28 = FontLoader.loadFont("/fonts/slkscr.ttf", 28);
		font50 = FontLoader.loadFont("/fonts/slkscr.ttf", 50);
		arial = FontLoader.loadFont("/fonts/Arial.ttf", 20);
		arial40 = FontLoader.loadFont("/fonts/Arial.ttf", 40);
		courier = FontLoader.loadFont("/fonts/COURIER.ttf", 60);
		garamonditalic = FontLoader.loadFont("/fonts/garamonditalic.otf", 23);
		garamondbold = FontLoader.loadFont("/fonts/garamondbold.otf", 40);
		sevensegment = FontLoader.loadFont("/fonts/sevensegment.ttf", 30);
	}
	
}
