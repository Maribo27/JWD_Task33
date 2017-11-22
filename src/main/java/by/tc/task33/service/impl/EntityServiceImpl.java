package by.tc.task33.service.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOFactory;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.EntityService;
import by.tc.task33.service.ServiceException;

import java.util.List;

public class EntityServiceImpl implements EntityService {

    public List<Medicine> getMedicineList() throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        DAO entityDAO = factory.getEntityDAO();
        List<Medicine> medicines;
        try {
            medicines = entityDAO.getMedicineList();
            return medicines;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}