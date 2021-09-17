package com.company.Domein;

import java.sql.Date;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString() {
        if(adres==null){
            return "Reiziger{" +
                    "reiziger_id=" + reiziger_id +
                    ", voorletters='" + voorletters + '\'' +
                    ", tussenvoegsel='" + tussenvoegsel + '\'' +
                    ", achternaam='" + achternaam + '\'' +
                    ", geboortedatum=" + geboortedatum +
                    '}';
        } else{
            return "Reiziger{" +
                    "reiziger_id=" + reiziger_id +
                    ", voorletters='" + voorletters + '\'' +
                    ", tussenvoegsel='" + tussenvoegsel + '\'' +
                    ", achternaam='" + achternaam + '\'' +
                    ", geboortedatum=" + geboortedatum +
                    '}'+
                    "Adres{" +
                    "adres_id=" + adres.getAdres_id() +
                    ", postcode='" + adres.getPostcode() + '\'' +
                    ", huisnummer='" + adres.getHuisnummer() + '\'' +
                    ", straat='" + adres.getStraat() + '\'' +
                    ", woonplaats='" + adres.getWoonplaats() + '\'' +
                    ", reiziger_id=" + adres.getReiziger_id() +
                    '}';
        }
    }
}
