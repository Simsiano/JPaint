package gui;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import graphics.Colori;

public class DialogoGradienzaDraw extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint paint = new GradientPaint(0, 75, Colori.getColoreGradienza1(), 100, 100, Colori.getColoreGradienza2());
		g2d.setPaint(paint);
		g2d.fillRect(0, 75, 100, 100);
		repaint();
	}

}
