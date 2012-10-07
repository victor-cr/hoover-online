package edu.programming.hoover.lang;

import edu.programming.hoover.Command;
import edu.programming.hoover.State;
import edu.programming.hoover.StateBuilder;
import edu.programming.hoover.exception.BagOverflowHooverException;
import edu.programming.hoover.exception.OutOfBoundsHooverException;
import edu.programming.hoover.exception.ParseHooverException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 20:25
 * To change this template use File | Settings | File Templates.
 */
public class CommandGeneratorTest extends Assert {
    @Test
    public void testGeneratePlainCommandSequence() throws OutOfBoundsHooverException, BagOverflowHooverException, ParseHooverException {
        State state = new StateBuilder().setHoover(0, 0).setBounds(10, 5).setCapacity(10).toState();

        CommandGenerator generator = new CommandGenerator(20);

        Collection<Command> commands = generator.generate(state, "move right move right move right move right");

        assertEquals(4, commands.size());
    }

    @Test
    public void testGeneratePlainLoop() throws OutOfBoundsHooverException, BagOverflowHooverException, ParseHooverException {
        State state = new StateBuilder().setHoover(0, 0).setBounds(10, 5).setCapacity(10).toState();

        CommandGenerator generator = new CommandGenerator(20);

        Collection<Command> commands = generator.generate(state, "while can right loop move right end loop");

        assertEquals(9, commands.size());
    }

    @Test
    public void testGenerateOutOfBound() throws OutOfBoundsHooverException, BagOverflowHooverException, ParseHooverException {
        State state = new StateBuilder().setHoover(0, 0).setBounds(10, 5).setCapacity(10).toState();

        CommandGenerator generator = new CommandGenerator(20);

        Collection<Command> commands = generator.generate(state, "while can down loop move right end loop");

        assertEquals(10, commands.size());
    }

    @Test
    public void testGenerateInfinityLoop() throws OutOfBoundsHooverException, BagOverflowHooverException, ParseHooverException {
        State state = new StateBuilder().setHoover(0, 0).setBounds(10, 5).setCapacity(10).toState();

        CommandGenerator generator = new CommandGenerator(20);

        Collection<Command> commands = generator.generate(state, "while can left or can right loop if can left then move left else move right end if end loop");

        assertEquals(21, commands.size());
    }

    @Test
    public void testGenerateTakePut() throws OutOfBoundsHooverException, BagOverflowHooverException, ParseHooverException {
        State state = new StateBuilder().setHoover(0, 0).setBounds(10, 5).setCapacity(10).setFieldRow(1, null, null, 1, 3, 2).toState();

        CommandGenerator generator = new CommandGenerator(100);

        Collection<Command> commands = generator.generate(state,
                "while can right or can down loop " +
                        "while can down loop " +
                        "  if cell full and not bag full then " +
                        "    take " +
                        "  end if" +
                        "  move down " +
                        "end loop " +
                        "if can right then " +
                        "while can up loop move up end loop " +
                        "move right " +
                        "end if " +
                        "end loop");

        assertEquals(88, commands.size());
    }
}
