/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buku_tamu_perpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fadil
 */
public class model_tamu {

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost/buku_tamu";
    String user = "root";
    String password = "";

    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;

    boolean respons;

    public model_tamu() {
        try {
            Class.forName(jdbcDriver);
            System.out.println("driver load");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver tidak ditemukan");
            Logger.getLogger(model_tamu.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Berhasl Terkoneksi ke Database");
        } catch (SQLException ex) {
            System.out.println("Gagal Terkoneksi ke Database");
            Logger.getLogger(model_tamu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertTamu(String tanggal, String nim, String nama, String jurusan, int semester, int ruang_loket, String keperluan) {
        String query = "insert into tbl_tamu (Tanggal, NIM, Nama, Jurusan, Semester, Ruang_Loket, Keperluan) values (?,?,?,?,?,?,?)";

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, tanggal);
            pst.setString(2, nim);
            pst.setString(3, nama);
            pst.setString(4, jurusan);
            pst.setString(5, Integer.toString(semester));
            pst.setString(6, Integer.toString(ruang_loket));
            pst.setString(7, keperluan);
            pst.executeUpdate();
            respons = true;
            System.out.println("Sukses Insert Data");
        } catch (SQLException ex) {
            System.out.println("Gagal Insert Data");
            Logger.getLogger(model_tamu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respons;
    }

    public ResultSet getAllTamu() {
        String query = "select * from tbl_tamu";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(model_tamu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public boolean updateTamu(String tanggal, String nama, String jurusan, int semester, int ruang_loket, String keperluan, String nim) {
        String query = "update tbl_tamu set Tanggal = ?, Nama = ?, Jurusan = ?, Semester = ?, Ruang_Loket = ?, Keperluan = ? where NIM = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, tanggal);
            pst.setString(2, nama);
            pst.setString(3, jurusan);
            pst.setString(4, Integer.toString(semester));
            pst.setString(5, Integer.toString(ruang_loket));
            pst.setString(6, keperluan);
            pst.setString(7, nim);
            pst.executeUpdate();
            
            respons = true;
            System.out.println("Data Berhasil di Update");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("Data Gagal di Update");
            Logger.getLogger(model_tamu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respons;
    }
    
    public void hapusTamu(String nim){
        String query = "delete from tbl_tamu where NIM = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nim);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(model_tamu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
