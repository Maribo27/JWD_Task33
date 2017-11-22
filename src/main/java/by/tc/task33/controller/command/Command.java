package by.tc.task33.controller.command;

import by.tc.task33.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command{
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
