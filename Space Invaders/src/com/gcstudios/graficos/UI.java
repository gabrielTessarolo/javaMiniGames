package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class UI {
	
	
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;
	
	public static String time;
	
	public static int showScore = 0;
	public static int alternateCounter = 0;
	public static boolean addScore = true;
	
	public void tick() {
		
		alternateCounter++;

		if(alternateCounter % 2 == 0) {
			addScore = false;
		}else {
			addScore = true;
		}
		
		if(addScore) {
		if(showScore < Player.score) {
			showScore+= 1;
		}else {
			showScore = Player.score;
		}
		}
		
		
		frames++;
		if(frames == 60) {
			//Passou 1 segundo.
			frames = 0;
			seconds++;
			if(seconds == 60) {
				seconds = 0;
				minutes++;
				if(UI.minutes % 2 == 0) {
					World.CICLO++;
					if(World.CICLO > World.noite) {
						World.CICLO = 0;
					}
				}
			}
		}
		
	}
	
	public static void writeTime(String time) {
		String name = "";
		BufferedWriter write = null;
		try {
			FileWriter file = new FileWriter("points.txt");
			write = new BufferedWriter(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			write.write(time);
			write.newLine();
		}catch(IOException e) {}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {}
	}
	
	public void smoothCounter(int originalValue, int startSmooth) {
		
		int show;
		int counter = 0;
		boolean canChange = true;
		
		counter++;
		if(counter == 61) {
			counter = 0;
		}
		
		if(counter % 2 == 1) {
			canChange = false;
		}else {
			canChange = true;
		}
		
		if(canChange) {
			if(startSmooth < originalValue) {
			startSmooth += 1;
			}else if(startSmooth > originalValue){
			startSmooth -= 1;
			}
		}
		
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
		
		g.drawString("Score: "+showScore, Game.WIDTH*Game.SCALE/2 - 40, Game.HEIGHT*Game.SCALE - 0);
		g.drawString("FPS: "+Game.Fps, Game.WIDTH*Game.SCALE/2 - 40, 30);
		
		
		for(int i = 0; i < Game.player.life; i++) {
		g.drawImage(Player.player, (0 + i*18), Game.HEIGHT * Game.SCALE - 16, 16, 16, null);
		}
		
		
		
	}
	
}
