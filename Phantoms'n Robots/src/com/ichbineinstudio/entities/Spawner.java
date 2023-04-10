package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;

public class Spawner extends Entity{
	
	private int timer = 60;
	private int timerCounter = 0;
	
	private int time = 0;
	public static int timeOfMatch = 0;
	private int sec = 0, min = 0;

	public static String showTime = "";
	public static String[] finalTime = {"00 : 30", "00 : 45", "01 : 00", "01 : 20", "01 : 30", "01 : 45", "99 : 99", "99 : 99"};
	
	public static int numberOfDeadPhantoms = 0;
	public static int[] maxKills = {999, 999, 999, 999, 999, 999, 100, 150};

	public Spawner(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public static int toTicks() {
		return (Integer.parseInt(finalTime[Game.CUR_LEVEL - 1].substring(0, 1+1)))*3600 + (Integer.parseInt(finalTime[Game.CUR_LEVEL - 1].substring(5, 6+1)))*60;
	}
	

	
	public void tick() {
		
		timeOfMatch++;
		
		String showTime = "";
		
		if(timeOfMatch % 60 == 0) {
			sec += 1;
			if(sec % 60 == 0) {
				min += 1;
				sec = 0;
			}
		}
		
		if(min < 10) {
			showTime += "0" + min + " : ";
		}else {
			showTime += min + " : ";
		}
		if(sec < 10) {
			showTime += "0" + sec;
		}else {
			showTime += sec;
		}
		
		
		this.showTime = showTime;
		
		//System.out.println(timeOfMatch);
		//System.out.println(time);
		
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
				if(time < 800) {
					time++;
				}
			}
			if(timeOfMatch < 10000) {
				timeOfMatch++;
			}
		}
		else {
		
			//time = tempo que define a frequência de spawn de inimigos
			if(Game.CUR_LEVEL == 1 || Game.CUR_LEVEL == 2 || Game.CUR_LEVEL == 3) {
				if(time < 300) {
				time++;
				}
			}else if(Game.CUR_LEVEL >= 4 && Game.CUR_LEVEL <= 5) {
				if(time < 500) {
					time++;
				}
			}else if(Game.CUR_LEVEL == 6) {
				if(time < 400) {
					time++;
				}
			}else if(Game.CUR_LEVEL >= 7 && Game.CUR_LEVEL <= 8) {
				if(time < 500) {
					time++;
				}else if(time > 700 && time < 850) {
					time++;
				}
			}
			
			//timeOfMatch = tempo que se usa para finalizar o nível
			if(min*3600+sec*60 == toTicks()) {
				time = 0;
				timeOfMatch = 0;
				Game.updateLvl = true;
			}
		}
		
		
		
		
		/*if(Game.CUR_LEVEL == 1) {  
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
		else if(Game.CUR_LEVEL == 4 || Game.CUR_LEVEL == 5 || Game.CUR_LEVEL == 6) {
			if(timeOfMatch < 4000) {
				timeOfMatch++;
			}
			else {
				timeOfMatch = 0;
				Game.updateLvl = true;
			}
			
		}
		else if(Game.CUR_LEVEL == 7 || Game.CUR_LEVEL == 8) {
			if(timeOfMatch < 4000) {
				timeOfMatch++;
			}
			if(this.numberOfDeadPhantoms >= 100) {
				Game.updateLvl = true;
			}
		} */
		timerCounter++;
		if(timerCounter >= timer) {
			timerCounter = 0;
			timer = (Entity.rand.nextInt(90 - 60) + 60)-(int)(time*0.1);
			//Criar inimigo
			if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
			Enemy enemy = new Enemy(x,y,16,16,Entity.rand.nextDouble(),1,Entity.ENEMY1_RIGHT);
			Game.entities.add(enemy);
			}else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
				Enemy enemy2 = new Enemy(x,y,16,16,Entity.rand.nextDouble(),2,Entity.ENEMY1_RIGHT);
				Game.entities.add(enemy2);	
			}
		}
	}
	
	public void render(Graphics g) {
		if(Game.numberOfPlayers == 1) {
			if(!Game.introduct) {
				if(Spawner.maxKills[Game.CUR_LEVEL - 1] == 999) {
					g.setColor(new Color(40, 40, 40));
					g.fillRect(Game.WIDTH/2-26, 9, 52, 13);
					g.setColor(new Color(190, 190, 190));
					g.fillRect(Game.WIDTH/2-25, 10, 50, 11);
					g.setColor(new Color(40, 40, 40));
					g.setFont(new Font("Uroob", Font.BOLD, 15));
					g.drawString(showTime, Game.WIDTH/2-19, 20);
				}else if(Spawner.toTicks() != 362340) {
					g.setColor(new Color(40, 40, 40));
					g.fillRect(Game.WIDTH/2-26, 9, 52, 13);
					g.setColor(new Color(190, 190, 190));
					g.fillRect(Game.WIDTH/2-25, 10, 50, 11);
					g.setColor(new Color(40, 40, 40));
					g.setFont(new Font("Uroob", Font.BOLD, 15));
					g.drawString(Integer.toString(this.numberOfDeadPhantoms)+" / "+Integer.toString(maxKills[Game.CUR_LEVEL - 1]), Game.WIDTH/2-22, 20);
				}
			}
		}
		/*g.setColor(Color.red);
		g.fillRect(this.getX(),this.getY(),16,16);*/
	}

}
