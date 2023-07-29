package it.jdk.openlab.lovecraftmoviemanagementsql.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_generator", sequenceName = "user_generator", initialValue = 1, allocationSize = 1)
public class UserEntity {

    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String dateOfBirth;
    private String note;
    private String role;
    private String registrationDate;
//    private List<FavoriteMoviesEntity> favoriteMovies;
//    private List<FavoriteBooksEntity> favoriteBooks;
    private int version;

    @Id
    @GeneratedValue(generator = "user_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "birthday")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "_role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "registration_date")
    public String getRegistrationDate() {
        return registrationDate.toString();
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    public List<FavoriteMoviesEntity> getFavoriteMovies() {
//        return favoriteMovies;
//    }
//
//    public void setFavoriteMovies(List<FavoriteMoviesEntity> favoriteMovies) {
//        this.favoriteMovies = favoriteMovies;
//    }
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
//    public List<FavoriteBooksEntity> getFavoriteBooks() {
//        return favoriteBooks;
//    }
//
//    public void setFavoriteBooks(List<FavoriteBooksEntity> favoriteBooks) {
//        this.favoriteBooks = favoriteBooks;
//    }

    @Version
    @Column(name = "user_version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
