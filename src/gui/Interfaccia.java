package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import graphics.Grafica;
import helpers.CButton;

public class Interfaccia {

	public JPanel panDisegno = new JPanel();
	public JMenuItem newFile = new JMenuItem("Nuovo");
	public JMenuItem newFileAdv = new JMenuItem("Nuovo...");
	public JMenuItem openFile = new JMenuItem("Apri");
	public JMenuItem newSaveFile = new JMenuItem("Salva con nome...");
	public JMenuItem saveFile = new JMenuItem("Salva");
	public JMenuItem exit = new JMenuItem("Esci");

	public JTabbedPane tabbedPane = new JTabbedPane();

	public JMenuItem strumenti = new JMenuItem("Strumenti");

///////////////////////////////////////////////////////////////////////////////
	public ButtonGroup rdbGroup = new ButtonGroup();
	// Visibili
	public JRadioButton rdbMatita = new JRadioButton("Matita");
	public JRadioButton rdbPennello = new JRadioButton("Pennello");
	public JRadioButton rdbGomma = new JRadioButton("Gomma");
	public JRadioButton rdbContaGoccie = new JRadioButton("Contagoccie");
	// Non Visibili
	public JRadioButton rdbLinea = new JRadioButton("Linea");
	public JRadioButton rdbQuadrato = new JRadioButton("Quadrato");
	public JRadioButton rdbRettangolo = new JRadioButton("Rettangolo");
	public JRadioButton rdbCerchio = new JRadioButton("Cerchio");
	// Null
	public JRadioButton rdbNull = new JRadioButton();
///////////////////////////////////////////////////////////////////////////////

	private String[] dimensioni = { "1px", "2px", "3px", "4px", "5px", "Custom" };
	public JComboBox<String> boxDimensioniMatita = new JComboBox<String>(dimensioni);

	private String[] pennelli = { "Aerografo", "Pennello" };
	public JComboBox<String> boxTipoPennelli = new JComboBox<String>(pennelli);
	public JButton btnOptionPannello = new JButton("...");

	private String[] forme = { "----------", "Linea", "Quadrato", "Rettangolo", "Cerchio" };
	public JComboBox<String> boxTipoForme = new JComboBox<String>(forme);
	
	private String[] opzioniForme = { "Vuoto", "Pieno" };
	public JComboBox<String> boxStileForme = new JComboBox<String>(opzioniForme);
	public JButton btnGradienza = new JButton("Personalizza");

	public Dimension btnColorSize = new Dimension(25, 25);
	public Dimension btnColorSizeBig = new Dimension(25, 25);

	public CButton btnColorChange = new CButton(true,true);
	public static CButton btnColorePrimario = new CButton(true,false);
	public static CButton btnColoreSecondario = new CButton(true,false);

	public CButton btnColoreRosso = new CButton(Color.RED);
	public CButton btnColoreVerde = new CButton(Color.GREEN);
	public CButton btnColoreBlu = new CButton(Color.BLUE);
	public CButton btnColoreGiallo = new CButton(Color.YELLOW);
	public CButton btnColoreNero = new CButton(Color.BLACK);
	public CButton btnColoreBianco = new CButton(Color.WHITE);

	public CButton btnColoreCustom1 = new CButton();
	public CButton btnColoreCustom2 = new CButton();
	public CButton btnColoreCustom3 = new CButton();
	public CButton btnColoreCustom4 = new CButton();
	public CButton btnColoreCustom5 = new CButton();
	
	public CButton btnColorePalette1 = new CButton();
	public CButton btnColorePalette2 = new CButton();
	public CButton btnColorePalette3 = new CButton();
	public CButton btnColorePalette4 = new CButton();
	public CButton btnColorePalette5 = new CButton();

	public JButton btnDetach = new JButton("\u2193");
	
	private JPanel bottomPaneInfo = new JPanel(); 
	private JLabel mousePosition = new JLabel();
	
	private JLabel lblZoom = new JLabel("Zoom: ");
	public JButton btnDeZoom = new JButton("-");
	public JButton btnZoom = new JButton("+");
	
	public JLabel lblDimension = new JLabel();

	public static JButton btnIndietro = new JButton("Indietro");
	public static JButton btnAvanti = new JButton("Avanti");
	public JButton btnSelezionaLayer = new JButton("Seleziona layer");
	public JButton btnAggiungiLayer = new JButton("Aggiungi layer");
	
	public ButtonGroup bG = new ButtonGroup();
//	public JRadioButton btnLayer1 = new JRadioButton("Layer 1");
	public JRadioButton btnLayer2 = new JRadioButton("Layer 2");

	public Icone icone = new Icone();

