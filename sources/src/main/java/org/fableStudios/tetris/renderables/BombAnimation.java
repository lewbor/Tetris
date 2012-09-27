package org.fableStudios.tetris.renderables;

import org.fableStudios.tetris.Main;
import org.fableStudios.tetris.TetrisPanel;

import java.awt.*;

/**
 * Displays a white tile above the current tile.
 * White tile fades in, current tile goes invisible,
 * white tile fades out.
 *
 * @author David Priddle
 */
public class BombAnimation implements Renderable {
    private int pause = 0;
    private int x;
    private int y;
    private int score;
    private float whiteTile = 0.0f;
    private boolean sequenceEnded = false;
    private boolean displayForeTile = true;

    public BombAnimation(int pause, int x, int y, int score) {
        this.pause = pause;
        this.x = x;
        this.y = y;
        this.score = score;
    }

    @Override
    public void render(Graphics g) {
        TetrisPanel.drawSquare(g, x, y, Color.red, (int) (whiteTile * 255));
    }

    @Override
    public void update() {
        if (pause > 0) {
            pause--;
            return;
        }
        if (displayForeTile) {
            if (whiteTile < 1.0f)
                whiteTile += 0.25f;
            else displayForeTile = false;
        } else {
            if (whiteTile > 0.0f)
                whiteTile -= 0.25f;
            else {
                sequenceEnded = true;
                Main.panel.createScore(score, x, y);
            }
        }
    }

    public boolean DisplayForeTile() {
        return displayForeTile;
    }

    public boolean isAlive() {
        return !sequenceEnded;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
