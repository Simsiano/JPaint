package main;

import com.formdev.flatlaf.FlatLightLaf;

public class Themes {
	
	public static final int LIGHT = 0;
	
	public static final int DARK = 1;
	
	public static final int INTELLIJ = 2;
	
	public static final int DARCULA = 3;
	
	public static final int MACOSDARK = 4;
	
	public static final int MACOLIGHT = 5;
	
	public void setTheme(int theme) {
		switch (theme) {
		case 1:
			FlatLightLaf.setup();
			FlatLightLaf.updateUI();
			break;
		case 2:
			break;

		default:
			break;
		}
	}

}
