package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Camera;
import com.gcstudios.world.Vector2i;



public class Enemy2 extends Entity{
	
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private BufferedImage[] upEnemy;
	private BufferedImage[] downEnemy;
	
	private int index = 0;
	private int frames = 0;
	private int maxFrames = 8;
	private int maxIndex = 1;
	
	public static boolean ghostMode = false;
	public int ghostFrames;

	public Enemy2(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		rightEnemy = new BufferedImage[2]; 
		leftEnemy = new BufferedImage[2]; 
		upEnemy = new BufferedImage[2]; 
		downEnemy = new BufferedImage[2]; 
		
		rightEnemy[0] = Game.spritesheet.getSprite(0, 0, 16, 16);
		rightEnemy[1] = Game.spritesheet.getSprite(16, 0, 16, 16);
		
		leftEnemy[0] = Game.spritesheet.getSprite(32, 32, 16, 16);
		leftEnemy[1] = Game.spritesheet.getSprite(48, 32, 16, 16);
		
		upEnemy[0] = Game.spritesheet.getSprite(96, 32, 16, 16);
		upEnemy[1] = Game.spritesheet.getSprite(112, 32, 16, 16);
		
		downEnemy[0] = Game.spritesheet.getSprite(64, 48, 16, 16);
		downEnemy[1] = Game.spritesheet.getSprite(80, 48, 16, 16);
	}

	public void tick(){
		depth = 1;
		
		
		if(!ghostMode) {
		if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
				path = AStar.findPath(Game.world, start, end);
			}
		
			if(new Random().nextInt(100) < 90)
				followPath(path);
			
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 10) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
			}
		}
			
			
				frames++;
				if(frames >= maxFrames) {
					frames = 0;
					index++;
					if(index > maxIndex)
						index = 0;
				}
				
				if(ghostMode) {
				    ghostFrames++;
				    if(ghostFrames >= 60*5) {
				    	ghostFrames = 0;
				    	ghostMode = false;
				    }
					}
				
				if(!ghostMode) {
					if(!Game.player.invencibleMode) {
					if(isCollidingWithPlayer()) {
						Game.player.stock-=1;
						Game.player.wasDied = true;
					}
					}
				}

	}
	
	 public boolean isCollidingWithPlayer(){
	    	Rectangle enemyCurrent = new Rectangle(this.getX(), this.getY(), 16, 16);
	    	Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(), 16, 16);
	    	return enemyCurrent.intersects(player);
	    }
	

	
	public void render(Graphics g) {
		
		if(!ghostMode) {
		g.drawImage(downEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else {
		g.drawImage(Entity.GhostMode, this.getX() - Camera.x, this.getY() - Camera.y, null);	
		}
	}
	
	
}
