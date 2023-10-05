package com.example.wizard.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WozardModel {

    private StringProperty firstnameField;
    private StringProperty lastNameField;
    private StringProperty birthDayField ;
    private StringProperty ahvNumberField;
    private StringProperty regionField;
    private IntegerProperty childrenField;

    public WozardModel() {

        this.firstnameField = new SimpleStringProperty();
        this.lastNameField = new SimpleStringProperty();
        this.birthDayField = new SimpleStringProperty();
        this.ahvNumberField = new SimpleStringProperty();
        this.regionField = new SimpleStringProperty();
        this.childrenField = new SimpleIntegerProperty();
    }


    public String getFirstnameField() {
        return firstnameField.get();
    }

    public StringProperty firstnameFieldProperty() {
        return firstnameField;
    }

    public void setFirstnameField(String firstnameField) {
        this.firstnameField.set(firstnameField);
    }

    public String getLastNameField() {
        return lastNameField.get();
    }

    public StringProperty lastNameFieldProperty() {
        return lastNameField;
    }

    public void setLastNameField(String lastNameField) {
        this.lastNameField.set(lastNameField);
    }

    public String getBirthDay() {
        return birthDayField.get();
    }

    public StringProperty birthDayProperty() {
        return birthDayField;
    }

    public void setBirthDay(String birthDay) {
        this.birthDayField.set(birthDay);
    }

    public String getAhvNumber() {
        return ahvNumberField.get();
    }

    public StringProperty ahvNumberProperty() {
        return ahvNumberField;
    }

    public void setAhvNumber(String ahvNumber) {
        this.ahvNumberField.set(ahvNumber);
    }

    public String getRegion() {
        return regionField.get();
    }

    public StringProperty regionProperty() {
        return regionField;
    }

    public void setRegion(String region) {
        this.regionField.set(region);
    }

    public int getChildrenField() {
        return childrenField.get();
    }

    public IntegerProperty childrenFieldProperty() {
        return childrenField;
    }

    public void setChildrenField(int childrenField) {
        this.childrenField.set(childrenField);
    }

    @Override
    public String toString() {
        return "WozardModel{" +
                "forstname=" + firstnameField +
                ", lastName=" + lastNameField +
                ", birthDay=" + birthDayField +
                ", ahvNumber=" + ahvNumberField +
                ", region=" + regionField +
                ", children=" + childrenField +
                '}';
    }
}
