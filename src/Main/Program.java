package main;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Program {

	public static int width = 600;
	public static int height = 600;

	public static float heartSize =10;
	public static float precision = 2f;
	static Color color = Color.red;
	public static void main(String[] args) {
		lunch();
	}

	 static void lunch() {

		 	JFrame frame = new JFrame("Drawing Heart");
		    frame.setSize(width, height);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		    Graphics g = image.createGraphics();

		    System.out.println();
		    g.setColor(Color.BLACK);
		    g.fillRect(0, 0, width, height);

		    drawheart(image, frame);
		    g.dispose();
		    System.err.println("done");
	 }
	public static void drawheart(BufferedImage image, JFrame frame) {

		Graphics g = image.createGraphics();

	    for (double t = 0.0; t <= precision * Math.PI; t += 0.01) {

	        double x = 16 * Math.pow(Math.sin(t), 3);
	        double y = 13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t);

	        int scaledX = (int) (x * heartSize) + width / 2;
	        int scaledY = (int) (y * heartSize) + height / 2;
	        int[] cord = getFlippedCoordinates(image, scaledX, scaledY);

	        g.setColor(color);
	        image.setRGB(cord[0], cord[1],color.getRGB());

	        g.drawLine(cord[0], cord[1], width/2, height/2);


	        try {
	        	Thread.sleep(10);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }


	        JLabel label = new JLabel(new ImageIcon(image));
	        frame.getContentPane().removeAll();
	        frame.add(label);
	        frame.revalidate();
	        frame.repaint();


	    }


	    g.dispose();

	}




	static Color randomColor() {
		int r = (int)( Math.random() * 3);

		switch (r) {
		case 0 :
			return Color.RED;
		case 1 :
			return Color.ORANGE;
		default :
			return Color.PINK;
		}
	}

	 private static BufferedImage flip(BufferedImage image) {
		 BufferedImage imageFlipped = new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
		 for(int y = 0 ;y < image.getHeight();y++) {
	        	for(int x = 0 ;x < image.getWidth();x++) {
	        		imageFlipped.setRGB(x,(image.getHeight()-1)-y,image.getRGB(x,y));
	        	}
	        }
		 return imageFlipped;
	}
	 private static int[] getFlippedCoordinates(BufferedImage image, int x, int y) {
		    int flippedY = image.getHeight() - 1 - y;
		    return new int[]{x, flippedY};
		}
	static void displayImage(BufferedImage image,String title){
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(image));
		JFrame frame = new JFrame(title);
		frame.setSize(image.getWidth(),image.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(label);
		frame.setVisible(true);


	}
}
