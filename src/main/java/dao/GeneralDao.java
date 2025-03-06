package dao;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ConcurrentModificationException;

public class GeneralDao {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("wk7");


    public <T> void save(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    // notify users of concurrent modification conflicts.
    public void update(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
            em.close();
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException " + e.getMessage());
            em.getTransaction().rollback();
        }
    }
}
