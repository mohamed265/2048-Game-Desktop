import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
	JButton[][] grid = new JButton[4][4];
	int[][] num = new int[4][4];
	JButton TryAgaine = new JButton("Try again");
	JLabel score = new JLabel("Score"), Best = new JLabel("Best"),
			MyScore = new JLabel("00000"), BestScore = new JLabel("00000") , Animation = new JLabel("End");
	Random rand = new Random();
	int SCORE, BEST = 0;
	final String FilePath = "2048.dat";

	Icon Default = new ImageIcon(getClass().getResource("Default.png"));
	Icon PIC2 = new ImageIcon(getClass().getResource("2.png"));
	Icon PIC4 = new ImageIcon(getClass().getResource("4.png"));
	Icon PIC8 = new ImageIcon(getClass().getResource("8.png"));
	Icon PIC16 = new ImageIcon(getClass().getResource("16.png"));
	Icon PIC32 = new ImageIcon(getClass().getResource("32.png"));
	Icon PIC64 = new ImageIcon(getClass().getResource("64.png"));
	Icon PIC128 = new ImageIcon(getClass().getResource("128.png"));
	Icon PIC256 = new ImageIcon(getClass().getResource("256.png"));
	Icon PIC512 = new ImageIcon(getClass().getResource("512.png"));
	Icon PIC1024 = new ImageIcon(getClass().getResource("1024.png"));
	Icon PIC2048 = new ImageIcon(getClass().getResource("2048.png"));

	ImageIcon icon = new ImageIcon(getClass().getResource("2048.png"));
	Game() {

		super("2048");
		this.setFocusable(true);
		setBackground(new Color(240, 171, 28));
		addKeyListener(new key());
		TryAgaine.addKeyListener(new key());

		score.setBounds(45, 10, 50, 20);
		score.setFont(new Font("SansSerif", Font.BOLD, 17));
		TryAgaine.setBounds(118, 10, 90, 20);
		Best.setBounds(250, 10, 50, 20);
		Best.setFont(new Font("SansSerif", Font.BOLD, 17));
		MyScore.setBounds(50, 40, 50, 20);
		Animation.setBounds(15 + 27 +7, 60 +7 +65, 65*4 , 120);
		Animation.setFont(new Font("SansSerif",Font.BOLD + Font .ITALIC ,125));
		Animation.setForeground(Color. WHITE);
		BestScore.setBounds(253, 40, 50, 20);
		Animation.setVisible(false);
		
		add(MyScore);
		add(BestScore);
		add(Best);
		add(TryAgaine);
		add(score);
		add(Animation);
		try {
			fileScore();
		} catch (Exception e) {
		}

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				grid[i][j] = new JButton(Default);
				grid[i][j]
						.setBounds(27 + 15 + i * 65, 60 + 15 + j * 65, 55, 55);
				grid[i][j].addKeyListener(new key());
				add(grid[i][j]);
			}
		//  End();
	/*	num[0][0] = 2;
		num[0][1] = 16;
		num[0][2] = 64;
		num[0][3] = 32;

		num[1][0] = 4;
		num[1][1] = 1024;
		num[1][2] = 16;
		num[1][3] = 4;

		num[2][0] = 2;
		num[2][1] = 2048;
		num[2][2] = 64;
		num[2][3] = 2;

		num[3][0] = 4;
		// num[3][1]= 1024;
		// num[3][2]= 2;
		// num[3][3]= 4;
		*/
		setIconImage(icon.getImage());
	//	Draw();
		AddNumber();
		AddNumber();
		
		TryAgaine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SCORE = 0;
				MyScore.setText(String.format("%05d", 0));
				Animation.setVisible(false);
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++) {
						grid[i][j].setIcon(Default);
						grid[i][j].setEnabled(true);
						num[i][j] = 0;
				}
				AddNumber();
				AddNumber();
				
			}
		});
	}

	void End() {
		if (BEST == SCORE) {
			Formatter fileScore = null;
			try {
				fileScore = new Formatter(FilePath);
				fileScore.format("%d", BEST);
			} catch (Exception e) {
			}
			fileScore.close();
		}
		Animation.setVisible(true);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				grid[i][j].setEnabled(false);
	}

	void fileScore() throws FileNotFoundException {
		File file = new File(FilePath);
		if (file.exists()) {
			Scanner scan = new Scanner(new File(FilePath));
			BEST = scan.nextInt();
			BestScore.setText(String.format("%05d", BEST));
		} else {
			final Formatter fileScore;
			try {
				fileScore = new Formatter(FilePath);
				fileScore.format("%d", 0);
				fileScore.close();
			} catch (Exception e) {

			}
		}

	}

	void Draw() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (num[i][j] == 0)
					grid[i][j].setIcon(Default);
				else if (num[i][j] == 2)
					grid[i][j].setIcon(PIC2);
				else if (num[i][j] == 4)
					grid[i][j].setIcon(PIC4);
				else if (num[i][j] == 8)
					grid[i][j].setIcon(PIC8);
				else if (num[i][j] == 16)
					grid[i][j].setIcon(PIC16);
				else if (num[i][j] == 32)
					grid[i][j].setIcon(PIC32);
				else if (num[i][j] == 64)
					grid[i][j].setIcon(PIC64);
				else if (num[i][j] == 128)
					grid[i][j].setIcon(PIC128);
				else if (num[i][j] == 256)
					grid[i][j].setIcon(PIC256);
				else if (num[i][j] == 512)
					grid[i][j].setIcon(PIC512);
				else if (num[i][j] == 1024)
					grid[i][j].setIcon(PIC1024);
				else if (num[i][j] == 2048)
					grid[i][j].setIcon(PIC2048);
				// System.out.print(num[i][j] + " ");
			}
			// System.out.println();
		}
		// System.out.println();
		// System.out.println();

	}

	boolean Cheak(int OriginalNum[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (OriginalNum[i][j] != num[i][j])
					return true;
			}
		}
		return false;

	}

	void CheckEnd() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (num[i][j] == 0)
					return;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (num[i][j] - num[i + 1][j] == 0)
					return;
				else if (num[i][j] - num[i][j + 1] == 0)
					return;
			}
		}
		for (int i = 0; i < 3; i++)
			if (num[3][i] - num[3][i + 1] == 0)
				return;
		for (int i = 0; i < 3; i++)
			if (num[i][3] - num[i + 1][3] == 0)
				return;
			End();

	}
	
	/*public class Ani extends Thread {

		int Score;
		Ani(int s)
		{
			Score = s;
		
		}
	    public void run() {
	    	Animation.setText("+" +String.valueOf(Score) );
			for (int i = 40 ; i > 0 ; i-=5 )
				{
					Animation.move(125,i);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		//	Animation.setText("");
			Animation.move(125,40);
	    }
	}*/
	
	void AddScore(int Score) {
		if (Score != 0) {
			SCORE += Score;
	//	new Ani(Score).run();
			
			
			MyScore.setText(String.format("%05d", SCORE));
			if (SCORE >= BEST) {
				BestScore.setText(String.format("%05d", SCORE));
				BEST = SCORE;
			}
		}
	}

	void AddNumber() {
		int ch = 0;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (num[i][j] == 0)
					ch++;

		if (ch != 0) {

			int targetI, targetJ;

			do {
				targetI = rand.nextInt(4);
				targetJ = rand.nextInt(4);
			} while (num[targetI][targetJ] != 0);

			int percent = rand.nextInt(100);

			if (percent > 90) {
				grid[targetI][targetJ].setIcon(PIC4);
				num[targetI][targetJ] = 4;
			} else {
				grid[targetI][targetJ].setIcon(PIC2);
				num[targetI][targetJ] = 2;
			}

		}
		CheckEnd();
	}

	private class key implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {

			ArrayList<Integer> ga = new ArrayList<Integer>();

			int[][] originalNum = new int[4][4];

			int Score = 0;

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					originalNum[i][j] = num[i][j];
				}
			}

			if (event.getKeyCode() == KeyEvent.VK_UP) {

				for (int i = 0; i < 4; i++) {
					ga.removeAll(ga);

					for (int j = 0; j < 4; j++)
						if (num[i][j] != 0) {
							ga.add(num[i][j]);
							num[i][j] = 0;
						}

					for (int j = 0; j < ga.size() - 1; j++) {
						if (ga.get(j) - ga.get(j + 1) == 0) {
							Score += (ga.get(j) + ga.get(j));
							ga.set(j, ga.get(j + 1) * 2);
							ga.remove(j + 1);
						}

					}

					for (int j = 0; j < ga.size(); j++)
						num[i][j] = ga.get(j);

				}

			} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {

				for (int i = 3; i >= 0; i--) {
					ga.removeAll(ga);
					for (int j = 3; j >= 0; j--)
						if (num[i][j] != 0) {
							ga.add(num[i][j]);
							num[i][j] = 0;
						}

					for (int j = 0; j < ga.size() - 1; j++)
						if (ga.get(j) - ga.get(j + 1) == 0) {
							Score += (ga.get(j) + ga.get(j));
							ga.set(j, ga.get(j + 1) * 2);
							ga.remove(j + 1);
						}

					for (int j = 0, g = 3; j < ga.size(); j++, g--)
						num[i][g] = ga.get(j);
				}

			} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				for (int i = 0; i < 4; i++) {
					ga.removeAll(ga);
					for (int j = 0; j < 4; j++)
						if (num[j][i] != 0) {
							ga.add(num[j][i]);
							num[j][i] = 0;
						}

					for (int j = 0; j < ga.size() - 1; j++)
						if (ga.get(j) - ga.get(j + 1) == 0) {
							Score += (ga.get(j) + ga.get(j));
							ga.set(j, ga.get(j + 1) * 2);
							ga.remove(j + 1);
						}
					for (int j = 0; j < ga.size(); j++)
						num[j][i] = ga.get(j);
				}

			} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {

				for (int i = 3; i >= 0; i--) {
					ga.removeAll(ga);
					for (int j = 3; j >= 0; j--)
						if (num[j][i] != 0) {
							ga.add(num[j][i]);
							num[j][i] = 0;
						}

					for (int j = 0; j < ga.size() - 1; j++)
						if (ga.get(j) - ga.get(j + 1) == 0) {
							Score += (ga.get(j) + ga.get(j));
							ga.set(j, ga.get(j + 1) * 2);
							ga.remove(j + 1);
						}
					for (int j = 0, g = 3; j < ga.size(); j++, g--)
						num[g][i] = ga.get(j);
				}

			}
			if (Cheak(originalNum)) {
				AddScore(Score);
				AddNumber();
				Draw();

			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			// System.out.print("allh");
		}
	}
}
