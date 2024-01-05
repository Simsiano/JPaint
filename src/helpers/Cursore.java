package helpers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import grafica.Grafica;

public class Cursore {

	private static JPanel tela;

	private static final int LARGHEZZA = 32;
	private static final int ALTEZZA = 32;

	private static Cursor cursore;

	private static BufferedImage cursoreBI;
	private static Point hotspot;
	private static Color colore;

	private static int grandezza;

	private static Tipo tipoCursore;
	private static Forma tipoForma;

	public Cursore(JPanel tela, int grandezza, Tipo tipoCursore, Forma tipoForma) {
		Cursore.tela = tela;
		Cursore.grandezza = grandezza;
		Cursore.hotspot = new Point(grandezza/2, grandezza/2);
		Cursore.tipoCursore = tipoCursore;
		Cursore.tipoForma = tipoForma;
		Cursore.colore = Color.BLACK;
		switchCursore();
	}

	public static void setTipo(int grandezza, Tipo tipoCursore, Forma tipoForma) {
		Cursore.grandezza = grandezza;
		Cursore.tipoCursore = tipoCursore;
		Cursore.tipoForma = tipoForma;
		Cursore.hotspot.setLocation(grandezza/2, grandezza/2);
		switchCursore();
	}

	public static void setGrandezza(int grandezza) {
		Cursore.grandezza = grandezza;
		Cursore.hotspot.setLocation(grandezza/2, grandezza/2);
		switchCursore();
	}

	public static void update() {
		Cursore.grandezza = Grafica.getStrokeWidth();
		Cursore.hotspot.setLocation(grandezza/2, grandezza/2);
		switchCursore();
	}

	public static void update(Color colore) {
		Cursore.colore = colore;
		switchCursore();
	}

	private static void switchCursore() {
		switch (tipoCursore) {
		case MATITA:
			setAsMatita();
			break;
		case GOMMA:
			setAsGomma();
			break;
		case MIRINO:
			break;
		}
	}

	private static void setAsMatita() {
		cursoreBI = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
		switch (tipoForma) {
		case QUADRATA:
			matitaQuadrata();
			break;
		case TONDA:
			matitaTonda();
			break;
		}
		cursore = Toolkit.getDefaultToolkit().createCustomCursor(cursoreBI, hotspot, "matita");
		cursoreBI = null;
		tela.setCursor(cursore);
	}

	private static void setAsGomma() {
		cursoreBI = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
		switch (tipoForma) {
		case QUADRATA:
			gommaQuadrata();
			break;
		case TONDA:
			gommaTonda();
			break;
		}
		cursore = Toolkit.getDefaultToolkit().createCustomCursor(cursoreBI, hotspot, "gomma");
		cursoreBI = null;
		tela.setCursor(cursore);
	}

	private static void matitaQuadrata() {
		Graphics2D g2d = cursoreBI.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, grandezza, grandezza);
		g2d.setColor(colore);
		g2d.fillRect(1, 1, grandezza-2, grandezza-2);
		g2d.dispose();
	}

	private static void matitaTonda() {
		Graphics2D g2d = cursoreBI.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillOval(0, 0, grandezza, grandezza);
		g2d.setColor(colore);
		g2d.fillOval(1, 1, grandezza-2, grandezza-2);
		g2d.dispose();
	}

	private static void gommaQuadrata() {
		Graphics2D g2d = cursoreBI.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, grandezza, grandezza);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(1, 1, grandezza-2, grandezza-2);
		g2d.dispose();
	}

	private static void gommaTonda() {
		Graphics2D g2d = cursoreBI.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillOval(0, 0, grandezza, grandezza);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(1, 1, grandezza-2, grandezza-2);
		g2d.dispose();
	}

	public enum Tipo {
		MATITA,
		GOMMA,
		MIRINO
	}

	public enum Forma {
		QUADRATA,
		TONDA
	}

	public static Cursor getCursore() {
		return Cursore.cursore;
	}

}