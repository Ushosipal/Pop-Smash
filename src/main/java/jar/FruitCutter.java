package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FruitCutter extends JPanel implements MouseMotionListener {

    private ArrayList<Fruit> fruits = new ArrayList<>();
    private int score = 0;
    private Random rand = new Random();

    public FruitCutter() {
        setBackground(new Color(135, 206, 250)); // sky blue
        setPreferredSize(new Dimension(600, 600));
        addMouseMotionListener(this);

        // Game loop timer
        Timer timer = new Timer(30, e -> {
            spawnFruits();
            moveFruits();
            repaint();
        });
        timer.start();
    }

    private void spawnFruits() {
        if (rand.nextInt(100) < 3) { // 3% chance each frame
            int size = rand.nextInt(30) + 30;
            int x = rand.nextInt(getWidth() - size);
            int y = getHeight();
            Color color = getRandomColor();
            fruits.add(new Fruit(x, y, size, color));
        }
    }

    private void moveFruits() {
        for (Fruit f : fruits) {
            f.y -= 5; // move upward
        }
        fruits.removeIf(f -> f.y + f.size < 0); // remove off-screen
    }

    private Color getRandomColor() {
        Color[] colors = {Color.YELLOW, Color.ORANGE, Color.RED, Color.GREEN, Color.MAGENTA};
        return colors[rand.nextInt(colors.length)];
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw fruits
        for (Fruit f : fruits) {
            f.draw(g);
        }

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        sliceFruit(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Not used
    }

    private void sliceFruit(int mx, int my) {
        for (Fruit f : fruits) {
            if (!f.sliced && f.contains(mx, my)) {
                f.sliced = true;
                score++;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fruit Cutter Game");
        FruitCutter game = new FruitCutter();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
