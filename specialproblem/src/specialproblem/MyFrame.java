package specialproblem;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class MyFrame extends JFrame implements ComponentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame[] frames;
	
	public MyFrame(String title, JFrame[] frames) {
		super(title);
		
		this.frames = frames;
		this.addComponentListener(this);
	}
	
	@Override
    public void componentHidden(ComponentEvent arg0) {}

    @Override
    public void componentMoved(ComponentEvent arg0) {
    	int x = this.getX() + 20;
    	int y = this.getY() + 57;
    	
//    	.setDialogLocation(x, y);
    	for(int i = 0; i < 4; i++) {
    		if(frames[i].isVisible()) {
    			frames[i].setLocation(x, y);
    		}
    	}
    
    }

    @Override
    public void componentResized(ComponentEvent arg0) {}

    @Override
    public void componentShown(ComponentEvent arg0) {}

}
