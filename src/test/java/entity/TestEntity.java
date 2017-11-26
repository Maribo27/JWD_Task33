package entity;

import by.tc.task33.entity.Dosage;
import by.tc.task33.entity.Medicine;
import by.tc.task33.entity.MedicineType;
import by.tc.task33.entity.Price;
import org.junit.Ignore;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Ignore
public class TestEntity {

	private static final String FILE_NAME = "Test.xml";
	private static final String OPEN_SQUARE = "%5b";
	private static final String CLOSE_SQUARE = "%5d";
	private static final String OPEN = "[";
	private static final String CLOSE = "]";

	public static List<Medicine> getMedicines() {
		List<Medicine> medicines = new ArrayList<>();
		Medicine aspirin = new Medicine();
		Medicine sinupret = new Medicine();

		aspirin.setId(1);
		aspirin.setName("Аспирин");
		aspirin.setPharm("Фирма 1");
		aspirin.setGroup("Болеутоляющее");

		List <String> analogs = new ArrayList<>();
		analogs.add("Анальгин");
		aspirin.setAnalogs(analogs);

		List<MedicineType> versions = new ArrayList<>();
		MedicineType typeOne = new MedicineType();
		typeOne.setType("Таблетки");

		Dosage doseOne = new Dosage();
		doseOne.setDose("1 таблетка");
		doseOne.setFrequency("2 раза в день");
		typeOne.setDosage(doseOne);

		Price priceTwo = new Price();
		priceTwo.setValue(2);
		priceTwo.setCurrency("BYN");
		typeOne.setPrice(priceTwo);

		typeOne.setPharmacySale("Без рецепта");
		typeOne.setCount("10");
		typeOne.setPackageType("Блистер");
		versions.add(typeOne);
		aspirin.setVersions(versions);

		medicines.add(aspirin);

		sinupret.setId(2);
		sinupret.setName("Синупрет");
		sinupret.setPharm("Шаражка");
		sinupret.setGroup("Секретолитики");

		analogs = new ArrayList<>();
		analogs.add("Доктор МОМ");
		analogs.add("Доктор МОМ Колд");
		sinupret.setAnalogs(analogs);

		versions = new ArrayList<>();

		MedicineType typeTwo = new MedicineType();
		typeTwo.setType("Драже");

		Dosage doseTwo = new Dosage();
		doseTwo.setDose("2");
		doseTwo.setFrequency("3 раза в день");
		typeTwo.setDosage(doseTwo);

		Price priceThree = new Price();
		priceThree.setValue(14);
		priceThree.setCurrency("BYN");
		typeTwo.setPrice(priceThree);

		typeTwo.setPharmacySale("Без рецепта");
		typeTwo.setCount("50");
		typeTwo.setPackageType("Блистер");
		versions.add(typeTwo);

		MedicineType typeThree = new MedicineType();
		typeThree.setType("Капли");

		Dosage doseThree = new Dosage();
		doseThree.setDose("10 капель");
		doseThree.setFrequency("3 раза в день");
		typeThree.setDosage(doseThree);

		Price priceFour = new Price();
		priceFour.setValue(11);
		priceFour.setCurrency("BYN");
		typeThree.setPrice(priceFour);

		typeThree.setPharmacySale("Без рецепта");
		typeThree.setCount("100 мл");
		typeThree.setPackageType("Флакон");
		versions.add(typeThree);

		MedicineType typeFour = new MedicineType();
		typeFour.setType("Сироп");

		Dosage doseFour = new Dosage();
		doseFour.setDose("1 чайная ложка");
		doseFour.setFrequency("3 раза в день");
		typeFour.setDosage(doseFour);

		Price priceOne = new Price();
		priceOne.setValue(12);
		priceOne.setCurrency("BYN");
		typeFour.setPrice(priceOne);

		typeFour.setPharmacySale("Без рецепта");
		typeFour.setCount("100 мл");
		typeFour.setPackageType("Флакон");
		versions.add(typeFour);
		sinupret.setVersions(versions);

		medicines.add(sinupret);
		return medicines;
	}

	public static String getFile(Class cls) {
		String filePath = null;
		URL fileURL = cls.getClassLoader().getResource(FILE_NAME);
		if (fileURL != null) {
			filePath = fileURL.getPath();
			filePath = filePath.replaceAll(OPEN_SQUARE, OPEN);
			filePath = filePath.replaceAll(CLOSE_SQUARE, CLOSE);
		}
		return filePath;
	}
}
