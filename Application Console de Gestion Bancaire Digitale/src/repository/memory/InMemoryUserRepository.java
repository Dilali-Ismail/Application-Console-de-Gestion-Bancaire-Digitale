package repository.memory;

import model.User;
import repository.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
