package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Target extends Entity{
	
	public Target(double x, double y, int width, int height){
		super(x, y, width, height, 0, null);
		
		this.x = x +8;
		this.y = y +8;
		this.width = width - 8;
		this.height = height - 8;
		
		
	}
	
	

}
