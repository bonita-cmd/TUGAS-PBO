package PeminjamanUang.oop;

import PeminjamanUang.oop.db.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {
    public final Connection conn;

    public DataModel(String driver) throws SQLException {
        this.conn = DBHelper.getConnection(driver);
    }
    public void Tambah_Peminjaman(Syariah peminjam) throws SQLException {
        String insertPeminjam = "INSERT INTO peminjaman_uang (ktp_peminjam, nama_peminjam, alamat, no_telp)" 
                + " VALUES (?, ?, ?, ?)";
        String insertSyariah = "INSERT INTO syariah (ktp_peminjam)"
                + " VALUES (?)";
        String insertNominal = "INSERT INTO nominal (id_peminjaman, nominal, ktp_peminjam)"
                + " VALUES (?, ?, ?)";

        PreparedStatement stmtPeminjam = conn.prepareStatement(insertPeminjam);
        stmtPeminjam.setInt(1, peminjam.getKTPPeminjam());
        stmtPeminjam.setString(2, peminjam.getNamaPeminjam());
        stmtPeminjam.setString(3, peminjam.getAlamat());
        stmtPeminjam.setInt(4, peminjam.getNoTelp());
        stmtPeminjam.execute();

        PreparedStatement stmtSyariah = conn.prepareStatement(insertSyariah);
        stmtSyariah.setInt(1, peminjam.getKTPPeminjam());
        stmtSyariah.execute();

        PreparedStatement stmtNominal = conn.prepareStatement(insertNominal);
        stmtNominal.setInt(1, peminjam.getNominals().get(0).getIDPeminjaman());
        stmtNominal.setInt(2, peminjam.getNominals().get(0).getNominal());
        stmtNominal.setInt(3, peminjam.getKTPPeminjam());
        stmtNominal.execute();
    }

    public ObservableList<Syariah> getSyariah() {
        ObservableList<Syariah> data = FXCollections.observableArrayList();
        String sql = "SELECT ktp_peminjam, nama_peminjam, alamat, no_telp "
                + "FROM peminjaman_uang NATURAL JOIN syariah "
                + "ORDER BY `nama_peminjam";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String sqlNominal = "SELECT id_peminjaman, nominal FROM nominal "
                        + "WHERE id_peminjaman=" + rs.getInt(1);
                ResultSet rsNominal = conn.createStatement().executeQuery(sqlNominal);
                ArrayList<Nominal> Nominal = new ArrayList<>();
                while (rsNominal.next()) {
                    Nominal.add(new Nominal(rsNominal.getInt(1), rsNominal.getInt(2)));
                }
                data.add(new Syariah(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), Nominal));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public ObservableList<Nominal> getNominal(int KTPPeminjam){
        ObservableList<Nominal> data = FXCollections.observableArrayList();
        String sqlNominal = "SELECT id_peminjaman, nominal FROM nominal "
                        + "WHERE ktp_peminjam=" + KTPPeminjam;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sqlNominal);
            while(rs.next()){
                data.add(new Nominal(rs.getInt(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    public void tambahNominal(int ktp_peminjam, Nominal data) throws SQLException {
        String insertPeminjam = "INSERT INTO nominal (ktp_peminjam, id_peminjaman, "
                + "nominal)" 
                + " VALUES (?, ?, ?)";

        PreparedStatement stmtPeminjaman = conn.prepareStatement(insertPeminjam);
        stmtPeminjaman.setInt(1, ktp_peminjam);
        stmtPeminjaman.setInt(2, data.getIDPeminjaman());
        stmtPeminjaman.setInt(3, data.getNominal());
        stmtPeminjaman.execute();
    }
}
