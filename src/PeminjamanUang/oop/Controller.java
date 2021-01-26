package PeminjamanUang.oop;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable {

    @FXML
    private TextField tfktp;

    @FXML
    private TextField tfnama;

    @FXML
    private TextField tfalamat;

    @FXML
    private TextField tftelp;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfnominal;

    @FXML
    private Button btnpinjam;

    @FXML
    private Button btnreload;

    @FXML
    private Button btnclear;

    @FXML
    private TableView<Syariah> tblpeminjam;

    @FXML
    private TableColumn<Syariah, Integer> colktp;

    @FXML
    private TableColumn<Syariah, String> colnama;

    @FXML
    private TableColumn<Syariah, String> colalamat;

    @FXML
    private TableColumn<Syariah, Integer> coltelp;

    @FXML
    private TableView<Nominal> tblpeminjaman;

    @FXML
    private TableColumn<Nominal, Integer> colid;

    @FXML
    private TableColumn<Nominal, Integer> colnominal;

    @FXML
    private TextField tfnewktp;

    @FXML
    private TextField tfnewid;

    @FXML
    private TextField tfnewnominal;

    @FXML
    private Button btnnewpinjam;

    private DataModel dm;

    @FXML
    void handleButtonClear(ActionEvent event) {
        tfktp.setText("");
        tfnama.setText("");
        tfalamat.setText("");
        tftelp.setText("");
        tfid.setText("");
        tfnominal.setText("");
        tfnewid.setText("");
        tfnewnominal.setText("");
    }

    @FXML
    void handleButtonPinjam(ActionEvent event) {
        Syariah syariah = new Syariah(Integer.parseInt(tfktp.getText()),
                tfnama.getText(), tfalamat.getText(), Integer.parseInt(tftelp.getText()), new Nominal(
                Integer.parseInt(tfid.getText()), Integer.parseInt(tfnominal.getText())));
        try {
            dm.Tambah_Peminjaman(syariah);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleButtonReload(ActionEvent event) {
        ObservableList<Syariah> syariah = dm.getSyariah();
        colktp.setCellValueFactory(new PropertyValueFactory<>("KTPPeminjam"));
        colnama.setCellValueFactory(new PropertyValueFactory<>("NamaPeminjam"));
        colalamat.setCellValueFactory(new PropertyValueFactory<>("Alamat"));
        coltelp.setCellValueFactory(new PropertyValueFactory<>("NoTelp"));
        tblpeminjam.setItems(null);
        tblpeminjam.setItems(syariah);
        btnnewpinjam.setDisable(true);
    }

    @FXML
    void handleButtonNewPinjam(ActionEvent event) {
        Nominal nominal = new Nominal(Integer.parseInt(tfnewid.getText()), Integer.parseInt(tfnewnominal.getText()));
        try {
            dm.tambahNominal(Integer.parseInt(tfnewktp.getText()), nominal);
            viewDataPinjaman(Integer.parseInt(tfnewktp.getText()));
            btnclear.fire();
            btnreload.fire();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            dm = new DataModel("MYSQL");
            btnclear.fire();
            btnreload.fire();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblpeminjam.getSelectionModel().selectedIndexProperty().addListener(listener -> {
            if (tblpeminjam.getSelectionModel().getSelectedItem() != null) {
                Syariah syariah = tblpeminjam.getSelectionModel().getSelectedItem();
                viewDataPinjaman(syariah.getKTPPeminjam());
                btnnewpinjam.setDisable(false);
                tfnewktp.setText("" + syariah.getKTPPeminjam());
            }
        });
    }

    public void viewDataPinjaman(int ktp_peminjam) {
        ObservableList<Nominal> nominal = dm.getNominal(ktp_peminjam);
        colid.setCellValueFactory(new PropertyValueFactory<>("IDPeminjaman"));
        colnominal.setCellValueFactory(new PropertyValueFactory<>("Nominal"));
        tblpeminjaman.setItems(null);
        tblpeminjaman.setItems(nominal);
    }
}
