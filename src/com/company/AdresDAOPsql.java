package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO<Adres>{
    private Connection conn;
    private ReizigerDAOPsql rdao;

    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    public boolean save(Adres adres){
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("INSERT INTO adres VALUES (?, ?, ?, ?, ?, ?)");
            mystmt.setInt(1, adres.getAdres_id());
            mystmt.setString(2, adres.getPostcode());
            mystmt.setString(3, adres.getHuisnummer());
            mystmt.setString(4, adres.getStraat());
            mystmt.setString(5, adres.getWoonplaats());
            mystmt.setInt(6, adres.getReiziger_id());
            mystmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Adres adres){
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? WHERE adres_id=?");
            mystmt.setString(1, adres.getPostcode());
            mystmt.setString(2, adres.getHuisnummer());
            mystmt.setString(3, adres.getStraat());
            mystmt.setString(4, adres.getWoonplaats());
            mystmt.setInt(5, adres.getReiziger_id());
            mystmt.setInt(6, adres.getAdres_id());
            mystmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Adres adres){
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("DELETE FROM adres WHERE adres_id=?");
            mystmt.setInt(1, adres.getAdres_id());
            mystmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public Adres findByReiziger(Reiziger reiziger) {
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("SELECT * FROM adres WHERE reiziger_id=?");
            mystmt.setInt(1, reiziger.getReiziger_id());
            ResultSet rs = mystmt.executeQuery();
            if(rs.next()){
                int adres_id = rs.getInt(1);
                String postcode = rs.getString(2);
                String huisnummer = rs.getString(3);
                String straat = rs.getString(4);
                String woonplaats = rs.getString(5);
                int reiziger_id = rs.getInt(6);

                Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);
                mystmt.close();
                rs.close();
                return adres;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findById(int i){
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            PreparedStatement mystmt = conn.prepareStatement("SELECT * FROM adres WHERE adres_id=?");
            mystmt.setInt(1, i);
            mystmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Adres> findAll(){
        List l1 = new ArrayList<Adres>();
        try {
            // 1. Get a connection to database
            conn = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "TugbaK26");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM adres");
            System.out.println("Alle adressen:");
            while (rs.next()) {
                int adres_id = rs.getInt(1);
                String postcode = rs.getString(2);
                String huisnummer = rs.getString(3);
                String straat = rs.getString(4);
                String woonplaats = rs.getString(5);
                int reiziger_id = rs.getInt(6);

                Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);
                l1.add(adres);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l1;
    }
}
