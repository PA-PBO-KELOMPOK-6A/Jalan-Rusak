/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Pelapor extends UserModel {
    private String namaLengkap;
    private String noTelp;
    private String email;
    private Connection conn;

    public Pelapor(String username, String password, String namaLengkap, String noTelp, String email) {
        super(username, password);
        this.namaLengkap = namaLengkap;
        this.noTelp = noTelp;
        this.email = email;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public ArrayList<Object[]> viewData() {
        ArrayList<Object[]> laporanList = new ArrayList<>();
        conn = Database.connect(); 
        try {
            String query = "SELECT * FROM laporan";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(); 

            while (rs.next()) { 
                Object[] laporan = new Object[7];
                laporan[0] = rs.getString("id_laporan");
                laporan[1] = rs.getString("nama_jalan");
                laporan[2] = rs.getString("kecamatan");
                laporan[3] = rs.getTimestamp("tanggal_laporan");
                laporan[4] = rs.getString("deskripsi");
                laporan[5] = rs.getString("tingkat_kerusakan");
                laporan[6] = rs.getString("status_laporan");
                laporanList.add(laporan); 
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close(); 
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return laporanList; 
    }
}
