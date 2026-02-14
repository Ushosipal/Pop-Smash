package game;

import java.awt.*;

public class Fruit {
    public int x, y, size;
    public Color color;
    public boolean sliced = false;

    public Fruit(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g) {
        if (!sliced) {
            g.setColor(color);
            g.fillOval(x, y, size, size);
        }
    }

    public boolean contains(int mx, int my) {
        int dx = mx - (x + size/2);
        int dy = my - (y + size/2);
        return dx*dx + dy*dy <= (size/2)*(size/2);
    }
}
