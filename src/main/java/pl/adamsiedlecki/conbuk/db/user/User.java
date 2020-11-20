package pl.adamsiedlecki.conbuk.db.user;

import pl.adamsiedlecki.conbuk.db.concept.Concept;
import pl.adamsiedlecki.conbuk.db.user.userRole.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRole> roles;
    @ManyToMany(mappedBy = "likeUsers")
    private List<Concept> likeConcepts = new ArrayList<>();
    @ManyToMany(mappedBy = "dislikeUsers")
    private List<Concept> dislikeConcepts = new ArrayList<>();

    public List<Concept> getLikeConcepts() {
        return likeConcepts;
    }

    public void setLikeConcepts(List<Concept> likeConcepts) {
        this.likeConcepts = likeConcepts;
    }

    public List<Concept> getDislikeConcepts() {
        return dislikeConcepts;
    }

    public void setDislikeConcepts(List<Concept> dislikeConcepts) {
        this.dislikeConcepts = dislikeConcepts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
