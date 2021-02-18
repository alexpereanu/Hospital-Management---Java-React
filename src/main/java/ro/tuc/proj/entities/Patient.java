package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private Date birthDate;

    @Column
    private String gender;

    @Column
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<MedicalRecord> patientMedicalRecords = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Doctor myDoctor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "caregiver_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Caregiver myCaregiver;



    public Patient(){

    }

    public Patient(int id, String name, Date birthDate, String gender, String address, Set<MedicalRecord> patientMedicalRecords, Doctor myDoctor, Caregiver myCaregiver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.patientMedicalRecords = patientMedicalRecords;
        this.myDoctor = myDoctor;
        this.myCaregiver = myCaregiver;
    }

    public Patient(int id, String name, String gender, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public Caregiver getMyCaregiver() {
        return myCaregiver;
    }

    public void setMyCaregiver(Caregiver myCaregiver) {
        this.myCaregiver = myCaregiver;
    }

    public int getId() {
        return id;
    }

    public Doctor getMyDoctor() {
        return myDoctor;
    }

    public void setMyDoctor(Doctor myDoctor) {
        this.myDoctor = myDoctor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<MedicalRecord> getPatientMedicalRecords() {
        return patientMedicalRecords;
    }

    public void setPatientMedicalRecords(Set<MedicalRecord> patientMedicalRecords) {
        this.patientMedicalRecords = patientMedicalRecords;
    }

    public void add(Link patientLink) {
    }
}
