package test_project;

import java.sql.*;

public class dbread extends MySQL_Base {
	public static void main(String[] args) {

		MySQL_Base base = new MySQL_Base();

		String url = "jdbc:mysql://" + base.getHostname() + ":" + base.getPort() + "/" + base.getDatabase(); 
		// Connect-String zur SQL-Datenbank
		
		Connection verbindung = null; // Ein Verbindungs-Objekt
		Statement statement = null; // Ein Statement-Objekt
		ResultSet resultset = null; // Ein Resultset-Objekt

		// Alle Datenbankzugriffe können in einen try-Block gelegt werden
		// Achtung: Zwischen den try- und catch-Blöcken darf kein weiterer
		// Quelltext stehen
		try {
			Class.forName("com.mysql.jdbc.Driver");
			verbindung = DriverManager.getConnection(url, "root", ""); // Verbindung zu unserer Datenbank
			statement = verbindung.createStatement(); // Statement-Objekt für SQL-Abfragen
			resultset = statement.executeQuery("select * from unfall_test");
			ResultSetMetaData rsmd = resultset.getMetaData(); // Hole Meta-Informationen über die Daten
			int iSpalten = rsmd.getColumnCount(); // Hole Anzahl der Spalten
			
			// Ausgabe der Spaltennamen
			for (int i = 1; i <= iSpalten; i++) {
				String SpaltenName = rsmd.getColumnName(i);
				System.out.print(SpaltenName + "\t");
			}
			// Ende der Zeile
			System.out.println();
			// Ausgabe der Daten
			while (resultset.next()) {
				// Hole für jede Spalte den Eintrag und gib aus...
				for (int i = 1; i <= iSpalten; i++) {
					String Eintrag = resultset.getString(i);
					System.out.print(Eintrag + "\t");
				}
				// Ende der Zeile
				System.out.println();
			}
		}

		// Fehlermeldung, wenn er nicht geladen werden konnte
		catch (ClassNotFoundException e) {
			System.out.println("Der Datenbanktreiber wurde nicht gefunden");
			return;
		}

		catch (SQLException e) {
			System.out.println("Beim Verbindungsaufbau ist ein Fehler aufgetreten.\n");
		}

		// resultset, statement und verbindung müssen wieder geschlossen werden.
		finally {
			// resultset schließen
			try {
				if (resultset != null) {
					resultset.close();
				}
			} catch (Exception ex) {
				// nichts zu tun...
			}
			// statmement schließen
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception ex) {
				// nichts zu tun...
			}
			// verbindung schließen
			try {
				if (verbindung != null) {
					verbindung.close();
				}
			} catch (Exception ex) {
				// nichts zu tun...
			}
		}
		// Erfolgsmeldung ist nicht mehr notwendig
		// System.out.println ("Der Datenbanktreiber konnte erfolgreich geladen
		// werden.");
	}
}