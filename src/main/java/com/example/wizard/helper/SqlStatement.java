package com.example.wizard.helper;

/**
 * In diesem enum, können wir unsere Sql-Statements sammeln und nach Bedarf abrufen.
 */

public enum SqlStatement {


    //Todo: hier noch EDIT und DELETE statements erstellen. wichtig: prepared-statements verwenden.
   SELECT_ALL("SELECT * FROM wizard"),
   EINFUEGEN("INSERT INTO wizard (name, vorname, geburtsdatum, ahvnr, region, kinder, geschlecht) VALUES (?, ?, ?, ?, ?, ?, ?)"),
    LOESCHEN("DELETE FROM wizard WHERE id = ?"),
    AKTUALISIEREN("UPDATE wizard SET name = ?, vorname = ?, geburtsdatum = ?, ahvnr = ?, region = ?, kinder = ?, geschlecht = ? WHERE id = ?");

   public final String query;

    /**
     * Getter  und  Setter
     * @param query
     */
    SqlStatement(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
