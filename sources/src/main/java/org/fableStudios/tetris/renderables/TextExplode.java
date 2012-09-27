package org.fableStudios.tetris.renderables;

import org.fableStudios.tetris.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextExplode implements Renderable {
    private BufferedImage text;
    private State state;
    private int width = 0;
    private int height = 0;
    private int wStep = 20;
    private int hStep = 15;
    private float aStep = 0.04f;
    private int life = 60;
    private int panelWidth;
    private int panelHeight;

    private enum State {
        Normal,
        ExpandIntoView,
        Hold,
        FadeOut
    }

    private float alpha = 0.0f;
    private Graphics2D g2d;

    public TextExplode(BufferedImage image, int panelWidth, int panelHeight) {
        this.text = image;
        this.state = State.Normal;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public void start() {
        this.state = State.ExpandIntoView;
        this.alpha = 0.0f;
        this.width = 0;
        this.height = 0;
        this.life = 60;
    }

    @Override
    public void render(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setComposite(Main.makeComposite(alpha));
        int dx = panelWidth / 2 - width / 2;
        int dy = panelHeight / 2 - height / 2;
        g2d.drawImage(text, dx, dy, dx + width, dy + height, 0, 0, 400, 300, null);
    }

    @Override
    public void update() {
        switch (state) {
            case ExpandIntoView:
                if (width < 400) {
                    width += wStep;
                    height += hStep;
                    alpha += aStep;
                } else state = State.Hold;
                break;
            case Hold:
                if (life > 0)
                    life--;
                else state = State.FadeOut;
                break;
            case FadeOut:
                if (alpha > 0)
                    alpha -= aStep;
                else state = State.Normal;
                break;
        }
    }

    public boolean isFinished() {
        return state == State.Normal;
    }

    public boolean isDirty() {
        return state == State.ExpandIntoView || state == State.FadeOut;
    }

    public void clear() {
        this.state = State.Normal;
    }
}
