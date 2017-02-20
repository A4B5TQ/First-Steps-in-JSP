package bookhut.services.userService;

import bookhut.models.bindingModels.LoginBindingModel;
import bookhut.models.entities.User;
import bookhut.repositories.UserRepository;
import bookhut.repositories.impl.UserRepositoryImpl;
import org.modelmapper.ModelMapper;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private ModelMapper modelMapper;

    public UserServiceImpl() {
        this.modelMapper = new ModelMapper();

    }

    @Override
    public void createUser(LoginBindingModel loginModel) {
        User user = this.modelMapper.map(loginModel,User.class);
        user.setId(this.userRepository.getNextId());
        this.userRepository.createUser(user);
    }

    @Override
    public User findOneByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username,password);
    }
}
