package by.tc.task33.entity;

import java.io.Serializable;

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

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (packageType != null ? !packageType.equals(that.packageType) : that.packageType != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (dosage != null ? !dosage.equals(that.dosage) : that.dosage != null) return false;
        return pharmacySale != null ? pharmacySale.equals(that.pharmacySale) : that.pharmacySale == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (packageType != null ? packageType.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (pharmacySale != null ? pharmacySale.hashCode() : 0);
        return result;
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