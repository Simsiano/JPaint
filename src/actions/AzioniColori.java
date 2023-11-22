package actions;

import static gui.Interfaccia.btnColorePrimario;
import static gui.Interfaccia.btnColoreSecondario;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import graphics.Colori;

public class AzioniColori {
	
	private JFrame frame;
	private ActionColor azioniColore;
	
	public AzioniColori(JFrame frame) {
		this.frame = frame;
		azioniColore = new ActionColor();
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
		Colori.setColorePrimario(null);
		btnColorePrimario.setBackground(colorDefiner(me));
		Colori.setColorePrimario(colorDefiner(me));
		frame.repaint();
	}

	private void SecondaryColorDefiner(MouseEvent me) {
		Colori.setColoreSecondario(null);		
		btnColoreSecondario.setBackground(colorDefiner(me));
		Colori.setColoreSecondario(colorDefiner(me));
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
