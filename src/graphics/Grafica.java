package graphics;

import static graphics.Layers.bufferedImagesLayers;
import static graphics.Layers.graphics2dsLayers;
import static gui.Interfaccia.btnColorePrimario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Grafica extends AzioniStacks {

	protected static JPanel tela;

	public static int index;

	public static int larghezza;
	public static int altezza;

	public static Line2D.Double linea = null;
	public static Rectangle seleziona = null;
	public static Rectangle quadrato = null;
	public static Rectangle rettangolo = null;
	public static Ellipse2D.Float cerchio = null;

	private boolean riempito;
	private boolean gradientMode;

	//	private int spessoreForme = 3;
	//	private int tempSpessore = 3;

	private static int spessoreInt = 10;

	private BasicStroke stroke;

	private static final int STROKERUBBER = 20;

	private static int posX;
	private static int posY;

	//	private int selezionaX;
	//	private int selezionaY;
	//	private int selezionaWidth;
	//	private int selezionaHeight;

	public Grafica(JPanel tela, int larghezza, int altezza) {

		Grafica.tela = tela;

		index = 0;

		posX = -1;
		posY = -1;

		stroke = new BasicStroke(spessoreInt,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

		Grafica.larghezza = larghezza;
		Grafica.altezza = altezza;

		addLayer(larghezza, altezza);
		setStroke(1);
	}

	/**
	 * Disegna tutti i BufferedImage.
	 * <p>
	 * @param g (Graphics)
	 */
	protected void drawAllLayers(Graphics2D g2d) {
		for (int i = 0; i < bufferedImagesLayers.size(); i++) {
			g2d.drawImage(bufferedImagesLayers.get(i), 0, 0, null);
		}
	}

	protected void drawLayer(Graphics g) {
		g.drawImage(bufferedImagesLayers.get(index), 0, 0, null);
	}

	@Deprecated
	protected void resize() {}

	/**
	 * Aggiungi un layer.
	 * <p>
	 * @param width (int)
	 * @param height (int)
	 */
	public void addLayer(int width, int height) {
		bufferedImagesLayers.add(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
		graphics2dsLayers.add(bufferedImagesLayers.get(bufferedImagesLayers.size()-1).createGraphics());
		graphics2dsLayers.get(bufferedImagesLayers.size()-1).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		index = bufferedImagesLayers.size() - 1;
		setColor(index, Color.BLACK);
	}


	@Deprecated
	protected void clean(int index) {
		graphics2dsLayers.get(index).setColor(Color.WHITE);
		graphics2dsLayers.get(index).fillRect(0, 0, bufferedImagesLayers.get(0).getWidth(), 
				bufferedImagesLayers.get(0).getHeight());
	}

	/**
	 * Imposta lo pressore per uno specifico layer.
	 * <p>
	 * @param index (int)
	 * @param stroke (int)
	 */
	public void setStroke(int index, int stroke) {
		graphics2dsLayers.get(index).setStroke(new BasicStroke(stroke,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		Grafica.spessoreInt = stroke;
	}
	
	/**
	 * Imposta lo spessore per il layer corrente.
	 * <p>
	 * @param stroke (int)
	 */
	public void setStroke(int stroke) {
		graphics2dsLayers.get(index).setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}

	/**
	 * Imposta lo pressore per uno specifico layer.
	 * <p>
	 * @param index (int)
	 * @param stroke (BasicStroke)
	 */
	public void setStroke(int index, BasicStroke stroke) {
		graphics2dsLayers.get(index).setStroke(stroke);
	}

	/**
	 * Imposta uno spessore per tutti i layer esistenti.
	 * <p>
	 * @param stroke (int)
	 */
	protected void setStrokeAll(int stroke) {
		for (int i = 0; i < graphics2dsLayers.size(); i++) {
			graphics2dsLayers.get(i).setStroke(new BasicStroke(stroke));
		}
	}

	/**
	 * Imposta la variabile "spessore".
	 * <p>
	 * @param spessore (int)
	 */
	public void setSpessore(int spessore) {
		Grafica.spessoreInt = spessore;
	}

	/**
	 * Imposta il colore ad un specifico layer.
	 * <p>
	 * @param index (int)
	 * @param color (Color)
	 */
	public void setColor(int index, Color color) {
		graphics2dsLayers.get(index).setColor(color);
	}

	/**
	 * Imposta il GradientPaint per le forme ad un specifico layer.
	 * <p>
	 * @param index (int)
	 */
	public void setPaint(int index) {
		graphics2dsLayers.get(index).setPaint(Colori.getColoreForme());
	}

	/**
	 * Imposta un colore per il layer corrente.
	 * <p>
	 * @param colore (Color)
	 */
	public void setPaint(Color colore) {
		graphics2dsLayers.get(index).setPaint(colore);
	}

	/**
	 * Setta la variabile di riempimento.
	 * <p>
	 * @param riempito (boolean)
	 */
	public void setRiempimento(boolean riempito) {
		this.riempito = riempito;
	}

	/**
	 * Setta la variabile per le gradienze.
	 * <p>
	 * @param gradientMode (boolean)
	 */
	public void setGradientMode(boolean gradientMode) {
		this.gradientMode = gradientMode;
	}

	/**
	 * Restituisce un vero/false se è attiva la variabile per le gradienze.
	 * <p>
	 * @return gradientMode (boolean)
	 */
	public boolean getGradientMode() {
		return this.gradientMode;
	}

	/**
	 * Metodo usato per disegnare linee generiche.
	 * Include diversi metodi al suo interno.
	 * <p>
	 * @param x (int)
	 * @param y (int)
	 * @param color (Color)
	 * @see
	 * 		{@link #setStroke(int, BasicStroke)}
	 * 		{@link #setColor(int, Color)}
	 * 		{@link #traccia(int, int)}
	 */
	private void draw(int x, int y, Color color) {
		setStroke(index, stroke);
		setColor(index, color);
		traccia(x, y);
	}

	/**
	 * Disegna una forma sul layer attuale.
	 * Include diversi metodi al suo interno.
	 * <p>
	 * @param shape (Shape) | Forma generica da disegnare.
	 * @see
	 * 		{@link #setStroke(int, int)}
	 * 		{@link #setPaint(index)}
	 */
	protected void draw(Shape shape) {
		setStroke(index, 3);
		setPaint(index);
		if (riempito == true) graphics2dsLayers.get(index).fill(shape);
		graphics2dsLayers.get(index).draw(shape);
		tela.repaint();
	}

	/**
	 * Disegna una forma sul layer attuale, utilizzato dal "paintComponent" della classe principale.
	 * Necessita di una Graphics2D.
	 * Include diversi metodi al suo interno
	 * <p>
	 * @param shape (Shape) | Forma generica da disegnare.
	 * @param g2d (Graphics2D)
	 */
	protected void draw(Shape shape, Graphics2D g2d) {
		setStroke(index, 3);
		setPaint(index);
		if (riempito == true) g2d.fill(shape);
		g2d.draw(shape);
		tela.repaint();
	}

	/**
	 * Disegna una forma sul layer attuale e imposta un colore specifico.
	 * Include diversi metodi al suo interno.
	 * <p>
	 * @param shape (Shape) | Forma generica da disegnare.
	 * @param colore (Color)
	 * @see
	 * 		{@link #setStroke(int, int)}
	 * 		{@link #setPaint(Color)}
	 */
	protected void draw(Shape shape, Color colore) {
		setStroke(index, 3);
		setPaint(colore);
		if (riempito == true) graphics2dsLayers.get(index).fill(shape);
		graphics2dsLayers.get(index).draw(shape);
	}

	/**
	 * Traccia in modo generico una linea.
	 * <p>
	 * @param x (int)
	 * @param y (int)
	 */
	private void traccia(int x, int y) {
		if (posX != -1) {
			graphics2dsLayers.get(index).drawLine(x, y, posX, posY);
			tela.repaint();
		}
		posX = x;
		posY = y;
	}

	/**
	 * Imposta il colore cliccato come colore primario.
	 * <p>
	 * @param x (int)
	 * @param y (int)
	 */
	public void getColorDropperColor(int x, int y) {
		Color color = new Color(bufferedImagesLayers.get(index).getRGB(x, y));
		Colori.setColorePrimario(color);
		btnColorePrimario.setBackground(color);
		btnColorePrimario.repaint();
	}

	/**
	 * Traccia una linea, ha lo stesso funzionamento del metodo "drawLine" di Graphics/Graphics2D
	 * <p>
	 * @param x (int)
	 * @param y (int)
	 * @param color (Color)
	 */
	public void tracciaMatita(int x, int y, Color color) {
		draw(x, y, color);
	}

	public void tracciaGomma(int x, int y) {
		setStroke(index, STROKERUBBER);
		setColor(index, Color.WHITE);
		traccia(x, y);
	}

	public void tracciaPennello(int x, int y, Color color) {
		draw(x, y, color);
	}

	public void tracciaAerografo(int x, int y, Color colore) {
		if (posX != -1) {
			setColor(index, colore);
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
				graphics2dsLayers.get(index).fillOval(x + quadrante1X, y + quadrante1Y, 3, 3);
				graphics2dsLayers.get(index).fillOval(x - quadrante2X, y + quadrante2Y, 3, 3);
				graphics2dsLayers.get(index).fillOval(x - quadrante3X, y - quadrante3Y, 3, 3);
				graphics2dsLayers.get(index).fillOval(x + quadrante4X, y - quadrante4Y, 3, 3);
				tela.repaint();
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		posX = x;
		posY = y;
	}

	public void tracciaLinea(Point startPoint, Point endPoint, Color colore) {
		linea = new Line2D.Double();
		Colori.setColoreForme(colore);
		linea.setLine(startPoint, endPoint);
		tela.repaint();
	}

	public void aggiungiLinea(Color colore) {
		draw(linea, colore);
	}

	public void tracciamentoQuadrato(Point startPoint, Point endPoint, Color colore) {
		int width = Math.abs(startPoint.x - endPoint.x);
		int height = Math.abs(startPoint.y - endPoint.y);
		int size = Math.min(width, height);
		int x = endPoint.x > startPoint.x ? startPoint.x : startPoint.x - size;
		int y = endPoint.y > startPoint.y ? startPoint.y : startPoint.y - size;
		if (gradientMode == true) {
			Colori.setColoreForme(startPoint, endPoint);
		} else {
			Colori.setColoreForme(colore);
		}	
		quadrato.setRect(x, y, size, size);
		tela.repaint();
	}

	public void aggiungiQuadrato(Color colore) {
		draw(quadrato, colore);
	}

	public void tracciamentoRettangolo(Point startPoint, Point endPoint, Color colore) {
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		int width = Math.abs(startPoint.x - endPoint.x);
		int height = Math.abs(startPoint.y - endPoint.y);
		if (gradientMode == true) {
			Colori.setColoreForme(startPoint, endPoint); 
		} else {
			Colori.setColoreForme(colore);
		}	
		rettangolo.setRect(x, y, width, height);
		tela.repaint();
	}

	public void aggiungiRettangolo(Color colore) {
		draw(rettangolo, colore);
	}

	public void tracciamentoCerchio(Point startPoint, Point endPoint) {
		int radius = (int) startPoint.distance(startPoint);
		int x = (int) startPoint.getX() - radius;
		int y = (int) startPoint.getY() - radius;
		cerchio = new Ellipse2D.Float(endPoint.x, endPoint.y, x, y);
		tela.repaint();
	}

	public void aggiungiCerchio(Color colore) {
		draw(cerchio, colore);
	}

	public void resetPosizione() {
		posX = -1;
		posY = -1;
	}

	/**
	 * Mostra un dialogo di opzione su cui è possibile scegliere il layer
	 * <p>
	 * @param parentComponent
	 */
	public static void selectLayer(JFrame frame) {
		String[] options = new String[bufferedImagesLayers.size()];
		int temp = index;
		for (int i = 0; i < bufferedImagesLayers.size(); i++) {
			options[i] = "Layer " + (i + 1);
		}

		int choice = JOptionPane.showOptionDialog(frame, "Select a layer", "Layer Selection",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if (choice != -1) {
			index = choice;
		} else {
			index = temp;
		}
		System.out.println(index);
	}

	/**
	 * Ritorna una Graphics2D in base all'index dato.
	 * <p>
	 * @param index (int)
	 * @return Graphics2D
	 */
	protected static Graphics2D getGraphics2D(int index) {
		return graphics2dsLayers.get(index);
	}

	/**
	 * Ritorna un BufferedImage in base all'index dato.
	 * <p>
	 * @param index (int)
	 * @return BufferedImage
	 */
	public static BufferedImage getBufferedImage(int index) {
		return bufferedImagesLayers.get(index);
	}

	protected static void setBufferedImage(int index, BufferedImage bI) {
		bufferedImagesLayers.set(index, bI);
	}

	protected void createGraphics2D(int index) {
		graphics2dsLayers.set(index, bufferedImagesLayers.get(bufferedImagesLayers.size()-1).createGraphics());
	}

	protected static void createGraphics2D(int index, Color color, BasicStroke stroke) {
		graphics2dsLayers.set(index, bufferedImagesLayers.get(bufferedImagesLayers.size()-1).createGraphics());
		graphics2dsLayers.get(index).setColor(color);
		graphics2dsLayers.get(index).setStroke(stroke);;
	}

	protected ArrayList<BufferedImage> getImageLayers() {
		return bufferedImagesLayers;
	}

	protected ArrayList<Graphics2D> getGraphicsLayers() {
		return graphics2dsLayers;
	}

	/**
	 * Ritorna la grandezza dell'ArrayList.
	 * <p>
	 * @return size (int)
	 */
	public int getSizeBuffer() {
		return bufferedImagesLayers.size();
	}

	protected int getPosX() {
		return Grafica.posX;
	}

	protected int getPosY() {
		return Grafica.posY;
	}

	protected void setPosX(int posX) {
		Grafica.posX = posX;
	}

	protected void setPosY(int posY) {
		Grafica.posY = posY;
	}


}
