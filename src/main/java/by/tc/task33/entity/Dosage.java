package by.tc.task33.entity;

import java.util.Objects;

public class Dosage {
    private String dose;
    private String frequency;

    public Dosage() {
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dosage dosage = (Dosage) o;
        return Objects.equals(dose, dosage.dose) &&
                Objects.equals(frequency, dosage.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dose, frequency);
    }

    @Override
    public String toString() {
        return dose + " - " + frequency;
    }
}
