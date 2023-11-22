package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Layers {
	
	protected static ArrayList<BufferedImage> bufferedImagesLayers = new ArrayList<BufferedImage>();
	protected static ArrayList<Graphics2D> graphics2dsLayers = new ArrayList<Graphics2D>();
	
	protected static ArrayList<Color> graphics2dColors = new ArrayList<Color>();
	protected static ArrayList<BasicStroke> graphics2dBasicStrokes = new ArrayList<BasicStroke>();

}
