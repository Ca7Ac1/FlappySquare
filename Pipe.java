import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pipe {

    private final int xShift = 3;
    private final int PIPE_WIDTH;

    private final int PIPE_DISTANCE;
    private final int HEIGHT;

    private int xPos;
    private int yPos;

    public Pipe(int xPos, int yPos, int height, int pipeWidth) {
        this.xPos = xPos;
        this.yPos = yPos;
        PIPE_WIDTH = pipeWidth;
        PIPE_DISTANCE = height / 4;
        HEIGHT = height;
    }

    public void update() {
        xPos -= xShift;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GREEN);
        g2d.fillRect(xPos, -1, PIPE_WIDTH, yPos);
        g2d.fillRect(xPos, yPos + PIPE_DISTANCE, PIPE_WIDTH, HEIGHT + 10);
    }

    public Rectangle getUpperHitbox() {
        return new Rectangle(xPos, -1, PIPE_WIDTH, yPos);
    }

    public Rectangle getLowerHitbox() {
        return new Rectangle(xPos, yPos + PIPE_DISTANCE, PIPE_WIDTH, HEIGHT + 10);
    }

    public boolean isOut() {
        return xPos + PIPE_WIDTH < -1;
    }
}