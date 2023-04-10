package com.gtstudios.graficos;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageFlipping {
	
	public static final int FLIP_VERTICAL = 1;
	public static final int FLIP_HORIZONTAL = -1;
	
	public static BufferedImage flip(BufferedImage img, int direction) {
	
		try {
			int width = img.getWidth(); int height = img.getHeight();
			BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					switch(direction){
					case FLIP_VERTICAL:
						flipped.setRGB(x, height - 1 - y, img.getRGB(x, y));
						break;
					case FLIP_HORIZONTAL:
						flipped.setRGB(width - 1 - x, y, img.getRGB(x, y));
						break;
					}
				}
			}
			return flipped;
		}catch(Exception e) {e.printStackTrace();}
		return null;

	}

}
