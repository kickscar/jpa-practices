package ex01.repository;

import ex01.domain.Guestbook;
import jpa.utils.JpaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JpqlGuestbookRepository {
    @PersistenceContext
    private EntityManager em;

    public Optional<Guestbook> findById(Integer id) {
        String jpql = "select gb from Guestbook gb where gb.id = ?1";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);

        query.setParameter(1, id);
        return Optional.ofNullable(query.getSingleResult());
    }

    public <T> Optional<T> findById(Integer id, Class<T> dtoClass) {
        TypedQuery<T> query = JpaUtils.queryProjectionFindById(em, Guestbook.class, dtoClass);
        query.setParameter("id", id);

        return Optional.ofNullable(query.getSingleResult());
    }

    public List<Guestbook> findAll() {
        String jpql = "select gb from Guestbook gb";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);
        return query.getResultList();
    }

    public List<Guestbook> findAll(Sort.Order... orders) {
        String jpql = "select gb from Guestbook gb";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);
        return query.getResultList();
    }

    public <T> List<T> findAll(Class<T> dtoClass) {
        TypedQuery<T> query = JpaUtils.queryProjectionFindAll(em, Guestbook.class, dtoClass);

        return query.getResultList();
    }

    public List<Guestbook> findAllOrderByRegDateDesc() {
        String jpql = "select gb from Guestbook gb order by gb.regDate desc";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);

        return query.getResultList();
    }

    public List<Guestbook> findTopKOrderByRegDateDesc(int page, int k) {
        String jpql = "select gb from Guestbook gb order by gb.regDate desc";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);

        query.setFirstResult((page - 1) * k); // offset
        query.setMaxResults(k);

        return query.getResultList();
    }

    public List<Guestbook> findTopKByNameOrderByRegDateDesc(String name, int page, int k) {
        String jpql = "select gb from Guestbook gb where gb.name = :name order by b.regDate desc";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);

        query.setParameter("name", name);
        query.setFirstResult((page - 1) * k); // offset
        query.setMaxResults(k);

        return query.getResultList();
    }

    public List<Guestbook> findTopKByContentsContainisOrderByRegDateDesc(String contents, int page, int k) {
        String jpql = "select gb from Guestbook gb where gb.contents like :contentsContainis order by b.regDate desc";
        TypedQuery<Guestbook> query = em.createQuery(jpql, Guestbook.class);

        query.setParameter("contentsContainis", "%" + contents + "%");
        query.setFirstResult((page - 1) * k); // offset
        query.setMaxResults(k);

        return query.getResultList();
    }

    public int update(Guestbook argGuestbook) {
        return JpaUtils.queryUpdate(em, argGuestbook).executeUpdate();
    }

    public int deleteById(Integer id) {
        String jpql = "delete from Guestbook gb where gb.id=:id";
        Query query = em.createQuery(jpql);

        query.setParameter("id", id);
        return query.executeUpdate();
    }

    public int deleteByIdAndPassword(Integer id, String password) {
        String jpql = "delete from Guestbook gb where gb.id=:id and gb.password=:password";
        Query query = em.createQuery(jpql);

        query.setParameter("id", id);
        query.setParameter("password", password);
        return query.executeUpdate();
    }

    public Long count() {
        String jpql = "select count(*) from Guestbook";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);

        return query.getSingleResult();
    }
}