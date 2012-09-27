package org.fableStudios.tetris;

import org.fableStudios.tetris.TetrisPanel.GlobalSettings;

public class TetrisPiece extends BaseTetrisPiece {
    public TetrisPiece() {
        super(4);
        setShape(TetrisShape.NoShape);
    }

    public void setRandomShape() {
        if (GlobalSettings.inDevelopment) {
            setShape(TetrisShape.LineShape);
            return;
        } else if (GlobalSettings.levelModes.contains(LevelMode.EvilMode)) {
            isEvil = generator.nextBoolean();
            if (isEvil) {
                setShape(TetrisShape.values()[generator.nextInt(1) + 1]);
                return;
            }
        }
        setShape(TetrisShape.values()[generator.nextInt(7) + 1]);
        generator.nextLong();
    }

    public void setShape(TetrisShape shape) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 2; j++)
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
        pieceShape = shape;
    }

    public TetrisPiece rotateLeft() {
        if (pieceShape == TetrisShape.SquareShape)
            return this;
        TetrisPiece result = new TetrisPiece();
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; i++) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }

    public TetrisPiece rotateRight() {
        if (pieceShape == TetrisShape.SquareShape)
            return this;
        TetrisPiece result = new TetrisPiece();
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; i++) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
}
