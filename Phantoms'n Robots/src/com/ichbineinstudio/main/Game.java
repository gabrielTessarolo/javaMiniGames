package com.ichbineinstudio.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

import com.ichbineinstudio.entities.Enemy_path2;
import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.Grenade;
import com.ichbineinstudio.entities.Player;
import com.ichbineinstudio.entities.Player2;
import com.ichbineinstudio.entities.Player2_path2;
import com.ichbineinstudio.entities.Player3;
import com.ichbineinstudio.entities.Player3_path2;
import com.ichbineinstudio.entities.Player4;
import com.ichbineinstudio.entities.Player4_path2;
import com.ichbineinstudio.entities.Player_path2;
import com.ichbineinstudio.entities.Spawner;
import com.ichbineinstudio.entities.Target;
import com.ichbineinstudio.entities.TowerController;
import com.ichbineinstudio.entities.TowerController2;
import com.ichbineinstudio.graficos.Spritesheet;
import com.ichbineinstudio.graficos.UI;
import com.ichbineinstudio.world.Camera;
import com.ichbineinstudio.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;
	
	private BufferedImage image;
	
	public static Spawner spawner;
	public static World world;
	public static List<Entity> entities;
	public static List<Grenade> arrows;
	public static List<Target> targets;
	public static Spritesheet spritesheet;
	
	public static boolean introduct = true;
	private int opacity = 120, introductFrames = 0;

	public UI ui;
	public Menu menu;
	public SelectMode selMode;
	public Towers towersDescription;
	public Controls controls;
	public selectingMLevel selectingM;
	
	public TowerController towerController;
	public TowerController2 towerController2;
	
	public static boolean select = false; 
	public static int numberOfPlayers = 1;
	
	public static String gameState = "Menu";
	
	public static int life = 5;
	public static int money = 150;
	
	public static int CUR_LEVEL = 1;
	public static int MAX_LEVEL = 8;
	public static boolean updateLvl = false;
	
	
	public Game(){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this); 
		//setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize())); // Descomente essa linha para que o jogo esteja em tela cheia.
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE)); // Descomente esta linha para que a janela do jogo tenha dimensões definidas.
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos.
		
		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		arrows = new ArrayList<Grenade>();
		targets = new ArrayList<Target>();
		world = new World("/level1.png");
		ui = new UI();
		menu = new Menu();
		selMode = new SelectMode();
		towersDescription = new Towers();
		controls = new Controls();
		selectingM = new selectingMLevel();

		towerController = new TowerController(0, 0, 0, 0, 0, null);
		towerController2 = new TowerController2(0, 0, 0, 0, 0, null);
		spawner = new Spawner(0, 0, 0, 0, 0, null);
		
		//towerController = new TowerController(8*16, 0, 0, 0, 0, null);
		
		
	}
	
	public void initFrame(){
		frame = new JFrame("Phantoms'n Robots");
		frame.add(this);
		// frame.setUndecorated(true); // Assim, não é mais possível fechar, minimizar ou rediminesionar a janela do jogo.
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
		
	//	System.out.println(Game.targets.size());
		
	//	System.out.println(TowerController2.hotbar);
		
		if(gameState == "Menu") {
			menu.tick();
		}else if(gameState == "Towers") {
			towersDescription.tick();
		}else if(gameState == "GameMode") {
			selMode.tick();
		}else if(gameState == "Controls") {
			controls.tick();
		}else if(gameState == "selectingM") {
			selectingM.tick();
		}
		
		
		/*if(CUR_LEVEL == 2) {
			SCALE = 2;
		}*/
		
		
		else if(gameState == "Normal") {
		if(this.life <= 0) {
			CUR_LEVEL = 1;
			String newWorld = "Level"+CUR_LEVEL+".png";
			World.restartGame(newWorld);
		}
		
		if(this.numberOfPlayers == 2) {
			if(TowerController.life <= 0 || TowerController2.life <= 0) {
				if(TowerController.life > TowerController2.life) {
					TowerController.score+=1;
				}else {
					TowerController2.score+=1;
				}
				
				String newWorld = "Level"+CUR_LEVEL+".png";
				World.restartGame(newWorld);
			}
			if(Spawner.timeOfMatch >= 8000) {
				if(TowerController.life != TowerController2.life) {
					if(TowerController.life > TowerController2.life) {
						TowerController.score +=1;
					}else if(TowerController.life < TowerController2.life) {
						TowerController2.score +=1;
					}
					String newWorld = "Level"+CUR_LEVEL+".png";
					World.restartGame(newWorld);
				}else {
					String newWorld = "Level"+CUR_LEVEL+".png";
					World.restartGame(newWorld);
				}
				
			}
		}
		
		if(updateLvl) {
			updateLvl = false;
			introduct = true;
			opacity = 120;
			introductFrames = 0;
			
			Player.powerAttack = (100/100);
			Player2.powerAttack = (100/100);
			Player3.powerAttack = (100/100);
			Player4.powerAttack = (100/100);
			Player_path2.powerAttack = (100/100);
			Player2_path2.powerAttack = (100/100);
			Player3_path2.powerAttack = (100/100);
			Player4_path2.powerAttack = (100/100);
			
			Player.powerSpeed = (100/100);
			Player2.powerSpeed = (100/100);
			Player3.powerSpeed = (100/100);
			Player4.powerSpeed = (100/100);
			Player_path2.powerSpeed = (100/100);
			Player2_path2.powerSpeed = (100/100);
			Player3_path2.powerSpeed = (100/100);
			Player4_path2.powerSpeed = (100/100);
			
			Spawner.numberOfDeadPhantoms = 0;
			
			TowerController.tinyMode = false;
			TowerController2.tinyMode = false;
			
			if(numberOfPlayers == 1) {
			    CUR_LEVEL += 1;
			}else if(numberOfPlayers == 2){

			}
			if(CUR_LEVEL > MAX_LEVEL) {
				CUR_LEVEL = 1;
			}
			String newWorld = "Level"+CUR_LEVEL+".png";
			World.restartGame(newWorld);
		}

		if(introduct == false) {
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
		}
		
		ui.tick();
		
		towerController.tick();
		if(this.numberOfPlayers == 2) {
		towerController2.tick();
		}

		}
		if(Game.gameState == "Normal") {
			if(introduct) {
				if(introductFrames < 180) {
			    	introductFrames++;
			    	if(introductFrames > 60) {
			    		opacity--;
			    	}
				}else {
					introduct = false;
				}
			    
			}
		}
	}
	


	
	public void render(){
		
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(122,102,255));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		//g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		
		/*Renderiza��o do jogo*/
		//Graphics2D g2 = (Graphics2D) g;
		
		if(gameState == "Normal") {
			world.render(g);
			towerController.render(g);
			if(this.numberOfPlayers == 2) {
			towerController2.render(g);
			}
		}	
			
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e instanceof Spawner == false) {
				e.render(g);
			}
		}
		
		spawner.render(g); 		
		if(gameState == "Menu") {
			menu.render(g);
		}else if(gameState == "Towers") {
			towersDescription.render(g);
		}else if(gameState == "GameMode") {
			selMode.render(g);
		}else if(gameState == "Controls") {
			controls.render(g);
		}else if(gameState == "selectingM") {
			selectingM.render(g);
		}
		/***/
		
		g.dispose();
		g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null); //Descomente essa linha para aplicar uma dimensão específica à janela do jogo.
		//g.drawImage(image, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null); //Descomente essa linha para aplicar a tela cheia à janela do jogo.
		if(gameState == "Normal") {
			ui.render(g);
			if(this.introduct && numberOfPlayers == 1) {
				g.setColor(new Color(30, 30, 30, opacity));
				g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
				String title = "Level "+Integer.toString(CUR_LEVEL); 
				String introductPhrase = "";
				g.setFont(new Font("Uroob", Font.BOLD, 100));
				g.setColor(new Color(255, 255, 255, opacity));
				g.drawString(title, Game.WIDTH*Game.SCALE/2 - 110, Game.HEIGHT*Game.SCALE/2 - 20);
				if(Spawner.maxKills[Game.CUR_LEVEL - 1] == 999) {
					introductPhrase += "Survive until " + Spawner.finalTime[Game.CUR_LEVEL - 1];;
					g.setFont(new Font("Uroob", Font.ITALIC, 50));
					g.drawString(introductPhrase, Game.WIDTH*Game.SCALE/2 - 150, Game.HEIGHT*Game.SCALE/2 + 20);
				}else if(Spawner.toTicks() != 362340) {
					introductPhrase += "Kill " + Spawner.maxKills[Game.CUR_LEVEL - 1] + " phantoms";
					g.drawString(introductPhrase, Game.WIDTH*Game.SCALE/2 - 115, Game.HEIGHT*Game.SCALE/2 + 20);
				}
			}
		}
		
		
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
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}

	public void keyPressed(KeyEvent e) {
		if(this.numberOfPlayers == 1) {
			if(this.CUR_LEVEL == 8) {
				if(e.getKeyCode() == KeyEvent.VK_G) {
					Camera.y = Camera.clamp(Camera.y + 16, 0, World.HEIGHT*16 - Game.HEIGHT);
				}
				else if(e.getKeyCode() == KeyEvent.VK_T) {
					Camera.y = Camera.clamp(Camera.y - 16, 0, World.HEIGHT*16 - Game.HEIGHT);
				}
			}
			if(TowerController.hotbar == 1) {
		if(e.getKeyCode() == KeyEvent.VK_1) {
			towerController.towerSelected = 1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_2) {
			towerController.towerSelected = 2;
		}
		else if(e.getKeyCode() == KeyEvent.VK_3) {
			towerController.towerSelected = 3;
		}
		else if(e.getKeyCode() == KeyEvent.VK_4) {
			towerController.towerSelected = 4;
		}
		else if(e.getKeyCode() == KeyEvent.VK_5) {
			towerController.towerSelected = 5;
		}
			}else if(TowerController.hotbar == 2){
				if(e.getKeyCode() == KeyEvent.VK_1) {
					towerController.deviceSelected = 1;
				}
				else if(e.getKeyCode() == KeyEvent.VK_2) {
					towerController.deviceSelected = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3) {
					towerController.deviceSelected = 3;
				}
			}
		
		}else if(numberOfPlayers == 2) {
			if(TowerController2.hotbar == 1) {
			if(e.getKeyCode() == KeyEvent.VK_1) {
				towerController2.towerSelected = 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_2) {
				towerController2.towerSelected = 2;
			}
			else if(e.getKeyCode() == KeyEvent.VK_3) {
				towerController2.towerSelected = 3;
			}
			else if(e.getKeyCode() == KeyEvent.VK_4) {
				towerController2.towerSelected = 4;
			}
			else if(e.getKeyCode() == KeyEvent.VK_5) {
				towerController2.towerSelected = 5;
			}
			}else if(TowerController2.hotbar == 2) {
				if(e.getKeyCode() == KeyEvent.VK_1) {
					towerController2.deviceSelected = 1;
				}
				else if(e.getKeyCode() == KeyEvent.VK_2) {
					towerController2.deviceSelected = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_3) {
					towerController2.deviceSelected = 3;
				}
			}
			if(TowerController.hotbar == 1) {
	        if(e.getKeyCode() == KeyEvent.VK_6) {
				towerController.towerSelected = 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_7) {
				towerController.towerSelected = 2;
			}
			else if(e.getKeyCode() == KeyEvent.VK_8) {
				towerController.towerSelected = 3;
			}
			else if(e.getKeyCode() == KeyEvent.VK_9) {
				towerController.towerSelected = 4;
			}
			else if(e.getKeyCode() == KeyEvent.VK_0) {
				towerController.towerSelected = 5;
			}
			}else if(TowerController.hotbar == 2) {
				if(e.getKeyCode() == KeyEvent.VK_6) {
					towerController.deviceSelected = 1;
				}
				else if(e.getKeyCode() == KeyEvent.VK_7) {
					towerController.deviceSelected = 2;
				}
				else if(e.getKeyCode() == KeyEvent.VK_8) {
					towerController.deviceSelected = 3;
				}
			}
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(gameState == "Normal") {
				towerController.yTarget += 16;
		}
			
			
			if(gameState == "Menu") {
			menu.down = true;
			}else if(gameState == "Towers") {
		    towersDescription.down = true;	
			}else if(gameState == "GameMode") {
			selMode.down = true;	
			}else if(gameState == "Controls") {
		    controls.down = true;	
			}else if(gameState == "selectingM") {
			selectingM.down = true;	
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(gameState == "Normal") {
					towerController.yTarget -= 16;
			}
			if(gameState == "Menu") {
				menu.up = true;
				}else if(gameState == "Towers") {
			    towersDescription.up = true;	
				}else if(gameState == "GameMode") {
					selMode.up = true;	
				}else if(gameState == "Controls") {
					controls.up = true;	
				}else if(gameState == "selectingM") {
					selectingM.up = true;	
				}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(gameState == "Normal") {
					towerController.xTarget -= 16;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(gameState == "Normal") {
					towerController.xTarget += 16;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "Normal") {
				if(!menu.pause) {
				gameState = "Menu";
				menu.pause = true;
				}
				}
				else if(menu.pause) {
				gameState = "Normal";
				menu.pause = false;
				}
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(gameState != "Normal") {
			select = true;
			}else {
				if(numberOfPlayers == 2) {
					towerController2.isPressed = true;
				}
			}
		
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameState == "Normal") {
			
				if(numberOfPlayers == 2) {
					towerController.isPressed = true;
				}
			}
		
		}
		
		else if(e.getKeyCode() ==  KeyEvent.VK_W) {
		if(gameState == "Normal") {
			if(numberOfPlayers == 2) {
				towerController2.yTarget -= 16;
			}
		}
		else if(gameState == "Menu") {
			menu.up = true;
			}else if(gameState == "Towers") {
		    towersDescription.up = true;	
			}else if(gameState == "GameMode") {
				selMode.up = true;	
			}else if(gameState == "Controls") {
				controls.up = true;	
			}else if(gameState == "selectingM") {
				selectingM.up = true;	
			}
		}
		else if(e.getKeyCode() ==  KeyEvent.VK_S) {
			if(gameState == "Normal") {
				if(numberOfPlayers == 2) {
					towerController2.yTarget += 16;
				}
			}
			else if(gameState == "Menu") {
				menu.down = true;
				}else if(gameState == "Towers") {
			    towersDescription.down = true;	
				}else if(gameState == "GameMode") {
				selMode.down = true;	
				}else if(gameState == "Controls") {
					controls.down = true;	
				}else if(gameState == "selectingM") {
					selectingM.down = true;	
				}
		}
		else if(e.getKeyCode() ==  KeyEvent.VK_A) {
			if(gameState == "Normal") {
				if(numberOfPlayers == 2) {
					towerController2.xTarget -= 16;
				}
			}
		}
		else if(e.getKeyCode() ==  KeyEvent.VK_D) {
			if(gameState == "Normal") {
				if(numberOfPlayers == 2) {
					towerController2.xTarget += 16;
				}
			}
		}
		else if(e.getKeyCode() ==  KeyEvent.VK_Q) {
			if(gameState == "Normal") {
				
				if(numberOfPlayers == 1) {
					if(TowerController.hotbar == 1) {
						TowerController.hotbar = 2;
					}else if(TowerController.hotbar == 2) {
						TowerController.hotbar = 1;
					}
				}
				
				else if(numberOfPlayers == 2) {
					if(TowerController2.hotbar == 1) {
						TowerController2.hotbar = 2;
					}else if(TowerController2.hotbar == 2) {
						TowerController2.hotbar = 1;
					}
				}
			}
		}
		else if(e.getKeyCode() ==  KeyEvent.VK_BACK_SPACE) {
			if(gameState == "Normal") {
			
					if(TowerController.hotbar == 1) {
						TowerController.hotbar = 2;
					}else if(TowerController.hotbar == 2) {
						TowerController.hotbar = 1;
					}
				
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		if(numberOfPlayers == 1) {
		towerController.isPressed = true;
		towerController.xTarget = e.getX() / 3; // Normal é /3, Tela cheia é /8
		towerController.yTarget = (int) (e.getY() / 3); // Normal é /3, Tela cheia é (int)(/6.75)
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//towerController.xTarget = e.getX() / 3;
		//towerController.yTarget = e.getY() / 3;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.numberOfPlayers == 1) {
		towerController.xTarget = e.getX() / 3; // Normal é /3, Tela cheia é /8
		towerController.yTarget = (int) (e.getY() / 3); // Normal é /3, Tela cheia é (int)(/6.75)
		//System.out.println(Game.WIDTH + " x " + Game.HEIGHT); 160*8 = 1280
		//System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width + " x " + Toolkit.getDefaultToolkit().getScreenSize().height);
		//System.out.println("X" + e.getX() / 3 / 16);
		//System.out.println("Y" + e.getY() / 3 / 16);
		}
		
	}

	
}
