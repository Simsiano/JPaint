package jpaint_graphics;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

public class jpaint_grafica extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage ImageBuffer = new BufferedImage(1500, 600, BufferedImage.TYPE_INT_ARGB);
	private Graphics2D gBuffer = ImageBuffer.createGraphics();
	public Rectangle quadrato;
	public Rectangle rettangolo;
	public Ellipse2D.Double cerchio;
//	public Circle cerchio;
	private int LARGHEZZA = 800;
	private int ALTEZZA = 600;
	private Color colorePrimario;
	private Color coloreSecondario;
	private Color coloreForme;
	private boolean pieno;
	private int Spessore = 3;
	private int spessoreForme = 3;
	private int tempSpessore = 3;
	private int posX = -1;
	private int posY = -1;

	public jpaint_grafica() {
		pulisci();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gBuffer = ImageBuffer.createGraphics();
		gBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(ImageBuffer, 0, 0, this);
		
		if (quadrato != null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(spessoreForme));
			g2d.setColor(coloreForme);
			if (pieno == true) {
				g2d.fill(quadrato);
			} else {}
			g2d.draw( quadrato );
			repaint();
		}
		
		if (rettangolo != null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(spessoreForme));
			g2d.setColor(coloreForme);
			if (pieno == true) {
				g2d.fill(rettangolo);
			} else {}
			g2d.draw( rettangolo );
			repaint();
		}
		
		if (cerchio != null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(spessoreForme));
			g2d.setColor(coloreForme);
			if (pieno == true) {
				g2d.fill(cerchio);
			} else {}
			g2d.draw( cerchio );
			repaint();
		}

	}

	public void pulisci() {
		LARGHEZZA = ImageBuffer.getWidth();
		ALTEZZA = ImageBuffer.getHeight();
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0, 0, LARGHEZZA, ALTEZZA);
		repaint();
	}

	public void tracciaMatita(int x, int y, Color colore) {
		if (posX != -1) {
//        	float alpha = 0.25f;
//        	Color color = new Color(colore.getRed(), colore.getGreen(), colore.getBlue(), alpha);
			gBuffer.setColor(colore);
			gBuffer.setStroke(new BasicStroke(Spessore));

			gBuffer.drawLine(x, y, posX, posY);
			repaint();
		}
		posX = x;
		posY = y;
	}

	public void tracciaGomma(int x, int y) {
		if (posX != -1) {
			gBuffer.setColor(Color.WHITE);
			gBuffer.setStroke(new BasicStroke(20.0f));
			gBuffer.drawLine(x, y, posX, posY);
			repaint();
		}
		posX = x;
		posY = y;
	}
	
	public void tracciaPennello(int x, int y, Color colore) {
		if (posX != -1) {
        	float alpha = 0.25f;
        	Color color = new Color(colore.getRed(), colore.getGreen(), colore.getBlue(), alpha);
			gBuffer.setColor(color);
			gBuffer.setStroke(new BasicStroke(Spessore));

			gBuffer.drawLine(x, y, posX, posY);
			repaint();
		}
		posX = x;
		posY = y;
	}

	public void tracciaAerografo(int x, int y, Color colore) {
		if (posX != -1) {
			gBuffer.setColor(null);
			gBuffer.setColor(colore);
			Random random = new Random();
			while (true) {
				int quadrante1X = random.nextInt(41);
				int quadrante1Y = random.nextInt(41);
				int quadrante2X = random.nextInt(41);
				int quadrante2Y = random.nextInt(41);
				int quadrante3X = random.nextInt(41);
				int quadrante3Y = random.nextInt(41);
				int quadrante4X = random.nextInt(41);
				int quadrante4Y = random.nextInt(41);
				gBuffer.fillOval(x + quadrante1X, y + quadrante1Y, 3, 3);
				gBuffer.fillOval(x - quadrante2X, y + quadrante2Y, 3, 3);
				gBuffer.fillOval(x - quadrante3X, y - quadrante3Y, 3, 3);
				gBuffer.fillOval(x + quadrante4X, y - quadrante4Y, 3, 3);
				repaint();
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
		}
		posX = x;
		posY = y;
	}
/*
	public void aggiungiCerchio(int x, int y, Color colore) {
		gBuffer.setColor(colore);
		gBuffer.setStroke(new BasicStroke(Spessore));
		gBuffer.fillOval(x - 10, y - 10, 20, 20);
		repaint();
	}
*/	
	public void aggiungiQuadrato(Rectangle quadrato, Color colore) {
		
		Graphics2D g2d = (Graphics2D)ImageBuffer.getGraphics();
		g2d.setColor( colore );
		g2d.setStroke(new BasicStroke(spessoreForme));
		if (pieno == true) {
			g2d.fill(quadrato);
		}
		g2d.draw( quadrato );
		repaint();
		
	}
	
	public void tracciamentoQuadrato(Point startPoint, int meX, int meY) {
		int width = Math.abs(startPoint.x - meX);
        int height = Math.abs(startPoint.y - meY);
        int size = Math.min(width, height);
        int x = meX > startPoint.x ? startPoint.x : startPoint.x - size;
        int y = meY > startPoint.y ? startPoint.y : startPoint.y - size;
        quadrato.setRect(x, y, size, size);
        repaint();
	}
	
	public void aggiungiRettangolo(Rectangle rettangolo, Color colore) {
		
      	Graphics2D g2d = (Graphics2D)ImageBuffer.getGraphics();
		g2d.setColor( colore );
		g2d.setStroke(new BasicStroke(spessoreForme));
		if (pieno == true) {
			g2d.fill(rettangolo);
		}
		g2d.draw( rettangolo );
		repaint();
		
	}
	
	public void tracciamentoRettangolo(Point startPoint, int meX, int meY) {
		int x = Math.min(startPoint.x, meX);
		int y = Math.min(startPoint.y, meY);
		int width = Math.abs(startPoint.x - meX);
		int height = Math.abs(startPoint.y - meY);
		rettangolo.setRect(x, y, width, height);
		repaint();
	}
	
	public void aggiungiCerchio(Ellipse2D cerchio, Color colore) {
		
		Graphics2D g2d = (Graphics2D)ImageBuffer.getGraphics();
		g2d.setColor(colore);
		g2d.setStroke(new BasicStroke(spessoreForme));
		if (pieno == true) {
			g2d.fill(cerchio);
		}
		g2d.draw(cerchio);
		repaint();
		
	}
	
	public void tracciamentoCerchio(Point startPoint, int meX, int meY) {
		
		Graphics2D g2d = (Graphics2D)ImageBuffer.getGraphics();
		int radius = (int) startPoint.distance(startPoint);
        int diameter = radius * 2;
        int x = (int) startPoint.getX() - radius;
        int y = (int) startPoint.getY() - radius;
        cerchio = new Ellipse2D.Double(x, y, diameter, diameter);
//        g2d.drawOval(x, y, diameter, diameter);
		
	}
	
	public void tracciaLinea(Point startPoint, Point endPoint, Color colore) {
			gBuffer.setColor(colore);
			gBuffer.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			repaint();
	}

	public void resetLinea() {
		posX = -1;
		posY = -1;
	}

	public BufferedImage getBuffer() {
		return this.ImageBuffer;
	}

	public void setBuffer(BufferedImage buffer) {
		this.ImageBuffer = buffer;
	}

	public Graphics2D getgBuffer() {
		return this.gBuffer;
	}

	public void setgBuffer(Graphics2D gBuffer) {
		this.gBuffer = gBuffer;
	}

	public Color getColorePrimario() {
		return this.colorePrimario;
	}

	public void setColorePrimario(Color colorePrimario) {
		this.colorePrimario = colorePrimario;
	}

	public Color getColoreSecondario() {
		return this.coloreSecondario;
	}

	public void setColoreSecondario(Color coloreSecondario) {
		this.coloreSecondario = coloreSecondario;
	}
	
	public void setColoreForme(Color coloreForme) {
		this.coloreForme = coloreForme;
	}
	
	public boolean getPieno() {
		return this.pieno;
	}
	
	public void setRiempimento(boolean pieno) {
		this.pieno = pieno;
	}

	public int getSpessore() {
		return this.Spessore;
	}

	public void setSpessore(int spessore) {
		this.Spessore = spessore;
	}
	
	public void setSpessoreForme(int spessoreForme) {
		this.spessoreForme = spessoreForme;
	}

	public int getTempSpessore() {
		return this.tempSpessore;
	}

	public void setTempSpessore(int tempSpessore) {
		this.tempSpessore = tempSpessore;
	}

	public int getLARGHEZZA() {
		return this.LARGHEZZA;
	}

	public void setLARGHEZZA(int LARGHEZZA) {
		this.LARGHEZZA = LARGHEZZA;
	}

	public int getALTEZZA() {
		return this.ALTEZZA;
	}

	public void setALTEZZA(int ALTEZZA) {
		this.ALTEZZA = ALTEZZA;
	}
}
