/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Tugas;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author farahaul
 */
public class TrackerController implements Initializable {

    @FXML
    private TextField txtTugas;
    @FXML
    private TextField txtMataKuliah;
    @FXML
    private DatePicker datePickerDeadline;
    @FXML
    private TableView<Tugas> tvTugas;
    @FXML
    private TableColumn<Tugas, String> colTugas;
    @FXML
    private TableColumn<Tugas, String> colMataKuliah;
    @FXML
    private TableColumn<Tugas, LocalDate> colDeadline;

    private ObservableList<Tugas> dataTugas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataTugas = FXCollections.observableArrayList();

        colTugas.setCellValueFactory(new PropertyValueFactory<>("tugas"));
        colMataKuliah.setCellValueFactory(new PropertyValueFactory<>("mataKuliah"));
        colDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
    
        //Bind data ke TableView
        tvTugas.setItems(dataTugas);
        
        //Tambahkan Listener untuk menampilkan detail saat baris dipilih
        tvTugas.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showTugasDetails(newValue));
    }    

        private void showTugasDetails(Tugas tugas) {
        if (tugas != null) {
            txtTugas.setText(tugas.getTugas());
            txtMataKuliah.setText(tugas.getMataKuliah());
            datePickerDeadline.setValue(tugas.getDeadline());
        } else {
            txtTugas.setText("");
            txtMataKuliah.setText("");
            datePickerDeadline.setValue(null);
        }
    }
        
    @FXML
    private void handleAddTugas(ActionEvent event) {
        String tugas = txtTugas.getText();
        String mataKuliah = txtMataKuliah.getText();
        LocalDate deadline = datePickerDeadline.getValue();

        if (tugas.isEmpty() || mataKuliah.isEmpty() || deadline == null) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Tugas, Mata Kuliah dan Deadline tidak boleh kosong.");
            return;
        }

        Tugas baru = new Tugas(tugas, mataKuliah, deadline);
        dataTugas.add(baru);
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Tugas berhasil ditambahkan.");
    }

    @FXML
    private void handleDeleteTugas(ActionEvent event) {
        Tugas selectedTugas = tvTugas.getSelectionModel().getSelectedItem();

        if (selectedTugas != null) {
            dataTugas.remove(selectedTugas);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Tugas berhasil dihapus.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih Tugas yang ingin dihapus.");
        }
    }

    @FXML
    private void handleEditTugas(ActionEvent event) {
        Tugas selectedTugas = tvTugas.getSelectionModel().getSelectedItem();

        if (selectedTugas != null) {
            // Update object model menggunakan setter biasa
            selectedTugas.setTugas(txtTugas.getText()); 
            selectedTugas.setMataKuliah(txtMataKuliah.getText());
            selectedTugas.setDeadline(datePickerDeadline.getValue());

            tvTugas.refresh();
            
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Tugas berhasil diubah.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih Tugas yang ingin diubah.");
        }
    }

    private void clearFields() {
        txtTugas.clear();
        txtMataKuliah.clear();
        datePickerDeadline.setValue(null);
        tvTugas.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
