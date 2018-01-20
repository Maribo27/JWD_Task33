package by.tc.task33.controller.parser.impl;

import by.tc.task33.controller.parser.Parser;
import by.tc.task33.service.ServiceException;

public class DOMParsing extends Parser {
    @Override
    public void parse(String filePath) throws ServiceException {
        service = factory.getDOMService();
        medicines = service.getMedicineList(filePath);
    }
}