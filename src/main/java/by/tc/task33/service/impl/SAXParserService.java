package by.tc.task33.service.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOFactory;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.Service;
import by.tc.task33.service.ServiceException;

import java.util.List;

public class SAXParserService implements Service {

    public List<Medicine> getMedicineList(String filePath) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getSAXParser();
        List<Medicine> medicines;
        try {
            entityDAO.parseFile(filePath);
            medicines = entityDAO.getMedicines();
            return medicines;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}