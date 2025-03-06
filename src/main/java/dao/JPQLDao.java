package dao;

import entity.Attendance;
import entity.Instructor;
import entity.Student;
import entity.TrainingSession;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JPQLDao {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("wk7");


    public List<TrainingSession> getStudentSessions(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<TrainingSession> query = em.createQuery(
                "SELECT a.trainingSession FROM Attendance a WHERE a.student.id = :id", TrainingSession.class
        );
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Student> getStudentsByRank(String rank) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT a FROM Student a WHERE a.rank = :rank", Student.class);
        query.setParameter("rank", rank);
        return query.getResultList();
    }

    public List<Instructor> getInstructorsSpecialization(String specialization) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Instructor> query = em.createQuery("Select i FROM Instructor i WHERE i.specialization = :specialization", Instructor.class);
        query.setParameter("specialization", specialization);
        return query.getResultList();

    }

    public List<Student> getStudentActive() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT a.student FROM ProgressReport a WHERE a.reportDate >= :threeMo", Student.class);
        query.setParameter("threeMo", Timestamp.valueOf(LocalDateTime.now().minusMonths(3)));
        return query.getResultList();
    }

}
