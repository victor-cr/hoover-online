package edu.programming.hoover.exception;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class EmptyBagHooverException extends HooverException {
    public EmptyBagHooverException() {
        super("Hoover cannot put anything since its bag is empty");
    }
}
