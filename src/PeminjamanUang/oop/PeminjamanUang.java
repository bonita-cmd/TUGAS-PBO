package PeminjamanUang.oop;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class PeminjamanUang {
    private IntegerProperty KTPPeminjam;
    private StringProperty NamaPeminjam;
    private StringProperty Alamat;
    private IntegerProperty NoTelp;
    private ArrayList<Nominal> nominals;

    public PeminjamanUang(Integer KTPPeminjam, String NamaPeminjam, 
            String Alamat, Integer NoTelp, ArrayList<Nominal> nominals) {
        this.KTPPeminjam = new SimpleIntegerProperty(KTPPeminjam);
        this.NamaPeminjam = new SimpleStringProperty(NamaPeminjam);
        this.Alamat = new SimpleStringProperty(Alamat);
        this.NoTelp = new SimpleIntegerProperty(NoTelp);
        this.nominals = nominals;
    }
    
    public PeminjamanUang(Integer KTPPeminjam, String NamaPeminjam, 
            String Alamat, Integer NoTelp, Nominal nominal) {
        nominals = new ArrayList<>();
        this.KTPPeminjam = new SimpleIntegerProperty(KTPPeminjam);
        this.NamaPeminjam = new SimpleStringProperty(NamaPeminjam);
        this.Alamat = new SimpleStringProperty(Alamat);
        this.NoTelp = new SimpleIntegerProperty(NoTelp);
        this.nominals = nominals;
    }

    public Integer getKTPPeminjam() {
        return KTPPeminjam.get();
    }

    public void setKTPPeminjam(Integer KTPPeminjam) {
        this.KTPPeminjam.set(KTPPeminjam);
    }

    public String getNamaPeminjam() {
        return NamaPeminjam.get();
    }

    public void setNamaPeminjam(String NamaPeminjam) {
        this.NamaPeminjam.set(NamaPeminjam);
    }

    public String getAlamat() {
        return Alamat.get();
    }

    public void setAlamat(String Alamat) {
        this.Alamat.set(Alamat);
    }

    public Integer getNoTelp() {
        return NoTelp.get();
    }

    public void setNoTelp(Integer NoTelp) {
        this.NoTelp.set(NoTelp);
    }

    public ArrayList<Nominal> getNominals() {
        return nominals;
    }

    public void setNominals(ArrayList<Nominal> nominals) {
        this.nominals = nominals;
    }
    
    public IntegerProperty getKTPPeminjamProperty() {
        return KTPPeminjam;
    }
    
    public StringProperty getNamaPeminjamProperty() {
        return NamaPeminjam;
    }
    
    public StringProperty getAlamatProperty() {
        return Alamat;
    }
    
    public IntegerProperty getNoTelpProperty() {
        return NoTelp;
    }
}
