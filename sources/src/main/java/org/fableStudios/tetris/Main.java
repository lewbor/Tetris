package org.fableStudios.tetris;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 7339686023606045192L;
    public static Main instance;
    public static TetrisPanel panel;

    //http://www.informit.com/articles/article.aspx?p=26349&seqNum=5
    public static AlphaComposite makeComposite(float alpha) {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    public Main(String title) {
        super(title);
    }

    public static void main(String[] args) {
        panel = new TetrisPanel();
        instance = new Main("Tetris");
        instance.setSize(new Dimension(500, 600));
        instance.setResizable(false);
        instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instance.setLocationRelativeTo(null);
        instance.add(panel);
        instance.setVisible(true);
    }
}
