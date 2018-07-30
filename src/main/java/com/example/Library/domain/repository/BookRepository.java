package com.example.Library.domain.repository;


import com.example.Library.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public BookRepository() {

    }

    public Collection<Book> getAllBooks()
    {
        return entityManager.createQuery("from Book", Book.class).getResultList();
    }

    public Book getBookById(Integer id)
    {
        return entityManager.find(Book.class,id);
    }

    @Transactional
    public void deleteBook(Integer id) {
        Book book = new Book();
        book.setId(id);
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    @Transactional
    public void saveBook(Book book)
    {
        entityManager.merge(book);
    }
}
