package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.gcstudios.world.Camera;
import com.gcstudios.world.World;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public static int right_dir = 0, up_dir = 1, left_dir = 2, down_dir = 3;
	
	public boolean moved = false;
	
	public static int dir;
	
	public static BufferedImage[] rightPlayer;
	public static BufferedImage[] leftPlayer;
	public static BufferedImage[] upPlayer;
	public static BufferedImage[] downPlayer;
	
	public static int index = 0;
	public static int frames = 0;
	public static int maxFrames = 8;
	public static int maxIndex = 1;
	
	public static int stock = 3;
	public static boolean wasDied = false;
	public static boolean wonTheGame = false;
	public static boolean gameOver = false;
	
	public static boolean invencibleMode;
	public static boolean showInvencible;
	public static int invencibleCounter;
	
	public static int score = 0;
	public static int totalScore;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		rightPlayer = new BufferedImage[2]; 
		leftPlayer = new BufferedImage[2]; 
		upPlayer = new BufferedImage[2]; 
		downPlayer = new BufferedImage[2]; 
		
		rightPlayer[0] = Game.spritesheet.getSprite(32, 0, 16, 16);
		rightPlayer[1] = Game.spritesheet.getSprite(48, 0, 16, 16);
		
		leftPlayer[0] = Game.spritesheet.getSprite(64, 0, 16, 16);
		leftPlayer[1] = Game.spritesheet.getSprite(80, 0, 16, 16);
		
		upPlayer[0] = Game.spritesheet.getSprite(64, 16, 16, 16);
		upPlayer[1] = Game.spritesheet.getSprite(80, 16, 16, 16);
		
		downPlayer[0] = Game.spritesheet.getSprite(32, 16, 16, 16);
		downPlayer[1] = Game.spritesheet.getSprite(48, 16, 16, 16);
		 
	}
	
	public void tick(){
		
		if(score == totalScore) {
			wonTheGame = true;
			Game.gameState = "PlayerWon";
		}
		
		depth = 2;
		
		moved = false;
		
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			dir = right_dir;
			x+=speed;
			moved = true;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			dir = left_dir;
			x-=speed;
			moved = true;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			dir = up_dir;
			y-=speed;
			moved = true;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			dir = down_dir;
			y+=speed;
			moved = true;
		}
		
		
		if(moved) {
		frames++;
		if(frames >= maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex)
				index = 0;
		}
		}
		
		
		
		
		if(wonTheGame) {
			wonTheGame = false;
			stock = 3;
			
		}
		
		if(wasDied) {

			wasDied = false;
			if(stock >= 1 ) {
				invencibleMode = true;
			/*	for(int i =0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(!(e instanceof Coin)) {
						Game.entities = new ArrayList<Entity>();
					}
				}*/

			/*Game.spritesheet = new Spritesheet("/spritesheet.png");
			Game.player = new Player(0,0,16,16,2,Game.spritesheet.getSprite(32,0,16,16));
			Game.entities.add(Game.player);
			Game.world = new World("/Level1.png");*/
			Game.player.x = 16*16;
			Game.player.y = 26*16;
			
			return;
		}else {
			gameOver = true;
		}
		
		}
		if(invencibleMode) {
			invencibleCounter++;
			if(invencibleCounter <= 25 || (invencibleCounter > 50 && invencibleCounter <= 75) || (invencibleCounter > 100 && invencibleCounter <= 125)
			|| (invencibleCounter > 150 && invencibleCounter <= 175) || (invencibleCounter > 200 && invencibleCounter <= 225) || (invencibleCounter > 250 && invencibleCounter <= 275)
			|| (invencibleCounter > 300 && invencibleCounter <= 325) || (invencibleCounter > 350 && invencibleCounter <= 375) || (invencibleCounter > 400 && invencibleCounter <= 425)
			|| (invencibleCounter > 450 && invencibleCounter <= 475)) {
				showInvencible = true;
			}else {
				showInvencible = false;
			}
			if(invencibleCounter >= 500) {
				invencibleCounter = 0;
				invencibleMode = false;
			}
		}
		
		if(gameOver) {
			gameOver = false;
			stock = 3;
			World.restartGame();
		}
		
		//System.out.println(Game.entities.size());
		
		checkCollisionCoin();
		checkCollisionFruit();
	}
	
	public void checkCollisionCoin() {
		for(int i = 0; i < Game.entities.size(); i++) {
			
			Entity curEntity = Game.entities.get(i);
			
			if(curEntity instanceof Coin) {
				if(Entity.isColidding(this, curEntity)){
					Sound.chompEffect.play();
					this.score += 10;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}
	public void checkCollisionFruit() {
		for(int i = 0; i < Game.entities.size(); i++) {
			
			Entity curEntity = Game.entities.get(i);
			
			if(curEntity instanceof Fruit) {
				if(Entity.isColidding(this, curEntity)){
					Game.entities.remove(i);
					Enemy.ghostMode = true;
					Enemy2.ghostMode = true;
					Enemy3.ghostMode = true;
					Enemy4.ghostMode = true;
					return;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(!invencibleMode) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX()  - Camera.x, this.getY()  - Camera.y, null);
		}
		else if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX()  - Camera.x, this.getY()  - Camera.y, null);
		}
		else if(dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX()  - Camera.x, this.getY()  - Camera.y, null);
		}
		}else {
		if(showInvencible) {
			if(dir == right_dir) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if(dir == left_dir) {
				g.drawImage(leftPlayer[index], this.getX()  - Camera.x, this.getY()  - Camera.y, null);
			}
			else if(dir == up_dir) {
				g.drawImage(upPlayer[index], this.getX()  - Camera.x, this.getY()  - Camera.y, null);
			}
			else if(dir == down_dir) {
				g.drawImage(downPlayer[index], this.getX()  - Camera.x, this.getY()  - Camera.y, null);
			}
		}else {
			
		}
		}
	}

	


}
