package com.ichbineinstudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ichbineinstudio.main.Game;
import com.ichbineinstudio.world.Camera;
import com.ichbineinstudio.world.World;

public class TowerController2 extends Entity{
	
	public boolean isPressed = false;
	public int xTarget, yTarget;
	
	public static int life = 5;
	public static int money = 150;
	public static int towerSelected = 1;
	public static int deviceSelected = 1;
	public static int score = 0;
	
	public static boolean tinyMode = false;
	
	public static int hotbar = 1;
	
	public boolean canSet = true;

	public TowerController2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

	}
	
	public static boolean blocked(Entity tower, int xx, int yy) {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
				if(Entity.isColidding(e, tower)) { 
					return true; //Já existe uma torre
				}
				
			}
		}
		
		if(tower.width == 32 && tower.height == 32) {
			if(World.isFree(xx, yy, 16, 16) || World.isFree(xx+16, yy, 16, 16) || World.isFree(xx, yy+16, 16, 16) || World.isFree(xx+16, yy+16, 16, 16)) {
				return true; //Colide com a estrada
			}
		}
		else if(tower.width == 16 && tower.height == 16) {
			if(World.isFree(xx, yy, 16, 16)) {
				return true; //Colide com a estrada
			}
		}
		
		return false;
	}
	
	public void tick() {
		
		if(Game.numberOfPlayers == 2) {
			if(Game.CUR_LEVEL == 0) {
			if(this.xTarget + 16 > 6*16) {
				this.xTarget = 6*16;
			}
			if(this.xTarget - 1 < 0) {
				this.xTarget = 0;
			}
			if(this.yTarget + 16 > 10*16) {
				this.yTarget = 9*16;
			}
			if(this.yTarget - 1 < 0) {
				this.yTarget = 0;
			}
			}else if(Game.CUR_LEVEL == -1) {
				if(this.xTarget + 16 > 15*16) {
					this.xTarget = 15*16;
				}
				if(this.xTarget - 1 < 0) {
					this.xTarget = 0;
				}
				if(this.yTarget + 16 > 10*16) {
					this.yTarget = 10*16;
				}
				if(this.yTarget - 1 < 5*16) {
					this.yTarget = 5*16;
				}
			}
		}
		
		if(hotbar == 1) {
		if(isPressed){
			if(towerSelected == 1) {
			isPressed = false;
			boolean canSetTower = true;
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			
			if(!tinyMode) {
			
			Player2_path2 player2_path2 = new Player2_path2(xx, yy, 32, 32, 0, Game.spritesheet.getSprite(6*16, 6*16, 32, 32));
			
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
					if(Entity.isColidding(e, player2_path2)) {
						canSetTower = false;
						//System.out.println("J� existe uma torre");
					}
					
				}
			}
			
			if(World.isFree(xx, yy, 16, 16) || World.isFree(xx+16, yy, 16, 16) || World.isFree(xx, yy+16, 16, 16) || World.isFree(xx+16, yy+16, 16, 16)) {
				canSetTower = false;
				//System.out.println("A� � estrada");
			}
			
			if(canSetTower) {
				if(this.money >= 25) {
					//System.out.println("Adicionado");
					Game.entities.add(player2_path2);	
				this.money -= 25;
				}else {
				
				}
			}
			}else {
				Player2_path2 player2_path2 = new Player2_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(6*16, 6*16, 32, 32));
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
						if(Entity.isColidding(e, player2_path2)) {
							canSetTower = false;
							//System.out.println("J� existe uma torre");
						}
						
					}
				}
				
				if(World.isFree(xx, yy, 16, 16)) {
					canSetTower = false;
					 //System.out.println("A� � estrada");
				}
				
				if(canSetTower) {
					if(this.money >= 25) {
						//System.out.println("Adicionado");
						Game.entities.add(player2_path2);	
					this.money -= 25;
					}else {
					
					}
				}
			}
			}else if(towerSelected == 2) {
				isPressed = false;
				boolean canSetTower = true;
				int xx = (xTarget/16) * 16;
				int yy = (yTarget/16) * 16;
				
				if(!tinyMode) {
				
				Player_path2 player_path2 = new Player_path2(xx, yy, 32, 32, 0, Game.spritesheet.getSprite(6*16, 8*16, 32, 32));
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
						if(Entity.isColidding(e, player_path2)) {
							canSetTower = false;
							//System.out.println("J� existe uma torre");
						}
						
					}
				}
				
				if(World.isFree(xx, yy, 16, 16) || World.isFree(xx+16, yy, 16, 16) || World.isFree(xx, yy+16, 16, 16) || World.isFree(xx+16, yy+16, 16, 16)) {
					canSetTower = false;
					//System.out.println("A� � estrada");
				}
				
				if(canSetTower) {
					canSet = true;
					if(money >= 50) {
						//	System.out.println("Adicionado");
					Game.entities.add(player_path2);	
					this.money -= 50;
					}else {
						canSet = false;
					}
				}
				}else {
					Player_path2 player_path2 = new Player_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(6*16, 8*16, 32, 32));
					
					for(int i = 0; i < Game.entities.size(); i++) {
						Entity e = Game.entities.get(i);
						if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
							if(Entity.isColidding(e, player_path2)) {
								canSetTower = false;
								//System.out.println("J� existe uma torre");
							}
							
						}
					}
					if(World.isFree(xx, yy, 16, 16)) {
						canSetTower = false;
						 //System.out.println("A� � estrada");
					}
					
					if(canSetTower) {
						canSet = true;
						if(money >= 50) {
							//	System.out.println("Adicionado");
						Game.entities.add(player_path2);	
						this.money -= 50;
						}else {
							canSet = false;
						}
					}
					
				}
			}else if(towerSelected == 3) {
				isPressed = false;
				boolean canSetTower = true;
				int xx = (xTarget/16) * 16;
				int yy = (yTarget/16) * 16;
				
				if(!tinyMode) {
				
				Player3_path2 player3_path2 = new Player3_path2(xx, yy, 32, 32, 0, Game.spritesheet.getSprite(10*16, 8*16, 32, 32));
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
						if(Entity.isColidding(e, player3_path2)) {
							canSetTower = false;
							//System.out.println("J� existe uma torre");
						}
						
					}
				}
				
				if(World.isFree(xx, yy, 16, 16) || World.isFree(xx+16, yy, 16, 16) || World.isFree(xx, yy+16, 16, 16) || World.isFree(xx+16, yy+16, 16, 16)) {
					canSetTower = false;
					 //System.out.println("A� � estrada");
				}
				
				if(canSetTower) {
					canSet = true;
					if(this.money >= 150) {
					Game.entities.add(player3_path2);	
					//System.out.println("Adicionado");
					this.money -= 150;
					}else {
						canSet = false;
					}
				}
				}else {
					Player3_path2 player3_path2 = new Player3_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(10*16, 8*16, 32, 32));
					
					for(int i = 0; i < Game.entities.size(); i++) {
						Entity e = Game.entities.get(i);
						if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
							if(Entity.isColidding(e, player3_path2)) {
								canSetTower = false;
								//System.out.println("J� existe uma torre");
							}
							
						}
					}
					if(World.isFree(xx, yy, 16, 16)) {
						canSetTower = false;
						 //System.out.println("A� � estrada");
					}
					
					if(canSetTower) {
						canSet = true;
						if(this.money >= 150) {
						Game.entities.add(player3_path2);	
						//System.out.println("Adicionado");
						this.money -= 150;
						}else {
							canSet = false;
						}
					}
				}
			}else if(towerSelected == 4) {
				isPressed = false;
				boolean canSetTower = true;
				int xx = (xTarget/16) * 16;
				int yy = (yTarget/16) * 16;
				
				if(!tinyMode) {
				
				Player4_path2 player4 = new Player4_path2(xx, yy, 32, 32, 0, Game.spritesheet.getSprite(6*16, 8*16, 32, 32));
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
						if(Entity.isColidding(e, player4)) {
							canSetTower = false;
							//System.out.println("J� existe uma torre");
						}
						
					}
				}
				
				if(World.isFree(xx, yy, 16, 16) || World.isFree(xx+16, yy, 16, 16) || World.isFree(xx, yy+16, 16, 16) || World.isFree(xx+16, yy+16, 16, 16)) {
					canSetTower = false;
					//System.out.println("A� � estrada");
				}
				
				if(canSetTower) {
					canSet = true;
					
						if(money >= 250) {
							//System.out.println("Adicionado");
							Game.entities.add(player4);	
						money -= 250;
						}else {
							canSet = false;
						}
						
				}
				}else {
					Player4_path2 player4 = new Player4_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(6*16, 8*16, 32, 32));
					
					for(int i = 0; i < Game.entities.size(); i++) {
						Entity e = Game.entities.get(i);
						if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
							if(Entity.isColidding(e, player4)) {
								canSetTower = false;
								//System.out.println("J� existe uma torre");
							}
							
						}
					}
					
					if(World.isFree(xx, yy, 16, 16)) {
						canSetTower = false;
						 //System.out.println("A� � estrada");
					}
					
					if(canSetTower) {
						canSet = true;
						
							if(money >= 250) {
								//System.out.println("Adicionado");
								Game.entities.add(player4);	
							money -= 250;
							}else {
								canSet = false;
							}
							
					}
				}
			
			}else if(towerSelected == 5) {
				isPressed = false;
				boolean canSetTower = true;
				int xx = (xTarget/16) * 16;
				int yy = (yTarget/16) * 16;
				
				AttackDevice a = new AttackDevice(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(4*16, 10*16, 16, 16)) ;
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
						if(Entity.isColidding(e, a)) {
							canSetTower = true;
							Game.entities.remove(e);
							Game.entities.remove(a);
							if(e instanceof AttackDevice) {
								Player_path2.powerAttack -= 0.2;
								Player2_path2.powerAttack -= 0.1;
								Player3_path2.powerAttack -= 0.1;
								Player4_path2.powerAttack -= 0.2;
							}
							if(e instanceof SpeedDevice) {
								Player_path2.powerSpeed -= 0;
								Player2_path2.powerSpeed -= 0.1;
								Player3_path2.powerSpeed -= 0.1;
								Player4_path2.powerSpeed -= 0.1;
							}
							if(e instanceof sizeDevice) {
								tinyMode = false;
							}
							
							//System.out.println("J� existe uma torre");
						}
						
					}
				}
		}
		}
		}else if(hotbar == 2) {
			if(isPressed) {
				isPressed = false;
			if(deviceSelected == 1) {
			
			boolean canSetTower = true;
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			
			AttackDevice_path2 attackDevice1_path2 = new AttackDevice_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(10*16, 8*16, 32, 32));
			
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
					if(Entity.isColidding(e, attackDevice1_path2)) {
						canSetTower = false;
						//System.out.println("J� existe uma torre");
					}
					
				}
			}
			
			if(World.isFree(xx, yy, 16, 16)) {
				canSetTower = false;
				 //System.out.println("A� � estrada");
			}
			
			if(canSetTower) {
				canSet = true;
				if(this.money >= 200) {
				Game.entities.add(attackDevice1_path2);
				Player_path2.powerAttack += 0.2;
				Player2_path2.powerAttack += 0.1;
				Player3_path2.powerAttack += 0.1;
				Player4_path2.powerAttack += 0.2;
				//System.out.println("Adicionado");
				this.money -= 200;
				}else {
					canSet = false;
				}
			}
			
		}else if(deviceSelected == 2) {
			isPressed = false;
			boolean canSetTower = true;
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			
			SpeedDevice_path2 speedDevice1 = new SpeedDevice_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(10*16, 8*16, 32, 32));
			
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2 || e instanceof sizeDevice_path2) {
					if(Entity.isColidding(e, speedDevice1)) {
						canSetTower = false;
						//System.out.println("J� existe uma torre");
					}
					
				}
			}
			
			if(World.isFree(xx, yy, 16, 16)) {
				canSetTower = false;
				 //System.out.println("A� � estrada");
			}
			
			if(canSetTower) {
				canSet = true;
				if(Game.numberOfPlayers == 2) {
					if(money >= 250) {
						//System.out.println("Adicionado");
						Game.entities.add(speedDevice1);	
						Player_path2.powerSpeed += 0;
						Player2_path2.powerSpeed += 0.1;
						Player3_path2.powerSpeed += 0.1;
						Player4_path2.powerSpeed += 0.1;
					money -= 250;
					}else {
						canSet = false;
					}
					}else if(Game.numberOfPlayers == 1) {
						
							if(Game.money >= 250) {
								//System.out.println("Adicionado");
								Game.entities.add(speedDevice1);
								Player_path2.powerSpeed += 0;
								Player2_path2.powerSpeed += 0.1;
								Player3_path2.powerSpeed += 0.1;
								Player4_path2.powerSpeed += 0.1;
							Game.money -= 250;
							}else {
								canSet = false;
							}
						
					
					}
				
			}
		}else if(deviceSelected == 3) {
			isPressed = false;
			boolean canSetTower = true;
			int xx = (xTarget/16) * 16;
			int yy = (yTarget/16) * 16;
			
			sizeDevice_path2 sizeDevice1 = new sizeDevice_path2(xx, yy, 16, 16, 0, Game.spritesheet.getSprite(10*16, 8*16, 32, 32));
			
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Player2_path2 || e instanceof Player_path2 || e instanceof Player3_path2 || e instanceof Player4_path2 || e instanceof AttackDevice_path2 || e instanceof SpeedDevice_path2  || e instanceof sizeDevice_path2) {
					if(Entity.isColidding(e, sizeDevice1)) {
						canSetTower = false;
						//System.out.println("J� existe uma torre");
					}
					
				}
			}
			
			if(World.isFree(xx, yy, 16, 16)) {
				canSetTower = false;
				 //System.out.println("A� � estrada");
			}
			
			if(canSetTower) {
				canSet = true;
			
				if(!tinyMode) {
					if(money >= 500) {
						//System.out.println("Adicionado");
						Game.entities.add(sizeDevice1);	
						tinyMode = true;
					money -= 500;
					}else {
						canSet = false;
					}
				}else {
					canSet = false;
				}
					
				
			
		}
		}
			
		}
		}
		
		
	}
	
	public void render(Graphics g) {
		
		if(hotbar == 1) {
			if(!tinyMode) {
				if(towerSelected != 5) {
		g.setColor(new Color(255, 204, 51, 130));
		g.fillRect((xTarget/16)*16,  (yTarget/16)*16, 32, 32);
				}else {
					g.setColor(new Color(255, 204, 51, 130));
					g.fillRect((xTarget/16)*16,  (yTarget/16)*16, 16, 16);
				}
			}else {
				g.setColor(new Color(255, 204, 51, 130));
				g.fillRect((xTarget/16)*16,  (yTarget/16)*16, 16, 16);	
			}
		}else if(hotbar == 2) {
			g.setColor(new Color(255, 204, 51, 130));
			g.fillRect((xTarget/16)*16,  (yTarget/16)*16, 16, 16);	
		}
		
		int xx = (xTarget/16)*16;
		int yy = (yTarget/16)*16 + Camera.y;
		if(!tinyMode && hotbar == 1 && (this.towerSelected >= 1 && towerSelected <= 4)) {
			Player player = new Player(xx, yy, 32, 32, 0, null);
			if(this.blocked(player, xx, yy)) {
				g.setColor(new Color(230, 0, 0, 200));
				g.fillRect(xx, yy - Camera.y, 32, 32);
			}
		}else if(tinyMode || hotbar == 2) {
			Player player2 = new Player(xx, yy, 16, 16, 0, null);
			if(this.blocked(player2, xx, yy)) {
				g.setColor(new Color(230, 0, 0, 150));
				g.fillRect(xx, yy - Camera.y, 16, 16);
			}
		}
		
	}

}
