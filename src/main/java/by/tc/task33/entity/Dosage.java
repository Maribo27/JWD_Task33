package by.tc.task33.entity;

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

        if (dose != null ? !dose.equals(dosage.dose) : dosage.dose != null) return false;
        return frequency != null ? frequency.equals(dosage.frequency) : dosage.frequency == null;
    }

    @Override
    public int hashCode() {
        int result = dose != null ? dose.hashCode() : 0;
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return dose + " - " + frequency;
    }
}
