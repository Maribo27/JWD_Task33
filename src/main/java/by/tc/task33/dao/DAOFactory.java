package by.tc.task33.dao;

import by.tc.task33.dao.impl.DAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DAO entityDAO = new DAOImpl();

    private DAOFactory() {}

    public DAO getEntityDAO() {
        return entityDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}