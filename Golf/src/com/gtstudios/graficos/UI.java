package com.gtstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gtstudios.entities.Entity;
import com.gtstudios.main.Game;
import com.gtstudios.world.World;

public class UI {
	
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;
	
	public static String time;
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		String formatTime = "";
		if(minutes < 10) {
			formatTime+="0"+minutes+":";
		}else {
			formatTime+=minutes+":";
		}
		
		if(seconds < 10) {
			formatTime+="0"+seconds;
		}else {
			formatTime+=seconds;
		}
		
		time = formatTime;
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(formatTime, Game.WIDTH*Game.SCALE - 60, Game.HEIGHT*Game.SCALE - 10);
		
		int[] rC = {255, 255, 255, 0, 0};
		int[] gC = {0, 102, 204, 255, 153};
		int[] bC = {0, 0, 51, 51, 0};
		
		for(int i = 0; i < 5; i++) {
			g.setColor(new Color(rC[i], gC[i], bC[i]));
			g.fillRect(15 + i*20, 15, 20, 15);
		}
		
		g.setColor(new Color(15, 100, 4));
		g.fillRect(15+(int)Game.control.force, 15, (int)(101-Game.control.force), 15);
		
		for(int i = 0; i < 5; i++) {
			g.setColor(Color.black);
			g.drawRect(15 + i*20, 15, 20, 15);
		}
	}
	
}
