package actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import graphics.Colori;
import graphics.Grafica;
import gui.DialogoGradienza;
import gui.DialogoGradienzaDraw;
import gui.Interfaccia;

public class AzioniBottoni {
	
	private JFrame frame;
	
	private Grafica grafica;
	private Interfaccia interfaccia;
	private DialogoGradienza dialogoGradienza;
	private DialogoGradienzaDraw shapeDraw;
	
	public static String selectedItem = "";
	private int NumberCustomButton = 0;
	
	public AzioniBottoni(JFrame frame, Grafica grafica, Interfaccia interfaccia, DialogoGradienza dialogoGradienza) {
		this.frame = frame;
		this.grafica = grafica;
		this.interfaccia = interfaccia;
		this.dialogoGradienza = dialogoGradienza;
		this.shapeDraw = new DialogoGradienzaDraw();
	}
	
	public void boxDimensionLinea() {
		StringBuilder selectedItem = new StringBuilder(new String((String) interfaccia.boxDimensioniMatita.getSelectedItem()));
		if (selectedItem.toString().equals("Custom")) {
			JLabel label = new JLabel("Inserisci uno spessore");
			JTextField textfield = new JTextField(3);
			Object[] item = { label, textfield };
			int chooser = JOptionPane.showOptionDialog(frame, item, "Seleziona spessore", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (chooser == JOptionPane.OK_OPTION) {
				try {
					grafica.setStroke(Integer.parseInt(textfield.getText()));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex, "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			grafica.setStroke(Integer.parseInt(selectedItem.delete(1, 3).toString()));
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
	
	public void dialogoAperturaPersonalizzazione() {
		SwingUtilities.invokeLater(() -> {
			dialogoGradienza.getDialog();
		});
	}
	
	public void sceltaColore(ActionEvent e) {
		if (e.getSource() == dialogoGradienza.btnColore1) {
			Color colore1 = sceltaColoreDialogo(Colori.getColoreGradienza1());
			Colori.setColoreGradienza1(colore1);
			dialogoGradienza.btnColore1.setBackground(colore1);
			shapeDraw.repaint();
		}
		if (e.getSource() == dialogoGradienza.btnColore2) {
			Color colore2 = sceltaColoreDialogo(Colori.getColoreGradienza2());
			Colori.setColoreGradienza2(colore2);
			dialogoGradienza.btnColore2.setBackground(colore2);
			shapeDraw.repaint();
		}
	}
	
	private Color sceltaColoreDialogo(Color colorePrecedente) {
		Color coloreScelto = JColorChooser.showDialog(null, "Seleziona un colore", colorePrecedente);
		if (coloreScelto==null) return colorePrecedente;
		shapeDraw.repaint();
		return coloreScelto;
	}

	
	public void gradientColorDefiner() {
		Colori.setColoreForme(Colori.getColoreGradienza1(), Colori.getColoreGradienza2());
		dialogoGradienza.close();
		grafica.setGradientMode(true);
	}
	
	public void btnColorChange() {
		Color coloreAttuale = Colori.getColorePrimario();
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
		}
	}
	
	public String getSelectedItem() {
		return selectedItem;
	}

}
