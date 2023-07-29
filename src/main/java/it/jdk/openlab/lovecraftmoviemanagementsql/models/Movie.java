package it.jdk.openlab.lovecraftmoviemanagementsql.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.annotations.MovieExists;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.annotations.MovieNonExistent;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.group.MovieValidationGroup.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {

    private Integer id;
    private String title;
    private String description;
    private String image;
    private int version;

    public Movie() {}
    @Autowired
    public Movie(Integer id, String title, String description, String image, Integer version) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.version = version;
    }

    @MovieNonExistent(groups = {CreateValidationGroup.class})
    @MovieExists(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}
