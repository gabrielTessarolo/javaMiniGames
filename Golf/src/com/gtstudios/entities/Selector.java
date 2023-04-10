package com.gtstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Selector extends Entity{

	public Selector(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		this.x = x; this.y = y; this.width = width; this.height = height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(this.getX(), this.getY(), width, height);
	}

}
