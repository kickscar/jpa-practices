package ex02.repository;


import ex02.domain.Emaillist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Slf4j
@Repository
public class EmaillistRepository {

    @PersistenceContext
    public EntityManager em;

    public void save(Emaillist email) {
        em.persist(email);
    }
}
