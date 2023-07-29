package it.jdk.openlab.lovecraftmoviemanagementsql.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
@SequenceGenerator(name = "movie_generator", sequenceName = "movie_generator", initialValue = 1, allocationSize = 1)
public class MovieEntity {

    private Integer id;
    private String title;
    private String description;
    private String image;
//    private UserEntity user;
    private int version;

    @Id
    @GeneratedValue(generator = "movie_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "movie_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "movie_title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "movie_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "movie_image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    @Column(name = "movie_version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
