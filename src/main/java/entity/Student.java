package entity;

import converter.RankConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @Convert(converter = RankConverter.class)
    private String rank;
    private Timestamp joinDate;

    @Transient
    private int totalAttendances;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Attendance> attendances = new HashSet<>();

    public Student() {
        this.joinDate = new Timestamp(System.currentTimeMillis());
    }

    public Student(String name, String email, String rank) {
        this.name = name;
        this.email = email;
        this.rank = rank;
        this.joinDate = new Timestamp(System.currentTimeMillis());
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", rank=" + rank + ", joinDate=" + joinDate + "]";
    }

    @PrePersist
    protected void onCreate() {
        this.joinDate = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.joinDate = new Timestamp(System.currentTimeMillis());
    }

    @PostLoad
    protected void onLoad() {
        this.totalAttendances = attendances.size();
    }
}
