package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String specialization;
    private int experienceYears;

    public Instructor() {}
    public Instructor(String name, String specialization, int experienceYears) {
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Instructor [id=" + id + ", name=" + name + ", specialization=" + specialization;
    }
}
