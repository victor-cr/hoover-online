package edu.programming.hoover.exception;

import edu.programming.hoover.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class ParseHooverException extends HooverException {
    public ParseHooverException() {
        super("Hoover cannot understand the instructions you have sent");
    }
}
