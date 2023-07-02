package jpaint_main;

import java.util.Collections;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatIntelliJLaf;
//import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class jpaint {

	public static void main(String[] args) {
		
		jpaint_main jpaint = new jpaint_main();
		
		FlatIntelliJLaf.setup();
		
		FlatIntelliJLaf.updateUI();
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
			FlatIntelliJLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#f00"));
			FlatIntelliJLaf.revalidateAndRepaintAllFramesAndDialogs();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			SwingUtilities.updateComponentTreeUI(jpaint);
			jpaint.pack();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
*/		jpaint.setVisible(true);

	}

}