	public JMenuBar mainMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu visualizza = new JMenu("Visualizza");
		JMenu interfaccia = new JMenu("Interfaccia");
		menuBar.add(file);
		file.add(newFileAdv);
		file.add(newFile);
		file.addSeparator();
		file.add(openFile);
		file.addSeparator();
		file.add(newSaveFile);
		file.add(saveFile);
		file.addSeparator();
		file.add(exit);
		menuBar.add(visualizza);
		visualizza.add(interfaccia);
		interfaccia.add(strumenti);
		return menuBar;
	}

	public JTabbedPane tabbedPane() {
		tabbedPane.addTab("Disegna", null, panelDisegnaMain(), "Disegna");
		tabbedPane.addTab("Colori", null, panelColoriMain(), "Colori");
		tabbedPane.addTab("Layers", null, panelLayers(), "Layers");
		tabbedPane.addTab("Visualizza", null, panelVisualizza(), "Visualizza");
		return tabbedPane;
	}

	private ButtonGroup btnGroup() {
		// Visibili
		rdbGroup.add(rdbMatita);
		rdbGroup.add(rdbPennello);
		rdbGroup.add(rdbGomma);
		rdbGroup.add(rdbContaGoccie);
		// Non Visibili
		rdbGroup.add(rdbLinea);
		rdbGroup.add(rdbQuadrato);
		rdbGroup.add(rdbRettangolo);
		rdbGroup.add(rdbCerchio);
		// Null
		rdbGroup.add(rdbNull);

		return rdbGroup;
	}

	private JPanel panelStrumentiDisegno() {
		JPanel paneStrumentiDisegno = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"  Strumenti  ", 2, 5);
		paneStrumentiDisegno.setBorder(titledBorder);

		btnGroup();
		
		rdbMatita.setSelected(true);

		paneStrumentiDisegno.add(rdbMatita);
		paneStrumentiDisegno.add(rdbPennello);
		paneStrumentiDisegno.add(rdbGomma);
		paneStrumentiDisegno.add(rdbContaGoccie);

		return paneStrumentiDisegno;
	}

	private JPanel panelDimensioniLinea() {
		JPanel paneDimensioniDisegno = new JPanel();
		JLabel label = new JLabel("Dimensioni matita: ");

		boxDimensioniMatita.setSelectedIndex(2);
		boxDimensioniMatita.setSelectedItem("3px");

		paneDimensioniDisegno.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"  Dimensioni  ", 2, 5);
		paneDimensioniDisegno.setBorder(titledBorder);
		paneDimensioniDisegno.add(label);
		paneDimensioniDisegno.add(boxDimensioniMatita);

		return paneDimensioniDisegno;
	}

	private JPanel panelTipoPannelli() {
		JPanel paneTipoPennello = new JPanel();
		JLabel label = new JLabel("Tipo pennello: ");

		boxTipoPennelli.setSelectedIndex(0);
		boxTipoPennelli.setSelectedItem("Pennello");

		paneTipoPennello.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "  Pennelli  ",
				2, 5);
		paneTipoPennello.setBorder(titledBorder);
		paneTipoPennello.add(label);
		paneTipoPennello.add(boxTipoPennelli);
		paneTipoPennello.add(btnOptionPannello);

		return paneTipoPennello;
	}

	private JPanel panelTipoForme() {
		JPanel paneTipoForme = new JPanel();
		JLabel label = new JLabel("Forme: ");

		boxTipoPennelli.setSelectedIndex(1);
		boxTipoPennelli.setSelectedItem("Rettangolo");

		paneTipoForme.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "  Forme  ",
				2, 5);         
		paneTipoForme.setBorder(titledBorder);
		paneTipoForme.add(label);
		paneTipoForme.add(boxTipoForme);
		paneTipoForme.add(boxStileForme);
		paneTipoForme.add(btnGradienza);
		return paneTipoForme;
	}

	private JPanel panelDisegnaMain() {
		JPanel paneDisegnaMain = new JPanel();
		paneDisegnaMain.setLayout(new FlowLayout(1));
		paneDisegnaMain.add(panelStrumentiDisegno());
		paneDisegnaMain.add(panelDimensioniLinea());
		paneDisegnaMain.add(panelTipoPannelli());
		paneDisegnaMain.add(panelTipoForme());
		return paneDisegnaMain;
	}

	private JPanel panelColori() {
		JPanel panPaletteColori = new JPanel();
		panPaletteColori.setLayout(new GridLayout(0, 6));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "  Colori  ",
				2, 5);
		panPaletteColori.setBorder(titledBorder);
		panPaletteColori.add(btnColoreRosso);
		panPaletteColori.add(btnColoreVerde);
		panPaletteColori.add(btnColoreBlu);
		panPaletteColori.add(btnColoreGiallo);
		panPaletteColori.add(btnColoreNero);
		panPaletteColori.add(btnColoreBianco);

		return panPaletteColori;
	}

	private JPanel panelCustomColori() {
		JPanel panCustomColori = new JPanel();
		panCustomColori.setLayout(new GridLayout(0, 5));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"  Colori Personalizzati  ", 2, 5);
		panCustomColori.setBorder(titledBorder);
		panCustomColori.add(btnColoreCustom1);
		panCustomColori.add(btnColoreCustom2);
		panCustomColori.add(btnColoreCustom3);
		panCustomColori.add(btnColoreCustom4);
		panCustomColori.add(btnColoreCustom5);

		return panCustomColori;
	}
	
	private JPanel panelCustomPalette() {
		JPanel panCustomPalette = new JPanel();
		panCustomPalette.setLayout(new GridLayout(0, 5));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"  Paletta Colori  ", 2, 5);
		panCustomPalette.setBorder(titledBorder);
		panCustomPalette.add(btnColorePalette1);
		panCustomPalette.add(btnColorePalette2);
		panCustomPalette.add(btnColorePalette3);
		panCustomPalette.add(btnColorePalette4);
		panCustomPalette.add(btnColorePalette5);
		
		return panCustomPalette;
	}

	private JPanel panelShowPrimaryColor() {
		JPanel panePrimaryColor = new JPanel();
		JLabel label = new JLabel("Colore 1");
		panePrimaryColor.setLayout(new BorderLayout());
		btnColorePrimario.setPreferredSize(btnColorSizeBig);
		btnColorePrimario.setBackground(new Color(0, 0, 0));
		btnColorePrimario.setFocusPainted(false);
		panePrimaryColor.add(btnColorePrimario, "Center");
		panePrimaryColor.add(label, "South");
		return panePrimaryColor;
	}

	private JPanel panelShowSecondaryColor() {
		JPanel paneSecondaryColor = new JPanel();
		JLabel label = new JLabel("Colore 2");
		paneSecondaryColor.setLayout(new BorderLayout());
		btnColoreSecondario.setPreferredSize(btnColorSizeBig);
		btnColoreSecondario.setBackground(new Color(0, 0, 0));
		btnColoreSecondario.setFocusPainted(false);
		paneSecondaryColor.add(btnColoreSecondario, "North");
		paneSecondaryColor.add(label, "Center");
		return paneSecondaryColor;
	}

	private JPanel panelShowColor() {
		JPanel paneShowColor = new JPanel();
		paneShowColor.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());
		paneShowColor.setBorder(titledBorder);
		paneShowColor.add(panelShowPrimaryColor());
		paneShowColor.add(panelShowSecondaryColor());
		return paneShowColor;
	}

	private JPanel panelBtnChangeColor() {
		JPanel paneChangeColor = new JPanel();
		JLabel label = new JLabel("Custom");
		paneChangeColor.setLayout(new BorderLayout());
		btnColorChange.setPreferredSize(btnColorSizeBig);
		btnColorChange.setBackground(new Color(0, 0, 0));
		paneChangeColor.add(btnColorChange, "North");
		paneChangeColor.add(label, "Center");
		return paneChangeColor;
	}

	private JPanel panelChangeColor() {
		JPanel paneChangeColor = new JPanel();
		paneChangeColor.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());
		paneChangeColor.setBorder(titledBorder);
		paneChangeColor.add(panelBtnChangeColor());
		return paneChangeColor;
	}

	private JPanel panelColoriMain() {
		JPanel panComandi = new JPanel();
		panComandi.setLayout(new FlowLayout(1));
		panComandi.add(panelShowColor());
		panComandi.add(panelColori());
		panComandi.add(panelCustomColori());
		panComandi.add(panelCustomPalette());
		panComandi.add(panelChangeColor());
		return panComandi;
	}

	private JPanel panelVisualizza() {
		JPanel paneVis = new JPanel();
		btnIndietro.setEnabled(false);
		btnAvanti.setEnabled(false);
		paneVis.add(btnIndietro);
		paneVis.add(btnAvanti);
		paneVis.add(btnSelezionaLayer);
		paneVis.add(btnAggiungiLayer);
		return paneVis;
	}
	
	private JPanel panelLayers() {
		JPanel pane = new JPanel();
		JButton btnLayer = new JButton();
		btnLayer.setIcon(new ImageIcon(Grafica.getBufferedImage(0)));
		btnLayer.setPreferredSize(new Dimension(60,20));
		pane.add(btnLayer);
		System.out.println(pane.getSize());
		return pane;
	}
	
	public void mouseLocation(int meX, int meY) {
		mousePosition.setText("X: " + meX + "   Y: " + meY);
	}
	
	public void dimensionUpdate(double width, double height) {
		this.lblDimension.setText("  Larghezza: " + width + "  Altezza: " + height);
	}
	
	public JPanel bottomPanelInfo() {
		bottomPaneInfo.add(mousePosition);
		bottomPaneInfo.add(lblZoom);
		bottomPaneInfo.add(btnDeZoom);
		bottomPaneInfo.add(btnZoom);
		bottomPaneInfo.add(lblDimension);
		return bottomPaneInfo;
	}
}
