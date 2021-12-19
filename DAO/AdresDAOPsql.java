package P3.DAO;

import P3.Domein.Adres;
import P3.Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    private Connection conn;

    public AdresDAOPsql(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) throws SQLException{
        PreparedStatement mystmt = conn.prepareStatement("INSERT INTO adres VALUES (?, ?, ?, ?, ?, ?)");
        mystmt.setInt(1, adres.getAdres_id());
        mystmt.setString(2, adres.getPostcode());
        mystmt.setString(3, adres.getHuisnummer());
        mystmt.setString(4, adres.getStraat());
        mystmt.setString(5, adres.getWoonplaats());
        mystmt.setInt(6, adres.getReiziger().getReizigerId());
        mystmt.executeUpdate();
        mystmt.close();
        return true;
    }

    @Override
    public boolean update(Adres adres) throws SQLException{
        PreparedStatement mystmt = conn.prepareStatement("UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? WHERE adres_id=?");
        mystmt.setString(1, adres.getPostcode());
        mystmt.setString(2, adres.getHuisnummer());
        mystmt.setString(3, adres.getStraat());
        mystmt.setString(4, adres.getWoonplaats());
        mystmt.setInt(5, adres.getReiziger().getReizigerId());
        mystmt.setInt(6, adres.getAdres_id());
        mystmt.executeUpdate();
        mystmt.close();
        return true;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException{
        PreparedStatement mystmt = conn.prepareStatement("DELETE FROM adres WHERE adres_id=?");
        mystmt.setInt(1, adres.getAdres_id());
        mystmt.executeUpdate();
        mystmt.close();
        return true;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException{

        PreparedStatement mystmt = conn.prepareStatement("SELECT * FROM adres WHERE reiziger_id=?");
        mystmt.setInt(1, reiziger.getReizigerId());
        ResultSet rs = mystmt.executeQuery();
        if(rs.next()){
            int adres_id = rs.getInt(1);
            String postcode = rs.getString(2);
            String huisnummer = rs.getString(3);
            String straat = rs.getString(4);
            String woonplaats = rs.getString(5);
            int reiziger_id = rs.getInt(6);
            Reiziger r = new Reiziger(reiziger_id, null, null, null, null);
            Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, r);
            mystmt.close();
            rs.close();
            return adres;
        }
        return null;
    }

    @Override
    public boolean findById(int i) throws SQLException{
        PreparedStatement mystmt = conn.prepareStatement("SELECT * FROM adres WHERE adres_id=?");
        mystmt.setInt(1, i);
        mystmt.executeQuery();
        return true;
    }

    @Override
    public List<Adres> findAll() throws SQLException{
        List l1 = new ArrayList<Adres>();
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

            Reiziger r = new Reiziger(reiziger_id, null, null, null, null);

            Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, r);
            l1.add(adres);
        }
        rs.close();
        st.close();
        return l1;
        }
    }

