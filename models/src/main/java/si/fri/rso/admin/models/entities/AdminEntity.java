package si.fri.rso.admin.models.entities;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "admins")
@NamedQueries(value =
        {
                @NamedQuery(name = "AdminEntity.getAll",
                        query = "SELECT no FROM AdminEntity no")
        })
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "roles")
    private String roles;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}