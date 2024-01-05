package azioni.dialoghi;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import interfaccia.dialoghi.DialogoSpessore;

public class AzioniDialogoSpessore {

	private JFrame frame;
	private DialogoSpessore dialogoSpessore;

	public AzioniDialogoSpessore(JFrame frame, DialogoSpessore dialogoSpessore) {
		this.frame = frame;
		this.dialogoSpessore = dialogoSpessore;
	}

	public void dialogoSpessore() {
		SwingUtilities.invokeLater(() -> {
			dialogoSpessore.getDialog(frame);
		});
	}

}
