package by.tc.task33.controller.parser.impl;

import by.tc.task33.controller.parser.Parser;
import by.tc.task33.service.ServiceException;

public class StAXParsing extends Parser {
    @Override
    public void parse(String filePath) throws ServiceException {
        service = factory.getStAXService();
        medicines = service.getMedicineList(filePath);
    }
}