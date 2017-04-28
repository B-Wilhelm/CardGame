package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	private boolean running = true;
	private JFrame frame;
	private DrawPanel draw;
	private ArrayList<Panel> coord;
	private int SLOT_WIDTH = 0;
	private int SLOT_HEIGHT = 0;
	private final int alpha = 75;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
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
		int height = draw.getHeight()/2-SLOT_HEIGHT/2;
		
		coord.add(new Panel(draw.getWidth()*5/14-SLOT_WIDTH/2, height));
		coord.add(new Panel(draw.getWidth()*7/14-SLOT_WIDTH/2, height));
		coord.add(new Panel(draw.getWidth()*9/14-SLOT_WIDTH/2, height));
		
		coord.add(new Panel(draw.getWidth()*2/14-SLOT_WIDTH/2, height+SLOT_HEIGHT));
		coord.add(new Panel(draw.getWidth()*12/14-SLOT_WIDTH/2, height+SLOT_HEIGHT));
		
		coord.add(new Panel(draw.getWidth()*2/14-SLOT_WIDTH/2, height-SLOT_HEIGHT));
		coord.add(new Panel(draw.getWidth()*12/14-SLOT_WIDTH/2, height-SLOT_HEIGHT));
	}
	private void setFrame() {
		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        draw = new DrawPanel();
        frame.getContentPane().add(draw);
        frame.setSize((int)screenSize.getHeight()/9*16, (int)screenSize.getHeight());
//        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setFocusable(true);
        
        SLOT_WIDTH = frame.getWidth()/10;
        SLOT_HEIGHT = frame.getHeight()/4;
	}
	
	class DrawPanel extends JPanel{
		private static final long serialVersionUID = 2617928535228650371L;

		public void paintComponent(Graphics g) {
			g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 32));
			FontMetrics m = g.getFontMetrics(g.getFont());
			Color blue = new Color(0, 0, 255, alpha);
			Color red = new Color(255, 0, 0, alpha);
			Color green = new Color(0, 255, 0, alpha);
			Color violet = new Color(255, 0, 255, alpha);
			
			g.setColor(red);
			g.fillRect(coord.get(1).getX(),coord.get(1).getY(),SLOT_WIDTH,SLOT_HEIGHT);

			g.setColor(blue);
			g.fillRect(coord.get(0).getX(),coord.get(0).getY(),SLOT_WIDTH,SLOT_HEIGHT);
			g.fillRect(coord.get(2).getX(),coord.get(2).getY(),SLOT_WIDTH,SLOT_HEIGHT);
			
			g.setColor(Color.gray);
			g.drawRect(coord.get(3).getX(),coord.get(3).getY(),SLOT_WIDTH,SLOT_HEIGHT);
			g.drawRect(coord.get(4).getX(),coord.get(4).getY(),SLOT_WIDTH,SLOT_HEIGHT);
			g.drawRect(coord.get(5).getX(),coord.get(5).getY(),SLOT_WIDTH,SLOT_HEIGHT);
			g.drawRect(coord.get(6).getX(),coord.get(6).getY(),SLOT_WIDTH,SLOT_HEIGHT);
			g.setColor(green);
			g.fillRect(coord.get(3).getX()+1,coord.get(3).getY()+1,SLOT_WIDTH-1,SLOT_HEIGHT-1);
			g.fillRect(coord.get(6).getX()+1,coord.get(6).getY()+1,SLOT_WIDTH-1,SLOT_HEIGHT-1);
			g.setColor(violet);
			g.fillRect(coord.get(4).getX()+1,coord.get(4).getY()+1,SLOT_WIDTH-1,SLOT_HEIGHT-1);
			g.fillRect(coord.get(5).getX()+1,coord.get(5).getY()+1,SLOT_WIDTH-1,SLOT_HEIGHT-1);
			
			
			g.setColor(Color.black);
			g.drawString("Primary", coord.get(1).getX()+SLOT_WIDTH/2-m.stringWidth("Primary")/2, coord.get(1).getY()+SLOT_HEIGHT+m.getHeight());
			g.drawString("Boost", coord.get(0).getX()+SLOT_WIDTH/2-m.stringWidth("Boost")/2, coord.get(0).getY()+SLOT_HEIGHT+m.getHeight());
			g.drawString("Boost", coord.get(2).getX()+SLOT_WIDTH/2-m.stringWidth("Boost")/2, coord.get(2).getY()+SLOT_HEIGHT+m.getHeight());
			g.drawString("Discard", coord.get(3).getX(), coord.get(3).getY()-10);
			g.drawString("Deck", coord.get(4).getX()+SLOT_WIDTH-m.stringWidth("Deck"), coord.get(4).getY()-10);
			g.drawString("Deck", coord.get(5).getX(), coord.get(5).getY()-10);
			g.drawString("Discard", coord.get(6).getX()+SLOT_WIDTH-m.stringWidth("Discard"), coord.get(6).getY()-10);
		}
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
	
	class Input extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_ESCAPE)
            {
            	System.out.println("boi");
               frame.dispose();
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {
        }
    }
}