package com.ichbineinstudio.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.TowerController;
import com.ichbineinstudio.graficos.UI;

public class Controls {

public static String[] options = {"Back to Menu","Single Player","Multi Player"};
	
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
			if(options[currentOption] == "Back to Menu") {
				
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

          if(options[currentOption] == "Back to Menu") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 7, 52, 22);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 9, 50, 18);
        	  
          }
          if(options[currentOption] == "Single Player") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 81, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 83, 70, 12);
        	  
        	  g.setColor(Color.white);
              g.setFont(new Font("Arial", Font.BOLD, 9));
        	  g.drawString("Move:",  110, 83);
        	  g.drawImage(Entity.mouse, 140, 72, null);
        	  g.drawString("Place:",  110, 103);
        	  g.drawImage(Entity.mouseButton1, 140, 92, null);
        	  g.drawString("Select Towers:",  110, 123);
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(175, 111, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(176, 112, 71, 14);
        	  g.setColor(Color.white);
        	  g.drawString("1 | 2 | 3 | 4 | 5", 178, 123);
        	  g.drawString("Change Type:",  110, 141);
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(175, 129, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(176, 130, 71, 14);
        	  g.setColor(Color.white);
        	  g.drawString("Backspa. | Q", 178, 141);
   
          }
          else if(options[currentOption] == "Multi Player") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 96, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 98, 70, 12);
        	  
        	 
        	
        	  g.setColor(Color.DARK_GRAY);
        	  
        	  g.fillRect(100, 65+42, 150, 2);
        	  g.setColor(Color.GRAY);
        	  g.setColor(Color.white);
        	  
        	  g.setColor(Color.white);
              g.setFont(new Font("Arial", Font.BOLD, 8));
              
              g.drawString("Player 1", 212, 77);
              g.drawString("Player 2", 105, 77+39);
             
              g.drawImage(Game.spritesheet.getSprite(6*16, 0, 32, 32), Game.WIDTH - 32, 75, 32, 32, null);
              g.drawImage(Game.spritesheet.getSprite(12*16, 0, 32, 32), Game.WIDTH - 140, 65+49, 32, 32, null);
        	  
              g.setFont(new Font("Arial", Font.BOLD, 9));
        	  g.drawString("Move:", 105, 77);
        	  g.drawString("Place:", 105, 87);
        	  g.drawString("Sel. Tow.:", 105, 97);
        	  g.drawString("Change Type:", 105, 106);
        	  

        	  g.drawString("Move: ", 135, 77+39);
        	  g.drawString("Place: ", 135, 87+39);
        	  g.drawString("Sel. Tow.:", 135, 97+39);
        	  g.drawString("Change Type: ", 135, 106+39);
        	  
        	  g.setColor(Color.blue);
        	  g.drawString("[Arrows]", 135, 77);
        	  g.drawString("Enter", 135, 87);
        	  g.drawString("Backpace", 172, 106);

        	  g.setColor(Color.red);
        	  g.drawString("W, A, S, D", 165, 77+40);
        	  g.drawString("Space", 165, 87+39);
        	  g.drawString("Q", 202, 106+39);
        	  
        	  g.setFont(new Font("Arial", Font.BOLD, 8));
        	  
        	  g.drawString("1 | 2 | 3 | 4 | 5", 185, 96+39);
        	  g.setColor(Color.blue);
              g.drawString("6 | 7 | 8 | 9 | 0", 155, 96);
          }
          
          g.setColor(Color.white);
          g.setFont(new Font("Arial", Font.ITALIC, 9));
          g.drawString("Back to", 10, 17);
          g.drawString("Menu", 14, 26);
          g.drawString("Single Player", 10, 93);
          g.drawString("Multi Player", 11, 108);
          
    	  }
	 
	
	
}
