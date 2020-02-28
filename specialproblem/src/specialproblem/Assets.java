package specialproblem;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	
	public static Font font28, font50;
	
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
	
	public static BufferedImage[][] alphabet;
	public static BufferedImage letterBox;
	public static BufferedImage cw_wallpaper;
	public static BufferedImage menuBackground;

	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font50 = FontLoader.loadFont("res/fonts/slkscr.ttf", 50);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
		SpriteSheet alphabetSheet = new SpriteSheet(ImageLoader.loadImage("/textures/alphabetSheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		wood = sheet.crop(width, height, width, height);
		
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
	}
	
}
