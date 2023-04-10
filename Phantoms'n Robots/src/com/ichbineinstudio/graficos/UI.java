package com.ichbineinstudio.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.Player;
import com.ichbineinstudio.entities.Player4;
import com.ichbineinstudio.entities.Player_path2;
import com.ichbineinstudio.entities.Spawner;
import com.ichbineinstudio.entities.TowerController;
import com.ichbineinstudio.entities.TowerController2;
import com.ichbineinstudio.main.Game;

public class UI {
	
	public static int index = 0;
	public static int frames = 0;
	public static int maxFrames = 20;
	public static int maxIndex = 1;
	
	
	private int timer = 120;
	private int timerCounter = 0;
	public int explosionIndex = 0;
	public int explosionframes = 0;
	public int explosionmaxFrames = 6;
	public int explosionmaxIndex = 2;
	private static BufferedImage[] explosion;
	
	public static BufferedImage[] player;
	public static BufferedImage[] playerHurt;
	
	public static BufferedImage[] player2;
	public static BufferedImage[] playerHurt2;
	
	public static BufferedImage[] iconTower;
	public static BufferedImage[] devices;
	
	public static BufferedImage[] heart;
	public static BufferedImage money;
	
	public static int[] prices = {25, 50, 150, 250, 0, 200, 250, 500};

	
	public void tick() {
		
		if(timerCounter > timer - 20) {
			explosionframes++;
			if(explosionframes >= explosionmaxFrames) {
				explosionframes = 0;
				explosionIndex++;
				if(explosionIndex > explosionmaxIndex)
					explosionIndex = 0;
			}
		}
		
		
		frames++;
		if(frames >= maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex)
				index = 0;
		}
		
