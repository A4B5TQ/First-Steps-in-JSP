package bookhut.services.bookService;

import bookhut.models.bindingModels.AddBookBindingModel;
import bookhut.models.bindingModels.EditBookBindingModel;
import bookhut.models.entities.Book;
import bookhut.models.viewModels.BookViewModel;
import bookhut.repositories.BookRepository;
import bookhut.repositories.impl.BookRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository = BookRepositoryImpl.getInstance();
    private ModelMapper modelMapper;

    public BookServiceImpl() {
        this.modelMapper = new ModelMapper();
        this.initMapping();
    }

    @Override
    public void saveBook(AddBookBindingModel addBookBindingModel) {
        Book book = this.modelMapper.map(addBookBindingModel, Book.class);
        this.bookRepository.saveBook(book);
    }

    @Override
    public List<BookViewModel> getAllBooks() {
        return this.bookRepository.getAllBooks().stream()
                .map(b -> this.modelMapper.map(b,BookViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookViewModel findBookByTitle(String title) {
        Book book = this.bookRepository.findBookByTitle(title);
        return this.modelMapper.map(book,BookViewModel.class);
    }

    @Override
    public void deleteBookByTitle(String title) {
        this.bookRepository.deleteBookByTitle(title);
    }

    @Override
    public boolean deleteBookById(int id) {
        try {
            this.bookRepository.deleteBookById(id);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public BookViewModel getBookById(int index) {
        Book book = null;
        try {
            book = this.bookRepository.getAllBooks()
                    .stream().filter(b -> b.getId().equals(Long.parseLong(index+ "")))
                    .findFirst().orElse(null);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return this.modelMapper.map(book,BookViewModel.class);
    }

    @Override
    public void editBook(EditBookBindingModel model) {
      Book book = this.bookRepository.getAllBooks()
              .stream().filter(b -> b.getId().equals(Long.parseLong(model.getId() + "")))
              .findFirst().orElse(null);
      book.setPages(model.getPages());
      book.setAuthor(model.getAuthor());
      book.setTitle(model.getTitle());
    }

    private void initMapping(){
        PropertyMap<AddBookBindingModel,Book> bookMap = new PropertyMap<AddBookBindingModel, Book>() {
            @Override
            protected void configure() {
                map().setTitle(source.getTitle());
                map().setAuthor(source.getAuthor());
                map().setPages(source.getPages());
                map().setCreationDate(new Date());
            }
        };
        this.modelMapper.addMappings(bookMap);

        PropertyMap<Book,BookViewModel> bookMapping = new PropertyMap<Book, BookViewModel>() {
            @Override
            protected void configure() {
                map().setTitle(source.getTitle());
                map().setPages(source.getPages());
                map().setAuthor(source.getAuthor());
                map().setId(source.getId());
            }
        };
        this.modelMapper.addMappings(bookMapping);
    }
}
