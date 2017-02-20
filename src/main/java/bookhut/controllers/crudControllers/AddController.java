package bookhut.controllers.crudControllers;

import bookhut.models.bindingModels.AddBookBindingModel;
import bookhut.services.bookService.BookService;
import bookhut.services.bookService.BookServiceImpl;
import bookhut.validators.SessionValidator;
import bookhut.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add")
public class AddController extends HttpServlet {

    private final BookService bookService;

    public AddController() {
        this.bookService = new BookServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = SessionValidator.getInstance();
        boolean isValid = validator.validate(req);
        if (isValid) {
            req.getRequestDispatcher("/templates/add.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/signup");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String addTrigger = req.getParameter("add");

        if (addTrigger != null) {
            String title = req.getParameter("title").trim();

            if ("".equals(title)) {
                errors.put("Title", "Title must contain at least one symbol!");
            }

            String author = req.getParameter("author").trim();

            if ("".equals(author)) {
                errors.put("Author", "Author must contain at least one symbol!");
            }

            String pageCount = req.getParameter("pages");

            if ("".equals(pageCount)) {
                errors.put("Pages", "Pages count must been at least one!");
            }


            if (errors.isEmpty()) {
                int pages = Integer.parseInt(pageCount);
                AddBookBindingModel addBookBindingModel = new AddBookBindingModel(title, author, pages);
                this.bookService.saveBook(addBookBindingModel);
                resp.sendRedirect("/shelves");
            } else {
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/templates/add.jsp").forward(req, resp);
            }
        }
    }
}
