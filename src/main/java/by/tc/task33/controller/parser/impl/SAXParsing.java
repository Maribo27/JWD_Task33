package by.tc.task33.controller.parser.impl;

import by.tc.task33.controller.parser.Parser;
import by.tc.task33.service.ServiceException;

public class SAXParsing extends Parser {
    @Override
    public void parse(String filePath) throws ServiceException {
        service = factory.getSAXService();
        medicines = service.getMedicineList(filePath);
    }
}