/*
 * Decompiled with CFR 0.151.
 */
package swing_interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import java.awt.Window.Type;
import javax.swing.*;

public class Interfaccia_ADV {
	
	private Icone icona = new Icone();
	
	public JButton btnMatita = new JButton();
	public JButton btnGomma = new JButton();
    
	public JDialog frameAdvStrumenti() {
        JDialog frame = new JDialog();
        frame.setSize(25,100);
        frame.setType(Type.UTILITY);
        frame.setAlwaysOnTop(true);
        frame.setModalityType (ModalityType.MODELESS);
        frame.setFocusable(true);
        frame.toFront();
        frame.requestFocus();
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(1,2)); // Primo numero per le colonne, secondo per le righe
		pane.add(btnMatita);
		btnMatita.setIcon(new ImageIcon(icona.matita));
		btnMatita.setSize(1, 1);
		pane.add(btnGomma);
		btnGomma.setIcon(new ImageIcon(icona.gomma));
		btnGomma.setSize(1, 1);
		frame.add(pane);
//		frame.pack();
		frame.setTitle("Strumenti");
		frame.setIconImage(icona.vuoto);
/*		In alternativa all'icona del JFrame vuota:
 * 		Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE); 	<- 	Icona da 1 pixel x 1 pixel vuota
 * 		frame.setIconImage(new ImageIcon(icon));
 */		frame.setResizable(true);
		frame.setDefaultCloseOperation(2); // 2 = Dispose
		frame.setVisible(true);
		frame.getContentPane();
        return frame;
    }
	
}

