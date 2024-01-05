package interfaccia;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import grafica.Grafica;
import helpers.LayerHolder;

public class PannelloLayers {

	private static Image resizedImage;
	private static LayerHolder btnLayer;

	public JPanel getPanel() {
		JPanel panel = new JPanel();
		btnLayer = new LayerHolder("Ciao)");
		resizedImage = Grafica.getBufferedImage(Grafica.index).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		btnLayer.setIcon(new ImageIcon(resizedImage));
		btnLayer.setPreferredSize(new Dimension(60,60));
		panel.add(btnLayer);
		return panel;
	}

	public static void update() {
		resizedImage = Grafica.getBufferedImage(Grafica.index).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		btnLayer.setIcon(new ImageIcon(resizedImage));
	}

}
