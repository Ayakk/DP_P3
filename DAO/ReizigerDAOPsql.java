package P3.DAO;

import P3.Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{
    private Connection conn;
    private AdresDAOPsql adao;

    public ReizigerDAOPsql(Connection conn){
        this.conn = conn;
        this.adao=new AdresDAOPsql(conn);
    }

    public boolean save(Reiziger reiziger) throws SQLException {
        PreparedStatement mystmt = conn.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
        mystmt.setInt(1, reiziger.getReizigerId());
        mystmt.setString(2, reiziger.getVoorletters());
        mystmt.setString(3, reiziger.getTussenvoegsel());
        mystmt.setString(4, reiziger.getAchternaam());
        mystmt.setDate(5, reiziger.getGeboortedatum());
        mystmt.executeUpdate();
        mystmt.close();
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        PreparedStatement mystmt = conn.prepareStatement("UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id=?");
        mystmt.setInt(5, reiziger.getReizigerId());
        mystmt.setString(1, reiziger.getVoorletters());
        mystmt.setString(2, reiziger.getTussenvoegsel());
        mystmt.setString(3, reiziger.getAchternaam());
        mystmt.setDate(4, reiziger.getGeboortedatum());
        mystmt.executeQuery();
        mystmt.close();
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException{
        PreparedStatement mystmt = conn.prepareStatement("DELETE FROM reiziger WHERE reiziger_id=?");
        mystmt.setInt(1, reiziger.getReizigerId());
        mystmt.executeQuery();
        mystmt.close();
        if (reiziger.getAdres() != null){
            adao.delete(reiziger.getAdres());
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) throws SQLException{
        Reiziger r1 = new Reiziger(0, null, null, null, null);
        PreparedStatement mystmt = conn.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id=?");
        mystmt.setInt(1, id);
        ResultSet rs = mystmt.executeQuery("SELECT * FROM reiziger");
        r1.setReizigerId(rs.getInt(1));
        r1.setVoorletters(rs.getString(2));
        r1.setTussenvoegsel(rs.getString(3));
        r1.setAchternaam(rs.getString(4));
        r1.setGeboortedatum(rs.getDate(5));
        mystmt.close();
        rs.close();
        return r1;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) throws SQLException{
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        PreparedStatement mystmt = conn.prepareStatement("SELECT * from reiziger WHERE geboortedatum =?");
        mystmt.setDate(1, Date.valueOf(datum));
        ResultSet rs = mystmt.executeQuery();
        while (rs.next()) {
            Reiziger reiziger = new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
            reizigers.add(reiziger);
        }
        mystmt.close();
        rs.close();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException{
        List l1 = new ArrayList<Reiziger>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM reiziger");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String voorletters = rs.getString(2);
            String tussenvoegsel = rs.getString(3);
            String achternaam = rs.getString(4);
            Date datum = rs.getDate(5);

            Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, datum);
            l1.add(reiziger);
        }
        rs.close();
        st.close();
        return l1;
    }
}
