package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import actions.AzioniBottoni;
import actions.AzioniColori;
import actions.AzioniMenu;
import actions.AzioniMouse;
import graphics.Grafica;
import graphics.Tela;
import gui.DialogoGradienza;
import gui.DialogoSpessore;
import gui.Icone;
import gui.Interfaccia;

public class JPaint {

	private JFrame frame = new JFrame();
	private JPanel pan;
	private Tela tela = new Tela(800,600);
	private Interfaccia interfaccia = new Interfaccia();
	private DialogoGradienza dialogoGradienza = new DialogoGradienza();
	private DialogoSpessore dialogoSpessore = new DialogoSpessore();
	private AzioniMenu azioniMenu = new AzioniMenu(frame,tela.grafica);
	private AzioniColori azioniColori = new AzioniColori(frame);
	private AzioniBottoni azioniBottoni = new AzioniBottoni(frame,tela.grafica,interfaccia,dialogoGradienza,dialogoSpessore);
	private AzioniMouse azioniMouse = new AzioniMouse(tela, tela.grafica, interfaccia);
	
	public JPaint() {
		
		this.pan = new JPanel();

		interfaccia.newFile.addActionListener(e -> azioniMenu.newFile());
		interfaccia.newFileAdv.addActionListener(e -> azioniMenu.newFileAdv());
		interfaccia.openFile.addActionListener(e -> azioniMenu.openFile());
		interfaccia.newSaveFile.addActionListener(e -> azioniMenu.newSaveFile());
		interfaccia.saveFile.addActionListener(e -> azioniMenu.saveFile());
		interfaccia.exit.addActionListener(e -> azioniMenu.exit());

		interfaccia.boxDimensioniMatita.addActionListener(e -> azioniBottoni.boxDimensionLinea());
		interfaccia.boxTipoPennelli.addActionListener(e -> azioniBottoni.actionTipoPennelli());
//		interfaccia.btnOptionPannello.addActionListener(e -> setterPennello());
		interfaccia.boxTipoForme.addActionListener(e -> azioniBottoni.actionTipoForme());
		interfaccia.boxStileForme.addActionListener(e -> azioniBottoni.actionStileForme());
		interfaccia.btnGradienza.addActionListener(e -> azioniBottoni.dialogoGradienza());
		interfaccia.btnCustomStroke.addActionListener(e -> azioniBottoni.dialogoSpessore());
		interfaccia.btnGC.addActionListener(e -> System.gc());
		
		dialogoGradienza.btnColore1.addActionListener(e -> azioniBottoni.sceltaColore(e));
		dialogoGradienza.btnColore2.addActionListener(e -> azioniBottoni.sceltaColore(e));
		dialogoGradienza.btnConfermaGradienza.addActionListener(e -> azioniBottoni.gradientColorDefiner());

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
		
		Interfaccia.btnIndietro.addActionListener(e -> tela.grafica.undo());
		Interfaccia.btnAvanti.addActionListener(e -> tela.grafica.redo());
		interfaccia.btnSelezionaLayer.addActionListener(e -> Grafica.selectLayer(frame));
		interfaccia.btnAggiungiLayer.addActionListener(e -> tela.grafica.addLayer(Grafica.larghezza, Grafica.altezza));

		tela.addMouseListener(azioniMouse);
		tela.addMouseMotionListener(azioniMouse);

		interfaccia.panDisegno.setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(tela);
		//		interfaccia.panDisegno.add(scroll, "Center");

		frame.setJMenuBar(interfaccia.mainMenuBar());

		pan.setLayout(new BorderLayout());
		pan.add(interfaccia.tabbedPane(), BorderLayout.NORTH);
		pan.add(scroll, BorderLayout.CENTER);
		scroll.setPreferredSize(new Dimension(Grafica.larghezza,Grafica.altezza));
		pan.add(interfaccia.bottomPanelInfo(), BorderLayout.SOUTH);

		frame.getContentPane().add(pan);
		frame.setTitle("Immagine - JPaint");
		frame.setIconImage(Icone.LOGO);
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
