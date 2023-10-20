package com.example.wizard.model;


public class Person {
    private int    id;
    private String name;
    private String vorname;
    private String gebDatum;
    private String ahvNr;
    private String region;
    private int kinder;
    private String geschlecht;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", gebDatum='" + gebDatum + '\'' +
                ", ahvNr='" + ahvNr + '\'' +
                ", region='" + region + '\'' +
                ", kinder=" + kinder +
                ", geschlecht='" + geschlecht + '\'' +
                '}';
    }

    public Person(int id, String name, String vorname, String gebDatum, String ahvNr, String region, int kinder, String geschlecht) {
        this.id = id;
        this.name = name;
        this.vorname = vorname;
        this.gebDatum = gebDatum;
        this.ahvNr = ahvNr;
        this.region = region;
        this.kinder = kinder;
        this.geschlecht = geschlecht;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(String gebDatum) {
        this.gebDatum = gebDatum;
    }

    public String getAhvNr() {
        return ahvNr;
    }

    public void setAhvNr(String ahvNr) {
        this.ahvNr = ahvNr;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getKinder() {
        return kinder;
    }

    public void setKinder(int kinder) {
        this.kinder = kinder;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }
}