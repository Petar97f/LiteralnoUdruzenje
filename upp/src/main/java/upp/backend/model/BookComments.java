package upp.backend.model;

import javax.persistence.*;

@Entity
public class BookComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User betaReader;

    @Column
    private String comment;

    public BookComments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBetaReader() {
        return betaReader;
    }

    public void setBetaReader(User betaReader) {
        this.betaReader = betaReader;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
