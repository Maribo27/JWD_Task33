package by.tc.task33.controller.command;

import by.tc.task33.entity.Medicine;
import by.tc.task33.service.ServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public interface Command{
    List<Medicine> execute(String filePath) throws ServletException, IOException, ServiceException;
}
