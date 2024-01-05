package grafica.forme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Tracciamento extends Line2D.Float implements Forme {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Point> puntiIniziali;
	private ArrayList<Point> puntiFinali;
	private Color colore;
	private BasicStroke spessore;

	public Tracciamento() {
		puntiIniziali = new ArrayList<Point>();
		puntiFinali = new ArrayList<Point>();
	}

	@Override
	public void tracciaLinea(int x1, int y1, int x2, int y2) {
		this.puntiIniziali.add(new Point(x1,y1));
		this.puntiFinali.add(new Point(x2,y2));
		this.setLine(x1, y1, x2, y2);
	}
	
	@Override
	public ArrayList<Point> getListaPuntiIniziali() {
		return this.puntiIniziali;
	}

	@Override
	public ArrayList<Point> getListaPuntiFinali() {
		return this.puntiFinali;
	}

	@Override
	public Point getPuntoIniziale(int index) {
		return this.puntiIniziali.get(index);
	}

	@Override
	public Point getPuntoFinale(int index) {
		return this.puntiFinali.get(index);
	}

	@Override
	public void setColore(Color colore) {
		this.colore = colore;
	}

	@Override
	public Color getColore() {
		return colore;
	}

	@Override
	public void setSpessore(BasicStroke spessore) {
		this.spessore = spessore;
	}

	@Override
	public BasicStroke getSpessore() {
		return this.spessore;
	}


	@Override
	public int getLarghezza() {
		return this.getBounds().width;
	}

	@Override
	public int getAltezza() {
		return this.getBounds().height;
	}

	@Override
	public void setLinea(Point staPoint, Point endPoint) {
	}

	@Override
	public void setRettangolo(int x, int y, int larghezza, int altezza) {
	}

}
