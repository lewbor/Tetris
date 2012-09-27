package org.fableStudios.tetris.renderables;

import org.fableStudios.tetris.Main;
import org.fableStudios.tetris.TetrisPanel.GlobalSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ScoreText implements Renderable {
    private static HashMap<Integer, BufferedImage> cache;

    static {
        cache = new HashMap<Integer, BufferedImage>();
    }

    private BufferedImage image;
    private int life = 60;    //1.2 seconds before it disappears
    private int x;
    private int basex;
    private int y;
    private int width;
    private int height;
    private float alpha = 1.0f;
    private Graphics2D g2d;

    public ScoreText(int score, int x, int y) {
        Integer key = new Integer(score);
        this.x = this.basex = x;
        this.y = y;
        if (cache.containsKey(key)) {
            this.image = cache.get(key);
            this.width = this.image.getWidth();
            this.height = this.image.getHeight();
            return;
        }
        String _score = GlobalSettings.formatter.format(score);
        this.width = GlobalSettings.fontMetrics.stringWidth(_score) + 2;
        this.height = GlobalSettings.fontMetrics.getHeight();
        BufferedImage im = new BufferedImage(this.width,
                this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = im.createGraphics();
        Color stroke = Color.black;
        Color inside = Color.lightGray;
        g.setFont(GlobalSettings.font);
        g.setColor(stroke);
        g.drawString(_score, 0, -2 + this.height);
        g.drawString(_score, 2, -2 + this.height);
        g.drawString(_score, 0, 0 + this.height);
        g.drawString(_score, 2, 0 + this.height);
        g.setColor(inside);
        g.drawString(_score, 1, -1 + this.height);
        g.dispose();
        this.image = im;
        cache.put(key, im);
    }

    public void update() {
        life--;
        y--;
        x = basex + (int) (6 * Math.sin(2 * alpha * Math.PI));
        alpha = (float) life / 60;
    }

    public boolean isAlive() {
        return life > 0;
    }

    public void render(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setComposite(Main.makeComposite(alpha));
        g2d.drawImage(this.image, x, y, width, height, null);
        g2d.setComposite(Main.makeComposite(1.0f));
    }
}
