package by.tc.task33.dao.impl;

import by.tc.task33.dao.DAO;
import by.tc.task33.dao.DAOException;
import by.tc.task33.dao.DAOMessages;
import by.tc.task33.dao.XMLConst;
import by.tc.task33.entity.Dosage;
import by.tc.task33.entity.Medicine;
import by.tc.task33.entity.MedicineType;
import by.tc.task33.entity.Price;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser implements DAO {
	private List<Medicine> medicines;

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void parseFile(String filePath) throws DAOException {

		try {
			File inputFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList medicinesNodeList = doc.getElementsByTagName(XMLConst.MEDICINE);

			medicines = createMedicineList(medicinesNodeList);
		} catch (ParserConfigurationException e) {
			throw new DAOException(DAOMessages.CONFIGURE_PARSER_MESSAGE);
		} catch (SAXException e) {
			throw new DAOException(DAOMessages.PARSER_EXCEPTION_MESSAGE);
		} catch (IOException e) {
			throw new DAOException(DAOMessages.FILE_NOT_FOUND_MESSAGE);
		}
	}

	private List<Medicine> createMedicineList(NodeList medicinesNodeList) {
		List<Medicine> medicines = new ArrayList<>();
		for (int medicineIndex = 0; medicineIndex < medicinesNodeList.getLength(); medicineIndex++) {
			Medicine medicine = new Medicine();
			Node medicineNode = medicinesNodeList.item(medicineIndex);

			if (medicineNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) medicineNode;

				String name = element.getElementsByTagName(XMLConst.NAME).item(0).getTextContent();
				medicine.setName(name);

				String pharm = element.getElementsByTagName(XMLConst.PHARM).item(0).getTextContent();
				medicine.setPharm(pharm);

				String group = element.getElementsByTagName(XMLConst.GROUP).item(0).getTextContent();
				medicine.setGroup(group);

				NodeList versionNodes = element.getElementsByTagName(XMLConst.VERSIONS);
				List<MedicineType> versions = createMedicineTypes(versionNodes);
				medicine.setVersions(versions);

				NodeList analogNodes = element.getElementsByTagName(XMLConst.ANALOGS);
				List<String> analogs = createAnalogs((Element) medicineNode, analogNodes);
				medicine.setAnalogs(analogs);

				medicines.add(medicine);
			}
		}
		return medicines;
	}

	private List<MedicineType> createMedicineTypes(NodeList versionNodes) {
		List<MedicineType> versions = new ArrayList<>();
		for (int versionCounter = 0; versionCounter < versionNodes.getLength(); versionCounter++) {
			MedicineType medicineType = new MedicineType();
			NodeList versionNode = versionNodes.item(versionCounter).getChildNodes();
			for (int versionField = 0; versionField < versionNode.getLength(); versionField++){
				Node medicineUseCase = versionNode.item(versionField);

				boolean elementNode = medicineUseCase.getNodeType() == Node.ELEMENT_NODE;
				if (elementNode) {
					Element element = (Element) medicineUseCase;

					String type = element.getAttribute(XMLConst.TYPE);
					medicineType.setType(type);

					NodeList aPackage = element.getElementsByTagName(XMLConst.PACKAGE);
					createPackage(medicineType, aPackage);

					NodeList dosages = element.getElementsByTagName(XMLConst.DOSAGE);
					createDosage(medicineType, dosages);

					String pharmacySale = element.getElementsByTagName(XMLConst.PHARMACY_SALE).item(0).getTextContent();
					medicineType.setPharmacySale(pharmacySale);
					versions.add(medicineType);
				}
			}
		}
		return versions;
	}

	private void createDosage(MedicineType medicineType, NodeList dosages) {
		for (int dosageFieldCounter = 0; dosageFieldCounter < dosages.getLength(); dosageFieldCounter++){
			Node dosageNode = dosages.item(dosageFieldCounter);

			boolean elementNode = dosageNode.getNodeType() == Node.ELEMENT_NODE;
			if (elementNode) {
				Dosage dosage = new Dosage();
				Element element = (Element) dosageNode;

				String frequency = element.getElementsByTagName(XMLConst.FREQUENCY).item(0).getTextContent();
				dosage.setFrequency(frequency);

				String measuring = element.getElementsByTagName(XMLConst.MEASURING).item(0).getTextContent();
				dosage.setDose(measuring);
				medicineType.setDosage(dosage);
			}
		}
	}

	private void createPackage(MedicineType medicineType, NodeList aPackage) {
		for (int packageField = 0; packageField < aPackage.getLength(); packageField++){
			Node packageNode = aPackage.item(packageField);
			if (packageNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) packageNode;

				String packageType = element.getElementsByTagName(XMLConst.PACKAGE_TYPE).item(0).getTextContent();
				medicineType.setPackageType(packageType);

				String count = element.getElementsByTagName(XMLConst.MEDICINE_COUNT).item(0).getTextContent();
				medicineType.setCount(count);

				Price price = createPrice(element);
				medicineType.setPrice(price);
			}
		}
	}

	private Price createPrice(Element element) {
		String priceValue = element.getElementsByTagName(XMLConst.PRICE).item(0).getTextContent();

		NamedNodeMap attributes = element.getElementsByTagName(XMLConst.PRICE).item(0).getAttributes();

		String currency = attributes.getNamedItem(XMLConst.CURRENCY).getNodeValue();

		Price price = new Price();
		price.setCurrency(currency);
		price.setValue(Integer.parseInt(priceValue));

		return price;
	}

	private List<String> createAnalogs(Element medicineNode, NodeList analogNodes) {
		List<String> analogs = new ArrayList<>();

		for (int analogCounter = 0; analogCounter < analogNodes.getLength(); analogCounter++) {
			Node analogNode = analogNodes.item(analogCounter);

			boolean elementNode = analogNode.getNodeType() == Node.ELEMENT_NODE;
			if (elementNode) {
				String analog = medicineNode.getElementsByTagName(XMLConst.ANALOG).item(0).getTextContent();
				analogs.add(analog);
			}
		}
		return analogs;
	}
}
