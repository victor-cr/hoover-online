package edu.programming.hoover.exception;

import edu.programming.hoover.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class EmptyCellHooverException extends HooverException {
    public EmptyCellHooverException(Position hoover) {
        super("Hoover cannot take anything from cell " + hoover + " since it is empty");
    }
}
