package com.ichbineinstudio.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.TowerController;

public class Towers {

public static String[] options = {"Back to Menu","Tower1","Tower2","Tower3","Tower4","Tower5","Device1","Device2","Device3"};
	
	public static int currentOption;
	public int MaxOption = options.length - 1;
	
	public boolean down,up;
	
	public static int whatToShow = 1;
	
	

	
	public void tick() {
		
		if(currentOption >= 6) {
			whatToShow = 2;
		}
		else {
			whatToShow = 1;
		}

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
    	  g.fillRect(120, 65, 120, 85);
    	  g.setColor(Color.GRAY);
    	  g.fillRect(124, 69, 120, 77);
    	  
    	  if(whatToShow == 1) {
    	  
    	  if(options[currentOption] != "Back to Menu" && options[currentOption] != "Tower5") {
    	
    	  g.drawImage(Entity.attack, 190, 80, 16, 16, null);
    	  g.drawImage(Entity.dexterity, 190, 100, 16, 16, null);
 
    	  }
    	  
          if(options[currentOption] == "Back to Menu") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 7, 52, 22);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 9, 50, 18);
        	  
          }
          else if(options[currentOption] == "Tower1") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 66, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 68, 70, 12);
        	  
        	  
        	  g.drawImage(Entity.iconTower1, 128, 72, 48, 48, null);
              g.setColor(Color.red);
              g.setFont(new Font("Arial", Font.BOLD, 16));
              g.drawString("1", 210, 94);
              g.setColor(new Color(255, 204, 51));
              g.setFont(new Font("Arial", Font.BOLD, 10));
              g.drawString("1/s", 210, 114);
              
              g.setFont(new Font("Arial", Font.BOLD, 10));
              g.setColor(Color.white);
              g.drawString("Shoots an arrow", 134, 130);
              g.drawString("every second.", 135, 140);
          }
          else if(options[currentOption] == "Tower2") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 81, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 83, 70, 12);
        	  
        	  g.drawImage(Entity.iconTower2, 128, 72, 48, 48, null);
              g.setColor(Color.red);
              g.setFont(new Font("Arial", Font.BOLD, 14));
              g.drawString("0.1", 210, 94);
              g.setColor(new Color(255, 204, 51));
              g.setFont(new Font("Arial", Font.BOLD, 9));
              g.drawString("???/s", 210, 114);
              
              g.setFont(new Font("Arial", Font.BOLD, 10));
              g.setColor(Color.white);
              g.drawString("Shoots a laser", 134, 130);
              g.drawString("continuously.", 135, 140);
          }
          else if(options[currentOption] == "Tower3") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 96, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 98, 70, 12);

        	  g.drawImage(Entity.iconTower3, 128, 72, 48, 48, null);
              g.setColor(Color.red);
              g.setFont(new Font("Arial", Font.BOLD, 14));
              g.drawString("1.5", 210, 94);
              g.setColor(new Color(255, 204, 51));
              g.setFont(new Font("Arial", Font.BOLD, 9));
              g.drawString("1/2s", 210, 114);
              
              g.setFont(new Font("Arial", Font.BOLD, 10));
              g.setColor(Color.white);
              g.drawString("Shoots an ice laser", 134, 130);
              g.drawString("that freeze enemies.", 135, 140);
          }
          else if(options[currentOption] == "Tower4") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 111, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 113, 70, 12);

        	  g.drawImage(Entity.iconTower4, 128, 72, 48, 48, null);
              g.setColor(Color.red);
              g.setFont(new Font("Arial", Font.BOLD, 14));
              g.drawString("5", 210, 94);
              g.setColor(new Color(255, 204, 51));
              g.setFont(new Font("Arial", Font.BOLD, 9));
              g.drawString("1/2s", 210, 114);
              
              g.setFont(new Font("Arial", Font.BOLD, 10));
              g.setColor(Color.white);
              g.drawString("Shoots a bomb.", 134, 135);
              g.setFont(new Font("Arial", Font.BOLD, 9));
              
              
          }
          else if(options[currentOption] == "Tower5") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 126, 72, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 128, 70, 12);
        	  
        	  g.drawImage(Game.spritesheet.getSprite(4*16, 12*16, 16, 16), 158, 72, 48, 48, null);
              
              g.setFont(new Font("Arial", Font.BOLD, 10));
              g.setColor(Color.white);
              g.drawString("Destroys a tower.", 134, 135);
              g.setFont(new Font("Arial", Font.BOLD, 9));
          }
          
          g.setColor(Color.WHITE);
          g.setFont(new Font("Arial", Font.ITALIC, 10));
          g.drawString("Tower 1", 20, 78);
          g.drawString("Tower 2", 20, 93);
          g.drawString("Tower 3", 20, 108);
          g.drawString("Tower 4", 20, 123);
          g.drawString("Tower 5", 20, 138);
          
          g.setFont(new Font("Arial", Font.ITALIC, 10));
          g.drawString("Back to", 10, 17);
          g.drawString("Menu", 14, 26);
          
          g.setFont(new Font("Arial", Font.BOLD, 10));
          g.drawString("V", 35, 150);

          
	}
    	  else if(whatToShow == 2) {
    		  
    		  if(options[currentOption] != "Device3") {
    		  g.drawImage(Entity.iconTower1, 172, 70, 16, 16, null);
        	  g.drawImage(Entity.iconTower2, 180, 87, 16, 16, null);
        	  g.drawImage(Entity.iconTower3, 190, 70, 16, 16, null);
        	  g.drawImage(Entity.iconTower4, 180, 105, 16, 16, null);	
    		  }
    		  
    		  if(options[currentOption] == "Device1") {
    			  g.setColor(Color.DARK_GRAY);
            	  g.fillRect(0, 81, 72, 16);
            	  g.setColor(Color.GRAY);
            	  g.fillRect(0, 83, 70, 12);

            	  g.drawImage(Entity.attackDevice1, 128, 72, 48, 48, null);
                  g.setColor(Color.red);
                  g.setFont(new Font("Arial", Font.BOLD, 10));
                  g.drawString("+10%", 210, 82);
              
                  g.drawString("+20%", 210, 100);
                  
                  g.drawString("+20%", 210, 118);
                  
                  g.setFont(new Font("Arial", Font.BOLD, 9));
                  g.setColor(Color.white);
                  g.drawString("Improves cumulatively", 134, 130);
                  g.drawString("the attack of towers.", 135, 140);
              }
              else if(options[currentOption] == "Device2") {
            	  g.setColor(Color.DARK_GRAY);
            	  g.fillRect(0, 96, 72, 16);
            	  g.setColor(Color.GRAY);
            	  g.fillRect(0, 98, 70, 12);

            	  g.drawImage(Entity.speedDevice1, 128, 72, 48, 48, null);
            	  g.setFont(new Font("Arial", Font.BOLD, 10));
            	  g.setColor(Color.green);
                  g.drawString("+10%", 210, 82);
              
                  g.drawString("+0%", 210, 100);

                  g.drawString("+10%", 210, 118);
                  
                  g.setFont(new Font("Arial", Font.BOLD, 9));
                  g.setColor(Color.white);
                  g.drawString("Improves cumulatively", 134, 130);
                  g.drawString("the speed of towers.", 135, 140);
              }
              else if(options[currentOption] == "Device3") {
            	  g.setColor(Color.DARK_GRAY);
            	  g.fillRect(0, 111, 72, 16);
            	  g.setColor(Color.GRAY);
            	  g.fillRect(0, 113, 70, 12);

            	  g.drawImage(Entity.sizeDevice1, 158, 72, 48, 48, null);             
                  
                  g.setFont(new Font("Arial", Font.BOLD, 9));
                  g.setColor(Color.white);
                  g.drawString("The next towers won't", 134, 130);
                  g.drawString("be so large!", 135, 140);
              }
    		  
    		  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
    		  
    		  g.drawString("Device 1", 20, 93);
              g.drawString("Device 2", 20, 108);
              g.drawString("Device 3", 20, 123);
              
              g.setFont(new Font("Arial", Font.BOLD, 15));
              g.drawString("^", 35, 85);
    	  }
	 
	}	
	
}
