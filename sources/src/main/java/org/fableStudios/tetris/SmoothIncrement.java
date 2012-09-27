package org.fableStudios.tetris;

public class SmoothIncrement {
    private int divideByTwo = 0;
    private int currBase = 0;
    private int offset = 0;
    private int nextBase = 0;
    private double deltaA = 0;
    private int time = 0;
    private boolean isDirty = false;
    private int highest = 0;

    public synchronized void update() {
        divideByTwo = divideByTwo++ % 2;
        if (divideByTwo != 0 || currBase == nextBase) return;
        time++;
        offset = (int) Math.ceil(deltaA * (time / (time + 1.2)));
        if (currBase + offset > nextBase) {
            currBase = nextBase;
            checkIsHighest();
            offset = 0;
        }
        isDirty = true;
    }

    private synchronized void checkIsHighest() {
        if (currBase > highest)
            highest = currBase;
    }

    public synchronized boolean needsRepaint() {
        if (isDirty) {
            isDirty = false;
            return true;
        }
        return false;
    }

    public synchronized void add(int toAdd) {
        nextBase += toAdd;
        currBase += offset;
        checkIsHighest();
        deltaA = (nextBase - currBase) * 1.05;
        time = 0;
    }

    public synchronized void set(int setTo) {
        nextBase = setTo;
        currBase += offset;
        checkIsHighest();
        deltaA = (nextBase - currBase) * 1.05;
        time = 0;
    }

    public synchronized void instantSet(int setTo) {
        currBase = nextBase = setTo;
        checkIsHighest();
    }

    public synchronized int intVal() {
        return currBase + offset;
    }

    public synchronized int nextVal() {
        return nextBase;
    }

    public synchronized int getHighest() {
        return highest;
    }

    public synchronized void clear() {
        currBase = 0;
        offset = 0;
        nextBase = 0;
        time = 0;
    }
}
