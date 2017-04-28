package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	private boolean running = true;
	private JFrame frame;
	private DrawPanel draw;
	private ArrayList<Panel> coord;
	
	public Game() {
		coord = new ArrayList<Panel>();
        setFrame();
        fillCoord();
		
		long last = System.nanoTime();
		long delta = 0;
		while(running) {
		    long now = System.nanoTime();
		    delta += ((now-last)/16666666);
		    last = now;
		    if(delta >= 1) {
		    	draw();
		    	delta--;
		    }
		}
	}
	
	private void draw() {
		draw.repaint();
	}
	
	private void fillCoord() {
		final int SLOT_HEIGHT = 100;
		final int SLOT_WIDTH = 100;
		int height = frame.getHeight()/2-SLOT_HEIGHT;
		coord.add(new Panel(draw.getWidth()*2/7-SLOT_WIDTH, height));
		coord.add(new Panel(draw.getWidth()*4/7-SLOT_WIDTH, height));
		coord.add(new Panel(draw.getWidth()*6/7-SLOT_WIDTH, height));
	}
	
	private void setFrame() {
		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        draw = new DrawPanel();
        frame.getContentPane().add(draw);
        frame.setSize(800,600);
        frame.setVisible(true);
	}
	
	
	class Panel {
		private int x, y;
		
		public Panel(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() { return x; }
		public int getY() { return y; }
	}

	
	class DrawPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(coord.get(0).getX(),coord.get(0).getY(),100,120);
			g.fillRect(coord.get(1).getX(),coord.get(1).getY(),100,120);
			g.fillRect(coord.get(2).getX(),coord.get(2).getY(),100,120);
		}
	}
}