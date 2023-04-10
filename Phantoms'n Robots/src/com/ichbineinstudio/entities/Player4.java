package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.Camera;

public class Player4 extends Entity{
	
	public int xTarget;
	public int yTarget;
	public boolean isAttacking = false;
	
	private int timer = 120;
	private int timerCounter = 0;
	
	public static double powerAttack = 1;
	public static double powerSpeed = 1;
	
	private static BufferedImage towerR, towerL;
	public static BufferedImage littletowerR;
	public static BufferedImage littletowerL;
	private BufferedImage[] explosion;
	public int explosionIndex = 0;
	public int explosionframes = 0;
	public int explosionmaxFrames = 6;
	public int explosionmaxIndex = 2;
	public static int xExplosion, yExplosion;
	
	public boolean bombShoot = false;
	public boolean isAtRight = false, isAtLeft = true;
	
	public Player4(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		towerR = Game.spritesheet.getSprite(6*16, 10*16, 32, 32);
		towerL = Game.spritesheet.getSprite(8*16, 10*16, 32, 32);

		littletowerR = Game.spritesheet.getSprite(8*16, 13*16, 16, 16);
		littletowerL = Game.spritesheet.getSprite(9*16, 13*16, 16, 16);
		
		explosion = new BufferedImage[3];

		explosion[0] = Game.spritesheet.getSprite(5*16, 12*16, 16, 16);
		explosion[1] = Game.spritesheet.getSprite(5*16, 13*16, 16, 16);
		explosion[2] = Game.spritesheet.getSprite(5*16, 14*16, 16, 16);
		
		
	}
	
	public void tick(){
		
		
		timerCounter+=powerSpeed;
		if(timerCounter >= timer) {
			timerCounter = 0;
			//Criar inimigo
			bombShoot = true;
			//Arrow arrow = new Arrow(x,y,16,16,Entity.rand.nextDouble(),Entity.ENEMY1_RIGHT);
			//Game.entities.add(arrow);
		}
		
		
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
			Grenade.thereIsAnEnemy = true;
			xTarget = enemy.getX() + 8;
			yTarget = enemy.getY();
			
			if(enemy.getX() < this.getX()) {
				isAtLeft = true;
				isAtRight = false;
			}
			else {
				isAtRight = true;
				isAtLeft = false;
			}
			
			
			if(timerCounter > timer - 20) {
				explosionframes++;
				if(explosionframes >= explosionmaxFrames) {
					explosionframes = 0;
					explosionIndex++;
					if(explosionIndex > explosionmaxIndex)
						explosionIndex = 0;
				}
			}
			if(bombShoot) {
				
				enemy.life -= 5 * (powerAttack);
				bombShoot = false;
				Game.entities.remove(this);
				Game.entities.add(this);	
			}
			
		}else {
			isAttacking = false;
		}
		
		
		
		
	}
	
	public void render(Graphics g) {

	if(width == 32 && height == 32) {
			if(isAtRight) {
				g.drawImage(towerR, this.getX(), this.getY() - Camera.y, 32, 32, null);
			}
			else if(isAtLeft) {
				g.drawImage(towerL, this.getX(), this.getY() - Camera.y, 32, 32, null);
			}
	}else if(width == 16 && height == 16) {
		if(isAtRight) {
			g.drawImage(littletowerR, this.getX(), this.getY() - Camera.y, 16, 16, null);
		}
		else if(isAtLeft) {
			g.drawImage(littletowerL, this.getX(), this.getY() - Camera.y, 16, 16, null);
		}
	}
			
			if(isAttacking) {
				
				g.drawImage(Entity.cannotSetTowerIcon, xTarget - 8, yTarget - Camera.y, 16, 16, null);
				
			if(timerCounter > timer - 20) {
				g.drawImage(explosion[explosionIndex], xTarget - 8, yTarget - Camera.y, 16, 16, null);
					
				}
					
			}
			
			
		}
		
	
	
	


}



