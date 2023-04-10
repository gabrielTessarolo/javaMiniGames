package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.graficos.UI;
import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.AStar;
import com.ichbineinstudio.world.Camera;
import com.ichbineinstudio.world.Vector2i;
import com.ichbineinstudio.world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
    public double life = 3;
    public double maxLife = 3;
    
    public double pathSpeed = 100;
	
	public int index = 0;
	public int frames = 0;
	public int maxFrames = 20;
	public int maxIndex = 1;
	
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	
	private BufferedImage[] frozenEnemy;
	
	private BufferedImage[] rightEnemy2;
	private BufferedImage[] leftEnemy2;
	
	private BufferedImage[] frozenEnemy2;
	
	public int type = 1;

	public Enemy(double x, double y, int width, int height, double speed, int type, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		this.type = type;
		
		if(this.type == 1) {
			
		}else if(this.type == 2) {
			life = 5;
			maxLife = 5;
		}else if(this.type == 3) {
			
		}
		
		path = AStar.findPath(Game.world,new Vector2i(World.xINITIAL,World.yINITIAL),new Vector2i(World.xFINAL,World.yFINAL));
		//path2 = AStar.findPath(Game.world,new Vector2i(World.xINITIAL2,World.yINITIAL2),new Vector2i(World.xFINAL2,World.yFINAL2));

		rightEnemy = new BufferedImage[2]; 
		leftEnemy = new BufferedImage[2]; 
		
		frozenEnemy = new BufferedImage[2];
		
		rightEnemy[0] = Game.spritesheet.getSprite(2*16, 0*16, 16, 16);
		rightEnemy[1] = Game.spritesheet.getSprite(2*16, 1*16, 16, 16);
		
		leftEnemy[0] = Game.spritesheet.getSprite(3*16, 0*16, 16, 16);
		leftEnemy[1] = Game.spritesheet.getSprite(3*16, 1*16, 16, 16);
		
		frozenEnemy[0] = Game.spritesheet.getSprite(3*16, 0*16, 16, 16);
		frozenEnemy[1] = Game.spritesheet.getSprite(3*16, 1*16, 16, 16);
		
		rightEnemy2 = new BufferedImage[2]; 
		leftEnemy2 = new BufferedImage[2]; 
		
		frozenEnemy2 = new BufferedImage[2];
		
		rightEnemy2[0] = Game.spritesheet.getSprite(2*16, 2*16, 16, 16);
		rightEnemy2[1] = Game.spritesheet.getSprite(2*16, 3*16, 16, 16);
		
		leftEnemy2[0] = Game.spritesheet.getSprite(3*16, 0*16, 16, 16);
		leftEnemy2[1] = Game.spritesheet.getSprite(3*16, 1*16, 16, 16);
		
		frozenEnemy2[0] = Game.spritesheet.getSprite(3*16, 2*16, 16, 16);
		frozenEnemy2[1] = Game.spritesheet.getSprite(3*16, 3*16, 16, 16);
		
		
		
	}
	
	public static void gradIncrease(int i, int f) {		
		while(i < f) {
			if(Entity.rand.nextInt(100) <= 10) { i += 1; }
		}	
	}
	
	public void tick() {
		
/*		if(Game.numberOfPlayers == 1) {
		if(Entity.rand.nextInt(100) < pathSpeed) {	
		followPath(path);
		}
		}else if(Game.numberOfPlayers == 2) {
		if(this.getX() >= World.xFINAL) {*/
			if(Entity.rand.nextInt(100) < pathSpeed) {	
				followPath(path);
				}
/*		}else {
			if(Entity.rand.nextInt(100) < pathSpeed) {	
				followPath(path2);
				}
		}
		}*/
			
        for(int i = 0; i < Game.entities.size(); i++) {
        	
        	Entity targ = Game.entities.get(i);
        	if(targ instanceof Target) {
		    if(Entity.isColidding(this, targ)) {
		    	if(Game.numberOfPlayers == 1) {
			Game.entities.remove(this);
			Game.life -= 1;
			Player.playerWasHurt = true;
			return;
		    	}
		}
        }
        }
		
		
		/*if(Game.CUR_LEVEL == 1 || Game.CUR_LEVEL == 2) {
		if(x >= Game.WIDTH) {
			//Perdendo vida
			Game.entities.remove(this);
			Game.life -= 1;
			Player.playerWasHurt = true;
			return;
		}
		}else if(Game.CUR_LEVEL == 3){
		if(x == 16*16 && y == 7*16) {
			Game.entities.remove(this);
			Game.life -= 1;
			Player.playerWasHurt = true;
			return;
		}
		}else if(Game.CUR_LEVEL == 4){
			if(x == 12*16 && y == 3*16) {
				Game.entities.remove(this);
				Game.life -= 1;
				Player.playerWasHurt = true;
				return;
			}
		}else if(Game.CUR_LEVEL == 5){
			if(x == 15*16 && y == 9*16) {
				Game.entities.remove(this);
				Game.life -= 1;
				Player.playerWasHurt = true;
				return;
			}
		}*/
		frames++;
		if(frames >= maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
		if(Game.numberOfPlayers == 1) {
		if(life <= 0) {
			Game.entities.remove(this);
			Spawner.numberOfDeadPhantoms+=1;
			Game.money += 10;
			
		}
		}else if(Game.numberOfPlayers == 2) {
			if(life <= 0) {
				if(this.getX() >= 8*16) {
				Game.entities.remove(this);
				TowerController.money += 10;
				}
				else {
					Game.entities.remove(this);
					TowerController2.money += 10;	
				}
			}	
		}
		
		
		if(Game.numberOfPlayers == 2) {
			for(int i = 0; i < Game.entities.size(); i++) {
	        	
	        	Entity targ = Game.entities.get(i);
	        	if(targ instanceof Target) {
	        		if(Game.CUR_LEVEL != 0) {
			    if(Entity.isColidding(this, targ)) {
			    	
				Game.entities.remove(this);
				TowerController.life -= 1;
				Player.playerWasHurt = true;
				return;
			    }
			}
	        }
	        }
			if(Game.CUR_LEVEL == 0) {
			if(this.getY() - Camera.y >= Game.HEIGHT) {
				Game.entities.remove(this);
				TowerController.life-=1;
				Player.playerWasHurt = true;
				return;
				
			}
		}else if(Game.CUR_LEVEL == -1) {
            if(x == 16*16 && y == 4*16) {
				Game.entities.remove(this);
				TowerController.life-=1;
				Player.playerWasHurt = true;
				return;
			}
		}
		}
		
	}
	
	public void render(Graphics g) {
		if(type == 1) {
		if(pathSpeed == 100) {
			
		if(right) {
		g.drawImage(rightEnemy[index], this.getX(), this.getY() - Camera.y, 16, 16, null);
		}
		else if(left) {
		g.drawImage(leftEnemy[index], this.getX(), this.getY() - Camera.y, 16, 16, null);	
		}
		}else {
	    g.drawImage(frozenEnemy[index], this.getX(), this.getY() - Camera.y, 16, 16, null);	
		}
		}else if(type == 2){
			if(pathSpeed == 100) {
				
				if(right) {
				g.drawImage(rightEnemy2[index], this.getX(), this.getY() - Camera.y, 16, 16, null);
				}
				else if(left) {
				g.drawImage(leftEnemy2[index], this.getX(), this.getY() - Camera.y, 16, 16, null);	
				}
				}else {
			    g.drawImage(frozenEnemy2[index], this.getX(), this.getY() - Camera.y, 16, 16, null);	
				}
			
		}
		g.setColor(Color.white);
		g.fillRect((this.getX() - 1), (this.getY() - Camera.y - 5), 18, 5);
		g.setColor(new Color(80, 80, 80, 130));
		g.fillRect((this.getX()), (this.getY() - Camera.y - 4),16,3);
		
		g.setColor(Color.red);
		g.fillRect((this.getX()), (this.getY() - Camera.y - 4),(int)((this.life/this.maxLife)*16),3);
		
		}
	
	
	
	

}
