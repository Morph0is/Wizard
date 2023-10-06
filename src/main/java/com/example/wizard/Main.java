package com.example.wizard;

import com.example.wizard.conroller.WizardController;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {


    public static void main(String[] args) throws SQLException, ParseException {

        //Dies ist ein erster Test um festzustellen ob, alles mit der Verbindung zu Datenbank geklappt hat.
        WizardController controller = new WizardController();
        controller.insertPerson();
       // controller.showWizards();
    }
}
