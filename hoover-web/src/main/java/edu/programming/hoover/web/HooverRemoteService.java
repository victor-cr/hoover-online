package edu.programming.hoover.web;

import edu.programming.hoover.Command;
import edu.programming.hoover.exception.ParseHooverException;
import edu.programming.hoover.lang.CommandGenerator;
import edu.programming.hoover.web.bean.StateBean;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 06/10/12
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class HooverRemoteService {
    private CommandGenerator commandGenerator;

    @Required
    public void setCommandGenerator(CommandGenerator commandGenerator) {
        this.commandGenerator = commandGenerator;
    }

    public Collection<String> execute(StateBean stateBean, String code) throws ParseHooverException {
        Collection<Command> commands = commandGenerator.generate(null, code);
        Collection<String> textCommands = new ArrayList<>(commands.size());

        for (Command command : commands) {
            textCommands.add(command.asText());
        }

        return textCommands;
    }
}
