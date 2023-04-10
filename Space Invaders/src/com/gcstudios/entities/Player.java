package com.gcstudios.entities;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.gcstudios.graficos.UI;
import com.gcstudios.main.Game;

public class Player extends Entity{

	public static BufferedImage player = Game.spritesheet.getSprite(0, 0, 16, 16);
	public BufferedImage[] fireBehindWave;
	
	public int index = 0;
	public int maxIndex = 2;
	public int frames = 0;
	public int maxFrames = 5;
	
	public static boolean right, left;
		
	public static int life = 5;
	
	public static int score = 0;
	
	public static String Name;
	
	public static boolean startShootCounter = false;
	public static int delay = 0;
	public static int maxDelay = 2;
	
	public static boolean shoot = false;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		fireBehindWave = new BufferedImage[3];
		
		fireBehindWave[0] = Game.spritesheet.getSprite(0, 16, 16, 4);
		fireBehindWave[1] = Game.spritesheet.getSprite(0, 20, 16, 5);
		fireBehindWave[2] = Game.spritesheet.getSprite(0, 25, 16, 5);
		
	}
	
	public void tick(){
		
		depth = 2;
		
		if(delay == 0) {
		if(shoot) {
			Game.canShoot = false;
			startShootCounter = true;
			shoot = false;
			Shoot shoot = new Shoot(this.getX() + 2, this.getY() + 2, 1, 2, 1, null);
			Game.entities.add(shoot);
			Shoot shoot2 = new Shoot(this.getX() + 13, this.getY() + 2, 1, 2, 1, null);
			Game.entities.add(shoot2);
		}
		}
		if(startShootCounter) {
			delay++;
	
		}
		
		if(delay > maxDelay) {
			delay = 0;
			startShootCounter = false;
		}
		
		if(right) {
			x++;
		}
		else if(left) {
			x--;
		}
		
		if(x > Game.WIDTH) {
			x = -16;
		}
		if(x + 16 < 0) {
			x = Game.WIDTH;
		}
		
		
		frames++;
		if(frames > maxFrames) {
			index++;
			frames = 0;
		}
		if(index > maxIndex) {
			index = 0;
		}
	}
	
	public static void saveGame(String name, int Score, String time) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("points.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		time = UI.time;
		name = Name;
		Score = score;
		
		try {
			write.write(time);
		}catch(IOException e) {
			
		}
	
	try {
		write.flush();
		write.close();
	}catch(IOException e) {}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(player, this.getX(), this.getY(), 16, 16, null);
		g.drawImage(fireBehindWave[index], this.getX(), this.getY() + 16, 16, 5, null);
	}

	

	


}
