package ro.tuc.ds2020.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor")
public class Doctor {

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "myDoctor", cascade = CascadeType.ALL)
    private Set<Patient> patients = new HashSet<>();

    public Doctor(int id, String name, Date birthDate, String gender, String address, Set<Patient> patients) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.patients = patients;
    }

    public Doctor(){

    }

    public Doctor(int id, String name, String gender){
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public int getId() {
        return id;
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

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
