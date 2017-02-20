package bookhut.controllers;

import bookhut.models.entities.User;
import bookhut.models.viewModels.UserViewModel;
import bookhut.services.userService.UserService;
import bookhut.services.userService.UserServiceImpl;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signin")
public class SingInController extends HttpServlet {

    private final UserService userService;

    public SingInController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/signin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String signUpTrigger = req.getParameter("signin");
        if (signUpTrigger != null) {
            String username = req.getParameter("username");
            if ("".equals(username)) {
                errors.put("Username","Username must contain at least one symbol!");
            }
            String password = req.getParameter("password");
            if ("".equals(password)) {
                errors.put("Password","Password must contain at least one symbol!");
            }

            User user = this.userService.findOneByUsernameAndPassword(username,password);

            if (user == null) {
                errors.put("Sign In","Incorrect username or password!");
            }


            if (errors.isEmpty()) {
                UserViewModel userViewModel = new ModelMapper().map(user,UserViewModel.class);
                req.getSession().setAttribute("user",userViewModel);
                resp.sendRedirect("/");
            } else {
                req.setAttribute("errors",errors);
                req.getRequestDispatcher("/templates/signin.jsp").forward(req,resp);
            }
        }
    }
}
