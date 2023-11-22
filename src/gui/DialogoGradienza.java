package gui;

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

import graphics.Colori;

public class DialogoGradienza {
	
	private JFrame frame;
	private JDialog dialog;
	
	private DialogoGradienzaDraw drawShape;
	private Icone icona;
	
	private JLabel lblColore1 = new JLabel("Colore 1");
	private JLabel lblColore2 = new JLabel("Colore 2");
		
	public JButton btnColore1 = new JButton();
	public JButton btnColore2 = new JButton();
	
	public JButton btnConfermaGradienza = new JButton("Conferma");

	
	public DialogoGradienza(JFrame frame) {
		this.frame = frame;
		drawShape = new DialogoGradienzaDraw();
		icona = new Icone();
	}
	
	public JDialog getDialog() {
		
		dialog = new JDialog(frame,"Personalizza",true);
		
		JPanel gradientColor1Panel = new JPanel();
		gradientColor1Panel.setLayout(new FlowLayout());
		gradientColor1Panel.add(lblColore1);
		btnColore1.setPreferredSize(new Dimension(35,25));;
		btnColore1.setBackground(Colori.getColoreGradienza1());
		gradientColor1Panel.add(btnColore1);
		
		JPanel gradientColor2Panel = new JPanel();
		gradientColor2Panel.setLayout(new FlowLayout());
		gradientColor2Panel.add(lblColore2);
		btnColore2.setPreferredSize(new Dimension(35,25));
		btnColore2.setBackground(Colori.getColoreGradienza2());
		gradientColor2Panel.add(btnColore2);
		
		JPanel gradientContentPanel = new JPanel();
		gradientContentPanel.setLayout(new BorderLayout());
		gradientContentPanel.add(gradientColor1Panel, BorderLayout.LINE_START);
		gradientContentPanel.add(gradientColor2Panel, BorderLayout.LINE_END);
		gradientContentPanel.add(drawShape, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(btnConfermaGradienza);
		
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
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(frame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setIconImage(icona.vuoto);
		dialog.setVisible(true);
		
		return dialog;
	}

	public void close() {
		dialog.dispose();
		System.gc();
	}
	
}


