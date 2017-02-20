package bookhut.repositories;

import bookhut.models.entities.Book;

import java.util.List;

public interface BookRepository {

    void saveBook(Book book);

    List<Book> getAllBooks();

    void deleteBookByTitle(String title);

    Book findBookByTitle(String title);

    Long getNextId();

    void deleteBookById(int id);
}
