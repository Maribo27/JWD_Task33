package by.tc.task33.controller;

import by.tc.task33.controller.command.Command;
import by.tc.task33.entity.Medicine;
import by.tc.task33.service.ServiceException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static by.tc.task33.controller.ControlConst.*;
import static by.tc.task33.controller.PageUrl.ERROR_PAGE_URL;
import static by.tc.task33.controller.PageUrl.INFO_PAGE_URL;
import static by.tc.task33.dao.DAOMessages.FILE_NOT_FOUND_MESSAGE;
import static com.sun.deploy.net.HttpRequest.CONTENT_TYPE;

public class Controller extends HttpServlet {

    private static final int ROWS_ON_PAGE = 4;
    private static final String MEDICINES_NOT_FOUND = "medicines not found";
    private static final String ERROR_OCCURRED_FORWARD_TO_ERROR_PAGE = "error occurred, forward to error page";
    private final CommandDirector director = new CommandDirector();
    private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(Controller.class);

    public Controller() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        String log4jLocation = config.getInitParameter("log4j-properties-location");
        ServletContext sc = config.getServletContext();
        if (log4jLocation == null) {
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File yoMamaYesThisSaysYoMama = new File(log4jProp);
            if (yoMamaYesThisSaysYoMama.exists()) {
                PropertyConfigurator.configure(log4jProp);
            } else {
                BasicConfigurator.configure();
            }
        }
        super.init(config);
    }
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType(CONTENT_TYPE);
        int page = Integer.parseInt(request.getParameter("page")) - 1;

        String parserType = request.getParameter(COMMAND).toUpperCase();
        Command command = director.getCommand(parserType);

        try {
            String filePath = getFilePath();
            List<Medicine> medicines = null;
            if (filePath == null){
                log.error(FILE_NOT_FOUND_MESSAGE);
            } else {
                medicines = command.execute(filePath);
                log.info("command executed");
            }

            RequestDispatcher requestDispatcher;
            if (medicines != null && medicines.size() > 0) {
                request.setAttribute(MEDICINE_ATTRIBUTE, medicines);
                request.setAttribute(BEGIN_ATTRIBUTE, page * ROWS_ON_PAGE);
                request.setAttribute(END_ATTRIBUTE, page * ROWS_ON_PAGE + 3);
                request.setAttribute(SIZE_ATTRIBUTE, medicines.size());
                request.setAttribute(PARSER_ATTRIBUTE, parserType);
                request.setAttribute(FIRST_ATTRIBUTE, 0);
                request.setAttribute(PREV_ATTRIBUTE, page - 1);
                request.setAttribute(NEXT_ATTRIBUTE,page + 1);
                int lastPage = medicines.size() / ROWS_ON_PAGE - 1;
                if (medicines.size() / ROWS_ON_PAGE != 0){
                    lastPage++;
                }
                request.setAttribute(LAST_ATTRIBUTE, lastPage);
                request.setAttribute(PAGE_ATTRIBUTE, ROWS_ON_PAGE);
                requestDispatcher = request.getRequestDispatcher(INFO_PAGE_URL);
                requestDispatcher.forward(request, response);
            } else {
                log.error(MEDICINES_NOT_FOUND);
                request.setAttribute(ERROR_ATTRIBUTE, MEDICINES_NOT_FOUND);
                requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            log.error(ERROR_OCCURRED_FORWARD_TO_ERROR_PAGE);
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
