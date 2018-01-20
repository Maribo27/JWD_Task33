package by.tc.task33.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
        return id == medicine.id &&
                Objects.equals(name, medicine.name) &&
                Objects.equals(pharm, medicine.pharm) &&
                Objects.equals(group, medicine.group) &&
                Objects.equals(analogs, medicine.analogs) &&
                Objects.equals(versions, medicine.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pharm, group, id, analogs, versions);
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