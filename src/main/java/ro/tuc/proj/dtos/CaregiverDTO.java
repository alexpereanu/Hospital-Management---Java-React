package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Caregiver;

import java.util.Objects;

public class CaregiverDTO extends RepresentationModel<CaregiverDTO> {

    private int id;
    private String name;
    private String gender;

    public CaregiverDTO(int id, String name, String gender) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CaregiverDTO that = (CaregiverDTO) o;
        return id == that.id &&
                name.equals(that.name) &&
                gender.equals(that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, gender);
    }
}
