package by.tc.task33.entity;

import java.io.Serializable;
import java.util.Objects;

public class MedicineType implements Serializable {
    private String type;
    private String packageType;
    private String count;
    private Price price;
    private Dosage dosage;
    private String pharmacySale;

    public MedicineType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public String getPharmacySale() {
        return pharmacySale;
    }

    public void setPharmacySale(String pharmacySale) {
        this.pharmacySale = pharmacySale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineType that = (MedicineType) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(packageType, that.packageType) &&
                Objects.equals(count, that.count) &&
                Objects.equals(price, that.price) &&
                Objects.equals(dosage, that.dosage) &&
                Objects.equals(pharmacySale, that.pharmacySale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, packageType, count, price, dosage, pharmacySale);
    }

    @Override
    public String toString() {
        return "MedicineType{" +
                "type='" + type + '\'' +
                ", packageType='" + packageType + '\'' +
                ", count='" + count + '\'' +
                ", price=" + price +
                ", dosage=" + dosage +
                ", pharmacySale='" + pharmacySale + '\'' +
                '}';
    }
}