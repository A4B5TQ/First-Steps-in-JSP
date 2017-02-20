package bookhut.controllers;

import bookhut.models.bindingModels.LoginBindingModel;
import bookhut.services.userService.UserService;
import bookhut.services.userService.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signup")
public class SingUpController extends HttpServlet {

    private final UserService userService;

    public SingUpController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String signUpTrigger = req.getParameter("signup");
        if (signUpTrigger != null) {
            String username = req.getParameter("username").trim();
            if ("".equals(username)) {
                errors.put("Username","Username must contain at least one symbol!");
            }
            String password = req.getParameter("password").trim();
            if ("".equals(password)) {
                errors.put("Password","Password must contain at least one symbol!");
            }

            if (errors.isEmpty()) {
                LoginBindingModel bindingModel = new LoginBindingModel(username,password);
                this.userService.createUser(bindingModel);
                resp.sendRedirect("/signin");
            } else {
                req.setAttribute("errors",errors);
                req.getRequestDispatcher("/templates/signup.jsp").forward(req,resp);
            }
        }
    }
}
