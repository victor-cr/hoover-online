package edu.programming.hoover;

import edu.programming.hoover.exception.HooverException;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public interface Command {
    String asText();

    State execute(State state) throws HooverException;
}
