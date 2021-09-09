package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {



    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        ReizigerDAOPsql reizigerDAO = new ReizigerDAOPsql(conn);
        AdresDAOPsql adresDAO = new AdresDAOPsql(conn);
        testReizigerDAO(reizigerDAO);
        testAdresDAO(reizigerDAO, adresDAO);
    }

    private void getConnection(){

    }

    private void closeConnection(){

    }

    private static void testReizigerDAO (ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");



        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        Reiziger updater = new Reiziger(77, "U", "", "UUUU", java.sql.Date.valueOf(gbdatum));
        rdao.update(updater);
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }

        Reiziger deleter = new Reiziger(77, "U", "", "UUUU", java.sql.Date.valueOf(gbdatum));
        rdao.delete(deleter);
        reizigers = rdao.findAll();
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
    }

    private static void testAdresDAO (ReizigerDAO rdao ,AdresDAO adao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        List<Reiziger> reizigers = rdao.findAll();
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende reizigers:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        Adres testAdress = new Adres(100, "8265XH", "25A", "Van Oss-straat", "Kampen", 6);
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adao.save(testAdress);
        adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println(adressen.size() + " adressen\n");

        Adres updater2 = new Adres(5, "U", "U", "UUUU", "Y", 6);
        adao.update(updater2);
        adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
        }

        Adres deleter2 = new Adres(100, "U", "U", "UUUU", "Y", 6);
        adao.delete(deleter2);
        adressen = adao.findAll();
        for (Adres a : adressen) {
            System.out.println(a);
        }

        reizigers = rdao.findAll();
        adressen = adao.findAll();
        for (Reiziger r : reizigers) {
            System.out.print(r);
            System.out.print(adressen.get(r.getReiziger_id()-1));
            System.out.println("\n");
        }
    }
}
