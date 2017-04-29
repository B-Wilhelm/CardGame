package com.wilhelm.portfolio;

/**
 * @author Brett Wilhelm
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	private final int alpha = 127, PLAY_SIZE = 3;
	private final String BOOST = "Boost", MAIN = "Hero", DECK = "Deck", UNUSED = "Unused";
	private boolean running = true, hasDrawn = false, hasPlayed = false;
	private JFrame frame;
	private DrawPanel draw;
	private ArrayList<Panel> coord;
	private int SLOT_WIDTH = 0, SLOT_HEIGHT = 0, fps, frameCount, playerTurn;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private long lastFrameTime = 0, updateLength = 0;
	private Field f = new Field();
	private ArrayList<Player> p;
	private ArrayList<Card> c;
	private ArrayList<Shape> shapes;
	private int ogDeckSize;
	
	public Game() {
		p = f.getPlayers();
		c = new ArrayList<Card>();
		coord = new ArrayList<Panel>();
		
		setFrame();
		SLOT_WIDTH = frame.getWidth()/10;
        SLOT_HEIGHT = frame.getHeight()/4;
		fillCoord();
		
        playerTurn = 0;
        frameCount = 0;
        lastFrameTime = 0;
        frameCount = 0;
        ogDeckSize = p.get(playerTurn).getDeck().size()-1;
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
		fillCards();
		
		
	}
	
	private void drawCard() {
		if(!hasDrawn) {
			p.get(playerTurn).drawCard();
		}
		hasDrawn = true;
	}
	private void endTurn() {
		playerTurn = Math.abs(playerTurn-1);
		hasDrawn = false;
		hasPlayed = false;
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
	private void fillCards() {
		int i;
		c = new ArrayList<Card>();
		
		for(i = 0; i < PLAY_SIZE; i++) {
			if(f.getPlay().get(i) != null) c.add(f.getPlay().get(i));
			else c.add(new Card());
		}
		if(p.get(playerTurn).getUnused().size()>0) c.add(p.get(playerTurn).getUnused().get(0));
		else c.add(new Card());
		if(p.get(playerTurn).getDeck().size()>0) c.add(p.get(playerTurn).getDeck().get(0));
		else c.add(new Card());
		if(p.get(Math.abs(playerTurn-1)).getDeck().size()>0) c.add(p.get(Math.abs(playerTurn-1)).getDeck().get(0));
		else c.add(new Card());
		if(p.get(Math.abs(playerTurn-1)).getUnused().size()>0) c.add(p.get(Math.abs(playerTurn-1)).getUnused().get(0));
		else c.add(new Card());
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
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        
        frame.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent mE) {
	        	if(shapes.get(4).contains(mE.getPoint())) {
	        		drawCard();
	        	}
	        }
		});
		
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent kE) {
				if(kE.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.out.print("boi");
					frame.setVisible(false);
					running = false;
				}
				if(kE.getKeyCode() == KeyEvent.VK_ENTER) { endTurn(); }
			}
			
			@Override
			public void keyTyped(KeyEvent e) {  }
			
			@Override
			public void keyReleased(KeyEvent e) {  }
		});
	}
	
	class DrawPanel extends JPanel{
		private static final long serialVersionUID = 2617928535228650371L;
		private Shape play1, play2, play3;
		private Shape deck1, deck2;
		private Shape unused1, unused2;
		
		public DrawPanel() {
			shapes = new ArrayList<Shape>();
		}

		@Override
		public void paintComponent(Graphics g) {
			if(coord.get(0).getX() != 0) {
				play1 = new Rectangle2D.Double(coord.get(0).getX()+1, coord.get(0).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				play2 = new Rectangle2D.Double(coord.get(1).getX()+1, coord.get(1).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				play3 = new Rectangle2D.Double(coord.get(2).getX()+1, coord.get(2).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				deck1 = new Rectangle2D.Double(coord.get(3).getX()+1, coord.get(3).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				unused1 = new Rectangle2D.Double(coord.get(4).getX()+1, coord.get(4).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				unused2 = new Rectangle2D.Double(coord.get(5).getX()+1, coord.get(5).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				deck2 = new Rectangle2D.Double(coord.get(6).getX()+1, coord.get(6).getY()+1, SLOT_WIDTH-1, SLOT_HEIGHT-1);
				
				Color blue = new Color(0, 0, 255, alpha);
				Color red = new Color(255, 0, 0, alpha);
				Color green = new Color(0, 255, 0, alpha);
				Color violet = new Color(255, 0, 255, alpha);
				Color lightgray = new Color(190, 190, 190, 255);
				String playerString = "Player " + (playerTurn+1) + "'s Turn";
				String otherPlayerString = "Player " + (Math.abs(playerTurn-1)+1) + " is Waiting";
				String playerLife = "Life: " + p.get(playerTurn).getLife();
				String otherPlayerLife = "Life: " + p.get(Math.abs(playerTurn-1)).getLife();
				String playerDeckSize = Math.min(p.get(playerTurn).getDeck().size(), ogDeckSize) + "";
				String otherPlayerDeckSize = Math.min(p.get(Math.abs(playerTurn-1)).getDeck().size(), ogDeckSize) + "";
				String playerUnusedSize = p.get(playerTurn).getUnused().size() + "";
				String otherPlayerUnusedSize = p.get(Math.abs(playerTurn-1)).getUnused().size() + "";
				String a="", b="", c="", d="";
				int ATTR_SIZE = SLOT_WIDTH/6;
				int i, j;
				
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
				
				g2.clearRect(0, 0, getWidth(), getHeight());	// Clear Graphics
				g2.setColor(Color.black);						// Black Background
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.setColor(lightgray);							// Gray Slot outlines
				for(i = 0; i < coord.size(); i++) {
					g2.drawRect(coord.get(i).getX(), coord.get(i).getY(), SLOT_WIDTH, SLOT_HEIGHT);
				}
				
				g2.setColor(red);	// Slots
				g2.fill(play2);
				g2.setColor(blue);
				g2.fill(play1);
				g2.fill(play3);
				g2.setColor(green);
				g2.fill(deck1);
				g2.fill(deck2);
				g2.setColor(violet);
				g2.fill(unused1);
				g2.fill(unused2);
				
				if((shapes.size() == 0) && (((Rectangle2D.Double)deck1).getWidth() != 0)) {
					shapes.add(play1);
					shapes.add(play2);
					shapes.add(play3);
					shapes.add(deck1);
					shapes.add(unused1);
					shapes.add(unused2);
					shapes.add(deck2);
				}
				
				for(j = 0; j < coord.size(); j++) {
					switch(j) {
					case 6:
						if(p.get(Math.abs(playerTurn-1)).getUnused().size() > 0) {
							a = p.get(Math.abs(playerTurn-1)).getUnused().get(0).getName();
							b = p.get(Math.abs(playerTurn-1)).getUnused().get(0).getType();
							c = p.get(Math.abs(playerTurn-1)).getUnused().get(0).getCost()+"";
							d = p.get(Math.abs(playerTurn-1)).getUnused().get(0).getVal()+"";
						}
						else {
							a = b = c = d = "";
						}
						break;
					case 5:
						if(p.get(Math.abs(playerTurn-1)).getDeck().size() > 0) {
							a = p.get(Math.abs(playerTurn-1)).getDeck().get(0).getName();
							b = p.get(Math.abs(playerTurn-1)).getDeck().get(0).getType();
							c = p.get(Math.abs(playerTurn-1)).getDeck().get(0).getCost()+"";
							d = p.get(Math.abs(playerTurn-1)).getDeck().get(0).getVal()+"";
						}
						else {
							a = b = c = d = "";
						}
						break;
					case 4:
						if(p.get(playerTurn).getDeck().size() > 0) {
							a = p.get(playerTurn).getDeck().get(0).getName();
							b = p.get(playerTurn).getDeck().get(0).getType();
							c = p.get(playerTurn).getDeck().get(0).getCost()+"";
							d = p.get(playerTurn).getDeck().get(0).getVal()+"";
						}
						else {
							a = b = c = d = "";
						}
						break;
					case 3:
						if(p.get(playerTurn).getUnused().size() > 0) {
							a = p.get(playerTurn).getUnused().get(0).getName();
							b = p.get(playerTurn).getUnused().get(0).getType();
							c = p.get(playerTurn).getUnused().get(0).getCost()+"";
							d = p.get(playerTurn).getUnused().get(0).getVal()+"";
						}
						else {
							a = b = c = d = "";
						}
						break;
					case 2:
					case 1:
					case 0:
						a = f.getPlay().get(j).getName();
						if(a.equals("")) {
							b = c = d = "";
						}
						else {
							b = f.getPlay().get(j).getType();
							c = f.getPlay().get(j).getCost()+"";
							d = f.getPlay().get(j).getVal()+"";
						}
						break;
					}
					
					if(!a.equals("")) {
						g.setColor(Color.black);
						g.fillRect(coord.get(j).getX()+1, coord.get(j).getY()+1, SLOT_WIDTH-1, ATTR_SIZE);
						g.fillRect(coord.get(j).getX()+1, coord.get(j).getY()+SLOT_HEIGHT*8/9-1, ATTR_SIZE, ATTR_SIZE);
						g.fillRect(coord.get(j).getX()+SLOT_WIDTH-ATTR_SIZE, coord.get(j).getY()+SLOT_HEIGHT*8/9-1, ATTR_SIZE, ATTR_SIZE);
						g.setColor(lightgray);
						g.drawRect(coord.get(j).getX()+SLOT_WIDTH/2, coord.get(j).getY()+1, 1, ATTR_SIZE-1);
					}
					
					g2.setFont(new Font(g2.getFont().getName(), Font.PLAIN, 24));
					FontMetrics m = g2.getFontMetrics(g2.getFont());
					
					if(!a.equals("")) {
						g2.setColor(lightgray);
						g2.drawString(a, coord.get(j).getX()+10, coord.get(j).getY()+ATTR_SIZE*13/16-2);
						g2.drawString(b, coord.get(j).getX()-10+SLOT_WIDTH-m.stringWidth(b), coord.get(j).getY()+ATTR_SIZE*13/16-2);
						g2.drawString(c, coord.get(j).getX()+10, coord.get(j).getY()+SLOT_HEIGHT*8/9-1+ATTR_SIZE*13/16-2);
						g2.drawString(d, coord.get(j).getX()-10+SLOT_WIDTH-m.stringWidth(d), coord.get(j).getY()+SLOT_HEIGHT*8/9-1+ATTR_SIZE*13/16-2);
					}
				}
				
				g2.setFont(new Font(g2.getFont().getName(), Font.PLAIN, 32));
				FontMetrics m = g2.getFontMetrics(g2.getFont());
				
				g2.setColor(lightgray);	// Strings
				g2.drawString("FPS: " + fps, 10, draw.getHeight()-10);
				g2.drawString(playerString, draw.getWidth()/2-m.stringWidth(playerString)/2, coord.get(3).getY()+SLOT_HEIGHT);
				g2.drawString(otherPlayerString, draw.getWidth()/2-m.stringWidth(otherPlayerString)/2, coord.get(5).getY()+m.getHeight()/2);
				g2.drawString(playerLife, draw.getWidth()/2-m.stringWidth(playerLife)/2, coord.get(3).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(otherPlayerLife, draw.getWidth()/2-m.stringWidth(otherPlayerLife)/2, coord.get(5).getY()+m.getHeight()*3/2);
				g2.drawString(MAIN, coord.get(1).getX()+SLOT_WIDTH/2-m.stringWidth(MAIN)/2, coord.get(1).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(BOOST, coord.get(0).getX()+SLOT_WIDTH/2-m.stringWidth(BOOST)/2, coord.get(0).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(BOOST, coord.get(2).getX()+SLOT_WIDTH/2-m.stringWidth(BOOST)/2, coord.get(2).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(DECK, coord.get(4).getX()+SLOT_WIDTH/2-m.stringWidth(DECK)/2, coord.get(4).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(DECK, coord.get(5).getX()+SLOT_WIDTH/2-m.stringWidth(DECK)/2, coord.get(5).getY()-16);
				g2.drawString(UNUSED, coord.get(3).getX()+SLOT_WIDTH/2-m.stringWidth(UNUSED)/2, coord.get(3).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(UNUSED, coord.get(6).getX()+SLOT_WIDTH/2-m.stringWidth(UNUSED)/2, coord.get(6).getY()-16);
				g2.drawString(playerDeckSize, coord.get(4).getX()+SLOT_WIDTH/2-m.stringWidth(playerDeckSize)/2, coord.get(4).getY()-16);
				g2.drawString(otherPlayerDeckSize, coord.get(5).getX()+SLOT_WIDTH/2-m.stringWidth(otherPlayerDeckSize)/2, coord.get(5).getY()+SLOT_HEIGHT+m.getHeight());
				g2.drawString(playerUnusedSize, coord.get(3).getX()+SLOT_WIDTH/2-m.stringWidth(playerUnusedSize)/2, coord.get(3).getY()-16);
				g2.drawString(otherPlayerUnusedSize, coord.get(6).getX()+SLOT_WIDTH/2-m.stringWidth(otherPlayerUnusedSize)/2, coord.get(6).getY()+SLOT_HEIGHT+m.getHeight());
			}
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
}