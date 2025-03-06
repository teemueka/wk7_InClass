package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class ProgressReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp reportDate;
    private String achievements;
    private String areasForImprovement;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Version
    private int version;


    public ProgressReport() {
        this.reportDate = new Timestamp(System.currentTimeMillis());
    }
    public ProgressReport(String achievements, String areasForImprovement) {
        this.achievements = achievements;
        this.areasForImprovement = areasForImprovement;
        this.reportDate = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "ProgressReport [id=" + id + ", reportDate=" + reportDate + ", achievements=" + achievements + ",areasForImprovement=" + areasForImprovement + ", Student=" + student + "]";
    }

    @PrePersist
    protected void onCreate() {
        this.reportDate = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.reportDate = new Timestamp(System.currentTimeMillis());
    }

}
