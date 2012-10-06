package edu.programming.hoover.command;

import edu.programming.hoover.Command;
import edu.programming.hoover.State;
import edu.programming.hoover.exception.HooverException;
import edu.programming.hoover.model.Direction;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class MoveCommand implements Command {
    private final Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public State execute(State state) throws HooverException {
        return state.move(direction);
    }

    @Override
    public String asText() {
        return direction.name();
    }

    @Override
    public String toString() {
        return asText();
    }
}
