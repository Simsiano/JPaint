package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import azioni.AzioniBottoni;
import azioni.AzioniColori;
import azioni.AzioniMenu;
import azioni.AzioniMouse;
import azioni.AzioniRDB;
import azioni.dialoghi.AzioniDialogoGradienza;
import azioni.dialoghi.AzioniDialogoSpessore;
import grafica.Grafica;
import grafica.Tela;
import interfaccia.AccentColor;
import interfaccia.Icone;
import interfaccia.Interfaccia;
import interfaccia.dialoghi.DialogoGradienza;
import interfaccia.dialoghi.DialogoSpessore;

public class JPaint {

	private JFrame frame;
	private JPanel pan;
	private Tela tela;
	private Interfaccia interfaccia;
	private DialogoSpessore dialogoSpessore;
	private AzioniDialogoSpessore azioniDialogoSpessore;
	private DialogoGradienza dialogoGradienza;
	private AzioniDialogoGradienza azioniDialogoGradienza;
	private AzioniMenu azioniMenu ;
	private AzioniColori azioniColori;
	private AzioniBottoni azioniBottoni;
	private AzioniRDB azioniRdb;
	private AzioniMouse azioniMouse;

	public JScrollPane scroll;

	public JPaint() {

		this.frame = new JFrame();
		this.pan = new JPanel();
		this.tela = new Tela(pan, 800, 600);
		this.scroll = new JScrollPane(tela);
		this.interfaccia = new Interfaccia();
		this.dialogoSpessore = new DialogoSpessore();
		this.azioniDialogoSpessore = new AzioniDialogoSpessore(frame, dialogoSpessore);
		this.dialogoGradienza = new DialogoGradienza();
		this.azioniDialogoGradienza = new AzioniDialogoGradienza(frame, dialogoGradienza);
		this.azioniMenu = new AzioniMenu(frame, tela.grafica);
		this.azioniColori = new AzioniColori(frame);
		this.azioniBottoni = new AzioniBottoni(frame,tela.grafica,interfaccia);
		this.azioniMouse = new AzioniMouse(tela, tela.grafica, interfaccia);
		this.azioniRdb = new AzioniRDB();

		interfaccia.newFile.addActionListener(e -> azioniMenu.newFile());
		interfaccia.newFileAdv.addActionListener(e -> azioniMenu.newFileAdv());
		interfaccia.openFile.addActionListener(e -> azioniMenu.openFile());
		interfaccia.newSaveFile.addActionListener(e -> azioniMenu.newSaveFile());
		interfaccia.saveFile.addActionListener(e -> azioniMenu.saveFile());
		interfaccia.exit.addActionListener(e -> azioniMenu.exit());

		interfaccia.rdbMatita.addActionListener(e -> azioniRdb.setCuroreMatita());
		interfaccia.rdbGomma.addActionListener(e -> azioniRdb.setCursoreGomma());

		interfaccia.boxDimensioniMatita.addActionListener(e -> azioniBottoni.boxDimensionLinea());
		interfaccia.boxTipoPennelli.addActionListener(e -> azioniBottoni.actionTipoPennelli());
//		interfaccia.btnOptionPannello.addActionListener(e -> setterPennello());
		interfaccia.boxTipoForme.addActionListener(e -> azioniBottoni.actionTipoForme());
		interfaccia.boxStileForme.addActionListener(e -> azioniBottoni.actionStileForme());
		interfaccia.btnCustomStroke.addActionListener(e -> azioniDialogoSpessore.dialogoSpessore());
		interfaccia.btnGradienza.addActionListener(e -> azioniDialogoGradienza.dialogoGradienza());
		interfaccia.btnGC.addActionListener(e -> Launcher.updateColor(AccentColor.RED));

		dialogoGradienza.btnColore1.addActionListener(e -> azioniDialogoGradienza.sceltaColore(e));
		dialogoGradienza.btnColore2.addActionListener(e -> azioniDialogoGradienza.sceltaColore(e));
		dialogoGradienza.btnConfermaGradienza.addActionListener(e -> azioniDialogoGradienza.gradientColorDefiner());

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


		//		interfaccia.panDisegno.add(scroll, "Center");

		frame.setJMenuBar(interfaccia.mainMenuBar());

		pan.setLayout(new BorderLayout());
		pan.add(interfaccia.tabbedPane(), BorderLayout.NORTH);
		pan.add(scroll, BorderLayout.CENTER);
		scroll.setPreferredSize(new Dimension(Grafica.larghezza,Grafica.altezza));
		pan.add(interfaccia.bottomPanelInfo(), BorderLayout.SOUTH);

		frame.setUndecorated(false);
		frame.getContentPane().add(pan);
		frame.setTitle("Immagine - JPaint");
		frame.setIconImage(Icone.LOGO);
		frame.setPreferredSize(new Dimension(1270, 800));
		frame.setMaximumSize(new Dimension(1280, 800));
		frame.setMinimumSize(new Dimension(1280, 800));
		frame.setResizable(true);
//		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
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
