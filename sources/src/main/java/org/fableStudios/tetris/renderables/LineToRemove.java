package org.fableStudios.tetris.renderables;

import org.fableStudios.tetris.Main;
import org.fableStudios.tetris.TetrisPanel;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class LineToRemove implements Renderable {
    private int y = 0;
    private float[] whiteTiles;
    private float[] foreTiles;
    private boolean[] finished;
    private int boardWidth;
    private Random generator;
    private int score;

    public enum State {
        InProgress,
        Finished
    }

    private State state;

    public LineToRemove(int y, int score) {
        this.y = y;
        this.state = State.InProgress;
        this.boardWidth = Main.panel.getBoardWidth();
        this.score = score;
        whiteTiles = new float[boardWidth];
        foreTiles = new float[boardWidth];
        finished = new boolean[boardWidth];
        Arrays.fill(whiteTiles, 0.5f);
        Arrays.fill(foreTiles, 1.0f);
        Arrays.fill(finished, false);
        generator = new Random();
    }

    public void update() {
        int arrayPos = generator.nextInt(boardWidth);
        int attempts = 0;
        while (finished[arrayPos] && attempts++ < boardWidth)
            arrayPos = generator.nextInt(boardWidth);
        if (foreTiles[arrayPos] > 0.0f)
            foreTiles[arrayPos] -= 0.5f;
        else if (whiteTiles[arrayPos] > 0.0f)
            whiteTiles[arrayPos] -= 0.5f;
        else {
            finished[arrayPos] = true;
            Main.panel.createScore(score, arrayPos, y);
        }
    }

    public void render(Graphics g) {
        if (state != State.Finished)
            for (int i = 0; i < Main.panel.getBoardWidth(); i++)
                TetrisPanel.drawSquare(g, i, y, Color.white, (int) (whiteTiles[i] * 255));
    }

    public boolean isFinished() {
        boolean isFinished = true;
        for (boolean cellFinished : finished)
            if (!cellFinished) {
                isFinished = false;
                break;
            }
        return isFinished;
    }

    public State getState() {
        return state;
    }

    /**
     * Used by the TetrisPanel's paint method to get the foreground opacity
     *
     * @param x int	The position of the square
     * @return int    Foreground alpha
     */
    public int getSquareOpacity(int x) {
        return (int) (foreTiles[x] * 255);
    }
}