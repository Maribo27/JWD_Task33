package by.tc.task33.dao;

import by.tc.task33.dao.impl.DOMParser;
import by.tc.task33.dao.impl.SAXParser;
import by.tc.task33.dao.impl.StAXParser;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DAO domParser = new DOMParser();
    private final DAO saxParser = new SAXParser();
    private final DAO staxParser = new StAXParser();

    private DAOFactory() {}

    public DAO getDOMParser() {
        return domParser;
    }

    public DAO getSAXParser() {
        return saxParser;
    }

    public DAO getStAXParser() {
        return staxParser;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}