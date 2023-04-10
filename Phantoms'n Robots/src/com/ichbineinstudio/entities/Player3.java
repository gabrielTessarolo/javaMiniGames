package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.Camera;

public class Player3 extends Entity{
	
	public int xTarget, yTarget;
	public boolean isAttacking = false;
	
	private int timer = 120;
	private int timerCounter = 0;
	
	private static BufferedImage towerR, towerL;
	public static BufferedImage littletowerR;
	public static BufferedImage littletowerL;
	
	public static double powerAttack = 1;
	public static double powerSpeed = 1;
	
	public boolean iceShoot = false;
	public boolean isAtRight = false, isAtLeft = true;
	
	public int iceCounter = 0;
	
	public Player3(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		towerR = Game.spritesheet.getSprite(10*16, 8*16, 32, 32);
		towerL = Game.spritesheet.getSprite(12*16, 8*16, 32, 32);
		
		littletowerR = Game.spritesheet.getSprite(6*16, 13*16, 16, 16);
		littletowerL = Game.spritesheet.getSprite(7*16, 13*16, 16, 16);
		
		
	}
	
	public void tick(){
	
		timerCounter+=powerSpeed;;
		if(timerCounter >= timer) {
			timerCounter = 0;
			//Criar inimigo
			iceShoot = true;
			//Arrow arrow = new Arrow(x,y,16,16,Entity.rand.nextDouble(),Entity.ENEMY1_RIGHT);
			//Game.entities.add(arrow);
		}
		
		
		Enemy enemy = null;
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				int xEnemy = e.getX();
				int yEnemy = e.getY();
				if(Entity.calculateDistance(this.getX() + 16, this.getY() + 5, xEnemy, yEnemy) < 40) {
					enemy = (Enemy) e;
				}
				
			}
		}
		
		if(enemy != null) {
			isAttacking = true;
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
			
			if(iceShoot) {
				enemy.life -= 1.5 * (powerAttack);
				if(iceCounter < 400) {
					iceCounter++;
					if(enemy.type == 1) {
					enemy.pathSpeed = 30;
					}else if(enemy.type == 2) {
					enemy.pathSpeed = 40;
					}
				}else {
					iceCounter = 0;
				}
				iceShoot = false;
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
		
		if(timerCounter > timer - 5 && isAttacking) {
			g.setColor(new Color(109, 194, 255));
			if(isAtRight) {
				g.drawLine(this.getX() +32 - 7, this.getY() + 5 - Camera.y, xTarget, yTarget - Camera.y);
				
			}
			else if(isAtLeft) {
				g.drawLine(this.getX() + 7, this.getY() + 5 - Camera.y, xTarget, yTarget - Camera.y);
			}
		}
		
	}else if(width == 16 && height == 16) {
		if(isAtRight) {
			g.drawImage(littletowerR, this.getX(), this.getY() - Camera.y, 16, 16, null);
		}
		else if(isAtLeft) {
			g.drawImage(littletowerL, this.getX(), this.getY() - Camera.y, 16, 16, null);
		}
		
		
		if(timerCounter > timer - 5 && isAttacking) {
			g.setColor(new Color(109, 194, 255));
			if(isAtRight) {
				g.drawLine(this.getX() + 16, this.getY() + 5 - Camera.y, xTarget, yTarget - Camera.y);
				
			}
			else if(isAtLeft) {
				g.drawLine(this.getX(), this.getY() + 5 - Camera.y, xTarget, yTarget - Camera.y);
			}
		}
	}
		
	}
	
	


}



