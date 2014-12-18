import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class test {
	public static void main(String[] arg) throws IOException {

		Game x = new Game();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Grap m = new Grap();
		x.add(m);
		x.setVisible(true);
		x.setSize(350, 390);
		m.setBackground(new Color(250, 248, 239));
	}

}
