package ru.sokoluk.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sokoluk.test.model.Book;
import ru.sokoluk.test.model.Person;



import java.util.List;


@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> list() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book index(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title,author,year) VALUES (?,?,?)",book.getTitle(),book.getAuthor(),book.getYear());

    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year = ? WHERE id=?",book.getTitle(), book.getAuthor(),book.getYear(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Person gettingTheBookOwner(int id){
       return jdbcTemplate.query("SELECT person.* FROM person JOIN book ON person.id=book.person_id WHERE book.id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void assign(int id,Person person){
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id=?",person.getId(),id);
    }
    public void release(int id){
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id=?",null,id);
    }
}