package edu.programming.hoover.web.bean;

import edu.programming.hoover.State;
import edu.programming.hoover.StateBuilder;
import edu.programming.hoover.exception.BagOverflowHooverException;
import edu.programming.hoover.exception.OutOfBoundsHooverException;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public class StateBean {
    private int width;
    private int height;
    private int x;
    private int y;
    private int capacity;
    private Integer[][] field;
    private int[] bag;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer[][] getField() {
        return field;
    }

    public void setField(Integer[][] field) {
        this.field = field;
    }

    public int[] getBag() {
        return bag;
    }

    public void setBag(int[] bag) {
        this.bag = bag;
    }

    public State toState() throws OutOfBoundsHooverException, BagOverflowHooverException {
        StateBuilder builder = new StateBuilder().setBounds(width, height).setHoover(x, y).setBag(capacity, bag);

        for (int i = 0, fieldLength = field.length; i < fieldLength; i++) {
            Integer[] row = field[i];

            builder.setFieldRow(i, row);
        }

        return builder.toState();
    }
}
