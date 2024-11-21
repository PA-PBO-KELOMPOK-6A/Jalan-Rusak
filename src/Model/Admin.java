/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Admin extends UserModel {
    private String jabatan;
    private Connection conn;

    public Admin(String username, String password, String jabatan) {
        super(username, password);
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    

    @Override
    public ArrayList<Object[]> viewData() {
    ArrayList<Object[]> laporanList = new ArrayList<>();
        conn = Database.connect(); 
        try {
            String query = "SELECT pelapor.nama_lengkap, pelapor.email, laporan.nama_jalan, laporan.kecamatan, laporan.tanggal_laporan, laporan.deskripsi, laporan.tingkat_kerusakan, laporan.status_laporan " +
                           "FROM pelapor " +
                           "JOIN laporan ON pelapor.id_user = laporan.user_id_user";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(); 

            while (rs.next()) { 
                Object[] laporan = new Object[8];
                laporan[0] = rs.getString("nama_lengkap");
                laporan[1] = rs.getString("email");
                laporan[2] = rs.getString("nama_jalan");
                laporan[3] = rs.getString("kecamatan");
                laporan[4] = rs.getTimestamp("tanggal_laporan");
                laporan[5] = rs.getString("deskripsi");
                laporan[6] = rs.getString("tingkat_kerusakan");
                laporan[7] = rs.getString("status_laporan");
                laporanList.add(laporan); 
            }

            rs.close();
            stmt.close(); 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return laporanList;
    }
}
