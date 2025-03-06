package application;

import dao.CriteriaDao;
import dao.GeneralDao;
import dao.JPQLDao;
import entity.*;
import java.util.List;

public class AikidoApp {
    public static void main(String[] args) {

        GeneralDao generalDao = new GeneralDao();
        JPQLDao jpqlDao = new JPQLDao();
        CriteriaDao criteriaDao = new CriteriaDao();

       // populate db

        Student s1 = new Student("goodname", "good@email", "novice");
        ProgressReport pr = new ProgressReport("good achievements", "no improvements");
        pr.setStudent(s1);
        Instructor i1 = new Instructor("goodInsName", "everything", 100);
        TrainingSession ts = new TrainingSession("Helsinki", 2.5);
        ts.setInstructor(i1);
        Attendance a1 = new Attendance("present", "good notes");
        a1.setStudent(s1);
        a1.setTrainingSession(ts);

        generalDao.save(s1);
        generalDao.save(pr);
        generalDao.save(i1);
        generalDao.save(ts);
        generalDao.save(a1);

        // JPQL Queries
        List<TrainingSession> studentSessions = jpqlDao.getStudentSessions(1);
        List<Student> noviceStudents = jpqlDao.getStudentsByRank("novice");
        List<Instructor> aikidoInstructors = jpqlDao.getInstructorsSpecialization("everything");
        List<Student> activeStudents = jpqlDao.getStudentActive();

        System.out.println("\nJPQL QUERIES");
        System.out.println("Training sessions attended by student 1: " + studentSessions);
        System.out.println("All students who have a novice rank: " + noviceStudents);
        System.out.println("All instructors specialized in an 'everything' Aikido technique: " + aikidoInstructors);
        System.out.println("Students with progress reports in the last three months: " + activeStudents);

        // Criteria API Queries
        List<Student> sixMoStudents = criteriaDao.getStudentsSixMo();
        List<TrainingSession> sessionsHelsinki = criteriaDao.searchSessionsByLocation("Helsinki");
        List<Instructor> instructorsFiveYears = criteriaDao.getInstructorsWithExperience();

        System.out.println("\nCriteria API queries");
        System.out.println("students who joined within the last six months. " + sixMoStudents);
        System.out.println("training sessions held in Helsinki." + sessionsHelsinki);
        System.out.println("all instructors with more than five years of experience." + instructorsFiveYears);

    }
}
