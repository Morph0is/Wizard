package com.example.wizard.model;

import javafx.beans.property.*;

/**
 * Properties die in der Controller-Klasse dann mit Data-binding an die Id's der View verbunden werden.
 */

public class WizardModel {


    private StringProperty firstnameField;
    private StringProperty lastNameField;
    private StringProperty birthDayField ;
    private StringProperty ahvNumberField;
    private StringProperty regionField;
    private StringProperty childrenField;
    private boolean isFemale;
    private boolean isMale;

    public boolean isFemale() {
        return isFemale;
    }

    public  void setFemale(boolean female) {
        isFemale = female;
    }

    public boolean isMale() {
        return isMale;
    }

    public  void setMale(boolean male) {
        isMale = male;
    }

    private StringProperty messageLabelField;

    public String getMessageLabelField() {
        return messageLabelField.get();
    }

    public StringProperty messageLabelFieldProperty() {
        return messageLabelField;
    }

    public void setMessageLabelField(String messageLabelField) {
        this.messageLabelField.set(messageLabelField);
    }

    //Konstruktor dieser Klasse
    public WizardModel() {

        this.firstnameField = new SimpleStringProperty();
        this.lastNameField = new SimpleStringProperty();
        this.birthDayField = new SimpleStringProperty();
        this.ahvNumberField = new SimpleStringProperty();
        this.regionField = new SimpleStringProperty();
        this.childrenField = new SimpleStringProperty();


    }

    //Getter und Setter
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

    public String getChildrenField() {
        return childrenField.get();
    }

    public StringProperty childrenFieldProperty() {
        return childrenField;
    }

    public void setChildrenField(String childrenField) {
        this.childrenField.set(childrenField);
    }



    @Override
    public String toString() {
        return "WizardModel{" +
                "firstnameField=" + firstnameField +
                ", lastNameField=" + lastNameField +
                ", birthDayField=" + birthDayField +
                ", ahvNumberField=" + ahvNumberField +
                ", regionField=" + regionField +
                ", childrenField=" + childrenField +
                '}';
    }
}
