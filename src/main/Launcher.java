package main;

import java.util.Collections;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class Launcher {

	public static void main(String[] args) {
		FlatMacDarkLaf.setup();
//		JPaint jpaint = new JPaint();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				new JPaint();
				
				FlatMacDarkLaf.updateUI();
				
				try {
					UIManager.setLookAndFeel(new FlatMacDarkLaf());
					FlatMacDarkLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#f00"));
					FlatMacDarkLaf.revalidateAndRepaintAllFramesAndDialogs();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		});
				
		
/*		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(jpaint);
			jpaint.pack();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
*/		

	}

}
