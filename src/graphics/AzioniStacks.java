package graphics;

import static graphics.Stacks.indexStackRedo;
import static graphics.Stacks.indexStackUndo;
import static graphics.Stacks.stackRedo;
import static graphics.Stacks.stackUndo;
import static gui.Interfaccia.btnIndietro;
//import static gui.Interfaccia.btnAvanti;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Stack;

public class AzioniStacks {

	public void updateStack(int index) {
		indexStackUndo.push(index);
		stackUndo.push(copy(Grafica.getBufferedImage(index)));
		btnIndietro.setEnabled(true);
	}

	public void undo() {
		if (!stackUndo.isEmpty()) { 
			indexStackRedo.push(indexStackUndo.peek());
			stackRedo.push(copy(Grafica.getBufferedImage(indexStackUndo.peek())));
			Graphics2D temp = Grafica.getGraphics2D(indexStackUndo.peek());
			Color color = temp.getColor();
			BasicStroke stroke = (BasicStroke) temp.getStroke();
			Grafica.setBufferedImage(indexStackUndo.peek(), stackUndo.pop());
			Grafica.createGraphics2D(indexStackUndo.pop(),color,stroke);
			Grafica.tela.repaint();
		}
		if (stackUndo.isEmpty()) {
			btnIndietro.setEnabled(false);
		}
	}

	public void redo() {
		if (!stackRedo.isEmpty()) {
			
		}
	}

	private BufferedImage copy(BufferedImage bI) {
		ColorModel cm = bI.getColorModel();
		WritableRaster raster = bI.copyData(null);
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public Stack<Integer> getIndexStackUndo() {
		return indexStackUndo;
	}

	public Stack<BufferedImage> getBufferedImagesStackUndo() {
		return stackUndo;
	}
	
}
