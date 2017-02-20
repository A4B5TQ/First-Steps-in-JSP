package bookhut.repositories.impl;

import bookhut.models.entities.Book;
import bookhut.repositories.BookRepository;

import java.util.*;
import java.util.stream.Collectors;

public class BookRepositoryImpl implements BookRepository {

    private static BookRepositoryImpl bookRepository = new BookRepositoryImpl();

    private List<Book> books;

    private BookRepositoryImpl() {
        this.books = new LinkedList<>();
    }

    public static BookRepository getInstance() {
        return bookRepository;
    }

    @Override
    public void saveBook(Book book) {
        book.setId(this.getNextId());
        this.books.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.books.stream()
                .filter(x -> x.getDeletedOn() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBookByTitle(String title) {
        Book bookToDelete = this.findBookByTitle(title);
        int index = this.books.indexOf(bookToDelete);
        bookToDelete.setDeletedOn(new Date());
        this.books.set(index,bookToDelete);
    }

    @Override
    public Book findBookByTitle(String title) {
        return this.books.stream()
                .filter(x -> x.getTitle().equals(title))
                .findFirst().orElse(null);
    }

    @Override
    public Long getNextId() {
      return this.books.size() + 1L;
    }

    @Override
    public void deleteBookById(int id) {
        this.books.get(id - 1).setDeletedOn(new Date());
    }
}
