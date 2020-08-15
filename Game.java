import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import java.util.LinkedList;
import java.util.List;

import java.util.Random;

public class Game extends JPanel implements ActionListener {

    private final int WIDTH = 1400;
    private final int HEIGHT = 600;

    private final int FONT_SIZE = 20;

    private final int PIPE_WIDTH = 75;

    private final int DELAY = 10;

    private PlayerSquare player;

    private List<Pipe> pipes = new LinkedList<Pipe>();

    private Timer timer;
    private boolean held;
    private Random random;
    private int score;

    public Game() {
        player = new PlayerSquare(WIDTH, HEIGHT);
        timer = new Timer(DELAY, this);
        held = false;
        random = new Random();
        score = 0;

        timer.start();

        initPipes();
        bind();

        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initPipes() {
        pipes.add(new Pipe(WIDTH / 2, random.nextInt((HEIGHT * 3 / 4)), HEIGHT, PIPE_WIDTH));
        pipes.add(new Pipe(WIDTH  * 3 / 4, random.nextInt((HEIGHT * 3 / 4)), HEIGHT, PIPE_WIDTH));
        pipes.add(new Pipe(WIDTH, random.nextInt((HEIGHT * 3 / 4)), HEIGHT, PIPE_WIDTH));
        pipes.add(new Pipe(WIDTH * 5 / 4, random.nextInt((HEIGHT * 3 / 4)), HEIGHT, PIPE_WIDTH));
        pipes.add(new Pipe(WIDTH * 3 / 2, random.nextInt((HEIGHT * 3 / 4)), HEIGHT, PIPE_WIDTH));
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

    private void updatePipes() {
        if (pipes.get(0).isOut()) {
            pipes.remove(0);
            pipes.add(new Pipe((WIDTH * 5 / 4) - PIPE_WIDTH, random.nextInt((HEIGHT * 3 / 4)), HEIGHT, PIPE_WIDTH));
            score++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        showScore(g);
    }

    private void showScore(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("TimesRoman", Font.PLAIN, FONT_SIZE);

        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString(Integer.toString(score), WIDTH / 2, HEIGHT / 5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        updatePipes();

        if (!player.checkDeath(pipes.get(0))) {
            player.update();
        }

        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }
}