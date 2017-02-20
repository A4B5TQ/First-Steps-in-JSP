package bookhut.controllers.crudControllers;

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

@WebServlet("/shelves/delete/*")
public class DeleteController extends HttpServlet {

    private final BookService bookService;

    public DeleteController() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = SessionValidator.getInstance();
        boolean isValid = validator.validate(req);
        if (isValid) {
            String path = req.getRequestURI();
            int indexOfSlash = path.lastIndexOf('/');

            int indexToDelete = 0;

            if (indexOfSlash != -1) {
                try {
                    indexToDelete = Integer.parseInt(path.substring(indexOfSlash + 1));
                    this.bookService.deleteBookById(indexToDelete);
                    resp.sendRedirect("/shelves");
                } catch (NumberFormatException ignored) {
                    resp.sendRedirect("/shelves");
                }
            }
        } else {
            resp.sendRedirect("/signup");
        }
    }
}
