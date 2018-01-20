package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOMessages;
import by.tc.task33.dao.XMLConst;
import by.tc.task33.entity.Dosage;
import by.tc.task33.entity.Medicine;
import by.tc.task33.entity.MedicineType;
import by.tc.task33.entity.Price;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StAXParser implements DAO {
	private static final String ENCODING = "UTF-8";

	private Medicine medicine;
	private String currentElement;
	private MedicineType medicineType;
	private Price price;
	private Dosage dosage;
	private List<String> analogs;
	private List<MedicineType> versions;
	private List<Medicine> medicines;
	private Logger logger;

	private boolean elementStarted;

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void parseFile(String filePath) throws DAOException {
		logger = Logger.getLogger(this.getClass());
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(filePath), ENCODING);
			logger.info(DAOMessages.START_PARSING_MESSAGE);
			parseElements(eventReader);
		} catch (FileNotFoundException e) {
			logger.info(DAOMessages.FILE_NOT_FOUND_MESSAGE);
			throw new DAOException(DAOMessages.FILE_NOT_FOUND_MESSAGE);
		} catch (XMLStreamException e) {
			logger.info(DAOMessages.PARSER_EXCEPTION_MESSAGE);
			throw new DAOException(DAOMessages.PARSER_EXCEPTION_MESSAGE);
		}
	}

	private void parseElements(XMLEventReader eventReader) throws XMLStreamException {

		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();

			boolean startElement = event.getEventType() == XMLStreamConstants.START_ELEMENT;
			boolean elementContext = event.getEventType() == XMLStreamConstants.CHARACTERS && elementStarted;
			boolean endElement = event.getEventType() == XMLStreamConstants.END_ELEMENT;

			if (startElement) {
				startElement(event);
				elementStarted = true;
			} else if (elementContext) {
				parseCharacter(event);
			} else if (endElement){
				event.asEndElement();
				elementStarted = false;
			}
		}
	}

	private void parseCharacter(XMLEvent event) {
		logger.info(DAOMessages.PARSE_ELEMENT_CONTENT + currentElement);
		Characters characters = event.asCharacters();
		String element = characters.getData();
		switch(currentElement) {
			case XMLConst.NAME:
				medicine.setName(element);
				break;
			case XMLConst.PHARM:
				medicine.setPharm(element);
				break;
			case XMLConst.GROUP:
				medicine.setGroup(element);
				break;
			case XMLConst.ANALOG:
				analogs.add(element);
				break;
			case XMLConst.TYPE:
				medicineType.setType(element);
				break;
			case XMLConst.PACKAGE_TYPE:
				medicineType.setPackageType(element);
				break;
			case XMLConst.MEDICINE_COUNT:
				medicineType.setCount(element);
				break;
			case XMLConst.PRICE:
				price.setValue(Double.parseDouble(element));
				break;
			case XMLConst.MEASURING:
				dosage.setDose(element);
				break;
			case XMLConst.FREQUENCY:
				dosage.setFrequency(element);
				break;
			case XMLConst.PHARMACY_SALE:
				medicineType.setPharmacySale(element);
				break;
		}
	}

	private void startElement(XMLEvent event) {
		logger.info(DAOMessages.PARSE_ELEMENT);
		StartElement startElement = event.asStartElement();
		Iterator<Attribute> attributes;
		String attribute;
		currentElement = startElement.getName().getLocalPart();

		switch(currentElement) {
			case XMLConst.MEDICINES:
				logger.info(DAOMessages.CREATING_MEDICINE_LIST);
				medicines = new ArrayList<>();
				break;
			case XMLConst.MEDICINE:
				logger.info(DAOMessages.CREATE_MEDICINE);
				medicine = new Medicine();
				attributes = startElement.getAttributes();
				if (attributes.hasNext()) {
					attribute = attributes.next().getValue().replace("ID-", "");
					medicine.setId(Integer.parseInt(attribute));
				}
				medicines.add(medicine);
				break;
			case XMLConst.ANALOGS:
				logger.info(DAOMessages.CREATE_ANALOGS);
				analogs = new ArrayList<>();
				medicine.setAnalogs(analogs);
				break;
			case XMLConst.VERSIONS:
				logger.info(DAOMessages.CREATE_MEDICINE_TYPE);
				versions = new ArrayList<>();
				medicine.setVersions(versions);
				break;
			case XMLConst.VERSION:
				logger.info(DAOMessages.CREATE_VERSION);
				medicineType = new MedicineType();
				attributes = startElement.getAttributes();
				if (attributes.hasNext()) {
					attribute = attributes.next().getValue();
					medicineType.setType(attribute);
				}
				versions.add(medicineType);
				break;
			case XMLConst.PRICE:
				logger.info(DAOMessages.CREATE_PRICE);
				price = new Price();
				attributes = startElement.getAttributes();
				if (attributes.hasNext()) {
					attribute = attributes.next().getValue();
					price.setCurrency(attribute);
				}
				medicineType.setPrice(price);
				break;
			case XMLConst.DOSAGE:
				logger.info(DAOMessages.CREATE_DOSAGE);
				dosage = new Dosage();
				medicineType.setDosage(dosage);
				break;
		}
	}
}