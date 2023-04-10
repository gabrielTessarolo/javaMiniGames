package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.setColor(new Color(255, 204, 51));
		g.drawString("Score: "+Player.score+" / "+Player.totalScore, 185, 170);
		
		if(Game.player.stock >= 1) {
		g.drawImage(Entity.PlayerImage, 05, 482, 16, 16, null);
		}
		if(Game.player.stock >= 2) {
	    g.drawImage(Entity.PlayerImage, 30, 482, 16, 16, null);
		}
		if(Game.player.stock >= 3) {
		g.drawImage(Entity.PlayerImage, 55, 482, 16, 16, null);
			
		}
	}
	
}
