package helpers;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class LayerHolder extends JButton {

	private static final long serialVersionUID = 1L;

	public LayerHolder(String label) {
		super(label);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }

        // Draw a perfect circle
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        // Call the superclass method to paint the button label
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        // Draw a circular border
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    @Override
    public boolean contains(int x, int y) {
        // Check if the click is within the circular shape
        int radius = getWidth() / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        return super.contains(x, y) && Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }
}