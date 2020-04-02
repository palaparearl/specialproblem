package specialproblem;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

	public static Font loadFont(String path, float size) {
		try {
//			Font f = Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path));
//	        return f.deriveFont(size);
//			return Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(path))).deriveFont(Font.PLAIN, size);
			
			InputStream is = null;
			is = FontLoader.class.getResourceAsStream(path);
			
			return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
