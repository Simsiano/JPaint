package graphics;

import static graphics.Grafica.cerchio;
import static graphics.Grafica.linea;
import static graphics.Grafica.quadrato;
import static graphics.Grafica.rettangolo;
import static graphics.Grafica.seleziona;

import java.awt.Graphics;
import java.awt.Graphics2D;

import helpers.TelaVuota;

public class Tela extends TelaVuota {
	
	private static final long serialVersionUID = 1L;
	
	public Grafica grafica;
	private Graphics2D g2d;
	
	public Tela(int larghezza, int altezza) {
		
		super(larghezza, altezza);
		
		grafica = new Grafica(this, larghezza, altezza);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;

		g2d.setStroke(Grafica.getGraphics2D(Grafica.index).getStroke());
		g2d.setPaint(Grafica.getGraphics2D(Grafica.index).getPaint());
		
		if (seleziona != null) {
			grafica.draw(seleziona, g2d);
		}
		
		if (linea != null) {
			grafica.draw(linea, g2d);
		}
		
		if (quadrato != null) {
			grafica.draw(quadrato, g2d);
		}
		
		if (rettangolo != null) {
			grafica.draw(rettangolo, g2d);
		}
		
		if (cerchio != null) {
			grafica.draw(cerchio, g2d);
		}
		
		grafica.drawAllLayers(g2d);
		
	}

}
