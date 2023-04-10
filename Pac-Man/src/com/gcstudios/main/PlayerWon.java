package com.gcstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.world.World;

public class PlayerWon {
	
	public static int yCNRTLTOS = 150;
	public static int xCNRTLTOS = 60;
	public static int xCNRTLTOSCounter = 0;
	
	public static int yStart = 450;
	public static int xStart = 180;
	
	public static int yOGAUAIN = 350;
	public static int xOGAUAIN = 60;
	
	public void tick() {
		
		if(Game.select) {
			Game.select = false;
			Game.gameState = "Normal";
			World.restartGame();
		}
		
	    xOGAUAIN = xCNRTLTOS + 10;
		
	    if(yCNRTLTOS <= 250) {
	    	yCNRTLTOS++;
	        yOGAUAIN--;
	        yStart--;
	    	
	    xCNRTLTOSCounter++;
	    if(xCNRTLTOSCounter >= 10 && xCNRTLTOSCounter <= 20) {
	    	xCNRTLTOS = 70;
	    }else {
	    	xCNRTLTOS = 50;
	    }
	    if(xCNRTLTOSCounter > 20) {
	    	xCNRTLTOSCounter = 0;
	    }
	    }else {
	    	/*xCNRTLTOS = 60;
	    	yCNRTLTOS = 150;
	    	yOGAUAIN = 350;*/
	    }
	    
	    if(Game.gameState == "Menu") {
			Player.frames++;
			if(Player.frames >= Player.maxFrames) {
				Player.frames = 0;
				Player.index++;
				if(Player.index > Player.maxIndex)
					Player.index = 0;	
		}
	    }
	    
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(30, 30, 100, 180));
		g.fillRect(0,0, 480, 500);
		
		g.setFont(new Font("Copperplate Gothic Bold", Font.CENTER_BASELINE, 30));
		g.setColor(new Color(255, 204, 51, 240));
		g.drawString("C   N   R   T    L   T  O   S", xCNRTLTOS - 20, yCNRTLTOS);
		g.drawString("  O   G   A   U   A   I   N", xOGAUAIN - 20, yOGAUAIN);
		
		g.setFont(new Font("Copperplate Gothic Bold", Font.CENTER_BASELINE, 20));
		g.drawString("Try Again?", xStart, yStart - 64);

		g.drawImage(Entity.Space_Key, xStart, yStart, 128, 64, null);
		g.drawImage(Entity.Play, xStart+32, yStart - 60, 64, 64, null);
	}

}
