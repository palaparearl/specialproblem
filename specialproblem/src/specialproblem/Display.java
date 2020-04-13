package specialproblem;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display {

	private JFrame frame;
	private Canvas canvas;
//	private SecondCanvas canvas;
	private JPanel panel;
	
	private String title;
	private int width, height;
	
	private boolean executedDoneLoading;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		executedDoneLoading = false;
		
		createDisplay();
	}
	
	public void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
//		canvas = new SecondCanvas(width, height);
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setSize(new Dimension(900, 600));
		panel.setBackground(Color.BLACK);
		
		ImageIcon imageIcon = new ImageIcon(Display.class.getResource("/textures/loadgif.gif"));
		JLabel load = new JLabel(imageIcon);
		
		ImageIcon imageIcon2 = new ImageIcon(Display.class.getResource("/textures/loadtext.png"));
		JLabel loadText = new JLabel(imageIcon2);
		
		panel.add(load);
		panel.add(loadText);
		
		frame.add(panel);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void doneLoading() {
		frame.remove(panel);
		executedDoneLoading = true;
	}
	
	public boolean getExecutedDoneLoading() {
		return executedDoneLoading;
	}
}
