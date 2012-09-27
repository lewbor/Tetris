package org.fableStudios.tetris;

public class Bomb extends BaseTetrisPiece {
    private static final long serialVersionUID = -5999209524976782113L;

    public Bomb() {
        super(6);
    }

    public enum State {
        Normal,
        BlowUp,
        Finished
    }

    @Override
    public BaseTetrisPiece rotateLeft() {
        return this;
    }

    @Override
    public BaseTetrisPiece rotateRight() {
        return this;
    }

    @Override
    public void setRandomShape() {
        pieceShape = TetrisShape.Bomb;
        coords = new int[][]
                {
                        {-1, -1},
                        {0, -1},
                        {-1, 0},
                        {0, 0},
                        {0, 1},
                        {0, 2}
                };
    }

    @Override
    public void setShape(TetrisShape shape) {
        pieceShape = TetrisShape.Bomb;
        coords = new int[][]
                {
                        {-1, -1},
                        {0, -1},
                        {-1, 0},
                        {0, 0},
                        {0, 1},
                        {0, 2}
                };
    }
}
