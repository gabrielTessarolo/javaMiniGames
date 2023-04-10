package com.gtstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import com.gtstudios.entities.Ball;
import com.gtstudios.entities.Controller;
import com.gtstudios.entities.Entity;
import com.gtstudios.entities.Hole;
import com.gtstudios.entities.Selector;
import com.gtstudios.entities.Spawner;
import com.gtstudios.graficos.Spritesheet;
import com.gtstudios.graficos.UI;
import com.gtstudios.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 360;
	public static final int HEIGHT = 300;
	public static final int SCALE = 2;
	
	private BufferedImage image;
	
	public static World world;
	public static List<Entity> entities;
	public static List<Ball> balls;
	public static List<Hole> holes;
	public static List<Selector> slcs;
	public static Spritesheet spritesheet;
	public static Spawner spawner;
	public static Controller control;
	
	public static UI ui;
	
	public static int curLevel = 1;
		
	public Game() {
	
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos.
		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		holes = new ArrayList<Hole>();
		balls = new ArrayList<Ball>();
		slcs = new ArrayList<Selector>();
		
		ui = new UI();
		world = new World();
		//ui = new UI();
		//background = new Background();
		spawner = new Spawner();
	
		control = new Controller(World.coordX[Game.curLevel - 1], World.coordY[Game.curLevel - 1], 16, 16, 0, null);
		
		world.restartGame();
			
	}
	
	public void initFrame(){
		frame = new JFrame("Golf");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			    cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		
		spawner.tick();
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		//ui.tick();
		//background.tick();
	
	}
	
	
	
	
	public void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(15, 100, 4));
	    g.fillRect(0, 0,WIDTH,HEIGHT);
	    
	    ui.render(g);
		//background.render(g);
		
		/*Renderiza��o do jogo*/
		//Graphics2D g2 = (Graphics2D) g;
		//world.render(g);
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(!(e instanceof Controller)){e.render(g);}
		}
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e instanceof Controller){e.render(g);}
		}
		
		
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		//ui.render(g);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
		}
		
		stop();
	}
		
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Game.control.xPos = e.getX()/2;
		Game.control.yPos = e.getY()/2;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.control.xPos = e.getX()/2;
		Game.control.yPos = e.getY()/2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Game.control.xPos = e.getX()/2;
		Game.control.yPos = e.getY()/2;
		if(Game.control.canClick) {
			Game.control.clicked = true;
			if(e.getButton() == MouseEvent.BUTTON1) {control.phase = control.selecting;}
			else if(e.getButton() == MouseEvent.BUTTON3) {control.phase = control.ignoring;}
			if(control.someBallIsSlc(false) && control.phase == control.selecting) {control.phase = control.playing;}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
