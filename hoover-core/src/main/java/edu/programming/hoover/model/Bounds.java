package edu.programming.hoover.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class Bounds implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private final int width;
    private final int height;

    public Bounds(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean contains(Position position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    @Override
    public int hashCode() {
        return width * 31 + height;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && this.getClass() == obj.getClass() && equals((Bounds) obj);
    }

    public boolean equals(Bounds that) {
        return this == that || that != null && this.width == that.width && this.height == that.height;
    }

    @Override
    public String toString() {
        return "(" + width + "," + height + ")";
    }

    @Override
    public Bounds clone() {
        return this;
    }
}
