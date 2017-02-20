package bookhut.validators;

import bookhut.models.viewModels.UserViewModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Validator {
    default boolean validate(HttpServletRequest request) throws IOException, ServletException {
        UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("user");
        return userViewModel != null;
    }
}
