package com.example.wizard.helper;

/**
 * In diesem enum, können wir unsere Sql-Statements sammeln und nach Bedarf abrufen.
 */

public enum SqlStatement {

   SELECT_ALL("SELECT * FROM wizard"),
   EINFUEGEN("INSERT INTO wizard (name, vorname, geburtsdatum, ahvnr, region, kinder, geschlecht) VALUES (?, ?, ?, ?, ?, ?, ?)");

   public final String query;

    //Konstruktor und GETTER
    SqlStatement(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
