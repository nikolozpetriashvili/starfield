import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

class Starfield extends JPanel {

  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private static final int MAX_NUMS = 150;
  private static final double MAX_SPEED = 5;
  private Star[] stars;

  public Starfield() {
    stars = new Star[MAX_NUMS];
    Random random = new Random();

    for (int i = 0; i < MAX_NUMS; i++) {
      int x = random.nextInt(WIDTH);
      int y = random.nextInt(HEIGHT);
      double speed = random.nextDouble(MAX_SPEED);
      stars[i] = new Star(x, y, speed);
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.BLACK);
    for (Star star : stars) {
      star.update();
      star.draw(g);
    }
    repaint();
  }

  private static class Star {

    private int x;
    private int y;
    private double speed;

    public Star(int x, int y, double speed) {
      this.x = x;
      this.y = y;
      this.speed = speed;
    }

    public void update() {
      x += speed;
      if (x > WIDTH) {
        x = 0;
      }
    }

    public void draw(Graphics g) {
      g.setColor(Color.WHITE);
      g.fillRect(x, y, 2, 2);
    }

  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Starfield Simulation");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WIDTH, HEIGHT);
    frame.setResizable(false);

    Starfield starfield = new Starfield();
    frame.add(starfield);

    frame.setVisible(true);
  }
}
