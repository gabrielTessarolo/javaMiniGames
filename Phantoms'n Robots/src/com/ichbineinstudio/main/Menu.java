package com.ichbineinstudio.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.Player;
import com.ichbineinstudio.graficos.UI;
import com.ichbineinstudio.world.World;

public class Menu {
	
	public static String[] options = {"Exit","New Game","Towers","Controls"};
	
	public static int currentOption = 1;
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
			if(options[currentOption] == "New Game") {
				
				if(!pause) {
			    Game.gameState = "GameMode";
				
				}else {
					Game.gameState = "Normal";
					pause =  false;
				}
			}
			else if(options[currentOption] == "Towers") {
				Game.gameState = "Towers";
			}
			else if(options[currentOption] == "Controls") {
				Game.gameState = "Controls";
			}
			else if(options[currentOption] == "Exit") {
				System.exit(1);
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
          
          if(options[currentOption] == "Exit") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 12, 52, 14);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 14, 50, 10);
        	  
          }
         
          else if(options[currentOption] == "New Game") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 96, 240, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 100, 240, 8);
        	  
        	  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
              g.drawString("> Space", 15, 108);
        	  
          }
          else if(options[currentOption] == "Towers") {
        	  g.setColor(Color.DARK_GRAY);
        	  g.fillRect(0, 116, 240, 16);
        	  g.setColor(Color.GRAY);
        	  g.fillRect(0, 120, 240, 8);
        	  
        	  g.setColor(Color.WHITE);
              g.setFont(new Font("Arial", Font.ITALIC, 10));
              g.drawString("> Space", 15, 128);
        	  
          }
          else if(options[currentOption] == "Controls") {
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
          if(!pause) {
        	  
              g.drawString("New Game", 100, 108);
              }
          else {
        	  g.drawString("Resume", 105, 108);
          }
          
          g.drawString("Towers and Upgrades", 70, 128);
          g.drawString("Controls", 104, 148);
          g.setFont(new Font("Arial", Font.ITALIC, 10));
          g.drawString("Exit", 15, 23);
         
	
	 
	}	

}
