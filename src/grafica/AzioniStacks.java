package grafica;

import static grafica.Stacks.classTypeRedo;
import static grafica.Stacks.classTypeUndo;
import static grafica.Stacks.indexStackRedo;
import static grafica.Stacks.indexStackUndo;
import static grafica.Stacks.stackRedo;
import static grafica.Stacks.stackUndo;

import java.util.ArrayDeque;

import grafica.forme.Forme;
import grafica.forme.Tracciamento;
import helpers.sizedobjects.SizedDeque;
import interfaccia.Interfaccia;

public class AzioniStacks {

	private StringBuilder tipo;
	private ArrayDeque<Forme> temp;


	/*
	public void updateStack(int index) {
		indexStackUndo.push(index);
		stackUndo.push(copy(Grafica.getBufferedImage(index)));
		btnIndietro.setEnabled(true);
	}
	 */
	public void inizializza() {
		Grafica.forma = new Tracciamento();
	}

	public void updateStack() {
		indexStackUndo.push(Grafica.index);
		stackUndo.push(Grafica.forma);
		tipo =  new StringBuilder(stackUndo.peekLast().getClass().toString()).delete(0, 20);
		classTypeUndo.add(tipo.toString());
		Grafica.forma = null;
		Interfaccia.btnIndietro.setEnabled(true);

	}

	public void undo() {
		classTypeRedo.add(classTypeUndo.spit());
		indexStackRedo.push(indexStackUndo.removeFirst());
		stackRedo.push(stackUndo.removeFirst());
		Grafica.Avvio.clean();
		Interfaccia.btnAvanti.setEnabled(true);
		cicloUndo();
	}

	private void cicloUndo() {
		System.out.println(classTypeUndo.size());
		if (!stackUndo.isEmpty()) {
			if (classTypeUndo.getLast().equals("Tracciamento")) {
				undoMatita();
			}
			//			else if (classType.get(classType.size()-1).equals("Rettangolo")) {

			//			}
		}
		if (stackUndo.isEmpty()) {
			Interfaccia.btnIndietro.setEnabled(false);
		}
	}

	private void undoMatita() {
		redrawMatita();
		temp = null;
	}
	
	private void redoMatita() {
		this.temp = null;
		stackUndo.push(stackRedo.removeFirst());
		redrawMatita();
		this.temp = null;
	}
	
	
	private void redrawMatita() {
		this.temp = stackUndo.clone();
		int x1, y1, x2, y2 = 0;
		for (Forme forma : temp) {
			if (forma.getClass().toString() != "class grafica.forme.Tracciamento") {
				
			}
			for (int index = 0; index < forma.getListaPuntiIniziali().size(); index++) {
				x1 = (int) forma.getPuntoIniziale(index).getX();
				y1 = (int) forma.getPuntoIniziale(index).getY();
				x2 = (int) forma.getPuntoFinale(index).getX();
				y2 = (int) forma.getPuntoFinale(index).getY();
				Grafica.getGraphics2D(Grafica.index).drawLine(x1, y1, x2, y2);
			}
		}
		Grafica.tela.repaint();
	}
	
	public void redo() {
		if (!stackRedo.isEmpty()) {
			redoMatita();
		} else {
			
		}
	}
	/*
	private BufferedImage copy(BufferedImage bI) {
		ColorModel cm = bI.getColorModel();
		WritableRaster raster = bI.copyData(null);
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	 */
	public SizedDeque<Integer> getIndexStackUndo() {
		return indexStackUndo;
	}

	public SizedDeque<Forme> getBufferedImagesStackUndo() {
		return stackUndo;
	}

	protected static void reInitialize() {
		indexStackRedo = null;
		indexStackUndo = null;
		stackRedo = null;
		stackUndo = null;
		indexStackRedo = new SizedDeque<>(50);
		indexStackUndo = new SizedDeque<>(50);
		//		stackRedo = new SizedStack<BufferedImage>(10);
		//		stackUndo = new SizedStack<BufferedImage>(10);
		stackRedo = new SizedDeque<>(10);
		stackUndo = new SizedDeque<>(50);
		System.gc();
	}

}
