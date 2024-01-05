package grafica.forme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

public interface Forme extends Shape {

	public int getLarghezza();
	public int getAltezza();

	public void setColore(Color colore);
	public Color getColore();

	public void setSpessore(BasicStroke spessore);
	public BasicStroke getSpessore();

	public void setLinea(Point staPoint, Point endPoint);

	public void tracciaLinea(int x1, int y1, int x2, int y2);

	public ArrayList<Point> getListaPuntiIniziali();
	public ArrayList<Point> getListaPuntiFinali();
	public Point getPuntoIniziale(int index);
	public Point getPuntoFinale(int index);

	public void setRettangolo(int x, int y, int larghezza, int altezza);

}
