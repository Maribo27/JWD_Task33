package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAOMessages;
import by.tc.task33.dao.XMLConst;
import by.tc.task33.entity.Dosage;
import by.tc.task33.entity.Medicine;
import by.tc.task33.entity.MedicineType;
import by.tc.task33.entity.Price;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXFileHandler extends DefaultHandler{

    private static final String EMPTY_STRING = "";
    private Medicine medicine;
    private String currentElement;
    private MedicineType medicineType;
    private Price price;
    private Dosage dosage;
    private List<String> analogs;
    private List<MedicineType> versions;
    private List<Medicine> medicines;
    private Logger logger;

    SAXFileHandler() {
        currentElement = EMPTY_STRING;
        logger = Logger.getLogger(this.getClass());
    }

    List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        logger.info(DAOMessages.PARSE_ELEMENT);
        currentElement = qName;

        switch (currentElement.toLowerCase()){
            case XMLConst.MEDICINES:
                logger.info(DAOMessages.PARSE_ELEMENT);
                medicines = new ArrayList<>();
                break;
            case XMLConst.MEDICINE:
                logger.info(DAOMessages.CREATE_MEDICINE);
                medicine = new Medicine();
                String id = attributes.getValue(XMLConst.ID);
                medicine.setId(Integer.parseInt(id));
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
                medicineType.setType(attributes.getValue(XMLConst.TYPE));
                versions.add(medicineType);
                break;
            case XMLConst.PRICE:
                logger.info(DAOMessages.CREATE_PRICE);
                price = new Price();
                price.setCurrency(attributes.getValue(XMLConst.CURRENCY));
                medicineType.setPrice(price);
                break;
            case XMLConst.DOSAGE:
                logger.info(DAOMessages.CREATE_DOSAGE);
                dosage = new Dosage();
                medicineType.setDosage(dosage);
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        logger.info(DAOMessages.PARSE_ELEMENT_CONTENT + currentElement);
        String elementValue = new String(ch, start, length);
        switch (currentElement.toLowerCase()){
            case XMLConst.NAME:
                medicine.setName(elementValue);
                break;
            case XMLConst.PHARM:
                medicine.setPharm(elementValue);
                break;
            case XMLConst.GROUP:
                medicine.setGroup(elementValue);
                break;
            case XMLConst.ANALOG:
                analogs.add(elementValue);
                break;
            case XMLConst.PACKAGE_TYPE:
                medicineType.setPackageType(elementValue);
                break;
            case XMLConst.MEDICINE_COUNT:
                medicineType.setCount(elementValue);
                break;
            case XMLConst.PRICE:
                price.setValue(new Double(elementValue));
                break;
            case XMLConst.MEASURING:
                dosage.setDose(elementValue);
                break;
            case XMLConst.FREQUENCY:
                dosage.setFrequency(elementValue);
                break;
            case XMLConst.PHARMACY_SALE:
                medicineType.setPharmacySale(elementValue);
                break;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        logger.info(DAOMessages.END_OF_ELEMENT);
        currentElement = EMPTY_STRING;
    }
}