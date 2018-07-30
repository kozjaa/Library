package com.example.Library.controller;

import com.example.Library.domain.Book;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        List<Book> allBooks = (List<Book>) bookService.getAllBooks();
        return allBooks;
    }

    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public Book createBook() {
        return new Book();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public boolean saveBook(@Valid @RequestBody Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            return false;
        } else {
            bookService.saveBook(book);
        }
        return true;
    }

    @RequestMapping(value = "book/delete/{id}", method = RequestMethod.GET)
    public boolean deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return true;
    }

    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("id") Integer id) {
        Book book = bookService.getBookById(id);
        return book;
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public Book editBook(@PathVariable("id") Integer id) {
        Book book = bookService.getBookById(id);
        return book;
    }

    @RequestMapping(value = "/book/read/{id}", method = RequestMethod.GET)
    public boolean isRead(@PathVariable("id") Integer id) {
        Book book = bookService.getBookById(id);
        if (book.getRead().equals("Nie")){
            book.setRead("Tak");}
        else{
            book.setRead("Nie");}
            bookService.saveBook(book);
        return true;
    }
}
