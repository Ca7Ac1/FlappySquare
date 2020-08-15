import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PlayerSquare {

    private final int WIDTH;
    private final int HEIGHT;

    private final int squareSize = 40;

    private final int xPos;
    private int yPos;

    private int yChange;

    public PlayerSquare(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        
        xPos = width / 8;
        yPos = height / 2;
        yChange = 0;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(xPos, yPos, squareSize, squareSize);
    }

    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, squareSize, squareSize);
    }

    public void jump() {
        yChange = 10;
    }

    public void update() {
        yPos -= yChange;
        yChange -= 1;
    }

    public boolean checkDeath() {
        if (yPos >= HEIGHT - squareSize) {
            return true;
        }

        if (yPos <= 0) {
            return true;
        }

        return false;
    }
}


