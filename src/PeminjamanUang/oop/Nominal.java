package PeminjamanUang.oop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Nominal {
    IntegerProperty IDPeminjaman;
    IntegerProperty Nominal;

    public Nominal(Integer IDPeminjaman, Integer Nominal) {
        this.IDPeminjaman = new SimpleIntegerProperty(IDPeminjaman);
        this.Nominal = new SimpleIntegerProperty(Nominal);
    }

    public Integer getIDPeminjaman() {
        return IDPeminjaman.get();
    }

    public void setIDPeminjaman(Integer IDPeminjaman) {
        this.IDPeminjaman.set(IDPeminjaman);
    }

    public Integer getNominal() {
        return Nominal.get();
    }

    public void setNominal(Integer Nominal) {
        this.Nominal.set(Nominal);
    }
    
    public IntegerProperty getIDPeminjamanProperty() {
        return IDPeminjaman;
    }
    
    public IntegerProperty getNominalProperty() {
        return Nominal;
    }
}
