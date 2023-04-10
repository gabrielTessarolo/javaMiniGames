package com.gtstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gtstudios.graficos.ImageFlipping;
import com.gtstudios.main.Game;

public class Ball extends Entity{
	
	public double speed, dx,dy;
	public BufferedImage[] imgs;
	public boolean isSlc = false;
	public static boolean moving = false, canChangeDir = true, canMove = false;
	public static double imgIndex = 0, maxIndex = 4;
	
	public Ball(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, null);
		this.x = x; this.y = y; this.width = width; this.height = height;
		imgs = new BufferedImage[4];
		imgs[0] = Game.spritesheet.getSprite(0, 0, 16, 16);
		imgs[1] = ImageFlipping.flip(imgs[0], ImageFlipping.FLIP_HORIZONTAL);
		imgs[2] = ImageFlipping.flip(imgs[1], ImageFlipping.FLIP_VERTICAL);
		imgs[3] = ImageFlipping.flip(imgs[0], ImageFlipping.FLIP_VERTICAL);
		this.speed = speed;
		
	}
	
	public double[] knockback(double x, double y, double dx, double dy, double speed) {
		double nx = x+dx*speed; double ny = y+dy*speed;
		if(nx >= Game.WIDTH - 16 || nx <= 0) {dx *= -1;}
		if(ny >= Game.HEIGHT - 16 || ny <= 0) {dy *= -1;}
		double fdx = dx; double fdy = dy; double[] fl = {fdx, fdy};
		return fl;
	}
	
	public void tick() {
		
		for(int i = 0; i < Game.holes.size(); i++) {
			Hole h = Game.holes.get(i);
			if(h instanceof Hole) {
				if(Entity.isColiddingWithHole(this, h)) {
					Game.entities.remove(this);
					Game.balls.remove(this);
					Game.control.force = 0;
				}
			}
		}
		collidingSlc();
		if(isSlc && speed > 0) {
			//Quando clico, a bolinha para de andar
			//Mais de uma bolinha movendo de uma só vez
			if(Game.control.phase == Game.control.playing) {
				//Velocidade diminuir com o tempo.
				if(Game.control.canChangeDir) {
					Game.control.canChangeDir = false;
					
					var radius = Math.atan2(Game.control.getY() - y, Game.control.getX() - x);
					dx = Math.cos(radius);
					dy = Math.sin(radius);
				}
				canMove = true;
			}
		}
	
		//System.out.println(isSlc);
		
		if(canMove) {
			isSlc = false;
			speed = Game.control.force/25;
			moving = true;
			dx = knockback(x, y, dx, dy, speed)[0];
			dy = knockback(x, y, dx, dy, speed)[1];
			x += dx*speed;
			y += dy*speed;
			
			if(speed <= 0.2/25) {
				speed = 0;
			}
			if(speed == 0) {
				canMove = false;
			}
			imgIndex += speed/50;
			if(imgIndex >= maxIndex) {imgIndex = 0;}
			speed = 4;
		}
		
		
		//SISTEMA DE CANCLICK NAO TA DANDO CERTO
		
		if(speed == 0) {Game.control.canClick = true; isSlc = false;}
		//if(moving == true) {speed-=0.2;}else {speed = 4;}
		//if(speed <= 0) {moving = false;}
		
	}

	public void collidingSlc() {
		try {
			for(int i = 0; i < Game.entities.size(); i++) {
				Selector e = Game.slcs.get(i);
				if(Entity.isColidding(e, this)) {
					isSlc = true;
				}else {
					if(Game.control.phase != Game.control.playing) {
						isSlc = false;
					}
				}
			}
		}catch(Exception e) {}
	}
	
	
	
	public void render(Graphics g) {
	
		if(isSlc) {
			g.setColor(new Color(255, 204, 51));
			g.fillOval(pos("x")-2, pos("y")-2, 19, 19);
			g.drawLine(pos("x")+8, pos("y")+8, Game.control.getX()+8, Game.control.getY()+8);
		}
		
		g.drawImage(imgs[(int)(imgIndex)], pos("x"), pos("y"), 16, 16, null);
	}

}
