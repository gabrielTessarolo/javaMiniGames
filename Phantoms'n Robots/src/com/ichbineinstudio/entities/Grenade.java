package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.World;

public class Grenade extends Entity{
	
	public int xTarget, yTarget;
	
	public static boolean thereIsAnEnemy = false;
	
	private double dx;
	private double dy;
    private double spd = 2;
    private int Durability = 25, curDurability = 0;
    
    private BufferedImage grenade;

	
    public Grenade(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, 1, sprite);
		this.dx = dx;
		this.dy = dy;
		
		grenade = Game.spritesheet.getSprite(4*16, 6*16, 16, 16);

		
	}

	public void tick() {
		//Para que o jogador possa atirar entre as paredes, comente as próximas 1ª, 4ª, 5ª, 6ª e 7ª linhas de código.
		
		
		/*	for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {*/
			//		xTarget = Player4.xTarget;
			//		yTarget = Player4.yTarget;
		//		}
		//	}
		
		
		
		   if(xTarget < x) {
			   x -= spd;
		   }else if(xTarget >= x) {
			   x += spd;
		   }
		   if(yTarget < y) {
			   y -= spd;
		   }else if(yTarget >= y) {
			   y += spd;
		   }
		   
		   if((x > xTarget - 5 || x < xTarget + 5) && (y > yTarget - 5 || y < yTarget + 5)) {
			//   explode = true;
		   }
			
	
		
		curDurability++;
		if(curDurability == Durability) {
			Game.arrows.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
	    //if(xTarget < this.getX()){
	//    g.drawImage(arrowL, this.getX(), this.getY(), 16, 16, null);	
	    /*}
	    else {
	    g.drawImage(arrowR, this.getX(), this.getY(), 16, 16, null);	
	    }*/
	}
	
}
	