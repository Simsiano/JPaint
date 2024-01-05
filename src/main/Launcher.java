package main;

import java.awt.Color;
import java.util.Collections;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLaf;
//import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class Launcher {

	public static void updateColor(String color) {
		FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", color));
		FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
		FlatMacDarkLaf.setup();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

//				FlatMacDarkLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", AccentColor.RED));
//				FlatMacDarkLaf.setup();

				FlatMacDarkLaf.setup();

				try {
					UIManager.setLookAndFeel(new FlatMacDarkLaf());
					UIManager.put("Button.focusColor", Color.RED);
					FlatLaf.updateUILater();
					FlatMacDarkLaf.setup();
					new JPaint();
					FlatMacDarkLaf.setup();

				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		});
	}
}