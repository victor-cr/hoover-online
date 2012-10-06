package edu.programming.hoover.lang;

import edu.programming.hoover.Command;
import edu.programming.hoover.State;
import edu.programming.hoover.command.BoomCommand;
import edu.programming.hoover.command.MoveCommand;
import edu.programming.hoover.command.PutCommand;
import edu.programming.hoover.command.SayCommand;
import edu.programming.hoover.command.TakeCommand;
import edu.programming.hoover.exception.CommandOverflowHooverException;
import edu.programming.hoover.exception.HooverException;
import edu.programming.hoover.exception.ParseHooverException;
import edu.programming.hoover.model.Content;
import edu.programming.hoover.model.Direction;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class CommandGenerator {
    private final int maxCommandCount;

    public CommandGenerator(int maxCommandCount) {
        this.maxCommandCount = maxCommandCount;
    }

    public Collection<Command> generate(State state, String instructions) throws ParseHooverException {
        Tree tree = parse(instructions);
        Collection<Command> commands = new LinkedList<>();

        try {
            Generator generator = new Generator(state, commands);

            generator.execute(tree);
        } catch (StopException e) {
        } catch (HooverException e) {
            commands.add(new BoomCommand(e));
        }

        return commands;
    }

    private class Generator {
        private State state;
        private Collection<Command> commands;

        public Generator(State state, Collection<Command> commands) {
            this.state = state;
            this.commands = commands;
        }

        public void execute(Tree tree) throws HooverException {
            for (int i = 0; i < tree.getChildCount(); i++) {
                Tree node = tree.getChild(i);

                body(node);
            }
        }

        private void program(Tree program) throws HooverException {
            execute(new SayCommand(program.getChild(0).getText()));
        }

        private void body(Tree body) throws HooverException {
            int type = body.getType();

            switch (type) {
                case HooverParser.PROGRAM:
                    program(body);
                    break;
                case HooverParser.BLOCK:
                    block(body);
                    break;
                case HooverParser.MOVE:
                    operatorMove(body);
                    break;
                case HooverParser.TAKE:
                    operatorTake(body);
                    break;
                case HooverParser.PUT:
                    operatorPut(body);
                    break;
                case HooverParser.EOF:
                case HooverParser.STOP:
                    operatorStop(body);
                    break;
                default:
                    throw new ParseHooverException();
            }
        }

        private void operatorTake(Tree take) throws HooverException {
            execute(new TakeCommand());
        }

        private void operatorPut(Tree put) throws HooverException {
            execute(new PutCommand());
        }

        private void operatorMove(Tree move) throws HooverException {
            execute(new MoveCommand(direction(move.getChild(0))));
        }

        private void operatorStop(Tree stop) throws HooverException {
            throw new StopException();
        }

        private void block(Tree block) throws HooverException {
            int type = block.getChild(0).getType();

            switch (type) {
                case HooverParser.IF:
                    statementIf(block);
                    break;
                case HooverParser.WHILE:
                    statementWhile(block);
                    break;
                default:
                    throw new ParseHooverException();
            }
        }

        private void statementIf(Tree blockIf) throws HooverException {
            boolean condition = false;

            for (int i = 0; i < blockIf.getChildCount(); i++) {
                Tree node = blockIf.getChild(i);

                int type = node.getType();

                switch (type) {
                    case HooverParser.IF:
                    case HooverParser.ELSEIF:
                        condition = checkCondition(node.getChild(0));
                        break;
                    case HooverParser.THEN:
                        if (condition) {
                            execute(node);
                            return;
                        }
                        break;
                    case HooverParser.ELSE:
                        if (!condition) {
                            execute(node);
                            return;
                        }
                        break;
                    default:
                        throw new ParseHooverException();
                }
            }
        }

        private void statementWhile(Tree blockWhile) throws HooverException {
            Tree condition = blockWhile.getChild(0).getChild(0);
            Tree loop = blockWhile.getChild(1);

            while (checkCondition(condition)) {
                execute(loop);
            }
        }

        private boolean checkCondition(Tree condition) throws HooverException {
            int type = condition.getType();

            switch (type) {
                case HooverParser.AND:
                    for (int j = 0; j < condition.getChildCount(); j++) {
                        Tree and = condition.getChild(j);

                        if (!checkCondition(and)) {
                            return false;
                        }
                    }
                    return true;
                case HooverParser.OR:
                    for (int j = 0; j < condition.getChildCount(); j++) {
                        Tree and = condition.getChild(j);

                        if (checkCondition(and)) {
                            return true;
                        }
                    }
                    return false;
                case HooverParser.NOT:
                    return !checkCondition(condition.getChild(0));
                case HooverParser.CAN:
                    return state.canMove(direction(condition.getChild(0)));
                case HooverParser.MATCH:
                    return state.match();
                case HooverParser.BAG:
                    return state.isBagContent(content(condition.getChild(0)));
                case HooverParser.CELL:
                    return state.isCellContent(content(condition.getChild(0)));
                default:
                    throw new ParseHooverException();
            }
        }

        private void execute(Command command) throws HooverException {
            if (commands.size() >= maxCommandCount) {
                throw new CommandOverflowHooverException(maxCommandCount);
            }

            state = command.execute(state);

            commands.add(command);
        }

        private Direction direction(Tree direction) throws ParseHooverException {
            int type = direction.getType();

            switch (type) {
                case HooverParser.DIR_DOWN:
                    return Direction.DOWN;
                case HooverParser.DIR_LEFT:
                    return Direction.LEFT;
                case HooverParser.DIR_RIGHT:
                    return Direction.RIGHT;
                case HooverParser.DIR_UP:
                    return Direction.UP;
                default:
                    throw new ParseHooverException();
            }
        }

        private Content content(Tree content) throws ParseHooverException {
            int type = content.getType();

            switch (type) {
                case HooverParser.EMPTY:
                    return Content.EMPTY;
                case HooverParser.FULL:
                    return Content.FULL;
                default:
                    throw new ParseHooverException();
            }
        }
    }

    private Tree parse(String instructions) throws ParseHooverException {
        try {
            HooverParser parser = new HooverParser(new CommonTokenStream(new HooverLexer(new ANTLRStringStream(instructions))));

            return (Tree) parser.parseApplication().getTree();
        } catch (RecognitionException e) {
            throw new ParseHooverException();
        }
    }

    private static class StopException extends RuntimeException {
    }
}
