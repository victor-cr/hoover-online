package edu.programming.hoover.exception;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class BagOverflowHooverException extends HooverException {
    public BagOverflowHooverException(int capacity) {
        super("Hoover cannot take more than " + capacity + " elements");
    }
}
