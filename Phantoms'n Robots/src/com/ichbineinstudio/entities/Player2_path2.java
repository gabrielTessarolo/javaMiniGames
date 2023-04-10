package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;

public class Player2_path2 extends Entity{
	
	public int xTarget, yTarget;
	public boolean isAttacking = false;
	
	private int timer = 60;
	private int timerCounter = 0;
	
	public static double powerAttack = 1;
	public static double powerSpeed = 1;
	
	private static BufferedImage towerR, towerL, towerRAttacking, towerLAttacking;
	public static BufferedImage littletowerR;
	public static BufferedImage littletowerL;
	
	public boolean arrowShoot = false;
	public boolean isAtRight = false, isAtLeft = true;
	
	public Player2_path2(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		towerR = Game.spritesheet.getSprite(6*16, 6*16, 32, 32);
		towerL = Game.spritesheet.getSprite(8*16, 6*16, 32, 32);
		towerRAttacking = Game.spritesheet.getSprite(10*16, 6*16, 32, 32);
		towerLAttacking = Game.spritesheet.getSprite(12*16, 6*16, 32, 32);
		
		littletowerR = Game.spritesheet.getSprite(6*16, 12*16, 16, 16);
		littletowerL = Game.spritesheet.getSprite(7*16, 12*16, 16, 16);
		
	}
	
	public void tick(){
		
		timerCounter+=powerSpeed;
		if(timerCounter >= timer) {
			timerCounter = 0;
			//Criar inimigo
			arrowShoot = true;
			//Arrow arrow = new Arrow(x,y,16,16,Entity.rand.nextDouble(),Entity.ENEMY1_RIGHT);
			//Game.entities.add(arrow);
		}
		
		
		Enemy_path2 enemy = null;
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy_path2) {
				int xEnemy = e.getX();
				int yEnemy = e.getY();
				if(Entity.calculateDistance(this.getX() + 16, this.getY() + 5, xEnemy, yEnemy) < 50) {
					enemy = (Enemy_path2) e;
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
			
			if(arrowShoot) {
				enemy.life -= 1 * (powerAttack);
				arrowShoot = false;
			}
			
		}else {
			isAttacking = false;
		}
		
		
		
		
	}
	
	public void render(Graphics g) {

		if(width == 32 && height == 32) {
			if(!isAttacking) {
				if(isAtRight) {
					g.drawImage(towerR, this.getX(), this.getY(), 32, 32, null);
				}
				else if(isAtLeft) {
					g.drawImage(towerL, this.getX(), this.getY(), 32, 32, null);
				}
			}
			else {
				if(isAtRight) {
					g.drawImage(towerRAttacking, this.getX(), this.getY(), 32, 32, null);
				}
				else if(isAtLeft) {
					g.drawImage(towerLAttacking, this.getX(), this.getY(), 32, 32, null);
				}
			}
		    }else if(width == 16 && height == 16) {
		    	if(isAtRight) {
					g.drawImage(littletowerR, this.getX(), this.getY(), 16, 16, null);
				}
				else if(isAtLeft) {
					g.drawImage(littletowerL, this.getX(), this.getY(), 16, 16, null);
				}
		    }
	}
	
	


}




