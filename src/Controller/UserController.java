/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserModel;
import Model.Admin;
import Model.Pelapor;
import Database.Database;
import java.sql.*;

/**
 *
 * @author ASUS
 */

public class UserController {
    private final Connection conn;
    private int currentUserId;

    public UserController() {
        conn = Database.connect();
    }
    
    public int getCurrentUserId() {
        return currentUserId;
    }

    public UserModel loginUser(String username, String password) {
        String queryUser = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement stmtUser = conn.prepareStatement(queryUser)) {
            stmtUser.setString(1, username);
            stmtUser.setString(2, password);
            ResultSet rsUser = stmtUser.executeQuery();

            if (rsUser.next()) {
                int userId = rsUser.getInt("id_user");
                currentUserId = userId;

                String queryAdmin = "SELECT * FROM admin WHERE id_user = ?";
                try (PreparedStatement stmtAdmin = conn.prepareStatement(queryAdmin)) {
                    stmtAdmin.setInt(1, userId);
                    ResultSet rsAdmin = stmtAdmin.executeQuery();

                    if (rsAdmin.next()) {
                        return new Admin(username, password, rsAdmin.getString("jabatan"));
                    }
                }

                String queryPelapor = "SELECT * FROM pelapor WHERE id_user = ?";
                try (PreparedStatement stmtPelapor = conn.prepareStatement(queryPelapor)) {
                    stmtPelapor.setInt(1, userId);
                    ResultSet rsPelapor = stmtPelapor.executeQuery();

                    if (rsPelapor.next()) {
                        return new Pelapor(username, password, rsPelapor.getString("nama_lengkap"),
                                           rsPelapor.getString("nomor_telepon"), rsPelapor.getString("email"));
                    }
                }
            } else {
                System.out.println("Login gagal: Username atau password salah");
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan SQL saat login: " + e.getMessage());
        }

        return null;
    }

    public boolean registerPelapor(Pelapor pelapor) {
        try {
            conn.setAutoCommit(false);

            String queryUser = "INSERT INTO user (username, password) VALUES (?, ?)";
            try (PreparedStatement stmtUser = conn.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS)) {
                stmtUser.setString(1, pelapor.getUsername());
                stmtUser.setString(2, pelapor.getPassword());
                stmtUser.executeUpdate();

                ResultSet generatedKeys = stmtUser.getGeneratedKeys();
                int userId;
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                    currentUserId = userId;
                } else {
                    throw new SQLException("Gagal mendapatkan user_id.");
                }

                String queryPelapor = "INSERT INTO pelapor (id_user, nama_lengkap, nomor_telepon, email) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmtPelapor = conn.prepareStatement(queryPelapor)) {
                    stmtPelapor.setInt(1, userId);
                    stmtPelapor.setString(2, pelapor.getNamaLengkap());
                    stmtPelapor.setString(3, pelapor.getNoTelp());
                    stmtPelapor.setString(4, pelapor.getEmail());
                    stmtPelapor.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Registrasi gagal: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan dalam pengaturan transaksi: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error mengatur ulang autocommit: " + e.getMessage());
            }
        }
    }
}