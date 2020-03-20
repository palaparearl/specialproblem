package specialproblem;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	
	public static Font font28, font50, arial, arial40, courier, lazyMorning;
	
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
	public static BufferedImage[] nxt_lvl;
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
	
	public static BufferedImage[][][][] choices;

	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font50 = FontLoader.loadFont("res/fonts/slkscr.ttf", 50);
		arial = FontLoader.loadFont("res/fonts/Arial.ttf", 20);
		arial40 = FontLoader.loadFont("res/fonts/Arial.ttf", 40);
		courier = FontLoader.loadFont("res/fonts/COURIER.ttf", 60);
		lazyMorning = FontLoader.loadFont("res/fonts/lazymorning.otf", 50);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
		SpriteSheet alphabetSheet = new SpriteSheet(ImageLoader.loadImage("/textures/alphabetSheet.png"));
		SpriteSheet levelsLockedSheet = new SpriteSheet(ImageLoader.loadImage("/textures/levelslocked.png"));
		SpriteSheet levelsSelected = new SpriteSheet(ImageLoader.loadImage("/textures/levelsselected.png"));
		SpriteSheet levelsUnselected = new SpriteSheet(ImageLoader.loadImage("/textures/levelsunselected.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		check = ImageLoader.loadImage("/textures/check.png");
		cross = ImageLoader.loadImage("/textures/cross.png");
		
		wood = sheet.crop(width, height, width, height);
		
		scroll = ImageLoader.loadImage("/textures/scroll.png");
		mazeBg = ImageLoader.loadImage("/textures/mazebg.png");
		
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
		play_btn[0] = menuSheet.crop(0, 0, width * 3, height * 2);
		play_btn[1] = menuSheet.crop(width * 3, 0, width * 3, height * 2);
		
		tutorial_btn = new BufferedImage[2];
		tutorial_btn[0] = menuSheet.crop(width * 12, 0, width * 3, height * 2);
		tutorial_btn[1] = menuSheet.crop(0, height * 2, width * 3, height * 2);
		
		credits_btn = new BufferedImage[2];
		credits_btn[0] = menuSheet.crop(width * 6, 0, width * 3, height * 2);
		credits_btn[1] = menuSheet.crop(width * 9, 0, width * 3, height * 2);
		
		quit_btn = new BufferedImage[2];
		quit_btn[0] = menuSheet.crop(width * 3, height * 2, width * 3, height * 2);
		quit_btn[1] = menuSheet.crop(width * 6, height * 2, width * 3, height * 2);
		
		submit_btn = new BufferedImage[2];
		submit_btn[0] = menuSheet.crop(0, height * 4, width * 3, height * 2);
		submit_btn[1] = menuSheet.crop(width * 3, height * 4, width * 3, height * 2);
		
		clear_btn = new BufferedImage[2];
		clear_btn[0] = menuSheet.crop(width * 6, height * 4, width * 3, height * 2);
		clear_btn[1] = menuSheet.crop(width * 9, height * 4, width * 3, height * 2);
		
		menu = new BufferedImage[2];
		menu[0] = menuSheet.crop(width * 12, height * 4, width * 3, height);
		menu[1] = menuSheet.crop(width * 12, height * 5, width * 3, height);
		
		nxt_lvl = new BufferedImage[2];
		nxt_lvl[0] = menuSheet.crop(0, height * 6, width * 3, height * 2);
		nxt_lvl[1] = menuSheet.crop(width * 3, height * 6, width * 3, height * 2);
		
		alphabet = new BufferedImage[26][2];
		alphabet[0][0] = alphabetSheet.crop(0, 0, width * 2, height * 2);
		alphabet[0][1] = alphabetSheet.crop(width * 2, 0, width * 2, height * 2);
		alphabet[1][0] = alphabetSheet.crop(width * 2 * 2, 0, width * 2, height * 2);
		alphabet[1][1] = alphabetSheet.crop(width * 2 * 3, 0, width * 2, height * 2);
		alphabet[2][0] = alphabetSheet.crop(width * 2 * 4, 0, width * 2, height * 2);
		alphabet[2][1] = alphabetSheet.crop(width * 2 * 5, 0, width * 2, height * 2);
		alphabet[3][0] = alphabetSheet.crop(width * 2 * 6, 0, width * 2, height * 2);
		alphabet[3][1] = alphabetSheet.crop(width * 2 * 7, 0, width * 2, height * 2);
		alphabet[4][0] = alphabetSheet.crop(0, height * 2, width * 2, height * 2);
		alphabet[4][1] = alphabetSheet.crop(width * 2, height * 2, width * 2, height * 2);
		alphabet[5][0] = alphabetSheet.crop(width * 2 * 2, height * 2, width * 2, height * 2);
		alphabet[5][1] = alphabetSheet.crop(width * 2 * 3, height * 2, width * 2, height * 2);
		alphabet[6][0] = alphabetSheet.crop(width * 2 * 4, height * 2, width * 2, height * 2);
		alphabet[6][1] = alphabetSheet.crop(width * 2 * 5, height * 2, width * 2, height * 2);
		alphabet[7][0] = alphabetSheet.crop(width * 2 * 6, height * 2, width * 2, height * 2);
		alphabet[7][1] = alphabetSheet.crop(width * 2 * 7, height * 2, width * 2, height * 2);
		alphabet[8][0] = alphabetSheet.crop(0, height * 2 * 2, width * 2, height * 2);
		alphabet[8][1] = alphabetSheet.crop(width * 2, height * 2 * 2, width * 2, height * 2);
		alphabet[9][0] = alphabetSheet.crop(width * 2 * 2, height * 2 * 2, width * 2, height * 2);
		alphabet[9][1] = alphabetSheet.crop(width * 2 * 3, height * 2 * 2, width * 2, height * 2);
		alphabet[10][0] = alphabetSheet.crop(width * 2 * 4, height * 2 * 2, width * 2, height * 2);
		alphabet[10][1] = alphabetSheet.crop(width * 2 * 5, height * 2 * 2, width * 2, height * 2);
		alphabet[11][0] = alphabetSheet.crop(width * 2 * 6, height * 2 * 2, width * 2, height * 2);
		alphabet[11][1] = alphabetSheet.crop(width * 2 * 7, height * 2 * 2, width * 2, height * 2);
		alphabet[12][0] = alphabetSheet.crop(0, height * 2 * 3, width * 2, height * 2);
		alphabet[12][1] = alphabetSheet.crop(width * 2, height * 2 * 3, width * 2, height * 2);
		alphabet[13][0] = alphabetSheet.crop(width * 2 * 2, height * 3 * 2, width * 2, height * 2);
		alphabet[13][1] = alphabetSheet.crop(width * 2 * 3, height * 3 * 2, width * 2, height * 2);
		alphabet[14][0] = alphabetSheet.crop(width * 2 * 4, height * 3 * 2, width * 2, height * 2);
		alphabet[14][1] = alphabetSheet.crop(width * 2 * 5, height * 3 * 2, width * 2, height * 2);
		alphabet[15][0] = alphabetSheet.crop(width * 2 * 6, height * 3 * 2, width * 2, height * 2);
		alphabet[15][1] = alphabetSheet.crop(width * 2 * 7, height * 3 * 2, width * 2, height * 2);
		alphabet[16][0] = alphabetSheet.crop(0, height * 2 * 4, width * 2, height * 2);
		alphabet[16][1] = alphabetSheet.crop(width * 2, height * 2 * 2, width * 2, height * 2);
		alphabet[17][0] = alphabetSheet.crop(width * 2 * 2, height * 2 * 4, width * 2, height * 2);
		alphabet[17][1] = alphabetSheet.crop(width * 2 * 3, height * 2 * 4, width * 2, height * 2);
		alphabet[18][0] = alphabetSheet.crop(width * 2 * 4, height * 2 * 4, width * 2, height * 2);
		alphabet[18][1] = alphabetSheet.crop(width * 2 * 5, height * 2 * 4, width * 2, height * 2);
		alphabet[19][0] = alphabetSheet.crop(width * 2 * 6, height * 2 * 4, width * 2, height * 2);
		alphabet[19][1] = alphabetSheet.crop(width * 2 * 7, height * 2 * 4, width * 2, height * 2);
		alphabet[20][0] = alphabetSheet.crop(0, height * 2 * 5, width * 2, height * 2);
		alphabet[20][1] = alphabetSheet.crop(width * 2, height * 2 * 5, width * 2, height * 2);
		alphabet[21][0] = alphabetSheet.crop(width * 2 * 2, height * 2 * 5, width * 2, height * 2);
		alphabet[21][1] = alphabetSheet.crop(width * 2 * 3, height * 2 * 5, width * 2, height * 2);
		alphabet[22][0] = alphabetSheet.crop(width * 2 * 4, height * 2 * 5, width * 2, height * 2);
		alphabet[22][1] = alphabetSheet.crop(width * 2 * 5, height * 2 * 5, width * 2, height * 2);
		alphabet[23][0] = alphabetSheet.crop(width * 2 * 6, height * 2 * 5, width * 2, height * 2);
		alphabet[23][1] = alphabetSheet.crop(width * 2 * 7, height * 2 * 5, width * 2, height * 2);
		alphabet[24][0] = alphabetSheet.crop(0, height * 2 * 6, width * 2, height * 2);
		alphabet[24][1] = alphabetSheet.crop(width * 2, height * 2 * 6, width * 2, height * 2);
		alphabet[25][0] = alphabetSheet.crop(width * 2 * 2, height * 2 * 6, width * 2, height * 2);
		alphabet[25][1] = alphabetSheet.crop(width * 2 * 3, height * 2 * 6, width * 2, height * 2);
		
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
	}
	
}
