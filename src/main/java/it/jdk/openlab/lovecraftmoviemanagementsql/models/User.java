package it.jdk.openlab.lovecraftmoviemanagementsql.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.annotations.UserExists;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.annotations.UserNonExistent;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.group.UserValidationGroup.CreateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.group.UserValidationGroup.DeleteValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.user.group.UserValidationGroup.UpdateValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

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
//    private List<Movie> favoriteMovies;
//    private List<Book> favoriteBooks;
    private int version;

    public User() {}

    @Autowired
    public User(Integer id, String username, String name, String surname,
                String email, String password, String dateOfBirth,
                String note, String role, String registrationDate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.note = note;
        this.role = role;
        this.registrationDate = registrationDate;
//        this.favoriteMovies = favoriteMovies;
//        this.favoriteBooks = favoriteBooks;
    }

    @UserNonExistent(groups = {CreateValidationGroup.class})
    @UserExists(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Email(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
//    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

//    public List<Movie> getFavoriteMovies() {
//        return favoriteMovies;
//    }
//
//    public void setFavoriteMovies(List<Movie> favoriteMovies) {
//        this.favoriteMovies = favoriteMovies;
//    }
//
//    public List<Book> getFavoriteBooks() {
//        return favoriteBooks;
//    }
//
//    public void setFavoriteBooks(List<Book> favoriteBooks) {
//        this.favoriteBooks = favoriteBooks;
//    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
