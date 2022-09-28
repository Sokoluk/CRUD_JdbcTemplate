package ru.sokoluk.test.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class Person {
    private int id;

    @NotEmpty(message = "Имя не может быть пустым")
    private String name;
    @Min(message = "Дата должна быть больше 1900 года", value = 1900)
    private int date;

    public Person() {

    }

    public Person(String name, int date) {
        this.name = name;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
