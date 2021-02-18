package ro.tuc.ds2020.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "medicalrecord")
public class MedicalRecord {

    @Id
    private int id;

    @Column
    private String medicalCondition;

    @Column
    private int intakeIntervals;

    @Column
    private int daysOfTreatment;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "medication_id")
    @Nullable
    private Medication listOfMedicaments;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;

    public MedicalRecord(){

    }

    public MedicalRecord(int id, String medicalCondition, int intakeIntervals, int daysOfTreatment, Medication listOfMedicaments, Patient patient) {
        this.id = id;
        this.medicalCondition = medicalCondition;
        this.intakeIntervals = intakeIntervals;
        this.daysOfTreatment = daysOfTreatment;
        this.listOfMedicaments = listOfMedicaments;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public int getIntakeIntervals() {
        return intakeIntervals;
    }

    public void setIntakeIntervals(int intakeIntervals) {
        this.intakeIntervals = intakeIntervals;
    }

    public int getDaysOfTreatment() {
        return daysOfTreatment;
    }

    public void setDaysOfTreatment(int daysOfTreatment) {
        this.daysOfTreatment = daysOfTreatment;
    }

    public Medication getListOfMedicaments() {
        return listOfMedicaments;
    }

    public void setListOfMedicament(Medication listOfMedicaments) {
        this.listOfMedicaments = listOfMedicaments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setListOfMedicaments(Medication listOfMedicaments) {
        this.listOfMedicaments = listOfMedicaments;
    }
}
