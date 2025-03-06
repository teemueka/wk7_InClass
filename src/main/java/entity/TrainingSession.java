package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp date;
    private String location;
    private double duration;

    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL)
    private Set<Attendance> attendances = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public TrainingSession() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public TrainingSession(String location, double duration) {
        this.date = new Timestamp(System.currentTimeMillis());
        this.location = location;
        this.duration = duration;
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    @Override
    public String toString() {
        return "TrainingSession [id=" + id + ", date=" + date + ", location=" + location + ", duration=" + duration + ", attendances=" + attendances + "]";
    }

    @PrePersist
    protected void onCreate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

}
