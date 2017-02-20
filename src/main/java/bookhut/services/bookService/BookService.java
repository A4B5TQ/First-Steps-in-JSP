package bookhut.services.bookService;

import bookhut.models.bindingModels.AddBookBindingModel;
import bookhut.models.bindingModels.EditBookBindingModel;
import bookhut.models.viewModels.BookViewModel;

import java.util.List;

public interface BookService {

    void saveBook(AddBookBindingModel addBookBindingModel);

    List<BookViewModel> getAllBooks();

    BookViewModel findBookByTitle(String title);

    void deleteBookByTitle(String title);

    boolean deleteBookById(int id);

    BookViewModel getBookById(int index);

    void editBook(EditBookBindingModel model);
}
