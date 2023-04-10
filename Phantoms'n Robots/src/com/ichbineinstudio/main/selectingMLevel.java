package com.ichbineinstudio.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.TowerController;
import com.ichbineinstudio.graficos.UI;

public class selectingMLevel {

public static String[] options = {"Back","Level 1","Level 2","Level 3"};
	
	public static int currentOption;
	public int MaxOption = options.length - 1;
	
	public boolean down,up;
	
	public void tick() {
		
		//System.out.println(currentOption);

		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0) {
				currentOption = MaxOption;
			}
			
		}
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > MaxOption) {
				currentOption = 0;
			}
		}
		
		

		if(Game.select) {
			Game.select = false;
			if(options[currentOption] == "Back") {
				
				Game.gameState = "GameMode";
				
			}
			else if(options[currentOption] == "Level 1") {
				Game.numberOfPlayers = 2;
				Game.CUR_LEVEL = 0;
				Game.gameState = "Normal";
				Game.updateLvl = true;
			}
			else if(options[currentOption] == "Level 2") {
				Game.numberOfPlayers = 2;
				Game.CUR_LEVEL = -1;
				Game.gameState = "Normal";
				Game.updateLvl = true;
			}
			
			
			
		}
	}

	
	
	public void render(Graphics g) {
		 g.setColor(Color.DARK_GRAY);
		 g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
          g.setColor(Color.LIGHT_GRAY);
          g.fillRect(6, 6, 240 -12, 160 - 12);
          
          g.setColor(Color.DARK_GRAY);
          g.setFont(new Font("Showcard Gothic", Font.ITALIC, 20));
          g.drawString("Phantoms'n", 60, 25);
          
          g.setColor(Color.GRAY);
          g.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
          g.drawString("Robots", 82, 60);
          
          g.setColor(Color.BLACK);
          g.fillRect(0, 30, 240, 13);
          
          g.setColor(Color.WHITE);
          g.setFont(new Font("Arial", Font.ITALIC, 10));
          g.drawString("Tower Defense", 90, 40);
          
          g.setColor(Color.DARK_GRAY);
    	  g.fillRect(100, 65, 140, 85);
    	  g.setColor(Color.GRAY);
    	  g.fillRect(104, 69, 140, 77);

          if(options[currentOption] == "Back") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 7, 52, 22);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 9, 50, 18);
        	  
          }
          if(options[currentOption] == "Level 1") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 81, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 83, 70, 12);
        	  
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(146, 74, 53, 41);
        	  g.drawImage((Game.spritesheet.getSprite(0, 256, 15, 11)), 150, 78, 45, 33, null);
        	  
        	  g.setColor(Color.blue);
        	  g.drawString("Easy", 161,  135);
   
          }
          else if(options[currentOption] == "Level 2") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 96, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 98, 70, 12);
        	  
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(146, 74, 53, 41);
        	  g.drawImage((Game.spritesheet.getSprite(16, 256, 16, 11)), 150, 78, 45, 33, null);
        	  
        	  g.setColor(Color.orange);
        	  g.drawString("Medium", 154,  135);
        	  
        	 
          }
          else if(options[currentOption] == "Level 3") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 111, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 113, 70, 12);
        	  
        	 
          }
          
          g.setColor(Color.white);
          g.setFont(new Font("Arial", Font.ITALIC, 9));
          g.drawString("Back", 14, 21);
          g.drawString("Level 1", 10, 93);
          g.drawString("Level 2", 11, 108);
          g.drawString("Level 3", 11, 123);
          
    	  }
	 
	
	
}

