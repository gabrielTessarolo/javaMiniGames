package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.Camera;
import com.ichbineinstudio.world.World;


public class SpeedDevice extends Entity{
	
	public int index = 0;
	public int frames = 0;
	public int maxFrames = 20;
	public int maxIndex = 2;
	
	public static BufferedImage[] speedDevice1;
	
	public static BufferedImage[] heart;
	
	public int xTarget, yTarget;
	public boolean isAttacking = false;
	
	private int timer = 60;
	private int timerCounter = 0;
	
	public SpeedDevice(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		speedDevice1 = new BufferedImage[3];
		
	    speedDevice1[0] = Game.spritesheet.getSprite(14*16, 9*16, 16, 16);
	    speedDevice1[1] = Game.spritesheet.getSprite(14*16, 10*16, 16, 16);
	    speedDevice1[2] = Game.spritesheet.getSprite(14*16, 11*16, 16, 16);
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
			
		
		
		frames++;
		if(frames >= maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex)
				index = 0;
		}
		
		
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(speedDevice1[index], this.getX(), this.getY() - Camera.y, 16, 16, null);
		
	}
	
	


}
