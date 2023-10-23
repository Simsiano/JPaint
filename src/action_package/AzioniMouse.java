package action_package;

import static action_package.AzioniBottoni.selectedItem;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import graphical_logic.Colori;
import graphical_logic.Grafica;
import swing_interface.Interfaccia;

public class AzioniMouse implements MouseListener, MouseMotionListener {
	
	private JFrame frame;
	private Grafica grafica;
	private Interfaccia interfaccia;
	private Colori colori;
	
	private Point startPoint;
	
	public AzioniMouse(JFrame frame, Grafica grafica, Interfaccia interfaccia) {
		this.frame = frame;
		this.grafica = grafica;
		this.interfaccia = interfaccia;
		colori = new Colori();
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		grafica.setCursor(cursor);	
	}

	@Override
	public void mouseExited(MouseEvent me) {
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		grafica.setCursor(cursor);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		/*		if (interfaccia.rdbCerchio.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.aggiungiCerchio(me.getX(), me.getY(), grafica.getColorePrimario());
			} else if (SwingUtilities.isRightMouseButton(me)) {
				grafica.aggiungiCerchio(me.getX(), me.getY(), grafica.getColoreSecondario());
			}
		}
*/		
		if (interfaccia.rdbContaGoccie.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.getColorDropperColor(me.getX(), me.getY());
				System.out.println("Funziona");
			}
		}
		if (interfaccia.rdbPennello.isSelected()) {
			 if (selectedItem.equals("Aerografo")) {
				 if (SwingUtilities.isLeftMouseButton(me)) {
					 grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColorePrimario());
				 } else if (SwingUtilities.isRightMouseButton(me)) {
					 grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColoreSecondario());
				 }
			 }
		 }
	}

	@Override
	public void mousePressed(MouseEvent me) {
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbLinea.isSelected()) {
			startPoint = me.getPoint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			startPoint = me.getPoint();
			grafica.quadrato = new Rectangle();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			startPoint = me.getPoint();
			System.out.println(startPoint);
			grafica.rettangolo = new Rectangle();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			startPoint = me.getPoint();
			grafica.cerchio = new Ellipse2D.Double();
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (grafica.getGradientMode() == true) {
			grafica.setGradientMode(false);
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.aggiungiLinea(colori.getColorePrimario());
			grafica.linea = null;
			frame.repaint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (grafica.quadrato.width != 0 || grafica.quadrato.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiQuadrato(colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiQuadrato(colori.getColoreSecondario());
				}
			}
			grafica.quadrato = null;
			grafica.repaint();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (grafica.rettangolo.width != 0 || grafica.rettangolo.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiRettangolo(colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiRettangolo(colori.getColoreSecondario());
				}
			}
			grafica.rettangolo = null;
			grafica.repaint();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (grafica.cerchio.width != 0 || grafica.cerchio.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiCerchio(grafica.cerchio, colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiCerchio(grafica.cerchio, colori.getColoreSecondario());
				}
			}
			grafica.cerchio = null;
			grafica.repaint();
		}
		grafica.resetLinea();
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		interfaccia.mouseLocation(me.getX(), me.getY());
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Pennello")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), colori.getColorePrimario());
				} if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), colori.getColoreSecondario());
				}
			}
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), colori.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbMatita.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.tracciaMatita(me.getX(), me.getY(), colori.getColorePrimario());
			} if (SwingUtilities.isRightMouseButton(me)) {
				grafica.tracciaMatita(me. getX(), me.getY(), colori.getColoreSecondario());
			}
		}
		if (interfaccia.rdbGomma.isSelected()) {
			grafica.tracciaGomma(me.getX(), me.getY());
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.tracciamentoLinea(startPoint, me.getX(), me.getY());
			grafica.repaint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoQuadrato(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoQuadrato(startPoint, me.getX(), me.getY());
			}
			grafica.repaint();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoRettangolo(colori.getColorePrimario(), startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoRettangolo(colori.getColoreSecondario(), startPoint, me.getX(), me.getY());
			}
			grafica.repaint();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoCerchio(startPoint, me.getX(), me.getY());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoCerchio(startPoint, me.getX(), me.getY());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		startPoint = me.getPoint();
		interfaccia.mouseLocation(me.getX(), me.getY());
	}

}
