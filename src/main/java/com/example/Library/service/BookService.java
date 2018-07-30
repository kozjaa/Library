package com.example.Library.service;

import com.example.Library.domain.Book;
import com.example.Library.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService() {

    }

    public Collection<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void deleteBook(Integer id)
    {
        bookRepository.deleteBook(id);
    }

    public Book getBookById(Integer id) {
        return bookRepository.getBookById(id);
    }

    public void saveBook(Book book)
    {
        bookRepository.saveBook(book);
    }
}
