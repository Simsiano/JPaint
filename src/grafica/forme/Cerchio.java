package grafica.forme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Cerchio extends Ellipse2D.Float implements Forme {

	private static final long serialVersionUID = 1L;

	private int larghezza;
	private int altezza;

	@Override
	public int getLarghezza() {
		return this.larghezza;
	}

	@Override
	public int getAltezza() {
		return this.altezza;
	}

	@Override
	public void setRettangolo(int x, int y, int width, int height) {}

	@Override
	public void setLinea(Point staPoint, Point endPoint) {}

	@Override
	public void tracciaLinea(int x1, int y1, int x2, int y2) {
	}

	@Override
	public void setColore(Color colore) {
	}

	@Override
	public Color getColore() {
		return null;
	}

	@Override
	public void setSpessore(BasicStroke spessore) {
	}

	@Override
	public BasicStroke getSpessore() {
		return null;
	}

	@Override
	public ArrayList<Point> getListaPuntiIniziali() {
		return null;
	}

	@Override
	public ArrayList<Point> getListaPuntiFinali() {
		return null;
	}

	@Override
	public Point getPuntoIniziale(int index) {
		return null;
	}

	@Override
	public Point getPuntoFinale(int index) {
		return null;
	}

}