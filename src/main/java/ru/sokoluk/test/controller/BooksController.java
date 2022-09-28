package ru.sokoluk.test.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sokoluk.test.dao.BookDAO;
import ru.sokoluk.test.dao.PersonDAO;
import ru.sokoluk.test.model.Book;
import ru.sokoluk.test.model.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("books", bookDAO.list());
        return "book/list";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.index(id));
        Person person1 = bookDAO.gettingTheBookOwner(id);
        if(person1 != null){
            model.addAttribute("owner",person1);
        }else {
            model.addAttribute("people", personDAO.list());
        }

        return "book/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.index(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,@ModelAttribute("person") Person person){
        bookDAO.assign(id,person);
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

}
