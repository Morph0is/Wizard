package com.example.wizard.helper;

/**
 * Hier befinden sich die Farben die wir definiert haben und nur diese auch nutzen.
 *
 */
public enum Colors {
    RED("-fx-text-fill: rgb(250, 33, 0);"),
    GREEN("-fx-text-fill: rgb(11, 227, 20);"),
    BLUE("-fx-text-fill: rgb(126, 3, 253);");

    public final String c;

    Colors(String c) {
        this.c = c;
    }
}
