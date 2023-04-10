package com.ichbineinstudio.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SelectMode {
	
public static String[] options = {"Singleplayer","Multiplayer","Back to Menu"};
	
	public static int currentOption;
	public int MaxOption = options.length - 1;
	
	public boolean down,up;
	public static boolean pause = false;
	
	public void tick() {

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
			if(options[currentOption] == "Singleplayer") {
				
				if(!pause) {
				Game.CUR_LEVEL = 1;
				Game.gameState = "Normal";
				pause = false;
				
				}else {
					Game.gameState = "Normal";
					pause =  false;
				}
			}
			else if(options[currentOption] == "Multiplayer") {
				/*Game.numberOfPlayers = 2;
				Game.CUR_LEVEL = 0;
				Game.gameState = "Normal";
				Game.updateLvl = true;*/
				Game.gameState = "selectingM";
				
			}
			else if(options[currentOption] == "Back to Menu") {
				Game.gameState = "Menu";
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
          g.drawString("Phantoms'n", 60, 55);
          
          g.setColor(Color.GRAY);
          g.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
          g.drawString("Robots", 82, 90);
          
          g.setColor(Color.BLACK);
          g.fillRect(0, 60, 240, 13);
          
          g.setColor(Color.WHITE);
          g.setFont(new Font("Arial", Font.ITALIC, 10));
          g.drawString("Tower Defense", 90, 70);
          
          
         
          if(options[currentOption] == "Singleplayer") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 96, 240, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 100, 240, 8);
        	  
        	  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
              g.drawString("> Space", 15, 108);
        	  
          }
          else if(options[currentOption] == "Multiplayer") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 116, 240, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 120, 240, 8);
        	  
        	  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
              g.drawString("> Space", 15, 128);
        	  
          }
          else if(options[currentOption] == "Back to Menu") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 136, 240, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 140, 240, 8);
        	  
        	  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
              g.drawString("> Space", 15, 148);
        	  
          }
          
        
        	  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
              g.drawString("Single Player", 95, 108);     
              g.drawString("Multi Player", 99, 128);
              g.drawString("Back to Menu", 95, 148);
	
	 
	}	



}
