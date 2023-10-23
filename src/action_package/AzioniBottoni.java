package action_package;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import graphical_logic.Colori;
import graphical_logic.Grafica;
import swing_interface.Custom_Shape;
import swing_interface.Custom_Shape_Draw;
import swing_interface.Interfaccia;

public class AzioniBottoni {
	
	private JFrame frame;
	
	private Grafica grafica;
	private Colori colori;
	private Interfaccia interfaccia;
	private Custom_Shape dialogo_personalizzazione;
	private Custom_Shape_Draw shapeDraw;
	
	public static String selectedItem = "";
	private int NumberCustomButton = 0;
	
	public AzioniBottoni(JFrame frame, Grafica grafica, Interfaccia interfaccia, Custom_Shape dialogo_personalizzazione) {
		this.frame = frame;
		this.grafica = grafica;
		this.interfaccia = interfaccia;
		this.dialogo_personalizzazione = dialogo_personalizzazione;
		this.colori = new Colori();
		this.shapeDraw = new Custom_Shape_Draw();
	}
	
	public void boxDimensionLinea() {
		StringBuilder selectedItem = new StringBuilder(new String((String) interfaccia.boxDimensioniMatita.getSelectedItem()));
		//		String selectedItem = new String((String) interfaccia.boxDimensioniMatita.getSelectedItem());
		System.out.println(selectedItem);
		if (selectedItem.toString().equals("Custom")) {
			JLabel label = new JLabel("Inserisci uno spessore");
			JTextField textfield = new JTextField(3);
			Object[] item = { label, textfield };
			int chooser = JOptionPane.showOptionDialog(frame, item, "Seleziona spessore", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (chooser == JOptionPane.OK_OPTION) {
				try {
					grafica.setSpessore(Integer.parseInt(textfield.getText()));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex, "Errore", JOptionPane.ERROR_MESSAGE);
				}
			} else {
			}
		} else {
			String a = selectedItem.substring(1, 3);
			System.out.println(a);
			//			grafica.setSpessore((int)(selectedItem.substring(1, 3)));

			grafica.setSpessore(Integer.parseInt(selectedItem.delete(1, 3).toString()));
		}
	}
	
	public void actionTipoPennelli() {
		selectedItem = new String((String) interfaccia.boxTipoPennelli.getSelectedItem());
		if (selectedItem.equals("Pennello")) {
			interfaccia.btnOptionPannello.setEnabled(true);
		} else {
			interfaccia.btnOptionPannello.setEnabled(false);
		}
		System.out.println(selectedItem);
	}
	
	public void actionTipoForme() {
		String selectedItem = new String((String) interfaccia.boxTipoForme.getSelectedItem());
		if (selectedItem.equals("----------")) {
			interfaccia.rdbNull.setSelected(true);
		}
		if (selectedItem.equals("Linea")) {
			interfaccia.rdbLinea.setSelected(true);
		}
		if (selectedItem.equals("Quadrato")) {
			interfaccia.rdbQuadrato.setSelected(true);
		}
		if (selectedItem.equals("Rettangolo")) {
			interfaccia.rdbRettangolo.setSelected(true);
		}
		if (selectedItem.equals("Cerchio")) {
			interfaccia.rdbCerchio.setSelected(true);
		}
	}
	
	public void actionStileForme() {
		String selectedItem = new String((String) interfaccia.boxStileForme.getSelectedItem());
		if (selectedItem.equals("Vuoto")) {
			grafica.setRiempimento(false);
		}
		if (selectedItem.equals("Pieno")) {
			grafica.setRiempimento(true);
		}
	}
	
	public void dialogoAperturaPersonalizzazione(JFrame frame) {
		SwingUtilities.invokeLater(() -> {
			dialogo_personalizzazione.Dialogo_Forme(frame);
		});
	}
	
	public void sceltaColore(ActionEvent e) {
		if (e.getSource() == dialogo_personalizzazione.btnColore1) {
			Color colore1 = sceltaColoreDialogo(colori.getColoreGradienza1());
			colori.setColoreGradienza1(colore1);
			System.out.println("Colore1 primitivo: " + colore1);
			dialogo_personalizzazione.btnColore1.setBackground(colore1);
			shapeDraw.repaint();
		}
		if (e.getSource() == dialogo_personalizzazione.btnColore2) {
			Color colore2 = sceltaColoreDialogo(colori.getColoreGradienza2());
			colori.setColoreGradienza2(colore2);
			dialogo_personalizzazione.btnColore2.setBackground(colore2);
			shapeDraw.repaint();
		}
	}
	
	private Color sceltaColoreDialogo(Color colorePrecedente) {
		Color coloreScelto = JColorChooser.showDialog(dialogo_personalizzazione.getDialog(), "Seleziona un colore", colorePrecedente);
		shapeDraw.repaint();
		return coloreScelto;
	}

	
	public void gradientColorDefiner() {
		grafica.setColoreForme(new GradientPaint(0,0,colori.getColoreGradienza1(),0,0,colori.getColoreGradienza2()));
		dialogo_personalizzazione.close();
		grafica.setGradientMode(true);
		frame.repaint();
	}
	
	public void btnColorChange() {
		Color coloreAttuale = colori.getColorePrimario();
		Color coloreScelto = JColorChooser.showDialog(frame, "Seleziona un colore", coloreAttuale);
		if (coloreScelto != null) {

			NumberCustomButton++;

			//			interfaccia.btnColorePrimario.setBackground(coloreScelto);
			interfaccia.btnColorChange.setBackground(coloreScelto);

			switch (NumberCustomButton) {
			case 1: {
				interfaccia.btnColoreCustom1.setEnabled(true);
				interfaccia.btnColoreCustom1.setBackground(coloreScelto);
				break;
			}
			case 2: {
				interfaccia.btnColoreCustom2.setEnabled(true);
				interfaccia.btnColoreCustom2.setBackground(coloreScelto);
				break;
			}
			case 3: {
				interfaccia.btnColoreCustom3.setEnabled(true);
				interfaccia.btnColoreCustom3.setBackground(coloreScelto);
				break;
			}
			case 4: {
				interfaccia.btnColoreCustom4.setEnabled(true);
				interfaccia.btnColoreCustom4.setBackground(coloreScelto);
				break;
			}
			case 5: {
				interfaccia.btnColoreCustom5.setEnabled(true);
				interfaccia.btnColoreCustom5.setBackground(coloreScelto);
				NumberCustomButton = 0;
			}
			}
			//			grafica.setColorePrimario(coloreScelto);
			frame.repaint();
		}
	}
	
	public String getSelectedItem() {
		return selectedItem;
	}

}
