/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
/**
 *
 * @author farahaul
 */
public class Tugas {
    private String tugas;
    private String mataKuliah;
    private LocalDate deadline;

    public Tugas(String tugas, String mataKuliah, LocalDate deadline) {
        this.tugas = tugas;
        this.mataKuliah = mataKuliah;
        this.deadline = deadline;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
    }
    
    public String getTugas() {
        return tugas;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }
    
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
    
}
