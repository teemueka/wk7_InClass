package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "training_session_id")
    private TrainingSession trainingSession;

    public Attendance() {}

    public Attendance(String status, String notes) {
        this.status = status;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Attendance [id=" + id + ", status=" + status + ", notes=" + notes + "]";
    }
}
