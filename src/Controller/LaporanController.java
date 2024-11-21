/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */

public class LaporanController {
    private final Connection conn;
    
    public LaporanController() {
        conn = Database.connect();
    }

    public boolean updateStatusLaporan(int laporanId, String statusBaru) {
        String query = "UPDATE laporan SET status_laporan = ? WHERE id_laporan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, statusBaru);
            stmt.setInt(2, laporanId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean tambahLaporan(String namaJalan, String kecamatan, String deskripsi, String tingkatKerusakan, String statusLaporan, int userId) {  
         String query = "INSERT INTO laporan (nama_jalan, kecamatan, tanggal_laporan, deskripsi, tingkat_kerusakan, status_laporan, user_id_user) "
                     + "VALUES (?, ?, NOW(), ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, namaJalan);
            stmt.setString(2, kecamatan);
            stmt.setString(3, deskripsi);
            stmt.setString(4, tingkatKerusakan);
            stmt.setString(5, statusLaporan);
            stmt.setInt(6, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan laporan: " + e.getMessage());
            return false;
        }
    }
    
    public boolean simpanLaporan(String namaJalan, String kecamatan, String deskripsi, String tingkatKerusakan, int userId) {
        String status = "Belum Diproses";
        
        LaporanController laporanController = new LaporanController();
        
        return laporanController.tambahLaporan(namaJalan, kecamatan, deskripsi, tingkatKerusakan, status, userId); 
    }
    
    public static ArrayList<Object[]> getAllLaporan() throws SQLException {
        ArrayList<Object[]> laporanList = new ArrayList<>();
        Connection conn = Database.connect();
        String query = "SELECT * FROM laporan";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            laporanList.add(new Object[]{
                rs.getInt("id_laporan"),
                rs.getString("nama_jalan"),
                rs.getString("kecamatan"),
                rs.getTimestamp("tanggal_laporan"),
                rs.getString("deskripsi"),
                rs.getString("tingkat_kerusakan"),
                rs.getString("status_laporan")
            });
        }

        rs.close();
        stmt.close();
        conn.close();
        return laporanList;
    }    

    public static ArrayList<Object[]> filterLaporanKerusakan(String tingkatKerusakan) throws SQLException {
        ArrayList<Object[]> laporanList = new ArrayList<>();
        Connection conn = Database.connect();

        String query;
        PreparedStatement stmt;

        if (tingkatKerusakan.equalsIgnoreCase("semua")) {
            query = "SELECT * FROM laporan";
            stmt = conn.prepareStatement(query);
        } else {
            query = "SELECT * FROM laporan WHERE tingkat_kerusakan = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, tingkatKerusakan);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            laporanList.add(new Object[]{
                rs.getInt("id_laporan"),
                rs.getString("nama_jalan"),
                rs.getString("kecamatan"),
                rs.getTimestamp("tanggal_laporan"),
                rs.getString("deskripsi"),
                rs.getString("tingkat_kerusakan"),
                rs.getString("status_laporan")
            });
        }

        rs.close();
        stmt.close();
        conn.close();
        return laporanList;
    }
    
    public static ArrayList<Object[]> filterLaporanStatus(String statusLaporan) throws SQLException {
        ArrayList<Object[]> laporanList = new ArrayList<>();
        Connection conn = Database.connect();

        String query;
        PreparedStatement stmt;

        if (statusLaporan.equalsIgnoreCase("semua")) {
            query = "SELECT pelapor.nama_lengkap, pelapor.email, laporan.nama_jalan, laporan.kecamatan, laporan.tanggal_laporan, laporan.deskripsi, laporan.tingkat_kerusakan, laporan.status_laporan " +
                    "FROM pelapor " +
                    "JOIN laporan ON pelapor.id_user = laporan.user_id_user";
            stmt = conn.prepareStatement(query);
        } else {
            query = "SELECT pelapor.nama_lengkap, pelapor.email, laporan.nama_jalan, laporan.kecamatan, laporan.tanggal_laporan, laporan.deskripsi, laporan.tingkat_kerusakan, laporan.status_laporan " +
                    "FROM pelapor " +
                    "JOIN laporan ON pelapor.id_user = laporan.user_id_user " +
                    "WHERE laporan.status_laporan = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, statusLaporan);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            laporanList.add(new Object[]{
                rs.getString("nama_lengkap"),
                rs.getString("email"),
                rs.getString("nama_jalan"),
                rs.getString("kecamatan"),
                rs.getTimestamp("tanggal_laporan"),
                rs.getString("deskripsi"),
                rs.getString("tingkat_kerusakan"),
                rs.getString("status_laporan")
            });
        }
        rs.close();
        stmt.close();
        conn.close();
        return laporanList;
    }
    
    public static ArrayList<Object[]> cariJalanAdmin(String namaJalan) throws SQLException {
        ArrayList<Object[]> laporanList = new ArrayList<>();
        Connection conn = Database.connect();
        String query = "SELECT pelapor.nama_lengkap, pelapor.email, laporan.nama_jalan, laporan.kecamatan, laporan.tanggal_laporan, laporan.deskripsi, laporan.tingkat_kerusakan, laporan.status_laporan " +
                        "FROM pelapor " +
                        "JOIN laporan ON pelapor.id_user = laporan.user_id_user " +
                        "WHERE laporan.nama_jalan LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + namaJalan + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            laporanList.add(new Object[]{
                rs.getString("nama_lengkap"),
                rs.getString("email"),
                rs.getString("nama_jalan"),
                rs.getString("kecamatan"),
                rs.getTimestamp("tanggal_laporan"),
                rs.getString("deskripsi"),
                rs.getString("tingkat_kerusakan"),
                rs.getString("status_laporan")
            });
        }

        rs.close();
        stmt.close();
        conn.close();
        return laporanList;
    }
    
    public static ArrayList<Object[]> cariJalanPelapor(String namaJalan) throws SQLException {
        ArrayList<Object[]> laporanList = new ArrayList<>();
        Connection conn = Database.connect();
        String query = "SELECT * FROM laporan WHERE nama_jalan LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + namaJalan + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            laporanList.add(new Object[]{
                rs.getString("id_laporan"),
                rs.getString("nama_jalan"),
                rs.getString("kecamatan"),
                rs.getTimestamp("tanggal_laporan"),
                rs.getString("deskripsi"),
                rs.getString("tingkat_kerusakan"),
                rs.getString("status_laporan")
            });
        }
        rs.close();
        stmt.close();
        conn.close();
        return laporanList;
    }  
}