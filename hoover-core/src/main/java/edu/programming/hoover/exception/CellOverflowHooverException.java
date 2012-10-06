package edu.programming.hoover.exception;

import edu.programming.hoover.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class CellOverflowHooverException extends HooverException {
    public CellOverflowHooverException(Position position) {
        super("Hoover cannot put anything in the cell " + position + " since it already contains object");
    }
}
