package actions;

import static actions.AzioniBottoni.selectedItem;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.SwingUtilities;

import graphics.Colori;
import graphics.Grafica;
import graphics.Tela;
import gui.Interfaccia;

public class AzioniMouse implements MouseListener, MouseMotionListener {
	
	private Tela tela;
	private Grafica grafica;
	private Interfaccia interfaccia;
	
	private Point startPoint;
	
	public AzioniMouse(Tela tela, Grafica grafica, Interfaccia interfaccia) {
		this.tela = tela;
		this.grafica = grafica;
		this.interfaccia = interfaccia;
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		tela.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));	
	}

	@Override
	public void mouseExited(MouseEvent me) {
		tela.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
			}
		}
		if (interfaccia.rdbPennello.isSelected()) {
			 if (selectedItem.equals("Aerografo")) {
				 if (SwingUtilities.isLeftMouseButton(me)) {
					 grafica.tracciaAerografo(me.getX(), me.getY(), Colori.getColorePrimario());
				 } else if (SwingUtilities.isRightMouseButton(me)) {
					 grafica.tracciaAerografo(me.getX(), me.getY(), Colori.getColoreSecondario());
				 }
			 }
		 }
	}

	@Override
	public void mousePressed(MouseEvent me) {
		grafica.updateStack(Grafica.index);
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), Colori.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), Colori.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbLinea.isSelected()) {
			startPoint = me.getPoint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			startPoint = me.getPoint();
			Grafica.quadrato = new Rectangle();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			startPoint = me.getPoint();
			System.out.println(startPoint);
			Grafica.rettangolo = new Rectangle();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			startPoint = me.getPoint();
			Grafica.cerchio = new Ellipse2D.Float();
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (grafica.getGradientMode() == true) {
			grafica.setGradientMode(false);
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.aggiungiLinea(Colori.getColorePrimario());
			Grafica.linea = null;
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (Grafica.quadrato.width != 0 || Grafica.quadrato.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiQuadrato(Colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiQuadrato(Colori.getColoreSecondario());
				}
			}
			Grafica.quadrato = null;
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (Grafica.rettangolo.width != 0 || Grafica.rettangolo.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiRettangolo(Colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiRettangolo(Colori.getColoreSecondario());
				}
			}
			Grafica.rettangolo = null;
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (Grafica.cerchio.width != 0 || Grafica.cerchio.height != 0) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.aggiungiCerchio(Colori.getColorePrimario());
				}
				if (SwingUtilities.isRightMouseButton(me)) {
					grafica.aggiungiCerchio(Colori.getColoreSecondario());
				}
			}
			Grafica.cerchio = null;
		}
		grafica.resetPosizione();
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		interfaccia.mouseLocation(me.getX(), me.getY());
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Pennello")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), Colori.getColorePrimario());
				} if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaPennello(me.getX(), me.getY(), Colori.getColoreSecondario());
				}
			}
			if (selectedItem.equals("Aerografo")) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), Colori.getColorePrimario());
				} else if (SwingUtilities.isRightMouseButton(me)) {
					grafica.tracciaAerografo(me.getX(), me.getY(), Colori.getColoreSecondario());
				}
			}
		}
		if (interfaccia.rdbMatita.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				grafica.tracciaMatita(me.getX(), me.getY(), Colori.getColorePrimario());
			} if (SwingUtilities.isRightMouseButton(me)) {
				grafica.tracciaMatita(me. getX(), me.getY(), Colori.getColoreSecondario());
			}
		}
		if (interfaccia.rdbGomma.isSelected()) {
			grafica.tracciaGomma(me.getX(), me.getY());
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.tracciaLinea(startPoint, me.getPoint(), Colori.getColorePrimario());
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoQuadrato(startPoint, me.getPoint(), Colori.getColorePrimario());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoQuadrato(startPoint, me.getPoint(), Colori.getColoreSecondario());
			}
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoRettangolo(startPoint, me.getPoint(), Colori.getColorePrimario());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoRettangolo(startPoint, me.getPoint(), Colori.getColoreSecondario());
			}
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (SwingUtilities.isLeftMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColorePrimario());
				grafica.tracciamentoCerchio(startPoint, me.getPoint());
			}
			if (SwingUtilities.isRightMouseButton(me)) {
				//				grafica.setColoreForme(grafica.getColoreSecondario());
				grafica.tracciamentoCerchio(startPoint, me.getPoint());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		startPoint = me.getPoint();
		interfaccia.mouseLocation(me.getX(), me.getY());
	}

}
