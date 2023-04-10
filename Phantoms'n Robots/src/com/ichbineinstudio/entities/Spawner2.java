package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;

public class Spawner2 extends Entity{
	
	private int timer = 60;
	private int timerCounter = 0;
	
	private int time = 0;
	public static int timeOfMatch = 0;

	public Spawner2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		if(Game.numberOfPlayers == 2) {
			if(timeOfMatch < 1000) {
			if(time < 300) {
				time++;
			}
			}else if(timeOfMatch >= 2000 && timeOfMatch < 3000) {
				if(time < 500) {
					time++;
				}
			}else if(timeOfMatch >= 4000 && timeOfMatch < 5000) {
				if(time < 620) {
					time++;
				}
			}else if(timeOfMatch >= 8000 && timeOfMatch < 10000) {
				if(time < 720) {
					time++;
				}
			}
			if(timeOfMatch < 10000) {
				timeOfMatch++;
			}
			
		}
		if(Game.CUR_LEVEL == 1 || Game.CUR_LEVEL == 2 || Game.CUR_LEVEL == 3) {
		if(time < 300) {
		time++;
		}
		}else if(Game.CUR_LEVEL == 4 || Game.CUR_LEVEL == 5) {
			if(time < 500) {
				time++;
		}
		}
			
		if(Game.CUR_LEVEL == 1) {
		if(timeOfMatch < 2000) {
			timeOfMatch++;
		}
		else {
			time = 0;
			timeOfMatch = 0;
			Game.updateLvl = true;
		}
		}
		else if(Game.CUR_LEVEL == 2 || Game.CUR_LEVEL == 3) {
			if(timeOfMatch < 3000) {
				timeOfMatch++;
			}
			else {
				timeOfMatch = 0;
				Game.updateLvl = true;
			}
		}
		else if(Game.CUR_LEVEL == 4 || Game.CUR_LEVEL == 5) {
			if(timeOfMatch < 4000) {
				timeOfMatch++;
			}
			else {
				timeOfMatch = 0;
				Game.updateLvl = true;
			}
			
		}
		timerCounter++;
		if(timerCounter >= timer) {
			timerCounter = 0;
			timer = (Entity.rand.nextInt(90 - 60) + 60)-(int)(time*0.1);
			//Criar inimigo
			if(Game.CUR_LEVEL == 0) {
			Enemy_path2 enemy_path2 = new Enemy_path2(x,y,16,16,Entity.rand.nextDouble(),1,Entity.ENEMY1_RIGHT);
			Game.entities.add(enemy_path2);
			}else if(Game.CUR_LEVEL == -1) {
				Enemy_path2 enemy_path2_2 = new Enemy_path2(x,y,16,16,Entity.rand.nextDouble(),2,Entity.ENEMY1_RIGHT);
				Game.entities.add(enemy_path2_2);	
			}
		}
	}
	
	public void render(Graphics g) {
		/*g.setColor(Color.red);
		g.fillRect(this.getX(),this.getY(),16,16);*/
	}

}

