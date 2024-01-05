package azioni;

import static interfaccia.Interfaccia.btnColorePrimario;
import static interfaccia.Interfaccia.btnColoreSecondario;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import grafica.Colori;
import helpers.Cursore;

public class AzioniColori {

	private JFrame frame;
	private ActionColor azioniColore;

	public AzioniColori(JFrame frame) {
		this.frame = frame;
		this.azioniColore = new ActionColor();
	}

	public class ActionColor extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent me) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				PrimaryColorDefiner(me);
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				SecondaryColorDefiner(me);
			}
		}
	}

	private void PrimaryColorDefiner(MouseEvent me) {
		Colori.setColorePrimario(colorDefiner(me));
		AzioniMouse.getColorMap().put(MouseEvent.BUTTON1, colorDefiner(me));
		Cursore.update(colorDefiner(me));
		btnColorePrimario.setBackground(colorDefiner(me));
		frame.repaint();
	}

	private void SecondaryColorDefiner(MouseEvent me) {
		Colori.setColoreSecondario(colorDefiner(me));
		AzioniMouse.getColorMap().put(MouseEvent.BUTTON3, colorDefiner(me));
		Cursore.update(colorDefiner(me));
		btnColoreSecondario.setBackground(colorDefiner(me));
		frame.repaint();
	}

	private Color colorDefiner(MouseEvent me) {
		JButton clickedButton = (JButton) me.getSource();
		Color colorDraw = clickedButton.getBackground();
		return colorDraw;
	}

	public ActionColor getActionColor() {
		return azioniColore;
	}

}
