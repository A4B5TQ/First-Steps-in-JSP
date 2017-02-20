package bookhut.repositories.impl;

import bookhut.models.entities.User;
import bookhut.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository userRepository = new UserRepositoryImpl();

    private List<User> users;

    private UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        return userRepository;
    }

    @Override
    public void createUser(User user) {
        this.users.add(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    @Override
    public Long getNextId() {
        return this.users.size() + 1L;
    }
}
