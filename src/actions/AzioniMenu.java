package actions;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import graphics.Grafica;

public class AzioniMenu {
	
	private JFrame frame;
	private Grafica grafica;
	
//	private BufferedImage buffer;
//	private Graphics2D gBuffer;
	private JFileChooser fileChooser;
	private int fileChoosed;
	private File filePicked;
	private boolean fileSaved;
//	private File outputFile;
	
	public AzioniMenu(JFrame frame, Grafica grafica) {
		this.frame = frame;
		this.grafica = grafica;
		fileChooser = new JFileChooser();
	}

	public void newFile() {
		try {
//			grafica.pulisci();
			frame.setTitle("Immagine - JPaint");
//			grafica.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void newFileAdv() {
		JLabel label1 = new JLabel("Larghezza:");
		JTextField larghezza = new JTextField(3);
		JLabel label2 = new JLabel("Altezza:");
		JTextField altezza = new JTextField(3);
		Object[] items = { label1, larghezza, label2, altezza };
		int chooser = JOptionPane.showOptionDialog(frame, items, "Inserisci le dimensioni", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (chooser == JOptionPane.OK_OPTION) {
			try {
				System.out.println("Larghezza field" + larghezza.getText());
				System.out.println("Altezza field" + altezza.getText());
//				grafica.aggiornaDimensioni(Integer.parseInt(larghezza.getText()), Integer.parseInt(altezza.getText()));
//				grafica.pulisci();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, ex, "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void openFile() {
		fileChoosed = fileChooser.showDialog(null, null);
		if (!(fileChoosed == JFileChooser.APPROVE_OPTION)) {}
		filePicked = new File(fileChooser.getSelectedFile().getAbsolutePath());
//		try {
//			buffer = ImageIO.read(filePicked);
			fileSaved = false;
			/*			if (buffer.getWidth() > 800 || buffer.getHeight() > 600) {
				String error = "Dimensioni immagine troppo grandi";
				JOptionPane.showMessageDialog(null, error, "Errore", 0);
			} else {
			 */				frame.setTitle(filePicked.getName() + " - JPaint");
//			 grafica.setLARGHEZZA(this.buffer.getWidth());
//			 grafica.setALTEZZA(this.buffer.getHeight());
//			 grafica.setImageBuffer(this.buffer);
//			 gBuffer = this.buffer.createGraphics();
//			 grafica.setGraphicsBuffer(this.gBuffer);
//			 grafica.repaint();
//			 grafica.revalidate();
			 //			}
//		} catch (IOException io) {
//			io.printStackTrace();
//		}
	}
	
	public void newSaveFile() {
/*		fileChoosed = fileChooser.showDialog(null, null);
		if (fileChoosed == JFileChooser.APPROVE_OPTION) {
			outputFile = fileChooser.getSelectedFile();
			String filePath = outputFile.getAbsolutePath();

			try {
				ImageIO.write(grafica.getImageBuffer(), "PNG", outputFile);
				frame.setTitle(outputFile.getName() + " - JPaint");
				System.out.println("Immagine salvata: " + filePath);
				fileSaved = true;
			} catch (IOException e) {
				System.out.println("Error saving image: " + e.getMessage());
			}
		} else {
			System.out.println("Nothing");
		}
*/	}
	
	public void saveFile() {
		if (fileSaved==false) {
			newSaveFile();
		}
//		try {
//			ImageIO.write(grafica.getImageBuffer(), "PNG", outputFile);
//		} catch (IOException e) {
//			System.out.println("Error saving image: " + e.getMessage());
//		}
	}
	
	public void exit() {
		frame.dispose();
	}
	
}
