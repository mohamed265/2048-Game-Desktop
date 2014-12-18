import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grap extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(187, 173, 160));
		g.fillRect(27, 60, 280, 280);
	//	g.drawString(String.format()"Please wait...", 0, 99);
	}
}
