package com.example.wizard;

import com.example.wizard.helper.DatabaseHandler;
public class Main {

    public static void main(String[] args) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.showWizards();
    }
}
