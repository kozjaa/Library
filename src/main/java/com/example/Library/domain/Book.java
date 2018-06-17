package com.example.Library.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Musisz wypełnić pole")
    private String author;

    @NotBlank(message = "Musisz wypełnić pole")
    private String title;

    private String description;

    private String read = "Nie";

    public Book()
    {}

    public Book(Integer id, String author, String title, String description, String read)
    {
        this.id = id;
        this.read = read;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public Book(String author, String title, String description, String read)
    {
        this.read = read;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Autor: " + author + "Tytuł: " + title + "Opis: " + description + "Czy przeczytana: " + read;
    }
}
