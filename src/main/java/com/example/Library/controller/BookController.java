package com.example.Library.controller;

import com.example.Library.domain.Book;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class BookController {

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks(Model model) {
        List<Book> allBooks = (List<Book>) bookService.getAllBooks();
        Book book = new Book("Adam Mieckiewicz", "Krzyżacy", "Lektura", "Nie");
        allBooks.add(book);

        //response.setHeader("Acces-Control-Allow-Origin", "*");
        //model.addAttribute("books", allBooks);
        //return "index";
        //return ResponseEntity.status(HttpStatus.ACCEPTED).header("Acces-Control-Allow-Origin", "*").body(allBooks);
        return allBooks;
    }

    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public Book createBook(Model model) {
        //model.addAttribute("book", new Book());
        //return "bookForm";
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

            // return "redirect:/books";}
        }
        return true;
    }

    @RequestMapping(value = "book/delete/{id}", method = RequestMethod.GET)
    public void deleteBook(@PathVariable("id") Integer id) {

        bookService.deleteBook(id);
        //return "redirect:/books";
    }


    @RequestMapping(value = "book/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);

        //model.addAttribute("book",  book);
        //return "book";
        return book;
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public Book editBook(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        //model.addAttribute("book", bookService.getBookById(id));
        //return "bookForm";
        return book;
    }

    @RequestMapping(value = "/book/read/{id}", method = RequestMethod.GET)
    public boolean isRead(@PathVariable("id") Integer id) {

        Book book = bookService.getBookById(id);

        if (book.getRead().equals("Nie"))
            book.setRead("Tak");

        else
            book.setRead("Nie");

        bookService.saveBook(book);

        return true;
        //return "redirect:/books";
    }
}
