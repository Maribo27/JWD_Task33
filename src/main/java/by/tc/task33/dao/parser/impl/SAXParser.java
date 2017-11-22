package by.tc.task33.dao.parser.impl;

import by.tc.task33.dao.parser.XMLConst;
import by.tc.task33.entity.Dosage;
import by.tc.task33.entity.Medicine;
import by.tc.task33.entity.MedicineType;
import by.tc.task33.entity.Price;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler{

    private Medicine medicine;
    private String currentElement;
    private MedicineType medicineType;
    private Price price;
    private Dosage dosage;
    private List<String> analogs;
    private List<MedicineType> versions;
    private List<Medicine> medicines;

    SAXParser() {
        currentElement = "";
    }

    List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        currentElement = qName;
        switch (currentElement.toLowerCase()){
            case XMLConst.MEDICINES:
                medicines = new ArrayList<>();
                break;
            case XMLConst.MEDICINE:
                medicine = new Medicine();
                medicines.add(medicine);
                break;
            case XMLConst.ANALOGS:
                analogs = new ArrayList<>();
                medicine.setAnalogs(analogs);
                break;
            case XMLConst.VERSIONS:
                versions = new ArrayList<>();
                medicine.setVersions(versions);
                break;
            case XMLConst.VERSION:
                medicineType = new MedicineType();
                versions.add(medicineType);
                break;
            case XMLConst.PRICE:
                price = new Price();
                price.setCurrency(attributes.getValue(XMLConst.CURRENCY));
                medicineType.setPrice(price);
                break;
            case XMLConst.DOSAGE:
                dosage = new Dosage();
                medicineType.setDosage(dosage);
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
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
            case XMLConst.TYPE:
                medicineType.setType(elementValue);
                break;
            case XMLConst.PACKAGE_TYPE:
                medicineType.setPackageType(elementValue);
                break;
            case XMLConst.MEDICINE_COUNT:
                medicineType.setCount(elementValue);
                break;
            case XMLConst.PRICE:
                price.setValue(new Integer(elementValue));
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
        currentElement = "";
    }
}