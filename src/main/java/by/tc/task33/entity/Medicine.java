package by.tc.task33.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Medicine implements Serializable {
    private String name;
    private String pharm;
    private String group;
    private int id;
    private List<String> analogs;
    private List<MedicineType> versions;

    public Medicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public List<MedicineType> getVersions() {
        return versions;
    }

    public void setVersions(List<MedicineType> versions) {
        this.versions = versions;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;

        if (name != null ? !name.equals(medicine.name) : medicine.name != null) return false;
        if (pharm != null ? !pharm.equals(medicine.pharm) : medicine.pharm != null) return false;
        if (group != null ? !group.equals(medicine.group) : medicine.group != null) return false;
        if (analogs != null ? !analogs.equals(medicine.analogs) : medicine.analogs != null) return false;
        return versions != null ? versions.equals(medicine.versions) : medicine.versions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pharm != null ? pharm.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (analogs != null ? analogs.hashCode() : 0);
        result = 31 * result + (versions != null ? versions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group='" + group + '\'' +
                ", analogs=" + analogs +
                ", versions=" + versions +
                '}';
    }
}