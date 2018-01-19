package by.tc.task33.controller;

import by.tc.task33.controller.command.Command;
import by.tc.task33.entity.Medicine;
import by.tc.task33.entity.Page;
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
import javax.servlet.http.HttpSession;
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
	private static final String MEDICINES_NOT_FOUND = "medicines not found";
	private static final String ERROR_OCCURRED_FORWARD_TO_ERROR_PAGE = "error occurred, forward to error page";
	private static final String COMMAND_EXECUTED = "command executed";
	private static final int ROWS_ON_PAGE = 4;
    private final CommandDirector director = new CommandDirector();
    private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(Controller.class);

    public Controller() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        String log4jLocation = config.getInitParameter(LOG4J_XML);
        ServletContext sc = config.getServletContext();
        if (log4jLocation == null) {
            BasicConfigurator.configure();
        } else {
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File logFile = new File(log4jProp);
            if (logFile.exists()) {
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
        int page = Integer.parseInt(request.getParameter(PAGE)) - 1;
        HttpSession session = request.getSession();
	    RequestDispatcher requestDispatcher;
	    List<Medicine> medicines = request.getParameter(COMMAND) != null ? getMedicines(request, response, session) : (List<Medicine>) session.getAttribute(MEDICINE);
	    if (medicines != null && medicines.size() > 0) {
		    Page currentPage = getPageInformation(page, medicines.size());
		    List<Medicine> medicinesOnPage = medicines.subList(currentPage.getBegin(), currentPage.getEnd());
		    request.setAttribute(MEDICINE, medicinesOnPage);
		    request.setAttribute(CURRENT_PAGE, currentPage);
		    requestDispatcher = request.getRequestDispatcher(INFO_PAGE_URL);
		    requestDispatcher.forward(request, response);
	    } else {
		    log.error(MEDICINES_NOT_FOUND);
		    request.setAttribute(ERROR, MEDICINES_NOT_FOUND);
		    requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
		    requestDispatcher.forward(request, response);
	    }

    }

	private List<Medicine> getMedicines(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		List<Medicine> medicines = null;
		RequestDispatcher requestDispatcher;
		String parserType = request.getParameter(COMMAND).toUpperCase();
		Command command = director.getCommand(parserType);
		try {
			String filePath = getFilePath();
			if (filePath == null) {
				log.error(FILE_NOT_FOUND_MESSAGE);
			} else {
				medicines = command.execute(filePath);
				session.setAttribute(MEDICINE, medicines);
				log.info(COMMAND_EXECUTED);
			}
		} catch (ServiceException e) {
			log.error(ERROR_OCCURRED_FORWARD_TO_ERROR_PAGE);
			request.setAttribute(ERROR, e.getMessage());
			requestDispatcher = request.getRequestDispatcher(ERROR_PAGE_URL);
			requestDispatcher.forward(request, response);
		}
		return medicines;
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

    private Page getPageInformation(int page, int size) {
        Page current = new Page();
        current.setBegin(page * ROWS_ON_PAGE);
        int end = (page + 1) * ROWS_ON_PAGE;
        int realEnd = end > size ? size : end;
        current.setEnd(realEnd);
        current.setSize(size);
        current.setFirst(0);
        current.setPrev(page - 1);
        current.setNext(page + 1);

        int lastPage = size / ROWS_ON_PAGE - 1;
        if (size / ROWS_ON_PAGE != 0){
            lastPage++;
        }
        current.setLast(lastPage);
        current.setPage(ROWS_ON_PAGE);
        return current;
    }
}
