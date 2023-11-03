package graphical_logic;

import static swing_interface.Interfaccia.btnColorePrimario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLayeredPane;

public class Grafica extends JLayeredPane {
	
	private static final long serialVersionUID = 1L;
	
	private  Colori colori = new Colori();
	
	private static int LARGHEZZA = 800;
	private static int ALTEZZA = 600;
	
	/**
	 * BufferedImage principale usato per qualsiasi funzione. Grafica2D associata: <i>#MainGraphicsBuffer</i>.
	 * <p>
	 * @see
	 * 		{@link #OperationsBuffer}
	 * 		{@link #TempImageBuffer}
	 */
	public static BufferedImage MainImageBuffer = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
	
	/**
	 * BufferedImage usato solo per la creazione del rettangolo nella modalità "seleziona". Non usare per altri scopi.
	 * Grafica2D associata: <i>SubGraphicsBuffer</i>.
	 * <p>
	 * @see
	 * 		{@link #MainImageBuffer}
	 * 		{@link #TempImageBuffer}
	 */
	public static BufferedImage OperationsBuffer = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
	
	/**
	 * BufferedImage "temporaneo" usato per lo spostamente di immagini dal BufferedImage <i>{@link #OperationsBuffer}</i>
	 * al BufferedImage principale <i>{@link #MainImageBuffer}</i>. Grafica2D associata (ma non inizializzata): <i>TempGraphicsBuffer</i>.
	 * <p>
	 * @see
	 * 		{@link #MainImageBuffer}
	 * 		{@link #OperationsBuffer}
	 */
	public static BufferedImage TempImageBuffer;
	
	public static Graphics2D MainGraphicsBuffer = MainImageBuffer.createGraphics();
	public static Graphics2D SubGraphicsBuffer = OperationsBuffer.createGraphics();
	public static Graphics2D TempGraphicsBuffer;
	
	private ArrayList<BufferedImage> arrayList;
	private BasicStroke stroke;
	public Rectangle seleziona;
	public Line2D.Double linea;
	public Rectangle quadrato;
	public Rectangle rettangolo;
	public Ellipse2D.Double cerchio;
	private static  GradientPaint coloreForme;
	private boolean pieno;
	private boolean gradientMode;
	private int Spessore = 3;
	private int spessoreForme = 3;
	private int tempSpessore = 3;
	private int posX = -1;
	private int posY = -1;
	private int selezionaX;
	private int selezionaY;
	private int selezionaWidth;
	private int selezionaHeight;

