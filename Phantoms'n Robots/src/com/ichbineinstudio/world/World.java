package com.ichbineinstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ichbineinstudio.entities.Enemy;
import com.ichbineinstudio.entities.Entity;
import com.ichbineinstudio.entities.Player;
import com.ichbineinstudio.entities.Player2;
import com.ichbineinstudio.entities.Player2_path2;
import com.ichbineinstudio.entities.Player3;
import com.ichbineinstudio.entities.Player3_path2;
import com.ichbineinstudio.entities.Player4;
import com.ichbineinstudio.entities.Player4_path2;
import com.ichbineinstudio.entities.Player_path2;
import com.ichbineinstudio.entities.Spawner;
import com.ichbineinstudio.entities.Spawner2;
import com.ichbineinstudio.entities.Target;
import com.ichbineinstudio.entities.TowerController;
import com.ichbineinstudio.entities.TowerController2;
import com.ichbineinstudio.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public Random rand;
	
	public static int xFINAL = 0;
	public static int yFINAL = 0;
	
	public static int xFINAL2 = 0;
	public static int yFINAL2 = 0;

	public static int xINITIAL = 0;
	public static int yINITIAL = 0;

	public static int xINITIAL2 = 0;
	public static int yINITIAL2 = 0;
	
	
	public World(String path){
		
		rand = new Random();
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			
			int[] colors = {0xFF000000, 0xFFffffff, 0xFFFF0000, 0xFFFF6A00, 0xFF00FFFF, 0xFF0026FF};
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					
					/*BufferedImage image = null;
					
					if(pixelAtual == colors[0]) {
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
							if(rand.nextInt(1) == 1) { image = Tile.images[1]; }else { image = Tile.images[2]; }
						}
						else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
							if(rand.nextInt(1) == 1) { image = Tile.images[1+3]; }else { image = Tile.images[2+3]; }
						}
					}else {
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
							image = Tile.images[0];
						}
						else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
							image = Tile.images[3];
						}
					}
			
					Tile tileAtual;
					if(pixelAtual == colors[0]) {
						tileAtual = new FloorTile(xx*16,yy*16,image);
					}else {
						tileAtual = new WallTile(xx*16, yy*16,image);
					}
					
					tiles[xx + (yy*WIDTH)] = tileAtual;
					
					if(pixelAtual == colors[2]) {
						Spawner spawner = new Spawner(xx*16,yy*16,16,16,0,null);
						xINITIAL = xx;
						yINITIAL = yy;
						Game.entities.add(spawner);
					}
					else if(pixelAtual == colors[3]) {
						Spawner2 spawner2 = new Spawner2(xx*16,yy*16,16,16,0,null);
						xINITIAL2 = xx;
						yINITIAL2 = yy;
						Game.entities.add(spawner2);
					}
					else if(pixelAtual == colors[4]) {
						Target targ = new Target(xx*16, yy*16, 16, 16);
						Game.entities.add(targ);
						
						xFINAL = xx;
						yFINAL = yy;
					}
					else if(pixelAtual == colors[5]) {
						Target targ2 = new Target(xx*16, yy*16, 16, 16);
						Game.entities.add(targ2);
						
						xFINAL2 = xx;
						yFINAL2 = yy;
					}*/
					
					
					
				
					
					if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}
					else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)){
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_);	
					}
					if(pixelAtual == 0xFF000000) {
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
						if(this.rand.nextInt(100) < 50) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
						}
						else {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL2);	
						}
						}
						else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
							if(this.rand.nextInt(100) < 50) {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_);
								}
								else {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_2);	
								}
								
						}
					}else if(pixelAtual == 0xFFffffff) {
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						}else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_);
						}
					}else if(pixelAtual == 0xFFFF0000) {
						//Criar Spawner!
						
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
							}else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
								tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_);
							}
						Spawner spawner = new Spawner(xx*16,yy*16,16,16,0,null);
						xINITIAL = xx;
						yINITIAL = yy;
						Game.entities.add(spawner);
					}else if(pixelAtual == 0xFFFF6A00) {
						//Criar Spawner!
						
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
							}else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
								tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_);
							}
						Spawner2 spawner2 = new Spawner2(xx*16,yy*16,16,16,0,null);
						xINITIAL2 = xx;
						yINITIAL2 = yy;
						Game.entities.add(spawner2);
					}else if(pixelAtual == 0xFF00FFFF) {
						//Targeto final do inimigo!
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
							}else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
								tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_);
							}
						
						Target targ = new Target(xx*16, yy*16, 16, 16);
						Game.entities.add(targ);
						
						xFINAL2 = xx;
						yFINAL2 = yy;
					
					}else if(pixelAtual == 0xFF0026FF) {
						//Targeto final do inimigo!
						
						if(Game.CUR_LEVEL >= 0 && Game.CUR_LEVEL <= 5) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
							}else if(Game.CUR_LEVEL == -1 || (Game.CUR_LEVEL >= 6 && Game.CUR_LEVEL <= 10)) {
								tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR_);
							}
						Target targ = new Target(xx*16, yy*16, 16, 16);
						Game.entities.add(targ);
						
						xFINAL = xx;
						yFINAL = yy;
					
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static boolean isFree(int xnext,int ynext,int width, int height){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+width-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+height-1) / TILE_SIZE;
		
		int x4 = (xnext+width-1) / TILE_SIZE;
		int y4 = (ynext+height-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String Level){
		//TODO: Aplicar m�todo para reiniciar o jogo corretamente.
		
		Camera.x = 0;
		Camera.y = 0;
		
		Spawner.timeOfMatch = 0;
		Spawner2.timeOfMatch = 0;
		
		Player.powerAttack = (100/100);
		Player2.powerAttack = (100/100);
		Player3.powerAttack = (100/100);
		Player4.powerAttack = (100/100);
		Player_path2.powerAttack = (100/100);
		Player2_path2.powerAttack = (100/100);
		Player3_path2.powerAttack = (100/100);
		Player4_path2.powerAttack = (100/100);
		
		Player.powerSpeed = (100/100);
		Player2.powerSpeed = (100/100);
		Player3.powerSpeed = (100/100);
		Player4.powerSpeed = (100/100);
		Player_path2.powerSpeed = (100/100);
		Player2_path2.powerSpeed = (100/100);
		Player3_path2.powerSpeed = (100/100);
		Player4_path2.powerSpeed = (100/100);
		
		TowerController.tinyMode = false;
		TowerController2.tinyMode = false;
		
		Game.money = 150;
		Game.life = 5;
		if(Game.numberOfPlayers == 2) {
			TowerController.life = 5;
			TowerController2.life = 5;
			TowerController.money = 150;
			TowerController2.money = 150;
			TowerController.hotbar = 1;
			TowerController2.hotbar = 1;
		}
		Game.entities.clear();
	    Game.world = new World("/level"+Game.CUR_LEVEL+".png");
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		 
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
