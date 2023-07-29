package it.jdk.openlab.lovecraftmoviemanagementsql.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.annotations.BookExists;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.annotations.BookNonExistent;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.group.BookValidationGroup.DeleteValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.group.BookValidationGroup.CreateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.book.group.BookValidationGroup.UpdateValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private Integer id;
    private String title;
    private String plot;
    private String image;
    private String published;
    private int version;

    public Book() {}

    public Book(Integer id, String title, String plot, String image, String published, int version) {
        this.id = id;
        this.title = title;
        this.plot = plot;
        this.image = image;
        this.published = published;
        this.version = version;
    }

    @BookNonExistent(groups = {CreateValidationGroup.class})
    @BookExists(groups = {UpdateValidationGroup.class, DeleteValidationGroup.class})
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
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
