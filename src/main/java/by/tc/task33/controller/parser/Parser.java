package by.tc.task33.controller.parser;

import by.tc.task33.entity.Medicine;
import by.tc.task33.service.Service;
import by.tc.task33.service.ServiceException;
import by.tc.task33.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public abstract class Parser {
    protected ServiceFactory factory = ServiceFactory.getInstance();
    protected List<Medicine> medicines;
    protected Service service;

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public abstract void parse(String filePath) throws ServletException, IOException, ServiceException;
}
