package edu.programming.hoover.exception;

import edu.programming.hoover.model.Bounds;
import edu.programming.hoover.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class OutOfBoundsHooverException extends HooverException {
    public OutOfBoundsHooverException(Position hoover, Bounds bounds) {
        super("New hoover position " + hoover + " is out of current field bounds " + bounds);
    }
}
