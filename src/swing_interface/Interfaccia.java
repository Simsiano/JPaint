package swing_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

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
	public JButton btnStilePersonalizzato = new JButton("Personalizza");

	public Dimension btnColorSize = new Dimension(25, 25);
	public Dimension btnColorSizeBig = new Dimension(25, 25);

	public JButton btnColorChange = new JButton();
	public JButton btnColorePrimario = new JButton();
	public JButton btnColoreSecondario = new JButton();

	public JButton btnColoreRosso = new JButton();
	public JButton btnColoreVerde = new JButton();
	public JButton btnColoreBlu = new JButton();
	public JButton btnColoreGiallo = new JButton();
	public JButton btnColoreNero = new JButton();
	public JButton btnColoreBianco = new JButton();

	public JButton btnColoreCustom1 = new JButton();
	public JButton btnColoreCustom2 = new JButton();
	public JButton btnColoreCustom3 = new JButton();
	public JButton btnColoreCustom4 = new JButton();
	public JButton btnColoreCustom5 = new JButton();
	
	public JButton btnColorePalette1 = new JButton();
	public JButton btnColorePalette2 = new JButton();
	public JButton btnColorePalette3 = new JButton();
	public JButton btnColorePalette4 = new JButton();
	public JButton btnColorePalette5 = new JButton();

	public JButton btnDetach = new JButton("\u2193");
	
	private JPanel bottomPaneInfo = new JPanel(); 
	private JLabel mousePosition = new JLabel();
	
	private JLabel lblZoom = new JLabel("Zoom: ");
	public JButton btnDeZoom = new JButton("-");
	public JButton btnZoom = new JButton("+");
	
	public JLabel lblDimension = new JLabel();

	public ButtonGroup bG = new ButtonGroup();
	public JRadioButton btnLayer1 = new JRadioButton("Layer 1");
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
		tabbedPane.addTab("Visualizza", null, panelDetach(), "Visualizza");
		return tabbedPane;
	}

	public ButtonGroup btnGroup() {
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

	public JPanel panelStrumentiDisegno() {
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

	public JPanel panelDimensioniLinea() {
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

	public JPanel panelTipoPannelli() {
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

	public JPanel panelTipoForme() {
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
		paneTipoForme.add(btnStilePersonalizzato);
		return paneTipoForme;
	}

	public JPanel panelDisegnaMain() {
		JPanel paneDisegnaMain = new JPanel();
		paneDisegnaMain.setLayout(new FlowLayout(1));
		paneDisegnaMain.add(panelStrumentiDisegno());
		paneDisegnaMain.add(panelDimensioniLinea());
		paneDisegnaMain.add(panelTipoPannelli());
		paneDisegnaMain.add(panelTipoForme());
		return paneDisegnaMain;
	}

	public JPanel panelColori() {
		JPanel panPaletteColori = new JPanel();
		panPaletteColori.setLayout(new GridLayout(0, 6));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "  Colori  ",
				2, 5);
		panPaletteColori.setBorder(titledBorder);

		btnColoreRosso.setPreferredSize(btnColorSize);
		btnColoreVerde.setPreferredSize(btnColorSize);
		btnColoreBlu.setPreferredSize(btnColorSize);
		btnColoreGiallo.setPreferredSize(btnColorSize);
		btnColoreNero.setPreferredSize(btnColorSize);
		btnColoreBianco.setPreferredSize(btnColorSize);

		btnColoreRosso.setBackground(new Color(255, 0, 0));
		btnColoreVerde.setBackground(new Color(0, 255, 0));
		btnColoreBlu.setBackground(new Color(0, 0, 255));
		btnColoreGiallo.setBackground(new Color(255, 255, 0));
		btnColoreNero.setBackground(new Color(0, 0, 0));
		btnColoreBianco.setBackground(new Color(255, 255, 255));

		btnColoreRosso.setFocusPainted(false);
		btnColoreVerde.setFocusPainted(false);
		btnColoreBlu.setFocusPainted(false);
		btnColoreGiallo.setFocusPainted(false);
		btnColoreNero.setFocusPainted(false);
		btnColoreBianco.setFocusPainted(false);;

		panPaletteColori.add(btnColoreRosso);
		panPaletteColori.add(btnColoreVerde);
		panPaletteColori.add(btnColoreBlu);
		panPaletteColori.add(btnColoreGiallo);
		panPaletteColori.add(btnColoreNero);
		panPaletteColori.add(btnColoreBianco);

		return panPaletteColori;
	}

	public JPanel panelCustomColori() {
		JPanel panCustomColori = new JPanel();
		panCustomColori.setLayout(new GridLayout(0, 5));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"  Colori Personalizzati  ", 2, 5);
		panCustomColori.setBorder(titledBorder);

		btnColoreCustom1.setPreferredSize(btnColorSize);
		btnColoreCustom2.setPreferredSize(btnColorSize);
		btnColoreCustom3.setPreferredSize(btnColorSize);
		btnColoreCustom4.setPreferredSize(btnColorSize);
		btnColoreCustom5.setPreferredSize(btnColorSize);

		btnColoreCustom1.setBackground(new Color(0, 0, 0));
		btnColoreCustom2.setBackground(new Color(0, 0, 0));
		btnColoreCustom3.setBackground(new Color(0, 0, 0));
		btnColoreCustom4.setBackground(new Color(0, 0, 0));
		btnColoreCustom5.setBackground(new Color(0, 0, 0));

		btnColoreCustom1.setEnabled(false);
		btnColoreCustom2.setEnabled(false);
		btnColoreCustom3.setEnabled(false);
		btnColoreCustom4.setEnabled(false);
		btnColoreCustom5.setEnabled(false);

		panCustomColori.add(btnColoreCustom1);
		panCustomColori.add(btnColoreCustom2);
		panCustomColori.add(btnColoreCustom3);
		panCustomColori.add(btnColoreCustom4);
		panCustomColori.add(btnColoreCustom5);

		return panCustomColori;
	}
	
	public JPanel panelCustomPalette() {
		JPanel panCustomPalette = new JPanel();
		panCustomPalette.setLayout(new GridLayout(0, 5));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"  Paletta Colori  ", 2, 5);
		panCustomPalette.setBorder(titledBorder);
		
		btnColorePalette1.setPreferredSize(btnColorSize);
		btnColorePalette2.setPreferredSize(btnColorSize);
		btnColorePalette3.setPreferredSize(btnColorSize);
		btnColorePalette4.setPreferredSize(btnColorSize);
		btnColorePalette5.setPreferredSize(btnColorSize);
		
		btnColorePalette1.setBackground(new Color(0, 0, 0));
		btnColorePalette2.setBackground(new Color(0, 0, 0));
		btnColorePalette3.setBackground(new Color(0, 0, 0));
		btnColorePalette4.setBackground(new Color(0, 0, 0));
		btnColorePalette5.setBackground(new Color(0, 0, 0));
		
		btnColorePalette1.setEnabled(false);
		btnColorePalette2.setEnabled(false);
		btnColorePalette3.setEnabled(false);
		btnColorePalette4.setEnabled(false);
		btnColorePalette5.setEnabled(false);
		
		panCustomPalette.add(btnColorePalette1);
		panCustomPalette.add(btnColorePalette2);
		panCustomPalette.add(btnColorePalette3);
		panCustomPalette.add(btnColorePalette4);
		panCustomPalette.add(btnColorePalette5);
		
		return panCustomPalette;
	}

	public JPanel panelShowPrimaryColor() {
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

	public JPanel panelShowSecondaryColor() {
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

	public JPanel panelShowColor() {
		JPanel paneShowColor = new JPanel();
		paneShowColor.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());
		paneShowColor.setBorder(titledBorder);
		paneShowColor.add(panelShowPrimaryColor());
		paneShowColor.add(panelShowSecondaryColor());
		return paneShowColor;
	}

	public JPanel panelBtnChangeColor() {
		JPanel paneChangeColor = new JPanel();
		JLabel label = new JLabel("Custom");
		paneChangeColor.setLayout(new BorderLayout());
		btnColorChange.setPreferredSize(btnColorSizeBig);
		btnColorChange.setBackground(new Color(0, 0, 0));
		paneChangeColor.add(btnColorChange, "North");
		paneChangeColor.add(label, "Center");
		return paneChangeColor;
	}

	public JPanel panelChangeColor() {
		JPanel paneChangeColor = new JPanel();
		paneChangeColor.setLayout(new FlowLayout(1));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());
		paneChangeColor.setBorder(titledBorder);
		paneChangeColor.add(panelBtnChangeColor());
		return paneChangeColor;
	}

	public JPanel panelColoriMain() {
		JPanel panComandi = new JPanel();
		panComandi.setLayout(new FlowLayout(1));
		panComandi.add(panelShowColor());
		panComandi.add(panelColori());
		panComandi.add(panelCustomColori());
		panComandi.add(panelCustomPalette());
		panComandi.add(panelChangeColor());
		return panComandi;
	}

	public JPanel panelDetach() {
		JPanel paneDetach = new JPanel();
		paneDetach.setLayout(new FlowLayout(1));
		btnDetach.setIcon(new ImageIcon(icone.matita));
		paneDetach.add(btnDetach);
		bG.add(btnLayer1);
		bG.add(btnLayer2);
		btnLayer1.isSelected();
		paneDetach.add(btnLayer1);
		btnLayer1.doClick();
		paneDetach.add(btnLayer2);
		return paneDetach;
	}
	
	public void mouseLocation(int meX, int meY) {
		bottomPaneInfo.remove(mousePosition);
		mousePosition = new JLabel("X: " + meX + "   Y: " + meY, SwingConstants.RIGHT);
		bottomPaneInfo.add(mousePosition);
		bottomPaneInfo.repaint();
		bottomPaneInfo.revalidate();
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
