package interfaccia.dialoghi;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import interfaccia.Icone;

public class DialogoSpessore {

	private JDialog dialog;
	private DialogoSpessoreDraw drawPanel;

	public JComboBox<String> joinBox;
	public JComboBox<String> capBox;

	public DialogoSpessore() {
		String[] joinType = {"Miter", "Round", "Bevel"};
		String[] capType = {"Butt", "Round", "Square"};

		joinBox = new JComboBox<>(joinType);
		capBox = new JComboBox<>(capType);
	}

	public JDialog getDialog(JFrame frame) {

		dialog = new JDialog(frame, "Personalizza", true);
		drawPanel = new DialogoSpessoreDraw();

		JPanel toolPane = new JPanel();
		JPanel previewPanel = new JPanel();

		TitledBorder toolBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Personalizza", TitledBorder.LEFT, TitledBorder.TOP);
		TitledBorder previewBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Anteprima", TitledBorder.LEFT, TitledBorder.TOP);

		toolPane.setLayout(new BoxLayout(toolPane, BoxLayout.PAGE_AXIS));
		toolPane.add(joinBox);
		toolPane.add(capBox);

		toolPane.setBorder(toolBorder);
		previewPanel.setBorder(previewBorder);

		dialog.setLayout(new BorderLayout());

		dialog.add(toolPane, BorderLayout.WEST);
		previewPanel.add(drawPanel);
		dialog.add(previewPanel, BorderLayout.CENTER);

		dialog.pack();
		dialog.setResizable(true);
		dialog.setLocationRelativeTo(frame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.gc();}});
		dialog.setIconImage(Icone.VUOTO);
		dialog.setVisible(true);

		return dialog;
	}

	public void close() {
		dialog.dispose();
		System.gc();
	}

	public void setStroke(int size, int join, int cap) {
		drawPanel.setStroke(size, join, cap);
	}
}

class DialogoSpessoreDraw extends JPanel {

	private static final long serialVersionUID = 1L;

	private BasicStroke stroke;

//	private int xCentro;
//	private int yCentro;
//	private int raggio;

	protected DialogoSpessoreDraw() {
		this.setPreferredSize(new Dimension(100,100));

		stroke = new BasicStroke(10, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 3);

//		xCentro = 100 / 2;
//		yCentro = 100 / 2;
//		raggio = 25;
	}

	protected void setStroke(int size, int join, int cap) {
		stroke = new BasicStroke(size,join,cap);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(stroke);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 100, 100);
		g2d.setColor(Color.BLACK);
		g2d.drawLine(10,10,60,60);
//		g2d.setColor(Color.BLACK);
//		g2d.fillOval(xCentro - raggio, yCentro - raggio, 2 * raggio, 2 * raggio);
		g2d.dispose();
	}

}
