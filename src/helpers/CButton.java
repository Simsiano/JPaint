package helpers;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * 
 */
public class CButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private Dimension btnColorSize = new Dimension(25, 25);

	/**
	 * Costruttore standard adebito a bottoni per colori disattiviati.
	 */
	public CButton() {
		this.setBackground(new Color(0, 0, 0));
		this.setPreferredSize(btnColorSize);
		this.setEnabled(false);
	}
	
	/**
	 * Costruttore adebito a quei bottoni che possono essere attivi/disattivi e/o
	 * evidenziati o non.
	 * <p>
	 * @param enabled (boolean)
	 * @param focusPainted (boolean)
	 */
	public CButton(boolean enabled, boolean focusPainted) {
		this.setBackground(new Color(0,0,0));
		this.setPreferredSize(btnColorSize);
		this.setEnabled(enabled);
		this.setFocusPainted(focusPainted);
	}
	
	/**
	 * Costruttore adebito a bottoni attivi e che devono essere inizializzati
	 * con un colore specifico.
	 * <p>
	 * @param colore (Colore)
	 */
	public CButton(Color colore) {
		this.setBackground(colore);
		this.setPreferredSize(btnColorSize);
		this.setFocusPainted(false);
	}
	/*
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillRect(0, 0, 25, 25);
	}
	*/
}