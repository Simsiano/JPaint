package azioni.dialoghi;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import grafica.Colori;
import grafica.Grafica;
import interfaccia.dialoghi.DialogoGradienza;

public class AzioniDialogoGradienza {

	private JFrame frame;
	private DialogoGradienza dialogoGradienza;

	public AzioniDialogoGradienza(JFrame frame, DialogoGradienza dialogoGradienza) {
		this.frame = frame;
		this.dialogoGradienza = dialogoGradienza;
	}

	public void dialogoGradienza() {
		SwingUtilities.invokeLater(() -> {
			dialogoGradienza.getDialog(frame);
		});
	}

	public void sceltaColore(ActionEvent e) {
		if (e.getSource() == dialogoGradienza.btnColore1) {
			Color colore1 = sceltaColoreDialogo(Colori.getColoreGradienza1());
			Colori.setColoreGradienza1(colore1);
			dialogoGradienza.btnColore1.setBackground(colore1);
		}
		if (e.getSource() == dialogoGradienza.btnColore2) {
			Color colore2 = sceltaColoreDialogo(Colori.getColoreGradienza2());
			Colori.setColoreGradienza2(colore2);
			dialogoGradienza.btnColore2.setBackground(colore2);
		}
	}

	private Color sceltaColoreDialogo(Color colorePrecedente) {
		Color coloreScelto = JColorChooser.showDialog(null, "Seleziona un colore", colorePrecedente);
		if (coloreScelto==null) {
			return colorePrecedente;
		}
		return coloreScelto;
	}

	public void gradientColorDefiner() {
		Colori.setColoreForme(Colori.getColoreGradienza1(), Colori.getColoreGradienza2());
		Grafica.setPaint(Colori.getColoreForme());
		dialogoGradienza.close();
		Grafica.setGradientMode(true);
	}

}
