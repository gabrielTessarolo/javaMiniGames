package com.gcstudios.entities;

import java.util.Random;

import com.gcstudios.main.Game;

public class Spawner {
	
	public static int timeOfMatch = 0;
	public int counterTime = 0;
	
	public double time = (Entity.rand.nextInt(120 - 60) + 60) ;
	public int curTime = 0;
	
	public static Random rand = new Random();
	public static Random rand2 = new Random();
	public static Random rand3 = new Random();
	public static Random rand4 = new Random();
	public static Random rand5 = new Random();
	
	
	public void tick() {
		
		timeOfMatch++;
		
		if(timeOfMatch >= 300) {
		    counterTime++;
		}
		
		curTime++;
		if(curTime >= time) {
			curTime = 0;
			int xx = rand.nextInt(Game.WIDTH - 16);
			int yy = -16;
			Enemy en = new Enemy(xx, yy, 16, 16, Entity.rand.nextDouble(), null);
			Game.entities.add(en);
			if(timeOfMatch > 600) {
				int xx2 = rand2.nextInt(Game.WIDTH - 16);
				Enemy en2 = new Enemy(xx2, yy, 16, 16, Entity.rand.nextDouble(), null);
				Game.entities.add(en2);
			}
			if(timeOfMatch > 1800) {
				int xx3 = rand3.nextInt(Game.WIDTH - 16);
				Enemy en3 = new Enemy(xx3, yy, 16, 16, Entity.rand.nextDouble(), null);
				Game.entities.add(en3);
			}
			if(timeOfMatch > 3000) {
				int xx4 = rand4.nextInt(Game.WIDTH - 16);
				Enemy en4 = new Enemy(xx4, yy, 16, 16, Entity.rand.nextDouble(), null);
				Game.entities.add(en4);
				int xx5 = rand5.nextInt(Game.WIDTH - 16);
				Enemy en5 = new Enemy(xx5, yy, 16, 16, Entity.rand.nextDouble(), null);
				Game.entities.add(en5);
			}
			
		
		}
	}
	

}
