package edu.programming.hoover.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class Position implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position move(Direction direction) {
        switch (direction) {
            case DOWN:
                return new Position(x, y + 1);
            case LEFT:
                return new Position(x - 1, y);
            case RIGHT:
                return new Position(x + 1, y);
            case UP:
                return new Position(x, y - 1);
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && this.getClass() == obj.getClass() && equals((Position) obj);
    }

    public boolean equals(Position that) {
        return this == that || that != null && this.x == that.x && this.y == that.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public Position clone() {
        return this;
    }
}
