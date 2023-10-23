package swing_interface;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import graphical_logic.Colori;

public class Custom_Shape {

	private Custom_Shape_Draw drawShape;
	private Colori colori;
	private Icone icona;
	
	private JDialog dialog;
	
	public JButton btnConfermaGradienza = new JButton("Conferma");
//	public JButton btnAnnulla = new JButton("Annulla");
	
	private JLabel lblColore1 = new JLabel("Colore 1");
	private JLabel lblColore2 = new JLabel("Colore 2");
		
	public JButton btnColore1 = new JButton();
	public JButton btnColore2 = new JButton();
	
	
	public Custom_Shape() {
	}
	
	public JDialog Dialogo_Forme(JFrame parentFrame) {
		
		drawShape = new Custom_Shape_Draw();
		colori = new Colori();
		icona = new Icone();
		
		dialog = new JDialog(parentFrame, "Personalizza", true);
		
		drawShape = new Custom_Shape_Draw();
		
		JPanel gradientColor1Panel = new JPanel();
		gradientColor1Panel.setLayout(new FlowLayout());
		gradientColor1Panel.add(lblColore1);
		btnColore1.setPreferredSize(new Dimension(35,25));;
		btnColore1.setBackground(colori.getColoreGradienza1());
		gradientColor1Panel.add(btnColore1);
		
		
		JPanel gradientColor2Panel = new JPanel();
		gradientColor2Panel.setLayout(new FlowLayout());
		gradientColor2Panel.add(lblColore2);
		btnColore2.setPreferredSize(new Dimension(35,25));
		btnColore2.setBackground(colori.getColoreGradienza2());
		gradientColor2Panel.add(btnColore2);
		
		JPanel gradientContentPanel = new JPanel();
		gradientContentPanel.setLayout(new BorderLayout());
		gradientContentPanel.add(gradientColor1Panel, BorderLayout.LINE_START);
		gradientContentPanel.add(gradientColor2Panel, BorderLayout.LINE_END);
		gradientContentPanel.add(drawShape, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnConfermaGradienza);
//		buttonPanel.add(btnAnnulla);
		
		JPanel gradientPanel = new JPanel();
		gradientPanel.setLayout(new BorderLayout());
		gradientPanel.add(gradientContentPanel, BorderLayout.CENTER);
		gradientPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		JPanel opacityPanel = new JPanel();
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab("Gradiente", null, gradientPanel, "Gradiente");
		tabPane.addTab("Opacità", null, opacityPanel, "Opacità");
		
		dialog.add(tabPane);
		
		dialog.setSize(325, 300);
		dialog.setResizable(true);
		dialog.setLocationRelativeTo(parentFrame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setIconImage(icona.vuoto);
		dialog.setVisible(true);
		
		return dialog;
	}
	
	public JDialog getDialog() {
		return dialog;
	}

/*	Metodo alternativo l'unico problema è che devo memorizzare due variabili, ed è poco chiaro.
	private void sceltaColore(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		Color colorButton = clickedButton.getBackground();
		clickedButton.setBackground(sceltaColoreDialogo(colorButton));
	}
*/

	public void close() {
		dialog.dispose();
	}
	
}


