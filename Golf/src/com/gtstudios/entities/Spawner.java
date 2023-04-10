package com.gtstudios.entities;

import java.util.Random;

import com.gtstudios.main.Game;

public class Spawner {
	
	public static int timeOfMatch = 0;
	public int counterTime = 0;
	
	public double time = 5*60;
	public int curTime = 2;
	public static int[] maxNumber = {1};
	
	public static Random rand = new Random();	
	
	public boolean blocked(Entity e1) {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e2 = Game.entities.get(i);
			if(Entity.isColidding(e1, e2) || Entity.calculateDistance(e1.getX(), e1.getY(), e2.getX(), e2.getY()) < 40) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
	
		time -= timeOfMatch/1000;
		
		curTime++;
		if(curTime >= time) {
			curTime = 0;
			if(Game.balls.size() < maxNumber[Game.curLevel - 1]) {
				int xx = rand.nextInt(Game.WIDTH - 16);
				int yy = rand.nextInt(Game.HEIGHT - 16);
				Ball b = new Ball(xx, yy, 16, 16, 4, null);
				while(blocked(b)) {
					xx = rand.nextInt(Game.WIDTH - 16);
					yy = rand.nextInt(Game.HEIGHT - 16);
					b = new Ball(xx, yy, 16, 16, 4, null);
				}
				Game.entities.add(b);
				Game.balls.add(b);
			}
		}
		
	}
	

}
