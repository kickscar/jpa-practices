package ex01.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@ToString
@Entity
public class Pet {
    @Id
    private Integer id;

    private String name;

    private String owner;

    private String species;

    private Date birth;

    private Date dead;
}
