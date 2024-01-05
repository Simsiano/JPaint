package grafica.forme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class FormeA implements Forme {
	
	@Override
	public Rectangle getBounds() {
		return null;	
	}
	
	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}

	@Override
	public boolean contains(double x, double y) {
		return false;
	}
	
	@Override
	public boolean contains(Point2D p) {
		return false;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return false;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return false;
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return false;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return false;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return null;
	}

	@Override
	public int getLarghezza() {
		return 0;
	}

	@Override
	public int getAltezza() {
		return 0;
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
	public void setLinea(Point staPoint, Point endPoint) {
	}

	@Override
	public void tracciaLinea(int x1, int y1, int x2, int y2) {
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

	@Override
	public void setRettangolo(int x, int y, int larghezza, int altezza) {
	}
}
