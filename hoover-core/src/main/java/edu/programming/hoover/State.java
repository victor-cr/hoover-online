package edu.programming.hoover;

import edu.programming.hoover.exception.BagOverflowHooverException;
import edu.programming.hoover.exception.CellOverflowHooverException;
import edu.programming.hoover.exception.EmptyBagHooverException;
import edu.programming.hoover.exception.EmptyCellHooverException;
import edu.programming.hoover.exception.OutOfBoundsHooverException;
import edu.programming.hoover.model.Content;
import edu.programming.hoover.model.Direction;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public interface State {
    State move(Direction direction) throws OutOfBoundsHooverException;

    State take() throws BagOverflowHooverException, EmptyCellHooverException;

    State put() throws CellOverflowHooverException, EmptyBagHooverException;

    boolean match() throws EmptyCellHooverException, EmptyBagHooverException;

    boolean canMove(Direction direction);

    boolean isBagContent(Content content);

    boolean isCellContent(Content content);
}
