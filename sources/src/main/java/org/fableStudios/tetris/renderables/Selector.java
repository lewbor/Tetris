package org.fableStudios.tetris.renderables;

import java.awt.*;

public class Selector implements Renderable {
    private String[] _options;
    private boolean isFocused = false;
    private int x;
    private int y;
    public int index = 0;

    private static OutlinedText lt;
    private static OutlinedText ltE;
    private static OutlinedText gt;
    private static OutlinedText gtE;
    private OutlinedText[] options;
    private OutlinedText[] optionsF; //Focused text

    public Selector(String[] options, int x, int y) {
        this(options, 0, x, y);
    }

    public Selector(String[] options, int index, int x, int y) {
        this._options = options;
        this.index = index;
        this.x = x;
        this.y = y;
        if (lt == null)
            lt = new OutlinedText(Color.gray, Color.black, "<");
        if (ltE == null)
            ltE = new OutlinedText(Color.orange, Color.black, "<");
        if (gt == null)
            gt = new OutlinedText(Color.gray, Color.black, ">");
        if (gtE == null)
            gtE = new OutlinedText(Color.orange, Color.black, ">");
        this.options = new OutlinedText[options.length];
        this.optionsF = new OutlinedText[options.length];
        for (int i = 0; i < options.length; i++) {
            this.options[i] = new OutlinedText(Color.gray, Color.black, options[i]);
            this.optionsF[i] = new OutlinedText(Color.orange, Color.black, options[i]);
        }
    }

    public void indexLeft() {
        if (index > 0)
            index--;
    }

    public void indexRight() {
        if (index < _options.length - 1)
            index++;
    }

    public void setFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    @Override
    public void render(Graphics g) {
        if (index > 0 && isFocused)
            g.drawImage(ltE, x, y, null);
        else g.drawImage(lt, x, y, null);
        if (!isFocused)
            g.drawImage(options[index], x + 60 - options[index].getWidth() / 2, y, null);
        else g.drawImage(optionsF[index], x + 60 - optionsF[index].getWidth() / 2, y, null);
        if (index < _options.length - 1 && isFocused)
            g.drawImage(gtE, x + 100, y, null);
        else g.drawImage(gt, x + 100, y, null);
    }

    @Override
    public void update() {
    }

    public String getString() {
        return _options[index];
    }
}
