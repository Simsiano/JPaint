package main_package;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import action_package.AzioniBottoni;
import action_package.AzioniColori;
import action_package.AzioniMenu;
import action_package.AzioniMouse;
import graphical_logic.Grafica;
import swing_interface.Custom_Shape;
import swing_interface.Icone;
import swing_interface.Interfaccia;

public class JPaint {

	private JFrame frame = new JFrame();
	private JPanel pan;
	private Grafica grafica = new Grafica();
	private Interfaccia interfaccia = new Interfaccia();
	private Custom_Shape dialogo_personalizzazione = new Custom_Shape();
	private AzioniMenu azioniMenu = new AzioniMenu(frame,grafica);
	private AzioniColori azioniColori = new AzioniColori(frame);
	private AzioniBottoni azioniBottoni = new AzioniBottoni(frame,grafica,interfaccia,dialogo_personalizzazione);
	private Icone icona;
	

	

	public JPaint() {
		this.pan = new JPanel();
		this.icona = new Icone();

		interfaccia.newFile.addActionListener(e -> azioniMenu.newFile());
		interfaccia.newFileAdv.addActionListener(e -> azioniMenu.newFileAdv());
		interfaccia.openFile.addActionListener(e -> azioniMenu.openFile());
		interfaccia.newSaveFile.addActionListener(e -> azioniMenu.newSaveFile());
		interfaccia.saveFile.addActionListener(e -> azioniMenu.saveFile());
		interfaccia.exit.addActionListener(e -> azioniMenu.exit());

		//		interfaccia.strumenti.addActionListener(this);


		interfaccia.boxDimensioniMatita.addActionListener(e -> azioniBottoni.boxDimensionLinea());
		interfaccia.boxTipoPennelli.addActionListener(e -> azioniBottoni.actionTipoPennelli());
//		interfaccia.btnOptionPannello.addActionListener(e -> setterPennello());
		interfaccia.boxTipoForme.addActionListener(e -> azioniBottoni.actionTipoForme());
		interfaccia.boxStileForme.addActionListener(e -> azioniBottoni.actionStileForme());
		interfaccia.btnStilePersonalizzato.addActionListener(e -> azioniBottoni.dialogoAperturaPersonalizzazione(frame));
		
		dialogo_personalizzazione.btnColore1.addActionListener(e -> azioniBottoni.sceltaColore(e));
		dialogo_personalizzazione.btnColore2.addActionListener(e -> azioniBottoni.sceltaColore(e));
		dialogo_personalizzazione.btnConfermaGradienza.addActionListener(e -> azioniBottoni.gradientColorDefiner());

		interfaccia.btnColoreRosso.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreVerde.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreBlu.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreGiallo.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreNero.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreBianco.addMouseListener(azioniColori.getActionColor());

		interfaccia.btnColorChange.addActionListener(e -> azioniBottoni.btnColorChange());

		interfaccia.btnColoreCustom1.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreCustom2.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreCustom3.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreCustom4.addMouseListener(azioniColori.getActionColor());
		interfaccia.btnColoreCustom5.addMouseListener(azioniColori.getActionColor());

		grafica.addMouseListener(new AzioniMouse(frame, grafica, interfaccia));
		grafica.addMouseMotionListener(new AzioniMouse(frame, grafica, interfaccia));
		JLabel test = new JLabel();
		test.setIcon(new ImageIcon(icona.matita));

		interfaccia.panDisegno.setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(grafica);
		//		interfaccia.panDisegno.add(scroll, "Center");

		frame.setJMenuBar(interfaccia.mainMenuBar());

		pan.setLayout(new BorderLayout());
		pan.add(interfaccia.tabbedPane(), BorderLayout.NORTH);
		pan.add(scroll, BorderLayout.CENTER);
		pan.add(interfaccia.bottomPanelInfo(), BorderLayout.SOUTH);

		frame.getContentPane().add(pan);
		frame.setTitle("Immagine - JPaint");
		frame.setIconImage(icona.logo);
		frame.setPreferredSize(new Dimension(1270, 800));
		frame.setMaximumSize(new Dimension(1280, 800));
		frame.setMinimumSize(new Dimension(1280, 800));
		frame.setResizable(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choose = JOptionPane.showConfirmDialog(null,
						"Vuoi veramente uscire?", "Conferma chiusura", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (choose == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				} else {
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		frame.setVisible(true);
	}
	
}
