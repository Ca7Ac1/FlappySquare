import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PlayerSquare {

    private final int WIDTH;
    private final int HEIGHT;

    private final int Y_BUFFER = 3;
    private final int JUMP_HEIGHT = 8;

    private final int squareSize = 40;

    private int xPos;
    private int yPos;

    private int yChange;
    private int yChangeCounter;

    public PlayerSquare(int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        xPos = width / 10;
        yPos = height / 2;
        yChange = 0;
        yChangeCounter = 0;
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
        yChange = JUMP_HEIGHT;
    }

    public void update() {
        yPos -= yChange;

        if (yChangeCounter >= Y_BUFFER || yChange > JUMP_HEIGHT - 3) {
            yChange -= 1;
            yChangeCounter = 0;
        }

        yChangeCounter++;
    }

    public boolean checkDeath(Pipe pipe) {
        if (yPos >= HEIGHT - squareSize || yPos <= 0) {
            return true;
        }

        if (getHitbox().intersects(pipe.getUpperHitbox()) || getHitbox().intersects(pipe.getLowerHitbox())) {
            return true;
        }

        return false;
    }
}
