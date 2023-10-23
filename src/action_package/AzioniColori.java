package action_package;

import static swing_interface.Interfaccia.btnColorePrimario;
import static swing_interface.Interfaccia.btnColoreSecondario;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import graphical_logic.Colori;

public class AzioniColori {
	
	private JFrame frame;
	private Colori colori;
	private ActionColor azioniColore;
	
	public AzioniColori(JFrame frame) {
		this.frame = frame;
		colori = new Colori();
		azioniColore = new ActionColor();
	}
	
	public class ActionColor extends MouseAdapter {
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
		colori.setColorePrimario(null);
		btnColorePrimario.setBackground(colorDefiner(me));
		colori.setColorePrimario(colorDefiner(me));
		frame.repaint();
	}

	private void SecondaryColorDefiner(MouseEvent me) {
		colori.setColoreSecondario(null);		
		btnColoreSecondario.setBackground(colorDefiner(me));
		colori.setColoreSecondario(colorDefiner(me));
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
