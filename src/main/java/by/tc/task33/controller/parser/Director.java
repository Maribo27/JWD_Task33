package by.tc.task33.controller.parser;

import by.tc.task33.controller.parser.impl.DOMParsing;
import by.tc.task33.controller.parser.impl.SAXParsing;
import by.tc.task33.controller.parser.impl.StAXParsing;

import java.util.HashMap;
import java.util.Map;

public class Director {
    private Map<ParserType, Parser> parsers = new HashMap<>();

    public Director() {
        parsers.put(ParserType.SAX, new SAXParsing());
        parsers.put(ParserType.DOM, new DOMParsing());
        parsers.put(ParserType.STAX, new StAXParsing());
    }

    public Parser getParser(String name) {
        ParserType commandName = ParserType.valueOf(name);
        return parsers.get(commandName);
    }
}