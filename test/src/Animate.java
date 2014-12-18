 import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
 
 /*  public class Animate extends JPanel {
	   int message;
	   Animate(int m)
	   {
		   message = m;
	   }
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		 
			String line = "+"+ String.valueOf(message);
			g.drawString(line, 0, 99);
		//	g.draws
	//		g.translate(0, 0);
		}
	}
   public class Animate extends JFrame implements Runnable
   {

      int x_pos = 10;
      int y_pos = 100;
      int radius = 20;

      public Animate()
      {
         //Set to exit on close
         this.addWindowListener(new WindowAdapter()
         {
            @Override
            public void windowClosing(WindowEvent we)
            {
               System.exit(0);
            }
         });
         this.setSize(300, 250);
      }
      public void start()
      {
         Thread th = new Thread(this);
         th.start();
      }
      
      public static void main(String args[])
      {
    	  Animate p = new Animate();
         p.setVisible(true);
         p.start();
      }

      @Override
      public void run()
      {

         //infini loop
         while (true)
         {
            x_pos++;
            repaint();

            try
            {
               //Sleep thread for 20 milliseconds
               Thread.sleep(20);
            }
            catch (InterruptedException ex)
            {
               //do nothing
            }
         }
      }

      @Override
      public void paint(Graphics g)
      {
         g.setColor(Color.RED);
         g.fillOval(x_pos - radius, y_pos - radius, 2 * radius, 2 * radius);
      }
   }
*/