package org.fableStudios.tetris.renderables;

import org.fableStudios.tetris.GameMode;
import org.fableStudios.tetris.Main;
import org.fableStudios.tetris.TetrisPanel;
import org.fableStudios.tetris.TetrisPanel.GameSettings;
import org.fableStudios.tetris.TetrisPanel.GlobalSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class Menu implements Renderable {
    private BufferedImage logo;
    private BufferedImage modesDesc;
    private Color transparentBlue;
    private Color _saved;
    private Selector[] selectors;
    private int index = 0;
    private String[][] descriptions = {
            {
                    "PLEASE SELECT THE GAME MODE YOU WISH TO PLAY ON."
            },
            {
                    "WITH ALTERNATE KEYS ON, THE DOWN ARROW ROTATES RIGHT",
                    "INSTEAD OF MOVING DOWN ONE LINE."
            },
            {
                    "WHEN THIS IS ENABLED, THE BACKGROUND WILL MOVE. THIS",
                    "MIGHT BE DISTRACTING FOR SOME PEOPLE."
            }
    };

    public enum Key {
        Left,
        Right,
        Up,
        Down
    }

    public enum State {
        Expired,
        Setup,
        Game,
        Highscore
    }

    private State state = State.Setup;
    private boolean isDirty = false;

    public void init() {
        try {
            logo = ImageIO.read(TetrisPanel.class.getClassLoader().
                    getResource("graphics/logo.png"));
            modesDesc = ImageIO.read(TetrisPanel.class.getClassLoader().
                    getResource("graphics/modesDesc.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String[] _values;
        selectors = new Selector[3];
        {
            GameMode[] values = GameMode.values();
            _values = new String[values.length];
            for (int i = 0; i < values.length; i++)
                _values[i] = values[i].name();
            selectors[0] = new Selector(_values, GameSettings.gameMode.ordinal(), TetrisPanel.getX(1), TetrisPanel.getY(5.5f));
            selectors[1] = new Selector(new String[]{"Off", "On"}, GameSettings.alternateKeys ? 1 : 0, TetrisPanel.getX(1), TetrisPanel.getY(9.5f));
            selectors[2] = new Selector(new String[]{"Off", "On"}, GameSettings.movingBackground ? 1 : 0, TetrisPanel.getX(1), TetrisPanel.getY(13));
        }
        selectors[0].setFocused(true);
        transparentBlue = new Color(26, 44, 92, 0xD0);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.lightGray);
        switch (getState()) {
            case Expired:
                g.drawString("THIS VERSION OF TETRIS HAS EXPIRED", TetrisPanel.getX(1), TetrisPanel.getY(1));
                g.drawString("PLEASE REQUEST A NEW VERSION OF THE CODE", TetrisPanel.getX(1), TetrisPanel.getY(2));
                break;
            case Setup:
                paintSetup(g);
                break;
            case Highscore:
                paintHighscore(g);
                break;
        }
    }

    @Override
    public void update() {
        switch (getState()) {
            case Setup:
                break;
            case Highscore:
                break;
        }
    }

    public boolean inGame() {
        return getState() == State.Game;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    private void paintSetup(Graphics g) {
        if (GlobalSettings.fontMetrics == null)
            GlobalSettings.fontMetrics = g.getFontMetrics();
        g.drawImage(logo, 0, TetrisPanel.getY(0), null);
        g.drawString("GAME MODE", TetrisPanel.getX(1), TetrisPanel.getY(5));
        selectors[0].render(g);
        g.drawString("ALTERNATE KEYS", TetrisPanel.getX(1), TetrisPanel.getY(8.5f));
        selectors[1].render(g);
        g.drawString("MOVING BACKGROUND", TetrisPanel.getX(1), TetrisPanel.getY(12));
        selectors[2].render(g);
        g.drawString("DESCRIPTION", TetrisPanel.getX(1), TetrisPanel.getY(15.5f));
        g.drawRect(TetrisPanel.getX(1), TetrisPanel.getY(16), 400, 100);
        for (int i = 0; i < descriptions[index].length; i++)
            g.drawString(descriptions[index][i], TetrisPanel.getX(1) + 5, TetrisPanel.getY(16.6f) + 16 * i);
        g.drawString("LEVEL MODES", TetrisPanel.getX(12.7f), TetrisPanel.getY(5));
        g.drawRect(TetrisPanel.getX(11), TetrisPanel.getY(5.5f), 160, 252);
        g.drawImage(modesDesc, TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 1, null);
        _saved = g.getColor();
        g.setColor(transparentBlue);
        switch (GameSettings.gameMode) {
            case Classic:
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 1, 158, 250);
                break;
            case Normal:
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 1, 158, 51);
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 106, 158, 32);
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 138, 158, 34);
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 172, 158, 79);
                break;
            case Expert:
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 106, 158, 32);
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 138, 158, 34);
                break;
            case Master:
                g.fillRect(TetrisPanel.getX(11) + 1, TetrisPanel.getY(5.5f) + 106, 158, 32);
                break;
            case GrandMaster:
                break;
        }
        g.setColor(_saved);
        String message = "PRESS SPACE TO START!";
        g.drawString(message, Main.panel.getWidth() / 2 - GlobalSettings.fontMetrics.stringWidth(message) / 2,
                Main.panel.getHeight() - 20);
    }

    private void paintHighscore(Graphics g) {
        g.drawString("WELL DONE!", TetrisPanel.getX(1), TetrisPanel.getY(1));
        g.drawString(String.format("SCORE: %16s", GlobalSettings.formatter.format(GlobalSettings.score.nextVal())),
                TetrisPanel.getX(1), TetrisPanel.getY(2));
        g.drawString(String.format("HIGHEST COMBO: %8s",
                GlobalSettings.combo.getHighest()), TetrisPanel.getX(1), TetrisPanel.getY(3));
        g.drawString(String.format("LEVEL: %16s", GlobalSettings.level), TetrisPanel.getX(1), TetrisPanel.getY(4));
        g.drawString(String.format("LINES CLEARED: %8s",
                GlobalSettings.formatter.format(GlobalSettings.numLinesDropped)), TetrisPanel.getX(1), TetrisPanel.getY(5));
        g.drawString(String.format("   SINGLE: %12s",
                GlobalSettings.numCombos[0]), TetrisPanel.getX(1), TetrisPanel.getY(6));
        g.drawString(String.format("   DOUBLE: %12s",
                GlobalSettings.numCombos[1]), TetrisPanel.getX(1), TetrisPanel.getY(7));
        g.drawString(String.format("   TRIPLE: %12s",
                GlobalSettings.numCombos[2]), TetrisPanel.getX(1), TetrisPanel.getY(8));
        g.drawString(String.format("   TETRIS: %12s",
                GlobalSettings.numCombos[3]), TetrisPanel.getX(1), TetrisPanel.getY(9));
        if (GlobalSettings.numCombos[4] > 0)
            g.drawString(String.format("   COMPLETE TETRIS: %3s",
                    GlobalSettings.numCombos[4]), TetrisPanel.getX(1), TetrisPanel.getY(10));
    }

    public void keyPressed(Key key) {
        if (key == Key.Up && index > 0) {
            selectors[index].setFocused(false);
            index--;
            selectors[index].setFocused(true);
        }
        if (key == Key.Down && index < selectors.length - 1) {
            selectors[index].setFocused(false);
            index++;
            selectors[index].setFocused(true);
        }
        if (key == Key.Left) {
            selectors[index].indexLeft();
            indexChanged();
        }
        if (key == Key.Right) {
            selectors[index].indexRight();
            indexChanged();
        }
    }

    private void indexChanged() {
        switch (index) {
            case 0:
                GameSettings.gameMode = GameMode.valueOf(selectors[0].getString());
                break;
            case 1:
                GameSettings.alternateKeys = selectors[1].index == 1 ? true : false;
                break;
            case 2:
                GameSettings.movingBackground = selectors[2].index == 1 ? true : false;
                break;
        }
    }
}
