package grafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import helpers.TelaVuota;

public class Tela extends TelaVuota {

	private static final long serialVersionUID = 1L;

	public Grafica grafica;
	public static Graphics2D g2d;

	public static Timer repaintTimer;
	private static int delay;

	private static int larghezza;
	private static int altezza;

	public Tela(JPanel panel, int larghezza, int altezza) {

		super(larghezza, altezza);

		Tela.larghezza = larghezza;
		Tela.altezza = altezza;
		
		Tela.delay = 16;

		this.grafica = new Grafica(panel, this, larghezza, altezza);

		//		Grafica.Debug.clean();
		
		Tela.repaintTimer = new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();

			}}
		);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;

		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		//g2d.setStroke(Grafica.getGraphics2D(Grafica.index).getStroke());
		//g2d.setPaint(Grafica.getGraphics2D(Grafica.index).getPaint());

		if (Grafica.forma != null) {
			System.out.println(Grafica.forma.getClass());
			grafica.draw(Grafica.forma, g2d);
		}

		if (Grafica.cerchio != null) {
			grafica.draw(Grafica.cerchio, g2d);
		}

		grafica.drawAllLayers(g2d);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(larghezza, altezza);
	}
	
	public static void setDelay(int delay) {
		Tela.delay = delay;
	}
	
}
