package repository.interfaces;

import model.User;

import java.util.Optional;

public interface UserRepository {
    void addUser(User user);
    Optional<User> findByEmail(String email);
    void updateUser(User user);
}
