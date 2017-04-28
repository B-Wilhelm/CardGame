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
	private int SLOT_WIDTH = 0, SLOT_HEIGHT = 0, fps, frameCount, playerTurn;
	private final int alpha = 75;
	private final String BOOST = "Boost", MAIN = "Hero", DECK = "Deck", UNUSED = "Unused";
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private long lastFrameTime = 0, updateLength = 0;
	
	public Game() {
		coord = new ArrayList<Panel>();
        setFrame();
        fillCoord();
		
        playerTurn = 0;
        frameCount = 0;
        lastFrameTime = 0;
        frameCount = 0;
		long now, last = System.nanoTime();
		double delta = 0;
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000/TARGET_FPS;
		fps = TARGET_FPS;
		
		while(running) {
		    now = System.nanoTime();
		    updateLength = now-last;
		    last = now;
		    delta = updateLength / ((double)OPTIMAL_TIME);
		    
		    fpsCount();
		    update(delta);
	    	draw();
	    	
		    try {
				Thread.sleep((last-System.nanoTime() + OPTIMAL_TIME) / 1000000 );
			}
		    catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void update(double delta) {
		if(playerTurn == 1) {
			
		}
		else {
			
		}
	}
	private void draw() {
		draw.repaint();
	}
	private void fpsCount() {
		lastFrameTime += updateLength;
	    frameCount++;
	    
	    if(lastFrameTime >= (1000000000)) {
	    	fps = frameCount;
	    	lastFrameTime = 0;
	    	frameCount = 0;
	    }
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
			String playerString = "Player " + (playerTurn+1) + "'s Turn";
			String otherPlayerString = "Player " + (Math.abs(playerTurn-1)+1) + " is Waiting";
			
			g.clearRect(0, 0, getWidth(), getHeight());
			
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
			g.drawString(fps+"", 10, draw.getHeight()-10);
			g.drawString(playerString, draw.getWidth()/2-m.stringWidth(playerString)/2, coord.get(3).getY()+SLOT_HEIGHT);
			g.drawString(otherPlayerString, draw.getWidth()/2-m.stringWidth(otherPlayerString)/2, coord.get(5).getY());
			g.drawString(MAIN, coord.get(1).getX()+SLOT_WIDTH/2-m.stringWidth(MAIN)/2, coord.get(1).getY()+SLOT_HEIGHT+m.getHeight());
			g.drawString(BOOST, coord.get(0).getX()+SLOT_WIDTH/2-m.stringWidth(BOOST)/2, coord.get(0).getY()+SLOT_HEIGHT+m.getHeight());
			g.drawString(BOOST, coord.get(2).getX()+SLOT_WIDTH/2-m.stringWidth(BOOST)/2, coord.get(2).getY()+SLOT_HEIGHT+m.getHeight());
			g.drawString(DECK, coord.get(4).getX()+SLOT_WIDTH-m.stringWidth(DECK), coord.get(4).getY()-10);
			g.drawString(DECK, coord.get(5).getX(), coord.get(5).getY()-10);
			g.drawString(UNUSED, coord.get(3).getX(), coord.get(3).getY()-10);
			g.drawString(UNUSED, coord.get(6).getX()+SLOT_WIDTH-m.stringWidth(UNUSED), coord.get(6).getY()-10);
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
		public void setX(int x) { this.x = x; }
		public void setY(int y) { this.y = y; }
	}
	
	class Input extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_ESCAPE)
            {
            	running = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {
        }
    }
}