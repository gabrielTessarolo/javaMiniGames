package com.gtstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gtstudios.main.Game;

public class Hole extends Entity{
	
	public static BufferedImage img;

	public Hole(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		this.x = x; this.y = y; this.width = width; this.height = height;
		img = Game.spritesheet.getSprite(16, 0, 16, 32);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(img, this.getX(), this.getY()-height, width, width*2, null);
	}
}