	public Grafica() {
		pulisci();
		this.setPreferredSize(new Dimension(MainImageBuffer.getWidth(), MainImageBuffer.getHeight()));
	}
/*	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(MainImageBuffer.getWidth(), MainImageBuffer.getHeight());
	}
*/	
	@Override
	public Dimension getSize() {
		return new Dimension(this.getPreferredSize());
	}
/*	
	@Override
	public Dimension getPreferredSize() {
		this.setPreferredSize(new Dimension(MainImageBuffer.getWidth(), MainImageBuffer.getHeight()));
		
	}
*/	
	@Override
	protected void paintComponent(Graphics internalGraphics) {
		
		super.paintComponent(internalGraphics);
		MainGraphicsBuffer = MainImageBuffer.createGraphics();
		MainGraphicsBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		SubGraphicsBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		internalGraphics.drawImage(MainImageBuffer, 0, 0, null);
		internalGraphics.drawImage(OperationsBuffer, 0, 0, null);
		Graphics2D g2d = (Graphics2D)internalGraphics;
		
		stroke = new BasicStroke(Spessore, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		
		if (seleziona != null) {
			g2d.setStroke(new BasicStroke(3));
			g2d.setPaint(coloreForme);
			g2d.draw( seleziona );
			repaint();
		}
		
		if (linea != null) {
			g2d.setStroke(new BasicStroke(spessoreForme));
			if (gradientMode == true) {
				g2d.setPaint(coloreForme);
			}
			g2d.setPaint(coloreForme);
			g2d.draw(linea);
		}
		
		if (quadrato != null) {
			g2d.setStroke(new BasicStroke(spessoreForme));
			g2d.setPaint(coloreForme);
			if (pieno == true) {
				g2d.fill(quadrato);
			}
			g2d.draw( quadrato );
			repaint();
		}
		
		if (rettangolo != null) {
			g2d.setStroke(new BasicStroke(spessoreForme));
			g2d.setPaint(coloreForme);
			if (pieno == true) {
				g2d.fill(rettangolo);
			}
			g2d.draw( rettangolo );
			repaint();
		}
		
		if (cerchio != null) {
			g2d.setStroke(new BasicStroke(spessoreForme));
			g2d.setPaint(coloreForme);
			if (pieno == true) {
				g2d.fill(cerchio);
			} else {}
			g2d.draw( cerchio );
			repaint();
		}

	}
	
	/**
	 * Annulla il l'ImageBuffer e il gBuffer.
	 */
	public void nullBuffer() {
		MainImageBuffer = null;
		MainGraphicsBuffer = null;
	}
	
	/**
	 * "Pulisce" l'ImageBuffer principale riempendolo di colore bianco.
	 */
	public void pulisci() {
		MainGraphicsBuffer.setColor(Color.WHITE);
		MainGraphicsBuffer.fillRect(0, 0, LARGHEZZA, ALTEZZA);
		repaint();
	}
	
	/**
	 * Richiama il metodo <i>{@link #nullBuffer()}</i> e ri-crea l'ImageBuffer e il gBuffer
	 * mantenendo la stessa Larghezza e Altezza. Richiama inoltre il metodo <i>{@link #pulisci()}</i>.
	 */
	public void resetBuffer() {
		nullBuffer();
		MainImageBuffer = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
		MainGraphicsBuffer = MainImageBuffer.createGraphics();
		pulisci();
	}
	
	/**
	 * Aggiorna esclusivamente le variabili Larghezza e Altezza.
	 * <p>
	 * @param larghezza (int)
	 * @param altezza (int)
	 * @see {@link #setDimensioniRicreaBuffer()}
	 */
	public void aggiornaDimensioni(int larghezza, int altezza) {
		Grafica.LARGHEZZA = larghezza;
		Grafica.ALTEZZA = altezza;
	}
	
	/**
	 * Richiama il metodo <i>{@link #nullBuffer()}</i> e ri-crea l'ImageBuffer e il gBuffer
	 * in base alle dimensioni date. Richiama inoltre il metodo <i>{@link #pulisci()}</i>.
	 * <p>
	 * @param larghezza (int)
	 * @param altezza (int)
	 * @see 
	 * 	{@link #aggiornaDimensioni(int, int)}
	 * 	{@link #resetBuffer()}
	 */
	public void setDimensioniRicreaBuffer(int larghezza, int altezza) {
		nullBuffer();
		Grafica.LARGHEZZA = larghezza;
		Grafica.ALTEZZA = altezza;
		MainImageBuffer = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
		MainGraphicsBuffer = MainImageBuffer.createGraphics();
		pulisci();
		repaint();
	}
	
	/**
	 * Richiama il metodo <i>{@link #nullBuffer()}</i> e ri-crea l'ImageBuffer e il gBuffer
	 * in base all'immagine data.
	 * <p>
	 * @param immagine (BufferedImage)
	 */
	public void rimpiazzaConImmagine(BufferedImage immagine) {
		nullBuffer();
		Grafica.MainImageBuffer = immagine;
		MainGraphicsBuffer = MainImageBuffer.createGraphics();
		repaint();
	}
	
	/**
	 * Decide se pulire il buffer usato dalle operazioni secondarie come il
	 * tracciamento del rettangolo della modalità seleziona.
	 * <p>
	 * @param haveToBeCleaned (boolean)
	 */
	public void pulisciBufferOperazioni(boolean haveToBeCleaned) {
		if (haveToBeCleaned == false) return;
		OperationsBuffer = null; SubGraphicsBuffer = null;
		OperationsBuffer = new BufferedImage(LARGHEZZA, ALTEZZA, BufferedImage.TYPE_INT_ARGB);
		SubGraphicsBuffer = OperationsBuffer.createGraphics();
		repaint();
	}
	
	public void tracciaRettangoloSeleziona(Point startPoint, Point endPoint) {
		this.selezionaX = Math.min(startPoint.x, endPoint.x);
		this.selezionaY = Math.min(startPoint.y, endPoint.y);
		this.selezionaWidth = Math.abs(startPoint.x - endPoint.x);
		this.selezionaHeight = Math.abs(startPoint.y - endPoint.y);
		Color startColor = getContrastColor(new Color(MainImageBuffer.getRGB(startPoint.x, startPoint.y)));
		Color endColor = getContrastColor(new Color(MainImageBuffer.getRGB(endPoint.x, endPoint.y)));
		setColoreForme(new GradientPaint(startPoint.x, startPoint.y, startColor, endPoint.x, endPoint.y, endColor));
		seleziona.setRect(selezionaX, selezionaY, selezionaWidth, selezionaHeight);
		repaint();
	}
	
	public void aggiungiRettangoloSeleziona() {
		SubGraphicsBuffer = (Graphics2D) OperationsBuffer.getGraphics();
		SubGraphicsBuffer.setPaint( coloreForme );
		SubGraphicsBuffer.setStroke(new BasicStroke(3));
		SubGraphicsBuffer.draw( seleziona );
		repaint();
	}
	
	private Color getContrastColor(Color color) {
		  double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
		  return y >= 128 ? Color.black : Color.white;
	}

	public void tracciaMatita(int x, int y, Color color) {
		if (posX != -1) {
//        	float alpha = 0.25f;
//        	Color color = new Color(colore.getRed(), colore.getGreen(), colore.getBlue(), alpha);
			MainGraphicsBuffer.setPaint(color);
			MainGraphicsBuffer.setStroke(stroke);

			MainGraphicsBuffer.drawLine(x, y, posX, posY);
			repaint();
		}
		posX = x;
		posY = y;
	}

	public void tracciaGomma(int x, int y) {
		if (posX != -1) {
			MainGraphicsBuffer.setPaint(Color.WHITE);
			MainGraphicsBuffer.setStroke(stroke);
			MainGraphicsBuffer.drawLine(x, y, posX, posY);
			repaint();
		}
		posX = x;
		posY = y;
	}
	
	public void tracciaPennello(int x, int y, Color color) {
		if (posX != -1) {
 //       	float alpha = 0.25f;
 //       	Color color = new Color(colore.getRed(), colore.getGreen(), colore.getBlue(), alpha);
			MainGraphicsBuffer.setPaint(color);
			MainGraphicsBuffer.setStroke(stroke);

			MainGraphicsBuffer.drawLine(x, y, posX, posY);
			repaint();
		}
		posX = x;
		posY = y;
	}
	
	public void getColorDropperColor(int x, int y) {
		Color color = new Color(MainImageBuffer.getRGB(x, y));
		colori.setColorePrimario(color);
		btnColorePrimario.setBackground(color);
		btnColorePrimario.repaint();
	}

	public void tracciaAerografo(int x, int y, Color colore) {
		if (posX != -1) {
			MainGraphicsBuffer.setColor(null);
			MainGraphicsBuffer.setPaint(colore);;
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
				MainGraphicsBuffer.fillOval(x + quadrante1X, y + quadrante1Y, 3, 3);
				MainGraphicsBuffer.fillOval(x - quadrante2X, y + quadrante2Y, 3, 3);
				MainGraphicsBuffer.fillOval(x - quadrante3X, y - quadrante3Y, 3, 3);
				MainGraphicsBuffer.fillOval(x + quadrante4X, y - quadrante4Y, 3, 3);
				repaint();
				try {
					wait();
				} catch (InterruptedException e) {
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
	public void tracciamentoLinea(Color colore, Point startPoint, int meX, int meY) {
		Point endPoint = new Point(meX, meY);
		linea = new Line2D.Double();
		setColoreForme(new GradientPaint(0,0,colore,0,0,colore));
		linea.setLine(startPoint, endPoint);
		repaint();
	}
	
	public void aggiungiLinea(Color colore) {
		Graphics2D g2d = (Graphics2D)MainImageBuffer.getGraphics();
		g2d.setPaint( colore );
		g2d.setStroke(new BasicStroke(spessoreForme));
		g2d.draw( linea );
		repaint();
	}
	
	public void tracciamentoQuadrato(Color colore, Point startPoint, int meX, int meY) {
		int width = Math.abs(startPoint.x - meX);
        int height = Math.abs(startPoint.y - meY);
        int size = Math.min(width, height);
        int x = meX > startPoint.x ? startPoint.x : startPoint.x - size;
        int y = meY > startPoint.y ? startPoint.y : startPoint.y - size;
        if (gradientMode == true) {
			setColoreForme(new GradientPaint(startPoint.x, startPoint.y, coloreForme.getColor1(), meX, meY, coloreForme.getColor2())); } 
        else {
			setColoreForme(new GradientPaint(0,0,colore,0,0,colore)); }	
        quadrato.setRect(x, y, size, size);
        repaint();
	}
	
	public void aggiungiQuadrato(Color colore) {
		
		Graphics2D g2d = (Graphics2D)MainImageBuffer.getGraphics();
		g2d.setPaint(colore);
		g2d.setStroke(new BasicStroke(spessoreForme));
		if (pieno == true) {
			g2d.fill(quadrato);
		}
		g2d.draw( quadrato );
		repaint();
		
	}
	
	public void tracciamentoRettangolo(Color colore,Point startPoint, int meX, int meY) {
		int x = Math.min(startPoint.x, meX);
		int y = Math.min(startPoint.y, meY);
		int width = Math.abs(startPoint.x - meX);
		int height = Math.abs(startPoint.y - meY);
		if (gradientMode == true) {
			setColoreForme(new GradientPaint(startPoint.x, startPoint.y, coloreForme.getColor1(), meX, meY, coloreForme.getColor2())); } 
		else {
			setColoreForme(new GradientPaint(0,0,colore,0,0,colore)); }	
		rettangolo.setRect(x, y, width, height);
		repaint();
	}
	
	public void aggiungiRettangolo(Color colore) {
		
      	Graphics2D g2d = (Graphics2D)MainImageBuffer.getGraphics();
		g2d.setPaint( coloreForme );
		g2d.setStroke(new BasicStroke(spessoreForme));
		if (pieno == true) {
			g2d.fill(rettangolo);
		}
		g2d.draw( rettangolo );
		repaint();
		
	}
	
	public void tracciamentoCerchio(Point startPoint, int meX, int meY) {
		
		int radius = (int) startPoint.distance(startPoint);
//        int diameter = radius * 2;
        int x = (int) startPoint.getX() - radius;
        int y = (int) startPoint.getY() - radius;
        cerchio = new Ellipse2D.Double(meX, meY, x, y);
//        g2d.drawOval(x, y, diameter, diameter);
		
	}
	
	public void aggiungiCerchio(Ellipse2D cerchio, Color colore) {
		
		Graphics2D g2d = (Graphics2D)MainImageBuffer.getGraphics();
		g2d.setPaint(colore);
		g2d.setStroke(new BasicStroke(spessoreForme));
		if (pieno == true) {
			g2d.fill(cerchio);
		}
		g2d.draw(cerchio);
		repaint();
		
	}
	
	
/*	
	public void tracciaLinea(Point startPoint, Point endPoint, Color colore) {
			gBuffer.setColor(colore);
			gBuffer.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			repaint();
	}
*/
	public void resetLinea() {
		posX = -1;
		posY = -1;
	}

	public BufferedImage getMainImageBuffer() {
		return Grafica.MainImageBuffer;
	}

	public void setMainImageBuffer(BufferedImage buffer) {
		Grafica.MainImageBuffer = buffer;
	}
	
	public BufferedImage getTempImageBuffer() {
		return Grafica.TempImageBuffer;
	}
	
	public void setTempImageBuffer(BufferedImage buffer) {
		Grafica.TempImageBuffer = buffer;
	}

	public Graphics2D getMainGraphicsBuffer() {
		return Grafica.MainGraphicsBuffer;
	}

	public void setMainGraphicsBuffer(Graphics2D gBuffer) {
		Grafica.MainGraphicsBuffer = gBuffer;
	}
	
	public Graphics2D getSubGraphicsBuffer() {
		return Grafica.SubGraphicsBuffer;
	}
	
	public void setSubGraphicsBuffer(Graphics2D gBuffer) {
		Grafica.SubGraphicsBuffer = gBuffer;
	}
	
	public Graphics2D getTempGraphicsBuffer() {
		return Grafica.TempGraphicsBuffer;
	}
	
	public void setTempGraphicsBuffer(Graphics2D buffer) {
		Grafica.TempGraphicsBuffer = buffer;
	}
	
	public void setColoreForme(GradientPaint coloreForme) {
		Grafica.coloreForme = coloreForme;
	}
	
	public boolean getPieno() {
		return this.pieno;
	}
	
	public void setRiempimento(boolean pieno) {
		this.pieno = pieno;
	}
	
	public boolean getGradientMode() {
		return this.gradientMode;
	}
	
	public void setGradientMode(boolean gradientMode) {
		this.gradientMode = gradientMode;
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
	
	public int getSelezionaX() {
		return this.selezionaX;
	}

	public int getSelezionaY() {
		return this.selezionaY;
	}
	
	public int getSelezionaWidth() {
		return this.selezionaWidth;
	}
	
	public int getSelezionaHeight() {
		return this.selezionaHeight;
	}
	
	public int getLARGHEZZA() {
		return Grafica.LARGHEZZA;
	}

	public int getALTEZZA() {
		return Grafica.ALTEZZA;
	}

}
