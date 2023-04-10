package com.gtstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gtstudios.main.Game;

public class Controller extends Entity{

	public static int life = 5;
	public static int xPos, yPos;
	public static int selected = -1;
	public static boolean clicked = false, canClick = true;
	
	public static double force = 0, maxForce = 100;
	public static double inc = 0.5, dec = -0.5, action = inc;
	public static int selecting = 1, ignoring = -1, playing = 0, phase = ignoring;
	
	public static boolean canChangeDir = true;
	
	public Controller(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		this.width = width; this.height = height;
	}
	
	public static boolean someBallIsSlc(boolean remove) {
		for(int i = 0; i < Game.entities.size(); i ++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Ball && ((Ball) e).isSlc) {
				if(remove == true) {((Ball) e).isSlc = false;}
				else{return true;}
			}
		}
		return false;
	}
	
	public void tick() {
		
		//System.out.println(force);
		
		if(phase != playing) {
			if(someBallIsSlc(false)) {
				if(force >= maxForce) {action = dec;}else if(force <= 0){action = inc;}
				force += action;
			}else {force = 0;}	
		}

		//System.out.println(x+" | "+y);*/
		x = xPos; y = yPos;
		if(clicked) {
			clicked = false;
			if(phase == selecting) {
				try {
					Game.slcs.remove(0);
				}catch(Exception e) {}
				
				Selector slc = new Selector(this.getX(), this.getY(), 16, 16, 0, null);
				Game.entities.add(slc);
				Game.slcs.add(slc);
				
			}else if(phase == ignoring) {
				try {
					Game.slcs.remove(0);
				}catch(Exception e) {}
				someBallIsSlc(true);
			}else if(phase == playing) {
				canChangeDir = true;
				//canClick = false;
			}
		}
		if(phase == playing) {
			canClick = false;
			try {
				Game.slcs.remove(0);
				}catch(Exception e) {}
			if(force >= 0.2) {force -= 0.2;}
			if(force < 0.2) {phase = ignoring; canClick = true;}
		}
		
	}
	
	public void render(Graphics g) {
		if(canClick) {
			g.setColor(new Color(255, 204, 51));
			g.fillRect(this.getX()+6, this.getY(), 4, 16);
			g.fillRect(this.getX(), this.getY()+6, 16, 4);
		}
	}

}
