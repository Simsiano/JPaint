package jpaint_main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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
import jpaint_graphics.jpaint_grafica;
import jpaint_interface.jpaint_icone;
import jpaint_interface.jpaint_interfaccia;
import jpaint_interface.jpaint_interfaccia_avanzata;
import javax.swing.JLabel;

public class jpaint_main extends JFrame implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private JPanel pan = new JPanel();
	private JFileChooser fileChooser = new JFileChooser();
	private jpaint_grafica grafica = new jpaint_grafica();
	private jpaint_interfaccia interfaccia = new jpaint_interfaccia();
	private jpaint_interfaccia_avanzata interfaccia_adv = new jpaint_interfaccia_avanzata();
	private jpaint_icone icona = new jpaint_icone();
	private int NumberCustomButton = 0;
	public BufferedImage buffer;
	public Graphics2D gBuffer;
	private File filePicked;
	private int fileChoosed;
	private String selectedItem;
	private Point startPoint;
	private Point endPoint;

	public jpaint_main() {

		grafica.setColorePrimario(Color.BLACK);
		grafica.setColoreSecondario(Color.BLACK);

		interfaccia.newFile.addActionListener(e -> newFile());
		interfaccia.openFile.addActionListener(e -> openFile());
		interfaccia.saveFile.addActionListener(e -> saveFile());
		interfaccia.exit.addActionListener(e -> exit());

//		interfaccia.strumenti.addActionListener(this);

		interfaccia_adv.btnMatita.addActionListener(e -> interfaceMatita(e));
		interfaccia_adv.btnGomma.addActionListener(e -> interfaceGomma(e));

		interfaccia.boxDimensioniMatita.addActionListener(e -> boxDimensionLinea());
		interfaccia.boxTipoPennelli.addActionListener(e -> actionTipoPennelli());
		interfaccia.btnOptionPannello.addActionListener(e -> setterPennello());
		interfaccia.boxTipoForme.addActionListener(e -> actionTipoForme());
		interfaccia.boxStileForme.addActionListener(e -> actionStileForme());

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

		interfaccia.btnDetach.addActionListener(e -> this.panelDetach());
		
		grafica.addMouseListener(this);
		grafica.addMouseMotionListener(this);
		JLabel test = new JLabel();
		test.setIcon(new ImageIcon(icona.matita));

		interfaccia.panDisegno.setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(grafica);
		interfaccia.panDisegno.add(scroll, "Center");

		setJMenuBar(interfaccia.mainMenuBar());

		pan.setLayout(new BorderLayout());
		pan.add(interfaccia.tabbedPane(), "North");
		pan.add(interfaccia.panDisegno, "Center");
		getContentPane().add(pan);
		setTitle("Immagine - JPaint");
		setIconImage(icona.logo);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
		System.out.println("Funziona");
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
		filePicked = new File(fileChooser.getSelectedFile().getAbsolutePath());
		try {
			buffer = ImageIO.read(filePicked);
			if (buffer.getWidth() > 800 || buffer.getHeight() > 600) {
				String error = "Dimensioni immagine troppo grandi";
				JOptionPane.showMessageDialog(null, error, "Errore", 0);
			} else {
				setTitle("JPaint" + filePicked.getName());
				grafica.setLARGHEZZA(this.buffer.getHeight());
				grafica.setALTEZZA(this.buffer.getHeight());
				grafica.setBuffer(this.buffer);
				gBuffer = this.buffer.createGraphics();
				grafica.setgBuffer(this.gBuffer);
				grafica.repaint();
				grafica.revalidate();
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private void saveFile() {
		fileChoosed = fileChooser.showDialog(null, null);
		if (fileChoosed == JFileChooser.APPROVE_OPTION) {
			File outputFile = fileChooser.getSelectedFile();
            String filePath = outputFile.getAbsolutePath();
            
            try {
                ImageIO.write(grafica.getBuffer(), "PNG", outputFile);
                setTitle(filePath + " - JPaint");
                System.out.println("Immagine salvata: " + filePath);
            } catch (IOException e) {
                System.out.println("Error saving image: " + e.getMessage());
            }
		} else {
			System.out.println("Nothing");
		}
	}

	private void exit() {
		dispose();
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
		grafica.setColorePrimario(null);
		JButton clickedButton = (JButton) me.getSource();
		Color colorDraw = clickedButton.getBackground();
		interfaccia.btnColorePrimario.setBackground(colorDraw);
//		interfaccia.btnColorChange.setBackground(colorDraw);
		grafica.setColorePrimario(colorDraw);
//		mainp.setForeground(colorDraw);
		repaint();
	}

	private void SecondaryColorDefiner(MouseEvent me) {
		grafica.setColoreSecondario(null);
		JButton clickedButton = (JButton) me.getSource();
		Color colorDraw = clickedButton.getBackground();
		interfaccia.btnColoreSecondario.setBackground(colorDraw);
//		interfaccia.btnColorChange.setBackground(colorDraw);
		grafica.setColoreSecondario(colorDraw);
		repaint();
	}

	private void btnColorChange() {
		Color coloreAttuale = this.grafica.getColorePrimario();
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
*/		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), grafica.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), grafica.getColoreSecondario());
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), grafica.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), grafica.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbLinea.isSelected()) {
			startPoint = me.getPoint();
			endPoint = startPoint;
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
		if (interfaccia.rdbLinea.isSelected()) {
			endPoint = me.getPoint();
			grafica.tracciaLinea(startPoint, endPoint, grafica.getColorePrimario());
			startPoint = endPoint = null;
			repaint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (grafica.quadrato.width != 0 || grafica.quadrato.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiQuadrato(grafica.quadrato, grafica.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiQuadrato(grafica.quadrato, grafica.getColoreSecondario());
				}
			}
			grafica.quadrato = null;
			grafica.repaint();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (grafica.rettangolo.width != 0 || grafica.rettangolo.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiRettangolo(grafica.rettangolo, grafica.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiRettangolo(grafica.rettangolo, grafica.getColoreSecondario());
				}
			}
			grafica.rettangolo = null;
			grafica.repaint();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (grafica.cerchio.width != 0 || grafica.cerchio.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiCerchio(grafica.cerchio, grafica.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiCerchio(grafica.cerchio, grafica.getColoreSecondario());
				}
			}
			grafica.cerchio = null;
			grafica.repaint();
		}
		grafica.resetLinea();
	}

	public void mouseDragged(MouseEvent me) {
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Pennello")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), grafica.getColorePrimario());
				} if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), grafica.getColoreSecondario());
				}
			}
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), grafica.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), grafica.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbMatita.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.tracciaMatita(me.getX(), me.getY(), grafica.getColorePrimario());
			} if (SwingUtilities.isRightMouseButton(me)) {
				grafica.tracciaMatita(me.getX(), me.getY(), grafica.getColoreSecondario());
			}
		}
		if (interfaccia.rdbGomma.isSelected()) {
			grafica.tracciaGomma(me.getX(), me.getY());
		}
		if (interfaccia.rdbLinea.isSelected()) {
			endPoint = me.getPoint();
			grafica.tracciaLinea(startPoint, endPoint, grafica.getColorePrimario());
			startPoint = endPoint = null;
			repaint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoQuadrato(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoQuadrato(startPoint, me.getX(), me.getY());
			}
			grafica.repaint();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoRettangolo(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoRettangolo(startPoint, me.getX(), me.getY());
			}
			grafica.repaint();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoCerchio(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoCerchio(startPoint, me.getX(), me.getY());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
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
