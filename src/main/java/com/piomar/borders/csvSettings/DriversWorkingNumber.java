package com.piomar.borders.csvSettings;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class DriversWorkingNumber {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 6)
    private String workingId;

    public DriversWorkingNumber() {
    }

    public DriversWorkingNumber(String name, String workingId) {
        this.name = name;
        this.workingId = workingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkingId() {
        return workingId;
    }

    public void setWorkingId(String workingId) {
        this.workingId = workingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriversWorkingNumber that = (DriversWorkingNumber) o;

        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(workingId, that.workingId);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (workingId != null ? workingId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DriversWorkingNumber{" +
                "name='" + name + '\'' +
                ", workingId=" + workingId +
                '}';
    }
}
