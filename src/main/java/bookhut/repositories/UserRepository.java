package bookhut.repositories;

import bookhut.models.entities.User;

public interface UserRepository {

    void createUser(User user);

    User findByUsernameAndPassword(String username, String password);

    Long getNextId();
}
