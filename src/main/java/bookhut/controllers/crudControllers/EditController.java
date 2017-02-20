package bookhut.controllers.crudControllers;

import bookhut.models.bindingModels.EditBookBindingModel;
import bookhut.models.viewModels.BookViewModel;
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

@WebServlet("/shelves/edit/*")
public class EditController extends HttpServlet {

    private final BookService bookService;

    public EditController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = SessionValidator.getInstance();
        boolean isValid = validator.validate(req);
        if (isValid) {
            String path = req.getRequestURI();
            int indexOfSlash = path.lastIndexOf('/');
            int index = 0;
            try {
                index = Integer.parseInt(path.substring(indexOfSlash + 1));
                BookViewModel bookView = this.bookService.getBookById(index);
                req.setAttribute("bookView", bookView);
                req.getRequestDispatcher("/templates/edit.jsp").forward(req,resp);
            } catch (NumberFormatException ignored) {
                resp.sendRedirect("/shelves");
            }
        } else {
            resp.sendRedirect("/signup");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String addTrigger = req.getParameter("edit");

        if (addTrigger != null) {

            String idText = req.getParameter("id").trim();

            if ("".equals(idText)) {
                errors.put("Invalid", "Invalid ID!");
            }

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
                int id = Integer.parseInt(idText);
                int pages = Integer.parseInt(pageCount);
                EditBookBindingModel editBookBindingModel = new EditBookBindingModel(id,title, author, pages);
                this.bookService.editBook(editBookBindingModel);
                resp.sendRedirect("/shelves");
            } else {
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/templates/edit.jsp").forward(req, resp);
            }
        }
    }
}
