package grafica;

import grafica.forme.Forme;
import helpers.sizedobjects.SizedDeque;
import helpers.sizedobjects.SizedList;

public class Stacks {

//	protected static SizedStack<Integer> indexStackUndo = new SizedStack<Integer>(10);
//	protected static SizedStack<BufferedImage> stackUndo = new SizedStack<BufferedImage>(10);

//	protected static SizedStack<Integer> indexStackRedo = new SizedStack<Integer>(10);
//	protected static SizedStack<BufferedImage> stackRedo = new SizedStack<BufferedImage>(10);

	protected static SizedDeque<Forme> stackUndo = new SizedDeque<>(50);
	protected static SizedDeque<Integer> indexStackUndo = new SizedDeque<>(50);

	protected static SizedDeque<Forme> stackRedo = new SizedDeque<>(50);
	protected static SizedDeque<Integer> indexStackRedo = new SizedDeque<>(50);
	
	protected static SizedList<String> classTypeUndo = new SizedList<>(50);
	protected static SizedList<String> classTypeRedo = new SizedList<>(50);

}
