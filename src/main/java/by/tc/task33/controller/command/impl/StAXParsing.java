package by.tc.task33.controller.command.impl;

import by.tc.task33.controller.command.Command;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.Service;
import by.tc.task33.service.ServiceException;
import by.tc.task33.service.ServiceFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class StAXParsing implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public List<Medicine> execute(String filePath) throws ServiceException {
        Service service = factory.getStAXService();

        List<Medicine> medicines;
        medicines = service.getMedicineList(filePath);
        return medicines;
    }
}