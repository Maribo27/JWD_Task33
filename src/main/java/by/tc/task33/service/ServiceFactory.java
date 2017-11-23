package by.tc.task33.service;

import by.tc.task33.service.impl.DOMParserService;
import by.tc.task33.service.impl.SAXParserService;
import by.tc.task33.service.impl.StAXParserService;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final Service domService = new DOMParserService();
    private final Service saxService = new SAXParserService();
    private final Service staxService = new StAXParserService();

    private ServiceFactory() {}

	public Service getDOMService() {
		return domService;
	}

	public Service getSAXService() {
		return saxService;
	}

	public Service getStAXService() {
		return staxService;
	}

	public static ServiceFactory getInstance() {
        return instance;
    }

}
