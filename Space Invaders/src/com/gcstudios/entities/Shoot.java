package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Shoot extends Entity{
	
	
	public Shoot(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		y--;
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(this.getX(), this.getY(), 1, 2);
	}
	
}
