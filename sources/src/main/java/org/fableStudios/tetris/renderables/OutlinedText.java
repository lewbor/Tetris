package org.fableStudios.tetris.renderables;

import org.fableStudios.tetris.TetrisPanel.GlobalSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OutlinedText extends BufferedImage {
    public OutlinedText(Color fontColour, Color outlineColour, String text) {
        super(GlobalSettings.fontMetrics.stringWidth(text) + 2, GlobalSettings.fontMetrics.getHeight() + 2, TYPE_INT_ARGB);
        int height = GlobalSettings.fontMetrics.getHeight();
        Graphics g = this.getGraphics();
        g.setColor(outlineColour);
        g.drawString(text, 0, 0 + height);
        g.drawString(text, 2, 0 + height);
        g.drawString(text, 0, 2 + height);
        g.drawString(text, 2, 2 + height);
        g.setColor(fontColour);
        g.drawString(text, 1, 1 + height);
        g.dispose();
    }
}
