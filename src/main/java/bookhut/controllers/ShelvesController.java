package bookhut.controllers;

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

@WebServlet(value = "/shelves")
public class ShelvesController extends HttpServlet {

    private final BookService bookService;

    public ShelvesController() {
        this.bookService = new BookServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = SessionValidator.getInstance();
        boolean isValid = validator.validate(req);
        if(isValid) {
            req.setAttribute("books",this.bookService.getAllBooks());
            req.getRequestDispatcher("/templates/shelves.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/signup");
        }
    }
}
