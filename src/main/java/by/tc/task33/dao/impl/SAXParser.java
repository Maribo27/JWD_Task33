package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOMessages;
import by.tc.task33.entity.Medicine;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXParser implements DAO {

    private List<Medicine> medicines;

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void parseFile(String filePath) throws DAOException {

        Logger logger = Logger.getLogger(this.getClass());
        medicines = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXFileHandler fileHandler = new SAXFileHandler();
            logger.info(DAOMessages.START_PARSING_MESSAGE);
            saxParser.parse(new File(filePath), fileHandler);
            medicines = fileHandler.getMedicines();
        } catch (SAXException e) {
            logger.info(DAOMessages.PARSER_EXCEPTION_MESSAGE);
            throw new DAOException(DAOMessages.PARSER_EXCEPTION_MESSAGE);
        } catch (ParserConfigurationException e) {
            logger.info(DAOMessages.CONFIGURE_PARSER_MESSAGE);
            throw new DAOException(DAOMessages.CONFIGURE_PARSER_MESSAGE);
        } catch (IOException e) {
            logger.info(DAOMessages.FILE_NOT_FOUND_MESSAGE);
            throw new DAOException(DAOMessages.FILE_NOT_FOUND_MESSAGE);
        }
    }
}