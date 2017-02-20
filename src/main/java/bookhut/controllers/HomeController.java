package bookhut.controllers;

import bookhut.validators.SessionValidator;
import bookhut.validators.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator validator = SessionValidator.getInstance();
        boolean isValid = validator.validate(req);
        if(isValid) {
            req.getRequestDispatcher("/templates/home.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/signup");
        }
    }
}
