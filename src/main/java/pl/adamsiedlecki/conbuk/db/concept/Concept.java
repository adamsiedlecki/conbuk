package pl.adamsiedlecki.conbuk.db.concept;

import pl.adamsiedlecki.conbuk.db.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concept {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User author;
    @ManyToMany()
    @JoinTable(
            name = "like_users",
            joinColumns = {@JoinColumn(name = "concept_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> likeUsers = new ArrayList<>();
    @ManyToMany()
    @JoinTable(
            name = "dislike_users",
            joinColumns = {@JoinColumn(name = "concept_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> dislikeUsers = new ArrayList<>();

    public List<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<User> likeUsers) {
        this.likeUsers = likeUsers;
    }

    public List<User> getDislikeUsers() {
        return dislikeUsers;
    }

    public void setDislikeUsers(List<User> dislikeUsers) {
        this.dislikeUsers = dislikeUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", likeUsers=" + likeUsers +
                ", dislikeUsers=" + dislikeUsers +
                '}';
    }
}
