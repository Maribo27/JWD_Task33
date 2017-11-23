package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOMessages;
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
public class SAXParser implements DAO {

    private List<Medicine> medicines;

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void parseFile(String filePath) throws DAOException {
        medicines = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXFileHandler fileHandler = new SAXFileHandler();
            saxParser.parse(new File(filePath), fileHandler);
            medicines = fileHandler.getMedicines();
        } catch (SAXException e) {
            throw new DAOException(DAOMessages.PARSER_EXCEPTION_MESSAGE);
        } catch (ParserConfigurationException e) {
            throw new DAOException(DAOMessages.CONFIGURE_PARSER_MESSAGE);
        } catch (IOException e) {
            throw new DAOException(DAOMessages.FILE_NOT_FOUND_MESSAGE);
        }
    }
}