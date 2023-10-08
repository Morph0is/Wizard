package com.example.wizard.helper;

public enum Colors {
    RED("-fx-text-fill: rgb(250, 33, 0);"),
    GREEN("-fx-text-fill: rgb(11, 227, 20);"),
    BLUE("-fx-text-fill: rgb(126, 3, 253);");

    public final String c;

    Colors(String c) {
        this.c = c;
    }
}
