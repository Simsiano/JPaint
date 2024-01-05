package azioni;

import static azioni.AzioniBottoni.selectedItem;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayDeque;
import java.util.HashMap;

import grafica.Colori;
import grafica.Grafica;
import grafica.Tela;
import grafica.forme.Rettangolo;
import helpers.Cursore;
import helpers.Cursore.Forma;
import helpers.Cursore.Tipo;
import interfaccia.Interfaccia;
import interfaccia.PannelloLayers;

public class AzioniMouse implements MouseListener, MouseMotionListener {

	private Grafica grafica;
	private Interfaccia interfaccia;
//	private PannelloLayers pannelloLayers;
	private static HashMap<Integer, Color> colorMap;
	private static Color colore;
	private ArrayDeque<Point> pointTest = new ArrayDeque<>();

	private Point startPoint;

	public AzioniMouse(Tela tela, Grafica grafica, Interfaccia interfaccia) {
		this.grafica = grafica;
		this.interfaccia = interfaccia;
//		this.pannelloLayers = new PannelloLayers();
		AzioniMouse.colorMap = new HashMap<>();
		AzioniMouse.colorMap.put(MouseEvent.BUTTON1, Colori.getColorePrimario());
		AzioniMouse.colorMap.put(MouseEvent.BUTTON3, Colori.getColoreSecondario());
		new Cursore(tela, Grafica.getStrokeWidth(), Tipo.MATITA, Forma.TONDA);
	}

	@Override
	public void mouseEntered(MouseEvent me) {}

	@Override
	public void mouseExited(MouseEvent me) {}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (interfaccia.rdbContaGoccie.isSelected()) {
			grafica.getColorDropperColor(me.getX(), me.getY());
		}
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				grafica.tracciaAerografo(me.getX(), me.getY(), colore);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		Tela.repaintTimer.start();
		colore = colorMap.get(me.getButton());
		Cursore.update(colore);
		if (interfaccia.rdbMatita.isSelected()) {
			grafica.inizializza();
		}
		if (interfaccia.rdbPennello.isSelected()) {
			if (selectedItem.equals("Aerografo")) {
				grafica.tracciaAerografo(me.getX(), me.getY(), colore);
			}
		}
		if (interfaccia.rdbLinea.isSelected()) {
			startPoint = me.getPoint();
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			startPoint = me.getPoint();
			Grafica.forma = new Rettangolo();
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			startPoint = me.getPoint();
			Grafica.forma = new Rettangolo();
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			startPoint = me.getPoint();
			Grafica.cerchio = new Ellipse2D.Float();
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		Tela.repaintTimer.stop();
		PannelloLayers.update();
		if (Grafica.getGradientMode()) {
			Grafica.setGradientMode(false);
		}
		if (interfaccia.rdbMatita.isSelected()) {
			Grafica.forma.setColore(colore);
			Grafica.forma.setSpessore(Grafica.getStroke());
			grafica.updateStack();
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.aggiungiLinea(colore);
			Grafica.forma = null;
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			if (Grafica.forma.getLarghezza() != 0 || Grafica.forma.getAltezza() != 0) {
				grafica.aggiungiQuadrato(colore);
			}
			Grafica.forma = null;
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			if (Grafica.forma.getLarghezza() != 0 || Grafica.forma.getAltezza() != 0) {
				grafica.aggiungiRettangolo(colore);
			}
			Grafica.forma = null;
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			if (Grafica.cerchio.width != 0 || Grafica.cerchio.height != 0) {
				grafica.aggiungiCerchio(colore);
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
				grafica.tracciaPennello(me.getX(), me.getY(), colore);
			}
			if (selectedItem.equals("Aerografo")) {
				grafica.tracciaAerografo(me.getX(), me.getY(), colore);
			}
		}
		if (interfaccia.rdbMatita.isSelected()) {
			pointTest.push(me.getPoint());
			grafica.tracciaMatita(me.getX(), me.getY(), colore);
		}
		if (interfaccia.rdbGomma.isSelected()) {
			grafica.tracciaGomma(me.getX(), me.getY());
		}
		if (interfaccia.rdbLinea.isSelected()) {
			grafica.tracciaLinea(startPoint, me.getPoint(), colore);
		}
		if (interfaccia.rdbQuadrato.isSelected()) {
			grafica.tracciamentoQuadrato(startPoint, me.getPoint(), colore);
		}
		if (interfaccia.rdbRettangolo.isSelected()) {
			grafica.tracciamentoRettangolo(startPoint, me.getPoint(), colore);
		}
		if (interfaccia.rdbCerchio.isSelected()) {
			grafica.tracciamentoCerchio(startPoint, me.getPoint());
		}
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		startPoint = me.getPoint();
		interfaccia.mouseLocation(me.getX(), me.getY());
	}

	public static HashMap<Integer, Color> getColorMap() {
		return colorMap;
	}

	public static Color getColore() {
		return colore;
	}

}
