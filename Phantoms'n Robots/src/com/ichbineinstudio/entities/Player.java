package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.Camera;
import com.ichbineinstudio.world.World;


public class Player extends Entity{
	
	public static boolean playerWasHurt = false;
	public static int playerHurtCounter = 0;
	
	public int index = 0;
	public int frames = 0;
	public int maxFrames = 20;
	public int maxIndex = 1;
	
	public static double powerAttack = 1;
	public static double powerSpeed = 1;
	
	
	public static BufferedImage[] player;
	public static BufferedImage[] playerHurt;
	
	public static BufferedImage[] tower2;
	public static BufferedImage[] littletower2;
	
	public static BufferedImage[] heart;
	
	public int xTarget, yTarget;
	public boolean isAttacking = false;
	
	private int timer = 60;
	private int timerCounter = 0;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		player = new BufferedImage[2];
		playerHurt = new BufferedImage[2];
		heart = new BufferedImage[2];
		tower2 = new BufferedImage[2];
		littletower2 = new BufferedImage[2];
		
		player[0] = Game.spritesheet.getSprite(6*16, 0, 32, 32);
		player[1] = Game.spritesheet.getSprite(6*16, 2*16, 32, 32);
		
		playerHurt[0] = Game.spritesheet.getSprite(8*16, 0, 32, 32);
		playerHurt[1] = Game.spritesheet.getSprite(8*16, 2*16, 32, 32);
		
		heart[0] = Game.spritesheet.getSprite(6*16, 4*16, 16, 16);
		heart[1] = Game.spritesheet.getSprite(7*16, 4*16, 16, 16);
		
		tower2[0] = Game.spritesheet.getSprite(6*16, 8*16, 32, 32);
		tower2[1] = Game.spritesheet.getSprite(8*16, 8*16, 32, 32);
		
	   littletower2[0] = Game.spritesheet.getSprite(8*16, 12*16, 16, 16);
	   littletower2[1] = Game.spritesheet.getSprite(9*16, 12*16, 16, 16);
		
	}
	
	public void tick(){
		
		
		
	/*	timerCounter++;
		if(timerCounter >= timer) {
			timerCounter = 0;
			//Criar inimigo
		//	Arrow arrow = new Arrow(x,y,16,16,Entity.rand.nextDouble(),Entity.ENEMY1_RIGHT);
		//	Game.entities.add(arrow);
		}
		*/
		
		
		Enemy enemy = null;
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				int xEnemy = e.getX();
				int yEnemy = e.getY();
				if(Entity.calculateDistance(this.getX() + 16, this.getY() + 5, xEnemy, yEnemy) < 50) {
					enemy = (Enemy) e;
				}
				
			}
		}
		
		if(enemy != null) {
			isAttacking = true;
			xTarget = enemy.getX();
			yTarget = enemy.getY();
			if(Entity.rand.nextInt(100) < 20) {
			enemy.life -= 0.1 * (powerAttack);
			}
		}else {
			isAttacking = false;
		}
		
		frames++;
		if(frames >= maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex)
				index = 0;
		}
		
		
		
		
	}
	
	public void render(Graphics g) {
		
	//	System.out.println(width+"/"+height);
		
		if(width == 32 && height == 32) {
		g.drawImage(tower2[index], this.getX(), this.getY() - Camera.y, 32, 32, null);
		if(isAttacking) {
			g.setColor(new Color(255, 204, 51));
			g.drawLine((int)(x+16), (int)(y+5) - Camera.y, xTarget + 8, (yTarget + 8) - Camera.y);
		}
		}
		else if(width == 16 && height == 16) {
			g.drawImage(littletower2[index], this.getX(), this.getY() - Camera.y, 16, 16, null);
			if(isAttacking) {
				g.setColor(new Color(255, 204, 51));
				g.drawLine((int)(x+8), (int)(y+2) - Camera.y, xTarget + 8, (yTarget + 8) - Camera.y);
			}
		}
		
	}
	
	


}
