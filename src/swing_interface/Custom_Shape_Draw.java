package swing_interface;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import graphical_logic.Colori;

public class Custom_Shape_Draw extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Colori colori = new Colori();
	
	public Custom_Shape_Draw() {}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint paint = new GradientPaint(0, 75, colori.getColoreGradienza1(), 100, 100, colori.getColoreGradienza2());
		g2d.setPaint(paint);
		g2d.fillRect(0, 75, 100, 100);
		repaint();
	}

}
