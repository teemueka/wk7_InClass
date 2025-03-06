package dao;

import entity.Instructor;
import entity.Student;
import entity.TrainingSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class CriteriaDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("wk7");

    public List<Student> getStudentsSixMo() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        Timestamp sixMo = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        cq.select(root).where(cb.greaterThanOrEqualTo(root.get("joinDate"), sixMo));
        return em.createQuery(cq).getResultList();
    }

    public List<TrainingSession> searchSessionsByLocation(String location) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrainingSession> cq = cb.createQuery(TrainingSession.class);
        Root<TrainingSession> root = cq.from(TrainingSession.class);
        cq.select(root).where(cb.equal(root.get("location"), location));
        return em.createQuery(cq).getResultList();
    }

    public List<Instructor> getInstructorsWithExperience() {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
        Root<Instructor> root = cq.from(Instructor.class);
        cq.select(root).where(cb.greaterThanOrEqualTo(root.get("experienceYears"), 5));
        return em.createQuery(cq).getResultList();
    }
}
