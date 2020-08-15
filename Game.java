import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Game extends JPanel implements ActionListener {

    private final int WIDTH = 1400;
    private final int HEIGHT = 600;

    private final int DELAY = 30;

    private PlayerSquare player;

    private Timer timer;
    private boolean held;

    public Game() {
        player = new PlayerSquare(WIDTH, HEIGHT);
        timer = new Timer(DELAY, this);
        held = false;

        timer.start();

        bind();

        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void bind() {
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "jump");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "jump");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "unhold");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "unhold");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "unhold");

        getActionMap().put("jump", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!held) {
                    player.jump();
                    held = true;
                }
            }
        });

        getActionMap().put("unhold", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                held = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        if (!player.checkDeath()) {
            player.update();
        }
    }
}