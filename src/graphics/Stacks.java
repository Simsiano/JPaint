package graphics;

import java.awt.image.BufferedImage;

import helpers.SizedStack;

public class Stacks {

	protected static SizedStack<Integer> indexStackUndo = new SizedStack<Integer>(10);
	protected static SizedStack<BufferedImage> stackUndo = new SizedStack<BufferedImage>(10);

	protected static SizedStack<Integer> indexStackRedo = new SizedStack<Integer>(10);
	protected static SizedStack<BufferedImage> stackRedo = new SizedStack<BufferedImage>(10);

}
