package graphical_logic;

import java.awt.Color;

public class Colori {
	
	private static Color colorePrimario = Color.BLACK;
	private static Color coloreSecondario = Color.BLACK;
	private static Color coloreGradienza1 = Color.BLACK;
	private static Color coloreGradienza2 = Color.BLACK;
	
	public void setColorePrimario(Color colore) {
		Colori.colorePrimario = colore;
	}

	public Color getColorePrimario() {
		return colorePrimario;
	}
	
	public void setColoreSecondario(Color colore) {
		Colori.coloreSecondario = colore;
	}
	
	public Color getColoreSecondario() {
		return coloreSecondario;
	}
	
	public void setColoreGradienza1(Color colore) {
		Colori.coloreGradienza1 = colore;
	}
	
	public void setColoreGradienza2(Color colore) {
		Colori.coloreGradienza2 = colore;
	}
	
	public Color getColoreGradienza1() {
		return coloreGradienza1;
	}
	
	public Color getColoreGradienza2() {
		return coloreGradienza2;
	}

}
