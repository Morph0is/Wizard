package com.example.wizard.helper;

/**
 * In diesem enum, können wir unsere Sql-Statements sammeln und nach Bedarf abrufen.
 */

public enum SqlStatement {



   SELECTALL("SELECT * FROM wizard"),
   INSERT("INSERT INTO wizard (name, vorname, geburtsdatum, ahvnr, region, kinder, geschlecht) VALUES (?, ?, ?, ?, ?, ?, ?)"),
    DELETE("DELETE FROM wizard WHERE id = ?"),
    UPDATE("UPDATE wizard SET name = ?, vorname = ?, geburtsdatum = ?, ahvnr = ?, region = ?, kinder = ?, geschlecht = ? WHERE id = ?");

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
