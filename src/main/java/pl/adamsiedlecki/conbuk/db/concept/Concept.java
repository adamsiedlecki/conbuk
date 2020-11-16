package pl.adamsiedlecki.conbuk.db.concept;

import pl.adamsiedlecki.conbuk.db.user.User;

import javax.persistence.*;

@Entity
public class Concept {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "author_id")
    private User author;
    private Long likeCounter;
    private Long dislikeCounter;

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

    public Long getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(Long likeCounter) {
        this.likeCounter = likeCounter;
    }

    public Long getDislikeCounter() {
        return dislikeCounter;
    }

    public void setDislikeCounter(Long dislikeCounter) {
        this.dislikeCounter = dislikeCounter;
    }
}
