package com.gcstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.world.World;

public class Menu {

	public static int yPMN = 150;
	public static int xPMN = 60;
	public static int xPMNCounter = 0;
	
	public static int yC = 110;
	public static int xC = 160;
	
	public static int yStart = 480;
	public static int xStart = 180;
	
	public static int yA_A = 350;
	public static int xA_A = 60;
	
	public void tick() {
		
		if(Game.select) {
			Game.select = false;
			Game.gameState = "Normal";
			World.restartGame();
		}
		
	    xA_A = xPMN + 10;
		
	    if(yPMN <= 250) {
	    	yPMN++;
	        yA_A--;
	        yStart--;
	    	
	    xPMNCounter++;
	    if(xPMNCounter >= 10 && xPMNCounter <= 20) {
	    	xPMN = 70;
	    }else {
	    	xPMN = 50;
	    }
	    if(xPMNCounter > 20) {
	    	xPMNCounter = 0;
	    }
	    }else {
	    	/*xPMN = 60;
	    	yPMN = 150;
	    	yA_A = 350;*/
	    }
	    
	    if((yC) <= 210){
	    	yC++;
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
		
		g.setFont(new Font("Copperplate Gothic Bold", Font.CENTER_BASELINE, 50));
		g.setColor(new Color(255, 204, 51, 240));
		g.drawString("P          M    N", xPMN, yPMN);
		g.drawString("  A     -     A ", xA_A, yA_A);
		if(yC >= 210) {
		g.drawImage(Player.rightPlayer[Player.index], xC, yC, 48, 48, null);
		}else {
		g.drawImage(Player.downPlayer[Player.index], xC, yC, 48, 48, null);
		}
		g.drawImage(Entity.Space_Key, xStart, yStart, 128, 64, null);
		g.drawImage(Entity.Play, xStart+32, yStart - 60, 64, 64, null);
	}

}
