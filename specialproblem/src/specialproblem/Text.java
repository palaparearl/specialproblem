package specialproblem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {

	public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		int x = xPos;
		int y = yPos;
		if(center) {
			FontMetrics fm = g.getFontMetrics(font);
			x = xPos - fm.stringWidth(text) / 2;
			y = (yPos - fm.getHeight() / 2) + fm.getAscent();
		}
		g.drawString(text, x, y);
	}
	
	public static void drawStringMultiLine(Graphics g, String text, int lineWidth, int x, int y, Color c, Font font) {
		g.setColor(c);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();
	    if(m.stringWidth(text) < lineWidth) {
	        g.drawString(text, x, y);
	    } else {
	        String[] words = text.split(" ");
	        String currentLine = words[0];
	        for(int i = 1; i < words.length; i++) {
	            if(m.stringWidth(currentLine+words[i]) < lineWidth) {
	                currentLine += " "+words[i];
	            } else {
	                g.drawString(currentLine, x, y);
	                y += m.getHeight();
	                currentLine = words[i];
	            }
	        }
	        if(currentLine.trim().length() > 0) {
	            g.drawString(currentLine, x, y);
	        }
	    }
	}
	
}
