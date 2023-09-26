package main_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import graphical_logic.Colori;
import graphical_logic.Grafica;
import swing_interface.Custom_Shape;
import swing_interface.Custom_Shape_Draw;
import swing_interface.Icone;
import swing_interface.Interfaccia;
import swing_interface.Interfaccia_ADV;

import javax.swing.JLabel;

public class JPaint extends JFrame implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pan;
	private JFileChooser fileChooser;
	private Grafica grafica;
	private Colori colori;
	private Interfaccia interfaccia;
	private Interfaccia_ADV dialoghi;
	private Custom_Shape dialogo_personalizzazione;
	private Custom_Shape_Draw shapeDraw;
	private Icone icona;
	private int NumberCustomButton = 0;
	private int originalImageWidth;
	private int originalImageHeight;
	private BufferedImage oldBuffer;
	private BufferedImage buffer;
	private Graphics2D gBuffer;
	private Graphics2D layer1;
	private Graphics2D layer2;
	private boolean fileSaved;
	private File outputFile;
	private File filePicked;
	private int fileChoosed;
	private String selectedItem;
	private Point startPoint;

	public JPaint() {

		this.pan = new JPanel();
		this.fileChooser = new JFileChooser();
		this.grafica = new Grafica();
		this.colori = new Colori();
		this.interfaccia = new Interfaccia();
		this.dialoghi = new Interfaccia_ADV();
		this.dialogo_personalizzazione = new Custom_Shape();
		this.shapeDraw = new Custom_Shape_Draw();
		this.icona = new Icone();

		interfaccia.newFile.addActionListener(e -> newFile());
		interfaccia.openFile.addActionListener(e -> openFile());
		interfaccia.newSaveFile.addActionListener(e -> newSaveFile());
		interfaccia.saveFile.addActionListener(e -> saveFile());
		interfaccia.exit.addActionListener(e -> exit());

		//		interfaccia.strumenti.addActionListener(this);

		dialoghi.btnMatita.addActionListener(e -> interfaceMatita(e));
		dialoghi.btnGomma.addActionListener(e -> interfaceGomma(e));

		interfaccia.boxDimensioniMatita.addActionListener(e -> boxDimensionLinea());
		interfaccia.boxTipoPennelli.addActionListener(e -> actionTipoPennelli());
		interfaccia.btnOptionPannello.addActionListener(e -> setterPennello());
		interfaccia.boxTipoForme.addActionListener(e -> actionTipoForme());
		interfaccia.boxStileForme.addActionListener(e -> actionStileForme());
		interfaccia.btnStilePersonalizzato.addActionListener(e -> dialogoAperturaPersonalizzazione(this));
		dialogo_personalizzazione.btnColore1.addActionListener(e -> sceltaColore(e));
		dialogo_personalizzazione.btnColore2.addActionListener(e -> sceltaColore(e));
		dialogo_personalizzazione.btnConfermaGradienza.addActionListener(e -> gradientColorDefiner());

		interfaccia.btnColoreRosso.addMouseListener(new ActionColor());
		interfaccia.btnColoreVerde.addMouseListener(new ActionColor());
		interfaccia.btnColoreBlu.addMouseListener(new ActionColor());
		interfaccia.btnColoreGiallo.addMouseListener(new ActionColor());
		interfaccia.btnColoreNero.addMouseListener(new ActionColor());
		interfaccia.btnColoreBianco.addMouseListener(new ActionColor());

		interfaccia.btnColorChange.addActionListener(e -> btnColorChange());

		interfaccia.btnColoreCustom1.addMouseListener(new ActionColor());
		interfaccia.btnColoreCustom2.addMouseListener(new ActionColor());
		interfaccia.btnColoreCustom3.addMouseListener(new ActionColor());
		interfaccia.btnColoreCustom4.addMouseListener(new ActionColor());
		interfaccia.btnColoreCustom5.addMouseListener(new ActionColor());

		interfaccia.btnDeZoom.addActionListener(e -> DeZoomImageTest2());
		interfaccia.btnZoom.addActionListener(e -> ZoomImageTest2());

		interfaccia.btnDetach.addActionListener(e -> this.panelDetach());

		interfaccia.btnLayer1.addActionListener(e -> layerSelector(e));
		interfaccia.btnLayer2.addActionListener(e -> layerSelector(e));

		grafica.addMouseListener(this);
		grafica.addMouseMotionListener(this);
		JLabel test = new JLabel();
		test.setIcon(new ImageIcon(icona.matita));

		interfaccia.panDisegno.setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(grafica);
		//		interfaccia.panDisegno.add(scroll, "Center");

		setJMenuBar(interfaccia.mainMenuBar());

		pan.setLayout(new BorderLayout());
		pan.add(interfaccia.tabbedPane(), BorderLayout.NORTH);
		pan.add(scroll, BorderLayout.CENTER);
		pan.add(interfaccia.bottomPanelInfo(), BorderLayout.SOUTH);

		getContentPane().add(pan);
		setTitle("Immagine - JPaint");
		setIconImage(icona.logo);
		//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//		int width = ((int) screenSize.getWidth());
		//		int height = ((int) screenSize.getHeight());
		setPreferredSize(new Dimension(1270, 800));
		setMaximumSize(new Dimension(1280, 800));
		setMinimumSize(new Dimension(1280, 800));
		setResizable(true);
		setExtendedState(MAXIMIZED_BOTH); 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choose = JOptionPane.showConfirmDialog(null,
						"Vuoi veramente uscire?", "Conferma chiusura", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});
		setVisible(true);
	}

	private void layerSelector(ActionEvent e) {
		String selected = e.getSource().toString();
		if (selected.equals("Layer 1")) {
			grafica.setgBuffer(layer1);
		} else if (selected.equals("Layer 2")){
			grafica.setgBuffer(layer2);
		}
	}

	private void newFile() {
		try {
			grafica.pulisci();
			setTitle("Immagine - JPaint");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void openFile() {
		fileChoosed = fileChooser.showDialog(null, null);
		if (!(fileChoosed == JFileChooser.APPROVE_OPTION)) {}
		filePicked = new File(fileChooser.getSelectedFile().getAbsolutePath());
		try {
			buffer = ImageIO.read(filePicked);
			fileSaved = false;
			/*			if (buffer.getWidth() > 800 || buffer.getHeight() > 600) {
				String error = "Dimensioni immagine troppo grandi";
				JOptionPane.showMessageDialog(null, error, "Errore", 0);
			} else {
			 */				setTitle(filePicked.getName() + " - JPaint");
			 this.originalImageWidth = buffer.getWidth();
			 this.originalImageHeight = buffer.getHeight();
			 grafica.setLARGHEZZA(this.buffer.getWidth());
			 grafica.setALTEZZA(this.buffer.getHeight());
			 grafica.setBuffer(this.buffer);
			 gBuffer = this.buffer.createGraphics();
			 grafica.setgBuffer(this.gBuffer);
			 grafica.repaint();
			 grafica.revalidate();
			 //			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private void newSaveFile() {
		fileChoosed = fileChooser.showDialog(null, null);
		if (fileChoosed == JFileChooser.APPROVE_OPTION) {
			outputFile = fileChooser.getSelectedFile();
			String filePath = outputFile.getAbsolutePath();

			try {
				ImageIO.write(grafica.getBuffer(), "PNG", outputFile);
				setTitle(outputFile.getName() + " - JPaint");
				System.out.println("Immagine salvata: " + filePath);
				fileSaved = true;
			} catch (IOException e) {
				System.out.println("Error saving image: " + e.getMessage());
			}
		} else {
			System.out.println("Nothing");
		}
	}

	private void saveFile() {
		if (fileSaved==false) {
			newSaveFile();
		}
		try {
			ImageIO.write(grafica.getBuffer(), "PNG", outputFile);
		} catch (IOException e) {
			System.out.println("Error saving image: " + e.getMessage());
		}
	}

	private void exit() {
		dispose();
	}

	private void DeZoomImage() {
		double width = grafica.getBuffer().getWidth();
		double height = grafica.getBuffer().getHeight();
		if (width<=375) {
			return;
		}
		BufferedImage dezoom = new BufferedImage((int)(width*0.5), (int)(height*0.5), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(0.5, 0.5);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		scaleOp.filter(grafica.getBuffer(), dezoom);
		grafica.setBuffer(dezoom);
		Graphics2D gBuffer = dezoom.createGraphics();
		grafica.setgBuffer(gBuffer);
		System.out.println("Dezoom");
		System.out.println(grafica.getBuffer().getWidth());
		System.out.println(grafica.getBuffer().getHeight());
		interfaccia.dimensionUpdate(grafica.getBuffer().getWidth(), grafica.getBuffer().getHeight());
		grafica.repaint();
		dezoom = null;
		gBuffer = null;
		at = null;
		scaleOp = null;
		System.gc();
	}

	private void ZoomImage() {
		double width = grafica.getBuffer().getWidth();
		double height = grafica.getBuffer().getHeight();
		BufferedImage zoom = new BufferedImage((int)(width*2), (int)(height*2), BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(2, 2);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		scaleOp.filter(grafica.getBuffer(), zoom);
		grafica.setBuffer(zoom);
		Graphics2D gBuffer = zoom.createGraphics();
		grafica.setgBuffer(gBuffer);
		System.out.println("Zoom");
		System.out.println(grafica.getBuffer().getWidth());
		System.out.println(grafica.getBuffer().getHeight());
		interfaccia.dimensionUpdate(grafica.getBuffer().getWidth(), grafica.getBuffer().getHeight());
		grafica.repaint();
		zoom = null;
		gBuffer = null;
		at = null;
		scaleOp = null;
		System.gc();
	}

	private void DeZoomImageTest() {
		int width = grafica.getBuffer().getWidth();
		int height = grafica.getBuffer().getHeight();
		BufferedImage vecchio = grafica.getBuffer();
		BufferedImage dezoom = new BufferedImage((int)(width*0.5), (int)(height*0.5), BufferedImage.TYPE_INT_ARGB);
		grafica.setBuffer(dezoom);
		Graphics2D gBuffer = dezoom.createGraphics();
		gBuffer.scale(0.5, 0.5);
		grafica.setgBuffer(gBuffer);
		grafica.getgBuffer().drawImage(vecchio, 0, 0, this);
		System.out.println("Dezoom");
		System.out.println(grafica.getBuffer().getWidth());
		System.out.println(grafica.getBuffer().getHeight());
		interfaccia.dimensionUpdate(grafica.getBuffer().getWidth(), grafica.getBuffer().getHeight());
		grafica.repaint();
		dezoom = null;
		gBuffer = null;
		System.gc();
		grafica.repaint();
	}

	private void ZoomImageTest() {
		int width = grafica.getBuffer().getWidth();
		int height = grafica.getBuffer().getHeight();
		BufferedImage vecchio = grafica.getBuffer();
		BufferedImage zoom = new BufferedImage((int)(width*2), (int)(height*2), BufferedImage.TYPE_INT_ARGB);
		grafica.setBuffer(zoom);
		Graphics2D gBuffer = zoom.createGraphics();
		gBuffer.scale(2, 2);
		grafica.setgBuffer(gBuffer);
		grafica.getgBuffer().drawImage(vecchio, 0, 0, this);
		System.out.println("Zoom");
		System.out.println(grafica.getBuffer().getWidth());
		System.out.println(grafica.getBuffer().getHeight());
		interfaccia.dimensionUpdate(grafica.getBuffer().getWidth(), grafica.getBuffer().getHeight());
		grafica.repaint();
		zoom = null;
		gBuffer = null;
		System.gc();
	}

	private void ZoomImageTest2() {
		double width = grafica.getBuffer().getWidth()*2;
		double height = grafica.getBuffer().getHeight()*2;
		if (this.originalImageWidth > width && this.originalImageHeight > height) {
			oldBuffer = grafica.getBuffer();
		}
		BufferedImage vecchio = grafica.getBuffer();
		BufferedImage dezoom = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_ARGB);
		grafica.setBuffer(dezoom);

		Graphics2D gBuffer = dezoom.createGraphics();
		gBuffer.scale(2, 2);
		grafica.setgBuffer(gBuffer);
		grafica.getgBuffer().drawImage(vecchio, 0, 0, this);
		System.out.println("Dezoom");
		System.out.println(grafica.getBuffer().getWidth());
		System.out.println(grafica.getBuffer().getHeight());
		interfaccia.dimensionUpdate(grafica.getBuffer().getWidth(), grafica.getBuffer().getHeight());
		grafica.repaint();
		dezoom = null;
		gBuffer = null;
		System.gc();
		grafica.repaint();
	}

	private void DeZoomImageTest2() {
		double width = grafica.getBuffer().getWidth()*0.5;
		double height = grafica.getBuffer().getHeight()*0.5;
		if (this.originalImageWidth > width && this.originalImageHeight > height) {
			oldBuffer = grafica.getBuffer();
		}
		BufferedImage vecchio = grafica.getBuffer();
		BufferedImage dezoom = new BufferedImage((int)(width), (int)(height), BufferedImage.TYPE_INT_ARGB);
		grafica.setBuffer(dezoom);
		Graphics2D gBuffer = dezoom.createGraphics();
		gBuffer.scale(0.5, 0.5);
		grafica.setgBuffer(gBuffer);
		grafica.getgBuffer().drawImage(vecchio, 0, 0, this);
		System.out.println("Dezoom");
		System.out.println(grafica.getBuffer().getWidth());
		System.out.println(grafica.getBuffer().getHeight());
		interfaccia.dimensionUpdate(grafica.getBuffer().getWidth(), grafica.getBuffer().getHeight());
		grafica.repaint();
		dezoom = null;
		gBuffer = null;
		System.gc();
		grafica.repaint();

	}

	private void actionTipoPennelli() {
		selectedItem = new String((String) interfaccia.boxTipoPennelli.getSelectedItem());
		if (selectedItem.equals("Pennello")) {
			interfaccia.btnOptionPannello.setEnabled(true);
		} else {
			interfaccia.btnOptionPannello.setEnabled(false);
		}
		System.out.println(selectedItem);
	}

	private void setterPennello() {
		if (interfaccia.rdbPennello.isSelected() && selectedItem.toString().equals("Pennello")) {

		} else {}
	}

	private void interfaceMatita(ActionEvent e) {
		interfaccia.rdbMatita.setSelected(true);
	}

	private void interfaceGomma(ActionEvent e) {
		interfaccia.rdbGomma.setSelected(true);
	}

	private void panelDetach() {
		interfaccia.panelDetach().setVisible(false);
		interfaccia.tabbedPane().setEnabled(false);
		interfaccia.panelDetach().setSize(1000, 100);
		repaint();
		revalidate();
	}

	private void boxDimensionLinea() {
		StringBuilder selectedItem = new StringBuilder(new String((String) interfaccia.boxDimensioniMatita.getSelectedItem()));
		//		String selectedItem = new String((String) interfaccia.boxDimensioniMatita.getSelectedItem());
		System.out.println(selectedItem);
		if (selectedItem.toString().equals("Custom")) {
			JLabel label = new JLabel("Inserisci uno spessore");
			JTextField textfield = new JTextField(3);
			Object[] item = { label, textfield };
			int chooser = JOptionPane.showOptionDialog(this, item, "Seleziona spessore", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (chooser == JOptionPane.OK_OPTION) {
				try {
					grafica.setSpessore(Integer.parseInt(textfield.getText()));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex, "Errore", JOptionPane.ERROR_MESSAGE);
				}
			} else {
			}
		} else {
			String a = selectedItem.substring(1, 3);
			System.out.println(a);
			//			grafica.setSpessore((int)(selectedItem.substring(1, 3)));

			grafica.setSpessore(Integer.parseInt(selectedItem.delete(1, 3).toString()));
		}
	}

	private void actionTipoForme() {
		String selectedItem = new String((String) interfaccia.boxTipoForme.getSelectedItem());
		if (selectedItem.equals("----------")) {
			interfaccia.rdbNull.setSelected(true);
		}
		if (selectedItem.equals("Linea")) {
			interfaccia.rdbLinea.setSelected(true);
		}
		if (selectedItem.equals("Quadrato")) {
			interfaccia.rdbQuadrato.setSelected(true);
		}
		if (selectedItem.equals("Rettangolo")) {
			interfaccia.rdbRettangolo.setSelected(true);
		}
		if (selectedItem.equals("Cerchio")) {
			interfaccia.rdbCerchio.setSelected(true);
		}
	}

	private void actionStileForme() {
		String selectedItem = new String((String) interfaccia.boxStileForme.getSelectedItem());
		if (selectedItem.equals("Vuoto")) {
			grafica.setRiempimento(false);
		}
		if (selectedItem.equals("Pieno")) {
			grafica.setRiempimento(true);
		}
	}

	private void PrimaryColorDefiner(MouseEvent me) {
		colori.setColorePrimario(null);
		interfaccia.btnColorePrimario.setBackground(colorDefiner(me));
		colori.setColorePrimario(colorDefiner(me));
		repaint();
	}

	private void SecondaryColorDefiner(MouseEvent me) {
		colori.setColoreSecondario(null);		
		interfaccia.btnColoreSecondario.setBackground(colorDefiner(me));
		colori.setColoreSecondario(colorDefiner(me));
		repaint();
	}

	private Color colorDefiner(MouseEvent me) {
		JButton clickedButton = (JButton) me.getSource();
		Color colorDraw = clickedButton.getBackground();
		return colorDraw;
	}

	private void dialogoAperturaPersonalizzazione(JFrame frame) {
		SwingUtilities.invokeLater(() -> {
			dialogo_personalizzazione.Dialogo_Forme(frame);
		});
	}

	private void sceltaColore(ActionEvent e) {
		if (e.getSource() == dialogo_personalizzazione.btnColore1) {
			Color colore1 = sceltaColoreDialogo(colori.getColoreGradienza1());
			colori.setColoreGradienza1(colore1);
			System.out.println("Colore1 primitivo: " + colore1);
			dialogo_personalizzazione.btnColore1.setBackground(colore1);
			shapeDraw.repaint();
		}
		if (e.getSource() == dialogo_personalizzazione.btnColore2) {
			Color colore2 = sceltaColoreDialogo(colori.getColoreGradienza2());
			colori.setColoreGradienza2(colore2);
			dialogo_personalizzazione.btnColore2.setBackground(colore2);
			shapeDraw.repaint();
		}
	}

	private Color sceltaColoreDialogo(Color colorePrecedente) {
		Color coloreScelto = JColorChooser.showDialog(dialogo_personalizzazione.getDialog(), "Seleziona un colore", colorePrecedente);
		shapeDraw.repaint();
		return coloreScelto;
	}

	private void gradientColorDefiner() {
		grafica.setColoreForme(new GradientPaint(0,0,colori.getColoreGradienza1(),0,0,colori.getColoreGradienza2()));
		dialogo_personalizzazione.close();
		grafica.setGradientMode(true);
		repaint();
	}

	private void btnColorChange() {
		Color coloreAttuale = colori.getColorePrimario();
		Color coloreScelto = JColorChooser.showDialog(this, "Seleziona un colore", coloreAttuale);
		if (coloreScelto != null) {

			NumberCustomButton++;

			//			interfaccia.btnColorePrimario.setBackground(coloreScelto);
			interfaccia.btnColorChange.setBackground(coloreScelto);

			switch (NumberCustomButton) {
			case 1: {
				interfaccia.btnColoreCustom1.setEnabled(true);
				interfaccia.btnColoreCustom1.setBackground(coloreScelto);
				break;
			}
			case 2: {
				interfaccia.btnColoreCustom2.setEnabled(true);
				interfaccia.btnColoreCustom2.setBackground(coloreScelto);
				break;
			}
			case 3: {
				interfaccia.btnColoreCustom3.setEnabled(true);
				interfaccia.btnColoreCustom3.setBackground(coloreScelto);
				break;
			}
			case 4: {
				interfaccia.btnColoreCustom4.setEnabled(true);
				interfaccia.btnColoreCustom4.setBackground(coloreScelto);
				break;
			}
			case 5: {
				interfaccia.btnColoreCustom5.setEnabled(true);
				interfaccia.btnColoreCustom5.setBackground(coloreScelto);
				NumberCustomButton = 0;
			}
			}
			//			grafica.setColorePrimario(coloreScelto);
			repaint();
		}
	}
	
	public void actionColorDropper() {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		setCursor(cursor);	
	}

	@Override
	public void mouseExited(MouseEvent me) {
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		setCursor(cursor);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		/*		if (interfaccia.rdbCerchio.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.aggiungiCerchio(me.getX(), me.getY(), grafica.getColorePrimario());
			} else if (SwingUtilities.isRightMouseButton(me)) {
				grafica.aggiungiCerchio(me.getX(), me.getY(), grafica.getColoreSecondario());
			}
		}
*/		
		if (interfaccia.rdbContaGoccie.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.getColorDropperColor(me.getX(), me.getY());
				System.out.println("Funziona");
			}
		}
		if (interfaccia.rdbPennello.isSelected()) {
			 if (selectedItem.equals("Aerografo")) {
				 if (SwingUtilities.isLeftMouseButton(me)) {
					 grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColorePrimario());
				 } else if (SwingUtilities.isRightMouseButton(me)) {
					 grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColoreSecondario());
				 }
			 }
		 }
	}

	@Override
	public void mousePressed(MouseEvent me) {
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbLinea.isSelected()) {
			startPoint = me.getPoint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			startPoint = me.getPoint();
			grafica.quadrato = new Rectangle();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			startPoint = me.getPoint();
			grafica.rettangolo = new Rectangle();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			startPoint = me.getPoint();
			grafica.cerchio = new Ellipse2D.Double();
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (grafica.getGradientMode() == true) {
			grafica.setGradientMode(false);
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.aggiungiLinea(colori.getColorePrimario());
			grafica.linea = null;
			repaint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (grafica.quadrato.width != 0 || grafica.quadrato.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiQuadrato(colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiQuadrato(colori.getColoreSecondario());
				}
			}
			grafica.quadrato = null;
			grafica.repaint();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (grafica.rettangolo.width != 0 || grafica.rettangolo.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiRettangolo(colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiRettangolo(colori.getColoreSecondario());
				}
			}
			grafica.rettangolo = null;
			grafica.repaint();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (grafica.cerchio.width != 0 || grafica.cerchio.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiCerchio(grafica.cerchio, colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiCerchio(grafica.cerchio, colori.getColoreSecondario());
				}
			}
			grafica.cerchio = null;
			grafica.repaint();
		}
		grafica.resetLinea();
	}

	public void mouseDragged(MouseEvent me) {
		interfaccia.mouseLocation(me.getX(), me.getY());
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Pennello")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), colori.getColorePrimario());
				} if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), colori.getColoreSecondario());
				}
			}
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbMatita.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.tracciaMatita(me.getX(), me.getY(), colori.getColorePrimario());
			} if (SwingUtilities.isRightMouseButton(me)) {
				grafica.tracciaMatita(me.getX(), me.getY(), colori.getColoreSecondario());
			}
		}
		if (interfaccia.rdbGomma.isSelected()) {
			grafica.tracciaGomma(me.getX(), me.getY());
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.tracciamentoLinea(startPoint, me.getX(), me.getY());
			grafica.repaint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoQuadrato(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoQuadrato(startPoint, me.getX(), me.getY());
			}
			grafica.repaint();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoRettangolo(colori.getColorePrimario(), startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoRettangolo(colori.getColoreSecondario(), startPoint, me.getX(), me.getY());
			}
			grafica.repaint();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoCerchio(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoCerchio(startPoint, me.getX(), me.getY());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		interfaccia.mouseLocation(me.getX(), me.getY());
	}

	private class ActionColor extends MouseAdapter {
		public void mouseClicked(MouseEvent me) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				PrimaryColorDefiner(me);
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				SecondaryColorDefiner(me);
			}
		}
	}
}
