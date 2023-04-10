package com.ichbineinstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_WALL2 = Game.spritesheet.getSprite(0,16,16,16);
	
	public static BufferedImage TILE_FLOOR_ = Game.spritesheet.getSprite(16,2*16,16,16);
	public static BufferedImage TILE_WALL_ = Game.spritesheet.getSprite(0,2*16,16,16);
	public static BufferedImage TILE_WALL_2 = Game.spritesheet.getSprite(0,3*16,16,16);
	
	public static BufferedImage[] images = {TILE_FLOOR, TILE_WALL, TILE_WALL2, TILE_FLOOR_, TILE_WALL_, TILE_WALL_2};

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
