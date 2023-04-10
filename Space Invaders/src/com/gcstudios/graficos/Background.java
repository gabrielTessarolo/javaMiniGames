package com.gcstudios.graficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Background {
	
	public int index = 0;
	public int maxIndex = 3;
	public int frames = 0;
	public int maxFrames = 30;
	
	public static int positionY = 0;
	public static int positionY2 = 160;
	
	public BufferedImage[] background;
	
	public void tick() {
		
		if(positionY <= Game.HEIGHT) {
			positionY+=1;
		}else {
			positionY = - 160;
		}
		
		if(positionY2 <= Game.HEIGHT) {
			positionY2+=1;
		}else {
			positionY2 = - 160;
		}
		
		
		frames++;
		if(frames > maxFrames) {
			index++;
			frames = 0;
		}
		if(index > maxIndex) {
			index = 0;
		}
	}
	
	public void render(Graphics g) {
		
		background = new BufferedImage[4];
		
		background[0] = Game.spritesheet.getSprite(130, 0, 30, 40);
		background[1] = Game.spritesheet.getSprite(130, 40, 30, 40);
		background[2] = Game.spritesheet.getSprite(130, 80, 30, 40);
		background[3] = Game.spritesheet.getSprite(130, 120, 30, 40);
		g.drawImage(background[index], 0, positionY, 120, 160, null);
		g.drawImage(background[index], 0, positionY2, 120, 160, null);

}
}
