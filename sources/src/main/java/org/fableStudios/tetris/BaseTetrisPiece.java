package org.fableStudios.tetris;

public abstract class BaseTetrisPiece {
    protected TetrisShape pieceShape;
    protected int[][] coords;
    private int maxCoords;
    protected static HighQualityRandom generator;
    protected boolean isEvil;

    protected static final int coordsTable[][][] = {
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
    };

    public enum TetrisShape {
        NoShape,
        ZShape,
        SShape,
        LineShape,
        TShape,
        SquareShape,
        LShape,
        MirroredLShape,
        Bomb,
        __BOMBCLEAR
    }

    public BaseTetrisPiece(int maxCoords) {
        this.maxCoords = maxCoords;
        coords = new int[maxCoords][2];
        generator = new HighQualityRandom();
    }

    public TetrisShape shape() {
        return pieceShape;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][1];
    }

    public int minX() {
        int min = coords[0][0];
        for (int i = 1; i < maxCoords; ++i)
            min = Math.min(min, coords[i][0]);
        return min;
    }

    public int maxX() {
        int max = coords[0][0];
        for (int i = 1; i < maxCoords; ++i)
            max = Math.max(max, coords[i][0]);
        return max;
    }

    public int minY() {
        int min = coords[0][1];
        for (int i = 1; i < maxCoords; ++i)
            min = Math.min(min, coords[i][1]);
        return min;
    }

    public int maxY() {
        int max = coords[0][1];
        for (int i = 1; i < maxCoords; ++i)
            max = Math.max(max, coords[i][1]);
        return max;
    }

    public int maxCoords() {
        return maxCoords;
    }

    protected void setX(int index, int x) {
        coords[index][0] = x;
    }

    protected void setY(int index, int y) {
        coords[index][1] = y;
    }

    public abstract void setShape(TetrisShape shape);

    public abstract void setRandomShape();

    public abstract BaseTetrisPiece rotateLeft();

    public abstract BaseTetrisPiece rotateRight();
}
