package ex02.repository;

import ex02.domain.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Slf4j
@Repository
public class PetRepository {

    @PersistenceContext
    public EntityManager em;

    public void save(Pet pet) {
        em.persist(pet);
    }
}
