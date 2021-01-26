package PeminjamanUang.oop;

import java.util.ArrayList;

public class Syariah extends PeminjamanUang{
    
    public Syariah(Integer KTPPeminjam, String NamaPeminjam, String Alamat, 
            Integer NoTelp, ArrayList<Nominal> nominals) {
        super(KTPPeminjam, NamaPeminjam, Alamat, NoTelp, nominals);
    }
    
    public Syariah(Integer KTPPeminjam, String NamaPeminjam, String Alamat, 
            Integer NoTelp, Nominal nominal) {
        super(KTPPeminjam, NamaPeminjam, Alamat, NoTelp, nominal);
    }
}
