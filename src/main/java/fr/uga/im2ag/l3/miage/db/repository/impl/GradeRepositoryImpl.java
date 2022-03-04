package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.Subject;

import javax.persistence.EntityManager;
import java.util.List;

public class GradeRepositoryImpl extends BaseRepositoryImpl implements GradeRepository {

    /**
     * Build a base repository
     *
     * @param entityManager the entity manager
     */
    public GradeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Grade> findHighestGrades(int limit) {
        List<Grade> grades = null;
        grades = entityManager.createNativeQuery("select * from (select * from Grade order by value DESC) where rownum <=" + limit, Grade.class).getResultList();
        return grades;
    }

    @Override
    public List<Grade> findHighestGradesBySubject(int limit, Subject subject) {
        List<Grade> grades = null;
        grades = entityManager.createNativeQuery("select * from (select * from Grade order by value DESC where subject = '" + subject +"') where rownum <=" + limit, Grade.class).getResultList();
        return grades;
    }

    @Override
    public void save(Grade entity) {
       entityManager.persist(entity);
    }

    @Override
    public void delete(Grade entity) {
        entityManager.remove(entity);
    }

    @Override
    public Grade findById(Long id) {
        return null;
    }

    @Override
    public List<Grade> getAll() {
        List<Grade> grades = null;
        grades = entityManager.createNativeQuery("select * from Grade", Grade.class).getResultList();
        return grades;
    }
}
