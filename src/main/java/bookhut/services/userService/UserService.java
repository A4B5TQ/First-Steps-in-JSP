package bookhut.services.userService;

import bookhut.models.bindingModels.LoginBindingModel;
import bookhut.models.entities.User;

public interface UserService {

    void createUser(LoginBindingModel loginModel);

    User findOneByUsernameAndPassword(String username, String password);
}
