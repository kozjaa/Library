package com.example.Library.controller;

import com.example.Library.domain.Book;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController
{

    @Autowired
    BookService bookService;


    @RequestMapping("/books")
    public String getAllBooks(Model model)
    {
        List<Book> allBooks = (List<Book>) bookService.getAllBooks();
        model.addAttribute("books", allBooks);

        return "index";
    }

    @RequestMapping("/newbook")
    public String createBook(Model model)
    {
        model.addAttribute("book", new Book());
        return "bookForm";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String saveBook(@Valid Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            return "bookForm";
        } else {
            bookService.saveBook(book);

        return "redirect:/books";}
    }

    @RequestMapping("book/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id)
    {
        bookService.deleteBook(id);

        return "redirect:/books";
    }


    @RequestMapping("book/{id}")
    public String getBookById(@PathVariable("id") Integer id, Model model)
    {
        Book book = bookService.getBookById(id);

        model.addAttribute("book",  book);
        return "book";
    }

    @RequestMapping("/book/edit/{id}")
    public String editBook(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("book", bookService.getBookById(id));

        return "bookForm";
    }

    @RequestMapping("/book/read/{id}")
    public String isRead(@PathVariable("id") Integer id)
    {
        Book book = bookService.getBookById(id);

        if (book.getRead().equals("Nie"))
            book.setRead("Tak");
        else
            book.setRead("Nie");

        bookService.saveBook(book);

        return "redirect:/books";
    }
}
