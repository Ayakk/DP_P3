package com.company;

import java.util.List;

public interface AdresDAO<Adres> {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    boolean findById(int i);
    List<Adres> findAll();
}
