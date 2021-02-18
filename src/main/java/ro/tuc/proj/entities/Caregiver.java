package ro.tuc.ds2020.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "caregiver")
public class Caregiver {

    @Id

    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private Date birthdate;

    @Column
    private String gender;

    @Column
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "myCaregiver", cascade = CascadeType.ALL)
    private Set<Patient> patients = new HashSet<>();

    public Caregiver(){

    }

    public Caregiver(int id, String name, Date birthdate, String gender, String address, Set<Patient> patients) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.patients = patients;
    }

    public Caregiver(int id, String name, String gender){
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
