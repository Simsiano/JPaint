package helpers;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TelaVuota extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int larghezza;
	private int altezza;
	
	public TelaVuota(int larghezza, int altezza) {
		this.larghezza = larghezza;
		this.altezza = altezza;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, larghezza, altezza);
	}

}
