package ro.tuc.ds2020.entities;

import com.sun.istack.Nullable;

import javax.persistence.*;


@Entity
@Table(name = "medication")
public class Medication {

    @Id
    private int id;

    @Column
    private String medicamentName;

    @Column
    private String sideEffects;

    @Column
    private int dosage;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "listOfMedicaments", cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "medicalrecord_id")
    @Transient
    @Nullable
    private MedicalRecord medicalRecord;


    public Medication(){

    }

    public Medication(int id, String medicamentName, String sideEffects, int dosage, MedicalRecord medicalRecord) {
        this.id = id;
        this.medicamentName = medicamentName;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.medicalRecord = medicalRecord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicamentName() {
        return medicamentName;
    }

    public void setMedicamentName(String medicamentName) {
        this.medicamentName = medicamentName;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
