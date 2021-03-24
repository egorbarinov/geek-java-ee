package ru.geekbrains.persist;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dto.RoleDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Data
@NamedQueries({
        @NamedQuery(name = "deleteRoleById", query = "delete from Role r where r.id = :id"),
})
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //TODO
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<User> users;

    public Role(@NotNull String name) {
        this.name = name;
    }

    public Role(RoleDto r) {
        this.id = r.getId();
        this.name = r.getName();
    }

}
