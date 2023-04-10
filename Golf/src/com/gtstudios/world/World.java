package com.gtstudios.world;

import java.awt.Graphics;
import java.util.ArrayList;

import com.gtstudios.entities.Ball;
import com.gtstudios.entities.Controller;
import com.gtstudios.entities.Entity;
import com.gtstudios.entities.Hole;
import com.gtstudios.entities.Selector;
import com.gtstudios.entities.Spawner;
import com.gtstudios.main.Game;

public class World {

	public static int WIDTH,HEIGHT;
	public static int[] coordX = {Game.WIDTH/2-16}; 
	public static int[] coordY = {Game.HEIGHT/2-16}; 
	
	public World(){
		
	}

	public static void restartGame(){
		
		Game.entities.clear();
		Game.entities = new ArrayList<Entity>();
		Game.holes = new ArrayList<Hole>();
		Game.balls = new ArrayList<Ball>();
		Game.slcs = new ArrayList<Selector>();
		Spawner.timeOfMatch = 0;
		Hole hole = new Hole(coordX[Game.curLevel - 1], coordY[Game.curLevel - 1], 32, 32, 0, null);
		Game.entities.add(hole);
		Game.holes.add(hole);
		Game.entities.add(Game.control);
		
		return;
	}
	
	public void render(Graphics g){
		
	}
	
}