		if(Player.playerWasHurt) {
			Player.playerHurtCounter++;
			if(Player.playerHurtCounter >= 100) {
				Player.playerHurtCounter = 0;
				Player.playerWasHurt = false;
			}
		}
		if(Player_path2.playerWasHurt) {
			Player_path2.playerHurtCounter++;
			if(Player_path2.playerHurtCounter >= 100) {
				Player_path2.playerHurtCounter = 0;
				Player_path2.playerWasHurt = false;
			}
		}
	}
	
	

	public void render(Graphics g) {
		
		player = new BufferedImage[2];
		playerHurt = new BufferedImage[2];
		player2 = new BufferedImage[2];
		playerHurt2 = new BufferedImage[2];
		heart = new BufferedImage[2];
		
		iconTower = new BufferedImage[4];
		devices = new BufferedImage[3];
		
		player[0] = Game.spritesheet.getSprite(6*16, 0, 32, 32);
		player[1] = Game.spritesheet.getSprite(6*16, 2*16, 32, 32);
		
		playerHurt[0] = Game.spritesheet.getSprite(8*16, 0, 32, 32);
		playerHurt[1] = Game.spritesheet.getSprite(8*16, 2*16, 32, 32);
		
		player2[0] = Game.spritesheet.getSprite(12*16, 0, 32, 32);
		player2[1] = Game.spritesheet.getSprite(12*16, 2*16, 32, 32);
		
		playerHurt2[0] = Game.spritesheet.getSprite(10*16, 0, 32, 32);
		playerHurt2[1] = Game.spritesheet.getSprite(10*16, 2*16, 32, 32);
		
		heart[0] = Game.spritesheet.getSprite(6*16, 4*16, 16, 16);
		heart[1] = Game.spritesheet.getSprite(7*16, 4*16, 16, 16);
		
		explosion = new BufferedImage[3];

		explosion[0] = Game.spritesheet.getSprite(5*16, 12*16, 16, 16);
		explosion[1] = Game.spritesheet.getSprite(5*16, 13*16, 16, 16);
		explosion[2] = Game.spritesheet.getSprite(5*16, 14*16, 16, 16);
		
		money = Game.spritesheet.getSprite(8*16, 4*16, 16, 16);
		
		iconTower[0] = Game.spritesheet.getSprite(4*16, 8*16, 16, 16);
		iconTower[1] = Game.spritesheet.getSprite(5*16, 8*16, 16, 16);
		iconTower[2] = Game.spritesheet.getSprite(4*16, 9*16, 16, 16);
		iconTower[3] = Game.spritesheet.getSprite(5*16, 7*16, 16, 16);
		devices[0] = Game.spritesheet.getSprite(5*16, 9*16, 16, 16);
		devices[1] = Game.spritesheet.getSprite(5*16, 11*16, 16, 16);
		devices[2] = Game.spritesheet.getSprite(4*16, 11*16, 16, 16);
		
		if(Game.numberOfPlayers == 1) {
			
		int kw = Toolkit.getDefaultToolkit().getScreenSize().width/240/3;
		int kh = Toolkit.getDefaultToolkit().getScreenSize().height/160/3;
		
		for(int i = 0; i < Game.life; i++) {
			g.drawImage(heart[index], 668,  130 + (i*40), 32, 32, null); 
		}

		if(TowerController.hotbar == 1) {
			
			int ixp = 230, iyp = 420;
			int[] imPos = {234, 285, 336, 390};
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(ixp, iyp, 264, 56);
			
			for(int i = 0; i < 5; i++) {
				if(i != 4) {
					if(TowerController.towerSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
						g.setFont(new Font("Uroob", Font.ITALIC, 32));
						g.drawString("$ "+Integer.toString(prices[i]), ixp+i*52+4, iyp);
					}else {
						g.setColor(Color.DARK_GRAY);
						g.setFont(new Font("Uroob", Font.BOLD, 20));
						g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					}
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(iconTower[i], imPos[i], iyp+4, 48, 48, null);
				}else {
					if(TowerController.towerSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
					}
					g.setColor(Color.DARK_GRAY);
					g.setFont(new Font("Uroob", Font.BOLD, 20));
					g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(Game.spritesheet.getSprite(4*16, 12*16, 16, 16), imPos[3]+52, iyp+4, 48, 48, null);
				}
				
			}
		
		
		
		
		
		
			
		/*for(int i = 0; i < Game.life; i++) {
			g.drawImage(heart[index], 668,  130 + (i*40), 32, 32, null); 
		}

		if(TowerController.hotbar == 1) {
			
			int ixp = 230, iyp = 420;
			int[] imPos = {234, 285, 336, 390};
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(ixp, iyp, 264, 56);
			
			for(int i = 0; i < 5; i++) {
				if(i != 4) {
					if(TowerController.towerSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
						g.setFont(new Font("Uroob", Font.ITALIC, 32));
						g.drawString("$ "+Integer.toString(prices[i]), ixp+i*52+4, iyp);
					}else {
						g.setColor(Color.DARK_GRAY);
						g.setFont(new Font("Uroob", Font.BOLD, 20));
						g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					}
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(iconTower[i], imPos[i], iyp+4, 48, 48, null);
				}else {
					if(TowerController.towerSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
					}
					g.setColor(Color.DARK_GRAY);
					g.setFont(new Font("Uroob", Font.BOLD, 20));
					g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(Game.spritesheet.getSprite(4*16, 12*16, 16, 16), imPos[3]+52, iyp+4, 48, 48, null);
				}
				
			}*/
			
			
			
		}else if(TowerController.hotbar == 2) {
			
			int ixp = 284, iyp = 420;
			int[] imPos = {286, 339, 390};
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(ixp, iyp, 160, 56);
			
			for(int i = 0; i < 3; i++) {
				if(TowerController.deviceSelected == i+1) {
					g.setColor(new Color(255, 204, 51));
					g.fillRect(ixp+i*52, iyp, 56, 56);
					g.setFont(new Font("Uroob", Font.ITALIC, 32));
					g.drawString("$ "+Integer.toString(prices[i+1+4]), ixp+i*52+4, iyp);
				}else {
					g.setColor(Color.DARK_GRAY);
					g.setFont(new Font("Uroob", Font.BOLD, 20));
					g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
				}
				g.setColor(Color.lightGray);
				g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
				g.drawImage(devices[i], imPos[i], iyp+4, 48, 48, null);
			}
			
		}
		
			g.setColor(Color.DARK_GRAY);
			g.fillRect(600, 30, 96, 96);
			g.setColor(Color.blue);
			g.fillRect(602, 32, 92, 92);
			
			if(!Player.playerWasHurt) {
			g.drawImage(player[index], 595, 30, 96, 96, null);
			}else {
			g.drawImage(playerHurt[index], 595, 30, 96, 96, null);	
			}
			
			g.setColor(new Color(255, 204, 51));
			g.drawImage(money, 20, 20, 32, 32, null);
			g.setFont(new Font("Uroob", Font.ITALIC, 32));
			g.drawString("x "+Game.money, 70, 50);
		
		}
		
		///////////////////////////////////////////////////// 2 JOGADORES /////////////////////////////////////////////////////
		
		else if(Game.numberOfPlayers == 2) {
			
			//Quadrado Azul
			g.setColor(Color.DARK_GRAY);
			g.fillRect(600, 30, 96, 96);
			g.setColor(Color.blue);
			g.fillRect(602, 32, 92, 92);
			
			//Vida 1
			for(int i = 0; i < TowerController.life; i++) {
				g.drawImage(heart[index], 668,  130 + (i*40), 32, 32, null); 
			}
			
			//Imagem jogador 1
			if(!Player.playerWasHurt) {
				g.drawImage(player[index], 595, 30, 96, 96, null);
			}else {
				g.drawImage(playerHurt[index], 595, 30, 96, 96, null);	
			}
			
			//Dinheiro jogador 1
			g.setColor(new Color(255, 204, 51));
			g.drawImage(money, 460, 20, 32, 32, null);
			g.setFont(new Font("Uroob", Font.ITALIC, 32));
			g.drawString("x "+TowerController.money, 500, 50);
			
			if(TowerController.hotbar == 1) {
				
				//Hotbar 1 - 1
				
				int ixp = 420, iyp = 420;
				int[] imPos = {424, 475, 526, 580};
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(ixp, iyp, 264, 56);
				
				for(int i = 0; i < 5; i++) {
					if(i != 4) {
						if(TowerController.towerSelected == i+1) {
							g.setColor(new Color(255, 204, 51));
							g.fillRect(ixp+i*52, iyp, 56, 56);
							g.setFont(new Font("Uroob", Font.ITALIC, 32));
							g.drawString("$ "+Integer.toString(prices[i]), ixp+i*52+4, iyp);
						}else {
							g.setColor(Color.DARK_GRAY);
							g.setFont(new Font("Uroob", Font.BOLD, 20));
							g.drawString(Integer.toString(i+6), ixp+i*52+24, iyp-6);
						}
						g.setColor(Color.lightGray);
						g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
						g.drawImage(iconTower[i], imPos[i], iyp+4, 48, 48, null);
					}else {
						if(TowerController.towerSelected == i+1) {
							g.setColor(new Color(255, 204, 51));
							g.fillRect(ixp+i*52, iyp, 56, 56);
						}
						g.setColor(Color.DARK_GRAY);
						g.setFont(new Font("Uroob", Font.BOLD, 20));
						g.drawString("0", ixp+i*52+24, iyp-6);
						g.setColor(Color.lightGray);
						g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
						g.drawImage(Game.spritesheet.getSprite(4*16, 12*16, 16, 16), imPos[3]+52, iyp+4, 48, 48, null);
					}
				}
				
			}
		else if(TowerController.hotbar == 2) {
				
				int ixp = 472, iyp = 420;
				int[] imPos = {475, 526, 580};
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(ixp, iyp, 160, 56);
				
				for(int i = 0; i < 3; i++) {
					if(TowerController.deviceSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
						g.setFont(new Font("Uroob", Font.ITALIC, 32));
						g.drawString("$ "+Integer.toString(prices[i+1+4]), ixp+i*52+4, iyp);
					}else {
						g.setColor(Color.DARK_GRAY);
						g.setFont(new Font("Uroob", Font.BOLD, 20));
						g.drawString(Integer.toString(i+6), ixp+i*52+24, iyp-6);
					}
					
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(devices[i], imPos[i], iyp+4, 48, 48, null);
				}
				
			}
			
			//Vida 2
	        for(int i = 0; i < TowerController2.life; i++) {
				g.drawImage(heart[index], 20,  130 + (i*40), 32, 32, null); 
	        }
			
			if(TowerController2.hotbar == 1) {
			
			int ixp = 40, iyp = 420;
			int[] imPos = {44, 95, 146, 200};
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(ixp, iyp, 264, 56);
			
			for(int i = 0; i < 5; i++) {
				if(i != 4) {
					if(TowerController2.towerSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
						g.setFont(new Font("Uroob", Font.ITALIC, 32));
						g.drawString("$ "+Integer.toString(prices[i]), ixp+i*52+4, iyp);
					}else {
						g.setColor(Color.DARK_GRAY);
						g.setFont(new Font("Uroob", Font.BOLD, 20));
						g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					}
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(iconTower[i], imPos[i], iyp+4, 48, 48, null);
				}else {
					if(TowerController2.towerSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
					}
					g.setColor(Color.DARK_GRAY);
					g.setFont(new Font("Uroob", Font.BOLD, 20));
					g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(Game.spritesheet.getSprite(4*16, 12*16, 16, 16), imPos[3]+52, iyp+4, 48, 48, null);
				}
			}
			
			}else if(TowerController2.hotbar == 2) {
				
				int ixp = 92, iyp = 420;
				int[] imPos = {95, 147, 199};
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(ixp, iyp, 160, 56);
				
				for(int i = 0; i < 3; i++) {
					if(TowerController2.deviceSelected == i+1) {
						g.setColor(new Color(255, 204, 51));
						g.fillRect(ixp+i*52, iyp, 56, 56);
						g.setFont(new Font("Uroob", Font.ITALIC, 32));
						g.drawString("$ "+Integer.toString(prices[i+1+4]), ixp+i*52+4, iyp);
					}else {
						g.setColor(Color.DARK_GRAY);
						g.setFont(new Font("Uroob", Font.BOLD, 20));
						g.drawString(Integer.toString(i+1), ixp+i*52+24, iyp-6);
					}
					g.setColor(Color.lightGray);
					g.fillRect(ixp+4 + (i*52), iyp+4, 48, 48);
					g.drawImage(devices[i], imPos[i], iyp+4, 48, 48, null);
					
				}
				
			}
			
			/////////////////////////////////////////////////////N�o mexe
			
			
			//Quadrado vermelho
			g.setColor(Color.DARK_GRAY);
			g.fillRect(22, 30, 96, 96);
			g.setColor(Color.red);
			g.fillRect(24, 32, 92, 92);
			
			if(!Player_path2.playerWasHurt) {
			g.drawImage(player2[index], 25, 30, 96, 96, null);
			}else {
			g.drawImage(playerHurt2[index], 25, 30, 96, 96, null);	
			}
			
			g.setColor(new Color(255, 204, 51));
			g.drawImage(money, 140, 20, 32, 32, null);
			g.setFont(new Font("Uroob", Font.ITALIC, 32));
			g.drawString("x "+TowerController2.money, 190, 50);
			
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(311, 4, 96, 48);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(314, 6, 90, 44);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(357, 4, 4, 48);
			
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString(Integer.toString(TowerController2.score), 324, 42);
			g.drawString(Integer.toString(TowerController.score), 370, 42);
			
			
		}
	
			
	}

}
