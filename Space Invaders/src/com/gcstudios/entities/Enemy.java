package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Enemy extends Entity{
	
	public BufferedImage enemy = Game.spritesheet.getSprite(16, 0, 16, 16);
	public BufferedImage[] fireBehindWave;
	
	public int index = 0;
	public int maxIndex = 2;
	public int frames = 0;
	public int maxFrames = 5;
	
	public static boolean right, left;
	
	public double life = 3;
	public double maxLife = 3;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, null);
		
        fireBehindWave = new BufferedImage[3];
		
		fireBehindWave[0] = Game.spritesheet.getSprite(16, 16, 16, 5);
		fireBehindWave[1] = Game.spritesheet.getSprite(16, 20, 16, 5);
		fireBehindWave[2] = Game.spritesheet.getSprite(16, 25, 16, 4);
		
	}
	
	public void tick() {
		frames++;
		if(frames > maxFrames) {
			index++;
			frames = 0;
		}
		if(index > maxIndex) {
			index = 0;
		}
		
		y+=speed;
		
		if(y > Game.HEIGHT +5) {
			
			Player.life -= 1;
			Game.entities.remove(this);
			
		}
		
		checkCollisionShoot();
		
		if(life <= 0) {
			Game.entities.remove(this);
			Player.score+= 10;
		}
		
	}
	
	public void checkCollisionShoot() {
	   for(int i = 0; i < Game.entities.size(); i++) {
		   Entity e = Game.entities.get(i);
		   if(e instanceof Shoot) {
			   if(Entity.isColidding(this, e)) {
				   this.life -= 1;
				   Game.entities.remove(e);
			   }
		   }
	   }
	}
	
	public void render(Graphics g) {
		g.drawImage(enemy, this.getX(), this.getY(), 16, 16, null);
		g.drawImage(fireBehindWave[index], this.getX(), this.getY() - 5, 16, 5, null);
		
		g.setColor(Color.blue);
		g.fillRect(this.getX(), this.getY() + 18, 16, 1);
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY() + 18, (int)((life/maxLife)*16), 1);
	}
	
	


}
