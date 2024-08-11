package ex02.repository;

import ex02.domain.Board;
import lombok.*;
import ex02.domain.User;
import ex02.domain.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@RequiredArgsConstructor
@Repository
public class JpqlUserRepository {

    @NonNull
    private EntityManager em;

    // 저장: 영속화
    public void save(User user) {
        em.persist(user);
    }

    // 조회01: Fetch One: 영속객체(o)
    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    // 삭제01: 영속객체(o)
    public void delete01(Integer id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    // 삭제02: JPQL: 영속객체(x)
    public Integer delete02(Integer id) {
        String qlString = "delete from User u where u.id=:id";
        Query query = em.createQuery(qlString);
        query.setParameter("id", id);

        return query.executeUpdate();
    }

    // 조회2: Fetch One: 영속객체(x): Projecttion with Embeded Object(DTO)
    public UserDto findByEmailAndPassword(String email, String password) {
        String qlString = "select new ex02.domain.dto.UserDto(u.id, u.name) from User u where u.email=:email and u.password=:password";
        TypedQuery<UserDto> query = em.createQuery(qlString, UserDto.class);

        query.setParameter("email", email);
        query.setParameter("password", password);

        return query.getSingleResult();
    }

    // 수정01: 영속객체(o)
    public User update01(User user) {
        User userPersisted = em.find(User.class, user.getId());

        if (userPersisted != null) {
            userPersisted.setRole(user.getRole());
            userPersisted.setGender(user.getGender());
            userPersisted.setEmail(user.getEmail());
            userPersisted.setName(user.getName());
            userPersisted.setPassword(user.getPassword());
        }

        return userPersisted;
    }

    // 수정02: JPQL: 영속객체(x)
    public Integer update02(User user) {
        Query query = em.createQuery("update User u set u.role=:role, u.gender=:gender, u.email=:email, u.name=:name, u.password=:password where u.id=:id");

        query.setParameter("id", user.getId());
        query.setParameter("role", user.getRole());
        query.setParameter("gender", user.getGender());
        query.setParameter("email", user.getEmail());
        query.setParameter("name", user.getName());
        query.setParameter("password", user.getPassword());

        return query.executeUpdate();
    }

    // count
    public Long count() {
        String qlString = "select count(u) from User u";
        TypedQuery<Long> query = em.createQuery(qlString, Long.class);
        return query.getSingleResult();
    }
}