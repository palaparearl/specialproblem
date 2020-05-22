package specialproblem;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Display {

	private MyFrame frame;
	private Canvas canvas;
//	private SecondCanvas canvas;
	private JPanel panel;
	
	private String title;
	private int width, height;
	
	private boolean executedDoneLoading;
	
	// child frames and textareas
	private JFrame[] childFrames;
	private JTextArea[] childAreas;
	private JScrollPane[] scrollPanes;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		executedDoneLoading = false;
		
		//child frames and textareas
		childFrames = new JFrame[4];
		childAreas = new JTextArea[4];
		scrollPanes = new JScrollPane[4];
		
		for(int i = 0; i < 4; i++) {
			childFrames[i] = new JFrame();
			
			childAreas[i] = new JTextArea("#!/bin/bash");
			childAreas[i].setBounds(5, 0, 490, 540);
			childAreas[i].setBackground(Color.DARK_GRAY);
			childAreas[i].setForeground(Color.WHITE);
			childAreas[i].setFont(FontLoader.loadFont("/fonts/Monospace.ttf", 20));
			childAreas[i].setTabSize(3);
			childAreas[i].setCaretColor(Color.WHITE);
			
			scrollPanes[i] = new JScrollPane(childAreas[i]);
			scrollPanes[i].setBounds(0, 0, 500, 540);
			scrollPanes[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanes[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			childFrames[i].getContentPane().add(scrollPanes[i]);
			childFrames[i].getContentPane().setBackground(Color.DARK_GRAY);
			childFrames[i].setUndecorated(true);
			childFrames[i].setAlwaysOnTop(true);
			childFrames[i].setSize(500, 540);
			childFrames[i].setLayout(null);
		}
		
		createDisplay();
		
	}
	
	public void createDisplay() {
		frame = new MyFrame(title, childFrames);
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
	
	public MyFrame getFrame() {
		return frame;
	}
	
	public void doneLoading() {
		frame.remove(panel);
		executedDoneLoading = true;
	}
	
	public boolean getExecutedDoneLoading() {
		return executedDoneLoading;
	}
	
	public void setFrameVisible(int i) {
		childFrames[i].setVisible(true);
		childFrames[i].setLocation(frame.getX() + 20, frame.getY() + 57);
	}
	
	public void setFrameInvisible(int i) {
		childFrames[i].setVisible(false);
//		childFrames[i].setLocation(frame.getX() + 20, frame.getY() + 57);
	}
	
	public JTextArea getTextArea(int i) {
		return childAreas[i];
	}
	
	public JFrame getChildFrame(int i) {
		return childFrames[i];
	}
}
