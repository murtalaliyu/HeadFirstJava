import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyDrawPanel extends JPanel {

  public void paintComponent(Graphics g) {
    // 1. rectangle
    // int rand = (int)(Math.random()*2);  
    // g.setColor(rand == 0 ? Color.blue : Color.red);
    // g.fill3DRect(3, 4, 100, 100, true);
    // -------------------------------------------------------

    // // 2. jpeg
    // Image image = new ImageIcon(getClass().getResource("catzilla.jpg")).getImage();
    // g.drawImage(image, 103, 104, this);
    // -------------------------------------------------------

    // 3. randomly colored circle on a black background
    // g.fillRect(0, 0, this.getWidth(), this.getHeight());
    // -------------------------------------------------------
    
    // 4. fill oval with random color
    Random random = new Random();
    // int red = random.nextInt(256);
    // int green = random.nextInt(256);
    // int blue = random.nextInt(256);

    // Color randomColor = new Color(red, green, blue);
    // g.setColor(randomColor);
    // g.fillOval(red, green, 100, 100);
    // -------------------------------------------------------

    // 5. use random gradient
    Graphics2D g2d = (Graphics2D) g;

    int red = random.nextInt(256);
    int green = random.nextInt(256);
    int blue = random.nextInt(256);
    Color startColor = new Color(red, green, blue);

    red = random.nextInt(256);
    green = random.nextInt(256);
    blue = random.nextInt(256);
    Color endColor = new Color(red, green, blue);

    GradientPaint gradient = new GradientPaint(red, green, startColor, blue, red, endColor);
    g2d.setPaint(gradient);
    g2d.fillOval(green, blue, red, green);
    // -------------------------------------------------------
  }
}
