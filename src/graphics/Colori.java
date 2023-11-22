package graphics;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Point;

public class Colori {
	
	private static Color colorePrimario = Color.BLACK;
	private static Color coloreSecondario = Color.BLACK;
	private static Color coloreGradienza1 = Color.BLACK;
	private static Color coloreGradienza2 = Color.BLACK;
	private static GradientPaint coloreForme = null;
	
	public static void setColorePrimario(Color colore) {
		Colori.colorePrimario = colore;
	}
	
	public static Color getColorePrimario() {
		return colorePrimario;
	}
	
	public static void setColoreSecondario(Color colore) {
		Colori.coloreSecondario = colore;
	}
	
	public static Color getColoreSecondario() {
		return coloreSecondario;
	}
	
	public static GradientPaint getColoreForme() {
		return Colori.coloreForme;
	}
	
	public static void setColoreForme(Color colore) {
		Colori.coloreForme = new GradientPaint(0,0,colore,0,0,colore);
	}
	
	public static void setColoreForme(Color colorePrimario, Color coloreSecondario) {
		Colori.coloreForme = new GradientPaint(0,0,colorePrimario,0,0,coloreSecondario);
	}
	
	public static void setColoreForme(Point startPoint, Point endPoint) {
		Colori.coloreForme = new GradientPaint(startPoint, coloreForme.getColor1(), endPoint, coloreForme.getColor2());
	}
	
	public static void setColoreGradienza1(Color colore) {
		Colori.coloreGradienza1 = colore;
	}
	
	public static void setColoreGradienza2(Color colore) {
		Colori.coloreGradienza2 = colore;
	}
	
	public static Color getColoreGradienza1() {
		return coloreGradienza1;
	}
	
	public static Color getColoreGradienza2() {
		return coloreGradienza2;
	}

}
