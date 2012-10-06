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
public class CommandOverflowHooverException extends HooverException {
    public CommandOverflowHooverException(int size) {
        super("Hoover cannot perform more than " + size + " operations");
    }
}
