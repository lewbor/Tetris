package org.fableStudios.tetris.renderables;

public final class GhostBorder {
    public boolean left = false;
    public boolean right = false;
    public boolean top = false;
    public boolean bottom = false;

    public boolean hasBorder() {
        return left || right || top || bottom;
    }

    public void setAll(boolean value) {
        left = right = top = bottom = value;
    }
}
