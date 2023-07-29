package it.jdk.openlab.lovecraftmoviemanagementsql.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
@SequenceGenerator(name = "book_generator", sequenceName = "book_generator", initialValue = 1, allocationSize = 1)
public class BookEntity {

    private Integer id;
    private String title;
    private String plot;
    private String image;
    private String published;
//    private UserEntity user;
    private int version;

    @Id
    @GeneratedValue(generator = "book_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "book_title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "book_plot")
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Column(name = "book_image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "book_published")
    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    public UserEntity getUser() {
//        return user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }

    @Version
    @Column(name = "book_version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
