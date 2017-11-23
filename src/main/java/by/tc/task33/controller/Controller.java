package by.tc.task33.controller;

import by.tc.task33.controller.command.Command;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static by.tc.task33.controller.ControlConst.*;
import static by.tc.task33.controller.PageUrl.ERROR_PAGE_URL;
import static by.tc.task33.controller.PageUrl.INFO_PAGE_URL;
import static com.sun.deploy.net.HttpRequest.CONTENT_TYPE;

public class Controller extends HttpServlet {

    private final CommandDirector director = new CommandDirector();
    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);

        String parserType = request.getParameter(COMMAND).toUpperCase();
        Command command = director.getCommand(parserType);

        try {
            String filePath = getFilePath();
            List<Medicine> medicines = command.execute(filePath);

            RequestDispatcher requestDispatcher;

            if (medicines != null && medicines.size() > 0) {
                request.setAttribute(MEDICINE_ATTRIBUTE, medicines);
                requestDispatcher = request.getRequestDispatcher(INFO_PAGE_URL);
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            request.setAttribute(ERROR_ATTRIBUTE, e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public String getFilePath() {
        URL fileURL = this.getClass().getClassLoader().getResource(FILE_NAME);
        String filePath = null;
        if (fileURL != null) {
            filePath = fileURL.getPath();

            filePath = filePath.replaceAll(OPEN_SQUARE, OPEN);
            filePath = filePath.replaceAll(CLOSE_SQUARE, CLOSE);
        }
        return filePath;
    }
}
