package by.tc.task33.dao.parser.impl;

import by.tc.task33.dao.parser.ParserException;
import by.tc.task33.entity.Medicine;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maria on 27.04.2017.
 */
public class SAXFile {

    private static final String PARSER_EXCEPTION_MESSAGE = "SAX parser error";
    private static final String FILE_NOT_FOUND_MESSAGE = "File not found";

    private String fileName;
    private List<Medicine> medicines;

    public SAXFile(String fileName) {
        this.fileName = fileName;
        medicines = new ArrayList<>();
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void readFile() throws ParserException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXParser fileHandler = new SAXParser();
            saxParser.parse(new File(fileName), fileHandler);
            medicines = fileHandler.getMedicines();
        } catch (SAXException | ParserConfigurationException e) {
            throw new ParserException(PARSER_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            throw new ParserException(FILE_NOT_FOUND_MESSAGE);
        }
    }
}
