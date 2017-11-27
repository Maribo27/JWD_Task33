package by.tc.task33.service.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOFactory;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.Service;
import by.tc.task33.service.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class DOMParserService implements Service {

    private static final String DOM_PARSER_START_WORK = "DOM Parser start work";
    private static final String DOM_PARSER_ENDS_WITH_ERROR = "DOM Parser ends with error";

    public List<Medicine> getMedicineList(String filePath) throws ServiceException {
        Logger logger = Logger.getLogger(this.getClass());
        try {
            logger.info(DOM_PARSER_START_WORK);
            DAOFactory factory = DAOFactory.getInstance();
            DAO entityDAO = factory.getDOMParser();
            List<Medicine> medicines;
            entityDAO.parseFile(filePath);
            medicines = entityDAO.getMedicines();
            return medicines;
        } catch (DAOException e) {
            logger.info(DOM_PARSER_ENDS_WITH_ERROR);
            throw new ServiceException(e.getMessage());
        }
    }
}