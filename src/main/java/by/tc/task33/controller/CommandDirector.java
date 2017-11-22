package by.tc.task33.controller;

import by.tc.task33.controller.command.Command;
import by.tc.task33.controller.command.impl.SAXParsing;
import by.tc.task33.controller.command.impl.DOMParsing;
import by.tc.task33.controller.command.impl.StAXParsing;

import java.util.HashMap;
import java.util.Map;

class CommandDirector {
    private Map<CommandType, Command> commands = new HashMap<>();

    CommandDirector() {
        commands.put(CommandType.SAX, new SAXParsing());
        commands.put(CommandType.DOM, new DOMParsing());
        commands.put(CommandType.STAX, new StAXParsing());
    }

    Command getCommand(String name) {
        CommandType commandName = CommandType.valueOf(name);
        return commands.get(commandName);
    }
}
