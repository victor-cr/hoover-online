package edu.programming.hoover.command;

import edu.programming.hoover.Command;
import edu.programming.hoover.State;
import edu.programming.hoover.exception.HooverException;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class SayCommand implements Command {
    private final String message;

    public SayCommand(String message) {
        this.message = message;
    }

    @Override
    public String asText() {
        return '+' + message;
    }

    @Override
    public State execute(State state) throws HooverException {
        return state;
    }

    @Override
    public String toString() {
        return asText();
    }
}
