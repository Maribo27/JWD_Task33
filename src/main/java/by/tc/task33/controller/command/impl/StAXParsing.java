package by.tc.task33.controller.command.impl;

import by.tc.task33.controller.command.Command;
import by.tc.task33.service.ServiceException;
import by.tc.task33.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StAXParsing implements Command {
    private static final String USER_NOT_FOUND_MESSAGE = "Medicine not found";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid password";

    private ServiceFactory factory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
    }
}