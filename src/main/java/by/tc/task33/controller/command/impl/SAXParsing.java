package by.tc.task33.controller.command.impl;

import by.tc.task33.controller.command.Command;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.EntityService;
import by.tc.task33.service.ServiceException;
import by.tc.task33.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.tc.task33.controller.command.ControlConst.*;
import static by.tc.task33.controller.command.PageUrl.*;

public class SAXParsing implements Command {

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        EntityService service = factory.getEntityService();

        RequestDispatcher requestDispatcher;

        List<Medicine> medicines;
        medicines = service.getMedicineList();

        if (medicines != null && medicines.size() > 0) {
            request.setAttribute(MEDICINE_ATTRIBUTE, medicines);
            requestDispatcher = request.getRequestDispatcher(USER_INFO_PAGE_URL);
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
            requestDispatcher.forward(request, response);
        }
    }
}