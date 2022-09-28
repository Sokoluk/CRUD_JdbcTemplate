package ru.sokoluk.test.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {
    private int id;
    @NotEmpty(message = "Название не может быть пустым")
    private String title;
    @NotEmpty(message = "Автор не может быть пустым")
    private String author;
    @Min(message = "Год больше 1900",value = 1900)
    @Max(message = "Год меньше 2023",value = 2023)
    private int year;

    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
