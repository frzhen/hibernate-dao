package guru.ysy.hibernatedao.domain;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zhenrui on 2021/11/28 11:47
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "author_fina_all", query = "SELECT a FROM Author a"),
        @NamedQuery(name = "fina_by_name",
                query = "SELECT a FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name")
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
